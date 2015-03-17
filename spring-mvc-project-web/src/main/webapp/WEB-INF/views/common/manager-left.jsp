<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="webRoot" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<link rel="stylesheet" href="${initParam.resourceRoot}/css/common/manager-left.css"/>
<div class="col-md-3 sidebar">
	<div class="col-md-7 col-md-offset-5" style="padding-right: 0">
		<ul class="nav nav-sidebar">
			<li class="active" style="white-space:nowrap; display:inline-block;width: 100%"><a href="javascript:void(0)">用户管理</a>
				<ul class="nav col-md-10 col-md-offset-2">
					<li style="white-space:nowrap; display:inline;width: 100%"><a href="${webRoot}/user/list.html">用户列表</a></li>
					<li style="white-space:nowrap; display:inline;width: 100%"><a href="">Nav item again</a></li>
				</ul>
			</li>
			<li style="white-space:nowrap; display:inline-block;width: 100%">
				<a href="#">Reports</a>
			</li>
			<li style="white-space:nowrap; display:inline-block;width: 100%"><a href="#">Analytics</a></li>
			<li style="white-space:nowrap; display:inline-block;width: 100%"><a href="#">Export</a></li>
		</ul>
	</div>
</div>