/**
 * add Category js
 */
$(function(){
	
	$('input').iCheck({
		checkboxClass: 'icheckbox_square-blue',
		radioClass: 'iradio_square-blue',
		increaseArea: '20%' // optional
	});
		  
	$('input').on('ifChecked', function(event){
		console.log(event.type + ' callback');
		console.log($(this).val());
	});
	
});