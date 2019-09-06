package kr.co.ethree.dev.common.model;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.co.ethree.dev.common.util.RequestUtil;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ListHelperVO {

	private String pageParamName = "pageIndex";

	private int totalCount = 0;
	private int pageIndex = 1;

	private int cntPerPage = 10;
	private int cntPerblock = 10;
	
	private int startRow = 0;

	private String btnType = "js";
	private String url = null;
	private String urlParam = null;

	private List list = null;
	private Map paramMap = null;

	public ListHelperVO() {
		this.paramMap = new HashMap();
	}

	public ListHelperVO(HttpServletRequest request) {

		this.btnType = "request";
		this.url = request.getRequestURI();
		this.urlParam = this.getQueryString(request);
		this.initListVO(RequestUtil.getRequestMap(request), this.cntPerPage);
	}

	public ListHelperVO(HttpServletRequest request, int cntPerPage) {

		this.btnType = "request";
		this.url = request.getRequestURI();
		this.urlParam = this.getQueryString(request);

		this.initListVO(RequestUtil.getRequestMap(request), cntPerPage);
	}

	public ListHelperVO(Map hMap) {
		this.initListVO(hMap, this.cntPerPage);
	}

	public ListHelperVO(Map hMap, int cntPerPage) {
		this.initListVO(hMap, cntPerPage);
	}

	private void initListVO(Map hMap, int cntPerPage) {

		if (this.paramMap == null) {
			this.paramMap = new HashMap();
		}

		this.paramMap.putAll(hMap);

		this.pageIndex = paramMap.get(this.pageParamName) != null ? Integer.valueOf(String.valueOf(paramMap.get(this.pageParamName))) : 1;
		this.cntPerPage = cntPerPage;
		
		this.startRow = cntPerPage * (pageIndex-1);
		
		this.paramMap.put("startRow", this.startRow);
		this.paramMap.put("pageIndex", this.pageIndex);
		this.paramMap.put("cntPerPage", this.cntPerPage);
	}

	public String getPageParamName() {
		return pageParamName;
	}

	public void setPageParamName(String pageParamName) {
		this.pageParamName = pageParamName;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getCntPerPage() {
		return cntPerPage;
	}

	public void setCntPerPage(int cntPerPage) {
		this.cntPerPage = cntPerPage;
	}

	public int getCntPerblock() {
		return cntPerblock;
	}

	public void setCntPerblock(int cntPerblock) {
		this.cntPerblock = cntPerblock;
	}

	public String getBtnType() {
		return btnType;
	}

	public void setBtnType(String btnType) {
		this.btnType = btnType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlParam() {
		return urlParam;
	}

	public void setUrlParam(String urlParam) {
		this.urlParam = urlParam;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Map getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map paramMap) {
		this.paramMap = paramMap;
	}

	public void setParamValue(String key, Object value) {

		if (value == null || value == "") {
			this.paramMap.put(key, null);
		}

		if (value instanceof Integer) {
			this.paramMap.put(key, Integer.parseInt(value.toString()));

		} else if (value instanceof Double) {
			this.paramMap.put(key, Double.parseDouble(value.toString()));

		} else if (value instanceof String) {
			this.paramMap.put(key, value.toString());

		} else {
			this.paramMap.put(key, value);
		}
	}

	public String getQueryString(HttpServletRequest request) {

		String parameters = "";

		if (request != null) {

			Enumeration requestParams = request.getParameterNames();

			while (requestParams.hasMoreElements()) {

				String paramName = (String) requestParams.nextElement();

				if (paramName == null || paramName.equals(pageParamName)) {
					continue;
				}

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