/**
 * detail.js
 */
$(function(){
	
	
	$('.reset').click();
	
	/**
	 * 定义变量
	 */
	var par={
		"eduLevel" : ""
	}
	
	
	/**
	 * 改变eduLevel
	 */
	$('#eduLevel').on('click',function(){
		//移出原来的值
		if($('#eduLevel').find("option").length==2){
			var eduLevel = '<option value="">请选择</option><option value="1">研究生专业</option><option value="2">本科生专业</option><option value="3">专科生专业</option>';
			$('#eduLevel').empty().append(eduLevel);
		}
	});
	
	/**
	 * 改变eduLevel
	 */
	$('#firstCode').on('click',function(){
		var eduLevel = $('#eduLevel').val();
		if( eduLevel == ''){
			return;
		}
		//移出原来的值
		if($('#firstCode').find("option").length==2){
			var zydl = '<option value="">请选择</option>';
			$.get('HTTP://' + window.location.host +'/edupubcode/zydl/list',{"eduLevel":eduLevel},function(data){
				var zydlHtml = '<option value="">请选择</option>';
				for(var i = 0; i< data.length; i++){
					zydlHtml += '<option value="'+data[i].code+'">'+ data[i].name+'</option>';
				}
				$('#firstCode').empty().append(zydlHtml);
			});
		}
	});
	
	/**
	 * 改变eduLevel
	 */
	$('#secondCode').on('click',function(){
		var eduLevel = $('#eduLevel').val();
		var firstCode = $('#firstCode').val();
		if( eduLevel == '' || firstCode == ''){
			return;
		}
		//移出原来的值
		if($('#secondCode').find("option").length==2){
			var zyzl = '<option value="">请选择</option>';
			$.get('HTTP://' + window.location.host +'/edupubcode/zydl/list',{"eduLevel":eduLevel,"firstCode":firstCode},function(data){
				var zyzlHtml = '<option value="">请选择</option>';
				for(var i = 0; i< data.length; i++){
					zyzlHtml += '<option value="'+data[i].code+'">'+data[i].name+'</option>';
				}
				$('#secondCode').empty().append(zyzlHtml);
			});
		}
	});
	
	/**
	 * 改变eduLevel
	 */
	$('#eduLevel').on('change',function(){
		
		par.eduLevel = $(this).val();
		$('#eduLevelerror').hide();
		$('#eduLevelerror').removeClass("error").css('color','green').html("√");
		if(par.eduLevel == ''){
			$('#firstCode').val("").trigger("change");
			$('#secondCode').val("").trigger("change");
			return;
		}else{
			var edupubcode = {
					"eduLevel": "",
					"keyword": ""
			}
			edupubcode.eduLevel = par.eduLevel;
			$.get('HTTP://'+ window.location.host + '/edupubcode/zydl/list',edupubcode,function(data){
				//移出原来的值
				$('#firstCode').find("option:not(:first)").remove();
				//重新添加select内容
				var zydlHtml = "",maxlength = 0;
				for(var i = 0; i < data.length; i++){
					zydlHtml+="<option value='"+data[i].code+"'>"+data[i].name+"</option>";
					if(data[i].name.length > maxlength){
						maxlength = data[i].name.length;
					}
				}
				$('#firstCode').append(zydlHtml);
				//设置area的长度
				//$('#firstCode').next('span').css("width", (maxlength*14+28+maxlength-1) < 93 ? 93 : (maxlength*14+28+maxlength-1));  
				$('#firstCode').val("").trigger("change");
				$('#secondCode').val("").trigger("change");
			});
		}
		$('#eduLevelerror').show();
	});
	
	/**
	 * 改变firstCode
	 */
	$('#firstCode').on('change',function(){
		var firstCode = $(this).val();
		$('#firstCodeerror').hide();
		$('#firstCodeerror').removeClass("error").css('color','green').html("√");
		if(firstCode == ''){
			$('#secondCode').val("").trigger("change");
			return;
		}else{
			var edupubcode = {
					"eduLevel": "",
					"firstCode": "",
					"keyword": ""
			}
			edupubcode.eduLevel = par.eduLevel;
			edupubcode.firstCode = firstCode;
			$.get('HTTP://'+ window.location.host + '/edupubcode/zyzl/list',edupubcode,function(data){
				//移出原来的值
				$('#secondCode').find("option:not(:first)").remove();
				//重新添加select内容
				var zyzlHtml = "",maxlength = 0;
				for(var i = 0; i < data.length; i++){
					zyzlHtml+="<option value='"+data[i].code+"'>"+data[i].name+"</option>";
					if(data[i].name.length > maxlength){
						maxlength = data[i].name.length;
					}
				}
				$('#secondCode').append(zyzlHtml);
				//设置area的长度
				//$('#secondCode').next('span').css("width", (maxlength*14+28+maxlength-1) < 93 ? 93 : (maxlength*14+28+maxlength-1));  
				$('#secondCode').val("").trigger("change");
			});
		}
		$('#firstCodeerror').show();
	});
	
	/**
	 * 选择专业学科中类
	 */
	$('#secondCode').on('change',function(){
		$('#secondCodeerror').hide();
		$('#secondCodeerror').removeClass("error").css('color','green').html("√");
		if($(this).val() == ''){
			return;
		}
		$('#secondCodeerror').show();
	});
	
	/**
	 * 焦点专业名输入框的时候
	 */
	$('#name').on('blur',function(){
		$('#nameerror').removeClass("error").css('color','green').html("√");
		var name = $.trim($('#name').val());
		var len = name.length;
		if(len==0){
			$('#nameerror').addClass("error").css('color','red').html("× 专业名不能为空！");
		}else if(len < 2 || len > 32){
			$('#nameerror').addClass("error").css('color','red').html("× 专业名长度只能为2到32个字符！");
		}else if(name != $('#name').attr('data-value')){
			var fieldName = $('#name').attr('name');

			$.get('HTTP://'+window.location.host+'/edupubcode/exist',{"eduLevel":$('#eduLevel').val(),"fieldName":fieldName,"fieldValue":name},function(data){
				if(data){
					$('#nameerror').addClass("error").css('color','red').html("× 专业名称已存在！");
				}
			});
		}
		$('#nameerror').show();
	});
	$('#name').on('focus',function(){
		$('#nameerror').hide();
	});
	
	/**
	 * 提交表单的时候
	 */
	$('#btn-submit').on('click',function(event){
		event.preventDefault();
		//检查是否有空的
		if($('#eduLevel').val() == ''){
			$('#eduLevelerror').addClass("error").css('color','red').html("× 请选择学历类别！");
			$('#eduLevelerror').show();
		}
		if($('#firstCode').val() == ''){
			$('#firstCodeerror').addClass("error").css('color','red').html("× 请选择专业学科大类！");
			$('#firstCodeerror').show();
		}
		if($('#secondCode').val() == ''){
			$('#secondCodeerror').addClass("error").css('color','red').html("× 请选择专业学科中类！");
			$('#secondCodeerror').show();
		}
		if($('#name').val() == ''){
			$('#nameerror').addClass("error").css('color','red').html("× 专业名不能为空！");
			$('#nameerror').show();
		}
		if($('#code').val() == ''){
			$('#codeerror').addClass("error").css('color','red').html("× 专业代码不能为空！");
			$('#codeerror').show();
		}
		
		//检查是否有错误标记
		var errorNum = $(".error").length;
		if(errorNum == 0){
			
			//将密码转换成md5密文形式
			var eduPubCode ={
				"name":"",
				"code":"",
				"eduLevel":"",
				"firstCode":"",
				"firstName" : "",
				"secondCode":"",
				"secondName": ""
				
			}
			eduPubCode.id = strUniCode($('#id').val());
			eduPubCode.name = strUniCode($('#name').val());
			eduPubCode.code = strUniCode($('#code').val());
			eduPubCode.eduLevel = strUniCode($('#eduLevel').val());
			eduPubCode.firstCode = strUniCode($('#firstCode').val());
			eduPubCode.firstName = strUniCode($('#firstCode option:selected').text());
			eduPubCode.secondCode = strUniCode($('#secondCode').val());
			eduPubCode.secondName = strUniCode($('#secondCode option:selected').text());
			
			
			$.post("HTTP://"+window.location.host+"/edupubcode/update",eduPubCode,function(data){
				
				if(data==true){
					$('#myModal').modal('show');
					setTimeout(function(){
						location.href="HTTP://"+window.location.host+"/edupubcode/list.html";
					}, 3000);
				}else{
					$('.modal-body').empty().append("提交失败，即将返回添加页面&hellip;");
					$('#myModal').modal('show');
					setTimeout(function(){
						location.href="HTTP://"+window.location.host+"/edupubcode/list.html";
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