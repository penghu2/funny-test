package org.funytest.common.sql;

/**
 * 表操作异常类
 * @author hupeng
 *
 */
public class TableHandleException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 异常描述信息
	 */
	private String message;
	
	public TableHandleException(String message){
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
