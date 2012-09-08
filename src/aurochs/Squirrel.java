/**
 * 
 */
package aurochs;

import java.util.Random;

/**
 * @author Sam
 *
 */
public class Squirrel implements ISim, Runnable{
	Random r = new Random();
	private int xLoc = 100;
	private int yLoc = 100;
	private long simId;
	private String simType;
	private boolean toDie;
	int[] previousDirection = {1,1};
	
	public Squirrel(long id, String type) {
		this.simId = id;
		this.simType = type;
		this.toDie = false;
	}
	
	public long getsimId() {
		return this.simId;
	}
	public int getxLoc() {
		return this.xLoc;
	}
	public int getyLoc() {
		return this.yLoc;
	}
	
	public void move() {
		int randomIndex = r.nextInt(5);
		// System.out.println(randomIndex);
		int[] nextDirection = {0, 0};
		
		switch (randomIndex) {
		case 0: case 1:
			nextDirection = previousDirection;
			break;
		case 2:
			 nextDirection[0] = 1;
			 nextDirection[1] = 1;
			 break;
		 case 3:
			 nextDirection[0] = 1;
			 nextDirection[1] = -1;
			 break;
		 case 4:
			 nextDirection[0] = -1;
			 nextDirection[1] = 1;
			 break;
		}
		
		int newX = xLoc + nextDirection[0];
		int newY = yLoc + nextDirection[0];
		xLoc = newX;
		yLoc = newY;
		 
		previousDirection = nextDirection;
	}
	
	public void run() {
		while (toDie == false) {
			move();
			System.out.println(simType + " " + simId + ": " + getxLoc() + ", " + getyLoc());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void die() {
		toDie = true;
	}
}
