package aurochs;

import java.util.Enumeration;
import java.util.Hashtable;

public class DeerControl implements ISimControl, Runnable {
	static Hashtable<Long, ISim> ml_simList;
	private String type;
	int speed;
	
	public DeerControl() {
		ml_simList = new Hashtable<Long, ISim>();
		this.type = "deer";
		this.speed = speedList.get(type);
	}
	
	public String getType() {
		return type;
	}
	public Hashtable<Long, ISim> getSimList() {
		return ml_simlist;
	}
	
	public ISim createSim(long id, String type) {
		ISim newdeer = new Deer(id, type);
		DeerControl.ml_simList.put(id, newdeer);
		return newdeer;
	}
	public void run() {
		while(true) {
			Enumeration<ISim> e = ml_simList.elements();
			while(e.hasMoreElements()) {
				Deer curDeer = (Deer) e.nextElement();
				if (!curDeer.getDeath()) {
					((ISim) curDeer).move();
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
