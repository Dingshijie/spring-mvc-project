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
	
	<link rel="stylesheet" href="${initParam.resourceRoot}/css/question.css">
</head>
<body>
	<c:import url="common/top.jsp"></c:import>
	<div class="container-fluid col-md-8 col-md-offset-2" style="padding: 20px;">
		<div class="show-grid">
			<div id="mainList">
			<fieldset>
				<legend>常见问题 </legend>
				<dl>
					<dt>1、不能正常注册？</dt>
					<dd>解决办法：用户注册时，必须输入自己的手机号码，并且一个手机号码只能注册一个用户名。当你首次注册，显示号码已注册，请联系。</dd>
					<dt>2、 搜索商品时，再返回首页时无商品信息？</dt>
					<dd>解决办法：当搜索商品时，如“女鞋”，显示“未查询到相关数据”，请重新设置搜索条件，或联系客服确定是否目前暂无数据。</dd>
					<dt>3、上传商品后，页面不显示上传的商品？</dt>
					<dd>解决办法：请在主页面上刷新页面，如还是没有显示，请进入管理中心，查看商品列表里是否有上传的商品。如果没有该商品，请重新上传；如果显示有该商品，请查看商品详情的商品管理部分，是否已经下架。</dd>
					<dt>4、 购买商品发现有瑕疵，想退货或换货？</dt>
					<dd>解决办法：请自己联系卖家。</dd>
				</dl>
			</fieldset>
			</div>
		</div>
	</div>
</body>
</html>