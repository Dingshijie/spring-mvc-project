<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="webRoot" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<link rel="stylesheet" href="${initParam.resourceRoot}/css/common/manager-left.css"/>
<div class="col-md-2 sidebar">
	<div class="col-md-7 col-md-offset-5" style="padding-right: 0">
		<ul class="nav nav-sidebar">
			<li <c:if test="${param.active=='usermanager'}">class="active"</c:if> style="white-space:nowrap; display:inline-block;width: 100%"><a href="${webRoot}/user/list.html">用户管理</a>
				<ul class="nav nav-sidebar-custom col-md-10 col-md-offset-2">
					<li <c:if test="${param.subactive=='userlist'}">class="active"</c:if> style="white-space:nowrap; display:inline;width: 100%"><a href="${webRoot}/user/list.html">用户列表</a></li>
					<li <c:if test="${param.subactive=='adduser'}">class="active"</c:if> style="white-space:nowrap; display:inline;width: 100%"><a href="${webRoot}/user/add.html">添加用户</a></li>
				</ul>
			</li>
			<li <c:if test="${param.active=='categorymanager'}">class="active"</c:if> style="white-space:nowrap; display:inline-block;width: 100%"><a href="${webRoot}/category/category.html">类别管理</a>
				<ul class="nav nav-sidebar-custom col-md-10 col-md-offset-2">
					<li <c:if test="${param.subactive=='categorylist'}">class="active"</c:if> style="white-space:nowrap; display:inline;width: 100%"><a href="${webRoot}/category/category.html">类别列表</a></li>
					<li <c:if test="${param.subactive=='addcategory'}">class="active"</c:if> style="white-space:nowrap; display:inline;width: 100%"><a href="${webRoot}/category/add.html">添加类别</a></li>
				</ul>
			</li>
			<li <c:if test="${param.active=='areamanager'}">class="active"</c:if> style="white-space:nowrap; display:inline-block;width: 100%"><a href="${webRoot}/area/area.html">地区管理</a>
				<ul class="nav nav-sidebar-custom col-md-10 col-md-offset-2">
					<li <c:if test="${param.subactive=='arealist'}">class="active"</c:if> style="white-space:nowrap; display:inline;width: 100%"><a href="${webRoot}/area/area.html">地区列表</a></li>
					<li <c:if test="${param.subactive=='addarea'}">class="active"</c:if> style="white-space:nowrap; display:inline;width: 100%"><a href="${webRoot}/area/add.html">添加地区</a></li>
				</ul>
			</li>
			<li style="white-space:nowrap; display:inline-block;width: 100%"><a href="#">Analytics</a></li>
			<li style="white-space:nowrap; display:inline-block;width: 100%"><a href="#">Export</a></li>
		</ul>
	</div>
</div>