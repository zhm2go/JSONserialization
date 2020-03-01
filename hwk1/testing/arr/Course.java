package hwk1.testing.arr;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Course{

	private Faculty instructor;

	private int numStudents;

	private ArrayList<Grade> grades;

	public Faculty getInstructor(){
		return instructor;
	}

	public int getNumStudents(){
		return numStudents;
	}

	public Grade getGrades(int grades_num){
		return grades.get(grades_num);
	}

	public void setInstructor(Faculty instructor_new){
		instructor = instructor_new;
	}

	public void setNumStudents(int numStudents_new){
		numStudents = numStudents_new;
	}

	public void setGrades(int grades_num, Grade grades_new){
		grades.set(grades_num, grades_new);
	}

	public int numGrades(){
		return grades.size();
	}

	public void addGrades(Grade grades_new){
		grades.add(grades_new);
	}

	public Course(){
		grades = new ArrayList<Grade>();
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
			JSONObject grades_parent = new JSONObject();
			JSONArray grades_array = new JSONArray();
			JSONObject grades_child = new JSONObject();
			Grade grades_ele;
			for(int grades_i = 0;grades_i < grades.size();grades_i++){
				grades_ele = grades.get(grades_i);
				grades_child = grades_ele.toJSONid(hMap);
				grades_array.put(grades_child);
			}
			grades_parent.put("grades", grades_array);
			values.put(grades_parent);
			serial.put("values", values);
		}
		return serial;
	}

	public JSONObject toJSON() throws JSONException{
		return toJSONid(new HashMap<Object, Integer>());
	}
}
