/**
 * User: Marx
 * Date: 12-10-12
 * Time: 下午3:04
 */
function showMoveBillWin(){
    var _iframeId = "MoveBillIframe";
    var options = {
        title : '调拨单',
        contentType : 'iframe',
        iframeId : _iframeId,
        width : 800,
        height : 400,
        okBtnName : '确定',
        //cancelBtnName : '',
        closeable:true,
        boxid:'winDiv',
        onok : function(box) {
            var result = jQuery("#" + _iframeId)[0].contentWindow.getValues();
            if(!result ){
                return ;
            }
            var headid = jQuery("#headid").val();
            var moveHeadid = result.headid;
            window.location = "moveDeleteApply.vm?user_action=toEdit&headid="+headid+"&moveHeadid="+moveHeadid+"&dodate=" + jQuery("#dodate").val();
            box.close();
        },
        oncancel : function(box) {
            box.close();
        }
    };
    var url = ctxPath + '/move/moveDeleteApply.vm?user_action=moveBill&headid='+jQuery("#headid").val();
    jQuery.weeboxs.open(url, options);
}


//检查表单有效性
function checkForm(){
    if(!Validator.Validate($("frm"),3))
        return false;

    var moveBillno = jQuery("#moveBillno").val();
    if(!moveBillno) {
        alert("请选择调拨单");
        return false;
    }
    return true;
}

/**
 * 保存表单
 */
function saveform() {
    if(!checkForm()){
        return ;
    }
    confirm("确定要保存？", function(){
        var head = {
            headid : jQuery("#headid").val(),
            billno : jQuery("#billno").val(),
            dodate : jQuery("#dodate").val(),
            orgId  : jQuery("#orgId").val(),
            memo   : jQuery("#memo").val(),
            status:'1',
            oldMoveHeadid: jQuery("#oldMoveHeadid").val(),
            moveHeadid: jQuery("#moveHeadid").val()
        };
        showLayer(true);
        MoveDeleteApplyDwr.saveOrUpdateMoveDeleteApply(head, function(msg){
            showLayer(false);
            if(msg) {
                alert(msg);
            } else {
                alert("保存成功", function(){
                    window.location = "moveDeleteApply.vm";
                });
            }
        });
    });
}

function checkBill(){
    if(!checkForm()){
        return ;
    }
    confirm("确认要提交审核？", function(){
        var head = {
            headid : jQuery("#headid").val(),
            billno : jQuery("#billno").val(),
            dodate : jQuery("#dodate").val(),
            orgId  : jQuery("#orgId").val(),
            memo   : jQuery("#memo").val(),
            status:'1',
            oldMoveHeadid: jQuery("#oldMoveHeadid").val(),
            moveHeadid: jQuery("#moveHeadid").val()
        };
        showLayer(true);
        MoveDeleteApplyDwr.saveAndCheckMoveDeleteApply(head, function(data){
            showLayer(false);
            if(data) {
                if(data['isSuccess'] == "true"){
                    alert("单据提交成功", function(){
                        window.location = "moveDeleteApply.vm?user_action=toEdit&headid=" + data['headid'];
                    });
                } else {
                    alert(data['msg']);
                }
            } else {
                alert("提交审核失败");
            }
        });
    });
}

function changeSeq(tblId){
    var len = $(tblId).rows.length;
    for(var i=0; i<len; i++){
        $(tblId).rows[i].cells[0].innerHTML = len-i;
    }
}