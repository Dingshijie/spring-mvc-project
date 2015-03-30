<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="webRoot" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>校园E+ 专业代码列表</title>
	<!-- 新 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet" href="${initParam.resourceRoot}/lib/bootstrap/css/bootstrap.min.css">
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/bootstrap/js/bootstrap.min.js"></script>
	
	<!-- list.css 文件必须放到bootstrap。min.cs之后 -->
	<link rel="stylesheet" href="${initParam.resourceRoot}/css/edupubcode/list.css">
	<script type="text/javascript" src="${initParam.resourceRoot}/js/edupubcode/list.js"></script>
</head>
<body>
	<c:import url="../common/manager-top.jsp"></c:import>
	<c:import url="../common/manager-left.jsp"><c:param name="active">edupubcodemanager</c:param><c:param name="subactive">edupubcodelist</c:param></c:import>
	<div class="container-fluid col-md-10 col-md-offset-2" style="padding: 20px;">
		<div class="row">
			<div class="col-md-10">
				<form class="form-inline" role="form">
					<div class="form-group">
						<select class="form-control" id="eduLevel">
	  						<option value="" selected="selected">全部学历</option>
	  						<option value="1">研究生专业</option>
	  						<option value="2">本科专业</option>
	  						<option value="3">专科专业</option>
	  					</select>
					</div>
					<div class="form-group">
						<input type="text" placeholder="请输入查询的关键字或代码" class="form-control"
							style="width: 200px;" id="keyword">
					</div>
					<button type="button" class="btn btn-success" id='keywordSearch'>查询</button>
					<button type="reset" class="btn reset"></button>
				</form>

			</div>
			<div class=" col-md-2 pubtool">
				<a href="javascript:void(0)" id="download" title="导出到处所有用户到excel文件" class="btn btn-info disabled pull-right"> 
				<span class="glyphicon glyphicon-save"></span> 导出到Excel
				</a>
			</div>
		</div>
		<div class="alert" role="alert"></div>
		<div class="row">
			<div class="container-fluid">
				<div class="panel comcode-wrap">
					<div class="panel-heading comcode-title clearfix">
						<ul class="pagination pull-right pageWrap"></ul>
						<div class="pull-left pageSizeWrap">
							<select id="pageSize">
								<option value="10" selected="selected">10</option>
								<option value="15">15</option>
								<option value="20">20</option>
							</select> 条 / 页
						</div>
					</div>
					<div id="content" class="panel-body">
						<div id="loadContentWrap">
							<div id="loadingImg">
								<img width="580px" height="435px" src="${initParam.resourceRoot}/img/loading.gif" />
							</div>
						</div>
						<div class="pagging clearfix">
							<ul class="pagination pageWrap pull-right">
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>