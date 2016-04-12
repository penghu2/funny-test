package org.funytest.common.model;

import org.funytest.common.utils.http.ResponseStatus;

/**
 * http测试信息类
 * @author xiuzhu
 *
 */
public class HttpTestInfo {
	
	private String id;
	
	/* 是否需要check */
	public boolean needCheck;
	
	/* http实体类 */
	private HttpEntry httpEntry;
	
	/* 期望的http返回状态 */
	private String expectHttpStatusCode;
	
	/* 期望的http返回结果 */
	private String expectResponseBody;

	/* 实际的http返回结果 */
	private ResponseStatus actualResponseStatus;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public HttpEntry getHttpEntry() {
		return httpEntry;
	}

	public void setHttpEntry(HttpEntry httpEntry) {
		this.httpEntry = httpEntry;
	}

	public String getExpectHttpStatus() {
		return expectHttpStatusCode;
	}

	public void setExpectHttpStatus(String expectHttpStatus) {
		this.expectHttpStatusCode = expectHttpStatus;
	}

	public String getExpectResponseBody() {
		return expectResponseBody;
	}

	public void setExpectResponseBody(String expectResponseBody) {
		this.expectResponseBody = expectResponseBody;
	}

	public ResponseStatus getActualResponseStatus() {
		return actualResponseStatus;
	}

	public void setActualResponseStatus(ResponseStatus actualResponseStatus) {
		this.actualResponseStatus = actualResponseStatus;
	}

	public boolean isNeedCheck() {
		return needCheck;
	}

	public void setNeedCheck(boolean needCheck) {
		this.needCheck = needCheck;
	}
	
	
}
