package org.funytest.common.model.teststep;

import java.util.LinkedList;
import java.util.List;

import org.funytest.common.exception.TestStepException;
import org.funytest.common.internal.handler.ITestStepHandler;
import org.funytest.common.model.Table;

/**
 * 初始化类
 * @author hupeng
 *
 */
public class InitTestStep implements ITestStep {
	
	private List<Table> tables;
	
	public Type getType() {
		
		return Type.INIT;
	}

	public void execute(ITestStepHandler handler) throws TestStepException {
		
		/**
		 * 执行表格操作
		 */
		for (Table tb : tables){
			handler.handle(tb);
		}
	}
	
	public void addTable(Table tb) {
		if (this.tables == null) {
			tables = new LinkedList<Table>();
		}
		
		tables.add(tb);
	}
	
	public boolean dohave(String tableName){
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
