package aurochs;

import java.util.Hashtable;

public class SimFactory {
	private static long id;
	private static Hashtable<String, SimControl> simControlList;
	private static Hashtable<String, Thread> simControlThreadList;
	private static SimFactory instance;
	
	public static String[] typeList = {
		"deer",
		"squirrel"
	};
	
	public SimFactory() {
		SimFactory.id = 0;
		simControlList = new Hashtable<String, SimControl>();
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
		return SimFactory.id;
	}
	public Sim newSim(String type) throws IllegalArgumentException {
		
		SimControl control;
		Sim newsim;
		try {
			control = getSimControlList().get(type);
			newsim = control.createSim(id, type);
		}
		catch (NullPointerException e){
			switch (type) {
				case "deer":
					control = new SimControl("deer");
					getSimControlList().put(type, control);
					newsim = control.createSim(id, type);
					Thread newSimControl = new Thread(control);
					// simControlThreadList.put(type, newSimControl);
					newSimControl.start();
					++id;
					break;
				default:
					throw new IllegalArgumentException();
			}
			/*
			Thread newSimControl = new Thread(fact);
			newSimControl.start();
			*/
			// this.simControlThreadList.put(type, newSimControl);
		}
		return newsim;
	}
	public Sim getSim(String type, long id) throws IllegalArgumentException {
		Sim toBeReturned = null;
		
		if (type.equals("deer")) {
			try {
				toBeReturned = simControlList.get("deer").getSimList().get(id);
			}
			catch (NullPointerException e){
				throw new IllegalArgumentException();
			}
		}
		else if (type.equals("squirrel")) {
			// toBeReturned = new Squirrel(++id, type);
		}
		else {
			throw new IllegalArgumentException();
		}
		
		return toBeReturned;
	}
}
