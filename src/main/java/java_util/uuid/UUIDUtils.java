package java_util.uuid;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

/**
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * mostSigBits 高64位
 * leastSigBits 低64位
 *
 */
public class UUIDUtils {
    private static final long leastSigBits;
    private static final ReentrantLock lock = new ReentrantLock();
    private static long lastTime;

    static {
        // 设置种子
        byte[] seed = new SecureRandom().generateSeed(8);
        leastSigBits = new BigInteger(seed).longValue();
    }

    /**
     * 创建随机UUID
     */
   public static UUID random() {
       // 设置种子字节数
        byte[] randomBytes = new byte[16];
       try {
           // 多线程产生安全的随机数
           UUIDUtils.class.getClassLoader().
                   loadClass("java.util.concurrent.ThreadLocalRandom");
           // 生成预定义的字节数的随机数 为传入的对象填充值
           ThreadLocalRandom.current().nextBytes(randomBytes);
       } catch (ClassNotFoundException e) {
           new Random(leastSigBits).nextBytes(randomBytes);;
       }

       long mostSigBits = 0;
       for (int i = 0; i < 8 ; i++) {
            mostSigBits = (mostSigBits << 8) | (randomBytes[i] & 0xff); // 按位与 再 按位或
       }
       long leastSigBits = 0;
       for (int i = 0; i < 8 ; i++) {
           leastSigBits = (leastSigBits <<8) | (randomBytes[i] & 0xff);
       }
       return new UUID(mostSigBits, leastSigBits);
   }

    /**
     * 基于时间的UUID
     */
   public static UUID create() {
        lock.lock();
        // long timeMillis = (System.currentTimeMillis() * 10000) + 0x01B21DD213814000L;
        long timeMillis = System.currentTimeMillis();
        if (timeMillis > lastTime) {
            lastTime = timeMillis;
        } else {
            timeMillis = ++lastTime;
        }
        lock.unlock();

        long mostSigBits = timeMillis << 32;

        mostSigBits |= (timeMillis & 0xFFFF00000000L) >> 16;

        // version = 1
        mostSigBits |= 0x1000 | (timeMillis >> 48) & 0x0FFF;
      /* // version = 2
       mostSigBits |= 0x2000 | (timeMillis >> 48) & 0x0FFF;
       // version = 3
       mostSigBits |= 0x3000 | (timeMillis >> 48) & 0x0FFF;*/
        return new UUID(mostSigBits, leastSigBits);
   }
}
