package aurochs;

public class SimMaster {
	private static SimMaster instance;
	
	public static SimMaster getInstance() {
		if (instance == null) {
			instance = new SimMaster();
		}
		return instance;
	}
	
	public void create() {
		System.out.println("Enter type?");
		for (String i : SimFactory.typeList) {
			System.out.println("\t" + i);
		}
		String ans = Entry.consoleIn().toLowerCase();
		
		try {
			SimFactory.getInstance().newSim(ans);
			// System.out.println(newsim.getsimId());
		}
		catch (IllegalArgumentException e) {
			System.out.println("Illegal type.");
			return;
		}
		
	}
	public void stop() {
		System.out.println("Enter type.");
		String simType = Entry.consoleIn();
		boolean legalId = true;
		System.out.println("Enter id.");
		String simId = Entry.consoleIn();
		Sim toDelete = null;
		try {
			toDelete = SimFactory.getInstance().getSim(simType, Long.parseLong(simId));
		}
		catch (IllegalArgumentException e) {
			System.out.println("Invalid simType or simId.");
			legalId = false;
		}
		if (legalId == true) {
			toDelete.die();
			SimFactory.getInstance().getSimControlList().get(simType).getSimList().remove(simId);
		}
	}
	public void locate() {
		System.out.println("Enter id.");
		String simId = Entry.consoleIn();
		try {
			int[] loc = Map.getInstance().locateSim(Long.parseLong(simId));
			System.out.println(loc[0] + ", " + loc[1]);
		}
		catch (IllegalArgumentException e) {
			System.out.println("Invalid simType or simId.");
		}
	}
}
