package org.funytest.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.testng.internal.ClassHelper;

public class ObjectUtil {

	public static Object getObject(String type, String value){
		
		if (StringUtils.isBlank(type)) return value;
		
		switch (type){
		case "byte":
			return (byte)Byte.parseByte(value);
			
		case "Byte":
			return Byte.parseByte(value);
		
		case "short":
			return (short)Short.parseShort(value);
		
		case "Short":
			return Short.parseShort(value);
		
		case "int":
			return (int)Integer.parseInt(value);
		
		case "Integer":
			return Integer.parseInt(value);
		
		case "float":
			return (float)Float.parseFloat(value);
			
		case "Float":
			return Float.parseFloat(value);
			
		case "double":
			return (double)Double.parseDouble(value);
			
		case "Double":
			return Double.parseDouble(value);
		
		case "long":
			return (long)Long.parseLong(value);
		
		case "Long":
			return Long.parseLong(value);
		}
		
		Class cls = ClassHelper.forName(type);
		
		/* class not fund */
		if (cls==null){
			return null;
		}
		
		return null;
	}
}
