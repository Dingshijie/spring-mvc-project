<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<!-- login CSS 文件 -->
	<link rel="stylesheet" href="${initParam.resourceRoot}/css/login.css">
	<!-- 新 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet" href="${initParam.resourceRoot}/lib/bootstrap/css/bootstrap.min.css">
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/bootstrap/js/bootstrap.min.js"></script>
	<!-- md5 的 js 文件 -->
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/md5.js"></script>
	
	<!-- 页面的js文件 文件必须放到bootstrap。min.cs之后 -->
	<script type="text/javascript" src="${initParam.resourceRoot}/js/login.js"></script>
</head>
<body>
	 <div class="container">
      <form class="form-signin" action="login" method="post">
	    <c:if test="${not empty  errorMsg }">
	       <div class="alert alert-danger"><i class="iconfont"></i> ${errorMsg}</div>
	    </c:if>
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="text" id="inputEmail" name="username" class="form-control" placeholder="Username / Email / Phonenumber" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit" id="btn-submit">Sign in</button>
      </form>
    </div> <!-- /container -->
</body>
</html>