<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title><sitemesh:write property='title' /> - 공통 프로젝트</title>
	<link rel="stylesheet" href="/assets/css/admin/bootstrap.css">
	<link rel="stylesheet" href="/assets/css/admin/adminCommon.css">
	
	<script type="text/javascript" src="/assets/js/common/library/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="/assets/js/common/library/jquery.cookie.js"></script>
	<script type="text/javascript" src="/assets/js/common/library/popper.min.js"></script>
	<script type="text/javascript" src="/assets/js/common/library/bootstrap.min.js"></script>
	<script type="text/javascript" src="/assets/js/admin/adminCommon.js"></script>
	<sitemesh:write property='head' />
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
				<sitemesh:write property='body' />
			</main>
		</div>
	</div>

</body>
</html>