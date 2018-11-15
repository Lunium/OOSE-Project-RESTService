package nl.han.oose.dynamicroombackend.service;

import nl.han.oose.dynamicroombackend.dto.ReservationDTO;
import nl.han.oose.dynamicroombackend.dto.RoomDTO;
import nl.han.oose.dynamicroombackend.model.Reservation;
import nl.han.oose.dynamicroombackend.model.Room;
import nl.han.oose.dynamicroombackend.model.id.ReservationId;
import nl.han.oose.dynamicroombackend.repository.ReservationRepository;
import nl.han.oose.dynamicroombackend.repository.RoomRepository;
import nl.han.oose.dynamicroombackend.util.AuthenticationHelper;
import nl.han.oose.dynamicroombackend.util.DateHelper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceImplTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    RoomRepository roomRepository;

    @Mock
    RoomService roomService;

    @Mock
    List<RoomDTO> validRoomList;

    @Mock
    List<Room> roomList;


    @Mock
    AuthenticationHelper authenticationHelper;

    @Mock
    MailMessagingServiceImpl mailMessagingService;

    @Mock
    DateHelper dateHelper;

    @Mock
    ModelMapper mapper;

    @Mock
    List<Reservation> validReservationList;

    @Mock
    ReservationRepository reservationRepository;

    @InjectMocks
    ReservationServiceImpl service;

    String roomCode = "1.13";
    String wingCode = "R26 D";

    @Before
    public void init(){
        when(reservationRepository
                .findByRoomCodeAndWingCode(roomCode, wingCode))
                .thenReturn(validReservationList);
    }

    @Test
    public void findAllReservationForDayIfGetList(){
        RoomDTO[] roomResults = {new RoomDTO(), new RoomDTO()};
        when(reservationRepository.findAllOfToday()).thenReturn(validReservationList);
        when(roomRepository.findAll()).thenReturn(roomList);
        when(mapper.map(roomList, RoomDTO[].class)).thenReturn(roomResults);

        List<RoomDTO> testRooms = service.findAllReservationForDay();

        verify(roomRepository, times(1)).findAll();
        verify(mapper).map(roomList, RoomDTO[].class);
        verify(reservationRepository).findAllOfToday();

        assertEquals(roomResults.length, testRooms.size());
    }

    @Test
    public void findAllReservationsForRoomForTodayIfGetList(){
        List<Reservation> testRooms = service
                .findAllReservationsForRoomForToday(roomCode, wingCode);

        verify(reservationRepository, times(1))
                .findByRoomCodeAndWingCode(roomCode, wingCode);

        assertEquals(validReservationList, testRooms);
    }


    @Test
    public void AddReservationWithReservationRepository(){
        ReservationId rId = Mockito.mock(ReservationId.class);
        Reservation r = new Reservation();

        when(mapper.map(r, ReservationId.class)).thenReturn(rId);
        when(reservationRepository.exists(rId)).thenReturn(false);
        when(reservationRepository.saveAndFlush(r)).thenReturn(r);

        boolean success = service.addReservation(r);
        verify(reservationRepository).saveAndFlush(r);
        verify(reservationRepository).exists(rId);

        assertTrue(success);
    }

    @Test
    public void AddReservationWhenObjectIsNull(){
        Reservation r = null;
        boolean success = service.addReservation(r);
        assertFalse(success);
    }

    @Test
    public void AddReservationWhenObjectExists(){
        Reservation r = mock(Reservation.class);
        ReservationId rId = Mockito.mock(ReservationId.class);
        when(mapper.map(r, ReservationId.class)).thenReturn(rId);
        when(reservationRepository.exists(rId)).thenReturn(true);

        boolean success = service.addReservation(r);

        verify(reservationRepository).exists(rId);
        assertFalse(success);
    }

    @Test
    public void findAllReservationOfWeekSuccess(){
        List<Reservation> result = new ArrayList<>();

        when(reservationRepository.findAllOfWeek(anyString(), anyString(), any(), any())).thenReturn(result);

        List<Reservation> reservations = service.findAllReservationOfWeek(33, 2, "D 1.11", "R 27");

        assertNotNull(reservations);

        verify(reservationRepository).findAllOfWeek(anyString(), anyString(), any(), any());
    }

    @Test
    public void findAllReservationOfWeekFailure(){
        when(reservationRepository.findAllOfWeek(anyString(), anyString(), any(), any())).thenThrow(RuntimeException.class);

        List<Reservation> reservations = service.findAllReservationOfWeek(33, 2, "D 1.11", "R 27");

        assertNull(reservations);

        verify(reservationRepository).findAllOfWeek(anyString(), anyString(), any(), any());
    }

    @Test
    public void cancelReservationParameterIsNull() throws Exception {
        assertFalse(service.cancelReservation(null));
    }
    @Test
    public void cancelReservationIsNull() throws Exception {
        ReservationId rId = mock(ReservationId.class);
        ReservationDTO dto = mock(ReservationDTO.class);
        when(mapper.map(dto, ReservationId.class)).thenReturn(rId);
        when(reservationRepository.findOne(rId)).thenReturn(null);

        boolean success = service.cancelReservation(dto);

        verify(mapper).map(dto, ReservationId.class);
        verify(reservationRepository).findOne(rId);
        assertFalse(success);
    }
    @Test
    public void cancelReservationParameterSuccess() throws Exception {
        ReservationId rId = mock(ReservationId.class);
        Reservation r = new Reservation();
        r.setStatus(Reservation.STATUS_BOOKED);
        ReservationDTO dto = mock(ReservationDTO.class);
        when(mapper.map(dto, ReservationId.class)).thenReturn(rId);
        when(reservationRepository.findOne(rId)).thenReturn(r);

        boolean success = service.cancelReservation(dto);

        verify(mapper).map(dto, ReservationId.class);
        verify(reservationRepository).findOne(rId);
        assertTrue(success);
    }

    @Test
    public void getAllActualReservationByUsername() throws Exception {
        String username = "username";
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation());
        reservations.add(new Reservation());
        when(reservationRepository.findActualReservationByUsername(username))
                .thenReturn(reservations);

        List<Reservation> result = service.getAllActualReservationByUsername(username);


        verify(reservationRepository).findActualReservationByUsername(username);
        assertTrue(reservations.size() == result.size());
        assertEquals(reservations, result);
    }
}
