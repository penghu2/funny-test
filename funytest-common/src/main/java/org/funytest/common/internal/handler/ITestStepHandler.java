package org.funytest.common.internal.handler;

import org.funytest.common.model.teststep.ITestStep;

public interface ITestStepHandler {
	
	public void handle(ITestStep step) throws Exception;
	
}
