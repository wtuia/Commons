package java_time.datetime_formatter;

import org.junit.Test;

import javax.xml.ws.RequestWrapper;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class DateTimeFormatterDemo {


    /**
     * 使用FormatStyle转换
     * date --> String
     *
     * <p>FormatStyle.FULL 包含时区的信息，LocalDateTime无法使用，ZonedDateTime可以使用<p/>
     */
    @Test
    public void date2StringByFormatStyle() {

        DateTimeFormatter fullFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
        DateTimeFormatter longFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        DateTimeFormatter mediumFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        DateTimeFormatter shortFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);


        String longDate = localDateTime.format(longFormat);
        String mediumDate = localDateTime.format(mediumFormat);
        String shortDate = localDateTime.format(shortFormat);

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        String fullDate = zonedDateTime.format(fullFormat);


        System.out.println(fullDate);
        System.out.println(longDate);
        System.out.println(mediumDate);
        System.out.println(shortDate);
    }

    /**
     * Locale 如果缺省，会使用当前国家
     */
    @Test
    public void date2StringByPattern() {
        // MM M 的区别在于 若月份为1，则M为1，MM为01,其他亦是
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("G u年MM月dd日 E H:mm:ss:SSS", Locale.CHINA);
        LocalDateTime localDateTime = LocalDateTime.now();
        String date = localDateTime.format(formatter);
        System.out.println(date);
    }

    /**
     * yyyy MM dd 的格式無法转换为LocalDateTime,至少需要yyyy MM dd H的格式才可以,
     *  <p>因为不包含时区，所以不能直接转为ZoneDateTime，需要先添加ZoneId
     *  {@link DateTimeFormatter#withZone(ZoneId)}<p/>
     *
     */
    @Test
    public void string2Date() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd H");
        String date = "2019 10 16 11";
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
        System.out.println(localDateTime);

        // ZoneDateTime
        DateTimeFormatter dateZoneFormat =
                DateTimeFormatter.ofPattern("yyyy MM dd H").withZone(ZoneId.systemDefault());
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(date, formatter);
        System.out.println(zonedDateTime);
    }
}



