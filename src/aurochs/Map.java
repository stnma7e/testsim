package aurochs;

import java.util.List;

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
		String xypos = jedis.hget("simlocations", String.valueOf(id));
		
		String xloc = xypos.substring(0, xypos.indexOf(','));
		String yloc = xypos.substring(xypos.indexOf(',') + 2); /* index + 2 for to eliminate the comma and the trailing space */
		
		int[] toReturn = {Integer.parseInt(xloc), Integer.parseInt(yloc)};
		return toReturn;
	}
}
