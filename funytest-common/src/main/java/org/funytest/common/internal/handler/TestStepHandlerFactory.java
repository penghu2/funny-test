package org.funytest.common.internal.handler;

import java.util.HashMap;
import java.util.Map;

import org.funytest.common.utils.Constant;

public class TestStepHandlerFactory implements ITestStepHandlerFactory {

	/**
	 * handler Map， 从这里面获取接口配置信息
	 */
	public static Map<String, ITestStepHandler> handlerConfigMap;
	
	static {
		handlerConfigMap = new HashMap<>();
		
		/* test-init&init */
		handlerConfigMap.put(Constant.STEP_TYPE_INIT +
				Constant.NAME_SPLIT + ExecutePhase.INIT.getValue(), null);
		
		/* test-check&init */
		handlerConfigMap.put(Constant.STEP_TYPE_INIT +
				Constant.NAME_SPLIT + ExecutePhase.INIT.getValue(), null);
	
		/* test-check&init */
		handlerConfigMap.put(Constant.STEP_TYPE_HTTP +
				Constant.NAME_SPLIT + ExecutePhase.EXECUTE.getValue(), new HttpTestStepHandler());
	}
	
	/**
	 * 
	 * @param stepType   测试步骤类型
	 * @param phase      执行阶段
	 * @return
	 */
	public ITestStepHandler getHandler(String stepType, ExecutePhase exephase){
		
		String key = this.genKey(stepType, exephase);
		
		return handlerConfigMap.get(key);
	}
	
	/**
	 * 根据stepType 和 执行阶段进行key的组装 stepType-phase
	 * @param stepType
	 * @param phase
	 * @return stepType-phase 格式
	 */
	private String genKey(String stepType, ExecutePhase phase){
		return stepType + Constant.NAME_SPLIT + phase.getValue();
	}
	
	/* ===============================[getter setter]================================== */
	
	public Map<String, ITestStepHandler> getHandlerConfigMap() {
		return handlerConfigMap;
	}

	public void setHandlerConfigMap(Map<String, ITestStepHandler> handlerConfigMap) {
		TestStepHandlerFactory.handlerConfigMap = handlerConfigMap;
	}
	
}
