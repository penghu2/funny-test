package org.funytest.common.model.teststep;

import java.util.Iterator;

import org.dom4j.Element;
import org.funytest.common.internal.IConfiguration;
import org.funytest.common.model.Table;

public class TestStepFactory {


	/**
	 * 根据节点内容，提炼出对应的ITestStep，采用策略模式
	 * @param name  名称
	 * @param element 对应上述节点
	 * @return
	 */
	public ITestStep buildStep(String name, Element element, IConfiguration config){
		
		ITestStep.Type type = ITestStep.Type.getTypeByCode(name);
		
		switch(type){
		case INIT: //初始化
			/* 构造初始化函数 */
			return buildInitTestStep(element, config);
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
	
	protected InitTestStep buildInitTestStep(Element element, IConfiguration config){
		
		InitTestStep step = new InitTestStep();
		/* 遍历子节点 */
		for (Iterator<?> it=element.elementIterator();it.hasNext();){
			
			Element item = (Element) it.next();
			if (item.getName().equals("table")){
				//构造table
				Table table = new Table();
				String name = item.attributeValue("name");
				table.setName(name);
				table.setDatasource(config.getDataSource(name));
				table.setType(Table.Type.insert);
			}
		}
		
		return null;
	}
}
