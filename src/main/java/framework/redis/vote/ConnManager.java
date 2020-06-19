package framework.redis.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * 获取连接
 */
public class ConnManager {
	private static final Logger logger = LoggerFactory.getLogger(ConnManager.class);
	private static final String host = "139.155.83.148";
	public static Jedis conn() {
		Jedis jedis = new Jedis(host);
		jedis.auth("357322");
		logger.info(jedis.ping());
		return jedis;
	}
}
