package nl.han.oose.dynamicroombackend;

import nl.han.oose.dynamicroombackend.dto.*;

import nl.han.oose.dynamicroombackend.model.*;
import nl.han.oose.dynamicroombackend.model.id.ReservationId;
import nl.han.oose.dynamicroombackend.model.id.ReservationParticipantsId;
import nl.han.oose.dynamicroombackend.model.id.RoomId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import pl.pojo.tester.api.assertion.Method;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

@RunWith(MockitoJUnitRunner.class)
public class PojoTest {

    @Test
    public void TestsPojoUser(){
        assertPojoMethodsFor(User.class)
                .testing(Method.GETTER, Method.SETTER)
                .testing(Method.CONSTRUCTOR)
                .areWellImplemented();
    }

    @Test
    public void TestsPojoReservation(){
        assertPojoMethodsFor(Reservation.class)
                .testing(Method.GETTER, Method.SETTER)
                .testing(Method.CONSTRUCTOR)
                .areWellImplemented();
    }

    @Test
    public void TestsPojoRoom(){
        assertPojoMethodsFor(Room.class)
                .testing(Method.GETTER, Method.SETTER)
                .testing(Method.CONSTRUCTOR)
                .areWellImplemented();
    }

    @Test
    public void TestsPojoReservationParticipants(){
        assertPojoMethodsFor(ReservationParticipants.class)
                .testing(Method.GETTER, Method.SETTER)
                .testing(Method.CONSTRUCTOR)
                .areWellImplemented();
    }

    @Test
    public void TestsPojoReservationId(){
        assertPojoMethodsFor(ReservationId.class)
                .testing(Method.GETTER, Method.SETTER)
                .testing(Method.CONSTRUCTOR)
                .areWellImplemented();
    }

    @Test
    public void TestsPojoRoomId(){
        assertPojoMethodsFor(RoomId.class)
                .testing(Method.GETTER, Method.SETTER)
                .testing(Method.CONSTRUCTOR)
                .areWellImplemented();
    }

    @Test
    public void TestsPojoReservationParticipantsId(){
        assertPojoMethodsFor(ReservationParticipantsId.class)
                .testing(Method.GETTER, Method.SETTER)
                .testing(Method.CONSTRUCTOR)
                .areWellImplemented();
    }

    @Test
    public void TestsPojoCheckInOutDTO(){
        assertPojoMethodsFor(CheckInOutDTO.class)
                .testing(Method.GETTER, Method.SETTER)
                .testing(Method.CONSTRUCTOR)
                .areWellImplemented();
    }

    @Test
    public void TestsPojoFilterRequest(){
        assertPojoMethodsFor(FilterDTO.class)
                .testing(Method.GETTER, Method.SETTER)
                .testing(Method.CONSTRUCTOR)
                .areWellImplemented();
    }

    @Test
    public void TestsPojoFacility(){
        assertPojoMethodsFor(Facility.class)
                .testing(Method.GETTER, Method.SETTER)
                .testing(Method.CONSTRUCTOR)
                .areWellImplemented();
    }
    @Test
    public void TestsPojoReservationDTO(){
        assertPojoMethodsFor(ReservationDTO.class)
                .testing(Method.GETTER, Method.SETTER)
                .testing(Method.CONSTRUCTOR)
                .areWellImplemented();
    }

    @Test
    public void TestsPojoWing(){
        assertPojoMethodsFor(Wing.class)
                .testing(Method.GETTER, Method.SETTER)
                .testing(Method.CONSTRUCTOR)
                .areWellImplemented();
    }

    @Test
    public void TestsPojoRoomDTO(){
        assertPojoMethodsFor(RoomDTO.class)
                .testing(Method.GETTER, Method.SETTER)
                .testing(Method.CONSTRUCTOR)
                .areWellImplemented();
    }

    @Test
    public void TestsPojoFacilityDTO(){
        assertPojoMethodsFor(FacilityDTO.class)
                .testing(Method.GETTER, Method.SETTER)
                .testing(Method.CONSTRUCTOR)
                .areWellImplemented();
    }
}
