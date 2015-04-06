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
	<title>校园E+ 主页</title>
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
          			所有分类  > 城市:郑州市 > 区域:高新区
          		</div>
          		<table class="table table-condensed" style="margin-bottom:0px;">
          		<colgroup><col width="10%"></col><col width="10%"></col><col width="10%"></col><col width="10%"></col><col width="10%"></col><col width="10%"></col><col width="10%"></col><col width="10%"></col><col width="10%"></col><col width="10%"></col></colgroup>
          			<tbody>
          				<tr>
          					<th>热门:</th>
          					<td><a >连衣裙</a></td>
          					<td><a >短裤</a></td>
          					<td><a >高跟鞋</a></td>
          					<td><a >女式包包</a></td>
          					<td><a >篮球</a></td>
          					<td><a >自行车</a></td>
          					<td><a >手机</a></td>
          					<td><a >电脑</a></td>
          					<td><a >台灯</a></td>
          				</tr>
          				<tr>
          					<th>城市:</th>
          					<td colspan="9">郑州市 &nbsp;<button class="btn btn-default btn-xs">切换城市</button></td>
          				</tr>
          				<tr>
          					<th>区域:</th>
          					<td><a >金水区</a></td>
          					<td><a >中原区</a></td>
          					<td><a >二七区</a></td>
          					<td><a >管城回族区</a></td>
          					<td><a >惠济区</a></td>
          					<td><a >上街区</a></td>
          					<td><a >高新区</a></td>
          					<td><button style="" class="btn btn-default btn-xs">更多<span class="caret"></span></button></td>
          				</tr>
          				<tr>
          					<th>学校:</th>
          					<td><a >郑州大学（新校区）</a></td>
          					<td><a >郑州大学（老校区）</a></td>
          					<td><a >河南农业大学</a></td>
          					<td><a >河南中医学院大学</a></td>
          					<td><a >河南教育学院</a></td>
          					<td><a >华北水利水电学院</a></td>
          					<td><a >河南工业大学</a></td>
          					<td><a >郑州轻工业学院</a></td>
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
              <img src="${initParam.resourceRoot}/img/list-1.jpg" alt="..." style="width:100%;height: 200px" class="img-responsive img-thumbnail">
              <p>Donec id elit non mi porta gravida at eget metus. </p>
              <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
            </div><!--/.col-xs-6.col-lg-4-->
            <div class="col-xs-6 col-lg-4 col-md-4">
              <img src="${initParam.resourceRoot}/img/list-2.jpg" alt="..." style="width:100%;height: 200px;" class="img-responsive img-thumbnail">
              <p>Donec id elit non mi porta gravida at eget metus.  </p>
              <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
            </div><!--/.col-xs-6.col-lg-4-->
            <div class="col-xs-6 col-lg-4 col-md-4">
              <img src="${initParam.resourceRoot}/img/list-3.jpg" alt="..." style="width:100%;height: 200px;" class="img-responsive img-thumbnail">
              <p>Donec id elit non mi porta gravida at eget metus.  </p>            
              <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
            </div><!--/.col-xs-6.col-lg-4-->
            <div class="col-xs-6 col-lg-4">
              <img src="${initParam.resourceRoot}/img/list-4.jpg" alt="..." style="width:100%;height: 200px" class="img-responsive img-thumbnail">
              <p>Donec id elit non mi porta gravida at eget metus. </p>
              <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
            </div><!--/.col-xs-6.col-lg-4-->
            <div class="col-xs-6 col-lg-4">
               <img src="${initParam.resourceRoot}/img/list-5.jpg" alt="..." style="width:100%;height: 200px" class="img-responsive img-thumbnail">
              <p>Donec id elit non mi porta gravida at eget metus. </p>
              <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
            </div><!--/.col-xs-6.col-lg-4-->
            <div class="col-xs-6 col-lg-4">
              <img src="${initParam.resourceRoot}/img/list-6.jpg" alt="..." style="width:100%;height: 200px" class="img-responsive img-thumbnail">
              <p>Donec id elit non mi porta gravida at eget metus. </p>
              <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
            </div><!--/.col-xs-6.col-lg-4-->
            <div class="col-xs-6 col-lg-4">
              <img src="${initParam.resourceRoot}/img/list-7.jpg" alt="..." style="width:100%;height: 200px" class="img-responsive img-thumbnail">
              <p>Donec id elit non mi porta gravida at eget metus. </p>
              <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
            </div><!--/.col-xs-6.col-lg-4-->
            <div class="col-xs-6 col-lg-4">
              <img src="${initParam.resourceRoot}/img/list-8.jpg" alt="..." style="width:100%;height: 200px" class="img-responsive img-thumbnail">
              <p>Donec id elit non mi porta gravida at eget metus. </p>
              <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
            </div><!--/.col-xs-6.col-lg-4-->
            <div class="col-xs-6 col-lg-4">
              <img src="${initParam.resourceRoot}/img/list-9.jpg" alt="..." style="width:100%;height: 200px" class="img-responsive img-thumbnail">
              <p>Donec id elit non mi porta gravida at eget metus. </p>
              <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
            </div><!--/.col-xs-6.col-lg-4-->
            <div class="col-xs-6 col-lg-4">
              <img src="${initParam.resourceRoot}/img/list-10.jpg" alt="..." style="width:100%;height: 200px" class="img-responsive img-thumbnail">
              <p>Donec id elit non mi porta gravida at eget metus. </p>
              <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
            </div><!--/.col-xs-6.col-lg-4-->
            <div class="col-xs-6 col-lg-4">
              <img src="${initParam.resourceRoot}/img/list-11.jpg" alt="..." style="width:100%;height: 200px" class="img-responsive img-thumbnail">
              <p>Donec id elit non mi porta gravida at eget metus. </p>
              <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
            </div><!--/.col-xs-6.col-lg-4-->
            <div class="col-xs-6 col-lg-4">
              <img src="${initParam.resourceRoot}/img/list-12.jpg" alt="..." style="width:100%;height: 200px" class="img-responsive img-thumbnail">
              <p>Donec id elit non mi porta gravida at eget metus. </p>
              <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
            </div><!--/.col-xs-6.col-lg-4-->
          </div><!--/row-->
        </div><!--/.col-xs-12.col-sm-9-->

      </div><!--/row-->
      <hr>
      <footer class="bs-docs-footer">
        <p>&copy; 2015 Zhengzhou University of Light Industry WangChen</p>
        <p></p>
      </footer>

    </div><!--/.container-->
    <!-- 首页小分类的展示 -->
    <div id="popover" style="background-color:#fff;color: #555;font-size: 14px;" >
	
	</div>
	
</body>
</html>