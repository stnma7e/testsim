package aurochs;

public class SimMaster {
	
	public void create() {
		System.out.println("Enter type?");
		for (String i : SimFactory.typeList) {
			System.out.println("\t" + i);
		}
		String ans = Entry.consoleIn().toLowerCase();
		
		try {
			Entry.simfactory.newSim(ans);
			// System.out.println(newsim.getsimId());
		}
		catch (IllegalArgumentException e) {
			System.out.print("Illegal type.");
			return;
		}
		
	}
	public void stop() {
		System.out.println("Enter type.");
		String simType = Entry.consoleIn();
		boolean legalId = true;
		System.out.println("Enter id.");
		String simId = Entry.consoleIn();
		ISim toDelete = null;
		try {
			toDelete = Entry.simfactory.getSim(simType, Long.parseLong(simId));
		}
		catch (IllegalArgumentException e) {
			System.out.println("Invalid simType or simId.");
			legalId = false;
		}
		if (legalId == true) {
			toDelete.die();
			Entry.simfactory.getSimControlList().get(simType).ml_simlist.remove(simId);
		}
	}
	public void locate() {
		System.out.println("Enter id.");
		String simId = Entry.consoleIn();
		try {
			int[] loc = Map.getInstance().locateSim(Long.parseLong(simId));
			for (int i : loc) {
				// System.out.println(i);
			}
			System.out.println(loc[0]);
			System.out.println(loc[1]);
		}
		catch (IllegalArgumentException e) {
			System.out.println("Invalid simType or simId.");
		}
	}
}
