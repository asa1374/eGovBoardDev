<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ethree" uri="/WEB-INF/tld/ethree.tld"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>로그인 페이지</title>

	<script type="text/javascript">
	function login() {
		$("#form").attr("method", "post");
		$("#form").attr("action", "/admin/login.do");
		$("#form").submit();
	}
	</script>
</head>
<body>
	<form id="form" name="form" class="form-signin">
		<h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>

		<label for="inputEmail" class="sr-only">ID</label>
		<input type="text" id="userId" name="userId" class="form-control" placeholder="Id" required autofocus value="admin">
		
		<label for="inputPassword" class="sr-only">Password</label>
		<input type="password" id="userPw" name="userPw" class="form-control" placeholder="Password" required value="1234">
		
		<div class="checkbox mb-3">
			<label> <input type="checkbox" value="remember-me"> Remember me</label>
		</div>
		<a href="javascript:login();" class="btn btn-lg btn-primary btn-block">Sign in</a>
		<p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
	</form>
</body>
</html>