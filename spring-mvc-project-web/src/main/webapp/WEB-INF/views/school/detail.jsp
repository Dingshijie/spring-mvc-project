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
	<title>校园E+ 学校详情</title>
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
	<script type="text/javascript" src="${initParam.resourceRoot}/js/school/detail.js"></script>
	<link rel="stylesheet" href="${initParam.resourceRoot}/css/school/detail.css" />
</head>
<body>
	<c:import url="../common/manager-top.jsp"></c:import>
	<c:import url="../common/manager-left.jsp"><c:param name="active">schoolmanager</c:param><c:param name="subactive">schooldetail</c:param></c:import>
	
	<div class="container-fluid col-md-10 col-md-offset-2" style="padding: 20px;">
		<div class="show-grid">
			<div id="mainList">
				<form class="form-horizontal col-md-offset-1 col-md-10" id="myForm" role="form">
					<fieldset>
						<legend>学校详情</legend>
						<input type="hidden" id="id" value="${school.id }"> 
						<div class="form-group" style="margin-top: 20px;">
							<label for="username" class="col-sm-2 control-label">院校名称:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="name" name="name" value="${school.name }" placeholder="请输入院校名称"> 
							</div>
							<div class="col-sm-3" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="nameerror"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="code" class="col-sm-2 control-label">院校代码:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="code" name="code" disabled="disabled" value="${school.code }" placeholder="请输入院校代码"> 
							</div>
							<div class="col-sm-3" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="codeerror"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="beUnderName" class="col-sm-2 control-label">隶属单位名称:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="beUnderName" name="beUnderName" value="${school.beUnderName }" placeholder="请输入隶属单位名称"> 
							</div>
							<div class="col-sm-3" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="beUnderNameerror"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="type" class="col-sm-2 control-label">学校性质:</label>
							<div class="col-sm-4">
							<select id="type" name="type" class="form-control  js-example-data-array-selected" style="width:100%;float: left;">
		                        <option value="" >请选择</option>
		                        <option value="01" <c:if test="${school.type=='01' }">selected="selected"</c:if> >综合大学</option>
		                        <option value="02" <c:if test="${school.type=='02' }">selected="selected"</c:if> >理工院校</option>
		                        <option value="03" <c:if test="${school.type=='03' }">selected="selected"</c:if> >农业院校</option>
		                        <option value="04" <c:if test="${school.type=='04' }">selected="selected"</c:if> >林业院校</option>
		                        <option value="05" <c:if test="${school.type=='05' }">selected="selected"</c:if> >医药院校</option>
		                        <option value="06" <c:if test="${school.type=='06' }">selected="selected"</c:if> >师范院校</option>
		                        <option value="07" <c:if test="${school.type=='07' }">selected="selected"</c:if> >语文院校</option>
		                        <option value="08" <c:if test="${school.type=='08' }">selected="selected"</c:if> >财经院校</option>
		                        <option value="09" <c:if test="${school.type=='09' }">selected="selected"</c:if> >政法院校</option>
		                        <option value="10" <c:if test="${school.type=='10' }">selected="selected"</c:if> >体育院校</option>
		                        <option value="11" <c:if test="${school.type=='11' }">selected="selected"</c:if> >艺术院校</option>
		                        <option value="12" <c:if test="${school.type=='12' }">selected="selected"</c:if> >民族院校</option>
		                        <option value="14" <c:if test="${school.type=='14' }">selected="selected"</c:if> >社会科学研究机构</option>
		                        <option value="15" <c:if test="${school.type=='15' }">selected="selected"</c:if> >自然科学研究机构</option>
		                        <option value="17" <c:if test="${school.type=='17' }">selected="selected"</c:if> >党政院校</option>
		                    </select>
							</div>
							<div class="col-sm-3" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="typeerror"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="buildType" class="col-sm-2 control-label">办学类型:</label>
							<div class="col-sm-4">
							<select id="buildType" name="buildType" class="form-control  js-example-data-array-selected" style="width:100%;float: left;">
		                        <option value="" selected="selected">请选择</option>
		                        <option value="1" <c:if test="${school.buildType=='1' }">selected="selected"</c:if> >大学</option>
		                        <option value="2" <c:if test="${school.buildType=='2' }">selected="selected"</c:if> >学院</option>
		                        <option value="3" <c:if test="${school.buildType=='3' }">selected="selected"</c:if> >独立学院</option>
		                        <option value="4" <c:if test="${school.buildType=='4' }">selected="selected"</c:if> >高专</option>
		                        <option value="5" <c:if test="${school.buildType=='5' }">selected="selected"</c:if> >高职</option>
		                        <option value="6" <c:if test="${school.buildType=='6' }">selected="selected"</c:if> >民办院校</option>
		                        <option value="9" <c:if test="${school.buildType=='9' }">selected="selected"</c:if> >科研机构</option>
		                    </select>
							</div>
							<div class="col-sm-3" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="buildTypeerror"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="province" class="col-sm-2 control-label">所在地:</label>
							<div class="col-sm-4">
							<select id="province" name="province" class="form-control js-example-data-array-selected" style="width:auto;float: left;">
		                        <option value="" selected="selected">请选择</option>
		                        <option value="11" <c:if test="${school.areaCode=='11' }">selected="selected"</c:if> >北京市</option>
		                        <option value="12" <c:if test="${school.areaCode=='12' }">selected="selected"</c:if> >天津市</option>
		                        <option value="13" <c:if test="${school.areaCode=='13' }">selected="selected"</c:if> >河北省</option>
		                        <option value="14" <c:if test="${school.areaCode=='14' }">selected="selected"</c:if> >山西省</option>
		                        <option value="15" <c:if test="${school.areaCode=='15' }">selected="selected"</c:if> >内蒙古自治区</option>
		                        <option value="21" <c:if test="${school.areaCode=='21' }">selected="selected"</c:if> >辽宁省</option>
		                        <option value="22" <c:if test="${school.areaCode=='22' }">selected="selected"</c:if> >吉林省</option>
		                        <option value="23" <c:if test="${school.areaCode=='23' }">selected="selected"</c:if> >黑龙江省</option>
		                        <option value="31" <c:if test="${school.areaCode=='31' }">selected="selected"</c:if> >上海市</option>
		                        <option value="32" <c:if test="${school.areaCode=='32' }">selected="selected"</c:if> >江苏省</option>
		                        <option value="33" <c:if test="${school.areaCode=='33' }">selected="selected"</c:if> >浙江省</option>
		                        <option value="34" <c:if test="${school.areaCode=='34' }">selected="selected"</c:if> >安徽省</option>
		                        <option value="35" <c:if test="${school.areaCode=='35' }">selected="selected"</c:if> >福建省</option>
		                        <option value="36" <c:if test="${school.areaCode=='36' }">selected="selected"</c:if> >江西省</option>
		                        <option value="37" <c:if test="${school.areaCode=='37' }">selected="selected"</c:if> >山东省</option>
		                        <option value="41" <c:if test="${school.areaCode=='41' }">selected="selected"</c:if> >河南省</option>
		                        <option value="42" <c:if test="${school.areaCode=='42' }">selected="selected"</c:if> >湖北省</option>
		                        <option value="43" <c:if test="${school.areaCode=='43' }">selected="selected"</c:if> >湖南省</option>
		                        <option value="44" <c:if test="${school.areaCode=='44' }">selected="selected"</c:if> >广东省</option>
		                        <option value="45" <c:if test="${school.areaCode=='45' }">selected="selected"</c:if> >广西壮族自治区</option>
		                        <option value="46" <c:if test="${school.areaCode=='46' }">selected="selected"</c:if> >海南省</option>
		                        <option value="50" <c:if test="${school.areaCode=='50' }">selected="selected"</c:if> >重庆市</option>
		                        <option value="51" <c:if test="${school.areaCode=='51' }">selected="selected"</c:if> >四川省</option>
		                        <option value="52" <c:if test="${school.areaCode=='52' }">selected="selected"</c:if> >贵州省</option>
		                        <option value="53" <c:if test="${school.areaCode=='53' }">selected="selected"</c:if> >云南省</option>
		                        <option value="54" <c:if test="${school.areaCode=='54' }">selected="selected"</c:if> >西藏自治区</option>
		                        <option value="61" <c:if test="${school.areaCode=='61' }">selected="selected"</c:if> >陕西省</option>
		                        <option value="62" <c:if test="${school.areaCode=='62' }">selected="selected"</c:if> >甘肃省</option>
		                        <option value="63" <c:if test="${school.areaCode=='63' }">selected="selected"</c:if> >青海省</option>
		                        <option value="64" <c:if test="${school.areaCode=='64' }">selected="selected"</c:if> >宁夏回族自治区</option>
		                        <option value="65" <c:if test="${school.areaCode=='65' }">selected="selected"</c:if> >新疆维吾尔自治区</option>
		                    </select>
							</div>
							<div class="col-sm-3" style="margin-top: 5px;padding-left: 0px;margin-left: 0px;">
								<span class="help-block" id="provinceerror"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="address" class="col-sm-2 control-label">院校类别:</label>
							<div class="col-sm-10">
								<input type="checkbox" name="tags" value="1" <c:if test="${school.is211==true }">checked</c:if> > 211
								<input type="checkbox" name="tags" value="2" <c:if test="${school.is985==true }">checked</c:if> > 985
								<input type="checkbox" name="tags" value="4" <c:if test="${school.isIndependent==true }">checked</c:if> > 独立学院
								<input type="checkbox" name="tags" value="8" <c:if test="${school.isNew==true }">checked</c:if> > 新增本科
								<input type="checkbox" name="tags" value="16" <c:if test="${school.isModelVocational==true }">checked</c:if>> 示范高职
								<input type="checkbox" name="tags" value="32" <c:if test="${school.isResearchInst==true }">checked</c:if>> 科研机构
								<input type="checkbox" name="tags" value="64" <c:if test="${school.isPrivate==true }">checked</c:if>> 民办院校
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