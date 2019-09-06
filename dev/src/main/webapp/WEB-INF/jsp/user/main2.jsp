<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	          <th style="width: 50px;">#</th>
	          <th>제목</th>
	          <th style="width: 226px;">글쓴이</th>
	     	  <th style="width: 226px;">날짜</th>
	        </tr>
	      </thead>
	      <tbody>
	      </tbody>
	    </table>
	  </div>
	</div>
	<div style="text-align: right;">
		<a href="#" class="btn btn-primary" style="margin-right: 15px; margin-bottom: 5px;">등록</a>
	</div>
 </div>

</body>
</html>
