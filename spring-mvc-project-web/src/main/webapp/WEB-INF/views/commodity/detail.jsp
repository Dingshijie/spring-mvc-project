<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="webRoot" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>校园E+ 商品信息</title>
	<!-- 新 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet" href="${initParam.resourceRoot}/lib/bootstrap/css/bootstrap.min.css">
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/bootstrap/js/bootstrap.min.js"></script>
	
	<link rel="stylesheet" href="${initParam.resourceRoot}/css/commodity/detail.css">
	<script type="text/javascript" src="${initParam.resourceRoot}/js/commodity/detail.js"></script>
	
</head>
<body>
	<c:import url="../common/manager-top.jsp"></c:import>
	<c:import url="../common/manager-left.jsp"><c:param name="active">commoditymanager</c:param><c:param name="subactive">commoditydetail</c:param></c:import>
	<div class="container-fluid col-md-10 col-md-offset-2" style="padding: 20px;">
		<div class="show-grid">
			<div id="mainList">
				<div class="row col-md-12">
				  <div class="col-md-4">
				    <a href="#" class="thumbnail">
				      <img src="..." alt="..."  style="width:100%;height: 250px" >
				    </a>
				  </div>
				  <div class="col-md-6">
					<ul class="list-unstyled">
						<li>商品名称</li>
						<li>价格</li>
						<li>描述</li>
						<li>商家店</li>
						<li>用户真实姓名</li>
						<li>联系方式</li>
						<li>...</li>
						<li><a href="" title="点击查看商品详情">链接</a></li>
					</ul>
				  </div>
				</div>
				<div class="col-md-12 highlight">
					<div class="form-group bg-primary col-md-offset-1">
						设置推荐：
						<input type="radio" >是 <span style="margin-right: 15px;"></span><input type="radio" >否
					</div>
					<div class="form-group bg-danger col-md-offset-1">
						强制下架：
						<input type="radio" >是 <span style="margin-right: 15px;"></span><input type="radio" >否
					</div>
					<div class="form-group col-md-offset-1">
						<input class="btn btn-info" type="submit" value="保存">
						<input class="btn btn-default" type="submit" value="取消">
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>