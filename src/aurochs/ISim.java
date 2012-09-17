package aurochs;

/**
 * @author Sam
 *
 */
public interface ISim {
	int xLoc = 0;
	int yLoc = 0;
	long simId = 0;
	
	long getsimId();
	int getxLoc();
	int getyLoc();
	void move();
	void die();
}

