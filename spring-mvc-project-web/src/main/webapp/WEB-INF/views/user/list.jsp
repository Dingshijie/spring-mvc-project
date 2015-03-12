<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<link rel="stylesheet" href="${initParam.resourceRoot}/css/user/list.css">
<script type="text/javascript" src="${initParam.resourceRoot}/js/user/list.js"></script>
</head>
<body>
	<nav class="navbar" role="navigation">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-9">
				<form class="form-inline" role="form">
					<div class="form-group">
						<select id="province" name="province" class="form-control" style="width: auto; float: left;">
							<option value="" selected="selected">全部</option>
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
						<select id="city" class="form-control" style="width:auto; float: left;">
							<option value="" selected="selected">全部</option>
						</select>
						<select id="area" class="form-control" style="width:auto; float: left;">
							<option value="" selected="selected">全部</option>
						</select>
						<select class="form-control" id="role">
							<option value="" selected="selected">全部</option>
							<option value="MANAGER">管理员</option>
							<option value="BUSSINESS">商家</option>
							<option value="VISITOR">游客</option>
						</select>
					</div>
					<div class="form-group">
						<input type="text" placeholder="请输入查询的关键字或代码" class="form-control" style="width: 200px;" id="keyword">
					</div>
					<button type="button" class="btn btn-success" id='keywordSearch'>查询</button>
					<button type="reset" class="btn reset" ></button>
					<span style="color: red; font-size: 15px;" id="error"></span>
				</form>

			</div>
			<div class=" col-md-3 pubtool">
				<a href="javascript:void(0)" id="download" title="导出到处所有用户到excel文件" class="btn btn-info disabled pull-right"> <span class="glyphicon glyphicon-circle-arrow-down"></span> 导出到Excel
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
								<option value="10">10</option>
								<option value="15" selected="selected">15</option>
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
	</nav>
</body>
</html>