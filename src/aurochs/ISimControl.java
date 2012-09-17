package aurochs;

import java.util.Hashtable;

public interface ISimControl extends Runnable {
	static final Hashtable<String, Integer> speedList = new Hashtable<String, Integer>() {{
		put("deer", 1000);
	}};
	String type = null;
	Hashtable<Long, ISim> ml_simlist = null;
	ISim createSim(long id, String type);
}
