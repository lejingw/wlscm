function createToolbar(start, limit, totalCount){
	document.write("<input type='hidden' id='start' name='start' value=''/>");
	document.write("<input type='hidden' id='limit' name='limit' value=''/>");
	document.write("当前第<input type='text' id='_pageindex' style='width:25px;text-align:center;' value=''>页,");
	document.write("共<input type='text' id='_totalPageCount' style='width:25px;text-align:center;' value='' readonly>页,");
	document.write("每页<input type='text' id='_pageCount' style='width:25px;text-align:center;' value=''>条,");
	document.write("共<input type='text' id='_totalCount' style='width:25px;text-align:center;' value='' readonly>条,");
	document.write("<input type='button' id='_btnFirst' class='pageToolbarBtn' value='首页'>");
	document.write("<input type='button' id='_btnPre' class='pageToolbarBtn' value='上一页'>");
	document.write("<input type='button' id='_btnNext' class='pageToolbarBtn' value='下一页'>");
	document.write("<input type='button' id='_btnLast' class='pageToolbarBtn' value='尾页'>");
	/**
	* 改变页数
	*/
	jQuery("#_pageindex").change(function(){
		if(isNumeric(jQuery("#_pageindex").val())){
	       	jQuery("#start").val((parseInt(jQuery("#_pageindex").val()) - 1) * parseInt(jQuery("#_pageCount").val()) + 1);
	       	loadStylePage(jQuery("#start").val(),jQuery("#limit").val());
		}else{
			jQuery("#_pageindex").val("");
		}
		
	});
	/**
	* 改变每页显示条数
	*/
	jQuery("#_pageCount").change(function(){
		if(isNumeric(jQuery("#_pageCount").val())){
	    	jQuery("#start").val("1");
	    	jQuery("#limit").val(jQuery("#_pageCount").val());
	    	loadStylePage(jQuery("#start").val(),jQuery("#limit").val());
		}else{
			jQuery("#_pageCount").val("");
		}
	});
	jQuery("#_totalPageCount").focus(function(){
		jQuery(this).blur();
	});
	jQuery("#_totalCount").focus(function(){
		jQuery(this).blur();
	});
	jQuery("#_btnFirst").click(function(){
		jQuery("#start").val("1");
		loadStylePage(jQuery("#start").val(),jQuery("#limit").val());
	});
	jQuery("#_btnPre").click(function(){
		if(parseInt(jQuery("#start").val()) - parseInt(jQuery("#limit").val()) - 1 >= 0){
	    	jQuery("#start").val(parseInt(jQuery("#start").val()) - parseInt(jQuery("#limit").val()));
	    	loadStylePage(jQuery("#start").val(),jQuery("#limit").val());
		}
	});
	jQuery("#_btnLast").click(function(){
		jQuery("#start").val((Math.ceil(parseInt(jQuery("#_totalCount").val()) / limit)-1)*parseInt(jQuery("#_pageCount").val()) + 1);
		loadStylePage(jQuery("#start").val(),jQuery("#limit").val());
	});
	jQuery("#_btnNext").click(function(){
		if(parseInt(jQuery("#start").val()) + parseInt(jQuery("#limit").val()) - 1 <= parseInt(jQuery("#_totalCount").val())){
	    	jQuery("#start").val(parseInt(jQuery("#start").val()) + parseInt(jQuery("#limit").val()));
	    	loadStylePage(jQuery("#start").val(),jQuery("#limit").val());
		}
	});
}
function countToolbar(){
	jQuery("#start").val(start);
	jQuery("#limit").val(limit);
	jQuery("#_pageindex").val(Math.ceil(start / limit));
	jQuery("#_totalPageCount").val(Math.ceil(totalCount / limit));
	jQuery("#_pageCount").val(limit);
	jQuery("#_totalCount").val(totalCount);
	
	if(parseInt(jQuery("#_pageindex").val())<=1){
		jQuery("#_btnPre").attr('disabled', true);
		jQuery("#_btnFirst").attr("disabled", true);
	}else{
		jQuery("#_btnPre").removeAttr("disabled");
		jQuery("#_btnFirst").removeAttr("disabled");
	}
	if(parseInt(jQuery("#_pageindex").val())>=parseInt(jQuery("#_totalPageCount").val())){
		jQuery("#_btnNext").attr('disabled', true);
		jQuery("#_btnLast").attr("disabled", true);
	}else{
		jQuery("#_btnNext").removeAttr("disabled");
		jQuery("#_btnLast").removeAttr("disabled");
	}
}