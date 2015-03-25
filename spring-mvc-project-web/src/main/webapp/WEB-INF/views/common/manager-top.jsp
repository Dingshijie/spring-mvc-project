<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="webRoot" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<link rel="stylesheet" href="${initParam.resourceRoot}/css/common/manager-top.css"/>
<script type="text/javascript" src="${initParam.resourceRoot}/js/common/manager-top.js"></script>
   <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
         <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" style="color: #337ab7" href="#">校园E+</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <shiro:authenticated>
            	<li>
            		<a href="javascript:void(0);">欢迎您：<shiro:principal></shiro:principal></a>
            	</li>
            	<li>
            		<a class="tag-a" href="${webRoot}/logout.html">[退出]</a>
            	</li>
            </shiro:authenticated>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>