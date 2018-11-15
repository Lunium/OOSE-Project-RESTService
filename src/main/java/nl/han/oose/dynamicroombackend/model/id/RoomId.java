package nl.han.oose.dynamicroombackend.model.id;

import javax.persistence.Column;
import java.io.Serializable;

public class RoomId implements Serializable {
    @Column(name = "ROOMCODE")
    private String roomCode;
    @Column(name = "WINGCODE")
    private String wingCode;

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
}
