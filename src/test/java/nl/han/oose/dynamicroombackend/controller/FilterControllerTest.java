package nl.han.oose.dynamicroombackend.controller;

import nl.han.oose.dynamicroombackend.dto.RoomDTO;
import nl.han.oose.dynamicroombackend.exception.WrongParametersException;
import nl.han.oose.dynamicroombackend.dto.FilterDTO;
import nl.han.oose.dynamicroombackend.model.Room;
import nl.han.oose.dynamicroombackend.service.FilterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FilterControllerTest {

    @Mock
    FilterService service;

    @InjectMocks
    FilterController testController;

    @Test
    public void applyFiltersTestResults() {
        //setup
        List<RoomDTO> roomList = new ArrayList<>();
        roomList.add(new RoomDTO());
        FilterDTO request = mock(FilterDTO.class);
        when(service.applyFilter(request)).thenReturn(roomList);


        //execute
        ResponseEntity entity = testController
                .applyFiltersTestNoResults(request);

        //check
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertTrue(entity.hasBody());
        assertEquals(entity.getBody(), roomList);
        verify(service).applyFilter(request);
    }

    @Test
    public void applyFiltersExceptionHandler() {
        //setup
        WrongParametersException e = new WrongParametersException("Message");

        //execute
        ResponseEntity entity = testController.applyFiltersExceptionHandler(e);

        //check
        assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
        assertTrue(entity.hasBody());
        assertTrue(e.getMessage().equals(entity.getBody()));
    }
}