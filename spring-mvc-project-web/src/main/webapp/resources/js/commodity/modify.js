/**
 * modify.js
 */
$(function(){
	
	//启用iCheck
	$('input').iCheck({
		checkboxClass: 'icheckbox_square-blue',
		radioClass: 'iradio_square-blue',
		increaseArea: '20%' // optional
	});
	
	
	$('#myForm').on('submit',function(e){
		
		e.preventDefault();
		return;
		
		if($('[name="goods"]').val()==''){
			$('#goodserror').addClass('error').css('color','red').html("× 请填写商品清单！");
			$('#goodserror').show();
		}
		
		if($('#name').val() == ''){
			$('#nameerror').addClass("error").css('color','red').html("× 地区名称不能为空！");
			$('#nameerror').show();
		}
		if($('#category').val() == ''){
			$('#categoryerror').addClass("error").css('color','red').html("× 请选择类别！");
			$('#categoryerror').show();
		}
		if($('#price').val() == ''){
			$('#priceerror').addClass("error").css('color','red').html("× 价格为必填项！");
			$('#priceerror').show();
		}
		if($('#unit').val() == ''){
			$('#uniterror').addClass("error").css('color','red').html("× 计量单位为必填项！");
			$('#uniterror').show();
		}
		
		//检查是否有错误标记
		var errorNum = $(".error").length;
		if(errorNum != 0){
			e.preventDefault();
		}
	});
});