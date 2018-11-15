package nl.han.oose.dynamicroombackend.model;

import nl.han.oose.dynamicroombackend.model.id.ReservationParticipantsId;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Entity
@IdClass(ReservationParticipantsId.class)
@Table(name="RESERVATION_PARTICIPANTS")
public class ReservationParticipants implements Serializable{

    @Id @Column(name="USERCODE")
    private String userCode;

    @Id @Column(name="DATE")
    private Date date;

    @Id @Column(name="BEGINTIME")
    private Time beginTime;

    @Id @Column(name="ROOMCODE")
    private String roomCode;

    @Id @Column(name="WINGCODE")
    private String wingCode;

   public ReservationParticipants(){}

   public ReservationParticipants(String userCode, Date date, Time beginTime, String roomCode, String wingCode){
       this.userCode = userCode;
       this.date = date;
       this.beginTime = beginTime;
       this.roomCode = roomCode;
       this.wingCode = wingCode;
   }

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
