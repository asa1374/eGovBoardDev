package kr.co.ethree.dev.common.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class RequestUtil {

	/**
	 * @RequestParam 기능과 동일한 목적으로 사용
	 * 
	 * @param request
	 * @return
	 */
	public static Map getRequestMap(HttpServletRequest request) {

		Map requestMap = new HashMap();

		Enumeration enumeration = request.getParameterNames();

		while (enumeration.hasMoreElements()) {

			String key = (String) enumeration.nextElement();
			String[] values = request.getParameterValues(key);

			if (values != null && values.length > 0) {
				for (int i = 0, len = values.length; i < len; i++) {
					String value = values[i];

					if (value != null && value.length() > 0) {
						value = value.replaceAll("<", "&lt;");
						value = value.replaceAll(">", "&gt;");

						values[i] = value;
					}
				}
			}

			if (values != null) {
				if (values.length > 1) {
					requestMap.put(key, values);

				} else {
					requestMap.put(key, values[0]);
				}
			}

		}

		return requestMap;
	}

	public static String getQueryString(final HttpServletRequest request) {

		String alreadyQueryString = request.getParameter("queryString");
		if (alreadyQueryString != null && alreadyQueryString.length() > 0) {
			return StringUtil.base64Decode(alreadyQueryString);
		}

		String parameters = "";

		if (request != null) {

			Enumeration requestParams = request.getParameterNames();

			while (requestParams.hasMoreElements()) {

				String paramName = (String) requestParams.nextElement();
				String[] paramValueArr = request.getParameterValues(paramName);

				if (paramValueArr.length == 0) {
					continue;
				}

				for (int i = 0, len = paramValueArr.length; i < len; i++) {

					if (parameters != null && parameters.length() > 0) {
						parameters += "&";
					}

					parameters += paramName;
					parameters += "=";

					String value = paramValueArr[i];

					if (value != null && value.length() > 0) {
						value = value.replaceAll("<", "&lt;");
						value = value.replaceAll(">", "&gt;");

						parameters += value;
					}

				}

			}
		}

		return parameters;
	}

}
