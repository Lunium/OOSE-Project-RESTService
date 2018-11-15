package nl.han.oose.dynamicroombackend.controller;

import nl.han.oose.dynamicroombackend.dto.ReservationDTO;
import nl.han.oose.dynamicroombackend.dto.RoomDTO;
import nl.han.oose.dynamicroombackend.model.Reservation;
import nl.han.oose.dynamicroombackend.model.Room;
import nl.han.oose.dynamicroombackend.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/reservation")
@RestController
public class ReservationController {

    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @RequestMapping(value = "/getroomreservation", method = RequestMethod.GET)
    public List<Reservation> getCurrentScheduleRoom(@RequestParam(value = "roomcode") String roomCode,
                                                    @RequestParam(value = "wingcode") String wingCode) {
        return this.reservationService.findAllReservationsForRoomForToday(roomCode, wingCode);
    }

    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    public List<RoomDTO> getAllReservationForDay() {
        return reservationService.findAllReservationForDay();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        boolean success = reservationService.addReservation(reservation);
        if (!success) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getroomreservationsbyweek")
    public ResponseEntity findAllReservationOfWeek(
            @RequestParam(value = "weeknumber") int week,
            @RequestParam(value = "year") int year,
            @RequestParam(value = "wingcode") String wingCode,
            @RequestParam(value = "roomcode") String roomCode
            ) {

        List<Reservation> reservations = reservationService.findAllReservationOfWeek(week, year, wingCode, roomCode);

        if(reservations == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }


    @RequestMapping(value = "/cancel", method = RequestMethod.PATCH)
    public ResponseEntity cancelReservation(@RequestBody ReservationDTO r){
        if(reservationService.cancelReservation(r)){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/user/reservations", method = RequestMethod.GET)
    public ResponseEntity<List<Reservation>> findActualReservationByUsername(@RequestParam(value = "owner") String username){
        List<Reservation> reservations = reservationService.getAllActualReservationByUsername(username);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }


    // has to be removed.
    public void setReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


}
