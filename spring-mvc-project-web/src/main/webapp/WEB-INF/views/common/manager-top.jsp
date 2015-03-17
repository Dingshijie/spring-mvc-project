<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="webRoot" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<link rel="stylesheet" href="${initParam.resourceRoot}/css/common/manager-top.css"/>
   <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
         <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Project name</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li><a class="tag-a" href="${webRoot}/login.html">登陆</a></li>
            <li><a class="tag-a" href="${webRoot}/user/register.html">注册</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><a class="tag-a" href="${webRoot}/index.html">首页</a></li>
            <li><a class="tag-a" href="#">我的收藏</a></li>
            <li class="dropdown">
            	<a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">商家中心<span class="caret"></span></a>
            	<ul class="dropdown-menu" role="menu" style="min-width: 80px;">
	                <li><a href="#">我发布的</a></li>
	                <li><a href="#">我想合作</a></li>
             	</ul>
            </li>
            <li class="dropdown">
            	<a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">联系我们<span class="caret"></span></a>
            	<ul class="dropdown-menu" role="menu" style="min-width: 80px;">
	                <li><a href="#">常见问题</a></li>
	                <li><a href="#">投诉建议</a></li>
	                <li class="divider"></li>
	                <li><a href="#">联系客服</a></li>
             	</ul>
            </li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>