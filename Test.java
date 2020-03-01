import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Test{

	private int x;

	public int getX(){
		return x;
	}

	public void setX(int x_new){
		x = x_new;
	}

	public Test(){
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
			serial.put("type", "Test");
			serial.put("id", hMap.size());
			JSONArray values = new JSONArray();
			JSONObject x_parent = new JSONObject();
			x_parent.put("x", x);
			values.put(x_parent);
			serial.put("values", values);
		}
		return serial;
	}

	public JSONObject toJSON() throws JSONException{
		return toJSONid(new HashMap<Object, Integer>());
	}
}
