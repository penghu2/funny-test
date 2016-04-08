package org.funytest.common.model.teststep;

import java.util.LinkedList;
import java.util.List;

import org.funytest.common.internal.handler.ITestStepHandler;
import org.funytest.common.model.Table;
import org.funytest.common.utils.Constant;

/**
 * 初始化类
 * @author hupeng
 *
 */
public class InitTestStep implements ITestStep {
	
	private List<Table> tables;
	
	public String getType() {
		
		return Constant.STEP_TYPE_INIT;
	}

	public void execute(ITestStepHandler handler) throws Exception {
		handler.handle(this);
	}
	
	public void addTable(Table tb) {
		if (this.tables == null) {
			tables = new LinkedList<Table>();
		}
		
		tables.add(tb);
	}
	
	public boolean dohave(String tableName){
		if (tables==null) return false;
		
		for (Table tb : tables) {
			if (tableName.equals(tb.getName())) {
				return true;
			}
		}
		
		return false;
	}
	
	public Table getTableByName(String name){
		
		for (Table tb : tables) {
			if (name.equals(tb.getName())) {
				return tb;
			}
		}
		
		return null;
	}
	
	public List<Table> getTables(){
		return this.tables;
	}
	
	public void setTables(List<Table> tables){
		this.tables = tables;
	}

}
