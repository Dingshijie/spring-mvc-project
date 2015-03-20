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

	<!-- iCheck css和JavaScript文件 -->
	<link href="${initParam.resourceRoot}/lib/iCheck/skins/square/blue.css" rel="stylesheet">
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/iCheck/js/icheck.min.js"></script>
	<!-- 页面的和css 文件必须放到bootstrap。min.cs之后-->
	<script type="text/javascript" src="${initParam.resourceRoot}/js/school/add.js"></script>
	<link rel="stylesheet" href="${initParam.resourceRoot}/css/school/add.css" />

</head>
<body>

	<c:import url="../common/manager-top.jsp"></c:import>
	<c:import url="../common/manager-left.jsp"><c:param name="active">schoolmanager</c:param><c:param name="subactive">addschool</c:param></c:import>
	
	<div class="container-fluid col-md-10 col-md-offset-2" style="padding: 20px;">
		<div class="show-grid">
			<div id="mainList">
				<form class="form-horizontal col-md-offset-1 col-md-10" id="myForm" role="form">
					<fieldset>
						<legend>添加学校</legend>
						<div class="form-group" style="margin-top: 20px;">
							<label for="username" class="col-sm-2 control-label">院校名称:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="name" name="name" placeholder="请输入院校名称"> 
							</div>
							<div class="col-sm-3" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="nameerror"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="code" class="col-sm-2 control-label">院校代码:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="code" name="code" placeholder="请输入院校代码"> 
							</div>
							<div class="col-sm-3" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="codeerror"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="beUnderName" class="col-sm-2 control-label">隶属单位名称:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="beUnderName" name="beUnderName" placeholder="请输入隶属单位名称"> 
							</div>
							<div class="col-sm-3" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="beUnderNameerror"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="type" class="col-sm-2 control-label">学校性质:</label>
							<div class="col-sm-8">
							<select id="type" name="type" class="form-control  js-example-data-array-selected" style="width:auto;float: left;">
		                        <option value="" selected="selected">请选择</option>
		                        <option value="01">综合大学</option>
		                        <option value="02">理工院校</option>
		                        <option value="03">农业院校</option>
		                        <option value="04">林业院校</option>
		                        <option value="05">医药院校</option>
		                        <option value="06">师范院校</option>
		                        <option value="07">语文院校</option>
		                        <option value="08">财经院校</option>
		                        <option value="09">政法院校</option>
		                        <option value="10">体育院校</option>
		                        <option value="11">艺术院校</option>
		                        <option value="12">民族院校</option>
		                        <option value="14">社会科学研究机构</option>
		                        <option value="15">自然科学研究机构</option>
		                        <option value="17">党政院校</option>
		                    </select>
							</div>
							<div class="col-sm-2" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="typeerror"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="buildType" class="col-sm-2 control-label">办学类型:</label>
							<div class="col-sm-8">
							<select id="buildType" name="buildType" class="form-control  js-example-data-array-selected" style="width:auto;float: left;">
		                        <option value="" selected="selected">请选择</option>
		                        <option value="1">大学</option>
		                        <option value="2">学院</option>
		                        <option value="3">独立学院</option>
		                        <option value="4">高专</option>
		                        <option value="5">高职</option>
		                        <option value="6">民办院校</option>
		                        <option value="9">科研机构</option>
		                    </select>
							</div>
							<div class="col-sm-2" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="typeerror"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="province" class="col-sm-2 control-label">所在地:</label>
							<div class="col-sm-10">
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
						<div class="form-group">
							<label for="address" class="col-sm-2 control-label">院校类别:</label>
							<div class="col-sm-10">
								<input type="checkbox" name="tags" value="1"> 211
								<input type="checkbox" name="tags" value="2"> 985
								<input type="checkbox" name="tags" value="4"> 独立学院
								<input type="checkbox" name="tags" value="8"> 新增本科
								<input type="checkbox" name="tags" value="16"> 示范高职
								<input type="checkbox" name="tags" value="32"> 科研机构
								<input type="checkbox" name="tags" value="64"> 民办院校
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