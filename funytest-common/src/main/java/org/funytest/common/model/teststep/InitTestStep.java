package org.funytest.common.model.teststep;

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

}
