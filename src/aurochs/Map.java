package aurochs;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class Map {
	private static Hashtable<int[], ArrayList<Sim>> locationOfSims = new Hashtable<int[], ArrayList<Sim>>();
	private static Hashtable<Sim, int[]> simLocations = new Hashtable<Sim, int[]>();
	private static Map instance;
	
	protected Map() {
		
	}
	
	public static Map getInstance() {
		if (instance == null) {
			instance = new Map();
		}
		return instance;
	}
	
	public Hashtable<int[], ArrayList<Sim>> getlocationOfSims() {
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
	public ArrayList<Sim> getLocationPopulation(int[] xypos) {
		return locationOfSims.get(xypos);
	}
	
	public void removeSim(int[] xypos, Sim newsim) {
		try {
			ArrayList<Sim> curlist = locationOfSims.get(xypos);
			curlist.remove(newsim);
			if (getLocationPopulation(xypos) == null) {
				locationOfSims.remove(xypos);
			}
		}
		catch (NullPointerException e) {
			return;
		}
	}
	public void killSim(int[] xypos, Sim newsim) {
		removeSim(xypos, newsim);
		simLocations.remove(newsim);
	}
	public void moveSim(int[] oldxypos, int[] newxypos, Sim newsim) {
		try {
			removeSim(oldxypos, newsim);
			ArrayList<Sim> curlist = locationOfSims.get(newxypos);
			curlist.add(newsim);
		}
		catch (NullPointerException e) {
			ArrayList<Sim> newsimList = new ArrayList<Sim>();
			newsimList.add(newsim);
			
			locationOfSims.put(newxypos, newsimList);
		}
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
