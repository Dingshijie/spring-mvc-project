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
		var heightWithPadding = $(this).innerHeight();
		console.log(heightWithPadding);
		var widthWithPadding = $(this).innerWidth();
		var left = $(this).offset().left + widthWithPadding + 2;
		var top = $(this).offset().top;
		
		var code = $(this).attr('data-code');
		if(code == ''){
			$('#popover').hide();
		}else{
			$.get("HTTP://" + window.location.host + "/category/list/"+code,function(data){
				if(data != ''){
					var html = "<table style='width:100%'><colgroup><col width='30%'></col><col width='30%'></col><col width='30%'></col></colgroup>";
					for(var i = 0;i < data.length; i++){
						if((i+1)%3==1){
							html += "<tr style='heigth:"+ heightWithPadding +"px'>";
						}
						html += "<td><a href='#'>"+ data[i].name +"</a></td>"
						if((i+1)%3==0){
							html += "</tr>";
						}
					}
					html +="</html>"
				}
				$('#popover').css("position","absolute").css('width',2*widthWithPadding +"px").css('height',heightWithPadding*(i%3==0?i/3:(i/3+1)) +"px").css("left",left + "px").css("top",top+"px").empty().append(html).show();
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