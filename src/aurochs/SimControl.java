package aurochs;

import java.util.Hashtable;

/**
 * @author Sam
 *
 */
public class SimControl {
	public Hashtable<Long, Thread> simlist;
	public Hashtable<Long, ISim> simobjlist;
	
	public SimControl() {
		simlist = new Hashtable<Long, Thread>();
		simobjlist = new Hashtable<Long, ISim>();
	}
	public void create() {
		System.out.println("Which type?");
		for (String i : SimFactory.typeList) {
			System.out.println("\t" + i);
		}
		String ans = Main.consoleIn().toLowerCase();
		ISim newsim;
		
		try {
			newsim = Main.simfactory.GetSim(ans);
			System.out.println(newsim.getsimId());
		}
		catch (IllegalArgumentException e) {
			System.out.print("Illegal type.");
			return;
		}
		
		simobjlist.put(newsim.getsimId(), newsim);
		simlist.put(newsim.getsimId(), new Thread(newsim));
		simlist.get(newsim.getsimId()).start();
	}
	public void stop() {
		System.out.println("Enter simId.");
		String simId = Main.consoleIn();
		boolean legalId = true;
		try {
			simobjlist.get(Long.parseLong(simId)).die();
		}
		catch (NullPointerException e) {
			System.out.println("Invalid simId.");
			legalId = false;
		}
		if (legalId == true) {
			simobjlist.remove(Long.parseLong(simId));
			simlist.remove(Long.parseLong(simId));
		}
	}
}
