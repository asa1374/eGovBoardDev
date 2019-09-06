<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ethree" uri="/WEB-INF/tld/ethree.tld"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<title>게시판 조회</title>
</head>
<body>

	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">게시판 조회</h1>
	</div>
	
	<div class="table-responsive">
		<form id="form" name="form">
			<table>
				<tr>
					<td>
						${boardDataMap.title}
					</td>
				</tr>
				<tr>
					<td>
						<div style="white-space: pre-line;"><ethree:clobToString value="${boardDataMap.content}" /></div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div class="table-responsive">
		<form id="form" name="form">
			<div class="form-group">
				<label for="title">제목</label>
				${boardDataMap.title}
			</div>
			<div class="form-group">
				<label for="content">내용</label>
				<div style="white-space: pre-line;"><ethree:clobToString value="${boardDataMap.content}" /></div>
			</div>
		</form>
	</div>
	
	<div style="text-align: right;">
		<a href="/admin/board/notice/noticeModify.do?seq=${boardDataMap.seq}" class="btn btn-primary">수정</a>
	</div>

</body>
</html>