package aurochs;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Map {
	private Hashtable<int[], ArrayList<Sim>> locationOfSims;
	private Hashtable<Sim, int[]> simLocations;
	private static Map instance;
	int mI_curX, mI_curY;
	private  JedisPool jedispool = new JedisPool(new JedisPoolConfig(), "localhost", 6379);
	
	protected Map() {
		simLocations = new Hashtable<Sim, int[]>();
		locationOfSims = new Hashtable<int[], ArrayList<Sim>>();
		mI_curX = 0;
		mI_curY = 0;
	}
	public static Map getInstance() {
		if (instance == null) {
			instance = new Map();
		}
		return instance;
	}
	public Hashtable<int[], ArrayList<Sim>> getlocationOfSims() {
		return locationOfSims;
	}
	boolean isLocationOpen(int[] xypos) {
		if (getLocation(xypos) == null) {
			return true;
		}
		else {
			return false;
		}
	}
	public ArrayList<Sim> getLocation(int[] xypos) {
		return locationOfSims.get(xypos);
	}
	public JedisPool getJedisPool() {
		return jedispool;
	}
	
	public void removeSim(Sim newsim) {
		try {
			int[] xypos = locateSim(newsim);
			ArrayList<Sim> curlist = locationOfSims.get(xypos);
			curlist.remove(newsim);
			if (getLocation(xypos) == null) {
				locationOfSims.remove(xypos);
			}
		}
		catch (NullPointerException e) {
			return;
		}
	}
	public void removeSim(int[] xypos, Sim newsim) {
		try {
			ArrayList<Sim> curlist = locationOfSims.get(xypos);
			curlist.remove(newsim);
			if (getLocation(xypos) == null) {
				locationOfSims.remove(xypos);
			}
		}
		catch (NullPointerException e) {
			return;
		}
	}
	public void killSim(int[] xypos, Sim newsim) {
		removeSim(newsim);
		simLocations.remove(newsim);
	}
	public void moveSim(int[] oldxypos, Sim newsim) {
		int[] newxypos =  null; //getNewCoordinates(oldxypos);
		removeSim(oldxypos, newsim);
		// if no old Sim is present, continue
		
		ArrayList<Sim> newsimList;
		try {
			newsimList = locationOfSims.get(newxypos);
			newsimList.add(newsim);
		}
		catch (NullPointerException e) {
			newsimList = new ArrayList<Sim>();
			newsimList.add(newsim);
		}
		locationOfSims.put(newxypos, newsimList);
		simLocations.put(newsim, newxypos);
	}
	public int[] locateSim(Sim simtofind) {
		return simLocations.get(simtofind);
	}
	public int[] locateSim(long id) throws IllegalArgumentException {
		Set<Sim> simObj = simLocations.keySet();
		Iterator<Sim> i = simObj.iterator();
		while (i.hasNext()) {
			Sim element = i.next();
			if (element.getSimId() == id) {
				int[] toBeReturned = { element.getxLoc(), element.getyLoc() };
				return toBeReturned;
			}
		}
		throw new IllegalArgumentException();
	}
}
