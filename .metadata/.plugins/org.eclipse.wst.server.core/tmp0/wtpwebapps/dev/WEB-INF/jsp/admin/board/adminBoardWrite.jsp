<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ethree" uri="/WEB-INF/tld/ethree.tld"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<title>게시판 등록</title>
	<script type="text/javascript">
	function insertBoardData() {
		
		var title = $.trim($("#title").val());
		
		if(title == null || title.length == 0) {
			alert("타이틀을 입력해주세요");
			return;
		}
		
		if(confirm("등록하시겠습니까?")) {
			$("#form").attr("method", "post");
			$("#form").attr("action", "/admin/board/notice/insertNotice.do");
			$("#form").submit();
		}
	}
	</script>
</head>
<body>
	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">게시판 등록</h1>
	</div>
	
	<div class="table-responsive">
		<form id="form" name="form">
			<div class="form-group">
				<label for="title">제목</label>
				<input type="text" id="title" name="title" class="form-control" />
			</div>
			<div class="form-group">
				<label for="content">내용</label>
				<textarea id="content" name="content" rows="5" cols="25" class="form-control"></textarea>
			</div>
		</form>
	</div>

	<div style="text-align: right;">
		<a href="javascript:insertBoardData();" class="btn btn-primary">등록</a>
	</div>	

</body>
</html>