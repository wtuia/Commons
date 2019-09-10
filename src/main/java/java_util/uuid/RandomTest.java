package java_util.uuid;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

public class RandomTest {

    public static void main(String[] args) {
        byte[] seed = new SecureRandom().generateSeed(8);
        byte[] randomBytes = new byte[16];
        new SecureRandom(seed).nextBytes(randomBytes);
        System.out.println(Arrays.toString(seed));
        System.out.println(Arrays.toString(randomBytes));
    }
}
