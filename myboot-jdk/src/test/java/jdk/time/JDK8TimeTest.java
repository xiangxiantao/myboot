package jdk.time;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Java 8中表示日期和时间的类有多个，主要的有：
 *
 * Instant：表示时刻，不直接对应年月日信息，需要通过时区转换
 * LocalDateTime: 表示与时区无关的日期和时间信息，不直接对应时刻，需要通过时区转换
 * LocalDate：表示与时区无关的日期，与LocalDateTime相比，只有日期信息，没有时间信息
 * LocalTime：表示与时区无关的时间，与LocalDateTime相比，只有时间信息，没有日期信息
 * ZonedDateTime：表示特定时区的日期和时间
 * ZoneId/ZoneOffset：表示时区
 */
public class JDK8TimeTest {

    @Test
    public void testInstant(){
        Instant instant=Instant.now();
        System.out.println(instant);

        Instant now=Instant.ofEpochMilli(System.currentTimeMillis());
        System.out.println(now);

        Instant fromDate=Instant.ofEpochMilli(new Date().getTime());
        System.out.println(fromDate);
    }

    @Test
    public void testLocalDate(){
        LocalDate localDate=LocalDate.now();
        System.out.println(localDate);
    }

    @Test
    public void testLocalTime(){
        LocalTime localTime=LocalTime.now();
        System.out.println(localTime);
    }

    @Test
    public void testLocalDateTime(){
        LocalDateTime localDateTime= LocalDateTime.now();
        System.out.println(localDateTime);//2019-09-19T10:16:10.156

        System.out.println(localDateTime.getDayOfWeek()); //获取礼拜信息

        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse("2019-09-19 10:16:55", formatter);//将字符串转为localdate
        System.out.println(dateTime);
        String format = formatter.format(localDateTime);//将localdate转为格式化字符串
        System.out.println(format);
    }


    /**
     * Java 8中表示时间段的类主要有两个，Period和Duration，
     * Period表示日期之间的差，用年月日表示，不能表示时间，
     * Duration表示时间差，用时分秒表等表示，也可以用天表示，一天严格等于24小时，不能用年月表示
     */

    @Test
    public void calDate(){
        LocalDate ld1 = LocalDate.of(1995, 8, 4);
        LocalDate ld2 = LocalDate.now();
        Period period = Period.between(ld1, ld2);
        System.out.println(period.getYears() + "年"
                + period.getMonths() + "个月" + period.getDays() + "天");
    }
    @Test
    public void calTime(){
        long lateMinutes = Duration.between(
                LocalTime.of(9,0),
                LocalTime.now()).toMinutes();
        System.out.println(lateMinutes);
    }

}
