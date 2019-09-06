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
	function register() {
		var reg_pwd = /^.*(?=.{8,15})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;
		
		if($('#userId').val() == "" || $('#userId').val() == null){
			alert("아이디를 입력해주세요");
		}else if(!reg_pwd.test($('#userPw').val())){
			alert("비밀번호를 확인하세요.\n(영문,숫자를 혼합하여 8~15자 이내)"); 
		}else if($('#userName').val() == "" || $('#userName').val() == null){
			alert("이름을 입력해주세요."); 
		}else{
			$("#form").attr("method", "post");
			$("#form").attr("action", "/user/signup.do");
			$("#form").submit();
			alert("가입이 완료 되었습니다.");
		}
	}
	
	function idCheck() {
		$.ajax({
			url: "/user/idCheck/"+$('#userId').val()+".do",
			type:'post',
			dataType:'json',
			contentType:'application/json',
			success: function (data) {
				if(data.msg==0){
					alert("사용가능한 아이디입니다.");
				}else{
					alert("중복된 아이디입니다.");
				}
			}, error : function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}
</script>
</head>

<body>

  <div class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header">가입</div>
      <div class="card-body">
        <form id="form">
          <div class="form-group">
          	아이디 : 
          	<div>
			  <input type="text" id="userId" name="userId" class="form-control" placeholder="Id" required autofocus>
			  <a class="btn btn-primary btn-block" href="javascript:idCheck();">중복체크</a>
			</div>
          </div>
          <div class="form-group">
                     비밀번호 :
			  <input type="password" id="userPw" name="userPw" class="form-control" placeholder="Password" required>
          </div>
          <div class="form-group">
                     이름 :
			  <input type="name" id="userName" name="userName" class="form-control" placeholder="Password" required>
          </div>
          <a class="btn btn-primary btn-block" href="javascript:register();">회원가입</a>
        </form>
      </div>
    </div>
  </div>
</body>


</html>
