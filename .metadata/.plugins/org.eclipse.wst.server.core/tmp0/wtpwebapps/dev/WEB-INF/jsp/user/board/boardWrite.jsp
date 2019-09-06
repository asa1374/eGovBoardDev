<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글쓰기</title>
<script type="text/javascript">
	function insertBoardData() {
		
		var title = $.trim($("#title").val());
		
		if(title == null || title.length == 0) {
			alert("타이틀을 입력해주세요");
			return;
		}
		
		if(confirm("등록하시겠습니까?")) {
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
	   <form id="form" name="form" enctype="multipart/form-data" method="post" action="/user/board/insert.do">
			<div class="form-group">
				<label for="title">제목</label>
				<input type="text" id="title" name="title" class="form-control" />
			</div>
			<div class="form-group">
				<label for="content">내용</label>
				<textarea id="content" name="content" rows="5" cols="25" class="form-control"></textarea>
			</div>
			<div class="form-group">
				<label for="file">첨부파일</label><br/>
				<input type="file" name="file" size="70" />
			</div>
		</form>
	  </div>
	</div>
	<div style="text-align: right;">
		<a href="javascript:insertBoardData();" class="btn btn-primary" style="margin-right: 15px; margin-bottom: 5px;">등록</a>
	</div>
 </div>
</body>
</html>