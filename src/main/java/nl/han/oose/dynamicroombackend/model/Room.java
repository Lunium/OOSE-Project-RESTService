package nl.han.oose.dynamicroombackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import nl.han.oose.dynamicroombackend.model.id.RoomId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@IdClass(RoomId.class)
@Table(name = "ROOM")
@NamedNativeQueries({
        @NamedNativeQuery(name = "Room.filterRoomsNoExtraParams",
                query = "SELECT * FROM Room R\n" +
                        " WHERE NOT EXISTS\n" +
                        "(\n" +
                        " SELECT 1 FROM reservation rv\n" +
                        " WHERE rv.ROOMCODE = r.ROOMCODE\n" +
                        " AND rv.WINGCODE = r.WINGCODE\n" +
                        " AND (\n" +
                        "    rv.BEGINTIME < ?1 AND rv.ENDTIME > ?1\n" +
                        "    OR rv.BEGINTIME > ?1 AND rv.ENDTIME < ?2\n" +
                        "    OR rv.BEGINTIME < ?2 AND rv.ENDTIME >?2\n" +
                        "    OR rv.BEGINTIME < ?1 AND rv.ENDTIME > ?2\n" +
                        "    )\n" +
                        "AND rv.DATE = ?3\n" +
                        ")\n" +
                        "AND r.CAPACITY = (SELECT MIN(r2.capacity) FROM room r2 WHERE r2.capacity >= ?4)", resultClass = Room.class),
        @NamedNativeQuery(name = "Room.filterRoomsFacilities",
                query = "SELECT * FROM Room R\n" +
                        " WHERE NOT EXISTS\n" +
                        "(\n" +
                        " SELECT 1 FROM reservation rv\n" +
                        " WHERE rv.ROOMCODE = r.ROOMCODE\n" +
                        " AND rv.WINGCODE = r.WINGCODE\n" +
                        " AND (\n" +
                        "    rv.BEGINTIME < ?1 AND rv.ENDTIME > ?1\n" +
                        "    OR rv.BEGINTIME > ?1 AND rv.ENDTIME < ?2\n" +
                        "    OR rv.BEGINTIME < ?2 AND rv.ENDTIME >?2\n" +
                        "    OR rv.BEGINTIME < ?1 AND rv.ENDTIME > ?2\n" +
                        "    )\n" +
                        "AND rv.DATE = ?3\n" +
                        ")\n" +
                        "AND r.CAPACITY = (SELECT MIN(capacity) FROM room r2 LEFT JOIN room_facilities rf ON rf.ROOMCODE = r2.ROOMCODE WHERE capacity >= ?4 AND rf.FACILITYTYPE IN ?6) " +
                        "AND (SELECT COUNT(*) FROM room_facilities rf WHERE FACILITYTYPE IN ?6 AND rf.ROOMCODE = r.ROOMCODE AND rf.WINGCODE = r.WINGCODE) = ?5\n " +
                        "AND (SELECT COUNT(*) FROM room_facilities rf WHERE rf.ROOMCODE = r.ROOMCODE AND rf.WINGCODE = r.WINGCODE) >= ?5\n ", resultClass = Room.class),
        @NamedNativeQuery(name = "Room.filterRoomsWing",
                query = "SELECT * FROM Room R\n" +
                        " WHERE NOT EXISTS\n" +
                        "(\n" +
                        " SELECT 1 FROM reservation rv\n" +
                        " WHERE rv.ROOMCODE = r.ROOMCODE\n" +
                        " AND rv.WINGCODE = r.WINGCODE\n" +
                        " AND (\n" +
                        "    rv.BEGINTIME < ?1 AND rv.ENDTIME > ?1\n" +
                        "    OR rv.BEGINTIME > ?1 AND rv.ENDTIME < ?2\n" +
                        "    OR rv.BEGINTIME < ?2 AND rv.ENDTIME >?2\n" +
                        "    OR rv.BEGINTIME < ?1 AND rv.ENDTIME > ?2\n" +
                        "    )\n" +
                        "AND rv.DATE = ?3\n" +
                        ")\n" +
                        "AND r.CAPACITY = (SELECT MIN(r2.capacity) FROM room r2 WHERE r2.capacity >= ?4 AND R.WINGCODE = ?5) " +
                        "AND r.WINGCODE = ?5", resultClass = Room.class),
        @NamedNativeQuery(name = "Room.filterRoomsFacilitiesAndWing",
                query = "SELECT * FROM Room R\n" +
                        " WHERE NOT EXISTS\n" +
                        "(\n" +
                        " SELECT 1 FROM reservation rv\n" +
                        " WHERE rv.ROOMCODE = r.ROOMCODE\n" +
                        " AND rv.WINGCODE = r.WINGCODE\n" +
                        " AND (\n" +
                        "    rv.BEGINTIME < ?1 AND rv.ENDTIME > ?1\n" +
                        "    OR rv.BEGINTIME > ?1 AND rv.ENDTIME < ?2\n" +
                        "    OR rv.BEGINTIME < ?2 AND rv.ENDTIME >?2\n" +
                        "    OR rv.BEGINTIME < ?1 AND rv.ENDTIME > ?2\n" +
                        "    )\n" +
                        "AND rv.DATE = ?3\n" +
                        ")\n" +
                        "AND r.CAPACITY = (SELECT MIN(capacity) FROM room r2 LEFT JOIN room_facilities rf ON rf.ROOMCODE = r2.ROOMCODE WHERE capacity >= ?4 AND rf.FACILITYTYPE IN ?5 AND R.WINGCODE = ?7) " +
                        "AND (SELECT COUNT(*) FROM room_facilities rf WHERE FACILITYTYPE IN ?5 AND rf.ROOMCODE = r.ROOMCODE AND rf.WINGCODE = r.WINGCODE) = ?6\n " +
                        "AND (SELECT COUNT(*) FROM room_facilities rf WHERE rf.ROOMCODE = r.ROOMCODE AND rf.WINGCODE = r.WINGCODE) >= ?6\n " +
                        "AND r.WINGCODE = ?7", resultClass = Room.class)
})

public class Room implements Serializable {

    @Id
    @Column(name = "ROOMCODE")
    private String roomCode;

    @Id
    @Column(name = "WINGCODE")
    private String wingCode;

    @Column(name = "CAPACITY")
    private int capacity;

    public Room() {
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumns({
            @JoinColumn(name = "ROOMCODE", referencedColumnName = "ROOMCODE"),
            @JoinColumn(name = "WINGCODE", referencedColumnName = "WINGCODE")
    })
    private List<Reservation> reservation;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "room_facilities", joinColumns = {@JoinColumn(name = "WINGCODE", referencedColumnName = "WINGCODE", nullable = false),
            @JoinColumn(name = "ROOMCODE", referencedColumnName = "ROOMCODE", nullable = false)},
            inverseJoinColumns = @JoinColumn(name = "FACILITYTYPE", referencedColumnName = "FACILITYTYPE", nullable = false))
    private List<Facility> facilities;

    @JsonIgnore
    public List<Reservation> getReservation() {
        return reservation;
    }

    public void setReservation(List<Reservation> reservation) {
        this.reservation = reservation;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getWingCode() {
        return wingCode;
    }

    public void setWingCode(String wingCode) {
        this.wingCode = wingCode;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Facility> facilities) {
        this.facilities = facilities;
    }
}
