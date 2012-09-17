package aurochs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Entry {
	public static SimFactory simfactory;
	public static SimMaster simmaster;
	public static String[] comlist = {
			"Create",
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
		simfactory = new SimFactory();
		simmaster = new SimMaster();
		System.out.println("Type a command or type ? for help.");
		// (new Thread(new Frame())).start();
	}
	private static void mainloop() {
		String com = consoleIn().toLowerCase();
		
		switch (com) {
			case "create":
				simmaster.create();
				break;
			case "stop":
				simmaster.stop();
				break;
			case "?":
				System.out.println("Possible commands:\n");
				for (String i : Entry.comlist) {
					System.out.println("\t" + i);
				}
				break;
			case "exit":
				System.exit(0);
				break;
			default:
				System.out.println("Not a command.");
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
