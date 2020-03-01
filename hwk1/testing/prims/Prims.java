package hwk1.testing.prims;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Prims{

	private int x;

	private boolean isAwesome;

	private byte ateBits;

	private float boat;

	private double trouble;

	private char isntPronouncedLikeCare;

	private long waysAway;

	private short stackOfPancakes;

	public int getX(){
		return x;
	}

	public boolean getIsAwesome(){
		return isAwesome;
	}

	public byte getAteBits(){
		return ateBits;
	}

	public float getBoat(){
		return boat;
	}

	public double getTrouble(){
		return trouble;
	}

	public char getIsntPronouncedLikeCare(){
		return isntPronouncedLikeCare;
	}

	public long getWaysAway(){
		return waysAway;
	}

	public short getStackOfPancakes(){
		return stackOfPancakes;
	}

	public void setX(int x_new){
		x = x_new;
	}

	public void setIsAwesome(boolean isAwesome_new){
		isAwesome = isAwesome_new;
	}

	public void setAteBits(byte ateBits_new){
		ateBits = ateBits_new;
	}

	public void setBoat(float boat_new){
		boat = boat_new;
	}

	public void setTrouble(double trouble_new){
		trouble = trouble_new;
	}

	public void setIsntPronouncedLikeCare(char isntPronouncedLikeCare_new){
		isntPronouncedLikeCare = isntPronouncedLikeCare_new;
	}

	public void setWaysAway(long waysAway_new){
		waysAway = waysAway_new;
	}

	public void setStackOfPancakes(short stackOfPancakes_new){
		stackOfPancakes = stackOfPancakes_new;
	}

	public Prims(){
	}

	public JSONObject toJSONid(HashMap<Object, Integer> hMap) throws JSONException{
		JSONObject serial = new JSONObject();
		if(hMap.containsKey(this)){
			int key = hMap.get(this);
			serial.put("ref", key);
		}
		else{
			int sz = hMap.size();
			hMap.put(this, sz+1);
			serial.put("type", "Prims");
			serial.put("id", hMap.size());
			JSONArray values = new JSONArray();
			JSONObject x_parent = new JSONObject();
			x_parent.put("x", x);
			values.put(x_parent);
			JSONObject isAwesome_parent = new JSONObject();
			isAwesome_parent.put("isAwesome", isAwesome);
			values.put(isAwesome_parent);
			JSONObject ateBits_parent = new JSONObject();
			ateBits_parent.put("ateBits", ateBits);
			values.put(ateBits_parent);
			JSONObject boat_parent = new JSONObject();
			boat_parent.put("boat", boat);
			values.put(boat_parent);
			JSONObject trouble_parent = new JSONObject();
			trouble_parent.put("trouble", trouble);
			values.put(trouble_parent);
			JSONObject isntPronouncedLikeCare_parent = new JSONObject();
			isntPronouncedLikeCare_parent.put("isntPronouncedLikeCare", isntPronouncedLikeCare);
			values.put(isntPronouncedLikeCare_parent);
			JSONObject waysAway_parent = new JSONObject();
			waysAway_parent.put("waysAway", waysAway);
			values.put(waysAway_parent);
			JSONObject stackOfPancakes_parent = new JSONObject();
			stackOfPancakes_parent.put("stackOfPancakes", stackOfPancakes);
			values.put(stackOfPancakes_parent);
			serial.put("values", values);
		}
		return serial;
	}

	public JSONObject toJSON() throws JSONException{
		return toJSONid(new HashMap<Object, Integer>());
	}
}
