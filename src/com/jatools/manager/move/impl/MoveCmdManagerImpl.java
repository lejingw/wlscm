package com.jatools.manager.move.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.dao.move.MoveCmdDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.move.MoveCmdManager;
import com.jatools.vo.move.MoveCmdHead;
import com.jatools.vo.move.MoveCmdLine;
import com.jatools.web.util.StringUtil;
import com.jatools.ws.remote.WorkflowService;

public class MoveCmdManagerImpl extends BaseManager implements MoveCmdManager {
	private MoveCmdDao moveCmdDao;
	private CommonDao commonDao;
	private WorkflowService workflowService;
	
	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}
	public CommonDao getCommonDao() {
		return commonDao;
	}
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	public void setMoveCmdDao(MoveCmdDao MoveCmdDao) {
		this.moveCmdDao = MoveCmdDao;
	}
	/**
	 * 获取调拨指令单分页数据
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Pager getMoveCmdPageData(Map<String, String> condition, String orgId, String userid) {
		return moveCmdDao.getMoveCmdPageData(condition, orgId, userid);
	}
	
	public Pager getMoveCmdPageDataByMoveBill(Map<String, String> condition) {
		return moveCmdDao.getMoveCmdPageDataByMoveBill(condition);
	}
	/**
	 * 获取现有量表信息
	 * 
	 * @param code
	 * @param ornaFlag
	 * @return
	 */
	public MoveCmdLine getMaterActiveInfo(String code, boolean ornaFlag, String inOrgId) {
		return moveCmdDao.getMaterActiveInfo(code, ornaFlag, inOrgId);
	}
	/**
	 * 检查饰品状态是否有效
	 * @param ornaCodeList
	 * @return
	 */
	public List<String> checkOrnaStatusAvail(List<String> ornaCodeList) {
		if(null == ornaCodeList || ornaCodeList.size()<1)
			return null;
		return moveCmdDao.checkOrnaStatusAvail(ornaCodeList);
	}
	/**
	 * 保存或修改调拨指令单
	 * @param moveHead
	 * @param newOrnaCodeList 添加的行记录
	 * @param deleteLineidList 删除的行记录
	 * @return
	 */
	public void saveMoveCmd(MoveCmdHead moveHead, List<String> newOrnaCodeList, List<String> deleteOrnaCodeList, String userid) {
		String billCode = GlobalConstant.BILL_CODE_MOVE_CMD;
		String headid = moveHead.getHeadid();
		if (StringUtil.isEmpty(headid)) {
			moveHead.setBillno(commonDao.getBillno(billCode));
			headid = moveCmdDao.saveMoveCmdHead(moveHead, userid);
		} else {
			asertStatus("jat_move_cmd_head", "headid", headid, "status", DictConstant.BILL_STATUS_SAVE);
			moveCmdDao.updateMoveCmdHead(moveHead, userid);
			moveCmdDao.deleteMoveCmdLineByOrnaCode(deleteOrnaCodeList, headid, userid);
		}
		moveCmdDao.saveMoveCmdLine(newOrnaCodeList, headid, moveHead.getInOrgId(), userid);
		moveCmdDao.updateMoveCmdSumNum(headid, userid);
		String ornaCodes = this.moveCmdDao.getInvalidOrgOrnaCode(headid);
		if(StringUtil.isNotBlank(ornaCodes)){
			throw new RuntimeException("以下饰品编码已经不在调出组织下:[" + ornaCodes + "]");
		}
		ornaCodes = this.moveCmdDao.getInvalidStateOrnaCode(headid);
		if(StringUtil.isNotBlank(ornaCodes)){
			throw new RuntimeException("以下饰品编码非有效状态:[" + ornaCodes + "]");
		}
		if(DictConstant.BILL_STATUS_REVIEWING.equals(moveHead.getStatus())){
			workflowService.enterReview(moveHead, userid);
		}
	}
	
	/**
	 * 删除调拨指令单
	 * @param netIdList
	 * @return
	 */
	public void deleteMoveCmd(List<String> headidList) {
		if(null != headidList && headidList.size()>0){
			for(String headid : headidList){
				asertStatus("jat_move_cmd_head", "headid", headid, "status", DictConstant.BILL_STATUS_SAVE);
				moveCmdDao.deleteMoveCmdLine(headid);
				moveCmdDao.deleteMoveCmdHead(headid);
			}
		}
	}
	/**
	 * 获取调拨指令单行
	 * @param headid
	 * @return
	 */
	public List<MoveCmdLine> getMoveCmdLine(String headid) {
		return moveCmdDao.getMoveCmdLine(headid);
	}
	/**
	 * 获取调拨指令单头
	 * @param headid
	 * @return
	 */
	public MoveCmdHead getMoveCmdHead(String headid){
		return moveCmdDao.getMoveCmdHead(headid);
	}
	
	public void invalidMoveCmd(String headid, String userId){
		asertStatus("jat_move_cmd_head", "headid", headid, "status", DictConstant.BILL_STATUS_REVIEWED);
		this.moveCmdDao.modifyMaterValidByMoveCmd(headid);// 修改饰品状态为有效状态
		this.moveCmdDao.updateMoveCmdStatus(headid, userId, DictConstant.BILL_STATUS_DISCARD);
	}
}