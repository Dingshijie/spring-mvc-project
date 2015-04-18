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
	<title>校园E+ 添加专业代码</title>
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
	<script type="text/javascript" src="${initParam.resourceRoot}/js/edupubcode/detail.js"></script>
	<link rel="stylesheet" href="${initParam.resourceRoot}/css/edupubcode/detail.css" />
</head>
<body>
	<c:import url="../common/manager-top.jsp"></c:import>
	<c:import url="../common/manager-left.jsp"><c:param name="active">edupubcodemanager</c:param><c:param name="subactive">edupubcodedetail</c:param></c:import>
	
	<div class="container-fluid col-md-10 col-md-offset-2" style="padding: 20px;">
		<div class="show-grid">
			<div id="mainList">
				<form class="form-horizontal col-md-offset-1 col-md-10" id="myForm" role="form">
					<fieldset>
						<legend>专业代码详情</legend>
						<input type="hidden" id="id" value="${edupubcode.id }">
						<div class="form-group" style="margin-top: 20px;">
							<label for="eduLevel" class="col-sm-2 control-label">学历类别:</label>
							<div class="col-sm-4">
								<select id="eduLevel" name="eduLevel" class="form-control" style="width:100%;float: left;">
									<option value="">请选择</option>
			                        <option value="${edupubcode.eduLevel }" selected="selected">${edupubcode.category }</option>
			                    </select> 
							</div>
							<div class="col-sm-4" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="eduLevelerror"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="firstCode" class="col-sm-2 control-label">专业学科大类:</label>
							<div class="col-sm-4">
							<select id="firstCode" name="firstCode" class="form-control" style="width:100%;float: left;">
								<option value="">请选择</option>
		                        <option value="${edupubcode.firstCode }" selected="selected">${edupubcode.firstName }</option>
		                    </select>
							</div>
							<div class="col-sm-4" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="firstCodeerror"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="secondCode" class="col-sm-2 control-label">专业学科中类:</label>
							<div class="col-sm-4">
							<select id="secondCode" name="secondCode" class="form-control" style="width:100%;float: left;">
		                        <option value="">请选择</option>
		                        <option value="${edupubcode.secondCode }" selected="selected">${edupubcode.secondName }</option>
		                    </select>
							</div>
							<div class="col-sm-4" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="secondCodeerror"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="code" class="col-sm-2 control-label">专业代码:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="code" name="code" disabled="disabled" value="${edupubcode.code }" placeholder="请输入专业代码"> 
							</div>
							<div class="col-sm-4" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="codeerror"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">专业名称:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="name" name="name" value="${edupubcode.name }" placeholder="请输入专业名称"> 
							</div>
							<div class="col-sm-4" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="nameerror"></span>
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