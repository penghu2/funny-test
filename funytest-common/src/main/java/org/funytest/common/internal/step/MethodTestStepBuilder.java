package org.funytest.common.internal.step;

import java.text.ParseException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;
import org.funytest.common.exception.TestStepException;
import org.funytest.common.internal.IConfiguration;
import org.funytest.common.model.TestExecuteMethod;
import org.funytest.common.model.teststep.ITestStep;
import org.funytest.common.model.teststep.MethodTestStep;
import org.funytest.common.utils.ObjectUtil;
import org.testng.internal.ClassHelper;

import com.alibaba.fastjson.JSON;

public class MethodTestStepBuilder extends AbstractStepBuilder {
	
	/**
	 * @param element 节点名称为"test-method-execute"
	 * @throws TestStepException 
	 */
	@Override
	ITestStep buildStep(Element element, IConfiguration config) throws TestStepException {
		MethodTestStep step = new MethodTestStep();
		
		List<TestExecuteMethod> methodList = new LinkedList<TestExecuteMethod>();
		
		/* 遍历子节点 */
		for (Iterator<?> it=element.elementIterator();it.hasNext();){
			Element item = (Element) it.next();
			if (item.getName().equals("api")){
				
				TestExecuteMethod m = buildExeMethod(item, config);
				
				if (m!=null) {
					/* 解析api子节点 */
					methodList.add(m);
				}
			}
		}
		
		step.setMethods(methodList);
		return step;
	}
	
	public TestExecuteMethod buildExeMethod(Element apiElement, IConfiguration config) throws TestStepException{
		TestExecuteMethod exeMethod = new TestExecuteMethod();
		
		String className = apiElement.attributeValue("class");
		String methodName = apiElement.attributeValue("method");
		String beanName = apiElement.attributeValue("bean");
		
		if (StringUtils.isBlank(className) || StringUtils.isBlank(methodName)) {
			
			throw new TestStepException("Api结点的className/methodName为空"); 
		}
		
		Class[] parameterTypes = null;
		
		/* 
		 * 如果beanName 不为空，则意味着方法为非静态方法，如果为空，则意味着方法为static方法，反射执行
		 * 的时候不需要获取对象的实例
		 *  */
		if (StringUtils.isNotBlank(beanName)){
			//从config中获取相应的bean信息
			exeMethod.setInstance(config.getBeanByName(beanName));
		} 
		
		/* 设置Class */
		exeMethod.setCls(ClassHelper.forName(className));
		
		/* 解析参数 */
		Object args[] = getArgs(apiElement.elements("arg"));
		exeMethod.setArgs(args);
		
		/* 获取参数类型 */
		if (args.length>0){
			parameterTypes = new Class[args.length];
			for (int i=0; i<args.length; i++){
				parameterTypes[i] = args[i].getClass();
			}
		}
		
		try {
			exeMethod.setMethod(ClassHelper.forName(className).getMethod(methodName, parameterTypes));
		} catch (NoSuchMethodException e) {
				
			e.printStackTrace();
		} catch (SecurityException e) {
				
			e.printStackTrace();
		}
		
		/* 解析期望结果 */
		Object expectObj = getResultExpect(apiElement.element("result-expect"));
		
		exeMethod.setExpectObj(expectObj);
		
		return exeMethod;
	}
	
	/**
	 * 获取期望结果
	 * @param resultExpectElement
	 * @return
	 */
	public Object getResultExpect(Element resultExpectElement) {
		String className = resultExpectElement.attributeValue("type");
		String prase = resultExpectElement.attributeValue("prase");
		String value = resultExpectElement.getText().trim();
		
		Object o=null;
		try {
			o = ObjectUtil.getObject(className, value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if (o!=null) return o;
		
		Class<?> cls = ClassHelper.forName(className);
		if ("json".equals(prase) && cls != null) return JSON.parseObject(value, cls);
		
		return null;
	}
	
	/**
	 * 从结点中解析出 对应的接口参数，复杂对象直接使用fastjson反序列化
	 * @param apiElement
	 * @return
	 */
	public Object[] getArgs(List apiSubElements){
		if (apiSubElements.isEmpty())
			return null;
		
		Object[] params = new Object[apiSubElements.size()];
		
		int index = 0;
		
		for (Object subElement : apiSubElements){
			String value = ((Element)subElement).getText().trim();
			String type = ((Element)subElement).attributeValue("type");
			String praseType = ((Element)subElement).attributeValue("prase");
			
			Object o=null;
			try {
				o = ObjectUtil.getObject(type, value);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			if (StringUtils.isNoneBlank(praseType)){
				if ("json".equals(praseType)){
					Class<?> cls = ClassHelper.forName(type);
					o = JSON.parseObject(value, cls);
				}
			}
			
			params[index]=o;
			index++;
		}
		
		return params;
	}

}
