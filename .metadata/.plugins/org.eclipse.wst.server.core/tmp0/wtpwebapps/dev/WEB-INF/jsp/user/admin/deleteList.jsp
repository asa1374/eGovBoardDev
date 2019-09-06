<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ethree" uri="/WEB-INF/tld/ethree.tld"%>
<!DOCTYPE html>
<html lang="kr">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>게시판 만들기</title>

  <!-- Custom styles for this template-->
  <link href="assets/css/common/sb-admin.css" rel="stylesheet">

</head>
<body>
		<!-- 관리자한테만 보여주기 -->
 <div class="row">
  <div class="col-xl-3 col-sm-6 mb-3">
    <div class="card text-white bg-primary o-hidden h-100">
      <div class="card-body">
        <div class="card-body-icon">
          <i class="fas fa-fw fa-comments"></i>
        </div>
        <div class="mr-5">회원수</div>
      </div>
    </div>
  </div>
  <div class="col-xl-3 col-sm-6 mb-3">
    <div class="card text-white bg-warning o-hidden h-100">
      <div class="card-body">
        <div class="card-body-icon">
          <i class="fas fa-fw fa-list"></i>
        </div>
        <div class="mr-5">총 게시글</div>
      </div>
    </div>
  </div>
  <div class="col-xl-3 col-sm-6 mb-3">
    <div class="card text-white bg-success o-hidden h-100">
      <div class="card-body">
        <div class="card-body-icon">
          <i class="fas fa-fw fa-shopping-cart"></i>
        </div>
        <div class="mr-5">공지사항수</div>
      </div>
    </div>
  </div>
</div>
<div class="card mb-3">
	<div class="card-header">
	  <i class="fas fa-table"></i>
	  	회원 목록
	</div>
	<div class="card-body">
	  <div class="table-responsive">
	    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
	      <thead>
	        <tr>
	          <th>아이디</th>
	          <th>이름</th>
	     	  <th>권한</th>
	     	  <th>추방</th>
	        </tr>
	      </thead>
	      <tbody>
         	<c:forEach var="row" items="${userDataListVO.list}" varStatus="status">
				<tr>
					<td scope="row">${row.userId}</td>
					<td>${row.userName}</td>
					<td>${row.userAuth}</td>
					<td>
						<a href="/user/delete.do?id=${row.userId}" class="btn btn-primary" style="margin-right: 15px; margin-bottom: 5px; width: -webkit-fill-available;">추방</a>
					</td>
				</tr>
			</c:forEach>
	      </tbody>
	    </table>
	  </div>
	</div>
 </div>
</body>
</html>
