/**
 * add.js 
 */
$(function(){
	
	$('.reset').click();
	
	//启用iCheck
	$('input').iCheck({
		checkboxClass: 'icheckbox_square-blue',
		radioClass: 'iradio_square-blue',
		increaseArea: '20%' // optional
	});
	

	/**
	 * 点击添加的图标的时候
	 */
	$('.goodsPlus').on('click',function(){
		
		if($('.goodsList').find('p').length != 0){
			$('.goodsAdd').addClass('col-sm-offset-2');
		}else{
			$('.goodsAdd').removeClass('col-sm-offset-2');
		}
		$('.goodsPlus').addClass('col-sm-offset-2');
		$('.goodsAdd').show();
		
	});
	
	/**
	 * 点击取消的时候
	 */
	$('.goodsAdd').on('click',".cancel",function(){
		
		$('.goodsAdd').removeClass('col-sm-offset-2');
		$('.goodsAdd').hide();
		
		if($('.goodsList').find('p').length == 0){
			$('.goodsPlus').removeClass('col-sm-offset-2');
		}
		
	});
	
	/**
	 * 点击保存的时候
	 */
	$('.goodsAdd').on('click',".save",function(){
		
		var value = $('#goods').val();
		if(value == ''){
			return;
		}
		var html='<p><input type="text" class="good" value="'+ value +'" style="width:'+ (value.length+1)*14 +'px"> <a href="javascript:void(0);" title="点击删除"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a><a href="javascript:void(0);" title="点击编辑"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a><input type="button" class="btn btn-info save" value="保存"> <input type="button" class="btn btn-default cancel" value="取消"> </p>';
		$('.goodsList').append(html);
		$('.goodsList').show();
		$('.goodsAdd').hide();
		
	});
	
	/**
	 * 点击glyphicon-remove按钮删除的时候
	 */
	$('.goodsList').on('click',".glyphicon-remove",function(){
		
		$(this).closest('p').remove();
		if($('.goodsList').find('p').length == 0){
			$('.goodsList').css('display','none');
			$('.goodsPlus').removeClass('col-sm-offset-2');
		}
		
	});
	
	/**
	 * 点击glyphicon-edit按钮编辑的时候
	 */
	$('.goodsList').on('click',".glyphicon-edit",function(){
		
		
		$(this).closest('p').find('input.good').css("border","1px solid #ccc").addClass('form-control');
		
		$(this).closest('p').find('.glyphicon').hide();
		$(this).closest('p').find('input.btn').show();
		
	});
	
	/**
	 * 点击glyphicon-edit按钮编辑的时候
	 */
	$('.goodsList').on('click',".save,.cancel",function(){
		

		$(this).closest('p').find('input.good').css("border","0px solid #ccc").removeClass('form-control');
		
		$(this).closest('p').find('.glyphicon').show();
		$(this).closest('p').find('input.btn').hide();
		
	});
	

	
});
