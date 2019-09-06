package kr.co.ethree.dev.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import kr.co.ethree.dev.common.model.ListHelperVO;

public class PagingTag extends SimpleTagSupport {

	private ListHelperVO listHelperVO = null;

	private int totalCount = 0;
	private int pageIndex = 1;

	private int cntPerPage = 10;
	private int cntPerblock = 10;

	private String jsFunction = null;

	public ListHelperVO getListHelperVO() {
		return listHelperVO;
	}

	public void setListHelperVO(ListHelperVO listHelperVO) {
		this.listHelperVO = listHelperVO;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public void setTotalCount(String totalCount) {

		if (totalCount == null || totalCount.length() == 0) {
			totalCount = "0";
		}

		this.totalCount = Integer.parseInt(totalCount);
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public void setPageIndex(String pageIndex) {

		if (pageIndex == null || pageIndex.length() == 0) {
			pageIndex = "1";
		}

		this.pageIndex = Integer.parseInt(pageIndex);
	}

	public int getCntPerPage() {
		return cntPerPage;
	}

	public void setCntPerPage(int cntPerPage) {
		this.cntPerPage = cntPerPage;
	}

	public void setCntPerPage(String cntPerPage) {

		if (cntPerPage == null || cntPerPage.length() == 0) {
			cntPerPage = "10";
		}

		this.cntPerPage = Integer.parseInt(cntPerPage);
	}

	public int getCntPerblock() {
		return cntPerblock;
	}

	public void setCntPerblock(int cntPerblock) {
		this.cntPerblock = cntPerblock;
	}

	public void setCntPerblock(String cntPerblock) {

		if (cntPerblock == null || cntPerblock.length() == 0) {
			cntPerblock = "10";
		}

		this.cntPerblock = Integer.parseInt(cntPerblock);
	}

	public String getJsFunction() {
		return jsFunction;
	}

	public void setJsFunction(String jsFunction) {

		if (this.listHelperVO == null) {
			jsFunction = "goPage";
		}

		this.jsFunction = jsFunction;
	}

	@Override
	public void doTag() throws JspException, IOException {

		int totalCount = this.totalCount;
		int pageIndex = this.pageIndex;
		int cntPerPage = this.cntPerPage;
		int cntPerblock = this.cntPerblock;

		if (this.listHelperVO != null) {
			totalCount = this.listHelperVO.getTotalCount();		// 전체 목록 수
			pageIndex = this.listHelperVO.getPageIndex(); 			// 현재 페이지 번호
			cntPerPage = this.listHelperVO.getCntPerPage(); 		// 페이징 표시 수
			cntPerblock = this.listHelperVO.getCntPerblock(); 	// 페이지당 목록 수
		}

		if (0 >= totalCount) {
			return;
		}

		StringBuffer paging = new StringBuffer();

		int totalPage = 0 == (totalCount - 1) ? 1 : (int) ((totalCount - 1) / cntPerPage) + 1;
		int currBlock = 0 == (pageIndex - 1) ? 1 : (int) ((pageIndex - 1) / cntPerblock) + 1;
		int totalBlock = 0 == (totalPage - 1) ? 1 : (int) ((totalPage - 1) / cntPerblock) + 1;
		int firstPage = ((currBlock - 1) * cntPerblock) + 1;
		int lastPage = currBlock * cntPerblock;

		if (firstPage == 0) {
			firstPage = 1;
		}

		if (totalBlock == currBlock) {
			lastPage = totalPage;
		}

		/**
		 * 맨앞으로 버튼
		 */
		if (pageIndex != 1 && firstPage > cntPerblock) {
			paging.append("<a href='javascript:").append(jsFunction).append("(").append(1).append(");' class='btn_first'>맨앞으로</a>");
		}

		/**
		 * 이전버튼
		 */
		if (firstPage > cntPerblock) {
			paging.append("<a href='javascript:").append(jsFunction).append("(").append(firstPage - 1).append(");' class='btn_prev'>이전</a>");
		}

		paging.append("<span>");

		/**
		 * 페이지 넘버링
		 */
		for (int i = firstPage; i <= lastPage; i++) {
			if (pageIndex == i) {
				paging.append("<strong><span>").append(i).append("</span></strong>");

			} else {
				if (this.listHelperVO != null && (this.jsFunction == null || this.jsFunction.length() == 0)) {

					String url = this.listHelperVO.getUrl();
					String urlParam = "?" + this.listHelperVO.getPageParamName() + "=" + i;

					if (this.listHelperVO.getUrlParam() != null && this.listHelperVO.getUrlParam().length() > 0) {
						urlParam += "&";
						urlParam += this.listHelperVO.getUrlParam();
					}

					paging.append("<a href='").append(url).append(urlParam).append("' class='btn_paging'>");
					paging.append("<span>").append(i).append("</span>");
					paging.append("</a>");

				} else {
					paging.append("<a href='javascript:").append(jsFunction).append("(").append(i).append(");' class='btn_paging'>");
					paging.append("<span>").append(i).append("</span>");
					paging.append("</a>");
				}
			}
		}

		paging.append("</span>");

		/**
		 * 다음 버튼
		 */
		if (totalPage > lastPage) {
			paging.append("<a href='javascript:").append(jsFunction).append("(").append(lastPage + 1).append(");' class='btn_next'>다음</a>");
		}

		/**
		 * 맨 뒤로 버튼
		 */
		if (totalPage > lastPage && pageIndex != totalPage) {
			paging.append("<a href='javascript:").append(jsFunction).append("(").append(totalPage).append(");' class='btn_last'>맨뒤로</a>");
		}

		getJspContext().getOut().write(paging.toString());
	}
}