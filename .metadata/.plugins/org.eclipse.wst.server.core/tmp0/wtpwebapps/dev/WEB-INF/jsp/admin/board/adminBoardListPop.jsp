<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ethree" uri="/WEB-INF/tld/ethree.tld"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<title>게시판 목록</title>
	<script type="text/javascript">
	function goPage(index) {
		$("#pageIndex").val(index);
		$("#form").attr("method", "get");
		$("#form").submit();
	}
	</script>
</head>
<body>
	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">게시판 목록</h1>
	</div>
	
	<div class="table-responsive">
		<form id="form" name="form">
			<input type="hidden" id="pageIndex" name="pageIndex" value="${paramMap.pageIndex}" />
			<table class="table table-hover table-sm">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">제목</th>
						<th scope="col">조회수</th>
						<th scope="col">등록일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="row" items="${boardList}" varStatus="status">
					<tr>
						<td scope="row">${(boardListCnt + 1) - row.rnum}</td>
						<td>
							<a href="/admin/board/notice/noticeView.do?seq=${row.seq}">${row.title}</a>
						</td>
						<td>${row.hitCnt}</td>
						<td>${row.regDate}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
	
			<div style="text-align: center; letter-spacing: 5px;">
				<!-- listHelperVO 미사용 시 pageIndex/js 필수 --> 
				<ethree:paging totalCount="${boardListCnt}" pageIndex="${paramMap.pageIndex}" jsFunction="goPage" />
			</div>
		</form>
	</div>

	<div style="text-align: right;">
		<a href="/admin/board/notice/noticeWrite.do" class="btn btn-primary">등록</a>
	</div>
</body>
</html>