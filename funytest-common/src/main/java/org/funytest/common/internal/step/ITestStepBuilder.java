package org.funytest.common.internal.step;

import org.dom4j.Element;
import org.funytest.common.internal.IConfiguration;
import org.funytest.common.model.teststep.ITestStep;
import org.funytest.common.model.teststep.InitTestStep;

/**
 * ITestStep工厂类
 * @author hupeng
 *
 */
public interface ITestStepBuilder {
	
	/**
	 * 工厂方法 
	 * @param element 对应xml中的结点
	 * @param config  test config 配置 
	 * @return
	 */
	public ITestStep buildStep(String name, Element element, IConfiguration config);

}
