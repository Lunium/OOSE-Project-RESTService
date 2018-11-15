package nl.han.oose.dynamicroombackend.util;

import nl.han.oose.dynamicroombackend.exception.NonExistingWeekNumberException;
import org.apache.tomcat.jni.Local;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DateHelperTest {

    @InjectMocks
    DateHelper helper;

//    @Test
//    public void getFirstDayOfWeek(){
//        assertEquals("2017-05-01", helper.getFirstDayOfWeek(18, 2017).toString());
//    }

    @Test(expected = NonExistingWeekNumberException.class)
    public void getFirstDayOfWeekWrongDatePattern(){
        helper.getFirstDayOfWeek(99, 2017);
    }

//    @Test
//    public void getLastDayOfWeek(){
//        assertEquals("2017-05-07", helper.getLastDayOfWeek(18, 2017).toString());
//    }

    @Test(expected = NonExistingWeekNumberException.class)
    public void getLastDayOfWeekWrongDatePattern(){
        helper.getLastDayOfWeek(99, 2017);
    }


    @Test
    public void getSQLTimeNow() throws Exception {
        Time time = helper.getSQLTimeNow();
        assertTrue(Time.class.isInstance(time));
        assertNotNull(time);
    }

    @Test
    public void addMinutesToSQLTime() throws Exception {
        Time time = Time.valueOf("15:00:00");
        Time timeWithAddedTime = helper.addMinutesToSQLTime(time, 15);
        assertEquals("15:15:00", timeWithAddedTime.toString());
    }

    @Test
    public void SQLTimeToLocalTime() throws Exception {
        Time time = Time.valueOf("15:00:00");
        LocalTime localTime = helper.SQLTimeToLocalTime(time);
        assertEquals(time.toString(), Time.valueOf(localTime).toString());
    }

    @Test
    public void localDateToUtilDateTest(){
        LocalDate localDate = LocalDate.now();
        Date date = helper.localDateToUtilDate(localDate);
        assertEquals(localDate.toString(),
                LocalDateTime.ofInstant(date.toInstant(),
                        ZoneId.systemDefault()).toLocalDate().toString());
    }

}