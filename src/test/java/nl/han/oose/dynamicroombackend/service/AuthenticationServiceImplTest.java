package nl.han.oose.dynamicroombackend.service;

import nl.han.oose.dynamicroombackend.dto.CheckInOutDTO;
import nl.han.oose.dynamicroombackend.model.Reservation;
import nl.han.oose.dynamicroombackend.repository.ReservationRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceImplTest {

    //For later use
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    ReservationRepository repository;

    @InjectMocks
    AuthenticationServiceImpl testService;


    @Test
    public void checkInTestSuccess() throws Exception {
        //setup
        Reservation r = Mockito.mock(Reservation.class);
        CheckInOutDTO authObj = new CheckInOutDTO();
        authObj.setPinCode(656543);
        authObj.setRoomCode("D 1.22");
        authObj.setWingCode("R 27");
        when(repository.checkInReservation(authObj.getRoomCode(), authObj.getWingCode(), authObj.getPinCode())).thenReturn(r);

        //execute
        boolean success = testService.checkIn(authObj);

        //check
        assertTrue(success);
        verify(repository).checkInReservation(authObj.getRoomCode(), authObj.getWingCode(), authObj.getPinCode());
    }

    @Test
    public void checkInTestFailed() throws Exception {
        //setup
        CheckInOutDTO authObj = new CheckInOutDTO();
        authObj.setPinCode(656543);
        authObj.setRoomCode("D 1.22");
        authObj.setWingCode("R 27");
        when(repository.checkInReservation(authObj.getRoomCode(), authObj.getWingCode(), authObj.getPinCode())).thenReturn(null);

        //execute
        boolean success = testService.checkIn(authObj);

        //check
        assertFalse(success);
        verify(repository).checkInReservation(authObj.getRoomCode(), authObj.getWingCode(), authObj.getPinCode());
    }

    @Test
    public void checkOutTestFailed() throws Exception {
        //setup
        CheckInOutDTO authObj = new CheckInOutDTO();
        authObj.setRoomCode("D 1.22");
        authObj.setWingCode("R 27");
        when(repository.checkOutReservation(authObj.getRoomCode(), authObj.getWingCode())).thenReturn(null);

        //execute
        boolean success = testService.checkOut(authObj);

        //check
        assertFalse(success);
        verify(repository).checkOutReservation(authObj.getRoomCode(), authObj.getWingCode());
    }
    @Test
    public void checkOutTestSuccess() throws Exception {
        //setup
        CheckInOutDTO authObj = new CheckInOutDTO();
        Reservation r = new Reservation();
        authObj.setRoomCode("D 1.22");
        authObj.setWingCode("R 27");
        when(repository.checkOutReservation(authObj.getRoomCode(), authObj.getWingCode())).thenReturn(r);

        //execute
        boolean success = testService.checkOut(authObj);

        //check
        assertTrue(success);
        verify(repository).checkOutReservation(authObj.getRoomCode(), authObj.getWingCode());
    }

}