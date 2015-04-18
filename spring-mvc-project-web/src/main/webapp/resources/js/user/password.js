/**
 * password.js
 */
$(function(){

	/**
	 * 旧密码输入框的焦点的转移和移入
	 */
	$('#oldpassword').on('focus',function(){
		$('#oldpassworderror').hide();
	});
	$('#oldpassword').on('blur',function(){
		$('#oldpassworderror').removeClass("error").css('color','green').html("√");
		var oldPassword =  $.trim($(this).val());
		if(oldPassword == '' || oldPassword.length<6 || oldPassword.length >128){
			$('#oldpassworderror').addClass("error").css('color','red').html("旧密码验证错误，请重新输入");
		}else{
			$.get('HTTP://'+ window.location.host +'/user/verifypassword',{"password":hex_md5(oldPassword)},function(data){
				if(!data){
					$('#oldpassworderror').addClass("error").css('color','red').html("旧密码验证错误，请重新输入");
				}
			});
		}
		$('#oldpassworderror').show();
	});
	
	/**
	 * 焦点定位和离开密码输入框的时候
	 */
	$('#newpassword').on('blur',function(){
		$('#newpassworderror').removeClass("error").css('color','green').html("√");
		var len = $.trim($('#newpassword').val()).length;
		if(len==0){
			$('#newpassworderror').addClass("error").css('color','red').html("× 密码不能为空！");
		}else if(len<6 || len >128){
			$('#newpassworderror').addClass("error").css('color','red').html("× 密码长度应为6到128个字符！");
		}
		$('#newpassworderror').show();
	});
	$('#newpassword').on('focus',function(){
		$('#newpassworderror').hide();
	});
	
	/**
	 * 焦点定位和离开密码输入框(确定的密码框)的时候
	 */
	$('#password').on('blur',function(){
		$('#passworderror').removeClass("error").css('color','green').html("√");
		var newpassword = $.trim($('#newpassword').val());
		var password = $.trim($('#password').val());
		if(newpassword  != password){
			$('#passworderror').addClass("error").css('color','red').html("× 两次输入的密码不一致！");
		}
		$('#passworderror').show();
	});
	$('#password').on('focus',function(){
		$('#passworderror').hide();
	});
	
	$('.btn-sure').on('click',function(data){
		$('.btn-sure').addClass('disabled');
		$('.btn-cancel').addClass('disabled');
		if($('#oldpassword').val() == ''){
			$('#oldpassworderror').addClass("error").css('color','red').html("× 旧密码不能为空！");
			$('#oldpassworderror').show();
		}
		if($('#newpassword').val() == '' ){
			$('#newpassworderror').addClass("error").css('color','red').html("× 密码不能为空！");
			$('#newpassworderror').show();
		}
		if($('#password').val() == ''){
			$('#passworderror').addClass("error").css('color','red').html("× 密码不能为空！");
			$('#passworderror').show();
		}
		if( $('#newpassword').val()  != $('#password').val()){
			$('#passworderror').addClass("error").css('color','red').html("× 两次输入的密码不一致！");
			$('#passworderror').show();
		}
		//检查是否有错误标记
		var errorNum = $(".error").length;
		if(errorNum == 0){
			$.post('HTTP://' + window.location.host + '/user/modifypassword',{"password":hex_md5($('#password').val())},function(data){
				if(data){
					$('.alert').removeClass('alert-danger').addClass('alert-info').html('提示：修改成功！即将跳转到登陆页面重新登陆...').show();
					setTimeout(function(){
						$('.btn-sure').removeClass('disabled');
						$('.btn-cancel').removeClass('disabled');
						location.href="HTTP://"+window.location.host+"/login.html";
					}, 3000);
				}else{
					$('.alert').removeClass('alert-info').addClass('alert-danger').html('提示：修改失败！').show();
					$('.btn-sure').removeClass('disabled');
					$('.btn-cancel').removeClass('disabled');
				}
			});
		}else{
			$('.btn-sure').removeClass('disabled');
			$('.btn-cancel').removeClass('disabled');
		}
	});
	
	$('.btn-cancel').on('click',function(){
		window.history.go(-1);
	});
});