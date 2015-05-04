<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="webRoot" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>校园E+ 用户详情</title>
	<!-- 新 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet" href="${initParam.resourceRoot}/lib/bootstrap/css/bootstrap.min.css">
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/bootstrap/js/bootstrap.min.js"></script>
	<!-- select2 css和js -->
	<link rel="stylesheet" href="${initParam.resourceRoot}/lib/select2/css/select2.min.css" />
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/select2/js/select2.min.js"></script>
	
	<!-- 页面的和css 文件必须放到bootstrap。min.cs之后-->
	<script type="text/javascript" src="${initParam.resourceRoot}/js/user/detail.js"></script>
	<link rel="stylesheet" href="${initParam.resourceRoot}/css/user/detail.css" />
</head>
<body>
	
	<c:import url="../common/manager-top.jsp"></c:import>
	<c:import url="../common/manager-left.jsp"><c:param name="active">usermanager</c:param><c:param name="subactive">userdetail</c:param></c:import>
	
	<div class="container-fluid col-md-10 col-md-offset-2" style="padding: 20px;">
		<div class="show-grid">
			<div id="mainList">
					<fieldset>
						<div class="alert text-center"></div>
						<div class="row">
						<div class="col-xs-6 col-md-2">
							 <a href="#" class="thumbnail">
							  <img src="${initParam.resourceRoot}/img/loading.gif" alt="...">
							 </a>
						</div>
						<h3>
							<span>${user.username }</span>
							<small>
							<c:if test="${user.authentication =='AUDITD' }"><span>已认证</span></c:if>
							<c:if test="${user.authentication =='AUDIT' }"><span>未认证</span></c:if>
							</small>
							<small>|
								<c:if test="${user.role =='ADMIN' }"><span>超级管理员</span></c:if>
								<c:if test="${user.role =='MANAGER' }"><span>管理员</span></c:if>
								<c:if test="${user.role =='BUSSINESS' }"><span>商家</span></c:if>
								<c:if test="${user.role =='STUDENT' }"><span>学生</span></c:if>
							</small>
							<shiro:hasRole name="ADMIN">
								<c:if test="${user.role =='STUDENT' }">
									<button type="button" class="btn btn-xs btn-info btn-bussiness" value="">设置为商家用户</button>
								</c:if>
								<c:if test="${user.role !='ADMIN' }">
									<button type="button" class="btn btn-xs btn-warning btn-resetpassword" value="">重置密码</button>
									<c:if test="${user.enable=='1' }">
									<button type="button" class="btn btn-xs btn-danger btn-stopaccount" value="">强制停用该账号</button>
									</c:if>
									<c:if test="${user.enable=='0' }">
									<button type="button" class="btn btn-xs btn-info btn-startaccount" value="">启用该账号</button>
									</c:if>
								</c:if>
								<c:if test="${user.authentication =='AUDITD' }">
								<button type="button" class="btn btn-xs btn-danger btn-audit" value="">撤销认证</button>
								</c:if>
								<c:if test="${user.authentication =='AUDIT' }">
								<button type="button" class="btn btn-xs btn-success btn-auditd" value="">认证通过</button>
								</c:if>
							</shiro:hasRole>
							<shiro:hasRole name="MANAGER">
								<c:if test="${user.role =='STUDENT' }">
									<button type="button" class="btn btn-xs btn-info btn-bussiness" value="">设置为商家用户</button>
								</c:if>
								<c:if test="${user.role =='STUDENT' || user.role =='BUSSINESS' }">
									<button type="button" class="btn btn-xs btn-warning btn-resetpassword" value="">重置密码</button>
									<c:if test="${user.enable=='1' }">
									<button type="button" class="btn btn-xs btn-danger btn-stopaccount" value="">强制停用该账号</button>
									</c:if>
									<c:if test="${user.enable=='0' }">
									<button type="button" class="btn btn-xs btn-info btn-startaccount" value="">启用该账号</button>
									</c:if>
									<c:if test="${user.authentication =='AUDITD' }">
									<button type="button" class="btn btn-xs btn-danger btn-audit" value="">撤销认证</button>
									</c:if>
									<c:if test="${user.authentication =='AUDIT' }">
									<button type="button" class="btn btn-xs btn-success btn-auditd" value="">认证通过</button>
									</c:if>
								</c:if>
							</shiro:hasRole>
							<input type="hidden" id="id" value="${user.id }">
						</h3>
						</div>
						<ul class="nav nav-tabs" role="tablist" id="myTab">
							<li role="presentation" class="active"><a href="#baseinfo" aria-controls="baseinfo" data-toggle="tab" class="btn">基本信息</a></li>
							<li role="presentation"><a href="#contactinfo" aria-controls="contactinfo" data-toggle="tab" class="btn">认证信息</a></li>
						</ul>
						<div role="tabpanel" class="tab-pane active" id="baseinfo">
						<table class="table table-condensed" style="margin-bottom:0px;">
							<colgroup><col width="15%"></col><col width="35%"></col><col width="15%"></col><col width="35%"></col></colgroup>
							<tr>
								<th>姓名</th>
								<td><span data-key="realName">${user.realName }</span>
									<%-- <div class="value"><span data-key="realName">${user.realName }</span></div>
									<!-- 这里设置如果已经申请认证或者正在认证中不可以修改姓名和身份证号 -->
									<c:if test="${user.authentication =='NOT_APPLY' || user.authentication =='NOT_PASS'}">
									<div class="edit" style="display:none;"><input id="realName" class="form-control" name="realName" type="text" value="${user.realName }" style="width:140px;"/></div>
									<a href="#" title="点击修改"><span class="update glyphicon glyphicon-pencil"></span></a>
									<button style="display:none;" class="btn btn-info update-sure">确定</button><button style="display:none;" class="btn btn-link update-cancel">取消</button>
									<span class="help-block" id="realNameerror"></span>
									</c:if> --%>
								</td>
								<th>身份证号</th>
								<td><span data-key="idCardNum">${user.idCardNum }</span>
									<%-- <div class="value"><span data-key="idCardNum">${user.idCardNum }</span></div>
									<c:if test="${user.authentication =='NOT_APPLY' || user.authentication =='NOT_PASS'}">
									<div class="edit" style="display:none;"><input id="idCardNum" class="form-control" name="idCardNum" type="text" value="${user.idCardNum }" style="width:140px;"/></div>
									<a href="#" title="点击修改"><span class="update glyphicon glyphicon-pencil"></span></a>
									<button style="display:none;" class="btn btn-info update-sure">确定</button><button style="display:none;" class="btn btn-link update-cancel">取消</button>
									<span class="help-block" id="idCardNumerror"></span>
									</c:if> --%>
								</td>
							</tr>
							<tr>
								<th>手机</th>
								<td>
									<div class="value"><span data-key="mobilPhone">${user.mobilPhone }</span></div>
									<div class="edit" style="display:none;"><input id="mobilPhone" class="form-control" name="mobilPhone" type="text" value="${user.mobilPhone }" style="width:140px;"/></div>
									<a href="#" title="点击修改"><span class="update glyphicon glyphicon-pencil"></span></a>
									<button style="display:none;" class="btn btn-info update-sure">确定</button><button style="display:none;" class="btn btn-link update-cancel">取消</button>
									<span class="help-block" id="mobilPhoneerror"></span>
								</td>
								<th>固定电话</th>
								<td>
									<div class="value"><span data-key="telPhone">${user.telPhone }</span></div>
									<div class="edit" style="display:none;"><input id="telPhone" class="form-control" name="telPhone" type="text" value="${user.telPhone }" style="width:140px;"/></div>
									<a href="#" title="点击修改"><span class="update glyphicon glyphicon-pencil"></span></a>
									<button style="display:none;" class="btn btn-info update-sure">确定</button><button style="display:none;" class="btn btn-link update-cancel">取消</button>
									<span class="help-block" id="telPhoneerror"></span>
								</td>
							</tr>
							<tr>
								<th>E-mail</th>
								<td>
									<div class="value"><span data-key="email">${user.email }</span></div>
									<div class="edit" style="display:none;"><input id="email" class="form-control" name="email" type="text" value="${user.email }" style="width:140px;"/></div>
									<a href="#" title="点击修改"><span class="update glyphicon glyphicon-pencil"></span></a>
									<button style="display:none;" class="btn btn-info update-sure">确定</button><button style="display:none;" class="btn btn-link update-cancel">取消</button>
									<span class="help-block" id="emailerror"></span>
								</td>
								<th>所在地区</th>
								<td>
									<div class="value"><span data-key="areaName" data-code="${user.areaCode }">${user.areaName }</span></div>
									<div class="edit" style="display:none;"><input class="form-control" name="areaName" type="text" value="${user.areaName }" style="width:140px;"/></div>
									<a href="#" title="点击修改"><span id="areaName" class="update glyphicon glyphicon-pencil"></span></a>
									<button id="btn-area" style="display:none;" class="btn btn-link update-cancel">取消</button>
								</td>
							</tr>
							<tr>
								<c:if test="${user.role =='STUDENT' }">
								<th>所在学校</th>
								<td>
									<div class="value"><span data-key="schoolName" data-code="${user.schoolCode }">${user.schoolName }</span></div>
									<div class="edit" style="display:none;"><input class="form-control" name="schoolName" type="text" value="${user.schoolName }" style="width:140px;"/></div>
									<a href="#" title="点击修改"><span id="schoolName" class="update glyphicon glyphicon-pencil"></span></a>
									<button id="btn-school" style="display:none;" class="btn btn-link update-cancel">取消</button>
								</td>
								<th>学历层次</th>
								<td>
									<div class="value"><span data-key="eduLevelName" data-code="${user.eduLevel }">${user.eduLevelName }</span></div>
									<div class="edit" style="display:none;">
										<select id="eduLevel" name="eduLevel" class="form-control js-example-data-array-selected" style="width: 80px; float: left;">
											<option value="">请选择</option>
											<option value="1">研究生</option>
											<option value="2">本科</option>
											<option value="3">专科</option>
										</select>
									</div>
									<a href="#" title="点击修改"><span class="update glyphicon glyphicon-pencil"></span></a>
									<button style="display:none;" class="btn btn-info update-select">确定</button><button style="display:none;" class="btn btn-link update-cancel">取消</button>
									<span class="help-block" id="eduLevelerror"></span>
								</td>
								</c:if>
							</tr>
							<c:if test="${user.role =='STUDENT' }">
							<tr>
								<th>所学专业</th>
								<td colspan="3">
								<div class="value"><span data-key="eduPubName" data-code="${user.eduPubCode }">${user.eduPubName }</span></div>
									<div class="edit" style="display:none;">
										<select id="zydl" name="zydl" class="form-control js-example-data-array-selected" style="width: 150px; float: left;">
											<option value="">请选择</option>
										</select>
										<select id="zyzl" name="zyzl" class="form-control js-example-data-array-selected" style="width: 150px; float: left;">
											<option value="">请选择</option>
										</select>
										<select id="zyxl" name="eduPubCode" class="form-control js-example-data-array-selected" style="width: 150px; float: left;">
											<option value="">请选择</option>
										</select>
									</div>
									<a href="#" title="点击修改" id="eduPubName"><span class="update glyphicon glyphicon-pencil"></span></a>
									<button style="display:none;" class="btn btn-info update-select">确定</button><button style="display:none;" class="btn btn-link update-cancel">取消</button>
									<span class="help-block" id="eduPubNameerror"></span>
								</td>
							</tr>
							</c:if>
							<tr>
								<th>详细地址</th>
								<td>
									<div class="value"><span data-key="address">${user.address }</span></div>
									<div class="edit" style="display:none;"><input id="address" class="form-control" name="address" type="text" value="${user.address }" style="width:140px;"/></div>
									<a href="#" title="点击修改"><span class="update glyphicon glyphicon-pencil"></span></a>
									<button style="display:none;" class="btn btn-info update-sure">确定</button><button style="display:none;" class="btn btn-link update-cancel">取消</button>
									<span class="help-block" id="addresserror"></span>
								</td>
								<c:if test="${user.role =='BUSSINESS' }">
								<th>商品数</th>
								<td>${user.productsNum }</td>
								</c:if>
							</tr>
							<tr>
								<th>注册时间</th>
								<td><fmt:formatDate value="${user.registerDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<th>最后登录时间</th>
								<td><fmt:formatDate value="${user.lastLoginDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							</tr>
							<tr>
								<th>最后登录ip</th>
								<td>${user.ip }</td>
							</tr>
						</table>
						</div>
						<div role="tabpanel" class="tab-pane" id="contactinfo" style="display: none">
							<div class="row">
							 <div class="col-md-12">
							 	<table class="table table-condensed" style="margin-top:0px;">
							 		<colgroup><col width="30%"></col><col width="20%"></col><col width="10%"></col><col width="40%"></col></colgroup>
							 		<tr>
							 			<th>店铺名称</th>
							 			<td>${user.companyName }</td>
							 		</tr>
							 	</table>
							 </div>
							  <div class="col-md-2 col-md-offset-2">
							    <a href="#" class="thumbnail">
							      <img src="${initParam.resourceRoot}/img/loading.gif" alt="...">
							      <div class="caption">
							        <h6>头像</h6>
							      </div>
							    </a>
							  </div>
							   <div class="col-xs-6 col-md-2">
							    <a href="#" class="thumbnail">
							      <img src="${initParam.resourceRoot}/img/loading.gif" alt="...">
							      <div class="caption">
							        <h6>店铺图片</h6>
							      </div>
							    </a>
							  </div>
							   <div class="col-xs-6 col-md-2">
							    <a href="#" class="thumbnail">
							      <img src="${initParam.resourceRoot}/img/loading.gif" alt="...">
							      <div class="caption">
							        <h6>身份证正面</h6>
							      </div>
							    </a>
							  </div>
							   <div class="col-xs-6 col-md-2">
							    <a href="#" class="thumbnail">
							      <img src="${initParam.resourceRoot}/img/loading.gif" alt="...">
							      <div class="caption">
							        <h6>身份证反面</h6>
							      </div>
							    </a>
							  </div>
							</div>
						</div>
					</fieldset>
			</div>
		</div>	
	</div>

	<div id="myModal" class="modal fade bs-example-modal-md" tabindex="-1" role="dialog" aria-labelledby="myMiddleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close btn-close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改所在地区</h4>
				</div>
				<div class="modal-body">
					<div class="alert" role="alert"></div>
					<div class="form-group">
					<select id="province" name="province" class="form-control js-example-data-array-selected" style="width: 150px; float: left;" data-role="${user.role }">
						<option value="">请选择</option>
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
					<select id="city" name="city" class="form-control js-example-data-array-selected" style="width: 112px; float: left;">
						<option value="">请选择</option>
					</select> 
					<select id="area" name="areaCode" class="form-control js-example-data-array-selected" style="width: auto; float: left;">
						<option value="">请选择</option>
					</select>
					<span class="help-block" id="areaerror"></span>
					</div>
					<div class="form-group school" style="display: none">
						<select id="school" name="schoolCode" class="form-control js-example-data-array-selected" style="width: 50%;float: left;">
							<option value="">请选择院校</option>
						</select>
						<span class="help-block" id="schoolerror"></span>
					</div>
				</div>
				<div class="modal-footer">
			        <button type="button" class="btn btn-default btn-close" data-dismiss="modal">关闭</button>
			        <button type="button" class="btn btn-primary btn-save">保存</button>
			   	</div>
			</div>
		</div>
	</div>
	<div id="alertModal" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		 <div class="modal-dialog modal-sm">
			 <div class="modal-content">
				 <div class="modal-header">
				   <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				   <h4 class="modal-title" id="myModalLabel">提示</h4>
				 </div>
				 <div class="modal-body">
				 </div>
				 <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			   	 </div>
			  </div>
		 </div>
	</div>
</body>
</html>