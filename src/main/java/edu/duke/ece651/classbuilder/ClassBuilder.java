package edu.duke.ece651.classbuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ClassBuilder {
  private JSONObject jsonObject;
  private JSONArray jsonArray;

  public ClassBuilder(String x) {///constructor that takes a string
    try {
      jsonObject = new JSONObject(x);
      JSONArray arr = jsonObject.getJSONArray("classes");
      jsonArray = arr;
      for (int i = 0; i < arr.length(); i++) {
        JSONObject obj = arr.getJSONObject(i);
        obj.getString("name");
        obj.getJSONArray("fields");
      }
    } catch (JSONException json_e) {
      System.err.println("JSONException: " + json_e.toString());
      json_e.printStackTrace();
      throw json_e;
    }
  }

  public ClassBuilder(InputStream origin) {///constructor that takes in inputstream, for file input
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(origin));
      StringBuilder builder = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        builder.append(line);
      }
      jsonObject = new JSONObject(builder.toString());
      JSONArray arr = jsonObject.getJSONArray("classes");
      jsonArray = arr;
      for (int i = 0; i < arr.length(); i++) {
        JSONObject obj = arr.getJSONObject(i);
        obj.getString("name");
        obj.getJSONArray("fields");
      }
    } catch (IOException io_e) {
      System.err.println("IOException: " + io_e.toString());
      io_e.printStackTrace();
      ///throw io_e;
    } catch (JSONException json_e) {
      System.err.println("JSONException: " + json_e.toString());
      json_e.printStackTrace();
      throw json_e;
    }
  }

  public Collection<String> getClassNames() {///get all classes' names from the jsonobject
    JSONArray classNames = jsonObject.getJSONArray("classes");
    Collection<String> nameCollection = new ArrayList<String>();
    for (int i = 0; i < classNames.length(); i++) {
      JSONObject jsonobj = classNames.getJSONObject(i);
      nameCollection.add(jsonobj.getString("name"));
    }
    return nameCollection;
  }

  public JSONObject getClass(String className) {///get a class JSONObject by its name
    JSONObject resultClass = null;
    for (int i = 0; i < jsonArray.length(); i++) {
      JSONObject targetClass = jsonArray.getJSONObject(i);
      if (targetClass.getString("name").equals(className)) {
        resultClass = targetClass;
      }
    }
    return resultClass;
    ///Exception except = new Exception("Exception message: class name not found!");
    ///except.printStackTrace();
  }
  public String getPrimitive(String type) {///convert the primitive type to its object,for arraylist use
    if(type.equals("int")){
      return "Integer";
    }
    if(type.equals("boolean")){
      return "Boolean";
    }
    if(type.equals("float")){
      return "Float";
    }
    if(type.equals("byte")){
      return "Byte";
    }
    if(type.equals("char")){
      return "Character";
    }
    if(type.equals("short")){
      return "Short";
    }
    if(type.equals("long")){
      return "Long";
    }
    if(type.equals("double")){
      return "Double";
    }
    return type;
  }
  
  public String getField(String sourceCode, JSONArray fields) {///get the field name by its class name
     for (int i = 0; i < fields.length(); i++) {///generate private attribute
      JSONObject field = fields.getJSONObject(i);
      Object type = field.get("type");
      String name = field.getString("name");
      if(type instanceof String){
      sourceCode += "\tprivate " + type + " " + name + ";\n\n";
      }else if(type instanceof JSONObject){
        JSONObject type_jsonobj = field.getJSONObject("type");
        String ArrayType = type_jsonobj.getString("e");
        String arrayListType = getPrimitive(ArrayType);
        sourceCode += "\tprivate ArrayList<" + arrayListType + "> " + name + ";\n\n";
      }
    }
   
    return sourceCode;
  }

   
  public String getPackage() {///get the JSONObject package name
    
    if(jsonObject.has("package")){
      
      String pkg = jsonObject.getString("package");
      ///System.out.println(pkg);
      return pkg;
    }
    return null;
  }

 
  
  public String getX(String sourceCode, JSONArray fields) {
    for (int i = 0; i < fields.length(); i++) {///generate getX method
      JSONObject field = fields.getJSONObject(i);
      Object type = field.get("type");
      String name = field.getString("name");
      if (type instanceof String) {///for single item
        sourceCode += "\tpublic " + type.toString() + " get" + name.substring(0, 1).toUpperCase() + name.substring(1) + "(){\n";
        sourceCode += "\t\treturn " + name + ";\n";
        sourceCode += "\t}\n\n";
      }
      else if (type instanceof JSONObject){///for array item
        JSONObject type_jsonobj = field.getJSONObject("type");
        String arrayType = type_jsonobj.getString("e");
        sourceCode += "\tpublic " + arrayType + " get" + name.substring(0, 1).toUpperCase() + name.substring(1) + "(int " + name + "_num){\n";
        sourceCode += "\t\treturn " + name + ".get(" + name + "_num);\n";
        sourceCode += "\t}\n\n";
      }
    }
    return sourceCode;
  }

   public String setX(String sourceCode, JSONArray fields) {
     for (int i = 0; i < fields.length(); i++) {///generate setX method
       JSONObject field = fields.getJSONObject(i);
      Object type = field.get("type");
      String name = field.getString("name");
      if(type instanceof String){///for single item
      sourceCode += "\tpublic " + "void" + " set" + name.substring(0,1).toUpperCase() + name.substring(1) + "(" + type.toString() + " " + name  + "_new)" + "{\n";
      sourceCode += "\t\t" + name + " = " + name +"_new;\n";
      sourceCode += "\t}\n\n";
      }else if(type instanceof JSONObject){///for array item
        JSONObject type_jsonobj = field.getJSONObject("type");
        String arrayType = type_jsonobj.getString("e");
        sourceCode += "\tpublic " + "void" + " set" + name.substring(0,1).toUpperCase() + name.substring(1) + "(int " + name + "_num, " + arrayType + " " + name + "_new)" + "{\n";
      sourceCode += "\t\t" + name + ".set(" + name + "_num, " + name + "_new);\n";
      sourceCode += "\t}\n\n";
      }
     }
    return sourceCode;
   }

  public String numX(String sourceCode, JSONArray fields){
     for (int i = 0; i < fields.length(); i++) {///generate numX method
       JSONObject field = fields.getJSONObject(i);
      Object type = field.get("type");
      String name = field.getString("name");
      if(type instanceof JSONObject){///for array item only
        sourceCode += "\tpublic int num" + name.substring(0, 1).toUpperCase() + name.substring(1) + "(){\n";
        sourceCode += "\t\treturn " + name + ".size();\n";
        sourceCode += "\t}\n\n";
      }
     }
     return sourceCode;
  }

  public String addX(String sourceCode, JSONArray fields) {
     for (int i = 0; i < fields.length(); i++) {///generate addX method
      JSONObject field = fields.getJSONObject(i);
      Object type = field.get("type");
      String name = field.getString("name");
      if(type instanceof JSONObject){///for array item only
        JSONObject type_jsonobj = field.getJSONObject("type");
        String arrayType = type_jsonobj.getString("e");
        sourceCode += "\tpublic void add" + name.substring(0, 1).toUpperCase() + name.substring(1) + "(" + arrayType + " " + name + "_new){\n";
        sourceCode += "\t\t" + name + ".add(" + name + "_new);\n";
        sourceCode += "\t}\n\n";
      }
     }
     return sourceCode;
  }

  public String classConstructor(String sourceCode, JSONArray fields, String className){
    sourceCode += "\tpublic " + className + "(){\n";
    for (int i = 0; i < fields.length(); i++) {///generate a default constructor
      JSONObject field = fields.getJSONObject(i);
      Object type = field.get("type");
      String name = field.getString("name");
      if(type instanceof JSONObject){///array item should be initialized
        JSONObject type_jsonobj = field.getJSONObject("type");
        String arrayType = type_jsonobj.getString("e");
        String arrayListType = getPrimitive(arrayType);
        sourceCode += "\t\t" + name + " = new ArrayList<" + arrayListType + ">();\n";
      }
    }
    sourceCode += "\t}\n\n";
    return sourceCode;
  }
  
  public String toJSONid(String sourceCode, JSONArray fields, String className) {///keep a hash map to update unique id, make recursive calls to it, to serialize
    sourceCode += "\tpublic JSONObject toJSONid(HashMap<Object, Integer> hMap) throws JSONException{\n";
    sourceCode += "\t\tJSONObject serial = new JSONObject();\n";
    sourceCode += "\t\tif(hMap.containsKey(this)){\n";
    sourceCode += "\t\t\tint key = hMap.get(this);\n";
    sourceCode += "\t\t\tserial.put(\"ref\", key);\n";
    sourceCode += "\t\t}\n";
    sourceCode += "\t\telse{\n";
	sourceCode += "\t\t\tint sz = hMap.size();\n";
    sourceCode += "\t\t\thMap.put(this, sz+1);\n";
    sourceCode += "\t\t\tserial.put(\"type\", \"" + className + "\");\n";
    sourceCode += "\t\t\tserial.put(\"id\", hMap.size());\n";
    sourceCode += "\t\t\tJSONArray values = new JSONArray();\n";
    Collection<String> classNames= getClassNames();
    for (int i = 0; i < fields.length(); i++) {///loop each class
      JSONObject field = fields.getJSONObject(i);
      Object type = field.get("type");
      String name = field.getString("name");
      if(type instanceof String){///single object
        if(classNames.contains(type)){///recursive call for objects
         
          sourceCode += "\t\t\tJSONObject " + name + "_child = " + name + ".toJSONid(hMap);\n";
          sourceCode += "\t\t\tJSONObject " + name + "_parent = new JSONObject();\n";
          sourceCode += "\t\t\t" + name + "_parent.put(\"" + name + "\", " + name + "_child);\n";
          sourceCode += "\t\t\tvalues.put(" + name + "_parent);\n";
        }
        else{///for primitives
          sourceCode += "\t\t\tJSONObject " + name + "_parent = new JSONObject();\n";
          sourceCode += "\t\t\t" + name + "_parent.put(\"" + name + "\", " + name + ");\n";
          sourceCode += "\t\t\tvalues.put(" + name + "_parent);\n";
        }
      }
      else if (type instanceof JSONObject) {///for arrays
        JSONObject arrayType = field.getJSONObject("type");
        String arraytype = arrayType.getString("e");
        if(classNames.contains(arraytype)){///for objects
          sourceCode += "\t\t\tJSONObject " + name + "_parent = new JSONObject();\n";
          sourceCode += "\t\t\tJSONArray " + name + "_array = new JSONArray();\n";
          sourceCode += "\t\t\tJSONObject " + name + "_child = new JSONObject();\n";
          sourceCode += "\t\t\t" + arraytype + " " + name + "_ele;\n";
          ///sourceCode += "\t\t\tIterator " + name + "_iter = " + name + ".iterator();\n";
          sourceCode += "\t\t\tfor(int " + name + "_i = 0;" + name + "_i < " + name + ".size();"+name+"_i++){\n";
          sourceCode += "\t\t\t\t" + name + "_ele = " + name + ".get("+name+"_i);\n";
          sourceCode += "\t\t\t\t" + name + "_child = " + name + "_ele.toJSONid(hMap);\n";
          sourceCode += "\t\t\t\t" + name + "_array.put(" + name + "_child);\n";
          sourceCode += "\t\t\t}\n";
          sourceCode += "\t\t\t" + name + "_parent.put(\"" + name + "\", " + name + "_array);\n";
          sourceCode += "\t\t\tvalues.put(" + name + "_parent);\n";
        }
        else {///for array of primitives
          sourceCode += "\t\t\tJSONObject " + name + "_parent = new JSONObject();\n";
          sourceCode += "\t\t\tJSONArray " + name + "_array = new JSONArray();\n";
         
          sourceCode += "\t\t\tfor(int " + name + "_i = 0;" + name + "_i < " + name + ".size();"+name+"_i++){\n";
          sourceCode += "\t\t\t\t" + name + "_array.put(" + name + ".get("+name+"_i));\n";
          sourceCode += "\t\t\t}\n";
          sourceCode += "\t\t\t" + name + "_parent.put(\"" + name + "\", " + name + "_array);\n";
          sourceCode += "\t\t\tvalues.put(" + name + "_parent);\n";
        }
        
      }
    }
    sourceCode += "\t\t\tserial.put(\"values\", values);\n";
    sourceCode += "\t\t}\n";
     sourceCode += "\t\treturn serial;\n";
    sourceCode += "\t}\n\n";
    return sourceCode;
  }
  public String toJSON(String sourceCode, JSONArray fields, String className) {///start a map and arrange them, to serialize
    sourceCode += "\tpublic JSONObject toJSON() throws JSONException{\n";
    sourceCode += "\t\treturn toJSONid(new HashMap<Object, Integer>());\n";
    sourceCode += "\t}\n";
    return sourceCode;
  }
  
  public String getSourceCode(String className) {///get a class code for the class
        try{
    String sourceCode;
    JSONObject targetClass = getClass(className);
    JSONArray fields = targetClass.getJSONArray("fields");
    String pkg = getPackage();
    if(pkg != null){///ad package
      sourceCode = "package " + pkg + ";\n\n";
      sourceCode += "import org.json.JSONArray;\nimport org.json.JSONException;\nimport org.json.JSONObject;\nimport java.util.ArrayList;\nimport java.util.HashMap;\nimport java.util.Iterator;\n\n";
    }
    else {
      sourceCode = "import org.json.JSONArray;\nimport org.json.JSONException;\nimport org.json.JSONObject;\nimport java.util.ArrayList;\nimport java.util.HashMap;\nimport java.util.Iterator;\n\n";
    }
    sourceCode += "public class " + className + "{\n\n";
    sourceCode = getField(sourceCode, fields);///add all methods
    sourceCode = getX(sourceCode, fields);
    sourceCode = setX(sourceCode, fields);
    sourceCode = numX(sourceCode, fields);
    sourceCode = addX(sourceCode, fields);
    sourceCode = classConstructor(sourceCode, fields, className);
    sourceCode = toJSONid(sourceCode, fields, className);
    sourceCode = toJSON(sourceCode, fields, className);
    sourceCode += "}\n";
    return sourceCode;
     } catch (JSONException json_e) {
          json_e.printStackTrace();
          throw json_e;
    }
  }

  public String realDeserial(String deserial) {///deserialize by keeping another map
    Collection<String> classNames = getClassNames();
    Iterator<String> iter = classNames.iterator();
    while(iter.hasNext()){
      String className = iter.next();
    JSONObject targetClass = getClass(className);
    JSONArray fields = targetClass.getJSONArray("fields");
    deserial += "\tpublic static "+className+" real_read"+className+"(JSONObject js, HashMap<Integer, Object> hmap) throws JSONException{\n";
    deserial += "\t\tint id = js.getInt(\"id\");\n";
   
    deserial += "\t\t"+className+" x = new " + className + "();\n";
    deserial += "\t\thmap.put(id, x);\n";
    deserial += "\t\tJSONArray all_fs = js.getJSONArray(\"values\");\n";
    for (int k = 0; k < fields.length();k++){
      JSONObject field = fields.getJSONObject(k);
      String fieldName = field.getString("name");
      Object fieldType = field.get("type");
      deserial += "\t\tfor(int " + fieldName + "_j = 0;" + fieldName + "_j < all_fs.length();"+fieldName+"_j++){\n";
      deserial += "\t\t\tJSONObject p_f = all_fs.getJSONObject("+fieldName+"_j);\n";
      deserial += "\t\t\tif(p_f.has(\""+fieldName+"\")){\n";
      if(fieldType instanceof JSONObject){///find array type or ref type
        JSONObject fieldtype = field.getJSONObject("type");
        String fieldtyp = fieldtype.getString("e");
        String fieldty = getPrimitive(fieldtyp);
         deserial += "\t\t\t\tJSONArray "+fieldName+"_ar = p_f.getJSONArray(\""+fieldName+"\");\n";
          deserial += "\t\t\t\tfor(int " + fieldName + "_l = 0; " + fieldName + "_l < " + fieldName + "_ar.length();" + fieldName + "_l++){\n";
          if(classNames.contains(fieldty)){///array of objects
          deserial += "\t\t\t\t\tJSONObject " + fieldName + "_f = " + fieldName + "_ar.getJSONObject("+fieldName+"_l);\n";
          deserial += "\t\t\t\t\tif("+fieldName+"_f.has(\"ref\")){\n";
          deserial += "\t\t\t\t\t\t" + fieldtyp + " y = (" + fieldtyp + ")hmap.get(\"ref\");\n";
          deserial += "\t\t\t\t\t\tx.add" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1)+"(y);\n";
          deserial += "\t\t\t\t\t}\n";
          deserial += "\t\t\t\t\telse{\n";
          deserial += "\t\t\t\t\t\t"+fieldtyp+" z = real_read"+fieldtyp.substring(0,1).toUpperCase()+fieldtyp.substring(1)+"("+fieldName+"_f, hmap);\n";
          deserial += "\t\t\t\t\t\tx.add"  + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1) + "(z);\n";
          deserial += "\t\t\t\t\t}\n";
        }
          else{///array of primitives
         
          deserial += "\t\t\t\t" + fieldtyp + " "+fieldName+"_e = ("+fieldtyp+")"+fieldName+"_ar.get("+fieldName+"_l);\n";
          deserial += "\t\t\t\tx.add" + fieldName.substring(0,1).toUpperCase()+ fieldName.substring(1) + "(" +fieldName+"_e);\n";
        }
        deserial += "\t\t\t\t}\n";
      }
      else {///for signle items
        String fieldt = field.getString("type");
        if(classNames.contains(fieldt)){
          deserial += "\t\t\t\tJSONObject " + fieldName + "_c = p_f.getJSONObject(\""+fieldName+"\");\n";
          deserial += "\t\t\t\tif("+fieldName+"_c.has(\"ref\")){\n";
          deserial += "\t\t\t\t\tint ref = " + fieldName + "_c.getInt(\"ref\");\n";
          deserial += "\t\t\t\t\t"+fieldt+" "+fieldName+"_obj = ("+fieldt+")hmap.get(ref);\n";
          deserial += "\t\t\t\t\tx.set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1) + "("
              + fieldName + "_obj);\n";
          deserial += "\t\t\t\t}\n";
          deserial += "\t\t\t\telse{\n";
          deserial += "\t\t\t\t\t" + fieldt + " " + fieldName + "_child = real_read"+fieldt+"("+fieldName+"_c, hmap);\n";
          deserial += "\t\t\t\t\tx.set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1) + "("
              + fieldName + "_child);\n";
          deserial += "\t\t\t\t}\n";
        }
        else {///for single object
          if(fieldt.equals("byte") || fieldt.equals("char") || fieldt.equals("short")){
             deserial += "\t\t\t\t\t" + fieldt + " " + fieldName + "_pri = ("+fieldt+")(int)p_f.get(\"" + fieldName + "\");\n";
          deserial += "\t\t\t\t\tx.set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1) + "("+fieldName+"_pri);\n";
          }
          else {///for single primitives
            deserial += "\t\t\t\t\t" + fieldt + " " + fieldName + "_pri = ("+fieldt+")p_f.get(\"" + fieldName + "\");\n";
          deserial += "\t\t\t\t\tx.set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1) + "("+fieldName+"_pri);\n";
          }
         
        }
      }
      deserial += "\t\t\t}\n";
      deserial += "\t\t}\n";
    }
    deserial += "\treturn x;\n";
    deserial += "\t}\n\n";
    }
    return deserial;
  }
  public String deserializer() {///start code for deserialize
    String deserial = null;
    String pkg = getPackage();
    if(pkg != null){///add package
      deserial = "package " + pkg + ";\n\n";
      deserial += "import org.json.JSONArray;\nimport org.json.JSONException;\nimport org.json.JSONObject;\nimport java.util.ArrayList;\nimport java.util.HashMap;\nimport java.util.Iterator;\n\n";
    }
    else {
      deserial = "import org.json.JSONArray;\nimport org.json.JSONException;\nimport org.json.JSONObject;\nimport java.util.ArrayList;\nimport java.util.HashMap;\nimport java.util.Iterator;\n\n";
    }
    deserial += "public class Deserializer{\n";
    deserial = realDeserial(deserial);
    Collection<String> classNames = getClassNames();
    Iterator<String> iter = classNames.iterator();
    while(iter.hasNext()){
      String className = iter.next();
      deserial += "\tpublic static "+className+" read"+className.substring(0, 1).toUpperCase()+className.substring(1)+"(JSONObject js) throws JSONException{\n";
      deserial += "\t\t" + className + " "+className+"_de = real_read"+className+"(js, new HashMap<Integer, Object>());\n";
      deserial += "\t\treturn " + className + "_de;\n";
      deserial += "}\n\n";
    }
    deserial += "}\n";
    return deserial;
  }
  
  public void mkDir(String path, String basePath) {///make directories for package
    File file = new File(basePath + path);
    file.mkdirs();
  }
  public void createAllClasses(String basePath) {///create all classes from class builder
    try{
      String pkg = getPackage();
      String path = null;
      if (pkg != null) {
       
        path = pkg.replace(".", "/");
      }
    Collection<String> classNames = getClassNames();
    Iterator<String> iterator = classNames.iterator();
    while(iterator.hasNext()){///output them to separate files
      String name = iterator.next();
      String sourceCode = getSourceCode(name);
      FileOutputStream output;
      if (path == null) {
        output = new FileOutputStream(basePath + name + ".java");
      }
      else {
        mkDir(path, basePath);
        System.out.println(basePath + path + "/" +name + ".java");
        output = new FileOutputStream(basePath + path + "/" +name + ".java");
      }
      byte[] stb = sourceCode.getBytes();
      output.write(stb);
      output.close();
    }
    String deserial = deserializer();///output deserializer
    FileOutputStream output_de;
      if (path == null) {
        output_de = new FileOutputStream(basePath + "Deserializer.java");
      }
      else {
        mkDir(path, basePath);
        System.out.println(basePath + path + "/Deserializer.java");
        output_de = new FileOutputStream(basePath + path + "/Deserializer.java");
      }
      byte[] stb_de = deserial.getBytes();
      output_de.write(stb_de);
      output_de.close();
  } catch (IOException io_e) {
      System.err.println("IOException: " + io_e.toString());
      io_e.printStackTrace();
    }
  }
    
   
   
}
