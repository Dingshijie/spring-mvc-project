/**
 * top.js
 */
$(function(){
	
	$('.dropdown-toggle').dropdown();
	
	/**
	 * 鼠标移动到class为tag-a的a标签上的效果
	 */
	$('.tag-a').hover(function(){
		
		$(this).css('text-decoration','underline').css('color','#337ab7');
		
	},function(){
		
		$(this).css('text-decoration','none').css('color','#777');
		return;
	});
	
	if(getCookie("used") == null || getCookie("used")==10){
		setCookie('used',10);
		$('#btn-scope').empty().append('全部&nbsp;<span class="caret"></span>');
	}else{
		$('#btn-scope').empty().append('二手&nbsp;<span class="caret"></span>');
	}
	
	$('.scope').on('click',function(){
		$('#btn-scope').empty().append($(this).text()+'&nbsp;<span class="caret"></span>');
		var used = $(this).attr('data-used');
		$('#btn-scope').attr('data-used',used);
		
		setCookie('used',used);
	});
	
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