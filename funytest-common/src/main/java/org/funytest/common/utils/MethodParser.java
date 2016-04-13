package org.funytest.common.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.ParseException;

import org.funytest.common.internal.TestContextHolder;
import org.funytest.common.internal.method.MethodWrapper;
import org.testng.internal.ClassHelper;

/**
 * method 解析器
 * 
 * @author xiuzhu
 */
public class MethodParser {

	/**
	 * 解析method配置信息
	 * 
	 * @param func_info
	 *            函数信息
	 * @param params_info
	 *            参数信息
	 * @return
	 */
	public MethodWrapper parse(String func_info, String[] params_infos) {

		// 以点分隔
		String func_infos[] = func_info.split("\\.");

		//TO_DO 暂时用if..else.. 后续需要扩展成策略模式，或者工厂模式
		/* this 调用, 调用本地方法 */
		if (func_infos.length == 2 && func_infos[0].equals("this")) {
			// 从TestContext上下文中获取bean
			Object bean = TestContextHolder.get().getTestInstance();
			String methodName = func_infos[1];
			Method m = ObjectUtil.getMethod(bean.getClass(), methodName);
			if (m==null) return null;
			
			Class<?>[] parameterTypes_actual = m.getParameterTypes();
			Object[] args = getParamObjects(parameterTypes_actual, params_infos);

			MethodWrapper methodWrapper = new MethodWrapper();
			methodWrapper.setArgs(args);
			methodWrapper.setCls(bean.getClass());
			methodWrapper.setInstance(bean);
			methodWrapper.setMethod(m);

			return methodWrapper;
		}
		else {
			return praseWithClassHelper(func_info, params_infos);
		}
	
	}
	
	private MethodWrapper praseWithClassHelper(String func_info, String[] params_infos) {
		
		int last_point_index = func_info.lastIndexOf(".");
		String className = func_info.substring(0, last_point_index);
		String methodName = func_info.substring(last_point_index+1, func_info.length());
		MethodWrapper methodWrapper = new MethodWrapper();
		
		methodWrapper.setCls(ClassHelper.forName(className));
		Method m = ObjectUtil.getMethod(methodWrapper.getCls(), methodName);
		Class<?>[] parameterTypes_actual = m.getParameterTypes();
		Object[] args = getParamObjects(parameterTypes_actual, params_infos);
		methodWrapper.setArgs(args);
		methodWrapper.setMethod(m);
		
		//判断是否为静态函数, 非静态函数需要实例化
		if (!Modifier.isStatic(m.getModifiers())){
			methodWrapper.setInstance(ClassHelper.newInstance(methodWrapper.getCls()));
		}


		return methodWrapper;
	}

	/**
	 * 获取参数
	 * 
	 * @return
	 */
	public static Object[] getParamObjects(Class<?>[] parameterTypes, String params_infos[]) {
		if (params_infos == null || parameterTypes ==null) return new Object[0];
		
		int len = parameterTypes.length;
		Object[] params = new Object[len];
		for (int i = 0; i < len; i++) {
			try {
				Object o = ObjectUtil.getObject(parameterTypes[i].getSimpleName(),
						params_infos[i].trim());
				params[i] = o;
			} catch (ParseException e) {
				params[i] = null;
			}
		}

		return params;
	}
}
