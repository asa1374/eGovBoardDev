<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta charset="utf-8" />
	<title><sitemesh:write property='title' /> - 공통 프로젝트</title>
	<script type="text/javascript" src="/assets/js/common/library/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="/assets/js/common/library/jquery.cookie.js"></script>
	<script type="text/javascript" src="/assets/js/user/userCommon.js"></script>
	<sitemesh:write property='head' />
</head>
<body>
	<h3>User Pop Test</h3>
	<sitemesh:write property='body' />
</body>
</html>