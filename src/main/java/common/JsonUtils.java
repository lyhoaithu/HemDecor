package common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtils {
public static String getValueByKey(String jsonString, String keyword) throws ParseException {
	JSONParser parser = new JSONParser();
	JSONObject jsonObj=(JSONObject) parser.parse(jsonString);
	String value= jsonObj.get(keyword).toString();
return value;
}

public static JSONArray readJsonArrayFile(String filePath) throws IOException, ParseException {
	File jsonFile = new File(filePath);
	FileReader reader= new FileReader(jsonFile);
	JSONParser parser= new JSONParser();
	JSONObject jsonObj= (JSONObject) parser.parse(reader);
	JSONArray array=(JSONArray)jsonObj.get("users");
	
	return array;
}

public static String getValueFromArray(JSONArray jsonArr, int index, String keyword) {
	JSONObject valueObj= (JSONObject) jsonArr.get(index);
	String valueObjString= valueObj.toJSONString();
return valueObjString;	
}


}

