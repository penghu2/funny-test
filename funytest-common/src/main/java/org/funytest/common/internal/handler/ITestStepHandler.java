package org.funytest.common.internal.handler;

import org.funytest.common.exception.TestStepException;
import org.funytest.common.model.Table;
import org.funytest.common.model.TestExecuteMethod;

public interface ITestStepHandler {

	public void handle(Table table) throws TestStepException;
	
	public void handle(TestExecuteMethod method) throws TestStepException;
}
