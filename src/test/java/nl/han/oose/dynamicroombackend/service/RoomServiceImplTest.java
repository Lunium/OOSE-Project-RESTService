package nl.han.oose.dynamicroombackend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.han.oose.dynamicroombackend.dto.RoomDTO;
import nl.han.oose.dynamicroombackend.model.Facility;
import nl.han.oose.dynamicroombackend.model.Reservation;
import nl.han.oose.dynamicroombackend.model.Room;
import nl.han.oose.dynamicroombackend.model.Wing;
import nl.han.oose.dynamicroombackend.model.id.RoomId;
import nl.han.oose.dynamicroombackend.repository.FacilityRepository;
import nl.han.oose.dynamicroombackend.repository.RoomRepository;
import nl.han.oose.dynamicroombackend.repository.WingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoomServiceImplTest {
    @InjectMocks
    RoomServiceImpl testService;

    @Mock
    FacilityRepository facilityRepository;

    @Mock
    WingRepository wingRepository;

    @Mock
    ModelMapper mapper;

    @Mock
    RoomRepository roomRepository;

    @Test
    public void findAllFacilities() throws Exception {
        List<Facility> facilities = Arrays.asList(new Facility(), new Facility());
        when(facilityRepository.findAll()).thenReturn(facilities);

        List<Facility> returnedFacilities = testService.findAllFacilities();
        verify(facilityRepository).findAll();
        assertTrue(returnedFacilities.size() == 2);
        assertEquals(facilities, returnedFacilities);
    }

    @Test
    public void findAllWings() throws Exception {
        List<Wing> wings = Arrays.asList(new Wing(), new Wing());
        when(wingRepository.findAll()).thenReturn(wings);

        List<Wing> result = testService.findAllWings();

        verify(wingRepository).findAll();
        assertTrue(result.size() == 2);
        assertEquals(wings, result);
    }

    @Test
    public void addReservationsToRoomDto() throws Exception {
        List<Reservation> reservations = new ArrayList<>();
        RoomDTO[] rooms = new RoomDTO[2];

        Reservation r = new Reservation();
        Reservation r2 = new Reservation();
        r.setRoomCode("R 16");
        r.setWingCode("D24");
        r2.setRoomCode("R 16");
        r2.setWingCode("D24");

        reservations.add(r);
        reservations.add(r2);

        RoomDTO room = new RoomDTO();
        RoomDTO room2 = new RoomDTO();
        room.setRoomCode("R 16");
        room.setWingCode("D24");
        room2.setRoomCode("R 17");
        room2.setWingCode("D574");

        rooms[0] = room;
        rooms[1] = room2;

        testService.addReservationsToRoomDto(rooms, reservations);

        assertEquals(2,  room.getRoomReservations().size());
        assertEquals(0, room2.getRoomReservations().size());
    }

    @Test
    public void findRoom() throws Exception {
        Room r = mock(Room.class);
        RoomId id = mock(RoomId.class);
        when(roomRepository.findOne(id)).thenReturn(r);

        Room result = testService.findRoom(id);

        verify(roomRepository).findOne(id);
        assertEquals(r , result);
    }
}