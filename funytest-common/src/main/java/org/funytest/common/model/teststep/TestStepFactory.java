package org.funytest.common.model.teststep;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
	
	/**
	 * 构造测试步骤  - 初始化
	 * @param element
	 * @param config
	 * @return
	 */
	protected InitTestStep buildInitTestStep(Element element, IConfiguration config){
		
		InitTestStep step = new InitTestStep();
		/* 遍历子节点 */
		for (Iterator<?> it=element.elementIterator();it.hasNext();){
			
			Element item = (Element) it.next();
			if (item.getName().equals("table")){
				//构造table
				Table table;
				String name = item.attributeValue("name");
				
				/* 判断是否已经存在这张表，如果存在，就不在更新flag，直接取表出来进行 */
				boolean doUpdateFlag = false;
				
				if (step.dohave(name)) {
					table = step.getTableByName(name);
					
				} else {
					table = new Table();
					
					table.setName(name);
					table.setDatasource(config.getDataSource(name));
					table.setType(Table.Type.insert);
					
					doUpdateFlag = true;
				}
					
				/* 解析table，这个过程会比较痛苦 */
				addTableData(item, table, doUpdateFlag);
				
				step.addTable(table);
			}
		}
		
		return null;
	}
	
	/**
	 * 初始化table
	 * @param element  name 为table
	 * @param table  table对象
	 */
	public void addTableData(Element tableElement, Table table, boolean doUpdateFlag){
		
		Map<String, String> data = new HashMap<String, String>();
		
		/* 遍历子节点 */
		for (Iterator<?> it=tableElement.elementIterator();it.hasNext();){
			
			Element item = (Element) it.next();
			//表列名
			String colName = item.getName();
			String flag = item.attributeValue("F");
			String value = item.getStringValue();
			
			if (StringUtils.isNotBlank(flag) && doUpdateFlag){
				table.addFlag(colName, flag);
			}
			
			data.put(colName, value);
		}
		
		//添加数据咯
		table.addData(data);
	}
}
