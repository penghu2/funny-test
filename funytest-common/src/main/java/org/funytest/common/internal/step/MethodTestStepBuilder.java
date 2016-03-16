package org.funytest.common.internal.step;

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
				TestExecuteMethod exeMethod = new TestExecuteMethod();
				
				String className = item.attributeValue("class");
				String methodName = item.attributeValue("method");
				String beanName = item.attributeValue("bean");
				
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
				
				exeMethod.setArgs(getArgs(item.elements()));
				
				try {
					exeMethod.setMethod(ClassHelper.forName(className).getMethod(methodName, parameterTypes));
				} catch (NoSuchMethodException e) {
						
					e.printStackTrace();
				} catch (SecurityException e) {
						
					e.printStackTrace();
				}
				
				methodList.add(exeMethod);
			}
		}
		
		step.setMethods(methodList);
		return step;
	}
	
	/**
	 * 从结点中解析出
	 * @param apiElement
	 * @return
	 */
	private Object[] getArgs(List apiSubElements){
		if (apiSubElements.isEmpty())
			return null;
		
		Object[] params = new Object[apiSubElements.size()];
		int index = 0;
		
		for (Object subElement : apiSubElements){
			String value = ((Element)subElement).getText();
			String type = ((Element)subElement).attributeValue("type");
			
			Object o = ObjectUtil.getObject(type, value);
			params[index]=o;
			
			index++;
		}
		
		return params;
	}

}
