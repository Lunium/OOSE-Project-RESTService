package nl.han.oose.dynamicroombackend.dto;

import java.util.List;

public class RoomDTO {

    private String roomCode;
    private String wingCode;
    private int capacity;
    private List<ReservationDTO> roomReservations;
    private List<FacilityDTO> roomFacilities;

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

    public List<ReservationDTO> getRoomReservations() {
        return roomReservations;
    }

    public void setRoomReservations(List<ReservationDTO> roomReservations) {
        this.roomReservations = roomReservations;
    }

    public List<FacilityDTO> getRoomFacilities() {
        return roomFacilities;
    }

    public void setRoomFacilities(List<FacilityDTO> roomFacilities) {
        this.roomFacilities = roomFacilities;
    }
}
