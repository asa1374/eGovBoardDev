<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항ㄴ</title>
</head>
<body>
<div class="card mb-3">
	<div class="card-header">
	  <i class="fas fa-table"></i>
	  	게시판
	</div>
	<div class="card-body">
	  <div class="table-responsive">
	    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
	      <thead>
	        <tr>
	          <th>제목 : ${boardData.boardTitle} </th>
	        </tr>
	      </thead>
	      <tbody>
			<tr>
				<td scope="row">${boardData.boardContent} </td>
			</tr>
	      </tbody>
	    </table>
	  </div>
	</div>
	<c:if test="${auth eq 'A' }">
		<div style="text-align: right;">
			<a href="/user/board/updateView.do?seq=${boardData.boardSeq}" class="btn btn-primary" style="margin-right: 15px; margin-bottom: 5px;">수정</a>
			<a href="/main2.do" class="btn btn-primary" style="margin-right: 15px; margin-bottom: 5px;">목록</a>
		</div>
	</c:if>
 </div>
</body>
</html>