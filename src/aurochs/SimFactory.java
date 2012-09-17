package aurochs;

import java.util.Hashtable;

public class SimFactory {
	private static long id;
	private static Hashtable<String, ISimControl> simControlList;
	private static Hashtable<String, Thread> simControlThreadList;
	
	public static String[] typeList = {
		"deer",
		"squirrel"
	};
	
	public SimFactory() {
		SimFactory.id = 0;
		simControlList = new Hashtable<String, ISimControl>();
	}

	public Hashtable<String, ISimControl> getSimControlList() {
		return simControlList;
	}
	public Hashtable<String, Thread> getSimControlThreadList() {
		return simControlThreadList;
	}
	
	public long getId() {
		return SimFactory.id;
	}
	public ISim newSim(String type) throws IllegalArgumentException {
		
		ISimControl control;
		ISim newsim;
		try {
			control = getSimControlList().get(type);
			newsim = control.createSim(id, type);
		}
		catch (NullPointerException e){
			switch (type) {
				case "deer":
					control = new DeerControl();
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
	public ISim getSim(String type, long id) throws IllegalArgumentException {
		ISim toBeReturned = null;
		
		if (type.equals("deer")) {
			try {
				toBeReturned = DeerControl.ml_simlist.get(id);
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
