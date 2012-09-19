package aurochs;

import java.util.Enumeration;
import java.util.Hashtable;

public class SimControl implements Runnable {
	static final Hashtable<String, Integer> ml_speedList = new Hashtable<String, Integer>() {{
		put("deer", 1000);
	}};
	
	static Hashtable<Long, Sim> ml_simList;
	private String type;
	int speed;
	
	public SimControl(String type) {
		ml_simList = new Hashtable<Long, Sim>();
		this.speed = ml_speedList.get(type);
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	public Hashtable<Long, Sim> getSimList() {
		return ml_simList;
	}
	
	public Sim createSim(long id, String type) {
		Sim newdeer = new Sim(type, id);
		SimControl.ml_simList.put(id, newdeer);
		return newdeer;
	}
	public void run() {
		while(true) {
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
