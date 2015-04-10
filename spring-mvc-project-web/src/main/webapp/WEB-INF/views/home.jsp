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
	<!-- <title>校园E+ 首页</title> -->
	<!-- 新 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet" href="${initParam.resourceRoot}/lib/bootstrap/css/bootstrap.min.css">
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script type="text/javascript" src="${initParam.resourceRoot}/lib/bootstrap/js/bootstrap.min.js"></script>
	
	<style type="text/css">
		.highlight {
		    background-color: #f7f7f9;
		    border: 1px solid #e1e1e8;
		    border-radius: 4px;
		    margin-bottom: 14px;
		    padding: 9px 14px;
		     border-style: solid;
		    padding: 45px 15px 15px;
   			position: relative;
		}
		.highlight::after {
		    content: "实例：" !important;
		}
		.highlight::after {
		    color: #959595;
		    content: "Example";
		    font-size: 12px;
		    font-weight: 700;
		    left: 15px;
		    letter-spacing: 1px;
		    position: absolute;
		    text-transform: uppercase;
		    top: 15px;
		}
		
		.highlight {
		    background-color: #fff;
		    border-color: #ddd;
		    border-radius: 4px 4px 0 0;
		    border-width: 1px;
		    box-shadow: none;
		    margin-left: 0;
		    margin-right: 0;
		}
		
		*::after, *::before {
		    box-sizing: border-box;
		}
		*::after, *::before {
		    box-sizing: border-box;
		}
		* {
		    box-sizing: border-box;
		}
		
	</style>
</head>
<body>
	<div class="container-fluid col-md-10 col-md-offset-2" style="padding: 20px;">
		<div class="show-grid">
			<div id="mainList">
				<div class="row col-md-12">
				  <div class="col-md-4">
				    <a href="#" class="thumbnail">
				      <img src="..." alt="..."  style="width:100%;height: 250px" >
				    </a>
				  </div>
				  <div class="col-md-6">
					<ul class="list-unstyled">
						<li>m</li>
						<li>bbbbbbbbbbbbbbbbbbbb</li>
						<li>mmmmmmmmmmmmmmmmmmmmmmmmmmmmm</li>
						<li><a href="" title="点我点我">点我点我</a></li>
					</ul>
				  </div>
				</div>
				<div class="col-md-12 highlight">
				
					
					<div class="form-group bg-success col-md-offset-1">
						好好玩：
						<input type="radio" >减肥 <span style="margin-right: 15px;"></span><input type="radio" >不减肥
					</div>
					<div class="form-group bg-danger col-md-offset-1">
						哈哈哈：
						<input type="radio" >选我 <span style="margin-right: 15px;"></span><input type="radio" >不选我
					</div>
					<div class="form-group col-md-offset-1">
						<input class="btn btn-info" type="submit" value="学习">
						<input class="btn btn-default" type="submit" value="bootstrap">
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>