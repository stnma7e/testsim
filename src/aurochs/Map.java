package aurochs;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Map {
	private Hashtable<int[], ArrayList<Sim>> locationOfSims;
	private Hashtable<Sim, int[]> simLocations;
	private static Map instance;
	static Random r;
	int[] previousDirection = {0, 0};
	int mI_curX, mI_curY;
	
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
	public void moveSim(int[] oldxypos, Sim newsim) {
		int[] newxypos = getNewCoordinates(oldxypos);
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
	private int[] getNewCoordinates(int[] prevxy) {
		r = new Random();
		int randomIndex = r.nextInt(5);
		// System.out.println(randomIndex);
		int[] nextDirection = {0, 0};
		
		switch (randomIndex) {
		case 0: case 1:
			nextDirection = previousDirection;
			break;
		case 2:
			 nextDirection[0] = 1;
			 nextDirection[1] = 1;
			 break;
		 case 3:
			 nextDirection[0] = 1;
			 nextDirection[1] = -1;
			 break;
		 case 4:
			 nextDirection[0] = -1;
			 nextDirection[1] = 1;
			 break;
		}
		
		int newX = prevxy[0] + nextDirection[0];
		int newY = prevxy[1] + nextDirection[0];
		previousDirection = nextDirection;
		int[] toBeReturned = { newX, newY };
		
		return toBeReturned;
	}
}
