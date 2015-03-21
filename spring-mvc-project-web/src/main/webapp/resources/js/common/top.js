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
	
	$('.scope').on('click',function(){
		$('#btn-scope').empty().append($(this).text()+'&nbsp;<span class="caret"></span>');
		$('#btn-scope').attr('data-used',$(this).attr('data-used'));
	});
	
	
});