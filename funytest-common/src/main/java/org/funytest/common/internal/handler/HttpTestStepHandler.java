package org.funytest.common.internal.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpException;
import org.funytest.common.internal.TestContextHolder;
import org.funytest.common.internal.checker.HttpChecker;
import org.funytest.common.model.HttpEntry;
import org.funytest.common.model.HttpEntry.MethodType;
import org.funytest.common.model.HttpTestInfo;
import org.funytest.common.model.TestContext;
import org.funytest.common.model.teststep.HttpTestStep;
import org.funytest.common.model.teststep.ITestStep;
import org.funytest.common.utils.CollectionUtil;
import org.funytest.common.utils.Constant;
import org.funytest.common.utils.LogUtils;
import org.funytest.common.utils.UrlUtil;
import org.funytest.common.utils.http.HttpClientExt;
import org.funytest.common.utils.http.ResponseStatus;

public class HttpTestStepHandler implements ITestStepHandler {

	public static final String CHECK_KEY = "http-uncheck";

	private HttpClientExt http;

	private HttpChecker checker;

	@Override
	public void handle(ITestStep step) throws Exception {
		/* 初始化http */
		initHttpAndChecker();

		/* 只处理http类型的 */
		if (!step.getType().equals(Constant.STEP_TYPE_HTTP))
			return;

		/* 强制转换一下 */
		HttpTestStep httpStep = (HttpTestStep) step;

		LogUtils.info("do http invoke ...");

		/* 执行http接口调用 */
		HttpTestInfo httpTestInfo = httpStep.getHttpTestInfo();
		HttpEntry entry = httpTestInfo.getHttpEntry();

		// 特殊逻辑
		filter(entry);

		LogUtils.info(entry.toString());
		try {
			ResponseStatus responseStatus = invoke(entry);
			httpTestInfo.setActualResponseStatus(responseStatus);

			LogUtils.info("http invoke suceess !!!");
			LogUtils.info(responseStatus.toString());

			if (httpTestInfo.needCheck) {
				initUnCheckInfo(httpTestInfo);
				checker.check();
			}
		} catch (HttpException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * 初始化check信息
	 * 
	 * @param context
	 * @param httpTestInfo
	 */
	protected void initUnCheckInfo(HttpTestInfo httpTestInfo) {
		TestContext context = TestContextHolder.getTestContext();
		Map<String, Object> checkMap = context.getContextWithInit();

		checkMap.put(CHECK_KEY, httpTestInfo);
	}

	/**
	 * 执行http接口调用
	 * 
	 * @param entry
	 * @return
	 * @throws IOException
	 * @throws HttpException
	 */
	private ResponseStatus invoke(HttpEntry entry) throws HttpException, IOException {
		MethodType type = entry.getMethodType();
		String url = entry.getUrl();
		Map<String, String> headers = entry.getHeaders();
		Map<String, Object> params = entry.getParams();

		switch (type) {
		case GET:
			return this.get(url, params, headers);

		case POST:
			return http.post(url, params, headers);

		}

		return null;
	}

	/**
	 * 带参数的get请求, 这里面会涉及到合并替换逻辑
	 * 
	 * @param url
	 * @param params
	 * @param headers
	 * @return
	 * @throws IOException
	 * @throws HttpException
	 */
	private ResponseStatus get(String url, Map<String, Object> params, Map<String, String> headers)
			throws HttpException, IOException {

		if (headers == null || headers.size() == 0) {
			return http.get(url);
		} else {
			return http.get(url, headers);
		}

	}

	/**
	 * 过滤替换逻辑
	 * @param entry
	 */
	private void filter(HttpEntry entry) {
		if (!entry.getMethodType().equals(MethodType.GET))
			return;

		String actural_url = entry.getUrl();
		Map<String, Object> params = entry.getParams();

		// 首先查找下url中是否已经存在, 存在的话需要将其覆盖掉
		Map<String, String> params_now = UrlUtil.praseGetUrlParams(actural_url);
		Map<String, String> params_new = new HashMap<String, String>();

		CollectionUtil.convert(params, params_new);
		Map<String, String> newCombineParams = CollectionUtil.combineMap(params_now, params_new);

		String absoluteUrl = UrlUtil.getAbsoluteUrl(actural_url) + "?";

		boolean isFirst = true;
		for (String key : newCombineParams.keySet()) {
			if (isFirst) {
				absoluteUrl = absoluteUrl + key + "=" + (newCombineParams.get(key) == null ? ""
						: newCombineParams.get(key));
				isFirst = false;
				continue;
			}
			absoluteUrl = absoluteUrl + "&" + key + "=" + (newCombineParams.get(key) == null ? ""
					: newCombineParams.get(key));
		}

		entry.setUrl(absoluteUrl);
	}

	/**
	 * 初始化http请求对象 + checker
	 */
	protected void initHttpAndChecker() {
		if (http == null) {
			http = HttpClientExt.getInstance();
		}

		if (checker == null) {
			checker = new HttpChecker();
		}
	}

	public HttpClientExt getHttp() {
		return http;
	}

	public void setHttp(HttpClientExt http) {
		this.http = http;
	}
}
