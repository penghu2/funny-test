package org.funytest.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class UrlUtil {
	
	private static Pattern p_url_get_param = Pattern.compile("(\\?\\w+=[^&]*)(&\\w+=.+)*");
	private static Pattern p_url_with_param = Pattern.compile("([a-zA-Z]+://.+)\\?.*");
	
	public static Map<String, String> praseGetUrlParams(String url) {
		Matcher m = p_url_get_param.matcher(url.trim());
		if (m.find() && m.groupCount() == 2) {
			//第一个参数一定是以?号开头的, 所以去掉问号
			String first_param = m.group(1).substring(1);
			String param_str = m.group(2);
			
			Map<String, String> paramMap = new HashMap<String, String>();
			String[] first = first_param.split("=");
			paramMap.put(first[0], first.length==2 ? first[1] : null);
			
			if (StringUtils.isBlank(param_str)) return paramMap;
			String[] params = param_str.split("&");{
				
				for (String param : params ){
					if (StringUtils.isBlank(param)) continue;
					String[] Key_value = param.split("=");
					String key = Key_value[0];
					String value = Key_value.length > 1 ? Key_value[1] : null;
					paramMap.put(key, value);
				}
			}
			return paramMap;
		}
		
		return null;
	}
	
	public static String getAbsoluteUrl(String url) {

		Matcher m = p_url_with_param.matcher(url.trim());
		if (m.find()) {
			return m.group(1);
		}
		return url;
	}
	
	public static void main(String[] args) {
		String url ="http://www.baidu.com?";
		Pattern p = Pattern.compile("([a-zA-Z]+://.+)\\?.*");
		Matcher m = p.matcher(url.trim());
		if (m.find()) {
			System.out.println(m.group(0));
			System.out.println(m.group(1));
			//System.out.println(m.group(2));
			System.out.println(m.groupCount());
		}
	}
	
}
