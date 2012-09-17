package aurochs;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class Map {
	private static Hashtable<int[], ArrayList<ISim>> locationOfSims = new Hashtable<int[], ArrayList<ISim>>();
	private static Hashtable<ISim, int[]> simLocations = new Hashtable<ISim, int[]>();
	private static Map instance = null;
	
	protected Map() {
		
	}
	
	public static Map getInstance() {
		if (instance == null) {
			instance = new Map();
		}
		return instance;
	}
	
	public Hashtable<int[], ArrayList<ISim>> getlocationOfSims() {
		return Map.locationOfSims;
	}
	
	boolean isLocationOpen(int[] xypos) {
		if (locationOfSims.get(xypos) == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public ArrayList<ISim> getLocationPopulation(int[] xypos) {
		return locationOfSims.get(xypos);
	}
	
	public void removeSim(int[] xypos, ISim newsim) {
		try {
			ArrayList<ISim> curlist = locationOfSims.get(xypos);
			curlist.remove(newsim);
			if (getLocationPopulation(xypos) == null) {
				locationOfSims.remove(xypos);
			}
		}
		catch (NullPointerException e) {
			return;
		}
	}
	public void killSim(int[] xypos, ISim newsim) {
		removeSim(xypos, newsim);
		simLocations.remove(newsim);
	}
	public void moveSim(int[] oldxypos, int[] newxypos, ISim newsim) {
		try {
			removeSim(oldxypos, newsim);
			ArrayList<ISim> curlist = locationOfSims.get(newxypos);
			curlist.add(newsim);
		}
		catch (NullPointerException e) {
			ArrayList<ISim> newsimList = new ArrayList<ISim>();
			newsimList.add(newsim);
			
			locationOfSims.put(newxypos, newsimList);
		}
		simLocations.put(newsim, newxypos);
	}
	public int[] locateSim(ISim simtofind) {
		return simLocations.get(simtofind);
	}
	public int[] locateSim(long id) throws IllegalArgumentException {
		Set<ISim> simObj = simLocations.keySet();
		Iterator<ISim> i = simObj.iterator();
		while (i.hasNext()) {
			ISim element = i.next();
			if (element.simId == id) {
				int[] toBeReturned = { element.getxLoc(), element.getyLoc() };
				return toBeReturned;
			}
		}
		throw new IllegalArgumentException();
	}
}
