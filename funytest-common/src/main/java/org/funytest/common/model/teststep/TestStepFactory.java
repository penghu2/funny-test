package org.funytest.common.model.teststep;

import org.dom4j.Element;

public class TestStepFactory {


	/**
	 * 根据节点内容，提炼出对应的ITestStep，采用策略模式
	 * @param name  名称
	 * @param element 对应上述节点
	 * @return
	 */
	public ITestStep buildStep(String name, Element element){
		
		ITestStep.Type type = ITestStep.Type.getTypeByCode(name);
		
		switch(type){
		case INIT: //初始化
			return null;
		case METHODEXE: //方法执行
			return null;
		case EXPECT: //期望比较
			return null;
		case CLEAN: //清理动作
			return null;
		case Other: //其它自定义
			return null;
		default:
			return null;
		}
	}
}
