/**
 * commodity.js
 */
$(function(){
$('.reset').click();
	
	
	/**
	 * 定义变量
	 */
	var par={
		"categoryCode" : "",
		"areaCode": "",
		"schoolCode": "",
		"status": 10,
		"recommend": 10,
		"used": 10,
		"keyword" : "",
		"pageIndex" : 1,
		"pageSize" : 10
	}
	
	setting = {
		"listUrl" : "HTTP://"+window.location.host+"/commodity/list",
		//加载页面的包含框（jquery对象）
		'pageWrap' : $('#loadContentWrap'),
		//放置翻页的包含狂（jquery对象）
		'pagingWrap' :  $('.pageWrap'),
		//页面数量
		'pageCount' : 0,
	};
	
	
	//首次初始化后加载
	loadData(par);
	
	
	loadPage = function(){
		
			//查询条件
		this.setting.pageWrap.load(setting.listUrl, data, function(){
			//执行回调函数
			that.setting.callBack();
		});
	};
	
	function loadData(par){

		var baseUrl = "http://"+window.location.host+"/resources/";
		//加载等待效果
		setting.pageWrap.append('<div id="loadingImg"><img width="580px" height="435px" src="'+baseUrl+'/img/loading.gif"/></div>');

		$.get("HTTP://"+window.location.host+"/commodity/list",par,function(data){
			if(data == ''){
				var html = "<div style='font-size:16px;text-align:center;line-height:120px;' class='alert-info' role='alert'><strong>提示</strong>：未查询到相关数据！</div>"
			}else{
				var html = "<table class='table table-hover table-striped'><colgroup><col width='5%'></col><col width='10%'></col><col width='10%'></col><col width='10%'></col><col width='10%'><col width='10%'></col><col width='10%'></col><col width='10%'></col><col width='10%'></col><col width='10%'></col></colgroup><thead><tr><th>序号</th><th>名称</th><th>类别</th><th>价格</th><th>计量单位</th><th>是否在售</th><th>是否被推广</th><th>是否二手</th><th>发布用户</th><th>操作</th></tr></thead><tbody>";
				for(var i = 0; i < data.length; i++){
					html += "<tr data-id='"+ data[i].id +"'><td>"+ (i+1) +"</td><td>" + data[i].name +"</td><td>" + data[i].category + "</td><td>" + data[i].price + "元</td><td> " + data[i].unit + " </td><td> " + ((data[i].status=="1" || data[i].status=="0") ? (data[i].status=="1" ? "√": "×") : "× 注：被强制下架" ) + " </td><td> " + (data[i].recommend=="0" ? "×": "√") + " </td><td> " + (data[i].used=="0" ? "×": "√") + " </td><td> " + data[i].username+ " </td><td><a href='HTTP://"+window.location.host+"/commodity/"+ data[i].id +"' title='点击查看详情'>查看详情</a></td></tr>";
				}
				html + "</tbody></table>";
			}
			setting.pageWrap.empty().append(html);
		});
		
		$.get("HTTP://"+window.location.host+"/commodity/count",par,function(data){
			if(data != 0){
				$('#download').removeClass('disabled');
			}else{
				$('#download').addClass('disabled');
			}
			setting.pageCount = Math.ceil(data / par.pageSize) || 0;
			//加载分页
			paging();
		});
		
	}
	
	/**
	 * 分页
	 */
	function paging() {
		var that = this;
		//page模板
		var pageHtml = "";
		//设定页面显示的个数
		var num = par.pageSize;
		if(setting.pageCount <= num){
			for(var i = 1; i <= setting.pageCount; i++){
				pageHtml += "<li><a href='javascript:void(0);'>"+i+"</a></li>";
			};
		}else{
			if(par.pageIndex < 4){
				for(var k = 1; k <= 4; k++){
					pageHtml += "<li><a href='javascript:void(0)'>"+k+"</a></li>";
				}
				pageHtml = pageHtml + "<li><a class='disabled'>...</a></li><li><a href='javascript:void(0)'>"+setting.pageCount+"</a></li>";
			}else if((setting.pageCount - par.pageIndex) <= 3){
				for(var j = (par.pageIndex-2); j <= setting.pageCount; j++){
					pageHtml += "<li><a href='javascript:void(0)'>"+j+"</a></li>";
				}
				pageHtml = "<li><a href='javascript:void(0)'>1</a></li><li><a  class='disabled'>...</a></li>" + pageHtml;
			}else{
				for(var m = (par.pageIndex-2); m <= (par.pageIndex+2); m++){
					pageHtml += "<li><a href='javascript:void(0)'>"+m+"</a></li>";
				}
				pageHtml = "<li><a href='javascript:void(0)'>1</a></li><li><a class='disabled'>...</a></li>" + pageHtml+ "<li><a  class='disabled'>...</a></li><li><a href='javascript:void(0)'>"+setting.pageCount+"</a></li>";
			};
		};
		pageHtml = "<li><a href='javascript:void(0)' class='pre'>上一页</li>" + pageHtml + "<li><a href='javascript:void(0)' class='next'>下一页</a></li>";
		//将翻页放入页面并为每个页面按钮绑定事件
		setting.pagingWrap.empty().append(pageHtml).find('a:not([class=disabled])').on('click',function(){
			if($(this).hasClass('pre')){
				pre();
			}else if($(this).hasClass('next')){
				next();
			}else{
				var currentIndex = parseInt($(this).text());
				clickPage(currentIndex);
			}
		});
		//如过页数为0
		if(setting.pageCount == 0){
			setting.pagingWrap.find('.pre').unbind('click').parent('li').addClass('disabled');
			setting.pagingWrap.find('.next').unbind('click').parent('li').addClass('disabled');
		}
		//锁定当前页
		if(par.pageIndex == 1){
			setting.pagingWrap.find('.pre').unbind('click').parent('li').addClass('disabled');
		}
		if(par.pageIndex == setting.pageCount){
			setting.pagingWrap.find('.next').unbind('click').parent('li').addClass('disabled');
		};
		setting.pagingWrap.find('li').each(function(){
			if(parseInt($(this).find('a').text()) == par.pageIndex){
				$(this).addClass('active');
				$(this).find('a').unbind('click');
			};
		});
	};
	
	//================
	//向前翻页
	//================
	function pre(){
		par.pageIndex --;
		if(par.pageIndex <= 0){
			par.pageIndex = 1;
			setting.pagingWrap.find('.pre').addClass('disabled');
		}else{
			setting.pagingWrap.find('.pre').removeClass('disabled');
		}
		loadData(par);
	};
	
	//===================
	//向后翻页
	//====================
	function next(){
		par.pageIndex ++;
		if(par.pageIndex > setting.pageCount){
			par.pageIndex = setting.pageCount;
			setting.pagingWrap.find('.next').addClass('disabled');
		}else{
			setting.pagingWrap.find('.next').removeClass('disabled');
		}
		loadData(par);
	};
	
	//=====================
	//点击页码
	//====================
	function clickPage(pageIndex){
		par.pageIndex = pageIndex;
		loadData(par);
	};
	
	//改变参数中的pageSize后重新load
	$('#pageSize').on('change',function(){
		par.pageSize = parseInt($(this).val()) || 10;
		loadData(par);
	});
	//点击查询
	$('#keywordSearch').on('click',function(){
		par.keyword = $('#keyword').val();
		par.categoryCode = $('#category').val();
		par.areaCode = $('#province').val();
		par.schoolCode = $('#school').val();
		par.status = $('#status').val();
		par.recommend = $('#recommend').val();
		par.used = $('#used').val();
		
		loadData(par);
	});
	$('#download').on('click',function(){
		window.location = "HTTP://"+ window.location.host + "/commodity/export?categoryCode="+par.categoryCode+"&areaCode="+par.areaCode+"&schoolCode="+par.schoolCode+"&status="+par.status+"&recommend="+par.recommend+"&used="+par.used+"&keyword="+par.keyword;
	});
	
});
