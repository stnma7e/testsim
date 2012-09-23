package aurochs;

import redis.clients.jedis.Jedis;

public class Sim {
	private long mL_id;
	private boolean mB_toDie;
	private String mS_type;
	private int mI_curXLoc, mI_curYLoc;
	Jedis jedis;
	SimControl parentControl;
	
	public Sim(String type, long id) {
		this.mS_type = type;
		this.mL_id = id;
		this.mB_toDie = false;
		this.mI_curXLoc = 100;
		this.mI_curYLoc = 100;
		parentControl = SimFactory.getInstance().getSimControlList().get(mS_type);
		jedis = Map.getInstance().getJedisPool().getResource();
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
		/*
			int[] xypos = Map.getInstance().locateSim(this);
			if (xypos != null) {
				Map.getInstance().moveSim(xypos, this);
			} else {
				int[] xypos1 = {
					mI_curXLoc,
					mI_curYLoc
				};
				Map.getInstance().moveSim(xypos1, this);
			}
			String jedisxypos = String.valueOf(mI_curXLoc);
			jedisxypos.concat(", " + String.valueOf(mI_curYLoc));
			jedis.sadd("locationofsims", jedisxypos, String.valueOf(mL_id));
	
			int[] newxy = Map.getInstance().locateSim(this);
		*/
		jedis.hset(String.valueOf(this.mL_id), "xLocation", String.valueOf(++mI_curXLoc));
		jedis.hset(String.valueOf(this.mL_id), "yLocation", String.valueOf(++mI_curYLoc));
	}
}
