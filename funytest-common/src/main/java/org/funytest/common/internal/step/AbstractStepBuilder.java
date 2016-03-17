package org.funytest.common.internal.step;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;
import org.funytest.common.exception.TestStepException;
import org.funytest.common.internal.IConfiguration;
import org.funytest.common.model.Table;
import org.funytest.common.model.teststep.ITestStep;

public abstract class AbstractStepBuilder implements ITestStepBuilder{
	
	@Override
	public ITestStep buildStep(String name, Element element, IConfiguration config)
	{
		ITestStep step=null;
		try {
			step = this.buildStep(element, config);
		} catch (TestStepException e) {
			
			e.printStackTrace();
		}
		return step;
	}
	
	abstract ITestStep buildStep(Element element, IConfiguration config) throws TestStepException;
	
	/**
	 * 初始化table
	 * @param element  对应xml中 "table"的结点
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
			String value = item.getTextTrim();
			
			/* 添加标识位，如果表已经存在，则不需要添加，不存在就添加 */
			if (StringUtils.isNotBlank(flag) && doUpdateFlag){
				table.addFlag(colName, flag);
			}
			
			data.put(colName, value);
		}
		
		//添加数据咯
		table.addData(data);
	}
}
