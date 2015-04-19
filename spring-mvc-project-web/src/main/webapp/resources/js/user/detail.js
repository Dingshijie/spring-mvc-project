
$(function(){
	
	/**
	 * 定义变量
	 */
	var par={
		"areaCode" : ""
	}
	/**
	 *点击切换tab按钮
	 */
	$('#myTab a').on('click',function(){
		
		//$('.error').hide().removeClass('error').html("");
		//设置显示的内容
		aria_control = $(this).attr('aria-controls');
		$('#myTab a[href="#'+aria_control+'"]').tab('show');
		if(aria_control == 'baseinfo'){
			$('#baseinfo').show();
			$('#contactinfo').hide();
		}else if(aria_control == 'contactinfo'){
			$('#contactinfo').show();
			$('#baseinfo').hide();
		}
	});
	
	/**
	 * 此处的作用是将class是js-example-data-array-selected的select控件可以做select2使用，相当于初始化
	 */
	$(".js-example-data-array-selected").select2({

	});
	
	/**
	 * 设置鼠标移动和移出td时的效果
	 */
	$('#mainList').find('table td').hover(function(){
		if($(this).children('.edit').is(':hidden')){
		$(this).children('a').show();
		}else{
			$(this).children('a').hide();
			}
		},function(){
		$(this).children('a').hide();
	});
	
	/**
	 * 设置点击编辑图标时的效果
	 */
	$('#mainList').on('click',function(event){
		
		var target = event.target || event.srcElement;
		var $obj = null;
		
		if(target.nodeName.toLowerCase() == 'a'){
	
		}else if(target.nodeName.toLowerCase() == 'span'){
			$obj = $(target);
			//点击修改的图标的时候
			if($obj.hasClass('update')){
				$obj.parent().hide().siblings('.value').hide().siblings('button,.edit').show().siblings('.help-block').removeClass('.error').html('').hide();
			}
		}else if(target.nodeName.toLowerCase() == 'button'){
			$obj = $(target);
			td = $obj.closest('td');
			//点击修改的图标的时候
			if($obj.hasClass('update-cancel')){
				$obj.hide().siblings('button,.edit').hide().siblings('a,.value').show().siblings('span,.help-block').html('');
			}
			if($obj.hasClass('update-sure')){

				//获取新的值，并判断是否合法，然后提交
				var field = td.find('.edit>input').attr('name');
				
				var olddata = $.trim(td.find('.value>span').text());
				
				var newdata = $.trim(td.find('.edit>input').val());
				if(olddata==newdata){

					td.find('.help-block').addClass("error").html("× 并未做任何修改").show();
					return;
					
				}
				var result = checkValue(field,newdata);
				if(result){
					
					var id = $('#id').val();
					//转码
					newdata = strUniCode(newdata);
					var paramter = "{'"+field+"':'"+ newdata +"'}";
					//更新数据库
					$.post('HTTP://'+window.location.host+'/user/update',{"paramter":paramter,'id':id},function(data){
						if(data){
							//更新成功后为span直接赋新值
							td.find('.value>span').text(strUnUniCode(newdata));
						}else{
							alert('更新失败!');
						}
						$obj.hide().siblings('button,.edit').hide().siblings('a,.value').show();
					});
				}
			}
			if($obj.hasClass('update-select')){
				//找到最后一个select框，获取新的值，并判断是否合法，然后提交
				var item = td.find('.edit>select').last();//获取选中的select框
				
				var field = item.attr('name');
				
				var newdata = item.find('option:selected').val();
				
				var olddata = td.find('.value>span').attr('data-code');
				
				if(olddata==newdata){
					td.find('.help-block').addClass("error").html("× 并未做任何修改").show();
					return;
				}
				var id = $('#id').val();
				var paramter = "{";
				if(field == 'eduLevel'){
					//如果改变的是学历层次的时候，专业代码和专业名称同时也给与改变
					paramter += "'eduPubCode':'','eduPubName':'','"+ field +"':'"+ newdata +"'";
				}else if(field == 'eduPubCode'){
					var newname = item.find('option:selected').text();
					paramter += "'eduPubCode':'" + newdata + "','eduPubName':'" + newname + "'";
				}
				paramter += "}";
				
				//更新数据库
				$.post('HTTP://'+window.location.host+'/user/update',{"paramter":paramter,'id':id},function(data){
					if(data){
						//更新成功后为span直接赋新值
						if(newdata==''){
							td.find('.value>span').attr('data-code',"");
							td.find('.value>span').text("");
						}else{

							td.find('.value>span').attr('data-code',item.find('option:selected').val());
							td.find('.value>span').text(item.find('option:selected').text());
							if(field == 'eduLevel'){
								var edupubcode_td = $('#eduPubName').closest('td');
								edupubcode_td.find('.value>span').attr('data-code',"");
								edupubcode_td.find('.value>span').text("");
								edupubcode_td.find('.edit').hide();
								$('#eduPubNameerror').addClass("error").html("× 请重新设置对应学历的专业").show();
							}
						}
					}else{
						alert('更新失败!');
					}
					$obj.hide().siblings('button,.edit').hide().siblings('a,.value').show();
				});
				
			}
		}
		
	});
	
	
	function checkValue(field, value){
		if(field=='realName'){
			$('#realNameerror').removeClass("error").html('');
			var len = value.length;
			if(len > 16){
				$('#realNameerror').addClass("error").html("× 长度最大为16位");
			}else{
				return true;
			}
			$('#realNameerror').show();
			return false;
		}else if(field=='idCardNum'){
			$('#idCardNumerror').removeClass("error").html('');
			var len = value.length;
			if(len!=18){
				$('#idCardNumerror').addClass("error").html("× 长度只能为18位");
			}else{
				return true;
			}
			$('#idCardNumerror').show();
			return false;
		}else if(field=='mobilPhone'){
			if($('#mobilPhoneerror').hasClass("error")){
				return false;
			}
			var len = value.length;
			var reg = /^(1[0-9]{10})?$/;
			if(len!=11){
				$('#mobilPhoneerror').addClass("error").html("× 长度只能为11位");
			}else if(!reg.test(value)){
				$('#mobilPhoneerror').addClass("error").html("× 格式不正确！");
			}else{
				return true;
			}
			$('#mobilPhoneerror').show();
			return false;
		}else if(field=='telPhone'){
			$('#telPhoneerror').removeClass("error").html('');
			var len = value.length;
			var reg = /^(((0\d{2,3})-)?(\d{7,8})(-(\d{3,5}))?)?$/;
			if(len > 24){
				$('#telPhoneerror').addClass("error").html("× 长度最大为24位");
			}else if(!reg.test(value)){
				$('#telPhoneerror').addClass("error").html("× 格式不正确！");
			}else{
				return true;
			}
			$('#telPhoneerror').show();
			return false;
		}else if(field=='email'){
			if($('#emailerror').hasClass("error")){
				return false;
			}
			var len = value.length;
			var reg = /^(\w+(\.\w+)*@\w+(\.\w+)+)?$/i;
			if(len > 32){
				$('#emailerror').addClass("error").html("× 长度最大为32位");
			}else if(!reg.test(value)){
				$('#emailerror').addClass("error").html("× 格式不正确！");
			}else{
				return true;
			}
			$('#emailerror').show();
			return false;
		}else if(field=='address'){
			$('#addresserror').removeClass("error").html('');
			var len = value.length;
			if(len > 128){
				$('#addresserror').addClass("error").html("× 长度最大为128位");
			}else{
				return true;
			}
			$('#addresserror').show();
			return false;
		}
	}
	
	$('#mobilPhone').on('focus',function(){
		$('#mobilPhoneerror').removeClass("error").html('');
	});
	$('#mobilPhone').on('blur',function(){
		var fieldName = $('#mobilPhone').attr('name');
		var mobilePhone = $('#mobilPhone').val();
		$.get('HTTP://'+window.location.host+'/user/exist',{"fieldName":fieldName,"fieldValue":mobilePhone},function(data){
			if(data){
				$('#mobilPhoneerror').addClass("error").html("× 该手机已被注册");
			}
		});
	});
	
	$('#email').on('focus',function(){
		$('#emailerror').removeClass("error").html('');
	});
	$('#email').on('blur',function(){
		var fieldName = $('#email').attr('name');
		var email = $('#email').val();
		$.get('HTTP://'+window.location.host+'/user/exist',{"fieldName":fieldName,"fieldValue":email},function(data){
			if(data){
				$('#emailerror').addClass("error").html("× 该邮箱已被注册");
			}
		});
	});
	
	//字符串编码
	function strUniCode(value){
		return value.replace(/>/g,"&gt").replace(/</g,"&lt");
	};
	//字符串转码
	function strUnUniCode(value){
		return value.replace(/&gt/g,">").replace(/&lt/g,"<");
	};
	
	
	//点击学校修改
	$('#schoolName').on('click',function(){
		//将学校显示出来，并定位
		$('.school').show();

		var schoolCode = $(this).closest('td').find('.value>span').attr('data-code');
		par.areaCode = $('#btn-area').closest('td').find('.value>span').attr('data-code').substring(0,2);
		
		$('#school').find("option:not(:first)").remove();
		$.get('HTTP://'+window.location.host+'/school/list/'+par.areaCode,function(schoolData){
			if(schoolData != ''){
				$('#school').find("option:not(:first)").remove();
				var schoolHtml = "";
				for(var i = 0; i < schoolData.length; i++){
					if(schoolCode==schoolData[i].code){
						//设置默认选中项
						schoolHtml+="<option value='"+schoolData[i].code+"' selected='selected'>"+schoolData[i].name+"</option>";
						$('#select2-school-container').html(schoolData[i].name);
						
					}else{
						schoolHtml+="<option value='"+schoolData[i].code+"'>"+schoolData[i].name+"</option>";
					}
				}
				$('#school').append(schoolHtml);
			}
		});
		$('#school').val("").trigger("change");
		
		$('#areaName').click();
		
	});
	
	//点击地区修改
	$('#areaName').on('click',function(){

		var areaCode = $(this).closest('td').find('.value>span').attr('data-code');
		//设置省份下拉框的初始值
		$('#province').find("option[value='"+areaCode.substring(0,2)+"']").attr("selected",true);
		$('#select2-province-container').html($('#province').find('option[value='+areaCode.substring(0,2)+']').text());
		//加载市区的初始值
		
		$.get("HTTP://"+window.location.host+"/area/list/city",{"areaCode" : areaCode.substring(0,2)},function(cityData){
			//向select中新增加数据
			var cityHtml = "",maxlength = 0;
			for(var i = 0; i < cityData.length; i++){
				if(areaCode.substring(0,4)==cityData[i].code){
					//设置默认选中项
					cityHtml+="<option value='"+cityData[i].code+"' selected='selected'>"+cityData[i].name+"</option>";
					$('#select2-city-container').html(cityData[i].name);
				}else{
					cityHtml+="<option value='"+cityData[i].code+"'>"+cityData[i].name+"</option>";
				}
				if(cityData[i].name.length > maxlength){
					maxlength = cityData[i].name.length;
				}
			}
			$('#city').append(cityHtml);
			//设置city的select的长度，这里不必重新设置area的长度，因为它的值暂未变化
			$('#city').next('span').css("width", (maxlength*14+28+cityData.length-1) < 93 ? 93 : (maxlength*14+28+cityData.length-1));  
			

			$.get("HTTP://"+window.location.host+"/area/list/area",{"areaCode" : areaCode.substring(0,4)},function(areaData){
				//向select中新增加数据
				var areaHtml = "",maxlength = 0;
				for(var i = 0; i < areaData.length; i++){
					if(areaCode==areaData[i].code){
						//设置默认选中项
						areaHtml+="<option value='"+areaData[i].code+"' selected='selected'>"+areaData[i].name+"</option>";
						$('#select2-area-container').html(areaData[i].name);
					}else{
						areaHtml+="<option value='"+areaData[i].code+"'>"+areaData[i].name+"</option>";
					}
					if(areaData[i].name.length > maxlength){
						maxlength = areaData[i].name.length;
					}
				}
				$('#area').append(areaHtml);
				//设置city的select的长度，这里不必重新设置area的长度，因为它的值暂未变化
				$('#area').next('span').css("width", (maxlength*14+28+areaData.length-1) < 93 ? 93 : (maxlength*14+28+areaData.length-1));  
				
			});
		});
		
		
		//点击时候出现的未选择学校或者由于相应速度问题导致学校没有选，默认将这些屏蔽掉
		$('#schoolerror').removeClass("error").html('');
		$('#schoolerror').hide();
		//显示修改地区的弹出框
		$('#myModal').modal('show');
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
			$('#schoolerror').hide();
			
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
				$('#city').next('span').css("width", (maxlength*14+28+cityData.length-1) < 93 ? 93 : (maxlength*14+28+cityData.length-1));  
				
				//如果是学生用户注册，需要填写学校
				if($('#province').attr('data-role') == 'STUDENT'){
				
					$('#school').find("option:not(:first)").remove();
					
				
					$.get('HTTP://'+window.location.host+'/school/list/'+par.areaCode,function(schoolData){
						if(schoolData != ''){
							$('#area').val("").trigger("change");
							$('#school').find("option:not(:first)").remove();
							var schoolHtml = "";
							for(var i = 0; i < schoolData.length; i++){
								schoolHtml+="<option value='"+schoolData[i].code+"'>"+schoolData[i].name+"</option>";
							}
							$('#school').append(schoolHtml);
						}
					});
					
					$('.school').show();
					
					//将后面两个的值设置会请选择的样式
					//之所以这种放置，是放置中间请求阶段时间较长，导致获取学校的时候会拿不到areaCode的值
					$('#city').val("").trigger("change");
					$('#area').val("").trigger("change");
					$('#school').val("").trigger("change");
				}else{
					//将后面两个的值设置会请选择的样式
					$('#city').val("").trigger("change");
					$('#area').val("").trigger("change");
				}
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
			$('#area').next('span').css("width", (maxlength*14+28+data.length-1) < 93 ? 93 : (maxlength*14+28+data.length-1));  
			
		});
		//重置市、区的值
		$('#area').val("").trigger("change");
	});
	
	/**
	 * 学校下拉框改变
	 */
	$('#school').on('change',function(){
		
		//目前学校的下拉框改变的时候可以暂时不做处理
		
	});
	
	/**
	 * 点击关闭按钮时候
	 */
	$('.btn-close').on('click',function(){
		
		//隐藏列表中的学校
		$('.school').hide();
		
		$('#btn-area').click();
		$('#btn-school').click();
	});
	
	/**
	 * 点击保存按钮时候
	 */
	$('.btn-save').on('click',function(){
		
		
		
		if($('#area option:selected').val() == ""){
			$('.alert').addClass('alert-danger').html('地区不能为空');
			$('.alert').show();
			setTimeout(function(){
				$('.alert').removeClass('alert-danger').removeClass('alert-success').css("display","none");
			},2000);
			return;
		}
		
		if($('#province').attr('data-role') == 'STUDENT' && $('.school').css("display") != "none"){
			if($('#school option:selected').val()==""){
				$('.alert').addClass('alert-danger').html('学校不能为空');
				$('.alert').show();
				setTimeout(function(){
					$('.alert').removeClass('alert-danger').removeClass('alert-success').css("display","none");
				},2000);
				return;
			}
		}
		
		
		var td_area = $('#btn-area').closest('td');
		var oldcode = td_area.find('.value>span').attr('data-code');
		var newcode = $('#area').val();
		var cityName = ($('#city option:selected').text() == '市辖区' || $('#city option:selected').text() == '县') ? '' : $('#city option:selected').text();
		var newname = $('#province option:selected').text() + cityName + $('#area option:selected').text();

		var td_school = $('#btn-school').closest('td');
		var oldSchoolCode = '';
		var newSchoolCode = '';
		var newSchoolName = '';
		
		var paramter="{";
		
		//如果是学生用户
		if($('#province').attr('data-role') == 'STUDENT' && $('.school').css("display") != "none"){
			//判断学校是否已经改变，如果已经改变，需要将学校的更新
			oldSchoolCode = td_school.find('.value>span').attr('data-code');
			newSchoolCode = $('#school').val();
			newSchoolName = $('#school option:selected').text();
			
			if(oldSchoolCode != newSchoolCode){
				if(paramter != "{") paramter += ",";
				paramter +="'schoolCode':'"+ newSchoolCode +"','schoolName':'"+ newSchoolName +"'";
			}
			
		}
		//判断地区是否已经改变
		if(oldcode != newcode){
			//如果改变了，需要更新
			if(paramter != "{") paramter += ",";
			paramter +="'areaCode':'"+ newcode +"','areaName':'"+ newname +"'";
		}
		paramter +="}";
		
		//隐藏列表中的学校
		$('.school').hide();
		//关闭模态框
		$('#myModal').modal('hide');
		//如果有改变
		if(paramter!="{}"){
			var id = $('#id').val();
			//更新数据库
			$.post('HTTP://'+window.location.host+'/user/update',{"paramter":paramter,'id':id},function(data){
				if(data){
					td_area.find('.value>span').attr('data-code',newcode);
					td_area.find('.value>span').html(newname);
					td_area.find('.edit>input').attr('value',newname);
					
					td_school.find('.value>span').attr('data-code',oldSchoolCode);
					td_school.find('.value>span').html(newSchoolName);
					td_school.find('.edit>input').attr('value',newSchoolName);
					
					$('#alertModal').find('.modal-body').empty().append('更新成功');
					$('#alertModal').modal('show');
				}else{
					$('#alertModal').find('.modal-body').empty().append('更新失败');
					$('#alertModal').modal('show');
				}
			});
		}
		
		$('#btn-area').click();		
		$('#btn-school').click();
	});
	
	/**
	 * 如果改动学历层次的时候，去掉这些错误提示信息
	 */
	$('#eduLevel').on('change',function(){	
		$(this).closest('td').find('.help-block').removeClass("error").html("").hide();
	});
	
	/**
	 * 点击专业后面的 编辑标志时，加载专业大类
	 */
	$('#eduPubName').on('click',function(){
		
		$('#zydl').find("option:not(:first)").remove();
		$('#zyzl').find("option:not(:first)").remove();
		$('#zyxl').find("option:not(:first)").remove();
		var edulevel = $('#eduLevel').val();
		$.get('HTTP://'+ window.location.host +'/edupubcode/zydl/list',{"eduLevel":edulevel,"keyword":""},function(data){
			
			var zydlHTML = "",maxlength = 0;
			for(var i = 0;i < data.length; i++){
				zydlHTML += "<option value='"+ data[i].code +"'>"+ data[i].name +"</option>";
				if(data[i].name.length > maxlength){
					maxlength = data[i].name.length;
				}
			}
			$('#zydl').append(zydlHTML);
			$('#zydl').next('span').css("width", (maxlength*14+28+maxlength-1) < 150 ? 150 : (maxlength*14+28+maxlength-1));
			
			$('#zydl').val("").trigger("change");
			$('#zyzl').val("").trigger("change");
			$('#zyxl').val("").trigger("change");
		});
		
	});
	
	/**
	 * 修改专业大类的时候
	 */
	$('#zydl').on('change',function(){
		if($(this).val()==""){
			return;
		}
		
		$('#zyzl').find("option:not(:first)").remove();
		$('#zyxl').find("option:not(:first)").remove();
		var edulevel = $('#eduLevel').val();
		var firstCode = $('#zydl').val();
		$.get('HTTP://'+ window.location.host +'/edupubcode/zyzl/list',{"eduLevel":edulevel,"firstCode":firstCode,"keyword":""},function(data){
			
			var zyzlHTML = "",maxlength = 0;
			for(var i = 0;i < data.length; i++){
				zyzlHTML += "<option value='"+ data[i].code +"'>"+ data[i].name +"</option>";
				if(data[i].name.length > maxlength){
					maxlength = data[i].name.length;
				}
			}
			$('#zyzl').append(zyzlHTML);
			$('#zyzl').next('span').css("width", (maxlength*14+28+maxlength-1) < 150 ? 150 : (maxlength*14+28+maxlength-1));
			
			$('#zyzl').val("").trigger("change");
			$('#zyxl').val("").trigger("change");
		});
		
	});
	
	/**
	 * 改变专业中类的时候加载专业小类
	 */
	$('#zyzl').on('change',function(){
		if($(this).val()==""){
			return;
		}
		
		$('#zyxl').find("option:not(:first)").remove();
		var edulevel = $('#eduLevel').val();
		var secondCode = $('#zyzl').val();
		$.get('HTTP://'+ window.location.host +'/edupubcode/zyxl/list',{"eduLevel":edulevel,"secondCode":secondCode,"keyword":""},function(data){
			
			var zyxlHTML = "",maxlength = 0;
			for(var i = 0;i < data.length; i++){
				zyxlHTML += "<option value='"+ data[i].code +"'>"+ data[i].name +"</option>";
				if(data[i].name.length > maxlength){
					maxlength = data[i].name.length;
				}
			}
			$('#zyxl').append(zyxlHTML);
			$('#zyxl').next('span').css("width", (maxlength*14+28+maxlength-1) < 150 ? 150 : (maxlength*14+28+maxlength-1));
		
			$('#zyxl').val("").trigger("change");
		});
	});
	/**
	 * 设置为商家用户
	 */
	$('.btn-bussiness').on('click',function(){
		var id = $("#id").val();
		var paramter = "{'role':'BUSSINESS'}"; 
		$.post('HTTP://'+window.location.host+'/user/update',{"id":id,"paramter":paramter},function(data){
			if(data){
				$('.alert').removeClass('alert-danger').addClass('alert-success').html('设置成功！').show();
			}else{
				$('.alert').removeClass('alert-success').addClass('alert-danger').html('设置失败！').show();
			}
			setTimeout(function(){
				$('.alert').html('').hide();
				location.reload();
			}, 1000);
		});
	});
	/**
	 * 重置密码
	 */
	$('.btn-resetpassword').on('click',function(){
		var id = $("#id").val();
		$.post('HTTP://'+window.location.host+'/user/resetpassword',{"id":id},function(data){
			if(data){
				$('.alert').removeClass('alert-danger').addClass('alert-success').html('重置密码成功！').show();
			}else{
				$('.alert').removeClass('alert-success').addClass('alert-danger').html('重置密码失败！').show();
			}
			setTimeout(function(){
				$('.alert').html('').hide();
				location.reload();
			}, 1000);
		});
	});
	/**
	 * 停用账户
	 */
	$('.btn-stopaccount').on('click',function(){
		var id = $("#id").val();
		var paramter = "{'enable':'0'}"; 
		$.post('HTTP://'+window.location.host+'/user/update',{"id":id,"paramter":paramter},function(data){
			if(data){
				$('.alert').removeClass('alert-danger').addClass('alert-success').html('设置成功！').show();
			}else{
				$('.alert').removeClass('alert-success').addClass('alert-danger').html('设置失败！').show();
			}
			setTimeout(function(){
				$('.alert').html('').hide();
				location.reload();
			}, 1000);
		});
	});
	
	/**
	 * 启用该账号
	 */
	$('.btn-startaccount').on('click',function(){
		var id = $("#id").val();
		var paramter = "{'enable':'1'}"; 
		$.post('HTTP://'+window.location.host+'/user/update',{"id":id,"paramter":paramter},function(data){
			if(data){
				$('.alert').removeClass('alert-danger').addClass('alert-success').html('设置成功！').show();
			}else{
				$('.alert').removeClass('alert-success').addClass('alert-danger').html('设置失败！').show();
			}
			setTimeout(function(){
				$('.alert').html('').hide();
				location.reload();
			}, 1000);
		});
	});
	
	/**
	 * 撤销认证
	 */
	$('.btn-audit').on('click',function(){
		var id = $("#id").val();
		var paramter = "{'authentication':'AUDIT'}"; 
		$.post('HTTP://'+window.location.host+'/user/update',{"id":id,"paramter":paramter},function(data){
			if(data){
				$('.alert').removeClass('alert-danger').addClass('alert-success').html('设置成功！').show();
			}else{
				$('.alert').removeClass('alert-success').addClass('alert-danger').html('设置失败！').show();
			}
			setTimeout(function(){
				$('.alert').html('').hide();
				location.reload();
			}, 1000);
		});
	});
	
	/**
	 * 认证通过
	 */
	$('.btn-auditd').on('click',function(){
		var id = $("#id").val();
		var paramter = "{'authentication':'AUDITD'}"; 
		$.post('HTTP://'+window.location.host+'/user/update',{"id":id,"paramter":paramter},function(data){
			if(data){
				$('.alert').removeClass('alert-danger').addClass('alert-success').html('设置成功！').show();
			}else{
				$('.alert').removeClass('alert-success').addClass('alert-danger').html('设置失败！').show();
			}
			setTimeout(function(){
				$('.alert').html('').hide();
				location.reload();
			}, 1000);
		});
	});
});