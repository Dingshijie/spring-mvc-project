<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>校园E+ 重置密码</title>
	<!-- 新 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet" href="${initParam.resourceRoot}/lib/bootstrap/css/bootstrap.min.css">
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/bootstrap/js/bootstrap.min.js"></script>
		
	<!-- 页面的和css 文件必须放到bootstrap。min.cs之后-->
	<script type="text/javascript" src="${initParam.resourceRoot}/js/user/password.js"></script>
	<link rel="stylesheet" href="${initParam.resourceRoot}/css/user/password.css" />
</head>
<body>
	<c:import url="../common/manager-top.jsp"></c:import>
	<c:import url="../common/manager-left.jsp"><c:param name="active">selfinfomanager</c:param><c:param name="subactive">password</c:param></c:import>
	
	<div class="container-fluid col-md-10 col-md-offset-2" style="padding: 20px;">
		<div class="row show-grid">
			<div id="mainList" class="col-md-8 col-md-offset-2">
				<fieldset>
					<legend>重置密码</legend>
				</fieldset>
			</div>
		</div>	
	</div>
</body>
</html>