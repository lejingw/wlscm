package com.jatools.vo.move;

import com.jatools.common.Global;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.intf.ReviewBill;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.UserCache;

/**
 * Created by 任明.
 * User: Marx
 * Date: 12-10-11
 * Time: 下午8:51
 */
public class MoveDeleteApply implements ReviewBill {
    private String headid;
    private String billno;
    private String dodate;
    private String orgId;
    private String status;
    private String isCheck;
    private String memo;
    private String createDate;
    private String createId;
    private String updateDate;
    private String updateId;
    private String moveHeadid;
    private String oldMoveHeadid;
    private String moveBillno;

    public String getOldMoveHeadid() {
        return oldMoveHeadid;
    }

    public void setOldMoveHeadid(String oldMoveHeadid) {
        this.oldMoveHeadid = oldMoveHeadid;
    }

    public String getMoveBillno() {
        return moveBillno;
    }

    public void setMoveBillno(String moveBillno) {
        this.moveBillno = moveBillno;
    }

    public String getMoveHeadid() {
        return moveHeadid;
    }

    public void setMoveHeadid(String moveHeadid) {
        this.moveHeadid = moveHeadid;
    }

    public String getHeadid() {
        return headid;
    }

    public void setHeadid(String headid) {
        this.headid = headid;
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billNo) {
        this.billno = billNo;
    }

    public String getDodate() {
        return dodate;
    }

    public void setDodate(String dodate) {
        this.dodate = dodate;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCheck() {
        return isCheck;
    }

    public void setCheck(String check) {
        isCheck = check;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getOrgName() {
        return OrgCache.getInstance().getOrgName(this.orgId);
    }

    public String getCreateName() {
        return UserCache.getInstance().getUserName(this.createId);
    }

    public String getUpdateName() {
        return UserCache.getInstance().getUserName(this.updateId);
    }

    @Override
    public String getBillCode() {
        return GlobalConstant.BILL_CODE_MOVE_DELETE_APPLY;
    }

    @Override
    public String getBillid() {
        return this.headid;
    }

    @Override
    public String getPageUrl() {
        return Global.CONTEXT + "/move/moveDeleteApply.vm?user_action=toEdit&headid=" + headid;
    }

    @Override
    public String getBeanName() {
        return "moveDeleteApplyDao";
    }
}
