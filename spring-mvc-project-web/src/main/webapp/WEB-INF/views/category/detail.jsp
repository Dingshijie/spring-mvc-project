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
	<title>校园E+ 添加类别</title>
	<!-- 新 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet" href="${initParam.resourceRoot}/lib/bootstrap/css/bootstrap.min.css">
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/bootstrap/js/bootstrap.min.js"></script>
	<!-- select2 css和js -->
	<link rel="stylesheet" href="${initParam.resourceRoot}/lib/select2/css/select2.min.css" />
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/select2/js/select2.min.js"></script>
	<!-- iCheck css和JavaScript文件 -->
	<link href="${initParam.resourceRoot}/lib/iCheck/skins/square/blue.css" rel="stylesheet">
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/iCheck/js/icheck.min.js"></script>
	
	<link rel="stylesheet" href="${initParam.resourceRoot}/css/category/detail.css">
	<script type="text/javascript" src="${initParam.resourceRoot}/js/category/detail.js"></script>
</head>
<body>

	<c:import url="../common/manager-top.jsp"></c:import>
	<c:import url="../common/manager-left.jsp"><c:param name="active">categorymanager</c:param><c:param name="subactive">categorydetail</c:param></c:import>
	<div class="container-fluid col-md-10 col-md-offset-2" style="padding: 20px;">
		<div class="show-grid">
			<div id="mainList">
				<form class="form-horizontal col-md-offset-2 col-md-6" id="myForm" role="form">
					<fieldset>
						<legend>添加分类</legend>
						<input type="hidden" id="id" value="${category.id }"> 
						<div class="form-group" style="margin-top: 20px;">
							<label for="code" class="col-sm-4 control-label">代码:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" disabled="disabled" id="code" name="code" value="${category.code }" placeholder="类别代码"> 
							</div>
							<div class="col-sm-4" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="codeerror"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-4 control-label">名称:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="name" name="name" value="${category.name }" placeholder="类别名称"> 
							</div>
							<div class="col-sm-4" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="nameerror"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="category" class="col-sm-4 control-label">所属分类:</label>
							<div class="col-sm-4">
								<select id="category" name="category" class="form-control js-example-data-array-selected" style="width: 100%; float: left;">
									<option value="" >所有类别</option>
									<option value="11" <c:if test="${category.category == '11' }">selected="selected"</c:if> >11 女装</option>
									<option value="12" <c:if test="${category.category == '12' }">selected="selected"</c:if> >12 男装</option>
				 			  	    <option value="13" <c:if test="${category.category == '13' }">selected="selected"</c:if> >13 鞋靴箱包</option>
									<option value="14" <c:if test="${category.category == '14' }">selected="selected"</c:if> >14 运动户外</option>
									<option value="15" <c:if test="${category.category == '15' }">selected="selected"</c:if> >15 手机数码</option>
									<option value="16" <c:if test="${category.category == '16' }">selected="selected"</c:if> >16 美妆配饰</option>
									<option value="17" <c:if test="${category.category == '17' }">selected="selected"</c:if> >17 生活用品</option>
									<option value="18" <c:if test="${category.category == '18' }">selected="selected"</c:if> >18 学习用品</option>
									<option value="91" <c:if test="${category.category == '91' }">selected="selected"</c:if> >91 其他</option>
								</select>  
							</div>
							<div class="col-sm-4" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="categoryerror"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="hot" class="col-sm-4 control-label">是否设置为热门:</label>
							<div class="col-sm-4 control-label">
			
								<div class="col-sm-5 ">
									<input name="hot" type="radio" value="1" <c:if test="${category.hot==1 }">checked</c:if> >是
								</div>
								<div class="col-sm-5">
									<input name="hot" type="radio" value="0" <c:if test="${category.hot==0 }">checked</c:if>>否
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="enable" class="col-sm-4 control-label">是否设置为可用:</label>
							<div class="col-sm-4 control-label">
								<div class="col-sm-5">
									<input name="enable" type="radio" value="1" <c:if test="${category.enable==1 }">checked</c:if>>是
								</div>
								<div class="col-sm-5">
									<input name="enable" type="radio" value="0" <c:if test="${category.enable==0 }">checked</c:if> >否
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-3 col-sm-6">
							     <button type="button" id="btn-submit" class="btn btn-info col-sm-offset-1" >保存</button>
							     <button type="reset" class="btn reset" ></button>
							     <button type="button" class="btn btn-default col-sm-offset-1" onclick="window.history.go(-1);" >返回</button>
						    </div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
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

</body>
</html>