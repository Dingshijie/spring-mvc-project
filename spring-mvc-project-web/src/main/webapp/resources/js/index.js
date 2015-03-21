/**
 * index的js
 */
$(function(){
	
	/**
	 * 使首页轮转的图片生效
	 */
	$('.carousel').carousel();
	
	
	/**
	 * 鼠标在分类上移动
	 */
	$('.list-group-item').hover(function(){
		
		$('.list-group-item').removeClass('active');
		
		$(this).addClass('active');
		
		
		
		var code = $(this).attr('data-code');
		if(code != ''){
			
			$.get("HTTP://" + window.location.host + "/category/list/"+code,function(data){
				if(data != ''){
					var html = "<table><tr><td>"+ data[0].name+"</td></tr></table>";
				}
				$('#popover').css("position","absolute").css("left",50+"px").css("top",50+"px").empty().append(html).show();
			
			});
		}
		
	},function(){
			
		return;
	});
	
	
	
});