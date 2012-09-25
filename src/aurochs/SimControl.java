package aurochs;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;

public class SimControl implements Runnable {
	static final Hashtable<String, Integer> ml_speedList = new Hashtable<String, Integer>() {{
		put("deer", 1000);
	}};
	
	Hashtable<Long, Sim> ml_simList;
	private String type;
	int speed;
	static Random r;
	int[] previousDirection = {0, 0};
	boolean dontStop = true;
	
	protected SimControl(String type) {
		ml_simList = new Hashtable<Long, Sim>();
		this.speed = ml_speedList.get(type);
		this.type = type;
		// dontStop = false;
	}
	public String getType() {
		return type;
	}
	public Hashtable<Long, Sim> getSimList() {
		return ml_simList;
	}
	public void stop() {
		dontStop = false;
	}
	public void startGoing() {
		dontStop = true;
	}
	
	public Sim createSim(long id, String type) {
		Sim newdeer = new Sim(type, id);
		ml_simList.put(id, newdeer);
		return newdeer;
	}
	int[] getNewCoordinates(int[] prevxy) {
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
		int newY = prevxy[1] + nextDirection[1];
		previousDirection = nextDirection;
		int[] toBeReturned = { newX, newY, prevxy[2] };
		
		return toBeReturned;
	}
	public void run() {
		while(true) {
			if(dontStop == true) {
				Enumeration<Sim> e = ml_simList.elements();
				while(e.hasMoreElements()) {
					Sim curDeer = (Sim) e.nextElement();
					if (!curDeer.getDeath()) {
						((Sim) curDeer).move();
					}
				}
				try {
					Thread.sleep(speed);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
