package aurochs;

public class Sim {
	private long mL_id;
	private boolean mB_toDie;
	private String mS_type;
	
	public Sim(String type, long id) {
		this.mS_type = type;
		this.mL_id = id;
		this.mB_toDie = false;
	}
	
	public String getType() {
		return mS_type;
	}
	public long getSimId() {
		return this.mL_id;
	}
	public void die() {
		this.mB_toDie = true;
	}
	public int getxLoc() {
		
		return 0;
	}
	public int getyLoc() {
		
		return 0;
	}
	public boolean getDeath() {
		return mB_toDie;
	}
	
	public void move() {
		int[] xypos = { getxLoc(), getyLoc() };
		
		int[] newXYpos = { getxLoc(), getyLoc() };
		
		Map.getInstance().moveSim(xypos, newXYpos, this);
		
		System.out.println(mS_type + " " + mL_id + ": " + getxLoc() + ", " + getyLoc());
	}
}
