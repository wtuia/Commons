package java_util.calender;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * DAY_OF_MONTH的主要作用是cal.get(DAY_OF_MONTH)，用来获得这一天在是这个月的第多少天
 *
 * Calendar.DAY_OF_YEAR的主要作用是cal.get(DAY_OF_YEAR)，用来获得这一天在是这个年的第多少天。
 *
 * 同样，还有DAY_OF_WEEK，用来获得当前日期是一周的第几天
 *
 * 如果做add操作,则DAY_OF_MONTH DAY_OF_WEEK DATE 是一样的
 *
 */
public class CalenderClass {

    private static final Logger logger = LoggerFactory.getLogger(CalenderClass.class);
    private static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {

        Calendar cal = Calendar.getInstance();

        //设置时间
        cal.set(Calendar.YEAR, 2018); // 设置年
        cal.set(Calendar.MONTH, 0); // 设置月 从0开始
        cal.set(Calendar.DATE, 1); // 设置日
        logger.info(SDF.format(cal.getTime()));
    }

    /**
     * add 用于做加减 set 用于设置 get 用于获取
     */
    @Test
    public void test1() {
        Calendar cal = Calendar.getInstance();
        //按偏移量加减
        cal.add(Calendar.MONTH, -1);
        logger.info(SDF.format(cal.getTime()));
    }
}
