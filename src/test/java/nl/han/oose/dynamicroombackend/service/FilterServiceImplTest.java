package nl.han.oose.dynamicroombackend.service;

import nl.han.oose.dynamicroombackend.dto.RoomDTO;
import nl.han.oose.dynamicroombackend.exception.WrongParametersException;
import nl.han.oose.dynamicroombackend.dto.FilterDTO;
import nl.han.oose.dynamicroombackend.model.Facility;
import nl.han.oose.dynamicroombackend.model.Reservation;
import nl.han.oose.dynamicroombackend.model.Room;
import nl.han.oose.dynamicroombackend.repository.ReservationRepository;
import nl.han.oose.dynamicroombackend.repository.RoomRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class FilterServiceImplTest {

    @InjectMocks
    FilterServiceImpl testService;

    //For later use
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    RoomRepository roomRepository;

    @Mock
    RoomService roomService;

    @Mock
    ReservationRepository reservationRepository;

    @Mock
    ModelMapper mapper;

    @Test
    public void applyFilterNoFacilitiesAndWingCode() throws Exception {
        //setup
        List<Room> filteredRoomList = new ArrayList<>();
        filteredRoomList.add(new Room());
        FilterDTO request = new FilterDTO();
        List<Reservation> reservations = new ArrayList<>();
        RoomDTO[] roomDTOS = new RoomDTO[1];

        when(roomRepository.filterRoomsNoExtraParams(request.getBeginTime(),
                request.getEndTime(), request.getDate(), request.getCapacity()))
                .thenReturn(filteredRoomList);
        when(mapper.map(filteredRoomList, RoomDTO[].class)).thenReturn(roomDTOS);
        when(reservationRepository.findAll()).thenReturn(reservations);

        //execute
        List<RoomDTO> results = testService.applyFilter(request);


        //checking
        verify(roomRepository).filterRoomsNoExtraParams(request.getBeginTime(),
                request.getEndTime(), request.getDate(), request.getCapacity());
        assertEquals(filteredRoomList.size(), results.size());
    }


    @Test
    public void applyFilterWrongParamsExceptionThrown() {
        //checking
        exception.expect(WrongParametersException.class);
        exception.expectMessage("Parameters incorrect");

        //executing
        testService.applyFilter(null);
    }

//    @Test
//    public void applyFilterWithFacilities() {
//        //setup
//        List<Room> filteredRoomList = new ArrayList<>();
//        List<Facility> facilities = new ArrayList<>();
//        List<String> facilityTypes = new ArrayList<>();
//        facilityTypes.add("fac1");
//        Room r = new Room();
//        filteredRoomList.add(r);
//        facilities.add(new Facility());
//        r.setFacilities(facilities);
//        FilterDTO request = new FilterDTO();
//
//        request.setFacilities(facilityTypes);
//        List<Reservation> reservations = new ArrayList<>();
//        RoomDTO[] roomDTOS = new RoomDTO[1];
//
//        when(roomRepository.filterRoomsFacilities(request.getBeginTime(), request.getEndTime(),
//                request.getDate(), request.getCapacity(), request.getFacilities()))
//                .thenReturn(filteredRoomList);
//        when(mapper.map(filteredRoomList, RoomDTO[].class)).thenReturn(roomDTOS);
//        when(reservationRepository.findAll()).thenReturn(reservations);
//
//        //execute
//        List<RoomDTO> results = testService.applyFilter(request);
//
//
//        //checking
//        verify(roomRepository).filterRoomsFacilities(request.getBeginTime(), request.getEndTime(),
//                request.getDate(), request.getCapacity(), request.getFacilities());
//        assertEquals(filteredRoomList.size(), results.size());
//    }

//    @Test
//    public void applyFilterWithFacilitiesAndWings() {
//        //setup
//        List<Room> filteredRoomList = new ArrayList<>();
//        List<Facility> facilities = new ArrayList<>();
//        List<String> facilityTypes = new ArrayList<>();
//        facilityTypes.add("fac1");
//        Room r = new Room();
//        filteredRoomList.add(r);
//        facilities.add(new Facility());
//        r.setFacilities(facilities);
//        FilterDTO request = new FilterDTO();
//        request.setWingCode("code");
//        request.setFacilities(facilityTypes);
//        List<Reservation> reservations = new ArrayList<>();
//        RoomDTO[] roomDTOS = new RoomDTO[1];
//
//        when(roomRepository.filterRoomsFacilitiesAndWing(request.getBeginTime(), request.getEndTime(),
//                request.getDate(), request.getCapacity(), request.getFacilities(), request.getWingCode()))
//                .thenReturn(filteredRoomList);
//        when(mapper.map(filteredRoomList, RoomDTO[].class)).thenReturn(roomDTOS);
//        when(reservationRepository.findAll()).thenReturn(reservations);
//
//        //execute
//        List<RoomDTO> results = testService.applyFilter(request);
//
//
//        //checking
//        verify(roomRepository).filterRoomsFacilitiesAndWing(request.getBeginTime(), request.getEndTime(),
//                request.getDate(), request.getCapacity(), request.getFacilities(), request.getWingCode());
//        assertEquals(filteredRoomList.size(), results.size());
//    }

    @Test
    public void applyFilterWings() {
        //setup
        List<Room> filteredRoomList = new ArrayList<>();
        List<Facility> facilities = new ArrayList<>();
        Room r = new Room();
        filteredRoomList.add(r);
        facilities.add(new Facility());
        r.setFacilities(facilities);
        FilterDTO request = new FilterDTO();
        request.setWingCode("code");
        List<Reservation> reservations = new ArrayList<>();
        RoomDTO[] roomDTOS = new RoomDTO[1];

        when(roomRepository.filterRoomsWing(request.getBeginTime(), request.getEndTime(),
                request.getDate(), request.getCapacity(), request.getWingCode()))
                .thenReturn(filteredRoomList);
        when(mapper.map(filteredRoomList, RoomDTO[].class)).thenReturn(roomDTOS);
        when(reservationRepository.findAll()).thenReturn(reservations);

        //execute
        List<RoomDTO> results = testService.applyFilter(request);


        //checking
        verify(roomRepository).filterRoomsWing(request.getBeginTime(), request.getEndTime(),
                request.getDate(), request.getCapacity(), request.getWingCode());
        assertEquals(filteredRoomList.size(), results.size());
    }

}