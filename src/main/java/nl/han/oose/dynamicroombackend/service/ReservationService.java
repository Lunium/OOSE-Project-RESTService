package nl.han.oose.dynamicroombackend.service;

import nl.han.oose.dynamicroombackend.dto.ReservationDTO;
import nl.han.oose.dynamicroombackend.dto.RoomDTO;
import nl.han.oose.dynamicroombackend.model.Reservation;

import java.util.List;

public interface ReservationService {

    List<Reservation> findAllReservationsForRoomForToday(String roomCode, String wingCode);
    List<RoomDTO> findAllReservationForDay();

    boolean addReservation(Reservation reservation);

    List<Reservation> findAllReservationOfWeek(int week, int year, String wingCode, String roomCode);

    boolean cancelReservation(ReservationDTO r);

    List<Reservation> getAllActualReservationByUsername(String dto);
}
