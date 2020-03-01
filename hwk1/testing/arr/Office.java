package hwk1.testing.arr;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Office{

	private Faculty occupant;

	private int number;

	private String building;

	public Faculty getOccupant(){
		return occupant;
	}

	public int getNumber(){
		return number;
	}

	public String getBuilding(){
		return building;
	}

	public void setOccupant(Faculty occupant_new){
		occupant = occupant_new;
	}

	public void setNumber(int number_new){
		number = number_new;
	}

	public void setBuilding(String building_new){
		building = building_new;
	}

	public Office(){
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
			serial.put("type", "Office");
			serial.put("id", hMap.size());
			JSONArray values = new JSONArray();
			JSONObject occupant_child = occupant.toJSONid(hMap);
			JSONObject occupant_parent = new JSONObject();
			occupant_parent.put("occupant", occupant_child);
			values.put(occupant_parent);
			JSONObject number_parent = new JSONObject();
			number_parent.put("number", number);
			values.put(number_parent);
			JSONObject building_parent = new JSONObject();
			building_parent.put("building", building);
			values.put(building_parent);
			serial.put("values", values);
		}
		return serial;
	}

	public JSONObject toJSON() throws JSONException{
		return toJSONid(new HashMap<Object, Integer>());
	}
}
