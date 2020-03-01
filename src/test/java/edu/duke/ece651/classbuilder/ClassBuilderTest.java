package edu.duke.ece651.classbuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import hwk1.testing.arr.Faculty;
import hwk1.testing.arr.Grade;
import hwk1.testing.arr.Office;
import hwk1.testing.arr.Course;
import hwk1.testing.arr.Deserializer;
public class ClassBuilderTest {
  @Test
  public void test_() throws IOException{
    ClassBuilder a = new ClassBuilder(
                                      "{ 'classes' : [{'name' : 'Test', 'fields' : [{'name' : 'x', 'type' : 'int'}]}]}");
    Collection<String> a_names = a.getClassNames();
    ///System.out.println(a_names.iterator().next());
    ///assert(a_names.iterator().next().equals("Test"));
    a.createAllClasses("");
    	ClassBuilder b = new ClassBuilder("{'package':'hwk1.testing.empty','classes' :[{'name' : 'Course', 'fields' : [ {'name' : 'instructor', 'type' : 'Faculty'},{'name' : 'numStudents', 'type' : 'int' }]},{'name' : 'Office',  'fields' : [ {'name' : 'occupant', 'type': 'Faculty'},{'name' : 'number', 'type': 'int'},{'name' : 'building' , 'type': 'String'}]},{'name' : 'Faculty', 'fields' : [ {'name' : 'name', 'type' : 'String' }]},{'name' : 'Grade', 'fields' : [ {'name' : 'course', 'type' : 'Course'},{'name' : 'student', 'type' : 'String'},{'name' : 'grade', 'type': 'double'}]}]}");
	b.createAllClasses("");
    ///System.out.println(resultb);
	ClassBuilder c = new ClassBuilder("{  'classes' :[{'name' : 'Course', 'fields' : [ {'name' : 'instructor', 'type' : 'Faculty'},{'name' : 'numStudents', 'type' : 'int' },{'name' : 'grades', 'type' : {'e': 'Grade'}}]},{'name' : 'Office',  'fields' : [ {'name' : 'occupant', 'type': 'Faculty'},{'name' : 'number', 'type': 'int'},{'name' : 'building' , 'type': 'String'}]},{'name' : 'Faculty', 'fields' : [ {'name' : 'name', 'type' : 'String' },{'name' : 'taught', 'type' : {'e': 'char'}} ]},{'name' : 'Grade', 'fields' : [ {'name' : 'course', 'type' : 'Course'},{'name' : 'student', 'type' : 'String'},{'name' : 'grade', 'type': 'double'}]}]}");
	c.createAllClasses("hwk1/testing/");
	File d_f = new File("empty.json");
	///System.out.println(d_f.exists());
	InputStream d_is = new FileInputStream(d_f);
	ClassBuilder d = new ClassBuilder(d_is);
	d.createAllClasses("");

File e_f = new File("arr.json");
	///System.out.println(e_f.exists());
	InputStream e_is = new FileInputStream(e_f);
	ClassBuilder e = new ClassBuilder(e_is);
	e.createAllClasses("");

File f_f = new File("simple.json");
	///System.out.println(f_f.exists());
	InputStream f_is = new FileInputStream(f_f);
	ClassBuilder f = new ClassBuilder(f_is);
	f.createAllClasses("");

File g_f = new File("prims.json");
	///System.out.println(g_f.exists());
	InputStream g_is = new FileInputStream(g_f);
	ClassBuilder g = new ClassBuilder(g_is);
	g.createAllClasses("");

File h_f = new File("nameRef.json");
	///System.out.println(h_f.exists());
	InputStream h_is = new FileInputStream(h_f);
	ClassBuilder h = new ClassBuilder(h_is);
	h.createAllClasses("");
	
/*File i_f = new File("simplearray.json");
	System.out.println(i_f.exists());
	InputStream i_is = new FileInputStream(i_f);
	ClassBuilder i = new ClassBuilder(i_is);
	i.createAllClasses("../ece651-hwk1-tester/src/main/java/");*/
	/*Office o = new Office();
	Faculty fa = new Faculty();
	fa.setName("drew");
	Course c1 = new Course();
	Course c2 = new Course();
	c1.setNumStudents(55);
	c2.setNumStudents(130);
	c1.setInstructor(fa);
	c2.setInstructor(fa);
	fa.addTaught(c1);
	fa.addTaught(c2);
	o.setOccupant(fa);
	System.out.println(fa.toJSON());
	String result = "x";
    assert(result.equals("Test"));*/
  }
	
}












