package nl.han.oose.dynamicroombackend.controller;

import nl.han.oose.dynamicroombackend.dto.CheckInOutDTO;
import nl.han.oose.dynamicroombackend.service.AuthenticationService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class AuthenticationControllerTest {

    //For later use
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    AuthenticationService service;

    @InjectMocks
    AuthenticationController testController;

    @Test
    public void checkInTestFailed() throws Exception {
        //setup
        CheckInOutDTO authObj = Mockito.mock(CheckInOutDTO.class);
        when(service.checkIn(authObj)).thenReturn(false);

        //execute
        ResponseEntity<Boolean> response = testController.checkIn(authObj);

        //check
        verify(service).checkIn(authObj);
        assertTrue(response.hasBody());
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertFalse(response.getBody());
    }

    @Test
    public void checkInTestSuccess() throws Exception {
        //setup
        CheckInOutDTO authObj = Mockito.mock(CheckInOutDTO.class);
        when(service.checkIn(authObj)).thenReturn(true);

        //execute
        ResponseEntity<Boolean> response = testController.checkIn(authObj);

        //check
        verify(service).checkIn(authObj);
        assertTrue(response.hasBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());
    }

    @Test
    public void checkOutTestSuccess() throws Exception {
        //setup
        CheckInOutDTO authObj = Mockito.mock(CheckInOutDTO.class);
        when(service.checkOut(authObj)).thenReturn(true);

        //execute
        ResponseEntity<String> response = testController.checkOut(authObj);

        //check
        verify(service).checkOut(authObj);
        assertTrue(response.hasBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().equals("Reservering is uitgecheckt"));
    }

    @Test
    public void checkOutTestFailed() throws Exception {
        //setup
        CheckInOutDTO authObj = Mockito.mock(CheckInOutDTO.class);
        when(service.checkOut(authObj)).thenReturn(false);

        //execute
        ResponseEntity<String> response = testController.checkOut(authObj);

        //check
        verify(service).checkOut(authObj);
        assertTrue(response.hasBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().equals("Reservering kan niet worden uitgecheckt"));
    }




}