package nl.han.oose.dynamicroombackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import nl.han.oose.dynamicroombackend.model.id.ReservationId;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Entity
@IdClass(ReservationId.class)
@Table(name="RESERVATION")
public class Reservation implements Serializable
{
    @Transient
    public static final String STATUS_ACTIVE = "Actief";
    @Transient
    public static final String STATUS_CANCELLED = "Geannuleerd";
    @Transient
    public static final String STATUS_BOOKED = "Gereserveerd";
    @Transient
    public static final String STATUS_EXPIRED = "Verlopen";
    @Transient
    public static final String STATUS_ENDED = "Afgesloten";


    @Id @Column(name="DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    @Id @Column(name="BEGINTIME")
    private Time beginTime;

    @Id @Column(name="ROOMCODE")
    private String roomCode;

    @Id @Column(name="WINGCODE")
    private String wingCode;

    @Column(name="OWNER")
    private String owner;

    @Column(name="ENDTIME")
    private Time endTime;

    @Column(name="STATUS")
    private String status;

    @Column(name = "PINCODE")
    int pinCode;

    public Reservation(){}

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
