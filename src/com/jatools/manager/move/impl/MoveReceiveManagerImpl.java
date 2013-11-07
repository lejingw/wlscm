package com.jatools.manager.move.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.dao.move.MoveBillDao;
import com.jatools.dao.move.MoveReceiveDao;
import com.jatools.dao.stock.MaterActiveDao;
import com.jatools.dao.stock.MaterTransDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.move.MoveReceiveManager;
import com.jatools.vo.move.MoveBillHead;
import com.jatools.vo.move.MoveBillLine;
import com.jatools.web.cache.OrgCache;

public class MoveReceiveManagerImpl extends BaseManager implements MoveReceiveManager {
	private MoveReceiveDao moveReceiveDao;
	private MoveBillDao moveBillDao;
	private MaterTransDao materTransDao;
	private MaterActiveDao materActiveDao;
	private CommonDao commonDao;

	@Override
	public CommonDao getCommonDao() {
		return this.commonDao;
	}
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	public void setMaterTransDao(MaterTransDao materTransDao) {
		this.materTransDao = materTransDao;
	}
	public void setMaterActiveDao(MaterActiveDao materActiveDao) {
		this.materActiveDao = materActiveDao;
	}
	public void setMoveBillDao(MoveBillDao moveBillDao) {
		this.moveBillDao = moveBillDao;
	}
	public void setMoveReceiveDao(MoveReceiveDao moveReceiveDao) {
		this.moveReceiveDao = moveReceiveDao;
	}
	/**
	 * 获取调拨单分页数据
	 * @param condition
	 * @return
	 */
	public Pager getMoveReceivePageData(Map<String, String> condition, String billType, String jmFlag, String orgId) {
		return moveReceiveDao.getMoveReceivePageData(condition, billType, jmFlag, orgId);
	}
	/**
	 * 获取调拨单行
	 * @param headid
	 * @return
	 */
	public List<MoveBillLine> getReceivedMoveBillLine(String headid) {
		return moveReceiveDao.getReceivedMoveBillLine(headid);
	}
	/**
	 * 获取调拨单头
	 * @param headid
	 * @return
	 */
	public MoveBillHead getMoveBillHead(String headid){
		return moveBillDao.getMoveBillHead(headid);
	}
	/**
	 * 获取饰品信息
	 * @param code
	 * @param ornaFlag
	 * @return
	 */
	public MoveBillLine getMoveLineInfo(String code, boolean ornaFlag, String billType, String jmFlag){
		return moveReceiveDao.getMoveLineInfo(code, ornaFlag, billType, jmFlag);
	}
	/**
	 * 接收调拨单行
	 * @param lineid
	 * @param userId
	 */
	public void receiveMoveLine(String lineid, String inGroupNo, String userId){
		moveReceiveDao.receiveMoveLine(lineid, inGroupNo, userId);
		moveBillDao.updateNorececount(null, lineid, userId);
	}
	/**
	 * 删除已接收的记录
	 * @param lineid
	 * @param userid
	 */
	public void deleteMoveReceiveLine(String lineid, String userid){
		moveReceiveDao.deleteMoveReceiveLine(lineid, userid);
		moveBillDao.updateNorececount(null, lineid, userid);
	}
	/**
	 * 检查是否存在已接收但未入库的调拨单行
	 * @param headid
	 * @return
	 */
	public boolean checkMoveLineStatusAvail(String headid){
		Long count = moveReceiveDao.getReceivedMoveLineCount(headid);
		return count < 1;
	}
	/**
	 * 将已经接收的饰品入库，并更改状态为已入库11
	 * @param headid
	 * @param userid
	 */
	public void inivMoveLine(String headid, String billType, String jmFlag, String userid){
		String billCode = null;
		if(DictConstant.YES_OR_NO_YES.equals(jmFlag)){
			if(	DictConstant.MOVE_BILL_TYPE_DIAOBODAN.equals(billType)){
				billCode = GlobalConstant.BILL_CODE_DIAOBODAN_JM;//调拨单
			}else if (DictConstant.MOVE_BILL_TYPE_TUIHUODAN.equals(billType)){
				billCode = GlobalConstant.BILL_CODE_TUIHUODAN_JM;//退货单
			}else if(DictConstant.MOVE_BILL_TYPE_TUICANDAN.equals(billType)){
				billCode = GlobalConstant.BILL_CODE_TUICANDAN_JM;//退残单
			}else{
				throw new RuntimeException("单据类型错误");
			}
		}else{
			if(	DictConstant.MOVE_BILL_TYPE_DIAOBODAN.equals(billType)){
				billCode = GlobalConstant.BILL_CODE_DIAOBODAN;//调拨单
			}else if (DictConstant.MOVE_BILL_TYPE_TUIHUODAN.equals(billType)){
				billCode = GlobalConstant.BILL_CODE_TUIHUODAN;//退货单
			}else if(DictConstant.MOVE_BILL_TYPE_TUICANDAN.equals(billType)){
				billCode = GlobalConstant.BILL_CODE_TUICANDAN;//退残单
			}else{
				throw new RuntimeException("单据类型错误");
			}
		}
        boolean isJmMove = moveReceiveDao.isJmMove(headid);
		//写事物处理表，出库
		materTransDao.insertFromMoveBill(billCode, headid, userid, true);
		//写事物处理表，入库
		materTransDao.insertFromMoveBill(billCode, headid, userid, false);
		//改现有量，组织、仓库，柜组，网点金额
		materActiveDao.updateOrgGroupFromMoveBill(headid, userid, isJmMove);
		//改状态为已入库11
		moveReceiveDao.inivMoveLine(headid, userid);
		moveBillDao.updateNorececount(headid, null, userid);
		//判断是否都已经入库，如果是则关闭单据
		Integer unInivCount = moveReceiveDao.getUnInivCount(headid);
		if(unInivCount<1){
			//关闭调拨单
			moveBillDao.closeMoveBill(headid, userid);
			MoveBillHead head = moveBillDao.getMoveBillHead(headid);
			//如果是来自订单配货的调拨，改配货饰品状态为已到店
			if(GlobalConstant.BILL_CODE_GATHER_BILL_JIHUA.equals(head.getSrcBillCode())
					|| GlobalConstant.BILL_CODE_PUSH_GATHER_BILL.equals(head.getSrcBillCode())){
				moveBillDao.updateDispatchOrnaStatus(headid, userid);
			}
		}
	}
	/**
	 * 未接收饰品
	 * @param headid
	 * @return
	 */
	public List<MoveBillLine> getUnreceiveMoveBillLine(String headid){
		return moveReceiveDao.getUnreceiveMoveBillLine(headid);
	}
	/**
	 * 已入库饰品
	 * @param headid
	 * @return
	 */
	public List<MoveBillLine> getInivOrnaMoveBillLine(String headid){
		return moveReceiveDao.getInivOrnaMoveBillLine(headid);
	}
	/**
	 * 关闭调拨单
	 * @param headid
	 * @param userid
	 */
	public void closeMoveBill(String headid, String userid){
		//关闭、部分关闭调拨单
		moveBillDao.closeMoveBill(headid, userid);
		MoveBillHead head = moveBillDao.getMoveBillHead(headid);
		boolean normalMoveFlag = false;
		// 如果是加盟的，且部分接收的，则需要确认后，才能废弃行、改现有量状态为有效
		//          如果是全部接收的，则饰品调拨状态改为关闭，此时若是来自订单配货，则需要改配货记录状态为已到店
		if (DictConstant.YES_OR_NO_YES.equals(head.getJmFlag())){
			if(DictConstant.MOVE_BILL_TYPE_DIAOBODAN.equals(head.getBillType())){
				//加盟店之间调拨，则结果与正常调拨单一致
				if(DictConstant.YES_OR_NO_YES.equals(OrgCache.getInstance().getOrgById(head.getOutOrgId()).getJmFlag())){
					normalMoveFlag = true;
				}else if(DictConstant.BILL_STATUS_CLOSED.equals(head.getStatus())){
					//如果是加盟调拨单，且全部都接收了
					//如果是来自订单配货的调拨，改配货饰品状态为已到店
					if(GlobalConstant.BILL_CODE_GATHER_BILL_JIHUA.equals(head.getSrcBillCode())
						|| GlobalConstant.BILL_CODE_PUSH_GATHER_BILL.equals(head.getSrcBillCode())){
						moveBillDao.updateDispatchOrnaStatus(headid, userid);
					}
				}
			}
		}else{
			normalMoveFlag = true;
		}
		if(normalMoveFlag){
			//废弃行
			moveReceiveDao.discardMoveLine(headid, userid);
			//改现有量状态，将废弃的行记录还原为有效
			materActiveDao.updateStatusFromMoveBill(headid, userid);
			moveBillDao.updateNorececount(headid, null, userid);
			//如果是来自订单配货的调拨，改配货饰品状态为已到店
			if(GlobalConstant.BILL_CODE_GATHER_BILL_JIHUA.equals(head.getSrcBillCode())
				|| GlobalConstant.BILL_CODE_PUSH_GATHER_BILL.equals(head.getSrcBillCode())){
				moveBillDao.updateDispatchOrnaStatus(headid, userid);
			}
		}
	}
	/**
	 *  修改调入柜组
	 * @param lineid
	 * @param inGroupNo
	 */
	public void changeInGroupNo(String lineid, String inGroupNo){
		moveReceiveDao.changeInGroupNo(lineid, inGroupNo);
	}
	/**
	 * 生成标签申请单
	 * @param headid
	 * @param sessionUserId
	 */
	public void createLabelApply(String headid, String userId){
		String applyBillNo = commonDao.getBillno(GlobalConstant.BILL_CODE_LABEL_APPLY);
		String applyHeadId = moveReceiveDao.createLabelApplyHead(headid, userId, applyBillNo);
		moveReceiveDao.createLabelApplyLine(headid, applyHeadId, userId);
		moveReceiveDao.updateLabelApplyFlag(headid, userId);
	}
	public Integer getLabelApplyLineCount(String headid){
		return moveReceiveDao.getLabelApplyLineCount(headid);
	}
	/**
	 * 确认关闭（加盟才有的）
	 * @param headid
	 * @param userid
	 */
	public void confirmClose(String headid, String userid){
		asertStatus("jat_move_head", "headid", headid, "status", DictConstant.BILL_STATUS_CLOSED_PART);
		//废弃行
		moveReceiveDao.discardMoveLine(headid, userid);
		//改现有量状态，将废弃的行记录还原为有效
		materActiveDao.updateStatusFromMoveBill(headid, userid);
		MoveBillHead head = moveBillDao.getMoveBillHead(headid);
		//若是调拨单，则改调拨单状态为关闭
		if(DictConstant.MOVE_BILL_TYPE_DIAOBODAN.equals(head.getBillType())){
			moveBillDao.updateMoveBillStatus(headid, userid, DictConstant.BILL_STATUS_CLOSED);
			//生成红字加盟销售结算单
			String result = moveBillDao.backRedSaleEstimate(headid, userid);
			if(!DictConstant.CALL_PROCEDURE_SUCCESS_FLAG.equals(result)){
				throw new RuntimeException("调用加盟系统接口,生成红字加盟销售结算单失败");
			}
			//如果是来自订单配货的调拨，改配货饰品状态为已到店
			if(GlobalConstant.BILL_CODE_GATHER_BILL_JIHUA.equals(head.getSrcBillCode())
				|| GlobalConstant.BILL_CODE_PUSH_GATHER_BILL.equals(head.getSrcBillCode())){
				moveBillDao.updateDispatchOrnaStatus(headid, userid);
			}
		}else{
			//若是退货单、退残单，则改状态为未生成结算单
			moveBillDao.updateMoveBillStatus(headid, userid, DictConstant.BILL_STATUS_NO_ESTIMATE);
		}
	}
	/**
	 * 继续接收（加盟才有的）
	 * @param headid
	 * @param userid
	 */
	public void continueReceive(String headid, String userid){
		asertStatus("jat_move_head", "headid", headid, "status", DictConstant.BILL_STATUS_CLOSED_PART);
		moveBillDao.updateMoveBillStatus(headid, userid, DictConstant.BILL_STATUS_RECEIVING);
	}
}