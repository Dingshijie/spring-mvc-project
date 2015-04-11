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
				<fieldset>
				<legend>商品信息 </legend>
				<div class="row col-md-12">
				  <div class="col-md-4">
				    <a href="#" class="thumbnail">
				      <img src="/resources${commodity.picture }" alt="..."  style="width:100%;height: 250px" >
				    </a>
				  </div>
				  <div class="col-md-6">
					<ul class="list-unstyled">
						<li>商品名称：${commodity.name }</li>
						<c:if test="${commodity.brand != '' }">
							<li>商品品牌：${commodity.brand }</li>
						</c:if>
						<li> <fmt:formatNumber value="${commodity.price }" type="currency"/> 元 / ${commodity.unit }</li>
						<li>商品清单：${commodity.goods }</li>
						<c:if test="${commodity.description != '' }">
							<li>描述：${commodity.description }</li>
						</c:if>
						<c:if test="${commodity.addTime != '' }">
							<li>上架时间：${commodity.addTime }</li>
						</c:if>
						<li>浏览次数：${commodity.views } 次</li>
						<c:if test="${commodity.link != '' }">
							<li>商品链接：<a>${commodity.link }</a></li>
						</c:if>
					</ul>
				  </div>
				</div>
				</fieldset>
				<fieldset>
				<legend>商品管理  </legend>
				<div class="col-md-12 ">
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
				</fieldset>
			</div>
		</div>
	</div>
</body>
</html>