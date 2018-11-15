package nl.han.oose.dynamicroombackend.repository;

import nl.han.oose.dynamicroombackend.model.Room;
import nl.han.oose.dynamicroombackend.model.id.RoomId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;


@Repository
public interface RoomRepository extends JpaRepository<Room, RoomId> {

    @Query(nativeQuery = true)
    List<Room> filterRoomsNoExtraParams(Time beginTime, Time endTime, Date date, int capacity);

    @Query(nativeQuery = true)
    List<Room> filterRoomsFacilities(Time beginTime, Time endTime, Date date, int capacity, int size, List<String> facilities);

    @Query(nativeQuery = true)
    List<Room> filterRoomsWing(Time beginTime, Time endTime, Date date, int capacity, String wingCode);

    @Query(nativeQuery = true)
    List<Room> filterRoomsFacilitiesAndWing(Time beginTime, Time endTime, Date date, int capacity, List<String> facilities, int size, String wingCode);
}