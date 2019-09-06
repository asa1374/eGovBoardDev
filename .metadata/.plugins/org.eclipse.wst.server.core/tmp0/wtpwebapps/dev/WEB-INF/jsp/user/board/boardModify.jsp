<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>수정</title>
<script type="text/javascript">
	function updateBoardData() {
		
		var title = $.trim($("#title").val());
		
		if(title == null || title.length == 0) {
			alert("타이틀을 입력해주세요");
			return;
		}
		
		if(confirm("수정하시겠습니까?")) {
			$("#form").attr("method", "post");
			$("#form").attr("action", "/user/board/update.do");
			$("#form").submit();
		}
	}
	</script>
</head>
<body>
<div class="card mb-3">
	<div class="card-header">
	  <i class="fas fa-table"></i>
	  	게시판
	</div>
	<div class="card-body">
	  <div class="table-responsive">
	   <form id="form" name="form" enctype="multipart/form-data">
	   		<input type="hidden" id="seq" name="seq" value="${boardData.boardSeq}" />
			<div class="form-group">
				<label for="title">제목</label>
				<input type="text" id="title" name="title" class="form-control" value="${boardData.boardTitle}">
			</div>
			<div class="form-group">
				<label for="content">내용</label>
				<textarea id="content" name="content" rows="5" cols="25" class="form-control">${boardData.boardContent}</textarea>
			</div>
			<div class="form-group">
				<label for="file">첨부파일</label><br/>
				<input type="file" name="file" size="70" />
				<div id="fileupload">
					<c:if test="${not empty boardData.fileName }">
							<a href="/user/board/fileDown.do?seq=${boardData.boardSeq}" name="file">${boardData.fileName }</a> 
								(${boardData.fileSize }kb)
							<button id="deletefile" name="button" class="btn btn-primary">삭제</button>
							
					</c:if>
				</div>
			</div>
		</form>
	  </div>
	</div>
	<div style="text-align: right;">
		<a href="javascript:updateBoardData();" class="btn btn-primary" style="margin-right: 15px; margin-bottom: 5px;">수정</a>
		<a href="/user/board/delete.do?seq=${boardData.boardSeq}" class="btn btn-primary" style="margin-right: 15px; margin-bottom: 5px;">삭제</a>
	</div>
 </div>
 <script type="text/javascript">
 	$("#deletefile").click(function(){
 		$("#fileupload").empty();
 	})
 </script>
</body>
</html>