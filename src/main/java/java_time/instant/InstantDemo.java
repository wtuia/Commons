package java_time.instant;

import org.junit.Test;

import java.time.*;
import java.time.zone.ZoneRules;
import java.util.TimeZone;

public class InstantDemo {

    /**
     * Instant为时间线的瞬时时间，Instant与时区无关,不同时区的instant则应该相等，如下例<br/>
     * 与LocalDateTime相比：
     * <p>如，UTC+8 与 UCT+0 的零点在与LocalDateTime看来是相等的，
     * 但在Instant看来则不等(不在同一个时间线上，Instant的UTC+8的8:00与UTC+0的0:00相等)</p>
     *
     * instant除了保存时间戳之外，保存一个时区信息，用于转换使用
     *
     */
    @Test
    public void instant() {
        Instant now = Instant.now();

        Clock zoneIdClock = Clock.system(TimeZone.getDefault().toZoneId());
        Clock utcClock = Clock.systemUTC();
        // 内部调用 TimeZone.getDefault().toZoneId();
        Clock defaultClock = Clock.systemDefaultZone();

        /*
         * Instant.now(Clock) 内部调用了Clock#instant, 故，如下输入与Instant.now(Clock) 的输出等同
         */
        System.out.println(zoneIdClock.instant());
        System.out.println(utcClock.instant());
        System.out.println(defaultClock.instant());


    }

    /**
     * 与localDate的装换
     */
    @Test
    public void toLocalDateTime() {
        Instant now = Instant.now();

        // 获取偏移量
        ZoneId zoneId = ZoneId.systemDefault();
        ZoneRules rules = zoneId.getRules();
        ZoneOffset offset = rules.getOffset(now);

        // 整合偏移量
        LocalDateTime haveRuleTime =
                LocalDateTime.ofInstant(now, ZoneId.systemDefault().getRules().getOffset(now));

        LocalDateTime localDateTime = LocalDateTime.ofInstant(now, offset);

        LocalDate localDate = localDateTime.toLocalDate();

        LocalTime localTime = localDateTime.toLocalTime();

        System.out.println(localDateTime);
        System.out.println(localDate);
        System.out.println(localTime);

        // 转为时间戳
        Instant instant = localDateTime.toInstant(offset);
        Instant dateInstant = localDate.atStartOfDay().toInstant(offset);
        Instant timeInstant = localTime.atDate(localDate).toInstant(offset);

        System.out.println(instant);
        System.out.println(dateInstant);
        System.out.println(timeInstant);
    }


    /**
     * 测试getNano()的值
     */
    @Test
    public void nanoTest() {
        Instant now = Instant.now();
        ZoneId zoneId = ZoneId.systemDefault();
        ZoneRules rules = zoneId.getRules();
        ZoneOffset offset = rules.getOffset(now);

        // 不设置Nano
        LocalDateTime dateTime =
                LocalDateTime.ofEpochSecond(now.getEpochSecond(), 0, offset);
        System.out.println(dateTime);

        // 设置Nano
        LocalDateTime nanoDateTime =
                LocalDateTime.ofEpochSecond(now.getEpochSecond(), now.getNano(), offset);
        System.out.println(nanoDateTime);
    }
}
