package aurochs;

public class Sim {
	private long mL_id;
	private boolean mB_toDie;
	private String mS_type;
	private int mI_curXLoc, mI_curYLoc;
	
	public Sim(String type, long id) {
		this.mS_type = type;
		this.mL_id = id;
		this.mB_toDie = false;
		this.mI_curXLoc = 100;
		this.mI_curYLoc = 100;
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
		return mI_curXLoc;
	}
	public int getyLoc() {
		return mI_curYLoc;
	}
	public boolean getDeath() {
		return mB_toDie;
	}
	
	public void move() {
		int[] xypos = { getxLoc(), getyLoc() };
		
		Map.getInstance().moveSim(xypos, this);
		int[] newxy = Map.getInstance().locateSim(this);
		
		System.out.println(mS_type + " " + mL_id + ": " + newxy[0] + ", " + newxy[1]);
	}
}
