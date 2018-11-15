package nl.han.oose.dynamicroombackend.service;

import nl.han.oose.dynamicroombackend.dto.RoomDTO;
import nl.han.oose.dynamicroombackend.model.Facility;
import nl.han.oose.dynamicroombackend.model.Reservation;
import nl.han.oose.dynamicroombackend.model.Room;
import nl.han.oose.dynamicroombackend.model.Wing;
import nl.han.oose.dynamicroombackend.model.id.RoomId;

import java.util.List;

public interface RoomService {
    List<Facility> findAllFacilities();

    List<Wing> findAllWings();

    Room findRoom(RoomId id);

    void addReservationsToRoomDto(RoomDTO[] rooms, List<Reservation> reservations);
}
