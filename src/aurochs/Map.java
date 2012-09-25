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
	public int[] parseStringLocation(String loc) throws IllegalArgumentException {
		try {
			String xloc = loc.substring(0, loc.indexOf(','));
			
			String inter = loc.substring(loc.indexOf(',') + 2); /* index + 2 for to eliminate the comma and the trailing space */
			
			String yloc = inter.substring(0, inter.indexOf(','));
			String zloc = inter.substring(inter.indexOf(',') + 2);
			
			int[] returnLoc = { Integer.parseInt(xloc),Integer.parseInt(yloc), Integer.parseInt(zloc) };
			return returnLoc;
		}
		catch (IndexOutOfBoundsException e) {
			System.err.println("Not valid location string.");
			throw new IllegalArgumentException();
		}
	}
	public String parseAsStringLocation(int[] xypos) {
		String s = String.valueOf(xypos[0]);
		s = s.concat(", " + String.valueOf(xypos[1]));
		s = s.concat(", " + String.valueOf(xypos[2]));
		
		return s;
	}
	public boolean isValidStringLocation(String loc) {
		try {
			parseStringLocation(loc);
		}
		catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}
	public int[] locateSim(long id) {
		String xypos = jedis.hget("simlocations", String.valueOf(id));
		
		int[] toReturn = parseStringLocation(xypos);
		return toReturn;
	}
	public void moveSim(long id, String xyloc) throws Exception {
		int[] xypos = parseStringLocation(xyloc);
		if (xypos[0] > 115) { 
			xypos[0] = 115;
		}
		else if (xypos[0] < 0) {
			xypos[0] = 0;
		}
		/* magic number being boundary of map
		 * to be changed when map instance is created */
		if (xypos[1] > 115) { 
			xypos[1] = 115;
		}
		else if (xypos[1] < 0) {
			xypos[1] = 0;
		}
		String strXypos = parseAsStringLocation(xypos);
		jedis.hset("simlocations", String.valueOf(id), strXypos);
	}
}
