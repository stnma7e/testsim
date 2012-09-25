package aurochs;

import redis.clients.jedis.Jedis;

public class Sim {
	private long mL_id;
	private boolean mB_toDie;
	private String mS_type;
	Jedis jedis;
	SimControl parentControl;
	int xLoc,
		yLoc,
		zLoc,
		wLoc,
		mapId;
	
	public Sim(String type, long id) {
		this.mS_type = type;
		this.mL_id = id;
		this.mB_toDie = false;
		parentControl = SimFactory.getInstance().getSimControlList().get(mS_type);
		jedis = Map.getInstance().getJedisPool().getResource();
		jedis.hset("simlocations", String.valueOf(mL_id), "100, 100, 0"); /*starting point for all*/
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
		int[] xypos = parentControl.getNewCoordinates(Map.getInstance().locateSim(mL_id));
		
		String s = Map.getInstance().parseAsStringLocation(xypos);
		
		try {
			Map.getInstance().moveSim(mL_id, s);
		} catch (Exception e) {
		}
		
		System.out.println(mS_type + " " + mL_id + ": " + s);
	}
}
