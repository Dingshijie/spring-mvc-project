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
	<title>校园E+ 添加商品</title>
	<!-- 新 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet" href="${initParam.resourceRoot}/lib/bootstrap/css/bootstrap.min.css">
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/bootstrap/js/bootstrap.min.js"></script>
	<!-- iCheck css和JavaScript文件 -->
	<link href="${initParam.resourceRoot}/lib/iCheck/skins/square/blue.css" rel="stylesheet">
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/iCheck/js/icheck.min.js"></script>
	
	<link rel="stylesheet" href="${initParam.resourceRoot}/css/commodity/add.css">
	<script type="text/javascript" src="${initParam.resourceRoot}/js/commodity/add.js"></script>
</head>
<body>

	<c:import url="../common/manager-top.jsp"></c:import>
	<c:import url="../common/manager-left.jsp"><c:param name="active">commoditymanager</c:param><c:param name="subactive">addcommodity</c:param></c:import>
	<div class="container-fluid col-md-10 col-md-offset-2" style="padding: 20px;">
		<div class="show-grid">
			<div id="mainList">
				<form class="form-horizontal col-md-offset-1 col-md-11" id="myForm" role="form" action="${webRoot }/commodity/add" method="post" enctype="multipart/form-data" >
					<fieldset>
						<legend>添加商品</legend>
						<div class="form-group col-md-6" style="margin-top: 20px;">
							<label for="name" class="col-sm-3 control-label">商品名称:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="name" name="name" placeholder="商品名称"> 
							</div>
							<div class="col-sm-3" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="nameerror"></span>
							</div>
						</div>
						<div class="form-group col-md-6" style="margin-top: 20px;">
							<label for="brand" class="col-sm-3 control-label">商品品牌:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="brand" name="brand" placeholder="商品品牌(选填)"> 
							</div>
							<div class="col-sm-3" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="branderror"></span>
							</div>
						</div>
						<div class="form-group col-md-9">
							<label for="category" class="col-sm-2 control-label">所属分类:</label>
							<div class="col-sm-8">
								<select id="categoryCode" name="categoryCode" class="form-control" style="width: 45%; float: left;">
									<option value="" selected="selected">所有类别</option>
									<option value="11">11 女装</option>
									<option value="12">12 男装</option>
									<option value="13">13 鞋靴箱包</option>
									<option value="14">14 运动户外</option>
									<option value="15">15 手机数码</option>
									<option value="16">16 美妆配饰</option>
									<option value="17">17 生活用品</option>
									<option value="18">18 学习用品</option>
									<option value="91">91 其他</option>
								</select>
								<select id="category" name="category" class="form-control" style="width: 45%; float: left;margin-left: 2%">
									<option value="" selected="selected">所有类别</option>
								</select>
							</div>
							<div class="col-sm-2" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="categoryerror"></span>
							</div>
						</div>
						<div class="form-group col-md-6">
							<label for="typeCode" class="col-sm-3 control-label">商品型号:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="typeCode" name="typeCode" placeholder="商品型号(选填)"> 
							</div>
							<div class="col-sm-3" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="typeCodeerror"></span>
							</div>
						</div>
						<div class="form-group col-md-6">
							<label for="link" class="col-sm-3 control-label">商品链接:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="link" name="link" placeholder="商品链接(选填)"> 
							</div>
							<div class="col-sm-3" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="linkerror"></span>
							</div>
						</div>
						<div class="form-group col-md-6">
							<label for="price" class="col-sm-3 control-label">商品价格:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" style="width: 90%;display: inline-block;" id="price" name="price" placeholder="价格">&nbsp;元
							</div>
							<div class="col-sm-3" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="priceerror"></span>
							</div>
						</div>
						<div class="form-group col-md-6">
							<label for="unit" class="col-sm-3 control-label">计量单位:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="unit" name="unit" placeholder="计量单位">
							</div>
							<div class="col-sm-3" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="uniterror"></span>
							</div>
						</div>
						<div class="form-group col-md-6">
							<label for="used" class="col-sm-3 control-label">是否二手:</label>
							<div class="col-sm-6 control-label">
								<div class="col-sm-5 ">
									<input name="used" type="radio" value="1" >是
								</div>
								<div class="col-sm-5">
									<input name="used" type="radio" value="0" checked>否
								</div>
							</div>
						</div>
						<div class="form-group col-md-10">
							<label for="newCondition" class="col-sm-2 control-label">新旧程度:</label>
							<div class="col-sm-10" style="padding-top: 7px;">
								<input name="newCondition" type="radio" value="全新" checked>全新<span style="margin-right: 5px;"></span>
								<input name="newCondition" type="radio" value="九成新">九成新<span style="margin-right: 5px;"></span>
								<input name="newCondition" type="radio" value="八成新">八成新<span style="margin-right: 5px;"></span>
								<input name="newCondition" type="radio" value="六成新">六成新<span style="margin-right: 5px;"></span>
								<input name="newCondition" type="radio" value="六成以下">六成以下
							</div>
						</div>
						<div class="form-group col-md-9">
							<input type="hidden" name="goods" value="">
							<label for="goods" class="col-sm-2 control-label">商品清单:</label>
							<div class="goodsList col-sm-10">
							 	
							</div>
							<div class="goodsAdd col-sm-10" >
								<input type="text" class="form-control" style="width:50%;" id="goods" placeholder="商品清单"> <input type="button" class="btn btn-info save" value="保存"> <input type="button" class="btn btn-default cancel" value="取消">
							</div>
							<div class="goodsPlus col-sm-1">
								<a href="javascript:void(0);" title="点击添加"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></a>
							</div>
						</div>
						
						<div class="form-group col-md-9">
							<label for="description" class="col-sm-2 control-label">描述:</label>
							<div class="col-sm-6">
								<textarea id="description" name="description" placeholder="在此处填写描述" rows="5" cols="10"></textarea>
							</div>
							<div class="col-sm-3" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="descriptionerror"></span>
							</div>
						</div>
						<div class="form-group col-md-9">
							<label for="picture" class="col-sm-2 control-label">商品图片:</label>
							<div class="col-sm-4">
								<input type="file" id="file" name="file" placeholder="商品图片">
							</div>
						</div>
						<div class="form-group col-md-9">
							<div class="localImag col-sm-9 col-sm-offset-2">
								<img id="imgpreview" src="" alt="" width="200px" style="display: none" height="180px" class="img-thumbnail">		
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-3 col-sm-6">
							     <button type="submit" id="btn-submit" class="btn btn-info col-sm-offset-1" >保存</button>
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
