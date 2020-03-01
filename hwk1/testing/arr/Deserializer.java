package hwk1.testing.arr;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Deserializer{
	public static Course real_readCourse(JSONObject js, HashMap<Integer, Object> hmap) throws JSONException{
		int id = js.getInt("id");
		Course x = new Course();
		hmap.put(id, x);
		JSONArray all_fs = js.getJSONArray("values");
		for(int instructor_j = 0;instructor_j < all_fs.length();instructor_j++){
			JSONObject p_f = all_fs.getJSONObject(instructor_j);
			if(p_f.has("instructor")){
				JSONObject instructor_c = p_f.getJSONObject("instructor");
				if(instructor_c.has("ref")){
					int ref = instructor_c.getInt("ref");
					Faculty instructor_obj = (Faculty)hmap.get(ref);
					x.setInstructor(instructor_obj);
				}
				else{
					Faculty instructor_child = real_readFaculty(instructor_c, hmap);
					x.setInstructor(instructor_child);
				}
			}
		}
		for(int numStudents_j = 0;numStudents_j < all_fs.length();numStudents_j++){
			JSONObject p_f = all_fs.getJSONObject(numStudents_j);
			if(p_f.has("numStudents")){
					int numStudents_pri = (int)p_f.get("numStudents");
					x.setNumStudents(numStudents_pri);
			}
		}
		for(int grades_j = 0;grades_j < all_fs.length();grades_j++){
			JSONObject p_f = all_fs.getJSONObject(grades_j);
			if(p_f.has("grades")){
				JSONArray grades_ar = p_f.getJSONArray("grades");
				for(int grades_l = 0; grades_l < grades_ar.length();grades_l++){
					JSONObject grades_f = grades_ar.getJSONObject(grades_l);
					if(grades_f.has("ref")){
						Grade y = (Grade)hmap.get("ref");
						x.addGrades(y);
					}
					else{
						Grade z = real_readGrade(grades_f, hmap);
						x.addGrades(z);
					}
				}
			}
		}
	return x;
	}

	public static Office real_readOffice(JSONObject js, HashMap<Integer, Object> hmap) throws JSONException{
		int id = js.getInt("id");
		Office x = new Office();
		hmap.put(id, x);
		JSONArray all_fs = js.getJSONArray("values");
		for(int occupant_j = 0;occupant_j < all_fs.length();occupant_j++){
			JSONObject p_f = all_fs.getJSONObject(occupant_j);
			if(p_f.has("occupant")){
				JSONObject occupant_c = p_f.getJSONObject("occupant");
				if(occupant_c.has("ref")){
					int ref = occupant_c.getInt("ref");
					Faculty occupant_obj = (Faculty)hmap.get(ref);
					x.setOccupant(occupant_obj);
				}
				else{
					Faculty occupant_child = real_readFaculty(occupant_c, hmap);
					x.setOccupant(occupant_child);
				}
			}
		}
		for(int number_j = 0;number_j < all_fs.length();number_j++){
			JSONObject p_f = all_fs.getJSONObject(number_j);
			if(p_f.has("number")){
					int number_pri = (int)p_f.get("number");
					x.setNumber(number_pri);
			}
		}
		for(int building_j = 0;building_j < all_fs.length();building_j++){
			JSONObject p_f = all_fs.getJSONObject(building_j);
			if(p_f.has("building")){
					String building_pri = (String)p_f.get("building");
					x.setBuilding(building_pri);
			}
		}
	return x;
	}

	public static Faculty real_readFaculty(JSONObject js, HashMap<Integer, Object> hmap) throws JSONException{
		int id = js.getInt("id");
		Faculty x = new Faculty();
		hmap.put(id, x);
		JSONArray all_fs = js.getJSONArray("values");
		for(int name_j = 0;name_j < all_fs.length();name_j++){
			JSONObject p_f = all_fs.getJSONObject(name_j);
			if(p_f.has("name")){
					String name_pri = (String)p_f.get("name");
					x.setName(name_pri);
			}
		}
		for(int taught_j = 0;taught_j < all_fs.length();taught_j++){
			JSONObject p_f = all_fs.getJSONObject(taught_j);
			if(p_f.has("taught")){
				JSONArray taught_ar = p_f.getJSONArray("taught");
				for(int taught_l = 0; taught_l < taught_ar.length();taught_l++){
					JSONObject taught_f = taught_ar.getJSONObject(taught_l);
					if(taught_f.has("ref")){
						Course y = (Course)hmap.get("ref");
						x.addTaught(y);
					}
					else{
						Course z = real_readCourse(taught_f, hmap);
						x.addTaught(z);
					}
				}
			}
		}
	return x;
	}

	public static Grade real_readGrade(JSONObject js, HashMap<Integer, Object> hmap) throws JSONException{
		int id = js.getInt("id");
		Grade x = new Grade();
		hmap.put(id, x);
		JSONArray all_fs = js.getJSONArray("values");
		for(int course_j = 0;course_j < all_fs.length();course_j++){
			JSONObject p_f = all_fs.getJSONObject(course_j);
			if(p_f.has("course")){
				JSONObject course_c = p_f.getJSONObject("course");
				if(course_c.has("ref")){
					int ref = course_c.getInt("ref");
					Course course_obj = (Course)hmap.get(ref);
					x.setCourse(course_obj);
				}
				else{
					Course course_child = real_readCourse(course_c, hmap);
					x.setCourse(course_child);
				}
			}
		}
		for(int student_j = 0;student_j < all_fs.length();student_j++){
			JSONObject p_f = all_fs.getJSONObject(student_j);
			if(p_f.has("student")){
					String student_pri = (String)p_f.get("student");
					x.setStudent(student_pri);
			}
		}
		for(int grade_j = 0;grade_j < all_fs.length();grade_j++){
			JSONObject p_f = all_fs.getJSONObject(grade_j);
			if(p_f.has("grade")){
					double grade_pri = (double)p_f.get("grade");
					x.setGrade(grade_pri);
			}
		}
	return x;
	}

	public static Course readCourse(JSONObject js) throws JSONException{
		Course Course_de = real_readCourse(js, new HashMap<Integer, Object>());
		return Course_de;
}

	public static Office readOffice(JSONObject js) throws JSONException{
		Office Office_de = real_readOffice(js, new HashMap<Integer, Object>());
		return Office_de;
}

	public static Faculty readFaculty(JSONObject js) throws JSONException{
		Faculty Faculty_de = real_readFaculty(js, new HashMap<Integer, Object>());
		return Faculty_de;
}

	public static Grade readGrade(JSONObject js) throws JSONException{
		Grade Grade_de = real_readGrade(js, new HashMap<Integer, Object>());
		return Grade_de;
}

}
