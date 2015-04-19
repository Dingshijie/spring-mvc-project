<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="webRoot" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<link rel="stylesheet" href="${initParam.resourceRoot}/css/common/top.css"/>
<script type="text/javascript" src="${initParam.resourceRoot}/js/common/top.js"></script>
	<!-- Fixed navbar -->
    <nav class="navbar navbar-default navbar-fixed-top">
	    
      <div class="container">
         <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" style="color: #337ab7" href="${webRoot}/index.html" title="点击返回首页">校园E+</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
          	<shiro:guest>
	            <li><a class="tag-a" href="${webRoot}/login.html">登陆</a></li>
	            <li><a class="tag-a" href="${webRoot}/register.html">注册</a></li>
            </shiro:guest>
            <shiro:user>
            	<li>
            	<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><shiro:principal></shiro:principal><span class="caret"></span></a>
            	<ul class="dropdown-menu" role="menu" style="min-width: 80px;">
	                <li><a href="${webRoot}/logout.html">退出</a></li>
             	</ul>
            	</li>
            	<li>
            	 <a class="tag-a" href="#">&nbsp;</a>
            	</li>
            </shiro:user>
          </ul>
          <form class="navbar-form navbar-left search-form" role="search">
            <div class="input-group">
              <div class="input-group-btn">
			    <div class="btn-group">
			    <button type="button" id="btn-scope" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false" data-used="">
			    	全部&nbsp;<span class="caret"></span>
			    </button>
			    <ul class="dropdown-menu" role="menu" style="min-width: 80px;"><!-- 重写下来出来的列表的最小宽度由160px改成100px -->
				    <li><a href="javascript:void(0)" class="scope" data-used="10">全部</a></li>
					<li><a href="javascript:void(0)" class="scope" data-used="1">二手</a></li>
				</ul>
				</div>
			  </div>
			  <input type="text" class="form-control" id="keyword" size="40" placeholder="请输入商品名称、商家等" aria-label="...">
			  <div class="input-group-btn">
			  	<button type="button" class="btn btn-success" id="btn-search">搜索</button>
			  </div>
			</div>
          </form>
          <ul class="nav navbar-nav navbar-right">
            <li><a class="tag-a" href="${webRoot}/index.html">首页</a></li>
            
            <!-- <li><a class="tag-a" href="#">我的收藏</a></li> -->
             <shiro:hasAnyRoles name="ADMIN, MANAGER">
		     <li><a href="${webRoot}/user/selfinfo.html">管理中心</a></li>
             </shiro:hasAnyRoles>
             <shiro:hasAnyRoles name="BUSSINESS, STUDENT">
             <li class="dropdown">
            	<a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">商家中心<span class="caret"></span></a>
            	<ul class="dropdown-menu" role="menu" style="min-width: 80px;">
	                <li><a href="${webRoot}/user/selfinfo.html">管理中心</a></li>
	                <li><a href="#">我想合作</a></li>
             	</ul>
            </li>
            </shiro:hasAnyRoles>
            <li class="dropdown">
            	<a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">联系我们<span class="caret"></span></a>
            	<ul class="dropdown-menu" role="menu" style="min-width: 80px;">
	                <li><a href="${webRoot}/question.html">常见问题</a></li>
	                <li><a href="${webRoot}/suggestions.html">投诉建议</a></li>
	                <li class="divider"></li>
	                <li><a href="${webRoot}/contact.html">联系客服</a></li>
             	</ul>
            </li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
