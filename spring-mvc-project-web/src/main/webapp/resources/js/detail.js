/**
 * 商品详情页detail.js
 */
$(function(){
	
	var id = $("#id").val();
	
	if(getCookie(id) == null ){
		setCookie(id,id);
		$.post('HTTP://'+ window.location.host + "/commodity/addviews/"+id,function(){
			//浏览次数自增1
		});
	}
	
	// 设置cook ======================================================================
	function setCookie(name,value){
		var exp = new Date(); 
		exp.setTime(exp.getTime() + 15*60*1000);//时间设置为15分钟
		document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
	}
	// 获取cook ======================================================================
	function getCookie(name){
		var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
		if(arr=document.cookie.match(reg)) 
			return unescape(arr[2]);
		else
			return null;
	}
});