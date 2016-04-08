package org.funytest.common.model.teststep;

import org.funytest.common.internal.handler.ITestStepHandler;
import org.funytest.common.model.HttpTestInfo;
import org.funytest.common.utils.Constant;

public class HttpTestStep implements ITestStep {

	private HttpTestInfo httpTestInfo;
	
	@Override
	public String getType() {
		
		return Constant.STEP_TYPE_HTTP;
	}

	@Override
	public void execute(ITestStepHandler handler) throws Exception {
		
		handler.handle(this);
	}

	public HttpTestInfo getHttpTestInfo() {
		return httpTestInfo;
	}

	public void setHttpTestInfo(HttpTestInfo httpTestInfo) {
		this.httpTestInfo = httpTestInfo;
	}

	
}
