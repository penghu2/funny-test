package org.funytest.common.internal.step;

import org.dom4j.Element;
import org.funytest.common.internal.IConfiguration;
import org.funytest.common.model.Table;
import org.funytest.common.model.teststep.CheckTableTestStep;
import org.funytest.common.model.teststep.ITestStep;

public class CheckTableStepBuilder extends InitTestStepBuilder {

	@Override
	public ITestStep buildStep(Element element, IConfiguration config) {
		
		CheckTableTestStep step = new CheckTableTestStep();
		initStep(element, config, step, Table.Type.compare);
		return step;
	}
}
