package org.funytest.common.model;
import java.util.Map;

/**
 * http 入口信息
 * @author xiuzhu
 *
 */
public class HttpEntry {
	
	public enum MethodType{
		GET, POST;
	} 
	
	private String id;
	
	private String url;
	
	private MethodType methodType;
	
	private Map<String, String> headers;
	
	/* post参数属性 */
	private Map<String, Object> params;
	
	public HttpEntry(){}
	
	/**
	 * 构造函数
	 * @param id
	 * @param url
	 * @param method
	 * @param headers
	 */
	public HttpEntry(String id, String url, String method, Map<String, String> headers){
		this.id = id;
		this.url = url;
		this.headers = headers;
		
		if ("post".equalsIgnoreCase(method)){
			this.methodType = MethodType.POST;
		} else {
			this.methodType = MethodType.GET;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
	
	public MethodType getMethodType() {
		return methodType;
	}

	public void setMethodType(MethodType methodType) {
		this.methodType = methodType;
	}
	
	public void setMethodType(String method){
		if ("post".equalsIgnoreCase(method)){
			this.methodType = MethodType.POST;
		} else {
			this.methodType = MethodType.GET;
		}
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	
	
}
