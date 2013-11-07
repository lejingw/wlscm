package com.jatools.manager.move.impl;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.dao.move.MoveDeleteApplyDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.move.MoveBillManager;
import com.jatools.manager.move.MoveDeleteApplyManager;
import com.jatools.vo.move.MoveDeleteApply;
import com.jatools.vo.move.MoveHeadLog;
import com.jatools.vo.move.MoveLineLog;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.ws.remote.WorkflowService;

import java.util.List;
import java.util.Map;

/**
 * Created by 任明.
 * User: Marx
 * Date: 12-10-12
 * Time: 下午1:03
 */
public class MoveDeleteApplyManagerImpl extends BaseManager implements MoveDeleteApplyManager {


    @Override
    public Pager getMoveDeleteApplyPageData(Map<String, String> condition) {
        return this.moveDeleteApplyDao.getMoveDeleteApplyPageData(condition);
    }

    @Override
    public MoveDeleteApply getMoveDeleteApply(String headid) {
        return this.moveDeleteApplyDao.getMoveDeleteApply(headid);
    }

    @Override
    public MoveHeadLog getMoveHeadByMoveDelete(String moveDeleteId) {
        return this.moveDeleteApplyDao.getMoveHeadByMoveDelete(moveDeleteId);
    }

    @Override
    public MoveHeadLog getMoveHeadByMoveId(String moveId) {
        return this.moveDeleteApplyDao.getMoveHeadByMoveId(moveId);
    }

    @Override
    public List<MoveLineLog> getMoveLineByMoveDelete(String moveDeleteId) {
        return this.moveDeleteApplyDao.getMoveLineByMoveDelete(moveDeleteId);
    }

    @Override
    public List<MoveLineLog> getMoveLineByMoveId(String moveId) {
        return this.moveDeleteApplyDao.getMoveLineByMoveId(moveId);
    }

    @Override
    public void saveOrUpdateMoveDeleteApply(MoveDeleteApply moveDeleteApply, String userId) {
        this.saveUpdateCheckMoveDeleteApply(moveDeleteApply, userId, false);
    }

    @Override
    public void saveAndCheckMoveDeleteApply(MoveDeleteApply moveDeleteApply, String userId) {
        this.saveUpdateCheckMoveDeleteApply(moveDeleteApply, userId, true);
    }

    private void saveUpdateCheckMoveDeleteApply(MoveDeleteApply moveDeleteApply, String userId, boolean isCheck) {
        moveDeleteApply.setUpdateDate(DateUtil.getCurrentDate18());
        moveDeleteApply.setUpdateId(userId);
        if(isCheck) {
            moveDeleteApply.setStatus(DictConstant.BILL_STATUS_REVIEWING);
        } else {
            moveDeleteApply.setStatus(DictConstant.BILL_STATUS_SAVE);
        }
        if(StringUtil.isBlank(moveDeleteApply.getHeadid())){
            moveDeleteApply.setBillno(commonDao.getBillno(GlobalConstant.BILL_CODE_MOVE_DELETE_APPLY));
            moveDeleteApply.setCreateDate(DateUtil.getCurrentDate18());
            moveDeleteApply.setCreateId(userId);

            this.asertExistsMoveHeadLog(moveDeleteApply.getMoveHeadid());
            this.moveDeleteApplyDao.saveMoveDeleteApply(moveDeleteApply);
            this.moveDeleteApplyDao.saveMoveHeadLog(moveDeleteApply.getHeadid(), moveDeleteApply.getMoveHeadid());
            this.moveDeleteApplyDao.saveMoveLineLog(moveDeleteApply.getHeadid(), moveDeleteApply.getMoveHeadid());
            // 修改调拨单状态
            this.moveDeleteApplyDao.modifyMoveBillStatus(moveDeleteApply.getMoveHeadid(), DictConstant.BILL_STATUS_APPLY_DELETE);
        } else {
            this.moveDeleteApplyDao.updateMoveDeleteApply(moveDeleteApply);

            if(StringUtil.isNotBlank(moveDeleteApply.getOldMoveHeadid()) && !moveDeleteApply.getMoveHeadid().equals(moveDeleteApply.getOldMoveHeadid())) {
                this.moveDeleteApplyDao.deleteMoveHeadAndLine(moveDeleteApply.getOldMoveHeadid());
                this.asertExistsMoveHeadLog(moveDeleteApply.getMoveHeadid());
                this.moveDeleteApplyDao.saveMoveHeadLog(moveDeleteApply.getHeadid(), moveDeleteApply.getMoveHeadid());
                this.moveDeleteApplyDao.saveMoveLineLog(moveDeleteApply.getHeadid(), moveDeleteApply.getMoveHeadid());
                // 修改调拨单状态
                this.moveDeleteApplyDao.modifyMoveBillStatus(moveDeleteApply.getOldMoveHeadid(), DictConstant.BILL_STATUS_REVIEWED);
                this.moveDeleteApplyDao.modifyMoveBillStatus(moveDeleteApply.getMoveHeadid(), DictConstant.BILL_STATUS_APPLY_DELETE);
            }
        }
        if(isCheck) {
            this.workflowService.enterReview(moveDeleteApply, userId);
        }
    }

    private void asertExistsMoveHeadLog(String moveId) {
        boolean isExists = this.moveDeleteApplyDao.isExistsMoveHeadLog(moveId);
        if(isExists) {
            throw new RuntimeException("调拨单删除已经申请, 无法重复申请");
        }
    }
    @Override
    public Pager getMoveBillpagerData(Map<String, String> condition) {
        return this.moveDeleteApplyDao.getMoveBillpagerData(condition);
    }


    public void deleteMoveDeleteApply (String headid, String userId){
        MoveDeleteApply moveDeleteApply = this.getMoveDeleteApply(headid);
        this.moveDeleteApplyDao.modifyMoveBillStatus(moveDeleteApply.getMoveHeadid(), DictConstant.BILL_STATUS_REVIEWED);
        this.moveDeleteApplyDao.deleteMoveHeadAndLine(moveDeleteApply.getMoveHeadid());
        this.moveDeleteApplyDao.deleteMoveDeleteApply(headid);
    }
    private MoveDeleteApplyDao moveDeleteApplyDao;
    private CommonDao commonDao;
    private WorkflowService workflowService;


    public void setWorkflowService(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }
    public void setMoveDeleteApplyDao(MoveDeleteApplyDao moveDeleteApplyDao) {
        this.moveDeleteApplyDao = moveDeleteApplyDao;
    }

    @Override
    public CommonDao getCommonDao() {
        return this.commonDao;
    }

    public void setCommonDao(CommonDao commonDao) {
        this.commonDao = commonDao;
    }
}
