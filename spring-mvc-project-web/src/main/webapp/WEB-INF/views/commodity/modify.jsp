<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="webRoot" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>校园E+ 编辑商品</title>
	<!-- 新 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet" href="${initParam.resourceRoot}/lib/bootstrap/css/bootstrap.min.css">
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/bootstrap/js/bootstrap.min.js"></script>
	<!-- iCheck css和JavaScript文件 -->
	<link href="${initParam.resourceRoot}/lib/iCheck/skins/square/blue.css" rel="stylesheet">
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/iCheck/js/icheck.min.js"></script>
	
	<link rel="stylesheet" href="${initParam.resourceRoot}/css/commodity/modify.css">
	<script type="text/javascript" src="${initParam.resourceRoot}/js/commodity/modify.js"></script>
</head>
<body>

	<c:import url="../common/manager-top.jsp"></c:import>
	<c:import url="../common/manager-left.jsp"><c:param name="active">commoditymanager</c:param><c:param name="subactive">modifycommodity</c:param></c:import>
	<div class="container-fluid col-md-10 col-md-offset-2" style="padding: 20px;">
		<div class="show-grid">
			<div id="mainList">
				<form class="form-horizontal col-md-offset-1 col-md-11" id="myForm" role="form" action="${webRoot }/commodity/modify" method="post" enctype="multipart/form-data">
					<c:if test="${not empty  errorMsg }">
				       <div class="alert alert-danger"><i class="iconfont"></i> ${errorMsg}</div>
				    </c:if>
					<fieldset>
						<legend>编辑商品   <small style="color: #999">以下为可编辑字段信息</small></legend>
						<input type="hidden" id="id" name="id" value="${commodityinfo.id }">
						<div class="form-group col-md-6" style="margin-top: 20px;">
							<label for="name" class="col-sm-3 control-label">商品名称:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="name" name="name" value="${commodityinfo.name }" placeholder="商品名称（必填）"> 
							</div>
								<span class="help-block" id="nameerror"></span>
						</div>
						<div class="form-group col-md-6" style="margin-top: 20px;">
							<label for="brand" class="col-sm-3 control-label">商品品牌:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="brand" name="brand" value="${commodityinfo.brand }" placeholder="商品品牌（选填）"> 
							</div>
							<div class="col-sm-3" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="branderror"></span>
							</div>
						</div>
						<div class="form-group col-md-6">
							<label for="typeCode" class="col-sm-3 control-label">商品型号:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="typeCode" name="typeCode" value="${commodityinfo.typeCode }" placeholder="商品型号（选填）"> 
							</div>
							<div class="col-sm-3" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="typeCodeerror"></span>
							</div>
						</div>
						<div class="form-group col-md-6">
							<label for="link" class="col-sm-3 control-label">商品介绍:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="link" name="link" value="${commodityinfo.link }" placeholder="商品链接（选填）"> 
							</div>
							<div class="col-sm-3" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="linkerror"></span>
							</div>
						</div>
						<div class="form-group col-md-6">
							<label for="price" class="col-sm-3 control-label">商品价格:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" style="width: 85%;display: inline-block;" id="price" name="price" value="${commodityinfo.price }" placeholder="价格（必填）">&nbsp;元
							</div>
							<span class="help-block" id="priceerror"></span>
						</div>
						<div class="form-group col-md-6">
							<label for="unit" class="col-sm-3 control-label">计量单位:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="unit" name="unit" value="${commodityinfo.unit }" placeholder="计量单位（必填）">
							</div>
							<span class="help-block" id="uniterror" style="margin-top: 5px;"></span>
						</div>
						<div class="form-group col-md-9">
							<input type="hidden" name="goods" value="">
							<label for="goods" class="col-sm-2 control-label">商品清单:</label>
							<div class="goodsList col-sm-10">
							 	<c:forEach items="${commodityinfo.goodsList }" var="good">
							 		<p><input type="text" class="good" value="${good }" style="width:${(fn:length(good)+1)*14}px"> <a href="javascript:void(0);" title="点击删除"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a><a href="javascript:void(0);" title="点击编辑"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a><input type="button" class="btn btn-info save" value="保存"> <input type="button" class="btn btn-default cancel" value="取消"></p>
							 	</c:forEach>
							</div>
							<div class="goodsAdd col-sm-10" >
								<input type="text" class="form-control" style="width:50%;" id="goods" placeholder="商品清单"> <input type="button" class="btn btn-info save" value="保存"> <input type="button" class="btn btn-default cancel" value="取消">
							</div>
							<div class="goodsPlus  col-md-offset-2 col-sm-1">
								<a href="javascript:void(0);" title="点击添加"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></a>
							</div>
							<span class="help-block" id="goodserror"></span>
						</div>
						
						<div class="form-group col-md-9">
							<label for="description" class="col-sm-2 control-label">描述:</label>
							<div class="col-sm-6">
								<textarea id="description" name="description" placeholder="在此处填写描述" rows="5" cols="10">${commodityinfo.description }</textarea>
							</div>
							<span class="help-block" id="descriptionerror"></span>
						</div>
						<div class="form-group col-md-9">
							<label for="picture" class="col-sm-2 control-label">商品图片:</label>
							<div class="col-sm-4">
								<input type="file" id="file" name="file" placeholder="商品图片">
							</div>
						</div>
						<div class="form-group col-md-9">
							<div class="localImag col-sm-9 col-sm-offset-2">
								<img id="imgpreview" src="/resources${commodityinfo.picture }" alt="" width="200px" height="180px" class="img-thumbnail">		
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-3 col-sm-6">

							     <button type="submit" id="btn-submit"  class="btn btn-info col-sm-offset-1" >保存</button>
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
