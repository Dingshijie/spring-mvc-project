<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="webRoot" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="${initParam.resourceRoot}/lib/bootstrap/css/bootstrap.min.css">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script type="text/javascript" src="${initParam.resourceRoot}/lib/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script type="text/javascript" src="${initParam.resourceRoot}/lib/bootstrap/js/bootstrap.min.js"></script>

<!-- index.css 文件必须放到bootstrap。min.cs之后 -->
<script type="text/javascript" src="${initParam.resourceRoot}/js/index.js"></script>
<link rel="stylesheet" href="${initParam.resourceRoot}/css/index.css"/>
</head>
<body>
	<c:import url="common/top.jsp"></c:import>
    <div class="container">
      <div class="row row-offcanvas row-offcanvas-right">
		 <div class="col-xs-6 col-sm-2 sidebar-offcanvas" id="sidebar">
          <div class="list-group">
         	<a href="javascritp:void(0);" class="list-group-item active" data-code="">所有分类</a>
            <a href="#" class="list-group-item" data-code='11'>女装</a>
            <a href="#" class="list-group-item" data-code='12'>男装</a>
            <a href="#" class="list-group-item" data-code='13'>鞋靴箱包</a>
            <a href="#" class="list-group-item" data-code='14'>运动户外</a>
            <a href="#" class="list-group-item" data-code='15'>手机数码</a>
            <a href="#" class="list-group-item" data-code='16'>美妆配饰</a>
            <a href="#" class="list-group-item" data-code='17'>生活用品</a>
            <a href="#" class="list-group-item" data-code='18'>学习用品</a>
            <a href="#" class="list-group-item" data-code='91'>其他</a>
          </div>
        </div><!--/.sidebar-offcanvas-->
        <div class="col-xs-12 col-sm-10">
          <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
          </p>
          	<div class="form-group">
          		<div class="form-group">
          			所有分类  > 城市:北京市 > 区域:海淀区
          		</div>
          		<table class="table table-condensed" style="margin-bottom:0px;">
          		<colgroup><col width="10%"></col><col width="10%"></col><col width="10%"></col><col width="10%"></col><col width="10%"></col><col width="10%"></col><col width="10%"></col><col width="10%"></col><col width="10%"></col><col width="10%"></col></colgroup>
          			<tbody>
          				<tr>
          					<th>热门:</th>
          					<td><a >美食</a></td>
          					<td><a >电影</a></td>
          					<td><a >经济型酒店</a></td>
          					<td><a >火锅</a></td>
          					<td><a >本地/周边游</a></td>
          					<td><a >景点门票</a></td>
          					<td><a >代金券</a></td>
          					<td><a >温泉</a></td>
          					<td><a >烤肉</a></td>
          				</tr>
          				<tr>
          					<th>城市:</th>
          					<td colspan="9">北京市 &nbsp;<button class="btn btn-default btn-xs">切换城市</button></td>
          				</tr>
          				<tr>
          					<th>区域:</th>
          					<td><a >东城区</a></td>
          					<td><a >西城区</a></td>
          					<td><a >房山区</a></td>
          					<td><a >海淀区</a></td>
          					<td><a >朝阳区</a></td>
          					<td><a >丰台区</a></td>
          					<td><a >昌平区</a></td>
          					<td><a >通州区</a></td>
          					<td><button style="" class="btn btn-default btn-xs">更多<span class="caret"></span></button></td>
          				</tr>
          				<tr>
          					<th>学校:</th>
          					<td><a >北京大学</a></td>
          					<td><a >清华大学</a></td>
          					<td><a >北京邮电大学</a></td>
          					<td><a >北京科技大学</a></td>
          					<td><a >中央名族大学</a></td>
          					<td><a >国际关系学院</a></td>
          					<td><a >中央美术学院</a></td>
          					<td><a >中国矿业大学</a></td>
          					<td><button style="" class="btn btn-default btn-xs">更多<span class="caret"></span></button></td>
          				</tr>
          			</tbody>
          		</table>
          	</div>
          <div class="jumbotron" style="padding-top: 10px;"><!-- 这里对这个jumbotron的“内上”边距进行了重写设置比较padding-top为10px -->
         	<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
			  <!-- Indicators -->
			  <ol class="carousel-indicators">
			    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
			    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
			    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
			    <li data-target="#carousel-example-generic" data-slide-to="3"></li>
			    <li data-target="#carousel-example-generic" data-slide-to="4"></li>
				<li data-target="#carousel-example-generic" data-slide-to="5"></li>
			  </ol>
			  <!-- Wrapper for slides -->
			  <div class="carousel-inner" role="listbox">
			    <div class="item active">
			      <img src="${initParam.resourceRoot}/img/test-1.jpg" alt="...">
			    </div>
			    <div class="item">
			      <img src="${initParam.resourceRoot}/img/test-2.jpg"  alt="...">
			    </div>
			    <div class="item">
			      <img src="${initParam.resourceRoot}/img/test-3.jpg"  alt="...">
			    </div>
			    <div class="item">
			      <img src="${initParam.resourceRoot}/img/test-4.jpg"  alt="...">
			    </div>
			    <div class="item">
			      <img src="${initParam.resourceRoot}/img/test-5.jpg" alt="...">
			    </div>
				<div class="item">
			      <img src="${initParam.resourceRoot}/img/test-6.jpg" alt="...">
			    </div>
			    
			  </div>
			
			  <!-- Controls -->
			  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
			    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
			    <span class="sr-only">Previous</span>
			  </a>
			  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
			    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			    <span class="sr-only">Next</span>
			  </a>
			</div>
          </div>
          <div class="row">
            <div class="col-xs-6 col-lg-4 col-md-4">
              <h2>Heading</h2>
              <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
              <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
            </div><!--/.col-xs-6.col-lg-4-->
            <div class="col-xs-6 col-lg-4 col-md-4">
              <h2>Heading</h2>
              <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
              <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
            </div><!--/.col-xs-6.col-lg-4-->
            <div class="col-xs-6 col-lg-4 col-md-4">
              <h2>Heading</h2>
              <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
              <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
            </div><!--/.col-xs-6.col-lg-4-->
            <div class="col-xs-6 col-lg-4">
              <h2>Heading</h2>
              <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
              <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
            </div><!--/.col-xs-6.col-lg-4-->
            <div class="col-xs-6 col-lg-4">
              <h2>Heading</h2>
              <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
              <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
            </div><!--/.col-xs-6.col-lg-4-->
            <div class="col-xs-6 col-lg-4">
              <h2>Heading</h2>
              <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
              <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
            </div><!--/.col-xs-6.col-lg-4-->
          </div><!--/row-->
        </div><!--/.col-xs-12.col-sm-9-->

      </div><!--/row-->
      <hr>
      <footer>
        <p>&copy; Company 2014</p>
      </footer>

    </div><!--/.container-->
	<div id="popover" style="background-color:#fff;color: #555;font-size: 14px;" >
	
	</div>
</body>
</html>