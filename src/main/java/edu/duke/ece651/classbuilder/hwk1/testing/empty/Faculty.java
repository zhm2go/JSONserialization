package hwk1.testing.empty;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Faculty{

	private String name;

	public String getName(){
		return name;
	}

	public void setName(String name_new){
		name = name_new;
	}

	public Faculty(){
	}

	public JSONObject toJSONid(HashMap<Object, Integer> hMap) throws JSONException{
		JSONObject serial = new JSONObject();
		if(hMap.containsKey(this)){
			serial.put("ref", hMap.size());
		}
		else{
			hMap.put(this, hMap.size());
			serial.put("type", "Faculty");
			serial.put("id", hMap.size());
			JSONArray values = new JSONArray();
			JSONObject name_parent = new JSONObject();
			name_parent.put("name", name);
			values.put(name_parent);
			serial.put("values", values);
		}
		return serial;
	}

	public JSONObject toJSON() throws JSONException{
		return toJSONid(new HashMap<Object, Integer>());
	}
}
