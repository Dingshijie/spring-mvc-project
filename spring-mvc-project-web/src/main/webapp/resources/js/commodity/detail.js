/**
 * 商品详细信息的js
 */
$(function(){
	
$('.reset').click();
	
	//启用iCheck
	$('input').iCheck({
		checkboxClass: 'icheckbox_square-blue',
		radioClass: 'iradio_square-blue',
		increaseArea: '20%' // optional
	});
	
	var par = {
		'recommend': $('#recommend').val() || '' ,
		'status': $('#status').val() || ''
	}
	
	//切换是否热门项时
	$('[name="recommend"]').on('ifChecked', function(event){
		 par.recommend = $(this).val();
		
	});
	
	//切换是否热门项时
	$('[name="status"]').on('ifChecked', function(event){
		 par.status = $(this).val();
		 console.log(par.status);
	});
	
	/**
	 * 点击确定
	 */
	$('.btn-submit').on('click',function(){
		
		var paramter = "{";
		if(par.recommend != ''){
			paramter += "recommend:"+par.recommend;
		}
		if(par.status != ''){
			if(paramter != "{")
				paramter +=",";
			paramter += "status:"+par.status;
		}
		paramter +="}";
		if(paramter != "{}"){
			var id = $('#id').val();
			$.post('HTTP://'+ window.location.host +'/commodity/update',{"id":id,"paramter":paramter},function(data){
				if(data){
					$('.alert').removeClass('alert-danger').addClass('alert-success').html('设置成功！即将重新加载页面．．．').show();
					setTimeout(function(){
						$('.alert').html('').hide();
						location.href = 'HTTP://'+window.location.host+'/commodity/find/'+id;
					}, 1000);
				}else{
					$('.alert').removeClass('alert-success').addClass('alert-danger').html('设置失败！').show();
					setTimeout(function(){
						$('.alert').html('').hide();
						location.reload();
					}, 1000);
				}
			});
		}
	});
	
	/**
	 * 编辑商品信息
	 */
	$('.btn-edit').on('click',function(){
		var id=$('#id').val();
		location.href = 'HTTP://' + window.location.host+'/commodity/find?id='+id;
	});
});