/**
 * index的js
 */
$(function(){
	
	/**
	 * 使首页轮转的图片生效
	 */
	$('.carousel').carousel();
	
	$('.dropdown-toggle').dropdown();
	
	/**
	 * 鼠标在分类上移动
	 */
	$('.list-group-item').hover(function(){
		
		$('.list-group-item').removeClass('active');
		
		$(this).addClass('active');
		
	},function(){
			
		return;
	});
	/**
	 * 鼠标移动到class为tag-a的a标签上的效果
	 */
	$('.tag-a').hover(function(){
		
		$(this).css('text-decoration','underline').css('color','#337ab7');
		
	},function(){
		
		$(this).css('text-decoration','none').css('color','#777');
		return;
	});
	
	$('.scope').on('click',function(){
		$('#btn-scope').empty().append($(this).text()+'&nbsp;<span class="caret"></span>');
		$('#btn-scope').attr('data-used',$(this).attr('data-used'));
	});
	
	
	
	
});