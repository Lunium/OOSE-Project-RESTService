package nl.han.oose.dynamicroombackend.controller;


import nl.han.oose.dynamicroombackend.controller.ReservationController;
import nl.han.oose.dynamicroombackend.dto.ReservationDTO;
import nl.han.oose.dynamicroombackend.dto.RoomDTO;
import nl.han.oose.dynamicroombackend.model.Reservation;
import nl.han.oose.dynamicroombackend.model.Room;
import nl.han.oose.dynamicroombackend.service.ReservationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class  ReservationControllerTest {


    @InjectMocks
    ReservationController reservationController;

    @Mock
    ReservationService reservationService;

    @Mock
    List<RoomDTO> validRoomList;

    @Mock
    List<Reservation> validReservationList;

    String roomCode = "1.13";
    String wingCode = "R26 D";

    @Mock
    Reservation reservation;

    @Before
    public void init() {
        when(reservationService.findAllReservationForDay()).thenReturn(validRoomList);
        when(reservationService.findAllReservationsForRoomForToday(roomCode, wingCode)).thenReturn(validReservationList);
    }


    @Test
    public void findAllReservationForDayIfGetList(){
        List<RoomDTO> testRooms = reservationController.getAllReservationForDay();
        verify(reservationService, times(1)).findAllReservationForDay();
        assertEquals(validRoomList, testRooms);
    }

    @Test
    public void findAllReservationsForRoomForTodayIfGetList(){
        List<Reservation> testRooms = reservationController
                .getCurrentScheduleRoom(roomCode, wingCode);
        verify(reservationService, times(1))
                .findAllReservationsForRoomForToday(roomCode, wingCode);
        assertEquals(validReservationList, testRooms);
    }

    @Test
    public void addReservationForRoom(){
        when(reservationService.addReservation(reservation)).thenReturn(true);
        reservationController.setReservationService(reservationService);

        ResponseEntity<Reservation> entity = reservationController.addReservation(reservation);

        verify(reservationService, times(1)).addReservation(reservation);

        assertEquals(HttpStatus.CREATED, entity.getStatusCode());
    }

    @Test
    public void addReservationWentWrong(){
        //Updating the default service given in the init method
        when(reservationService.addReservation(reservation)).thenReturn(false);
        reservationController.setReservationService(reservationService);

        ResponseEntity<Reservation> entity = reservationController.addReservation(reservation);

        verify(reservationService, times(1)).addReservation(reservation);

        assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
    }

    @Test
    public void getReservationsOfWeekSuccess(){
        List<Reservation> reservations = new ArrayList<>();

        when(reservationService
                .findAllReservationOfWeek(33, 2017, "R27", "D 1.11"))
                .thenReturn(reservations);

        ResponseEntity entity = reservationController.findAllReservationOfWeek(33, 2017, "R27", "D 1.11");

        verify(reservationService).findAllReservationOfWeek(33, 2017, "R27", "D 1.11");

        assertEquals(HttpStatus.OK, entity.getStatusCode());

        assertTrue(entity.hasBody());
    }

    @Test
    public void getReservationsOfWeekFailure(){
        when(reservationService
                .findAllReservationOfWeek(33, 2017,  "D 1.11", "R 27"))
                .thenReturn(null);

        reservationController.setReservationService(reservationService);

        ResponseEntity entity = reservationController.findAllReservationOfWeek(33, 2017,  "D 1.11", "R 27");

        verify(reservationService).findAllReservationOfWeek(33, 2017,  "D 1.11", "R 27");

        assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
    }

    @Test
    public void cancelReservationTestSuccess() throws Exception {
        ReservationDTO dto = mock(ReservationDTO.class);
        when(reservationService.cancelReservation(dto)).thenReturn(true);

        ResponseEntity result = reservationController.cancelReservation(dto);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void cancelReservationTestFailed() throws Exception {
        ReservationDTO dto = mock(ReservationDTO.class);
        when(reservationService.cancelReservation(dto)).thenReturn(false);

        ResponseEntity result = reservationController.cancelReservation(dto);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void findActualReservationByUsername() throws Exception {
        String username = "username";
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation());
        reservations.add(new Reservation());
        when(reservationService.getAllActualReservationByUsername(username)).thenReturn(reservations);


        ResponseEntity<List<Reservation>> results = reservationController.findActualReservationByUsername(username);

        assertTrue(results.hasBody());
        assertEquals(results.getBody(), reservations);
        assertTrue(results.getBody().size() == 2);
    }
}
