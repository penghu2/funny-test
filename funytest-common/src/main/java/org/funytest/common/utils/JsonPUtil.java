package org.funytest.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * JsonP工具，解析蘑菇街通用jsonP对象
 * @author xiuzhu
 *
 */
public class JsonPUtil {
	
	/**
	 * 
	 */
	private static Pattern jsonPPattern = Pattern.compile("^(.+)\\((.+)\\)");
	
	public static String praseToJson(String moguJsonPString) {
		
		Matcher match = jsonPPattern.matcher(moguJsonPString);    
		
		if (match.find()) {
			return match.group(2);
		}
		
		return null;
	}
	
	public static JSONObject praseToJsonObject(String moguJsonPString) {
		return JSON.parseObject(praseToJson(moguJsonPString));
	}
	
	public static HashMap<?, ?> praseToMap(String moguJsonPString) {
		String str = praseToJson(moguJsonPString);
		return JSON.parseObject(str, HashMap.class);
	}
	
	public static void main(String[] args) {
		String str = "openFanGJ({\"data\":{\"leftFlopNumber\":1},\"returnCode\":\"SUCCESS\",\"success\":true})";
		praseToJson(str);
		
		HashMap<?, ?> map = praseToMap(str);
		Map<?, ?> data = (Map<?, ?>) map.get("data");
		int i = (int)data.get("leftFlopNumber");
		System.out.println(i);
		
	}
}
