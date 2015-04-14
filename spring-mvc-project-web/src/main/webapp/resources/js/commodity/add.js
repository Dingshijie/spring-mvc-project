/**
 * add.js 
 */
$(function(){
	
	$('.reset').click();
	
	//启用iCheck
	$('input').iCheck({
		checkboxClass: 'icheckbox_square-blue',
		radioClass: 'iradio_square-blue',
		increaseArea: '20%' // optional
	});
	

	/**
	 * 点击添加的图标的时候
	 */
	$('.goodsPlus').on('click',function(){
		
		$('#goods').val('');
		if($('.goodsList').find('p').length != 0){
			$('.goodsAdd').addClass('col-sm-offset-2');
		}else{
			$('.goodsAdd').removeClass('col-sm-offset-2');
		}
		$('.goodsPlus').addClass('col-sm-offset-2');
		$('.goodsAdd').show();
		
	});
	
	/**
	 * 点击取消的时候
	 */
	$('.goodsAdd').on('click',".cancel",function(){
		
		$('.goodsAdd').removeClass('col-sm-offset-2');
		$('.goodsAdd').hide();
		
		if($('.goodsList').find('p').length == 0){
			$('.goodsPlus').removeClass('col-sm-offset-2');
		}
		
	});
	
	/**
	 * 点击保存的时候
	 */
	$('.goodsAdd').on('click',".save",function(){
		
		var value = $('#goods').val();
		if(value == ''){
			return;
		}
		
		var html='<p><input type="text" class="good" value="'+ value +'" style="width:'+ (value.length+1)*14 +'px"> <a href="javascript:void(0);" title="点击删除"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a><a href="javascript:void(0);" title="点击编辑"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a><input type="button" class="btn btn-info save" value="保存"> <input type="button" class="btn btn-default cancel" value="取消"> </p>';
		$('.goodsList').append(html);
		$('.goodsList').show();
		$('.goodsAdd').hide();
		var val = "";
		$('.goodsList').find('p').each(function(){
			if(val != ""){
				val +=";"
			}
			val += $(this).find('input.good').val();
		});
		if(val==''){
			$('#goodserror').addClass('error').css('color','red').html("× 请添加商品清单！");
		}else{
			$('#goodserror').removeClass('error').css('color','green').html("√");
		}
		$('#goodserror').show();
		$('[name="goods"]').val(val);
		
	});
	
	/**
	 * 点击glyphicon-remove按钮删除的时候
	 */
	$('.goodsList').on('click',".glyphicon-remove",function(){
		
		$(this).closest('p').remove();
		if($('.goodsList').find('p').length == 0){
			$('.goodsList').css('display','none');
			$('.goodsPlus').removeClass('col-sm-offset-2');
		}
		var val = "";
		$('.goodsList').find('p').each(function(){
			if(val != ""){
				val +=";"
			}
			val += $(this).find('input.good').val();
		});
		if(val==''){
			$('#goodserror').addClass('error').css('color','red').html("× 请添加商品清单！");
		}else{
			$('#goodserror').removeClass('error').css('color','green').html("√");
		}
		$('[name="goods"]').val(val);
		
	});
	
	/**
	 * 点击glyphicon-edit按钮编辑的时候
	 */
	$('.goodsList').on('click',".glyphicon-edit",function(){
		
		
		$(this).closest('p').find('input.good').css("border","1px solid #ccc").addClass('form-control');
		
		$(this).closest('p').find('.glyphicon').hide();
		$(this).closest('p').find('input.btn').show();
		
	});
	
	/**
	 * 点击glyphicon-edit按钮编辑的时候
	 */
	$('.goodsList').on('click',".save,.cancel",function(){
		

		$(this).closest('p').find('input.good').css("border","0px solid #ccc").removeClass('form-control');
		
		$(this).closest('p').find('.glyphicon').show();
		$(this).closest('p').find('input.btn').hide();
		
		var val = "";
		$('.goodsList').find('p').each(function(){
			if(val != ""){
				val +=";"
			}
			val += $(this).find('input.good').val();
		});
		if(val==''){
			$('#goodserror').addClass('error').css('color','red').html("× 请添加商品清单！");
		}else{
			$('#goodserror').removeClass('error').css('color','green').html("√");
		}
		$('[name="goods"]').val(val);
		
	});
	
	/**
	 * 点击大类选择的时候，加载小类
	 */
	$('#categoryCode').on('change',function(){
		
		var code = $(this).val();
		$.get("HTTP://" + window.location.host + "/category/list/"+code,function(data){
			
			var html = "";
			$('#category').find("option:not(:first)").remove();
			for(var i = 0;i < data.length; i++){
				html += '<option value="'+ data[i].code +'">'+ data[i].name +'</option>';
			}
			$('#category').append(html);
		});
		
	});
	
	/**
	 * 选择的图片改变的时候
	 */
	$('#file').on('change',function(){
		
		var docObj=document.getElementById("file");
		var imgObjPreview=document.getElementById("imgpreview");
		if(docObj.files && docObj.files[0]){
			//火狐下，直接设img属性
			imgObjPreview.style.display = 'block';
			imgObjPreview.style.width = '200px';
			imgObjPreview.style.height = '180px';
			//imgObjPreview.src = docObj.files[0].getAsDataURL();
			//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
			imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
		}else{
			//IE下，使用滤镜
			docObj.select();
			var imgSrc = document.selection.createRange().text;
			var localImagId = document.getElementById("localImag");
			//必须设置初始大小
			localImagId.style.width = "200px";
			localImagId.style.height = "180px";
			//图片异常的捕捉，防止用户修改后缀来伪造图片
			try{
				localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
				localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
			}catch(e){
				alert("您上传的图片格式不正确，请重新选择!");
			}
			imgObjPreview.style.display = 'none';
			document.selection.empty();
		}
		
	});
	

	/**
	 * 焦点商品名称名输入框的时候
	 */
	$('#name').on('blur',function(){
		$('#nameerror').removeClass("error").css('color','green').html("√");
		var name = $.trim($('#name').val());
		var len = name.length;
		if(len==0){
			$('#nameerror').addClass("error").css('color','red').html("× 名称不能为空！");
		}else if(len < 2 || len > 128){
			$('#nameerror').addClass("error").css('color','red').html("× 名称长度只能为2到128个字符！");
		}
		$('#nameerror').show();
	});
	$('#name').on('focus',function(){
		$('#nameerror').hide();
	});
	
	/**
	 * 焦点到商品品牌名输入框的时候
	 */
	$('#brand').on('blur',function(){
		$('#branderror').removeClass("error").css('color','green').html("√");
		var brand = $.trim($('#brand').val());
		var len = brand.length;
		if (len > 64){
			$('#branderror').addClass("error").css('color','red').html("× 名称长度不能超过64个字符！");
		}
		$('#branderror').show();
	});
	$('#brand').on('focus',function(){
		$('#branderror').hide();
	});
	
	$('#category').on('change',function(){
		$('#categoryerror').removeClass("error").css('color','green').html("√");
		if($(this).val()==''){
			$('#categoryerror').addClass("error").css('color','red').html("× 请选择类别！");
		}
		$('#categoryerror').show();
	});
	
	/**
	 * 焦点商品型号输入框的时候
	 */
	$('#typeCode').on('blur',function(){
		$('#typeCodeerror').removeClass("error").css('color','green').html("√");
		var typeCode = $.trim($('#typeCode').val());
		var len = typeCode.length;
		if (len > 64){
			$('#typeCodeerror').addClass("error").css('color','red').html("× 商品型号不能超过64个字符！");
		}
		$('#typeCodeerror').show();
	});
	$('#typeCode').on('focus',function(){
		$('#typeCodeerror').hide();
	});
	
	/**
	 * 焦点商品型号输入框的时候
	 */
	$('#link').on('blur',function(){
		$('#linkerror').removeClass("error").css('color','green').html("√");
		var link = $.trim($('#link').val());
		var len = link.length;
		if (len > 64){
			$('#linkerror').addClass("error").css('color','red').html("× 商品链接不能超过64个字符！");
		}else if((link.substring(0,4)).toUpperCase() != "HTTP" ){
			$('#linkerror').addClass("error").css('color','red').html("× 商品链接请带上http或https！");
		}
		$('#linkerror').show();
	});
	$('#link').on('focus',function(){
		$('#linkerror').hide();
	});
	
	/**
	 * 焦点商品价格输入框的时候
	 */
	$('#price').on('blur',function(){
		$('#priceerror').removeClass("error").css('color','green').html("√");
		var price = $.trim($('#price').val());
		if(price == ''){
			$('#priceerror').addClass("error").css('color','red').html("× 价格为必填项！");
		}else if(isNaN(price)){
			$('#priceerror').addClass("error").css('color','red').html("× 数据不正确！");
		}else if(price < 0){
			$('#priceerror').addClass("error").css('color','red').html("× 价格不可为负数！");
		}
		var len = price.length;
		$('#priceerror').show();
	});
	$('#price').on('focus',function(){
		$('#priceerror').hide();
	});
	
	/**
	 * 焦点商品型号输入框的时候
	 */
	$('#unit').on('blur',function(){
		$('#uniterror').removeClass("error").css('color','green').html("√");
		var unit = $.trim($('#unit').val());
		var len = unit.length;
		if(len==0){
			$('#uniterror').addClass("error").css('color','red').html("× 计量单位为必填项！");
		}else if ( len > 16){
			$('#uniterror').addClass("error").css('color','red').html("× 计量单位不能超过16个字符！");
		}
		$('#uniterror').show();
	});
	$('#unit').on('focus',function(){
		$('#uniterror').hide();
	});
	
	/**
	 * 焦点商品描述输入框的时候
	 */
	$('#description').on('blur',function(){
		$('#descriptionerror').removeClass("error").css('color','green').html("√");
		var description = $.trim($('#description').val());
		var len = description.length;
		if (len > 512){
			$('#descriptionerror').addClass("error").css('color','red').html("× 描述不能超过512个字符！");
		}
		$('#descriptionerror').show();
	});
	$('#description').on('focus',function(){
		$('#descriptionerror').hide();
	});
	
	$('#myForm').on('submit',function(e){
		console.log($('[name="goods"]').val());
		if($('[name="goods"]').val()==''){
			$('#goodserror').addClass('error').css('color','red').html("× 请填写商品清单！");
			$('#goodserror').show();
		}
		
		if($('#name').val() == ''){
			$('#nameerror').addClass("error").css('color','red').html("× 地区名称不能为空！");
			$('#nameerror').show();
		}
		if($('#category').val() == ''){
			$('#categoryerror').addClass("error").css('color','red').html("× 请选择类别！");
			$('#categoryerror').show();
		}
		if($('#price').val() == ''){
			$('#priceerror').addClass("error").css('color','red').html("× 价格为必填项！");
			$('#priceerror').show();
		}
		if($('#unit').val() == ''){
			$('#uniterror').addClass("error").css('color','red').html("× 计量单位为必填项！");
			$('#uniterror').show();
		}
		
		//检查是否有错误标记
		var errorNum = $(".error").length;
		if(errorNum != 0){
			e.preventDefault();
		}
	});

});
