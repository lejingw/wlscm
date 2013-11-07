
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
        analysisId = valueDict.analysisId||'';
        styleItemClassId = valueDict.styleItemClassId||'';
    BdCommonDwr.getAnalysisForSlt(itemClassId, ornaClassId, function(data){
        addOptions("analysisId", data, null, null, true, true, analysisId);
    });
    BdCommonDwr.getStyleItemClassForSlt(itemClassId, ornaClassId, function(data){
        addOptions("styleItemClassId", data, null, null, true, true, styleItemClassId);
        changeStyleItemClass(valueDict);
    });
}

function changeStyleItemClass(valueDict) {
    var itemClassId = valueDict.itemClassId||'',
        ornaClassId = valueDict.ornaClassId||'',
        styleItemClassId = valueDict.styleItemClassId||'',
        styleId = valueDict.styleId||'';
    BdCommonDwr.getStyleForSlt3(itemClassId, ornaClassId, styleItemClassId, function(data){
        addOptions("styleId", data, null, null, true, true, styleId);
    });
}

function checkStyleRate() {
    var styleRate = jQuery("#styleRate").val();
    if(!styleRate || !isInteger((styleRate))) {
        alert("款式比例必须为整数");
        return false;
    } else if(floatSub(styleRate, 0) <=0 || floatSub(styleRate, 99) >0) {
        alert("款式比例必须为1-99之间的整数");
        return false;
    }
    return true;
}

function checkField(){
    if(!Validator.Validate($("frm"),3))
        return false;
    if(!checkStyleRate()) return false;
    return true;
}

function saveform(){
    if(!checkField()) return;
    var salable = {
        id:jQuery("#id").val(),
        itemClassId:jQuery("#itemClassId").val(),
        ornaClassId:jQuery("#ornaClassId").val(),
        analysisId:jQuery("#analysisId").val(),
        styleItemClassId:jQuery("#styleItemClassId").val(),
        styleId:jQuery("#styleId").val(),
        styleRate:jQuery("#styleRate").val()
    };
    confirm("确定保存?", function(){
        showLayer(true);
        SalableDwr.saveOrUpdateSalable(salable, function(data){
            showLayer(false);
            if(data) {
                alert(data);
            } else {
                alert("保存成功", function(){
                    window.location = "salable.vm";
                });
            }
        });
    });
}

