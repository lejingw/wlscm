function loadItemClass(){
	//大类下的小类
	BdCommonDwr.getOrnaClassByItemClassIdForSlt(jQuery("#itemClassId").val(), function(data){
		addOptions("ornaClassId", data, null, null, true, true);
	});
}
/**
 * 保存
 */
function saveLabelType(){alert(jQuery("#privateType").attr("checked"))
	if(!Validator.Validate($("frm"),3)){
		return ;
	}
	confirm("确定保存?", function(){
		var labelType = {
				labelTypeId:jQuery("#labelTypeId").val(),
				labelTypeName:jQuery("#labelTypeName").val(),
				itemClassId:jQuery("#itemClassId").val(),
				ornaClassId:jQuery("#ornaClassId").val(),
				unitId:jQuery("#unitId").val(),
				bracketFlag:jQuery("#bracketFlag").attr("checked")?'1':'0',
				dblLabelFlag:jQuery("#dblLabelFlag").attr("checked")?'1':'0',
				archiveflag:jQuery("#archiveflag").attr("checked")?'1':'0',
				privateType:jQuery("#privateType").attr("checked")?'1':'0',
				memo:jQuery("#memo").val()
		};
		showLayer(true);
		LabelTypeDwr.checkLabelType(labelType, function(data1){		
			showLayer(false);
			if(data1 == "-1"){
				confirm("标签名称或是否双标签已经修改，需要审批，来更新库存信息，确定继续?", function(){
					var head = labelType;
					saveOrUpdate(head);
				});
			}else if(data1){
				alert(data1);
			}else{
				saveOrUpdate(labelType);
			}
		});
	});
}
function saveOrUpdate(labelType){
	showLayer(true);
	LabelTypeDwr.saveOrUpdateLabelType(labelType, function(data){
		showLayer(false);
		alert(data?data:"保存成功", function(){
			if(!data){
				window.location = "labelType.vm";
			}
		});
	});
}
/**
 * 更新库存
 */
function updateStock(){
	if(isNull(jQuery("#labelTypeId").val())){
		alert("请先保存数据");
		return ;
	}
	confirm("确定更新库存，此操作涉及修改如下表：<br>库存表、标签申请单、标签打印单、调拔单、入库单、现有量表?", function(){
		LabelTypeDwr.updateStock(jQuery("#labelTypeId").val(), function(data){
			alert(data?data:"更新库存成功", function(){
				if(!data){
					window.location = "labelType.vm";
				}
			});
		});
	});
}