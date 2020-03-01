package hwk1.testing.simple;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Deserializer{
	public static Test real_readTest(JSONObject js, HashMap<Integer, Object> hmap) throws JSONException{
		int id = js.getInt("id");
		Test x = new Test();
		hmap.put(id, x);
		JSONArray all_fs = js.getJSONArray("values");
		for(int x_j = 0;x_j < all_fs.length();x_j++){
			JSONObject p_f = all_fs.getJSONObject(x_j);
			if(p_f.has("x")){
					int x_pri = (int)p_f.get("x");
					x.setX(x_pri);
			}
		}
	return x;
	}

	public static Test readTest(JSONObject js) throws JSONException{
		Test Test_de = real_readTest(js, new HashMap<Integer, Object>());
		return Test_de;
}

}
