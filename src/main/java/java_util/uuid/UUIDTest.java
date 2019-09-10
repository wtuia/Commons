package java_util.uuid;

import java.text.SimpleDateFormat;
import java.util.UUID;

public class UUIDTest {

    public static void main(String[] args) {
        UUID uuid = UUIDUtils.create();
        long time = uuid.timestamp();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        String date = sdf.format(time);
        System.out.println(uuid.toString());
        System.out.println(date);
    }
}
