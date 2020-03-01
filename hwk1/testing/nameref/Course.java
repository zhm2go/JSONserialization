package hwk1.testing.nameref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Course{

	private Faculty instructor;

	private int numStudents;

	public Faculty getInstructor(){
		return instructor;
	}

	public int getNumStudents(){
		return numStudents;
	}

	public void setInstructor(Faculty instructor_new){
		instructor = instructor_new;
	}

	public void setNumStudents(int numStudents_new){
		numStudents = numStudents_new;
	}

	public Course(){
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
			serial.put("type", "Course");
			serial.put("id", hMap.size());
			JSONArray values = new JSONArray();
			JSONObject instructor_child = instructor.toJSONid(hMap);
			JSONObject instructor_parent = new JSONObject();
			instructor_parent.put("instructor", instructor_child);
			values.put(instructor_parent);
			JSONObject numStudents_parent = new JSONObject();
			numStudents_parent.put("numStudents", numStudents);
			values.put(numStudents_parent);
			serial.put("values", values);
		}
		return serial;
	}

	public JSONObject toJSON() throws JSONException{
		return toJSONid(new HashMap<Object, Integer>());
	}
}
