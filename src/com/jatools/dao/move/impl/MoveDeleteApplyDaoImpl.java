package com.jatools.dao.move.impl;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.intf.ReviewActionIntf;
import com.jatools.dao.BaseDao;
import com.jatools.dao.move.MoveDeleteApplyDao;
import com.jatools.vo.move.MoveDeleteApply;
import com.jatools.vo.move.MoveHeadLog;
import com.jatools.vo.move.MoveLineLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 任明.
 * User: Marx
 * Date: 12-10-12
 * Time: 上午11:51
 */
public class MoveDeleteApplyDaoImpl extends BaseDao implements MoveDeleteApplyDao , ReviewActionIntf {
    @Override
    public Pager getMoveDeleteApplyPageData(Map<String, String> condition) {
        return this.executeQueryForPager("MoveDeleteApply.getMoveDeleteApplyPageData", "MoveDeleteApply.getMoveDeleteApplyTotalCount", condition);
    }

    @Override
    public MoveDeleteApply getMoveDeleteApply(String headid) {
        return (MoveDeleteApply)this.executeQueryForObject("MoveDeleteApply.getMoveDeleteApply", headid);
    }

    @Override
    public MoveHeadLog getMoveHeadByMoveDelete(String moveDeleteId) {
        return this.getMoveHeadLog(null, moveDeleteId);
    }

    @Override
    public MoveHeadLog getMoveHeadByMoveId(String moveId) {
        return this.getMoveHeadLog(moveId, null);
    }

    private MoveHeadLog getMoveHeadLog(String moveId, String moveDeleteId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("moveId", moveId);
        params.put("moveDeleteId", moveDeleteId);
        return (MoveHeadLog)this.executeQueryForObject("MoveDeleteApply.getMoveHeadLog", params);
    }
    @Override
    public List<MoveLineLog> getMoveLineByMoveDelete(String moveDeleteId) {
        return this.getMoveLine(null, moveDeleteId);
    }

    @Override
    public List<MoveLineLog> getMoveLineByMoveId(String moveId) {
        return this.getMoveLine(moveId, null);
    }

    @SuppressWarnings("unchecked")
    private List<MoveLineLog> getMoveLine (String moveId, String moveDeleteId){
        Map<String, String> params = new HashMap<String, String>();
        params.put("moveId", moveId);
        params.put("moveDeleteId", moveDeleteId);
        return (List<MoveLineLog>)this.executeQueryForList("MoveDeleteApply.getMoveLineLog", params);
    }

    @Override
    public void saveMoveDeleteApply(MoveDeleteApply moveDeleteApply) {
        this.executeInsert("MoveDeleteApply.saveMoveDeleteApply", moveDeleteApply);
    }

    public void updateMoveDeleteApply(MoveDeleteApply moveDeleteApply){
        this.executeUpdate("MoveDeleteApply,updateMoveDeleteApply", moveDeleteApply);
    }

    public void deleteMoveDeleteApply(String moveDeleteId){
        this.delete("MoveDeleteApply.deleteMoveDeleteApply", moveDeleteId);
    }

    @Override
    public void saveMoveHeadLog(String moveDeleteId, String moveId) {
        this.executeInsert("MoveDeleteApply.insertMoveHeadLog", moveId);
    }

    @Override
    public void saveMoveLineLog(String moveDeleteId, String moveId) {
        this.executeInsert("MoveDeleteApply.insertMoveLineLog", moveId);
    }

    @Override
    public Pager getMoveBillpagerData(Map<String, String> condition) {
        return this.executeQueryForPager("MoveDeleteApply.getMoveBillPageData", "MoveDeleteApply.getMoveBillTotalCount", condition);
    }

    @Override
    public void deleteMoveHeadAndLine(String moveId) {
        this.delete("MoveDeleteApply.deleteMoveLineLog", moveId);
        this.delete("MoveDeleteApply.deleteMoveHeadLog", moveId);
    }

    @Override
    public void reviewSuccess(String billid, String userid) {
        this.modifyMoveDeleteApplyStatus(billid, DictConstant.BILL_STATUS_CLOSED);
        // 修改现有量状态
        this.executeUpdate("MoveDeleteApply.markMaterActiveValid", billid);
        // 备份配货记录
        this.executeInsert("MoveDeleteApply.bakDispatchOrnaLog", billid);
        // 删除配货数据
        this.delete("MoveDeleteApply.deleteDispatchOrnaLog", billid);

        // 删除调拨指令单
        this.delete("MoveDeleteApply.deleteMoveCmdLine", billid);
        this.delete("MoveDeleteApply.deleteMoveCmdHead", billid);

        // 删除调拨单 头行
        this.delete("MoveDeleteApply.deleteMoveBillLine", billid);
        this.delete("MoveDeleteApply.deleteMoveBillHead", billid);
    }

    @Override
    public void reviewFail(String billid, String userid) {
        this.modifyMoveDeleteApplyStatus(billid, DictConstant.BILL_STATUS_SAVE);
    }

    @Override
    public void modifyMoveDeleteApplyStatus(String headid, String status) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("headid", headid);
        params.put("status", status);
        this.executeUpdate("MoveDeleteApply.updateMoveDeleteApplyStatus", params);
    }

    @Override
    public void modifyMoveBillStatus(String headid, String status) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("headid", headid);
        params.put("status", status);
        this.executeUpdate("MoveDeleteApply.updateMoveBillStatus", params);
    }

    public boolean isExistsMoveHeadLog(String moveId) {
        Integer res = (Integer)this.executeQueryForObject("MoveDeleteApply.isExistsMoveHeadLog", moveId);
        if(res != null && res.intValue() > 0) {
            return true;
        }
        return false;
    }

}
