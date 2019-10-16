package java_time.zone_date;

import org.junit.Test;

import java.time.*;
import java.time.zone.ZoneRules;

/**
 * 带时区的本地时间，与LocalDateTime相比，含有时区的概念
 */
public class ZoneDateTimeDemo {

    /**
     * 各种值的获取
     */
    @Test
    public void demo() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();

        ZoneOffset offset = zonedDateTime.getOffset();

        ZoneId zoneId = zonedDateTime.getZone();

        ZoneRules rules = zoneId.getRules();

        Instant instant = zonedDateTime.toInstant();

        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();

        ZonedDateTime afterTime =  zonedDateTime.minusYears(1);

        System.out.println(afterTime);
    }

    /**
     * ZonedDateTime 与 ZoneRules 的时区相等
     */
    @Test
    public void offsetEquals() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();

        ZoneOffset zoneOffset = ZoneId.systemDefault().getRules().getOffset(Instant.now());

        ZoneOffset offset = zonedDateTime.getOffset();

        System.out.println(offset.equals(zoneOffset));
    }
}
