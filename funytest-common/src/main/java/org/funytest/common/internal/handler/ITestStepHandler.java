package org.funytest.common.internal.handler;

import org.funytest.common.exception.TestStepException;
import org.funytest.common.model.Table;

public interface ITestStepHandler {

	public void handle(Table table) throws TestStepException;
	
	public void handle() throws Exception;
}
