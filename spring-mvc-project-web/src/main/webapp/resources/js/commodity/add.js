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
	

});
