package aurochs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Entry {
	public static String[] comlist = {
			"Create",
			"Kill",
			"Locate",
			"Start",
			"Stop",
			"Exit"
	};
	
	public static void main(String[] args) {
		startup();
		while (true) {
			mainloop();
		}
	}
	private static void startup() {
		System.out.println("Type a command or type ? for help.");
		// (new Thread(new Frame())).start();
	}
	private static void mainloop() {
		String com = consoleIn().toLowerCase();
		
		switch (com) {
			case "create":
				SimMaster.getInstance().create();
				break;
			case "kill":
				SimMaster.getInstance().stop();
				break;
			case "?":
				System.out.println("Possible commands:\n");
				for (String i : Entry.comlist) {
					System.out.println("\t" + i);
				}
				break;
			case "locate":
				SimMaster.getInstance().locate();
				break;
			case "start":
				SimMaster.getInstance().startControl();
				break;
			case "stop":
				SimMaster.getInstance().stopControl();
				break;
			case "exit":
				System.exit(0);
				break;
			default:
				System.err.println("Not a command.");
		}
	}
	public static String consoleIn() {
		try{
		    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		    String s = bufferRead.readLine();
	 
		    return s;
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return null;
		
	}
}
