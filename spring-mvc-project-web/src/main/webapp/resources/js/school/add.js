/**
 * add.js
 */
$(function(){
	
	//启用iCheck
	$('input').iCheck({
		checkboxClass: 'icheckbox_square-blue',
		radioClass: 'iradio_square-blue',
		increaseArea: '20%' // optional
	});
	
	
	/**
	 * 刷新页面设置各个下拉框以及各个输入框的内容均为空
	 */
	$('.reset').click();
	/**
	 * 定义变量
	 */
	var par={
		"areaCode" : ""
	}
	
	/**
	 * 此处的作用是将class是js-example-data-array-selected的select控件可以做select2使用，相当于初始化
	 */
	$(".js-example-data-array-selected").select2({

	});
	/**
	 * 选择省份
	 */
	$('#province').on('change',function(){
		$('#provinceerror').hide();
		$('#provinceerror').removeClass("error").css('color','green').html("√");
		if($(this).val() == ''){
			$('#provinceerror').addClass("error").css('color','red').html("请选择所在地区");
		}
		$('#provinceerror').show();
	});
	
	
	/**
	 * 焦点定位和离开院校名输入框的时候
	 */
	$('#name').on('blur',function(){
		$('#nameerror').removeClass("error").css('color','green').html("√");
		var name = $('#name').val();
		var len = name.length;
		if(len==0){
			$('#nameerror').addClass("error").css('color','red').html("× 院校名不能为空！");
		}else if(len<2 || len >32){
			$('#nameerror').addClass("error").css('color','red').html("× 院校名长度为2到32个字符！");
		}else{
			var fieldName = $('#name').attr('name');

			$.get('HTTP://'+window.location.host+'/school/exist',{"fieldName":fieldName,"fieldValue":name},function(data){
				if(data){
					$('#nameerror').addClass("error").css('color','red').html("× 该院校名称已存在！");
				}
			});
		}
		$('#nameerror').show();
	});
	$('#name').on('focus',function(){
		$('#nameerror').hide();
	});
	
	/**
	 * 焦点定位和离开院校代码输入框的时候
	 */
	$('#code').on('blur',function(){
		$('#codeerror').removeClass("error").css('color','green').html("√");
		var code = $('#code').val();
		var len = code.length;
		if(len==0){
			$('#codeerror').addClass("error").css('color','red').html("× 院校代码不能为空！");
		}else if(len != 6){
			$('#codeerror').addClass("error").css('color','red').html("× 院校代码只能为6个字符！");
		}else{
			var fieldName = $('#code').attr('name');

			$.get('HTTP://'+window.location.host+'/school/exist',{"fieldName":fieldName,"fieldValue":code},function(data){
				if(data){
					$('#codeerror').addClass("error").css('color','red').html("× 该院校代码已存在！");
				}
			});
		}
		$('#codeerror').show();
	});
	$('#code').on('focus',function(){
		$('#codeerror').hide();
	});
	
	/**
	 * 焦点定位和离开手机的输入框的时候
	 */
	$('#beUnderName').on('blur',function(){
		$('#beUnderNameerror').removeClass("error").css('color','green').html("√");
		var beUnderName = $('#beUnderName').val();
		var len = beUnderName.length;
		if(len==0){
			$('#beUnderNameerror').addClass("error").css('color','red').html("× 隶属单位名称不能为空！");
		}else if(len <2 || len > 32){
			$('#beUnderNameerror').addClass("error").css('color','red').html("× 隶属单位名称长度为2到32个字符！");
		}
		$('#beUnderNameerror').show();
	});
	$('#beUnderName').on('focus',function(){
		$('#beUnderNameerror').hide();
	});
	
	/**
	 * 改变学校类型代码
	 */
	$('#type').on('change',function(){
		$('#typeerror').hide();
		$('#typeerror').removeClass("error").css('color','green').html("√");
		var type = $(this).val();
		if(type == ''){
			$('#typeerror').addClass("error").css('color','red').html("× 请选择学校性质！");
		}
		$('#typeerror').show();
	});
	
	/**
	 * 办学类型
	 */
	$('#buildType').on('change',function(){
		$('#buildTypeerror').removeClass("error").css('color','green').html("√");
		var type = $(this).val();
		if(type == ''){
			$('#buildTypeerror').addClass("error").css('color','red').html("× 请选择办学类型！");
		}
		$('#buildTypeerror').show();
	});
	
	/**
	 * 提交表单的时候
	 */
	$('#btn-submit').on('click',function(event){
		event.preventDefault();
		
		//检查是否有空的
		if($('#name').val() == ''){
			$('#nameerror').addClass("error").css('color','red').html("× 院校名不能为空！");
			$('#nameerror').show();
		}
		if($('#code').val() == ''){
			$('#codeerror').addClass("error").css('color','red').html("× 院校代码不能为空！");
			$('#codeerror').show();
		}
		if($('#beUnderName').val() == ''){
			$('#beUnderNameerror').addClass("error").css('color','red').html("× 隶属单位名称不能为空！");
			$('#beUnderNameerror').show();
		}
		if($('#type').val() == ''){
			$('#typeerror').addClass("error").css('color','red').html("× 请选择学校性质！");
			$('#typeerror').show();
		}
		if($('#buildType').val() == ''){
			$('#buildTypeerror').addClass("error").css('color','red').html("× 请选择办学类型！");
			$('#buildTypeerror').show();
		}
		if($('#area').val() == ''){
			$('#areaerror').addClass("error").css('color','red').html("× 请选择所在地区！");
			$('#areaerror').show();
		}
		
		//检查是否有错误标记
		var errorNum = $(".error").length;
		if(errorNum == 0){
			
			//将密码转换成md5密文形式
			var school ={
				"name":"",
				"code":"",
				"beUnderName":"",
				"type":"",
				"buildType":"",
				"areaCode" : "",
				"tags": 0
			}
			school.name = strUniCode($('#name').val());
			school.code = strUniCode($('#code').val());
			school.beUnderName = strUniCode($('#beUnderName').val());
			school.type = strUniCode($('#type').val());
			school.buildType = strUniCode($('#buildType').val());
			school.type = strUniCode($('#type').val());
			school.areaCode = $('#province').val();
			
			$('input[name="tags"]:checked').each(function(){
				school.tags += parseInt(this.value,10);
			 }); 
			
			$.post("HTTP://"+window.location.host+"/school/add",school,function(data){
				
				if(data==true){
					$('#myModal').modal('show');
					setTimeout(function(){
						location.href="HTTP://"+window.location.host+"/school/list.html";
					}, 3000);
				}else{
					$('.modal-body').empty().append("提交失败，即将返回添加页面&hellip;");
					$('#myModal').modal('show');
					setTimeout(function(){
						location.href="HTTP://"+window.location.host+"/school/add.html";
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