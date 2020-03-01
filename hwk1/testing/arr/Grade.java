package hwk1.testing.arr;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Grade{

	private Course course;

	private String student;

	private double grade;

	public Course getCourse(){
		return course;
	}

	public String getStudent(){
		return student;
	}

	public double getGrade(){
		return grade;
	}

	public void setCourse(Course course_new){
		course = course_new;
	}

	public void setStudent(String student_new){
		student = student_new;
	}

	public void setGrade(double grade_new){
		grade = grade_new;
	}

	public Grade(){
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
			serial.put("type", "Grade");
			serial.put("id", hMap.size());
			JSONArray values = new JSONArray();
			JSONObject course_child = course.toJSONid(hMap);
			JSONObject course_parent = new JSONObject();
			course_parent.put("course", course_child);
			values.put(course_parent);
			JSONObject student_parent = new JSONObject();
			student_parent.put("student", student);
			values.put(student_parent);
			JSONObject grade_parent = new JSONObject();
			grade_parent.put("grade", grade);
			values.put(grade_parent);
			serial.put("values", values);
		}
		return serial;
	}

	public JSONObject toJSON() throws JSONException{
		return toJSONid(new HashMap<Object, Integer>());
	}
}
