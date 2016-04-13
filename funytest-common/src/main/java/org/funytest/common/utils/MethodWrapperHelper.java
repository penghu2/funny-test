package org.funytest.common.utils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.funytest.common.internal.method.MethodWrapper;

public class MethodWrapperHelper {
	
	private static Pattern p_func = Pattern.compile("^return (.*)\\((.*)\\)");
	public static MethodParser methodParser = new MethodParser();
	
	public final static String FUNC_KEY = "FUNC_KEY";
	public final static String PARAM_KEY = "PARAM_KEY";
	/**
	 * 判断是否为接口调用
	 * @param text
	 * @return
	 */
	public static boolean isMethodInvoke(String text) {
		Matcher m = p_func.matcher(text.trim());
		
		if (m.find()) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 获取正则匹配的信息
	 * @param text
	 * @param funInfo
	 * @param paramInfo
	 * @return
	 */
	public static boolean getMehtodInfo(String text, Map<String, String> val) {
		Matcher m = p_func.matcher(text.trim());
		
		if (m.find()) {
			val.put(FUNC_KEY,  m.group(1));
			val.put(PARAM_KEY,  m.group(2));
			return true;
		}
		
		return false;
	}
	
	/**
	 * 根据指定的接口信息执行接口调用
	 * @param text
	 * @return
	 */
	public static MethodWrapper getMethodWrapper(String func_info, String[] params_infos) {
		MethodWrapper methodW = methodParser.parse(func_info, params_infos);
		
		return methodW;
	}
	
	public static MethodWrapper getMethodWrapper(String text) {
		Matcher m = p_func.matcher(text.trim());
		
		if (m.find()) {
			String func_info = m.group(1);
			String params_info = m.group(2);
			String[] params_infos = null;
			if (!StringUtils.isBlank(params_info)) {
				params_infos = params_info.split(",");
			}
			 
			MethodWrapper methodW = MethodWrapperHelper.getMethodWrapper(func_info, params_infos);
			return methodW;
		}
		
		return null;
	}
}
