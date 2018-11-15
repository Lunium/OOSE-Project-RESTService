package nl.han.oose.dynamicroombackend.repository;

import nl.han.oose.dynamicroombackend.model.Reservation;
import nl.han.oose.dynamicroombackend.model.id.ReservationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, ReservationId> {
    @Query("SELECT r FROM Reservation r WHERE r.roomCode = ?1 AND r.wingCode = ?2 and r.date = current_date")
    List<Reservation> findByRoomCodeAndWingCode(String roomCode, String wingCode);

    @Query("SELECT r FROM Reservation r WHERE r.roomCode = ?1 AND r.wingCode = ?2 AND r.date BETWEEN ?3 AND ?4")
    List<Reservation> findAllOfWeek(String roomCode, String wingCode, Date firstDay, Date lastDay);

    @Query("SELECT r from Reservation r WHERE r.roomCode = ?1 " +
            "AND r.wingCode = ?2 AND r.pinCode = ?3 " +
            "AND r.date = current_date AND current_time BETWEEN r.beginTime AND r.endTime " +
            "AND r.status = 'Gereserveerd'")
    Reservation checkInReservation(String roomCode, String wingCode, int pinCode);

    @Query("SELECT r FROM Reservation r WHERE r.status = 'Actief' AND r.roomCode = ?1 AND r.wingCode = ?2 AND r.date = current_date ")
    Reservation checkOutReservation(String roomCode, String wingCode);

    @Query("SELECT r FROM Reservation r WHERE r.owner = ?1 AND r.status = nl.han.oose.dynamicroombackend.model.Reservation.STATUS_BOOKED " +
            "AND r.date >= current_date ")
    List<Reservation> findActualReservationByUsername(String owner);

    @Query("SELECT r FROM Reservation r WHERE r.date = current_date AND r.status <> nl.han.oose.dynamicroombackend.model.Reservation.STATUS_CANCELLED")
    List<Reservation> findAllOfToday();

    @Query("SELECT r FROM Reservation r WHERE r.date = ?1 AND r.status <> nl.han.oose.dynamicroombackend.model.Reservation.STATUS_CANCELLED")
    List<Reservation> findAllForDate(Date date);
}
