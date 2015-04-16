<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="webRoot" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>校园E+ 常见问题</title>
	<!-- 新 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet" href="${initParam.resourceRoot}/lib/bootstrap/css/bootstrap.min.css">
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/bootstrap/js/bootstrap.min.js"></script>
	
	<link rel="stylesheet" href="${initParam.resourceRoot}/css/suggestions.css">
</head>
<body>
	<c:import url="common/top.jsp"></c:import>
	<div class="container-fluid col-md-8 col-md-offset-2" style="padding: 20px;">
		<div class="show-grid">
			<div id="mainList">
			<fieldset>
				<legend>投诉建议 </legend>
				<dl>
					<dd>1、用户对本服务平台不满意的地方，请把本人不满意的地方以及带来不便的相关投诉建议，已word文档形式发到客服邮箱，客服会尽快给你回复。</dd>
					<dd>2、 买卖双方发生争执，且双方解决不了的问题，请QQ联系客服。</dd>
				</dl>
			</fieldset>
			</div>
		</div>
	</div>
</body>
</html>