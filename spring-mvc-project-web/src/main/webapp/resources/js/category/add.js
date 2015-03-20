/**
 * add Category js
 */
$(function(){
	
	//启用iCheck
	$('input').iCheck({
		checkboxClass: 'icheckbox_square-blue',
		radioClass: 'iradio_square-blue',
		increaseArea: '20%' // optional
	});
	//初始化变量
	var category={
		"code": "",
		"name": "",
		"category": "",
		"hot": 0,
		"enable": 1
	}

	//切换是否热门项时
	$('[name="hot"]').on('ifChecked', function(event){
		category.hot = $(this).val();
	});
	//切换是否可用项时
	$('[name="enable"]').on('ifChecked', function(event){
		category.enable = $(this).val();
	});
	/**
	 * 对类别代码处理
	 */
	$('#code').on('focus',function(){
		$('#codeerror').hide();
	});
	$('#code').on('blur',function(){
		$('#codeerror').removeClass("error").css('color','green').html("√");
		var code = $.trim($('#code').val());
		var len = code.length;
		if(len==0){
			$('#codeerror').addClass("error").css('color','red').html("× 类别代码不能为空！");
		}else if(len != 4){
			$('#codeerror').addClass("error").css('color','red').html("× 类别代码长度应为4个字符！");
		}else{
			var fieldName = $('#code').attr('name');

			$.get('HTTP://'+window.location.host+'/category/exist',{"fieldName":fieldName,"fieldValue":code},function(data){
				if(data){
					$('#codeerror').addClass("error").css('color','red').html("× 该代码已经存在！");
				}
			});
		}
		$('#codeerror').show();
	});
	/**
	 * 对类别名称的检测
	 */
	$('#name').on('focus',function(){
		$('#nameerror').hide();
	});
	$('#name').on('blur',function(){
		$('#nameerror').removeClass("error").css('color','green').html("√");
		var name = $.trim($('#name').val());
		var len = name.length;
		if(len==0){
			$('#nameerror').addClass("error").css('color','red').html("× 类别名称不能为空！");
		}else if(len > 32){
			$('#nameerror').addClass("error").css('color','red').html("× 类别代码长度不能超过32个字符！");
		}else{
			var fieldName = $('#name').attr('name');

			$.get('HTTP://'+window.location.host+'/category/exist',{"fieldName":fieldName,"fieldValue":name},function(data){
				if(data){
					$('#nameerror').addClass("error").css('color','red').html("× 该类别名称已经存在！");
				}
			});
		}
		$('#nameerror').show();
	});
	
	/**
	 * 选择所属分类的的判断
	 */
	$('#category').on('change',function(){
		$('#categoryerror').removeClass("error").css('color','green').html("√");
		var category = $(this).val();
		if(category == ""){
			$('#categoryerror').removeClass("error").css('color','green').html("请选择类别代码");
		}
		$('#categoryerror').show();
	});
	
	/**
	 * 确认提交
	 */
	$('#btn-submit').on('click',function(){

		if($('#code').val() == ''){
			$('#codeerror').addClass("error").css('color','red').html("× 类别代码不能为空！");
			$('#codeerror').show();
		}
		if($('#name').val() == ''){
			$('#nameerror').addClass("error").css('color','red').html("× 类别名称不能为空！");
			$('#nameerror').show();
		}
		if($('#category').val() == ''){
			$('#categoryerror').addClass("error").css('color','red').html("× 所属类别不能为空");
			$('#categoryerror').show();
		}

		if($('#code').val().substring(0,2) != $('#category').val() ){
			$('#categoryerror').addClass("error").css('color','red').html("× 上述代码不属于该分类");
			$('#categoryerror').show();
		}
	
		//检查是否有错误标记
		var errorNum = $(".error").length;
		if(errorNum == 0){
			category.code = strUniCode($('#code').val());
			category.name = strUniCode($('#name').val());
			category.category = $('#category').val();
			$.post("HTTP://"+window.location.host+"/category/add",category,function(data){
				
				if(data==true){
					$('#myModal').modal('show');
					setTimeout(function(){
						location.href="HTTP://"+window.location.host+"/category/category.html";
					}, 3000);
				}else{
					$('.modal-body').empty().append("提交失败，即将返回注册页面&hellip;");
					$('#myModal').modal('show');
					setTimeout(function(){
						location.href="HTTP://"+window.location.host+"/category/add.html";
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
