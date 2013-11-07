package com.jatools.dao.move.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.intf.ReviewActionIntf;
import com.jatools.dao.BaseDao;
import com.jatools.dao.move.MovePackDao;
import com.jatools.vo.move.MovePackHead;
import com.jatools.vo.move.MovePackLine;
import com.jatools.web.util.DateUtil;

@SuppressWarnings("unchecked")
public class MovePackDaoImpl extends BaseDao implements MovePackDao, ReviewActionIntf {

	/**
	 * 获取装箱单分页数据
	 * @param condition
	 * @return
	 */
	public Pager getMovePackPageData(Map<String, String> condition, String billType, String jmFlag, String orgId, String userId){
		condition.put("billType", billType);
		condition.put("jmFlag", jmFlag);
		condition.put("orgId", orgId);
		condition.put("userId", userId);
		return executeQueryForPager("MovePack.getMovePackPageData", "MovePack.getMovePackTotalCount", condition);
	}
	/**
	 * 获取装箱单信息
	 * @param moveNo
	 * @return
	 */
	public MovePackLine getMoveBillInfo(String moveNo, String jmFlag){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("moveNo", moveNo);
		condition.put("jmFlag", jmFlag);
		return (MovePackLine) executeQueryForObject("MovePack.getMoveBillInfo", condition);
	}
	/**
	 * 获取维修单数据
	 * @param fixNo
	 * @return
	 */
	public MovePackLine getFixBillInfo(String fixNo){
		return (MovePackLine) executeQueryForObject("MovePack.getFixBillInfo", fixNo);
	}
	/**
	 * 获取交接数据
	 * @param handoverNo
	 * @return
	 */
	public MovePackLine getHandoverBillInfo(String handoverNo){
		return (MovePackLine) executeQueryForObject("MovePack.getHandoverBillInfo", handoverNo);
	}
	/**
	 * 形态转换单
	 * @param procChangeNo
	 * @return
	 */
	public MovePackLine getProcChangeBillInfo(String procChangeNo){
		return (MovePackLine) executeQueryForObject("MovePack.getProcChangeBillInfo", procChangeNo);
	}
	/**
	 * 检查新添加的单据是否已经存在
	 * @param newLineList
	 * @return
	 */
	public List<String> checkBillStatusAvail(List<MovePackLine> newLineList, String billType, String jmFlag, String headid){
		String tmp = "$";
		for(MovePackLine line :newLineList){
			tmp += "," + line.getBillId();
		}
		if("$".equals(tmp)){
			return null;
		}
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("billType", billType);
		condition.put("jmFlag", jmFlag);
		condition.put("headid", headid);
		condition.put("billids", tmp.substring(2));
		return executeQueryForList("MovePack.checkBillStatusAvail", condition);
	}
	public List<String> checkBillStatusAvail2(List<MovePackLine> newLineList, String billType, String jmFlag, String status){
		String tmp = "$";
		for(MovePackLine line :newLineList){
			tmp += "," + line.getBillId();
		}
		if("$".equals(tmp)){
			return null;
		}
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("billType", billType);
		condition.put("jmFlag", jmFlag);
		condition.put("status", status);
		condition.put("billids", tmp.substring(2));
		return executeQueryForList("MovePack.checkBillStatusAvail2", condition);
	}
	/**
	 * 保存装箱单头
	 * @param moveHead
	 * @param userid
	 * @return
	 */
	public String saveMovePackHead(MovePackHead moveHead, String userid){
		moveHead.setCreateDate(DateUtil.getCurrentDate18());
		moveHead.setCreateId(userid);
		moveHead.setUpdateDate(DateUtil.getCurrentDate18());
		moveHead.setUpdateId(userid);
		moveHead.setCashFlag(DictConstant.YES_OR_NO_NO);//未结算
		return (String) executeInsert("MovePack.saveMovePackHead", moveHead);
	}
	/**
	 * 修改装箱单头
	 * @param moveHead
	 * @param userid
	 * @return
	 */
	public void updateMovePackHead(MovePackHead moveHead, String userid){
		moveHead.setUpdateDate(DateUtil.getCurrentDate18());
		moveHead.setUpdateId(userid);
		executeUpdate("MovePack.updateMovePackHead", moveHead);
	}
	/**
	 * 保存装箱单行
	 * @param newMoveIdList
	 * @param headid
	 * @param userid
	 */
	public void saveMovePackLine(List<MovePackLine> newLineList, String headid, String userid){
		List<MovePackLine> parameterList = new ArrayList<MovePackLine>();
		for(MovePackLine line : newLineList){
			line.setHeadid(headid);
			line.setCreateId(userid);
			line.setCreateDate(DateUtil.getCurrentDate18());
			line.setStatus(DictConstant.BILL_STATUS_SAVE);
			parameterList.add(line);
		}
		executeBatchInsert("MovePack.saveMovePackLine", parameterList);
	}
	/**
	 * 根据调拨单id删除装箱单行
	 * @param deleteMoveIdList
	 * @param headid
	 * @param userid
	 */
	public void deleteMovePackLineByBillId(List<String> deleteMoveIdList, String headid, String userid){
		List<Map<String, String>> parameterList = new ArrayList<Map<String,String>>();
		for(String moveId : deleteMoveIdList){
			Map<String, String> map = new HashMap<String, String>();
			map.put("billId", moveId);
			map.put("headid", headid);
//			map.put("userid", userid);
//			map.put("date", DateUtil.getCurrentDate18());
			parameterList.add(map);
		}
		executeBatchDelete("MovePack.deleteMovePackLineByBillId", parameterList);
	}
	/**
	 * 删除装箱单行
	 * @param headid
	 */
	public void deleteMovePackLine(String headid){
		executeUpdate("MovePack.deleteMovePackLine", headid);
	}
	/**
	 * 删除装箱单头
	 * @param headid
	 */
	public void deleteMovePackHead(String headid){
		executeUpdate("MovePack.deleteMovePackHead", headid);
	}
	/**
	 * 获取装箱单行
	 * @param headid
	 * @return
	 */
	public List<MovePackLine> getMovePackLine(String headid){
		return executeQueryForList("MovePack.getMovePackLine", headid);
	}
	/**
	 * 获取装箱单头
	 * @param headid
	 * @return
	 */
	public MovePackHead getMovePackHead(String headid){
		return (MovePackHead) executeQueryForObject("MovePack.getMovePackHead", headid);
	}
	@Override
	public void reviewSuccess(String billid, String userid) {
		updateMovePackStatus(billid, userid, DictConstant.BILL_STATUS_REVIEWED, true);
	}
	@Override
	public void reviewFail(String billid, String userid) {
		updateMovePackStatus(billid, userid, DictConstant.BILL_STATUS_SAVE, true);
	}
	private void updateMovePackStatus(String billid, String userid, String status, boolean reviewFlag) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", billid);
		condition.put("status", status);
		condition.put("userid", userid);
		condition.put("date", DateUtil.getCurrentDate18());
		condition.put("reviewFlag", reviewFlag?"1":"0");
		executeUpdate("MovePack.updateMovePackHeadStatus", condition);
	}
	/**
	 * 判断单据是否存在时，获取所在的装箱单单号
	 * @param billType
	 * @param headid
	 * @param billno
	 * @return
	 */
	public List<String> getPackNoByBillno(String billType, String jmFlag, String headid, String billno){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("billType", billType);
		condition.put("jmFlag", jmFlag);
		condition.put("billno", billno);
		condition.put("headid", headid);
		return executeQueryForList("MovePack.getPackNoByBillno", condition);
	}
	/**
	 * 接收装箱单
	 * @param headid
	 * @param userid
	 */
	public void receiveMovePack(String headid, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", headid);
		condition.put("status", DictConstant.BILL_STATUS_RECEIVED);
		condition.put("userid", userid);
		condition.put("date", DateUtil.getCurrentDate18());
		executeUpdate("MovePack.receiveMovePack", condition);
	}
	/**
	 * 维修件装箱单回写快递单号
	 * @param headid
	 * @param expressNo null表示清除
	 */
	public void updateFixBillPackNo(String headid, String planStatus, String expressNo){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", headid);
		condition.put("status", planStatus);
		condition.put("expressNo", expressNo);
		executeInsert("MovePack.updateFixBillPackNo", condition);
	}
	/**
	 * 修改可接收人
	 * @param headid
	 * @param receUserIds
	 */
	public void updateReceUserIds(String headid, String receUserIds, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", headid);
		condition.put("receUserIds", receUserIds);
		condition.put("userid", userid);
		condition.put("date", DateUtil.getCurrentDate18());
		executeInsert("MovePack.updateReceUserIds", condition);
	}
	/**
	 * 回写调拨单装箱单号
	 * @param packId
	 * @param billno
	 */
	public void updateMoveBillPackNo(String packId, String jmFlag, String packNo){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("packId", packId);
		condition.put("packNo", packNo);
		condition.put("jmFlag", jmFlag);
		executeUpdate("MovePack.updateMoveBillStatusAndPackNo", condition);
	}
	public void updateExpress(String headid, String expressCharge,
			String expressPremium, String expressMoney){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", headid);
		condition.put("expressCharge", expressCharge);
		condition.put("expressPremium", expressPremium);
		condition.put("expressMoney", expressMoney);
		executeUpdate("MovePack.updateExpress", condition);
	}
}
