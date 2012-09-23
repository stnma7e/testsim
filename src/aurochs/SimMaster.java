package aurochs;

public class SimMaster {
	private static SimMaster instance;
	protected SimMaster() {
		
	}
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
			Sim newsim = SimFactory.getInstance().newSim(ans);
			System.out.println(newsim.getSimId());
		}
		catch (IllegalArgumentException e) {
			System.err.println("Illegal type.");
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
			System.err.println("Invalid simType or simId.");
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
			System.err.println("Invalid simType or simId.");
		}
	}
	public void startControl() {
		System.out.println("Enter type.");
		String type = Entry.consoleIn();
		try {
			SimFactory.getInstance().getSimControl(type).startGoing();
		}
		catch (IllegalArgumentException e) {
			System.err.println("Invalid type.");
		}
	}
	public void stopControl() {
		System.out.println("Enter type.");
		String type = Entry.consoleIn();
		
		try {
			SimFactory.getInstance().getSimControl(type).stop();
		}
		catch (IllegalArgumentException e) {
			System.err.println("Invalid type.");
		}
	}
}
