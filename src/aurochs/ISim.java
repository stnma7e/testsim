package aurochs;

/**
 * @author Sam
 *
 */
public interface ISim extends Runnable {
	int xLoc = 0;
	int yLoc = 0;
	long simId = 0;
	
	long getsimId();
	int getxLoc();
	int getyLoc();
	void move();
	void die();
}

