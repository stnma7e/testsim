package aurochs;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Map {
	private static Map instance;
	private  JedisPool jedispool = new JedisPool(new JedisPoolConfig(), "localhost", 6379);
	Jedis jedis;
	
	protected Map() {
		jedis = jedispool.getResource();
	}
	public static Map getInstance() {
		if (instance == null) {
			instance = new Map();
		}
		return instance;
	}
	public JedisPool getJedisPool() {
		return jedispool;
	}
	
	public void killSim(long id) {
		jedis.del(String.valueOf(id));
	}
	public int[] locateSim(long id) {
		int xloc = Integer.parseInt(jedis.hget(String.valueOf(id), "xLocation"));
		int yloc = Integer.parseInt(jedis.hget(String.valueOf(id), "yLocation"));
		int[] toReturn = {xloc, yloc};
		return toReturn;
	}
}
