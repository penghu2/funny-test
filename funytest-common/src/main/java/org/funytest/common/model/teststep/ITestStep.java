package org.funytest.common.model.teststep;

import org.funytest.common.exception.TestStepException;
import org.funytest.common.internal.handler.ITestStepHandler;

/**
 * 测试步骤
 * @author Administrator
 *
 */
public interface ITestStep {
	
	public enum Type{
		INIT("test-init"), 
		METHODEXE("test-method-execute"),
		EXPECT("test-expect"),
		CLEAN("test-clean"),
		Other("other");
		
		private String code;
		
		Type(String code){
			this.code = code;
		}
		
		public String getCode(){
			return this.code;
		}
		
		public static Type getTypeByCode(String code){
			
			for (Type item : Type.values()){
				if (item.getCode().equals(code)) return item;
			}
			
			return null;
		}
	}
	
	/**
	 * 获取类型
	 * @return
	 */
	public Type getType();
	
	/**
	 * 执行方法
	 * @throws TestStepException
	 */
	public void execute(ITestStepHandler handler) throws TestStepException;
}
