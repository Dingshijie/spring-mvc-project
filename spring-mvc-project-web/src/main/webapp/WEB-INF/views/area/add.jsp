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
<title>Insert title here</title>
	<!-- 新 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet" href="${initParam.resourceRoot}/lib/bootstrap/css/bootstrap.min.css">
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/bootstrap/js/bootstrap.min.js"></script>
	<!-- select2 css和js -->
	<link rel="stylesheet" href="${initParam.resourceRoot}/lib/select2/css/select2.min.css" />
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/select2/js/select2.min.js"></script>
	
	<!-- 页面的和css 文件必须放到bootstrap.min.cs之后-->
	<script type="text/javascript" src="${initParam.resourceRoot}/js/area/add.js"></script>
	<link rel="stylesheet" href="${initParam.resourceRoot}/css/area/add.css" />

</head>
<body>

	<c:import url="../common/manager-top.jsp"></c:import>
	<c:import url="../common/manager-left.jsp"><c:param name="active">areamanager</c:param><c:param name="subactive">addarea</c:param></c:import>
	
	<div class="container-fluid col-md-10 col-md-offset-2" style="padding: 20px;">
		<div class="show-grid">
			<div id="mainList">
				<form class="form-horizontal col-md-offset-1 col-md-10" id="myForm" role="form">
					<fieldset>
						<legend>添加地区</legend>
						<div class="form-group">
							<label for="code" class="col-sm-2 control-label">地区代码:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="code" name="code" placeholder="请输入地区代码"> 
							</div>
							<div class="col-sm-3" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="codeerror"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">地区名称:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="name" name="name" placeholder="请输入地区名称"> 
							</div>
							<div class="col-sm-3" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="nameerror"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="display" class="col-sm-2 control-label">显示名称:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="display" name="display" placeholder="请输入地区显示名称"> 
							</div>
							<div class="col-sm-3" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="displayerror"></span>
							</div>
						</div>
						<div class="form-group" style="margin-top: 20px;">
							<label for="typeCode" class="col-sm-2 control-label">地区类型:</label>
							<div class="col-sm-4">
								<select id="typeCode" name="typeCode" class="form-control  js-example-data-array-selected" style="width:auto;float: left;">
			                        <option value="" selected="selected">请选择</option>
			                        <option value="1">直辖市</option>
			                        <option value="2">省会城市</option>
			                        <option value="3">计划单列市</option>
			                        <option value="4">地级市</option>
			                        <option value="5">县级市</option>
			                        <option value="6">县</option>
			                    </select> 
							</div>
							<div class="col-sm-3" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="typeCodeerror"></span>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
							     <button type="button" id="btn-submit" class="btn btn-info col-sm-offset-1" >保存</button>
							     <button type="reset" class="btn reset" ></button>
							     <button type="button" class="btn btn-default col-sm-offset-1" onclick="window.history.go(-1);">返回</button>
						    </div>
					  </div>
					</fieldset>
				</form>
			</div>
			<div id="myModal" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			  <div class="modal-dialog modal-sm">
			    <div class="modal-content">
				    <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				        <h4 class="modal-title" id="myModalLabel">Info</h4>
				    </div>
				    <div class="modal-body">
				      	 表单提交成功，即将跳转到列表页面&hellip;
				    </div>
			    </div>
			  </div>
			</div>
		</div>
	</div>
</body>
</html>