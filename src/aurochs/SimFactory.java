package aurochs;

import java.util.Hashtable;

public class SimFactory {
	private long id;
	private Hashtable<String, SimControl> simControlList;
	private Hashtable<String, Thread> simControlThreadList;
	private static SimFactory instance;
	
	public static String[] typeList = {
		"deer",
		"squirrel"
	};
	
	protected SimFactory() {
		id = 0;
		simControlList = new Hashtable<String, SimControl>();
		simControlThreadList = new Hashtable<String, Thread>();
	}
	public static SimFactory getInstance() {
		if (instance == null) {
			instance = new SimFactory();
		}
		return instance;
	}
	public Hashtable<String, SimControl> getSimControlList() {
		return simControlList;
	}
	public Hashtable<String, Thread> getSimControlThreadList() {
		return simControlThreadList;
	}
	public long getId() {
		return this.id;
	}
	
	public Sim newSim(String type) throws IllegalArgumentException {
		
		SimControl control;
		Sim newsim = null;
		try {
			control = simControlList.get(type);
			newsim = control.createSim(id, type);
		}
		catch (NullPointerException e) {
			for (String i : typeList) {
				if (type.equals(i)) {
					control = new SimControl(type);
					simControlList.put(type, control);
					newsim = control.createSim(id, type);
					
					/* create simControl thread for later starting/stopping */
					
					Thread newSimControl = new Thread(control);
					newSimControl.start();
					
					simControlThreadList.put(type, newSimControl);
				}
			}
		}
		++id;
		
		if (newsim != null) {
			return newsim;
		}
		throw new IllegalArgumentException();
	}
	public Sim getSim(String type, long id) throws IllegalArgumentException {
		Sim toBeReturned = null;
		try {
			toBeReturned = simControlList.get(type).getSimList().get(id);
		}
		catch (NullPointerException e){
			throw new IllegalArgumentException();
		}
		
		return toBeReturned;
	}
	public SimControl getSimControl(String type) throws IllegalArgumentException {
		SimControl toReturn = simControlList.get(type);
		if (toReturn != null) {
			return toReturn;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	public Thread getSimControlThread(String type) throws IllegalArgumentException {
		Thread toReturn = simControlThreadList.get(type);
		if (toReturn != null) {
			return toReturn;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
}
