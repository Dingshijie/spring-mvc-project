/**
 * add.js
 */
$(function(){
	
	/**
	 * 刷新页面设置各个下拉框以及各个输入框的内容均为空
	 */
	$('.reset').click();
	
	/**
	 * 此处的作用是将class是js-example-data-array-selected的select控件可以做select2使用，相当于初始化
	 */
	$(".js-example-data-array-selected").select2({

	});
	
	/**
	 * 焦点地区代码输入框的时候
	 */
	$('#code').on('blur',function(){
		$('#codeerror').removeClass("error").css('color','green').html("√");
		var code = $('#code').val();
		var len = code.length;
		if(len==0){
			$('#codeerror').addClass("error").css('color','red').html("× 地区代码不能为空！");
		}else if(len !=6 ){
			$('#codeerror').addClass("error").css('color','red').html("× 地区代码只能为6个字符！");
		}else{
			var fieldName = $('#code').attr('name');

			$.get('HTTP://'+window.location.host+'/area/exist',{"fieldName":fieldName,"fieldValue":code},function(data){
				if(data){
					$('#codeerror').addClass("error").css('color','red').html("× 该地区代码已存在！");
				}
			});
		}
		$('#codeerror').show();
	});
	$('#code').on('focus',function(){
		$('#codeerror').hide();
	});
	
	/**
	 * 焦点地区名输入框的时候
	 */
	$('#name').on('blur',function(){
		$('#nameerror').removeClass("error").css('color','green').html("√");
		var name = $.trim($('#name').val());
		var len = name.length;
		if(len==0){
			$('#nameerror').addClass("error").css('color','red').html("× 地区名称不能为空！");
		}else if(len < 2 || len > 32){
			$('#nameerror').addClass("error").css('color','red').html("× 地区名称长度只能为2到32个字符！");
		}
		$('#nameerror').show();
	});
	$('#name').on('focus',function(){
		$('#nameerror').hide();
	});
	
	/**
	 * 焦点显示名称输入框的时候
	 */
	$('#display').on('blur',function(){
		$('#displayerror').removeClass("error").css('color','green').html("√");
		var display = $('#display').val();
		var len = display.length;
		if(len==0){
			$('#displayerror').addClass("error").css('color','red').html("× 地区显示名称不能为空！");
		}else if(len < 2 || len > 32){
			$('#displayerror').addClass("error").css('color','red').html("× 地区显示名称长度只能为2到32个字符！");
		}else{
			var fieldName = $('#display').attr('name');

			$.get('HTTP://'+window.location.host+'/area/exist',{"fieldName":fieldName,"fieldValue":display},function(data){
				if(data){
					$('#displayerror').addClass("error").css('color','red').html("× 该名称已存在！");
				}
			});
		}
		$('#displayerror').show();
	});
	$('#display').on('focus',function(){
		$('#displayerror').hide();
	});
	
	/**
	 * 地区类型选择改变
	 */
	$('#typeCode').on('change',function(){
		$('#typeCodeerror').removeClass("error").css('color','green').html("√");
		if($(this).val() == ''){
			$('#typeCodeerror').removeClass("error").css('color','green').html("× 请选择地区类型！");
		}
		$('#typeCodeerror').show();
	});
	/**
	 * 提交表单的时候
	 */
	$('#btn-submit').on('click',function(event){
		event.preventDefault();
		//检查是否有空的
		if($('#code').val() == ''){
			$('#codeerror').addClass("error").css('color','red').html("× 地区代码不能为空！");
			$('#codeerror').show();
		}
		if($('#name').val() == ''){
			$('#nameerror').addClass("error").css('color','red').html("× 地区名称不能为空！");
			$('#nameerror').show();
		}
		if($('#display').val() == ''){
			$('#displayerror').addClass("error").css('color','red').html("× 地区显示名称不能为空！");
			$('#displayerror').show();
		}
		if($('#typeCode').val() == ''){
			$('#typeCodeerror').addClass("error").css('color','red').html("× 请选择地区类型！");
			$('#typeCodeerror').show();
		}
		
		//检查是否有错误标记
		var errorNum = $(".error").length;
		if(errorNum == 0){
			
			//将密码转换成md5密文形式
			var area ={
				"name":"",
				"code":"",
				"display":"",
				"typeCode":""
				
			}
			area.name = strUniCode($('#name').val());
			area.code = strUniCode($('#code').val());
			area.display = strUniCode($('#display').val());
			area.typeCode = $('#typeCode').val();
			
			
			$.post("HTTP://"+window.location.host+"/area/add",edupubcode,function(data){
				
				if(data==true){
					$('#myModal').modal('show');
					setTimeout(function(){
						location.href="HTTP://"+window.location.host+"/area/list";
					}, 3000);
				}else{
					$('.modal-body').empty().append("提交失败，即将返回添加页面&hellip;");
					$('#myModal').modal('show');
					setTimeout(function(){
						location.href="HTTP://"+window.location.host+"/area/add";
					}, 3000);
				}
			});
		}
	});
	
	//字符串编码
	function strUniCode(value){
		return value.trim().replace(/>/g,"&gt").replace(/</g,"&lt");
	};
	//字符串转码
	function strUnUniCode(value){
		return value.replace(/&gt/g,">").replace(/&lt/g,"<");
	};
});