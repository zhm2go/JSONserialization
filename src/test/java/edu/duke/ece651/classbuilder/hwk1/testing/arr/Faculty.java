package hwk1.testing.arr;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Faculty{

	private String name;

	private ArrayList<Course> taught;

	public String getName(){
		return name;
	}

	public Course getTaught(int taught_num){
		return taught.get(taught_num);
	}

	public void setName(String name_new){
		name = name_new;
	}

	public void setTaught(int taught_num, Course taught_new){
		taught.set(taught_num, taught_new);
	}

	public int numTaught(){
		return taught.size();
	}

	public void addTaught(Course taught_new){
		taught.add(taught_new);
	}

	public Faculty(){
		taught = new ArrayList<Course>();
	}

	public JSONObject toJSONid(HashMap<Object, Integer> hMap) throws JSONException{
		JSONObject serial = new JSONObject();
		if(hMap.containsKey(this)){
			int key = hMap.get(this);
			serial.put("ref", key+1);
		}
		else{
			int sz = hMap.size();
			hMap.put(this, sz);
			serial.put("type", "Faculty");
			serial.put("id", hMap.size());
			JSONArray values = new JSONArray();
			JSONObject name_parent = new JSONObject();
			name_parent.put("name", name);
			values.put(name_parent);
			JSONObject taught_parent = new JSONObject();
			JSONArray taught_array = new JSONArray();
			JSONObject taught_child = new JSONObject();
			Course taught_ele;
			for(int taught_i = 0;taught_i < taught.size();taught_i++){
				taught_ele = taught.get(taught_i);
				taught_child = taught_ele.toJSONid(hMap);
				taught_array.put(taught_child);
			}
			taught_parent.put("taught", taught_array);
			values.put(taught_parent);
			serial.put("values", values);
		}
		return serial;
	}

	public JSONObject toJSON() throws JSONException{
		return toJSONid(new HashMap<Object, Integer>());
	}
}
