package org.funytest.common.internal.step;
import java.util.Iterator;

import org.dom4j.Element;
import org.funytest.common.internal.IConfiguration;
import org.funytest.common.model.Table;
import org.funytest.common.model.teststep.ITestStep;
import org.funytest.common.model.teststep.InitTestStep;

/**
 * 带来的问题
 * @author hupeng
 *
 */
public class InitTestStepBuilder extends AbstractStepBuilder {
	
	protected void initStep(Element element, IConfiguration config, InitTestStep step, Table.Type type){
		
		if (step == null) return;
		
		/* 遍历子节点 */
		for (Iterator<?> it=element.elementIterator();it.hasNext();){
			
			Element item = (Element) it.next();
			if (item.getName().equals("table")){
				//构造table
				Table table;
				String name = item.attributeValue("name");
				String group = item.attributeValue("group");
				String[] groups = group == null ? null : group.split(",");
				
				/* 判断是否已经存在这张表，如果存在，就不在更新flag，直接取表出来进行 */
				boolean doUpdateFlag = false;
				
				if (step.dohave(name)) {
					table = step.getTableByName(name);
					
				} else {
					table = new Table();
					
					table.setName(name);
					table.setDatasource(config.getDataSource(name, groups));
					table.setType(type);
					table.setGroups(groups);		
					doUpdateFlag = true;
				}
					
				/* 解析table，这个过程会比较痛苦 */
				addTableData(item, table, doUpdateFlag);
				
				/* 如果不存在，则添加表 */
				if (doUpdateFlag){
					step.addTable(table);
				}
			}
		}
		
	}

	/**
	 * @param element 对应xml中的节点 "test-init"
	 */
	@Override
	public ITestStep buildStep(Element element, IConfiguration config) {
		
		InitTestStep step = new InitTestStep();
		initStep(element, config, step, Table.Type.insert);
		return step;
	}
}
