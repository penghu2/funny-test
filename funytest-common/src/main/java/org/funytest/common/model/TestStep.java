package org.funytest.common.model;

import org.funytest.common.exception.TestStepException;

/**
 * 测试步骤
 * @author Administrator
 *
 */
public abstract class TestStep {
	
	public enum Type{
		INIT("test-init"), 
		METHODEXE("test-method-execute"),
		EXPECT("test-expect"),
		CLEAN("test-clean");
		
		private String code;
		
		Type(String code){
			this.code = code;
		}
		
		public String getCode(){
			return this.code;
		}
	}
	
	protected String name;
	
	protected String id;
	
	protected Type type;
	
	public abstract void execute() throws TestStepException;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}
