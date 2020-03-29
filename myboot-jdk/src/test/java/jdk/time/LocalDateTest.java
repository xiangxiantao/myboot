package jdk.time;

import com.sun.org.apache.bcel.internal.generic.FSUB;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LocalDateTest {

    @Test
    public void test1(){
        String time="2019-01-22 12:01:59";

        LocalDate date=LocalDate.parse(time.substring(0,10), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(date);
    }

    @Test
    public void test3(){
        String s="2020-02-28 2020-03-05";
        System.out.println(s.substring(11,21));
    }

    @Test
    public void test(){
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.parse("2020-03-05",DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<LocalDate> dates = Stream.iterate(start, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(start, end.plusDays(1)))
                .collect(Collectors.toList());
        System.out.println(dates.size());
        System.out.println(dates);
    }

    @Test
    public void test4(){
        LocalDate now = LocalDate.now();
        LocalDate now2 = LocalDate.now();
        LocalDate date= LocalDate.parse("2020-03-05",DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(now.isBefore(now2));
        System.out.println(now.isEqual(now2));
    }

    @Test
    public void test5(){
        LocalTime now=LocalTime.now();
        System.out.println(now);
        LocalTime trigger=LocalTime.parse("14:44",DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime trigger2=LocalTime.parse(now.format(DateTimeFormatter.ofPattern("HH:mm")),DateTimeFormatter.ofPattern("HH:mm"));
        System.out.println(now.isAfter(trigger)&&now.isBefore(trigger.plusMinutes(3)));
        System.out.println(trigger2.equals(trigger));

    }


}
