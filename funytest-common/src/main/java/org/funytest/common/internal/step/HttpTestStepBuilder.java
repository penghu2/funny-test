package org.funytest.common.internal.step;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.funytest.common.exception.TestStepException;
import org.funytest.common.internal.IConfiguration;
import org.funytest.common.model.HttpEntry;
import org.funytest.common.model.HttpTestInfo;
import org.funytest.common.model.teststep.HttpTestStep;
import org.funytest.common.model.teststep.ITestStep;

public class HttpTestStepBuilder extends AbstractStepBuilder {

	@Override
	ITestStep buildStep(Element element, IConfiguration config) throws TestStepException {
		
		//获取根目录
		Element root = element.getDocument().getRootElement();
		
		@SuppressWarnings("unchecked")
		List<Element> httpElements = root.elements("http");
		
		//获取id的属性
		Attribute id_attr = element.attribute("id");
		if (id_attr == null) return null;
		
		/* 寻找对应id的http对象 */
		Element theHttpElement = null;
		for (Element httpElement : httpElements){
			if (httpElement.attribute("id").getValue().trim().equals(id_attr.getValue())){
				theHttpElement = httpElement;
				break;
			}
		}
		
		/* 没有找到对应的http配置 */
		if (theHttpElement==null) return null;
		
		HttpTestStep step = new HttpTestStep();
		step.setHttpTestInfo(buildHttpTestInfo(element, theHttpElement));
		
		return step;
	}
	
	private HttpTestInfo buildHttpTestInfo(Element testHttpEle, Element httpEle){
		HttpTestInfo testInfo = new HttpTestInfo();
		testInfo.setId(testHttpEle.attributeValue("id").trim());
		
		/* 更新check信息 */
		Element checkEle = testHttpEle.element("check");
		if (checkEle != null) {
			testInfo.setExpectHttpStatus(checkEle.elementText("status").trim());
			testInfo.setExpectResponseBody(checkEle.elementText("responseBody").trim());
			//设置check标志位
			testInfo.setNeedCheck(true);
		}
		
		HttpEntry httpEntry = buildHttpEntry(httpEle);
		/* 打入参数 */
		Element paramsEle = testHttpEle.element("params");
		if (paramsEle != null && paramsEle.hasContent()){
			Map<String, Object> params = new HashMap<String, Object>();
			for (Iterator<?> it=paramsEle.elementIterator();it.hasNext();) {
				Element item = (Element) it.next();
				params.put(item.getName(), item.getTextTrim());
			}
			httpEntry.setParams(params);
		}
		
		testInfo.setHttpEntry(httpEntry);
		
		return testInfo;
	}

	private HttpEntry buildHttpEntry(Element httpEle){
		HttpEntry httpEntry = new HttpEntry();
		httpEntry.setId(httpEle.attributeValue("id"));
		httpEntry.setMethodType(httpEle.attributeValue("method"));
		
		//解析url
		httpEntry.setUrl(httpEle.element("url").getTextTrim());
		//解析headers
		Map<String, String> headers = new HashMap<String, String>();
		Element headersEle = httpEle.element("headers");
		for (Iterator<?> it=headersEle.elementIterator();it.hasNext();){
			Element item = (Element) it.next();
			headers.put(item.getName(), item.getTextTrim());
		}
		
		httpEntry.setHeaders(headers);
		return httpEntry;
	}

}
