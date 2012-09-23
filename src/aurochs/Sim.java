package aurochs;

import redis.clients.jedis.Jedis;

public class Sim {
	private long mL_id;
	private boolean mB_toDie;
	private String mS_type;
	Jedis jedis;
	SimControl parentControl;
	
	public Sim(String type, long id) {
		this.mS_type = type;
		this.mL_id = id;
		this.mB_toDie = false;
		parentControl = SimFactory.getInstance().getSimControlList().get(mS_type);
		jedis = Map.getInstance().getJedisPool().getResource();
		jedis.hset(String.valueOf(this.mL_id), "xLocation", String.valueOf(100));	/*starting point for all*/
		jedis.hset(String.valueOf(this.mL_id), "yLocation", String.valueOf(100));	/*starting point for all*/
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
		int[] xypos = parentControl.getNewCoordinates(Map.getInstance().locateSim(mL_id));
		
		jedis.hset(String.valueOf(this.mL_id), "xLocation", String.valueOf(xypos[0]));
		jedis.hset(String.valueOf(this.mL_id), "yLocation", String.valueOf(xypos[1]));
		
		System.out.println(mS_type + " " + mL_id + ": " + xypos[1] + ", " + xypos[0]);
	}
}
