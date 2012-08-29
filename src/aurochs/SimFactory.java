/**
 * 
 */
package aurochs;

/**
 * @author James
 *
 */
public class SimFactory {
	private static long id;
	public static String[] typeList = {
		"Deer",
		"Squirrel"
	};
	
	public SimFactory() {
		SimFactory.id = 0;
	}
	
	public ISim GetSim(String type)
	{
		ISim toBeReturned = null;
		
		if (type.equals("deer")) {
			toBeReturned = new Deer(++id, type);
			System.out.println(id);
		}
		else if (type.equals("squirrel")) {
			toBeReturned = new Squirrel(++id, type);
			System.out.println(id);
		}
		else {
			throw new IllegalArgumentException();
		}
		
		return toBeReturned;
	}
}
