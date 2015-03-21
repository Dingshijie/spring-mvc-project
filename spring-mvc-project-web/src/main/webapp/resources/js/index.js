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
		
		//距离左边的位置:a标签距离左边的位置+a标签的宽度（带padding）+边框的宽度（目前两边都是1 ）
		var heightWithPadding = $(this).outerHeight();
		
		var widthWithPadding = $(this).innerWidth();
		var left = $(this).offset().left + widthWithPadding + 2;
		var top = $(this).offset().top;
		
		var code = $(this).attr('data-code');
		if(code == ''){
			$('#popover').hide();
		}else{
			$.get("HTTP://" + window.location.host + "/category/list/"+code,function(data){
				if(data != ''){
					var len = Math.ceil((data.length + 1)/3);
					
					var html = "<table class='table' style='padding: 10px'><colgroup><col width='30%'></col><col width='30%'></col><col width='30%'></col></colgroup><tr><td style='padding: 10px;'><a href='#'>全部</a></td>";
					for(var i = 0;i < data.length; i++){

						if(i%3==2){
							html += "<tr>";
						}
						html += "<td style='padding: 10px;'><a href='#'>"+ data[i].name +"</a></td>"
						if(i%3==1){
							html += "</tr>";
						}
					}
					html +="</table>"
				}
				$('#popover').css("position","absolute").css('width',2*widthWithPadding +"px").css('height',len*(heightWithPadding) +"px").css("left",left + "px").css("top",top+"px").empty().append(html).show();
			});
		}
		
	},function(){
		
		return;
		
	});
	
	$(document).on('click',function(event){
		//隐藏
		$('#popover').hide();
	});
	
	
});