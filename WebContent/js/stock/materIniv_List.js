/**
 * 标签打印
 */
function btnPrint(){
	var index = getSelectIndexs('chk');
	if(index.length==0){
		alert("请选择要打印的记录");
		return ;
	}else{
		var ids = [] ;
		for(var i=0; i<index.length; i++){
			ids.push($n("chk")[index[i]].value);
		}
		var selectLabel = jQuery("#selectLabel").val();
		var pt = "";
		var params="";
		if(selectLabel=="1"){
			pt="1";
			params="-1";
		}else if(selectLabel=="2"){
			pt="2";
			params="-2";
		}else if(selectLabel=="3"){
			pt="3";
			params="-3";
		}
		if(ids.toString()){
			var url = ctxPath + '/stock/materIniv.vm?user_action=printLabels&billid='+ids.toString()+"&pt="+pt+"&params="+params;
			showLabelPrint(url);
		}
		
	}
}

function exportListToExcel(){
	jQuery("#user_action").val("exportExcel");
	jQuery("#myForm").submit();
	//user_action改回之前的值，否则之后的任何提交如果不重新设置user_action，则都将是导出excel，比如上下翻页
	jQuery("#user_action").val("");
}
/**
 * 删除
 * @param id
 * @param state
 */
function deleteMaterIniv(){
	var index = getSelectIndexs('chk');
	if(index.length!=1){
		alert("请选择一行");
		return;
	}
	var state = $n("state")[index[0]].value;
	var id = $n("chk")[index[0]].value;
	if("1"!=state){
		alert("单据状态不为保存");
		return;
	}
	confirm("确定删除?", function(){
		MaterInivDwr.deleteMaterIniv(id, function(errMsg){
			if(errMsg){
				alert(errMsg);
			}else{
				alert("删除成功", function(){
        			window.location = "materIniv.vm";
				});
			}
		});
	});
}
function changeLabelType(){
	var selectLabel = jQuery("#selectLabel").val();
	jQuery("#myForm").submit();
}
function showAllBill(obj){
	jQuery("#myForm").submit();
}


function showdz(){	
	var options = {
			title : '修改定做单号',
			contentType : 'iframe',
			iframeId : "dzWindowId",
			width : 750,
			height : 250,
			okBtnName : '确定',
			closeable:true,
			boxid:'winDiv',
			onok : function(box) {
				var result = jQuery("#dzWindowId")[0].contentWindow.getValues();
				if(result==""||result==null)
					return;
				else{
					PriceHeadDwr.updateMaterDZByCode(result.wlCode,result.dzCode, function(count){
						alert("修改成功");
					});
				}
				box.close();
			},
			oncancel : function(box) {
				box.close();
			}
		};
		var url = ctxPath + "/calc/priceHead.vm?user_action=getDZWindow";
		printWin= jQuery.weeboxs.open(url, options);
}