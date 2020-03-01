package hwk1.testing.nameref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Faculty{

	private String name;

	private ArrayList<Course> courses;

	public String getName(){
		return name;
	}

	public Course getCourses(int courses_num){
		return courses.get(courses_num);
	}

	public void setName(String name_new){
		name = name_new;
	}

	public void setCourses(int courses_num, Course courses_new){
		courses.set(courses_num, courses_new);
	}

	public int numCourses(){
		return courses.size();
	}

	public void addCourses(Course courses_new){
		courses.add(courses_new);
	}

	public Faculty(){
		courses = new ArrayList<Course>();
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
			serial.put("type", "Faculty");
			serial.put("id", hMap.size());
			JSONArray values = new JSONArray();
			JSONObject name_parent = new JSONObject();
			name_parent.put("name", name);
			values.put(name_parent);
			JSONObject courses_parent = new JSONObject();
			JSONArray courses_array = new JSONArray();
			JSONObject courses_child = new JSONObject();
			Course courses_ele;
			for(int courses_i = 0;courses_i < courses.size();courses_i++){
				courses_ele = courses.get(courses_i);
				courses_child = courses_ele.toJSONid(hMap);
				courses_array.put(courses_child);
			}
			courses_parent.put("courses", courses_array);
			values.put(courses_parent);
			serial.put("values", values);
		}
		return serial;
	}

	public JSONObject toJSON() throws JSONException{
		return toJSONid(new HashMap<Object, Integer>());
	}
}
