package framework.redis.vote;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 添加新文章
 * zset 键 artTime:, key:文章ID, score:时间戳，按时间排序
 * zset 键 artScore:, key:文章ID, score:文章分值，初始化为时间戳(每投票一查加500分)
 *
 * set voted:文章ID，存储为该文章投票的用户ID,避免重复投票
 *
 */
public class AddArticle {

	/**
	 */
	public static void addArticle(Jedis jedis) {
		String artId = UUID.randomUUID().toString().split("-")[1]; // 生成文字ID;
		String votedSetName = "voted:" + artId; // 创建文章对应的键，存储投票的用户ID
		long time = System.currentTimeMillis()/1000;
		Map<String, String> artContent = new HashMap<>();
		artContent.put("title", "title " + artId);
		artContent.put("link", "http://www." + artId);
		artContent.put("time", time+"");
		artContent.put("votes", time+"");
		jedis.hmset(artId, artContent); // 保存文章信息
		jedis.sadd(votedSetName, artId); // 保存发布者ID
		jedis.expire(votedSetName, 86400);// 设置表的操作时间
		jedis.zadd("artScore:", time, artId);// 保存评分
		jedis.zadd("artTime:", time, artId);// 保存时间戳(s)

	}

	public static void main(String[] args) {
		Jedis jedis = ConnManager.conn();
		//jedis.hset("login:", "1123", "user1");
		System.out.println(jedis.hget("login:", "1123"));
		System.out.println(jedis.hget("login:", "user1"));
		jedis.close();
	}

}
