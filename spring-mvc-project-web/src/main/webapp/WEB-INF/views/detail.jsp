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
	
	<link rel="stylesheet" href="${initParam.resourceRoot}/css//detail.css">
	<script type="text/javascript" src="${initParam.resourceRoot}/js/detail.js"></script>
	
</head>
<body>
		<c:import url="common/top.jsp"></c:import>
		<div class="container-fluid col-md-10 col-md-offset-2" style="padding: 20px;">
		<div class="show-grid">
			<div id="mainList">
			<fieldset>
				<legend>商品信息 </legend>
				<div class="row col-md-12">
				  <div class="col-md-4">
				    <a href="#" class="thumbnail">
				    	<input type="hidden" id="id" value="${commodity.id }">
				      <img src="/resources${commodity.picture }" alt="..."  style="width:100%;height: 250px" >
				    </a>
				  </div>
				  <div class="col-md-6">
				  	<dl class="dl-horizontal">
					  <dt>商品名称</dt>
					  <dd>${commodity.name }</dd>
					  <c:if test="${commodity.brand != '' }">
						  <dt>商品品牌</dt>
						  <dd>${commodity.brand }</dd>
					  </c:if>
					  <dt>价格</dt>
					  <dd><span class="price-symbol">¥</span><strong class="price-current"><fmt:formatNumber value="${commodity.price }" pattern="#,###,#00.00"></fmt:formatNumber></strong>  元 / ${commodity.unit }</dd>
					  <dt>商品清单</dt>
					  <c:forEach items="${commodity.goods }" var="good">
					  <dd>${good}</dd>
					  </c:forEach>
					  <c:if test="${commodity.description != '' }">
						  <dt>描述</dt>
						  <dd>${commodity.description }</dd>
					  </c:if>
					  <c:if test="${commodity.addTime != '' }">
					  	  <dt>上架时间</dt>
						  <dd><fmt:formatDate value="${commodity.addTime }" pattern="yyyy-MM-dd HH:mm:ss" timeZone="GMT+8"/></dd>
					  </c:if>
					  <dt>浏览次数</dt>
					  <dd><strong class="views">${commodity.views }</strong> 次</dd>
					  <c:if test="${commodity.realName != '' }">
					   	 <dt>商家</dt>
					 	 <dd>${commodity.realName }</dd>
					  </c:if>
					  <c:if test="${commodity.companyName != '' }">
					   	 <dt>店铺名</dt>
					 	 <dd>${commodity.companyName }</dd>
					  </c:if>
					 <dt>联系方式</dt>
					 <dd>${commodity.mobilePhone }</dd>
					 <c:if test="${commodity.telPhone != '' }">
						 <dt>固定电话</dt>
						 <dd>${commodity.telPhone }</dd>
					 </c:if>
					 <c:if test="${commodity.link != '' }">
						 <dt>相关链接</dt>
						 <dd><a href="${commodity.link }">${commodity.link }</a></dd>
					 </c:if>
					</dl>
				  </div>
				</div>
			</fieldset>
			</div>
		</div>
	</div>
</body>
</html>