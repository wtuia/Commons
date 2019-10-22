package java_time.zoneid;

import org.junit.Test;
import sun.security.action.GetPropertyAction;

import java.security.AccessController;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.zone.ZoneRules;
import java.util.TimeZone;

/**
 * 时区
 * 可用于设置Instant、ZoneDateTime或时间类型转换等的时区信息
 */
public class ZoneIdDemo {

    /**
     * TimeZone.getDefault() AccessController#doPrivileged#GetPropertyAction 获取，
     * GetPropertyAction 则通过System.getProperty("user.timezone") 获取，都是本地方法
     * 若AccessController#doPrivileged#GetPropertyAction获取为null,则通过Java_Home获取
     * <p>(直接调用*System.getProperty("user.timezone")得到的ZondId为"" )<p/>
     * {@link #getID2JavaHome()}
     *<br/><br/>
     * 通过TimeZone#toZoneId获取ZoneId,包含了几个美国特殊地市州的检查之后调用ZoneId#of,
     * 该方法获取的ZoneId 返回的是ZoneRegion为ZoneId的子类
     *
     */
    @Test
    public void id() {

        // 1 ,调用TimeZone 获取
        String toTimeZoneId = TimeZone.getDefault().getID();
        ZoneId toTimeZoneZoneId = TimeZone.getDefault().toZoneId();

        // 获取时区规则和偏移量
        ZoneId zoneId = ZoneId.systemDefault();
        ZoneRules rules = zoneId.getRules();
        ZoneOffset offset = rules.getOffset(Instant.now());

        System.out.printf("toTimeZoneId = %s, toTimeZoneZoneId = %s %n",
                toTimeZoneId, toTimeZoneZoneId);
    }



    private  void getID2JavaHome() {
        String javaHome = AccessController.doPrivileged(new GetPropertyAction("java.home"));
        getSystemTimeZoneID(javaHome);
    }
    private static native String getSystemTimeZoneID(String javaHome);
}
