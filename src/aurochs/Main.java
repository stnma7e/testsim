package aurochs;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Sam
 *
 */
public class Main {
	public static SimFactory simfactory;
	public static SimControl simcontrol;
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
		simcontrol = new SimControl();
		System.out.println("Type a command or type ? for help.");
		(new Thread(new Frame())).start();
	}
	
	private static void mainloop() {
		String com = consoleIn().toLowerCase();
		
		if (com.equals("create")) {
			simcontrol.create();
		}
		else if (com.equals("stop")) {
			simcontrol.stop();
		}
		else if (com.equals("?")) {
			for (String i : Main.comlist) {
				System.out.println("\t" + i);
			}
		}
		else if (com.equals("exit")) {
			System.exit(0);
		}
		else {
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
