package org.funytest.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.testng.internal.ClassHelper;

public class ObjectUtil {

	public static Object getObject(String type, String value) throws ParseException{
		
		if (StringUtils.isBlank(type)) return value;
		
		switch (type){
		case "String":
			return value;
		
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
		
		case "java.util.Date":
			return praseUtilDate(value);
		
		case "java.sql.Date":
			return praseSqlDate(value);
		}
		
		return null;
	}
	
	public static java.util.Date praseUtilDate(String dateValue) throws ParseException{
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		
		return format.parse(dateValue);
	}
	
	public static java.sql.Date praseSqlDate(String dateValue) throws ParseException {
		java.util.Date date = new SimpleDateFormat("yyyyMMdd").parse(dateValue);
		
		return new java.sql.Date(date.getTime());
	}
}
