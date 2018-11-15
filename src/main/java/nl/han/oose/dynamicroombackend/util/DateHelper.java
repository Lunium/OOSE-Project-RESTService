package nl.han.oose.dynamicroombackend.util;

import nl.han.oose.dynamicroombackend.exception.NonExistingWeekNumberException;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;

@Component
public class DateHelper {
    private final WeekFields weekField;

    public DateHelper() {
        weekField = WeekFields.of(Locale.getDefault());
    }

    public LocalDate getFirstDayOfWeek(int week, int year) {
        try {
            return LocalDate.now()
                    .withYear(year)
                    .with(weekField.weekOfYear(), week)
                    .with(weekField.dayOfWeek(), 1);
        } catch (DateTimeException e) {
            e.printStackTrace();
            throw new NonExistingWeekNumberException("Week " + week + " is an invalid week number");
        }

    }

    public LocalDate getLastDayOfWeek(int week, int year) {
        try {
            return LocalDate.now()
                    .withYear(year)
                    .with(weekField.weekOfYear(), week)
                    .with(weekField.dayOfWeek(), 7);
        } catch (DateTimeException e) {
            e.printStackTrace();
            throw new NonExistingWeekNumberException("Week " + week + " is an invalid week number");
        }
    }

    public Date localDateToUtilDate(LocalDate date) {
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public Time getSQLTimeNow(){
        LocalTime currentDate = LocalTime.now();
        return Time.valueOf(currentDate);
    }

    public Time addMinutesToSQLTime(Time time, long minutes){
        LocalTime localTime = time.toLocalTime();
        return Time.valueOf(localTime.plusMinutes(minutes));
    }

    public LocalTime SQLTimeToLocalTime(Time time){
        return time.toLocalTime();
    }
}
