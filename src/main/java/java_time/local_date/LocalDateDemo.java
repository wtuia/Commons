package java_time.local_date;

import java_time.instant.InstantDemo;

import java.time.*;
import java.util.Date;

/**
 * 本地的不带时区的时间，与Instant的转换参加
 * {@link InstantDemo#toLocalDateTime()}
 */
public class LocalDateDemo {



    public static void main(String[] args) {
        // 日期
        LocalDate now = LocalDate.now();

        // 时间
        LocalTime localTime = LocalTime.now();
        // 日期时间
        LocalDateTime localDateTime = LocalDateTime.now();

        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();

        // 转为date
        Date date = Date.from(instant);
        System.out.println(date);

        // 计算时间偏移量
        LocalDate localDate = now.minusYears(-1);

    }
}
