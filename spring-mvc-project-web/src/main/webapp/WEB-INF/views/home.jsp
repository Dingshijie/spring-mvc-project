<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="webRoot" value="${pageContext.request.contextPath}"/>
<%
	String contextPath = request.getContextPath();
	String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + contextPath;
%>
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
	 <!--引用百度地图API-->
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=063E2bf30e2b32d71187f520e74b01af"></script>
	
</head>
<body>
	<div class="jumbotron">
	  <h1>Hello!</h1>
	  <p><a class="btn btn-primary btn-lg" href="${webRoot}/index.html" role="button">Go ！</a></p>
	  <p><label id="localCity"></label></p>
	  <!--百度地图容器-->
    <div style="width:500px;height:400px;border:#ccc solid 1px;font-size:12px" id="map" class="col-md-offset-2"></div>
	  <script type="text/javascript">
	    //创建和初始化地图函数：
	    function initMap(){
	      createMap();//创建地图
	      setMapEvent();//设置地图事件
	      addMapControl();//向地图添加控件
	      addMapOverlay();//向地图添加覆盖物
	    }
	    function createMap(){ 
	     map = new BMap.Map("map"); 
	      var point = new BMap.Point(113.689123,34.752505);
	      map.centerAndZoom("郑州市",11);
	      var marker = new BMap.Marker(point);  // 创建标注
	  	  map.addOverlay(marker);               // 将标注添加到地图中
	  	  marker.enableDragging(); 
	  		
	  	   // 创建地址解析器实例
		   var myCity = new BMap.LocalCity();
			myCity.get(myFun);
    		function myFun(result){
    			var cityName = result.name;
    			map.setCenter(cityName);
    			$('#localCity').text("当前定位城市为:"+cityName);
    			var myGeo = new BMap.Geocoder();
    		  	// 将地址解析结果显示在地图上,并调整地图视野
    		  	myGeo.getPoint(cityName, function(point){
    		  		if (point) {
    		  			map.centerAndZoom(point, 11);
    		  			var marker = new BMap.Marker(point);  // 创建标注
    		  	  	 	map.addOverlay(marker);    
    		  			marker.enableDragging(); 
    		  		}else{
    		  			alert("您选择地址没有解析到结果!");
    		  		}
    		  	}, "cityName"); 
    		}
	    }
	    function setMapEvent(){
	      map.enableScrollWheelZoom();
	      map.enableKeyboard();
	      map.enableDragging();
	      map.enableDoubleClickZoom()
	    }
	    function addClickHandler(target,window){
	      target.addEventListener("click",function(){
	        target.openInfoWindow(window);
	      });
	    }
	    function addMapOverlay(){
	    }
	    //向地图添加控件
	    function addMapControl(){
	      var scaleControl = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
	      scaleControl.setUnit(BMAP_UNIT_METRIC);
	      map.addControl(scaleControl);
	      var navControl = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:0});
	      map.addControl(navControl);
	    }
	    var map;
	      initMap();
	  </script>
	</div>
</body>
</html>