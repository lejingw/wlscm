/**
 * 根据门店总单行id，获取在table中的下标
 * @param useOrderLineIdFlag true表示lineId是门店总单行id,false表示总部总单行id
 * @param lineId 门店总单行id或者总部总单行id
 * @returns {Number} 门店总单行id在table中的下标，未找到返回-1
 */
function getIndexByOrderLineId(useOrderLineIdFlag, lineId){
	if(useOrderLineIdFlag == "1"){
		for(var i=0;i<$n("orderLineId").length;i++){
			if(lineId == $n("orderLineId")[i].value){
				return i;
			}
		}
	}else{
		for(var i=0;i<$n("gatherLineId").length;i++){
			if(lineId == $n("gatherLineId")[i].value){
				return i;
			}
		}
	}
	return -1;
}
/**
 * 获取正配货数量控件
 * @param useOrderLineIdFlag true表示lineId是门店总单行id,false表示总部总单行id
 * @param lineId 门店总单行id或者总部总单行id
 * @returns
 */
function getDispatchingNumInput(useOrderLineIdFlag, lineId){
	var index = getIndexByOrderLineId(useOrderLineIdFlag, lineId);
	if(-1 != index){
		return $n("dispatchingNumInput")[index];
	}
	return null;
}
/**
 * 更新正配货数量显示
 * @param orderLineId 门店总单行id
 */
function changeDispatchingNum(orderLineId){
	var obj = getDispatchingNumInput(true, orderLineId);
	if(obj){
		DispatchOrnaDwr.getDispatchingNum(orderLineId, function(data){
			obj.value = data;
		});
	}
}
/**
 * 获取已配货数量控件
 * @param useOrderLineIdFlag true表示lineId是门店总单行id,false表示总部总单行id
 * @param lineId 门店总单行id或者总部总单行id
 */
function getDispatchedNumInput(useOrderLineIdFlag, lineId){
	var index = getIndexByOrderLineId(useOrderLineIdFlag, lineId);
	if(-1 != index){
		return $n("dispatchedNumInput")[index];
	}
	return null;
}
/**
 * 更新已配货数量显示
 * @param useOrderLineIdFlag true表示lineId是门店总单行id,false表示总部总单行id
 * @param lineId 门店总单行id或者总部总单行id
 */
function changeDispatchedNum(useOrderLineIdFlag, lineId){
	var obj = getDispatchedNumInput(useOrderLineIdFlag, lineId);
	if(obj){
		DispatchOrnaDwr.getDispatchedNum(useOrderLineIdFlag, lineId, function(data){
			obj.value = data;
		});
	}
}
/**
 * 显示正配数据
 */
function showDispatching(obj, orderLineId){
	var _iframeId = "dispatchingOrna";
	var options = {
			title : '查看正分配饰品',
			contentType : 'iframe',
			iframeId : _iframeId,
			width : 1000,
			height : 350,
			okBtnName : '关闭',
			//cancelBtnName : '',
			showCancel : false,
			closeable:true,
			boxid:'winDiv',
			onok : function(box) {
				box.close();
			}
	};
	var url =ctxPath + "/stock/dispatchOrna.vm?user_action=showDispatching&orderLineId=" + orderLineId;
	jQuery.weeboxs.open(url, options);
}
/**
 * 显示已配数据
 */
function showDispatched(obj, useOrderLineIdFlag, lineId, srcBillCode){
	var _iframeId = "dispatchedOrna";
	var options = {
		title : '查看已分配饰品',
		contentType : 'iframe',
		iframeId : _iframeId,
		width : 1000,
		height : 350,
		okBtnName : '关闭',
		//cancelBtnName : '',
		showCancel : false,
		closeable:true,
		boxid:'winDiv',
		onok : function(box) {
			box.close();
		}
	};
	var url =ctxPath + "/stock/dispatchOrna.vm?user_action=showDispatched&useOrderLineIdFlag="+(useOrderLineIdFlag?1:0)+"&lineId=" + lineId+"&srcBillCode="+(srcBillCode||'');
	jQuery.weeboxs.open(url, options);
}
/**
 * 刷新显示正配货饰品记录窗口
 */
function refreshShowDispatchingWin(newOrderLineId, oldOrderLineId){
	jQuery("#dispatchingOrna")[0].contentWindow.refreshWin();
	if(newOrderLineId){
		changeDispatchingNum(newOrderLineId);
	}
	if(oldOrderLineId){
		changeDispatchingNum(oldOrderLineId);		
	}
}