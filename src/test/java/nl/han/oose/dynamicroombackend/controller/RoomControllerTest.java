package nl.han.oose.dynamicroombackend.controller;

import nl.han.oose.dynamicroombackend.model.Facility;
import nl.han.oose.dynamicroombackend.model.Room;
import nl.han.oose.dynamicroombackend.model.Wing;
import nl.han.oose.dynamicroombackend.model.id.RoomId;
import nl.han.oose.dynamicroombackend.service.RoomService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoomControllerTest {

    @InjectMocks
    RoomController testController;

    @Mock
    RoomService roomService;


    @Test
    public void getAllFacilities() throws Exception {
        List<Facility> facilities = new ArrayList<>();
        when(roomService.findAllFacilities()).thenReturn(facilities);
        ResponseEntity response = testController.getAllFacilities();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        assertEquals(facilities, response.getBody());
    }

    @Test
    public void getWings() throws Exception {
        List<Wing> wings = new ArrayList<>();
        wings.add(new Wing());
        when(roomService.findAllWings()).thenReturn(wings);

        ResponseEntity<List<Wing>> response = testController.getAllWings();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        assertEquals(wings, response.getBody());
        assertEquals(wings.size(), response.getBody().size());
    }

    @Test
    public void getRoomInformation() throws Exception {
        Room room = new Room();
        RoomId id = mock(RoomId.class);
        when(roomService.findRoom(id)).thenReturn(room);

        ResponseEntity<Room> response = testController.getRoomInformation(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        assertEquals(room, response.getBody());
    }



}