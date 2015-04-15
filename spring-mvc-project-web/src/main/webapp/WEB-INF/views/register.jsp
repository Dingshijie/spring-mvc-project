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
	<title>校园E+ 注册</title>
	<!-- 新 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet" href="${initParam.resourceRoot}/lib/bootstrap/css/bootstrap.min.css">
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/bootstrap/js/bootstrap.min.js"></script>
	<!-- select2 css和js -->
	<link rel="stylesheet" href="${initParam.resourceRoot}/lib/select2/css/select2.min.css" />
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/select2/js/select2.min.js"></script>
	<!-- md5 的 js 文件 -->
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/md5.js"></script>
	<!-- 页面的和css 文件必须放到bootstrap。min.cs之后-->
	<script type="text/javascript" src="${initParam.resourceRoot}/js/register.js"></script>
	<link rel="stylesheet" href="${initParam.resourceRoot}/css/register.css" />

</head>
<body>
	<div class="container-fluid">
		<div class="row show-grid">
			<div id="mainList" class="col-md-8 col-md-offset-2">
				<form class="form-horizontal" id="myForm" role="form">
					<fieldset>
						<legend>用户注册</legend>
						<ul class="nav nav-tabs nav-justified" role="tablist" id="myTab">
							<li role="presentation" class="active"><a href="#bussiness" aria-controls="bussiness" data-toggle="tab" class="btn">商家用户</a></li>
							<li role="presentation"><a href="#student" aria-controls="student" data-toggle="tab" class="btn">学生用户</a></li>
						</ul>
						<!-- <legend>基本信息</legend> -->
						<div class="form-group" style="margin-top: 20px;">
							<label for="username" class="col-sm-2 control-label">用户名:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名"> 
							</div>
							<div class="col-sm-3" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="usernameerror"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-2 control-label">密码:</label>
							<div class="col-sm-4">
								<input type="password" class="form-control" id="password" name="password" placeholder="请输入密码"> 
							</div>
							<div class="col-sm-3" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="passworderror"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="mobilPhone" class="col-sm-2 control-label">手机:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="mobilPhone" name="mobilPhone" placeholder="请输入手机号码"> 
							</div>
							<div class="col-sm-3" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="mobilPhoneerror"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="province" class="col-sm-2 control-label">地区:</label>
							<div class="col-sm-7">
							<select id="province" name="province" class="form-control js-example-data-array-selected" style="width:auto;float: left;">
		                        <option value="" selected="selected">请选择</option>
		                        <option value="11">北京市</option>
		                        <option value="12">天津市</option>
		                        <option value="13">河北省</option>
		                        <option value="14">山西省</option>
		                        <option value="15">内蒙古自治区</option>
		                        <option value="21">辽宁省</option>
		                        <option value="22">吉林省</option>
		                        <option value="23">黑龙江省</option>
		                        <option value="31">上海市</option>
		                        <option value="32">江苏省</option>
		                        <option value="33">浙江省</option>
		                        <option value="34">安徽省</option>
		                        <option value="35">福建省</option>
		                        <option value="36">江西省</option>
		                        <option value="37">山东省</option>
		                        <option value="41">河南省</option>
		                        <option value="42">湖北省</option>
		                        <option value="43">湖南省</option>
		                        <option value="44">广东省</option>
		                        <option value="45">广西壮族自治区</option>
		                        <option value="46">海南省</option>
		                        <option value="50">重庆市</option>
		                        <option value="51">四川省</option>
		                        <option value="52">贵州省</option>
		                        <option value="53">云南省</option>
		                        <option value="54">西藏自治区</option>
		                        <option value="61">陕西省</option>
		                        <option value="62">甘肃省</option>
		                        <option value="63">青海省</option>
		                        <option value="64">宁夏回族自治区</option>
		                        <option value="65">新疆维吾尔自治区</option>
		                    </select>
		                    <select id="city" name="city" class="form-control js-example-data-array-selected" style="width:112px; float: left;">
								<option value="" selected="selected">请选择</option>
							</select>
							<select id="area" name="user.areaCode" class="form-control js-example-data-array-selected" style="width:auto; float: left;">
								<option value="" selected="selected">请选择</option>
							</select>
							<span class="help-block" id="areaerror">地区不能为空！</span>
							</div>
						</div>
						<div class="form-group student">
							<label class="col-sm-2 control-label">院校:</label>
							<div class="col-sm-5">
								<select id="school" name="user.schoolCode" class="form-control js-example-data-array-selected" style="width: 50%;float: left">
									<option value="" selected="selected">请选择院校</option>
								</select>
								<span class="help-block" id="schoolerror">院校不能为空！</span>
							</div>
						</div>
						<div class="form-group student">
							<label for="dormitory" class="col-sm-2 control-label">宿舍地址:</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="dormitory" name="dormitory" placeholder="请输入宿舍地址"> 
							</div>
							<div class="col-sm-3" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="dormitoryerror"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="address" class="col-sm-2 control-label">街道地址:</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="address" name="address" placeholder="请输入街道地址"> 
							</div>
							<div class="col-sm-3" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="addresserror"></span>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
							     <button type="button" id="btn-submit" class="btn btn-info col-sm-offset-1" >保存</button>
							     <button type="reset" class="btn reset" ></button>
							     <button type="button" class="btn btn-default col-sm-offset-1" >返回</button>
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
				      	 表单提交成功，即将跳转到登录页面&hellip;
				    </div>
			    </div>
			  </div>
			</div>
		</div>
	</div>
</body>
</html>