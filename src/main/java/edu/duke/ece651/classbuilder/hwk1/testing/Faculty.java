import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Faculty{

	private String name;

	private ArrayList<Character> taught;

	public String getName(){
		return name;
	}

	public char getTaught(int taught_num){
		return taught.get(taught_num);
	}

	public void setName(String name_new){
		name = name_new;
	}

	public void setTaught(int taught_num, char taught_new){
		taught.set(taught_num, taught_new);
	}

	public int numTaught(){
		return taught.size();
	}

	void addTaught(char taught_new){
		taught.add(taught_new);
	}

	public Faculty(){
		taught = new ArrayList<Character>();
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
			JSONObject taught_parent = new JSONObject();
			JSONArray taught_array = new JSONArray();
			for(int taught_i = 0;taught_i < taught.size();taught_i++){
				taught_array.put(taught.get(taught_i));
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
