package org.funytest.common.internal.checker;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.funytest.common.exception.CheckFailException;
import org.funytest.common.internal.handler.HttpTestStepHandler;
import org.funytest.common.internal.method.MethodWrapper;
import org.funytest.common.model.HttpTestInfo;
import org.funytest.common.model.TestContext;
import org.funytest.common.utils.ExceptionUtil;
import org.funytest.common.utils.MethodWrapperHelper;
import org.funytest.common.utils.http.ResponseStatus;

public class HttpChecker extends BaseChecker {
		
	@Override
	public String getName() {
		
		return "http-Checker";
	}

	@Override
	protected void check(TestContext context) throws CheckFailException {
		
		HttpTestInfo testInfo = getHttpTestInfo(context);
		
		if (testInfo==null) return;
		
		checkHttpStatusCode(testInfo);
		checkResponseContent(testInfo);
	}
	
	/**
	 * check http接口的返回数据，这个比较特殊, 详情请看代码
	 * @param testInfo
	 * @throws CheckFailException
	 */
	protected void checkResponseContent(HttpTestInfo testInfo) throws CheckFailException {
		
		String expectResponseBody = testInfo.getExpectResponseBody();
		
		Map<String, String> val = new HashMap<String, String>();
		if (MethodWrapperHelper.getMehtodInfo(expectResponseBody, val))
		{	
			//获取函数信息
			String func_info = val.get(MethodWrapperHelper.FUNC_KEY);
			//获取参数信息
			String params_info = val.get(MethodWrapperHelper.PARAM_KEY);
			String[] params_infos = params_info.split(",");
			replace(params_infos, testInfo.getActualResponseStatus());
			MethodWrapper methodW = MethodWrapperHelper.getMethodWrapper(func_info, params_infos);
			try {
				methodW.invoke();
			} catch (InvocationTargetException e) {
				
				throw new CheckFailException(ExceptionUtil.getStackTrace(e.getTargetException()));
			}
		}
		
	}
	
	/**
	 * 替换特殊变量
	 * @param params_infos
	 * @param responseStatus
	 */
	private void replace(String[] params_infos, ResponseStatus responseStatus){
		int len = params_infos.length;
		for (int i=0; i<len; i++) {
			
			if (params_infos[i].equals("$ResponseStr")) {
				try {
					params_infos[i] = responseStatus.getUTFContent();
				} catch (UnsupportedEncodingException e) {
					
					e.printStackTrace();
				}
			}
			
			if (params_infos[i].equals("$RespStatusCode")){
				params_infos[i] = String.valueOf(responseStatus.getStatusCode());
			}
		}
		
	}
	
	/**
	 * check httpStatusCode
	 * @param testInfo
	 * @throws CheckFailException
	 */
	protected void checkHttpStatusCode(HttpTestInfo testInfo) throws CheckFailException {
		//开始check
		String expectHttpStatus = testInfo.getExpectHttpStatus();
		if (expectHttpStatus != null) return;
		
		int actual = testInfo.getActualResponseStatus().getStatusCode();
		
		if (String.valueOf(actual).equals(expectHttpStatus)){
			return;
		} else {
			throw new CheckFailException(expectHttpStatus, String.valueOf(actual), "check http status code fail!!");
		}
	}
	
	/**
	 * 从测试上下文中获取HttpTestInfo
	 * @param context
	 * @return
	 */
	private HttpTestInfo getHttpTestInfo(TestContext context){
		if (context.getContext() != null){
			return (HttpTestInfo)context.getContext().get(HttpTestStepHandler.CHECK_KEY);
		}

		return null;
	}

	@Override
	protected void cleanCheckInfo(TestContext context) {
		if (context.getContext() != null){
			context.getContext().remove(HttpTestStepHandler.CHECK_KEY);
		}
	}

	public static void main(String[] args){
		String str = "return this.check($ResponseStr, 0)";
		
		Pattern p = Pattern.compile("^return (.*)\\((.*)\\)");
		Matcher m = p.matcher(str);
		while (m.find()){
			System.out.println(m.group( 0 ));
			System.out.println(m.group( 1 ));
			System.out.println(m.group( 2 ));
		}
	}
}
