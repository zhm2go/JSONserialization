package hwk1.testing.empty;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Deserializer{
	public static Empty real_readEmpty(JSONObject js, HashMap<Integer, Object> hmap) throws JSONException{
		int id = js.getInt("id");
		Empty x = new Empty();
		hmap.put(id, x);
		JSONArray all_fs = js.getJSONArray("values");
	return x;
	}

	public static Empty readEmpty(JSONObject js) throws JSONException{
		Empty Empty_de = real_readEmpty(js, new HashMap<Integer, Object>());
		return Empty_de;
}

}
