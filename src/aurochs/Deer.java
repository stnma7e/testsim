package aurochs;

import java.util.Random;

public class Deer implements ISim {
	Random r = new Random();
	private int xLoc = 100;
	private int yLoc = 100;
	private long simId;
	private String simType;
	private boolean toDie;
	int[] previousDirection = {1,1};
	
	public Deer(long id, String type) {
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
	public boolean getDeath() {
		return this.toDie;
	}
	
	public void getNewCoordinates(int x, int y) {
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
		
		int newX = x + nextDirection[0];
		int newY = y + nextDirection[0];
		previousDirection = nextDirection;
		
		xLoc = newX;
		yLoc = newY;
	}
	
	public void move() {
		int[] xypos = { getxLoc(), getyLoc() };
		
		getNewCoordinates(xLoc, yLoc);
		
		int[] newXYpos = { getxLoc(), getyLoc() };
		Map.getInstance().moveSim(xypos, newXYpos, this);
		
		System.out.println(simType + " " + simId + ": " + getxLoc() + ", " + getyLoc());
	}
	
	public void die() {
		toDie = true;
	}
}