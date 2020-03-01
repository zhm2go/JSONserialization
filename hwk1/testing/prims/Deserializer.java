package hwk1.testing.prims;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Deserializer{
	public static Prims real_readPrims(JSONObject js, HashMap<Integer, Object> hmap) throws JSONException{
		int id = js.getInt("id");
		Prims x = new Prims();
		hmap.put(id, x);
		JSONArray all_fs = js.getJSONArray("values");
		for(int x_j = 0;x_j < all_fs.length();x_j++){
			JSONObject p_f = all_fs.getJSONObject(x_j);
			if(p_f.has("x")){
					int x_pri = (int)p_f.get("x");
					x.setX(x_pri);
			}
		}
		for(int isAwesome_j = 0;isAwesome_j < all_fs.length();isAwesome_j++){
			JSONObject p_f = all_fs.getJSONObject(isAwesome_j);
			if(p_f.has("isAwesome")){
					boolean isAwesome_pri = (boolean)p_f.get("isAwesome");
					x.setIsAwesome(isAwesome_pri);
			}
		}
		for(int ateBits_j = 0;ateBits_j < all_fs.length();ateBits_j++){
			JSONObject p_f = all_fs.getJSONObject(ateBits_j);
			if(p_f.has("ateBits")){
					byte ateBits_pri = (byte)(int)p_f.get("ateBits");
					x.setAteBits(ateBits_pri);
			}
		}
		for(int boat_j = 0;boat_j < all_fs.length();boat_j++){
			JSONObject p_f = all_fs.getJSONObject(boat_j);
			if(p_f.has("boat")){
					float boat_pri = (float)p_f.get("boat");
					x.setBoat(boat_pri);
			}
		}
		for(int trouble_j = 0;trouble_j < all_fs.length();trouble_j++){
			JSONObject p_f = all_fs.getJSONObject(trouble_j);
			if(p_f.has("trouble")){
					double trouble_pri = (double)p_f.get("trouble");
					x.setTrouble(trouble_pri);
			}
		}
		for(int isntPronouncedLikeCare_j = 0;isntPronouncedLikeCare_j < all_fs.length();isntPronouncedLikeCare_j++){
			JSONObject p_f = all_fs.getJSONObject(isntPronouncedLikeCare_j);
			if(p_f.has("isntPronouncedLikeCare")){
					char isntPronouncedLikeCare_pri = (char)(int)p_f.get("isntPronouncedLikeCare");
					x.setIsntPronouncedLikeCare(isntPronouncedLikeCare_pri);
			}
		}
		for(int waysAway_j = 0;waysAway_j < all_fs.length();waysAway_j++){
			JSONObject p_f = all_fs.getJSONObject(waysAway_j);
			if(p_f.has("waysAway")){
					long waysAway_pri = (long)p_f.get("waysAway");
					x.setWaysAway(waysAway_pri);
			}
		}
		for(int stackOfPancakes_j = 0;stackOfPancakes_j < all_fs.length();stackOfPancakes_j++){
			JSONObject p_f = all_fs.getJSONObject(stackOfPancakes_j);
			if(p_f.has("stackOfPancakes")){
					short stackOfPancakes_pri = (short)(int)p_f.get("stackOfPancakes");
					x.setStackOfPancakes(stackOfPancakes_pri);
			}
		}
	return x;
	}

	public static Prims readPrims(JSONObject js) throws JSONException{
		Prims Prims_de = real_readPrims(js, new HashMap<Integer, Object>());
		return Prims_de;
}

}
