/**
 * index的js
 */
$(function(){
	/**
	 * 加载首页数据
	 */
	//默认首页上的地区是郑州市（code=4101）
	var categoryCode = '';
	var categoryName = '';
	var cityCode = '';
	var cityName = '';
	var areaCode = '';
	var areaName = '';
	var schoolCode = '';
	var schoolName = '';
	var user = 10;
	var par={
		categoryCode: getCookie('categoryCode') || "",
		areaCode: getCookie('areaCode') || "",
		schoolCode: getCookie('schoolCode') || "",
		status: 1,
		recommend: 10,
		used: getCookie('used') || 10,
		keyword: "",
		pageSize: 12,
		pageIndex: 1
	}
	/**
	 * 设置类别
	 */
	if(getCookie("categoryCode") == null || getCookie("categoryName") == null){
		
	}else{
		categoryCode = getCookie("categoryCode");
		categoryName = getCookie("categoryName");
		
		$('#id_category').attr('data-code',getCookie("categoryCode"));
		$('#id_category').html(getCookie("categoryName"));
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
		
		$('#id_area').html('不限');
		
		//$('#id_area').hide();
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
		$('#id_school').html('不限');
		//$('#id_school').hide();
	}else{
		$('#id_school').attr('data-code',getCookie("schoolCode"));
		$('#id_school').html(getCookie("schoolName"));
		schoolCode = getCookie("schoolCode");
		schoolName = getCookie("schoolName");
		$('#id_school').show();
	}
	
	
	//设置首页上的地区
	$.get("HTTP://"+ window.location.host + "/area/list/area",{"areaCode":cityCode},function(data){
		var areahtml = '<th data-code="'+cityCode+'">区域:</th><td><a class="btn btn-info btn-xs" href="javascript:void(0);" data-code="'+cityCode+'" data-name="不限">不限</a></td>';
		for(var i=0; i < 7 && i< data.length  ;i++){
			areahtml += '<td><a href="javascript:void(0);" data-code="'+data[i].code+'" data-name="'+ data[i].name +'"> '+ data[i].name +' </a></td>';
		}
		areahtml +='<td><button class="btn btn-default btn-xs" data-code="'+ cityCode +'">更多<span class="caret"></span></button></td>';
		$('.area').empty().append(areahtml);

	});
	
	//设置首页上的学校
	$.get("HTTP://"+ window.location.host + "/school/list/"+cityCode.substring(0,2),function(data){
		var schoolHtml = '<th data-code="">学校:</th><td><a class="btn btn-info btn-xs" href="javascript:void(0);" data-code="" data-name="不限">不限</a></td>';
		for(var i=0; i < 7 && i< data.length  ;i++){
			schoolHtml += '<td><a href="javascript:void(0);" data-code="'+data[i].code+'" data-name="'+ data[i].name +'"> '+ data[i].name +' </a></td>';
		}
		schoolHtml +='<td><button class="btn btn-default btn-xs" data-code="'+ cityCode.substring(0,2) +'">更多<span class="caret"></span></button></td>';
		$('.school').empty().append(schoolHtml);
	});
	
	// 设置cook ======================================================================
	function setCookie(name,value){
		var exp = new Date(); 
		exp.setTime(exp.getTime() + 15*60*1000);//时间设置为15分钟
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
	setting = {
		"listUrl" : "HTTP://" + window.location.host + "/commodity/list/all",
		// 加载页面的包含框（jquery对象）
		'pageWrap' : $('#loadContentWrap'),
		// 放置翻页的包含狂（jquery对象）
		'pagingWrap' : $('.pageWrap'),
		// 页面数量
		'pageCount' : 0,
	};
		

			
	// 首次初始化后加载
	loadData(par);

	loadPage = function() {

		// 查询条件
		this.setting.pageWrap.load(setting.listUrl, data, function() {
			// 执行回调函数
			that.setting.callBack();
		});
	};

	function loadData(par) {

		var baseUrl = "http://" + window.location.host + "/resources/";
		// 加载等待效果
		setting.pageWrap.append('<div id="loadingImg"><img width="580px" height="435px" src="'+ baseUrl + '/img/loading.gif"/></div>');

		$.get("HTTP://" + window.location.host + "/commodity/list/all",par,function(data) {
			if(data == ''){
				var html = "<div style='font-size:16px;text-align:center;line-height:120px;' class='alert-info' role='alert'><strong>提示</strong>：未查询到相关数据！</div>"
			}else{
				setting.pageWrap.empty().html(($.templates("#commodityListTmpl")).render(data));
			}
		});

		$.get("HTTP://" + window.location.host + "/commodity/count/all", par,function(data) {
			setting.pageCount = Math.ceil(data / par.pageSize) || 0;
			// 加载分页
			paging();
		});

	}

	$('#loadContentWrap').on('click','a',function(){
		var id = $(this).attr('data-id');
		window.open("HTTP://"+window.location.host+"/commodity/detail/"+id,"_blank");
	});
	
	/**
	 * 分页
	 */
	function paging() {
		var that = this;
		// page模板
		var pageHtml = "";
		// 设定页面显示的个数
		var num = par.pageSize;
		if (setting.pageCount <= num) {
			for (var i = 1; i <= setting.pageCount; i++) {
				pageHtml += "<li><a href='javascript:void(0);'>" + i + "</a></li>";
			};
		} else {
			if (par.pageIndex < 4) {
				for (var k = 1; k <= 4; k++) {
					pageHtml += "<li><a href='javascript:void(0)'>" + k	+ "</a></li>";
				}
				pageHtml = pageHtml	+ "<li><a class='disabled'>...</a></li><li><a href='javascript:void(0)'>"+ setting.pageCount + "</a></li>";
			} else if ((setting.pageCount - par.pageIndex) <= 3) {
				for (var j = (par.pageIndex - 2); j <= setting.pageCount; j++) {
					pageHtml += "<li><a href='javascript:void(0)'>" + j	+ "</a></li>";
				}
				pageHtml = "<li><a href='javascript:void(0)'>1</a></li><li><a  class='disabled'>...</a></li>"+ pageHtml;
			} else {
				for (var m = (par.pageIndex - 2); m <= (par.pageIndex + 2); m++) {
					pageHtml += "<li><a href='javascript:void(0)'>" + m	+ "</a></li>";
				}
				pageHtml = "<li><a href='javascript:void(0)'>1</a></li><li><a class='disabled'>...</a></li>"+ pageHtml+ "<li><a  class='disabled'>...</a></li><li><a href='javascript:void(0)'>"+ setting.pageCount + "</a></li>";
			};
		};
		pageHtml = "<li><a href='javascript:void(0)' class='pre'>上一页</li>"+ pageHtml+ "<li><a href='javascript:void(0)' class='next'>下一页</a></li>";
		// 将翻页放入页面并为每个页面按钮绑定事件
		setting.pagingWrap.empty().append(pageHtml).find('a:not([class=disabled])').on('click', function() {
			if ($(this).hasClass('pre')) {
				pre();
			} else if ($(this).hasClass('next')) {
				next();
			} else {
				var currentIndex = parseInt($(this).text());
				clickPage(currentIndex);
			}
		});
		// 如过页数为0
		if (setting.pageCount == 0) {
			setting.pagingWrap.find('.pre').unbind('click').parent('li').addClass('disabled');
			setting.pagingWrap.find('.next').unbind('click').parent('li').addClass('disabled');
		}
		// 锁定当前页
		if (par.pageIndex == 1) {
			setting.pagingWrap.find('.pre').unbind('click').parent('li').addClass('disabled');
		}
		if (par.pageIndex == setting.pageCount) {
			setting.pagingWrap.find('.next').unbind('click').parent('li').addClass('disabled');
		};
		setting.pagingWrap.find('li').each(function() {
			if (parseInt($(this).find('a').text()) == par.pageIndex) {
				$(this).addClass('active');
				$(this).find('a').unbind('click');
			};
		});
	};

	// ================
	// 向前翻页
	// ================
	function pre() {
		par.pageIndex--;
		if (par.pageIndex <= 0) {
			par.pageIndex = 1;
			setting.pagingWrap.find('.pre').addClass('disabled');
		} else {
			setting.pagingWrap.find('.pre').removeClass('disabled');
		}
		loadData(par);
	};

	// ===================
	// 向后翻页
	// ====================
	function next() {
		par.pageIndex++;
		if (par.pageIndex > setting.pageCount) {
			par.pageIndex = setting.pageCount;
			setting.pagingWrap.find('.next').addClass('disabled');
		} else {
			setting.pagingWrap.find('.next').removeClass('disabled');
		}
		loadData(par);
	};

	//=====================
	//点击页码
	//====================
	function clickPage(pageIndex) {
		par.pageIndex = pageIndex;
		loadData(par);
	};
		
	
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
		var name = $(this).attr('data-name');
		if(code == ''){
			$('#popover').hide();
		}else{
			$.get("HTTP://" + window.location.host + "/category/list/"+code,function(data){
				if(data != ''){
					var len = Math.ceil((data.length + 1)/3);
					
					var html = "<table class='table' style='padding: 10px'><colgroup><col width='30%'></col><col width='30%'></col><col width='30%'></col></colgroup><tr><td style='padding: 10px;'><a class='item' href='javascript:void(0);' data-code='"+code+"' data-name='"+name+"（全部）'>全部</a></td>";
					for(var i = 0;i < data.length; i++){

						if(i%3==2){
							html += "<tr>";
						}
						html += "<td style='padding: 10px;'><a class='item' href='javascript:void(0);' data-code='"+data[i].code+"' data-name='"+data[i].name+"'>"+ data[i].name +"</a></td>"
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
	 * 点击大分类
	 */
	$('.list-group-item').on('click',function(){
		
		
		categoryCode = $(this).attr('data-code');
		categoryName = $(this).attr('data-name');
		
		setCookie("categoryCode",categoryCode);
		setCookie("categoryName",categoryName);
		
		
		$('#id_category').attr('data-code',getCookie("categoryCode"));
		$('#id_category').html(getCookie("categoryName"));
		$('#id_category').show();
		
		window.location.reload();
		
	});
	/**
	 * 点击小分类
	 */
	$('#popover').on('click','.item',function(){
		console.log('aa');
		
		categoryCode = $(this).attr('data-code');
		categoryName = $(this).attr('data-name');
		
		setCookie("categoryCode",categoryCode);
		setCookie("categoryName",categoryName);
		
		$('#id_category').attr('data-code',getCookie("categoryCode"));
		$('#id_category').html(getCookie("categoryName"));
		$('#id_category').show();
		
		window.location.reload();
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
		//重置省、市的内容
		$('#province').val('').trigger("change");
		$('#city').val('').trigger("change");
		
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
		
		//地区的代码设置不限
		setCookie("areaCode",cityCode);
		setCookie("areaName",'不限');
		
		$('#id_area').attr('data-code',getCookie("areaCode"));
		$('#id_area').html(getCookie("areaName"));
		$('#id_area').show();
		
		//高校的代码设置：不限
		setCookie("schoolCode",'');
		setCookie("schoolName",'不限');
		
		$('#id_school').attr('data-code',getCookie("schoolCode"));
		$('#id_school').html(getCookie("schoolName"));
		$('#id_school').show();
		
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
	 * 点击热门的a标签
	 */
	$('.hot').on('click','a',function(){
		var code = $(this).attr('data-code');
		var name = $(this).attr('data-name');
		setCookie("categoryCode",code);
		setCookie("categoryName",name);
		
		$('#id_category').attr('data-code',getCookie("categoryCode"));
		$('#id_category').html(getCookie("categoryName"));
		$('#id_category').show();
		
		window.location.reload();
	});
	
	/**
	 * 点击地区上的“a标签”
	 */
	$('.area').on('click','a',function(){
		
		var code = $(this).attr('data-code');
		var name = $(this).attr('data-name');
		setCookie("areaCode",code);
		setCookie("areaName",name);
		
		$('#id_area').attr('data-code',getCookie("areaCode"));
		$('#id_area').html(getCookie("areaName"));
		$('#id_area').show();
		
		window.location.reload();
	});
	
	/**
	 * 点击学校上的“a标签”
	 */
	$('.school').on('click','a',function(){
		
		var code = $(this).attr('data-code');
		var name = $(this).text();
		setCookie("schoolCode",code);
		setCookie("schoolName",name);
		
		$('#id_school').attr('data-code',getCookie("schoolCode"));
		$('#id_school').html(getCookie("schoolName"));
		$('#id_school').show();
		
		window.location.reload();
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
				areahtml += "<td style='padding: 10px;'><input name='area' type='radio' value='"+data[i].code+"' data-name='"+ data[i].name +"'>"+ data[i].name +"</td>"
				if(i%3==2){
					areahtml += "</tr>";
				}
			}
			areahtml +='</table>';
			$('#moreAreaModal').find('.modal-body').empty().append(areahtml);
			$('#moreAreaModal').modal('show');
		});
		
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
				schoolHtml += "<td style='padding: 10px;'><input name='school' type='radio' value='"+data[i].code+"' data-name='"+ data[i].name +"'>"+ data[i].name +"</a></td>"
				if(i%3==2){
					schoolHtml += "</tr>";
				}
			}
			schoolHtml +='</table>';
			$('#moreSchoolModal').find('.modal-body').empty().append(schoolHtml);
		});
		$('#moreSchoolModal').modal('show');
	});
	
	/**
	 * 点击地区，选择确定的时候
	 */
	$("#moreAreaModal").on('click','.btn-sure',function(){
		var input = $('#moreAreaModal').find('.modal-body').find('input[name="area"]:checked');
		
		if(input.length == 0){
			$('#moreAreaModal').find('.help-block').show();
		}else{
			$('#moreAreaModal').find('.help-block').hide();
			var code = input.val();
			var name = input.attr('data-name');
			
			setCookie("areaCode",code);
			setCookie("areaName",name);
			
			$('#id_area').attr('data-code',getCookie("areaCode"));
			$('#id_area').html(getCookie("areaName"));
			$('#id_area').show();
			
			$("#moreAreaModal").modal('hide');
			
			window.location.reload();
		}
	});
	
	/**
	 * 点击学校，选择确定的时候
	 */
	$("#moreSchoolModal").on('click','.btn-sure',function(){
		var input = $('#moreSchoolModal').find('.modal-body').find('input[name="school"]:checked');
		
		if(input.length == 0){
			$('#moreSchoolModal').find('.help-block').show();
		}else{
			var code = input.val();
			var name = input.attr('data-name');
			setCookie("schoolCode",code);
			setCookie("schoolName",name);
			
			$('#id_school').attr('data-code',getCookie("schoolCode"));
			$('#id_school').html(getCookie("schoolName"));
			$('#id_school').show();

			$("#moreSchoolModal").modal('hide');
			
			window.location.reload();
		}
	});
	
	/**
	 * 点击搜索加载页面
	 */
	$('.search-form').on('click','#btn-search',function(){
		par.categoryCode =  getCookie('categoryCode') || "";
		par.areaCode = getCookie('areaCode') || "";
		par.schoolCode = getCookie('schoolCode') || "";
		par.used = getCookie('used') || 10;
		par.keyword = $('.search-form').find('#keyword').val() || "";
		
		loadData(par);
	});
});