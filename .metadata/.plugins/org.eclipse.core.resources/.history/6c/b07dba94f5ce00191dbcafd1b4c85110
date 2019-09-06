<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html lang="kr">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>로그인</title>
  
  <script type="text/javascript">
	function login() {
		$("#form").attr("method", "post");
		$("#form").attr("action", "/user/login.do");
		$("#form").submit();
	}
</script>
</head>

<body>

  <div class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header">Login</div>
      <div class="card-body">
        <form id="form">
          <div class="form-group">
              <label for="inputEmail" class="sr-only">ID</label>
			  <input type="text" id="userId" name="userId" class="form-control" placeholder="Id" value="test" required autofocus>
          </div>
          <div class="form-group">
              <label for="inputPassword" class="sr-only">Password</label>
			  <input type="password" id="userPw" name="userPw" class="form-control" placeholder="Password" value="dlckdwns1" required>
          </div>
          <div class="form-group">
            <div class="checkbox">
              <label>
                <input type="checkbox" value="remember-me">
                Remember Password
              </label>
            </div>
          </div>
          <a class="btn btn-primary btn-block" href="javascript:login();">로그인</a>
          <a class="btn btn-primary btn-block" href="/register.do">회원가입</a>
        </form>
      </div>
    </div>
  </div>
</body>
</html>
