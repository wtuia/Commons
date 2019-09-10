package java_util.uuid;

import java.util.UUID;

public class UUIDDemo {

    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID(); // version 4
        String uuidStr = uuid.toString();
        System.out.println(uuid.version());
        System.out.println(uuid.variant());
    }
}
