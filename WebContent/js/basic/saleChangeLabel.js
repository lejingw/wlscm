//检查表单有效性
function checkForm(){
	if(!Validator.Validate($("frm"),3))
		return false;
	var pricelabel = jQuery("#pricelabel").attr("checked"),
		tranLabel = jQuery("#tranLabel").attr("checked");
	if(!(pricelabel || tranLabel)){
		alert("<span style='color:red;'>物流标签和价格标签 至少选择其一</span>");
		return false;
	}
	return true;
}
//保存
function saveform(){
	if(!checkForm()){
		return ;
	}
	confirm("确定保存?", function(){
		var pricelabel = jQuery("#pricelabel").attr("checked"),
			tranLabel = jQuery("#tranLabel").attr("checked"),
			isincreaseamount = jQuery("#isincreaseamount").attr("checked"),
			isbasicPrice = jQuery("#isbasicPrice").attr("checked"),
			specialWorkPrice = jQuery("#specialWorkPrice").attr("checked"),
			isupdatecauses = jQuery("#isupdatecauses").attr("checked"),
			isArchiveflag = jQuery("#isArchiveflag").attr("checked"),
			labelkind = "0";
		if(tranLabel){
			labelkind = labelkind + "1";
		} else {
			labelkind = labelkind + "0";
		}
		if(pricelabel){
			labelkind = labelkind + "1";
		} else {
			labelkind = labelkind + "0";
		}
		var saleChangeLabelObj = {
				changelabelId 		:jQuery("#changelabelId").val(),
				changelabelReason	:jQuery("#changelabelReason").val(),
				isincreaseamount	:isincreaseamount?"1":"0",
				isbasicPrice		:isbasicPrice?"1":"0",
				specialWorkPrice	:specialWorkPrice?"1":"0",
				isupdatecauses 		:isupdatecauses?"1":"0",
				labelKind			:labelkind,
				status				:isArchiveflag?"0":"1",
				note				:jQuery('#note').val()
			};
		showLayer(true);
		SaleChangeLabelDwr.saveOrUpdateSaleChangeLabel(saleChangeLabelObj, function(data){
			showLayer(false);
			if(data){
				if(data.isSuccess == "true"){
					alert("保存成功", function(){
						window.location = "saleChangeLabel.vm";
					});
				} else {
					alert(data.msg);
				}
			}else{
				alert("保存失败");
			}
		});
	});
}
