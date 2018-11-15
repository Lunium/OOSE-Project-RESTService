package nl.han.oose.dynamicroombackend.model.id;

import javax.persistence.Column;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class ReservationId implements Serializable{

    @Column(name = "DATE")
    private Date date;
    @Column(name = "BEGINTIME")
    private Time beginTime;
    @Column(name = "ROOMCODE")
    private String roomCode;
    @Column(name = "WINGCODE")
    private String wingCode;

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
