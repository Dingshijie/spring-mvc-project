/**
 * login.js
 */
$(function(){
	
	
	$('#btn-submit').on('click',function(){
		//将密码转换成md5密文形式
		$('#inputPassword').val(hex_md5($('#inputPassword').val()));
		
	});
		
		
});