
function initItenmClass(valueDict) {
    var itemClassId = valueDict.itemClassId||'';
    BdCommonDwr.getAllItemClassForSlt(function(data){
        addOptions("itemClassId", data, null, null, true, true, itemClassId);
        changeItemClass(valueDict);
    });
}

function changeItemClass(valueDict){
    var itemClassId = valueDict.itemClassId||'',
        ornaClassId = valueDict.ornaClassId||'';
    BdCommonDwr.getAllOrnaClassForSlt(itemClassId, function(data){
        addOptions("ornaClassId", data, null, null, true, true, ornaClassId);
        changeOrnaClass(valueDict);
    });
}

function changeOrnaClass(valueDict) {
    var itemClassId = valueDict.itemClassId||'',
        ornaClassId = valueDict.ornaClassId||'',
        styleItemClassId = valueDict.styleItemClassId||'',
        styleId1 = valueDict.styleId1||'',
        styleId2 = valueDict.styleId2||'';
    BdCommonDwr.getStyleForSlt3(itemClassId, ornaClassId, styleItemClassId, function(data){
        addOptions("styleId1", data, null, null, true, true, styleId1);
    });
    BdCommonDwr.getStyleForSlt3(itemClassId, ornaClassId, styleItemClassId, function(data){
        addOptions("styleId2", data, null, null, true, true, styleId2);
    });
}

function checkField(){
    if(!Validator.Validate($("frm"),3))
        return false;
    var styleId1 = jQuery("#styleId1").val(),
          styleId2 = jQuery("#styleId2").val();
    if(styleId1 == styleId2) {
        alert("款式1和款式2不允许相同");
        return false;
    }
    return true;
}

function saveform(){
    if(!checkField()) return;
    var pushStyle = {
        id:jQuery("#id").val(),
        itemClassId:jQuery("#itemClassId").val(),
        ornaClassId:jQuery("#ornaClassId").val(),
        styleItemClassId:jQuery("#styleItemClassId").val(),
        styleId1:jQuery("#styleId1").val(),
        styleId2:jQuery("#styleId2").val()
    };
    confirm("确定保存?", function(){
        showLayer(true);
        PushStyleDwr.saveOrUpdatePushStyle(pushStyle, function(data){
            showLayer(false);
            if(data) {
                alert(data);
            } else {
                alert("保存成功", function(){
                    window.location = "pushStyle.vm";
                });
            }
        });
    });
}

