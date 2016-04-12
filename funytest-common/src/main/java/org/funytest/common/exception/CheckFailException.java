package org.funytest.common.exception;

public class CheckFailException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	/* fail 信息 */
	public String failMessage;
	
	public CheckFailException(String failMessage) {
		this.failMessage = failMessage;
	}
	
	public CheckFailException(String expect, String actual, String msg){
		StringBuffer buffer = new StringBuffer();
		buffer.append("===========[CHECK FAIL]===========\r\n");
		buffer.append("msg is : \r\n").append(msg);
		buffer.append("detail is :\r\n");
		buffer.append("expect = ").append(expect).append("\r\n");
		buffer.append("actual = ").append(expect).append("\r\n");
		buffer.append("==================================\r\n");
		this.failMessage = buffer.toString();
	}
	
	public String getFailMessage() {
		return failMessage;
	}

	public void setFailMessage(String failMessage) {
		this.failMessage = failMessage;
	}
	
}
