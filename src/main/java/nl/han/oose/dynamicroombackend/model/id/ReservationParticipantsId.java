package nl.han.oose.dynamicroombackend.model.id;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class ReservationParticipantsId implements Serializable {
    private String userCode;
    private Date date;
    private Time beginTime;
    private String roomCode;
    private String wingCode;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Time beginTime) {
        this.beginTime = beginTime;
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
}
