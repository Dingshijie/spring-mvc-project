
$(function(){
	
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
	var aria_control = "manager";
	
	/**
	 * 此处的作用是将class是js-example-data-array-selected的select控件可以做select2使用，相当于初始化
	 */
	$(".js-example-data-array-selected").select2({

	});
	
	/**
	 * 选择省份
	 */
	$('#province').on('change',function(){
		par.areaCode = $(this).val() || ""; 
		if(par.areaCode==""){
			return;
		}
		$.get("HTTP://"+window.location.host+"/area/list/city",par,function(cityData){
			
			//省份改变且不为空的时候直接将地区的不能为空的信息和学校不能为空的信息隐藏
			$('#areaerror').hide();
			
			if(cityData != ""){
				//移出原来的数据（除了第一个，即：请选择）
				$('#city').find("option:not(:first)").remove();
				$('#area').find("option:not(:first)").remove();
				//向select中新增加数据
				var cityHtml = "",maxlength = 0;
				for(var i = 0; i < cityData.length; i++){
					cityHtml+="<option value='"+cityData[i].code+"'>"+cityData[i].name+"</option>";
					if(cityData[i].name.length > maxlength){
						maxlength = cityData[i].name.length;
					}
				}
				$('#city').append(cityHtml);
				//设置city的select的长度，这里不必重新设置area的长度，因为它的值暂未变化
				$('#city').next('span').css("width", (maxlength*14+28+maxlength-1) < 93 ? 93 : (maxlength*14+28+maxlength-1));  
				
			}
		});
	});
	
	
	/**
	 * 选择市
	 */
	$('#city').on('change',function(){
		par.areaCode = $(this).val() || "";
		if(par.areaCode==""){
			return;
		}
		$.get("HTTP://"+window.location.host+"/area/list/area",par,function(data){
			//移出原来的值
			$('#area').find("option:not(:first)").remove();
			//重新添加select内容
			var areaHtml = "",maxlength = 0;
			for(var i = 0; i < data.length; i++){
				areaHtml+="<option value='"+data[i].code+"'>"+data[i].name+"</option>";
				if(data[i].name.length > maxlength){
					maxlength = data[i].name.length;
				}
			}
			$('#area').append(areaHtml);
			//设置area的长度
			$('#area').next('span').css("width", (maxlength*14+28+maxlength-1) < 93 ? 93 : (maxlength*14+28+maxlength-1));  
			
		});
		//重置市、区的值
		$('#area').val("").trigger("change");
	});
	
	/**
	 * 焦点定位和离开用户名输入框的时候
	 */
	$('#username').on('blur',function(){
		$('#usernameerror').removeClass("error").css('color','green').html("√");
		var username = $.trim($('#username').val());
		var len = username.length;
		if(len==0){
			$('#usernameerror').addClass("error").css('color','red').html("× 用户名不能为空！");
		}else if(len<2 || len >24){
			$('#usernameerror').addClass("error").css('color','red').html("× 用户名长度应为2到24个字符！");
		}else{
			var fieldName = $('#username').attr('name');

			$.get('HTTP://'+window.location.host+'/user/exist',{"fieldName":fieldName,"fieldValue":username},function(data){
				if(data){
					$('#usernameerror').addClass("error").css('color','red').html("× 该用户名已被注册！");
				}
			});
		}
		$('#usernameerror').show();
	});
	$('#username').on('focus',function(){
		$('#usernameerror').hide();
	});
	
	/**
	 * 焦点定位和离开密码输入框的时候
	 */
	$('#password').on('blur',function(){
		$('#passworderror').removeClass("error").css('color','green').html("√");
		var len = $.trim($('#password').val()).length;
		if(len==0){
			$('#passworderror').addClass("error").css('color','red').html("× 密码不能为空！");
		}else if(len<6 || len >128){
			$('#passworderror').addClass("error").css('color','red').html("× 密码长度应为6到128个字符！");
		}
		$('#passworderror').show();
	});
	$('#password').on('focus',function(){
		$('#passworderror').hide();
	});
	
	/**
	 * 焦点定位和离开手机的输入框的时候
	 */
	$('#mobilPhone').on('blur',function(){
		$('#mobilPhoneerror').removeClass("error").css('color','green').html("√");
		var mobilePhone = $.trim($('#mobilPhone').val());
		var len = mobilePhone.length;
		var reg = /^(1[0-9]{10})?$/;
		if(len!=11){
			$('#mobilPhoneerror').addClass("error").css('color','red').html("× 手机号长度只能为11位！");
		}else if(!reg.test(mobilePhone)){
			$('#mobilPhoneerror').addClass("error").css('color','red').html("× 手机号格式不正确！");
		}else{
			var fieldName = $('#mobilPhone').attr('name');

			$.get('HTTP://'+window.location.host+'/user/exist',{"fieldName":fieldName,"fieldValue":mobilePhone},function(data){
				if(data){
					$('#mobilPhoneerror').addClass("error").css('color','red').html("× 该手机已被注册！");
				}
			});
		}
		$('#mobilPhoneerror').show();
	});
	$('#mobilPhone').on('focus',function(){
		$('#mobilPhoneerror').hide();
	});
	
	
	$('#area').on('change',function(){
		$('#areaerror').removeClass("error").css('color','green').html("√");
		var areaCode = $('#area').val();
		if(areaCode == ''){
			$('#areaerror').addClass("error").css('color','red').html("× 请选择地区！");
		}
		$('#areaerror').show();
	});
	/**
	 * 焦点定位和离开地址的输入框的时候
	 */
	$('#address').on('blur',function(){
		$('#addresserror').removeClass("error").css('color','green').html("√");
		var len = $.trim($('#address').val()).length;
		if(len==0){
			$('#addresserror').addClass("error").css('color','red').html("× 街道地址长度不能为空！");
		}else if(len>128){
			$('#addresserror').addClass("error").css('color','red').html("× 街道地址长度应为最多为128个字符！");
		}
		$('#addresserror').show();
	});
	$('#address').on('focus',function(){
		$('#addresserror').hide();
	});
	
	

	/**
	 * 提交表单的时候
	 */
	$('#btn-submit').on('click',function(event){
		event.preventDefault();
		//检查是否有空的
		if($('#username').val() == ''){
			$('#usernameerror').addClass("error").css('color','red').html("× 用户名不能为空！");
			$('#usernameerror').show();
		}
		if($('#password').val() == ''){
			$('#passworderror').addClass("error").css('color','red').html("× 密码不能为空！");
			$('#passworderror').show();
		}
		if($('#mobilPhone').val() == ''){
			$('#mobilPhoneerror').addClass("error").css('color','red').html("× 手机长度不能为空！");
			$('#mobilPhoneerror').show();
		}
		if($('#area').val() == ''){
			$('#areaerror').addClass("error").css('color','red').html("× 请选择所在地区！");
			$('#areaerror').show();
		}
		if($('#address').val() == ''){
			$('#addresserror').addClass("error").css('color','red').html("× 街道地址长度不能为空！");
			$('#addresserror').show();
		}
		//检查是否有错误标记
		var errorNum = $(".error").length;
		if(errorNum == 0){
			
			//将密码转换成md5密文形式
			var user ={
				"username":"",
				"password":"",
				"mobilPhone":"",
				"areaCode":"",
				"address":"",
				"areaName" : "",
			}
			user.username = strUniCode($('#username').val());
			user.password = hex_md5($('#password').val());
			user.role = aria_control.toUpperCase();
			user.mobilPhone = strUniCode($('#mobilPhone').val());
			user.areaCode = $('#area option:selected').val();
			var city = ($('#city option:selected').text() == '市辖区' || $('#city option:selected').text() == '县') ? '' : $('#city option:selected').text();
			user.areaName = $('#province option:selected').text() + city + $('#area option:selected').text();
			user.address = strUniCode($('#address').val());
			
			$.post("HTTP://"+window.location.host+"/user/add",user,function(data){
				
				if(data==true){
					$('#myModal').modal('show');
					setTimeout(function(){
						location.href="HTTP://"+window.location.host+"/user/list.html";
					}, 3000);
				}else{
					$('.modal-body').empty().append("提交失败，即将返回添加页面&hellip;");
					$('#myModal').modal('show');
					setTimeout(function(){
						location.href="HTTP://"+window.location.host+"/user/add.html";
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