/**
 * index的js
 */
$(function(){
	/**
	 * 加载首页数据
	 */
	//默认首页上的地区是郑州市（code=4101）	
	var cityCode = '';
	var cityName = '';
	var areaCode = '';
	var areaName = '';
	var schoolCode = '';
	var schoolName = '';
	
	var par={
		categoryCode: "",
		areaCode: "",
		schoolCode: "",
		status: 1,
		recommend: 10,
		used: 10,
		keyword: "",
		pageSize: 10,
		pageIndex: 1
	}
	/**
	 * 设置城市
	 */
	if(getCookie("cityCode") == null || getCookie("cityName") == null){
		
		cityCode = $('#cityName').attr('data-code');
		cityName = $('#cityName').attr('data-name');
		
		setCookie("cityCode",cityCode);
		setCookie("cityName",cityName);
	}else{
		$('#cityName').html(getCookie("cityName"));
		$('#cityName').attr('data-code',getCookie("cityCode"));
		$('#cityName').attr('data-name',getCookie("cityName"));
		
		$('#id_city').attr('data-code',getCookie("cityCode"));
		$('#id_city').html(getCookie("cityName"));
		cityCode = getCookie("cityCode");
		cityName = getCookie("cityName");
	}
	/**
	 * 设置区域
	 */
	if(getCookie("areaCode") == null || getCookie("areaName") == null){
		$('#id_area').hide();
	}else{
		$('#id_area').attr('data-code',getCookie("areaCode"));
		$('#id_area').html(getCookie("areaName"));
		areaCode = getCookie("areaCode");
		areaName = getCookie("areaName");
		$('#id_area').show();
	}
	/**
	 * 设置学校
	 */
	if(getCookie("schoolCode") == null || getCookie("schoolName") == null){
		$('#id_school').hide();
	}else{
		$('#id_school').attr('data-code',getCookie("schoolCode"));
		$('#id_school').html(getCookie("schoolName"));
		schoolCode = getCookie("schoolCode");
		schoolName = getCookie("schoolName");
		$('#id_school').show();
	}
	
	
	//设置首页上的地区
	$.get("HTTP://"+ window.location.host + "/area/list/area",{"areaCode":cityCode},function(data){
		var areahtml = '<th data-code="'+cityCode+'">区域:</th><td><a data-code="'+cityCode+'">不限</a></td>';
		for(var i=0; i < 7 && i< data.length  ;i++){
			areahtml += '<td><a data-code="'+data[i].code+'"> '+ data[i].name +' </a></td>';
		}
		areahtml +='<td><button class="btn btn-default btn-xs" data-code="'+ cityCode +'">更多<span class="caret"></span></button></td>';
		$('.area').empty().append(areahtml);

	});
	
	//设置首页上的学校
	$.get("HTTP://"+ window.location.host + "/school/list/"+cityCode.substring(0,2),function(data){
		var schoolHtml = '<th data-code="">学校:</th><td><a data-code="">不限</a></td>';
		for(var i=0; i < 7 && i< data.length  ;i++){
			schoolHtml += '<td><a data-code="'+data[i].code+'"> '+ data[i].name +' </a></td>';
		}
		schoolHtml +='<td><button class="btn btn-default btn-xs" data-code="'+ cityCode.substring(0,2) +'">更多<span class="caret"></span></button></td>';
		$('.school').empty().append(schoolHtml);
	});
	
	// 设置cook ======================================================================
	function setCookie(name,value){
		var exp = new Date(); 
		exp.setTime(exp.getTime() + 60*60*1000);//时间设置为15分钟
		document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
	}
	// 获取cook ======================================================================
	function getCookie(name){
		var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
		if(arr=document.cookie.match(reg)) 
			return unescape(arr[2]);
		else
			return null;
	}
	

	/**
	 * 使首页轮转的图片生效
	 */
	$('.carousel').carousel();
	
	/**
	 * 此处的作用是将class是js-example-data-array-selected的select控件可以做select2使用，相当于初始化
	 */
	$(".js-example-data-array-selected").select2({
		
	});
	
	/**
	 * 加载页面数据
	 */
	console.log("加载页面数据");
	$.get("HTTP://"+ window.location.host +"/commodity/list/all",par,function(data){
		
		
	});
	
	/**
	 * 鼠标在分类上移动
	 */
	$('.list-group-item').hover(function(){
		
		$('.list-group-item').removeClass('active');
		
		$(this).addClass('active');
		
		//距离左边的位置:a标签距离左边的位置+a标签的宽度（带padding）+边框的宽度（目前两边都是1 ）
		var heightWithPadding = $(this).outerHeight();
		
		var widthWithPadding = $(this).innerWidth();
		var left = $(this).offset().left + widthWithPadding + 2;
		var top = $(this).offset().top;
		
		var code = $(this).attr('data-code');
		if(code == ''){
			$('#popover').hide();
		}else{
			$.get("HTTP://" + window.location.host + "/category/list/"+code,function(data){
				if(data != ''){
					var len = Math.ceil((data.length + 1)/3);
					
					var html = "<table class='table' style='padding: 10px'><colgroup><col width='30%'></col><col width='30%'></col><col width='30%'></col></colgroup><tr><td style='padding: 10px;'><a href='#'>全部</a></td>";
					for(var i = 0;i < data.length; i++){

						if(i%3==2){
							html += "<tr>";
						}
						html += "<td style='padding: 10px;'><a href='#'>"+ data[i].name +"</a></td>"
						if(i%3==1){
							html += "</tr>";
						}
					}
					html +="</table>"
				}
				$('#popover').css("position","absolute").css('width',2*widthWithPadding +"px").css('height',len*(heightWithPadding) +"px").css("left",left + "px").css("top",top+"px").empty().append(html).show();
			});
		}
		
	},function(){
		
		return;
		
	});
	
	/**
	 * 点击页面上其他地方，隐藏类别弹出框
	 */
	$(document).on('click',function(event){
		//隐藏
		$('#popover').hide();
	});
	
	/**
	 * 点击切换城市
	 */
	$('#changeCity').on('click',function(){
		$("#cityName").hide();
		$('#editCity').show();
		$('#changeCity').hide();
		$('#changeCitySure').show();
		$('#changeCityCancel').show();
	});
	
	/**
	 * 点击取消
	 */
	$('#changeCityCancel').on('click',function(){
		$('#areaerror').hide();
		$("#cityName").show();
		$('#editCity').hide();
		$('#changeCity').show();
		$('#changeCitySure').hide();
		$('#changeCityCancel').hide();
		
	});
	
	/**
	 * 点击确定的时候
	 */
	$('#changeCitySure').on('click',function(){
		//首先将需要显示和隐藏的做处理
		var cityCode = $('#city').val();
		var name = $('#city option:selected').text();
		var cityName = (name =='市辖区' || name=='县')? $('#province option:selected').text() + name : name;
		if(cityCode==''){
			$('#areaerror').show();
			return;
		}
		setCookie("cityCode",cityCode);
		setCookie("cityName",cityName);
		
		$('#cityName').html(cityName);
		$('#cityName').attr('data-code',cityCode);
		$('#cityName').attr('data-name',cityName);
		$("#cityName").show();
		$('#editCity').hide();
		$('#changeCity').show();
		$('#changeCitySure').hide();
		$('#changeCityCancel').hide();
		
		window.location.reload();
		
	});
	
	/**
	 * 选择省份
	 */
	$('#province').on('change',function(){
		cityCode = $(this).val() || ""; 
		if(cityCode==""){
			return;
		}
		$.get("HTTP://"+window.location.host+"/area/list/city",{"areaCode":cityCode},function(cityData){
			
			//省份改变且不为空的时候直接将地区的不能为空的信息和学校不能为空的信息隐藏
			$('#areaerror').hide();
			
			if(cityData != ""){
				//移出原来的数据（除了第一个，即：请选择）
				$('#city').find("option:not(:first)").remove();
				//向select中新增加数据
				var cityHtml = "",maxlength = 0;
				for(var i = 0; i < cityData.length; i++){
					cityHtml+="<option value='"+cityData[i].code+"'>"+cityData[i].name+"</option>";
					if(cityData[i].name.length > maxlength){
						maxlength = cityData[i].name.length;
					}
				}
				$('#city').append(cityHtml);
				$('#select2-city-container').html("请选择");
				//设置city的select的长度，这里不必重新设置area的长度，因为它的值暂未变化
				$('#city').next('span').css("width", (maxlength*14+28+maxlength-1) < 93 ? 93 : (maxlength*14+28+maxlength-1));  
				
			}
		});
	});
	/**
	 * 点击地区上的“a标签”
	 */
	$('.area').on('click','a',function(){
		
		var code = $(this).attr('data-code');
		var name = $(this).text();
		setCookie("areaCode",code);
		setCookie("areaName",code);
		
		$('#id_area').attr('data-code',getCookie("areaCode"));
		$('#id_area').html(getCookie("areaName"));
		$('#id_area').show();
		
		
	});
	
	/**
	 * 点击学校上的“a标签”
	 */
	$('.school').on('click','a',function(){
		
		var code = $(this).attr('data-code');
		var name = $(this).text();
		setCookie("schoolCode",code);
		setCookie("schoolName",code);
		
		$('#id_school').attr('data-code',getCookie("schoolCode"));
		$('#id_school').html(getCookie("schoolName"));
		$('#id_school').show();
		
		
	});
	
	/**
	 * 点击地区上的“更多”
	 */
	$('.area').on('click','.btn',function(){
		
		var code = '';
		if(getCookie("cityCode") == '' ){
			code = $('#cityName').attr('data-code');
			
		}else{
			code = getCookie("cityCode");
		}
		
		$.get("HTTP://"+ window.location.host + "/area/list/area",{"areaCode":code},function(data){
			var areahtml = "<table class='table' style='padding: 10px'><colgroup><col width='30%'></col><col width='30%'></col><col width='30%'></col></colgroup>";
			for(var i=0;i < data.length ;i++){
				if(i%3==0){
					areahtml += "<tr>";
				}
				areahtml += "<td style='padding: 10px;'><input name='area' type='radio' value='"+data[i].code+"'>"+ data[i].name +"</td>"
				if(i%3==2){
					areahtml += "</tr>";
				}
			}
			areahtml +='</table>';
			console.log($('#moreAreaModal').find('.modal-body'));
			$('#moreAreaModal').find('.modal-body').empty().append(areahtml);

		});
		$('#moreAreaModal').modal('show');
	});
	
	/**
	 * 点击学校上的“更多”
	 */
	$('.school').on('click','.btn',function(){
		
		var code = '';
		if(getCookie("cityCode") == '' ){
			code = $('#cityName').attr('data-code');
			
		}else{
			code = getCookie("cityCode");
		}
		
		$.get("HTTP://"+ window.location.host + "/school/list/"+code.substring(0,2),function(data){
			var schoolHtml = "<table class='table' style='padding: 10px'><colgroup><col width='30%'></col><col width='30%'></col><col width='30%'></col></colgroup>";
			for(var i=0; i< data.length  ;i++){
				
				if(i%3==0){
					schoolHtml += "<tr>";
				}
				schoolHtml += "<td style='padding: 10px;'><input name='school' type='radio' value='"+data[i].code+"'>"+ data[i].name +"</a></td>"
				if(i%3==2){
					schoolHtml += "</tr>";
				}
			}
			schoolHtml +='</table>';
			$('#moreSchoolModal').find('.modal-body').empty().append(schoolHtml);
		});
		$('#moreSchoolModal').modal('show');
	});
	
});