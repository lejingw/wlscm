package com.jatools.dao.move.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.intf.ReviewActionIntf;
import com.jatools.dao.BaseDao;
import com.jatools.dao.move.MoveCmdDao;
import com.jatools.vo.move.MoveCmdHead;
import com.jatools.vo.move.MoveCmdLine;
import com.jatools.vo.stock.MaterActive;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;


@SuppressWarnings({"unchecked", "rawtypes"})
public class MoveCmdDaoImpl extends BaseDao implements MoveCmdDao, ReviewActionIntf {

	/**
	 * 获取调拨指令单分页数据
	 * @param condition
	 * @return
	 */
	public Pager getMoveCmdPageData(Map<String, String> condition, String orgId, String userid){
		condition.put("orgId", orgId);
		condition.put("userId", userid);
		return executeQueryForPager("MoveCmd.getMoveCmdPageData", "MoveCmd.getMoveCmdTotalCount", condition);
	}
	
	public Pager getMoveCmdPageDataByMoveBill(Map<String, String> condition){
		return executeQueryForPager("MoveCmd.getMoveCmdPageDataByMoveBill", "MoveCmd.getMoveCmdTotalCountByMoveBill", condition);
	}
	
	/**
	 * 获取现有量表信息
	 * @param code
	 * @param ornaFlag
	 * @return
	 */
	public MoveCmdLine getMaterActiveInfo(String code, boolean ornaFlag, String inOrgId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("code", code);
		condition.put("ornaFlag", ornaFlag?"1":"0");
		condition.put("inOrgId", inOrgId);
		return (MoveCmdLine)executeQueryForObject("MoveCmd.getMaterActiveInfo", condition);
	}
	/**
	 * 检查饰品状态是否有效
	 * @param ornaCodeList
	 * @return
	 */
	public List<String> checkOrnaStatusAvail(List<String> ornaCodeList){
		String tmp = listToStr(ornaCodeList);
		return executeQueryForList("MoveCmd.checkOrnaStatusAvail", tmp.substring(2));
	}
	
	private String listToStr(List<String> ornaCodeList) {
		String tmp = "$";
		for(String str :ornaCodeList){
			tmp += ",'" + str + "'";
		}
		if("$".equals(tmp)){
			return null;
		}
		return tmp;
	}
	/**
	 * 保存调拨指令单头
	 * @param moveHead
	 * @param userid
	 * @return
	 */
	public String saveMoveCmdHead(MoveCmdHead moveHead, String userid){
		moveHead.setCreateDate(DateUtil.getCurrentDate18());
		moveHead.setCreateId(userid);
		moveHead.setUpdateDate(DateUtil.getCurrentDate18());
		moveHead.setUpdateId(userid);
		executeInsert("MoveCmd.saveMoveCmdHead", moveHead);
		return moveHead.getHeadid();
	}
	/**
	 * 修改调拨指令单头
	 * @param moveHead
	 * @param userid
	 * @return
	 */
	public void updateMoveCmdHead(MoveCmdHead moveHead, String userid){
		moveHead.setUpdateDate(DateUtil.getCurrentDate18());
		moveHead.setUpdateId(userid);
		executeUpdate("MoveCmd.updateMoveCmdHead", moveHead);
	}
	/**
	 * 保存调拨指令单行
	 * @param newOrnaCodeList
	 * @param headid
	 * @param userid
	 */
	public void saveMoveCmdLine(List<String> newOrnaCodeList, String headid, String inOrgId, String userid){
		List<Map<String, String>> parameterList = new ArrayList<Map<String,String>>();
		for(String ornaCode : newOrnaCodeList){
			Map<String, String> map = new HashMap<String, String>();
			map.put("inOrgId", inOrgId);
			map.put("ornaCode", ornaCode);
			map.put("headid", headid);
			map.put("userid", userid);
			map.put("date", DateUtil.getCurrentDate18());
			parameterList.add(map);
		}
		executeBatchInsert("MoveCmd.saveMoveCmdLine", parameterList);
	}
	/**
	 * 删除调拨指令单行
	 * @param newOrnaCodeList
	 * @param headid
	 * @param userid
	 */
	public void deleteMoveCmdLineByOrnaCode(List<String> deleteOrnaCodeList, String headid, String userid){
		List<Map<String, String>> parameterList = new ArrayList<Map<String,String>>();
		for(String ornaCode : deleteOrnaCodeList){
			Map<String, String> map = new HashMap<String, String>();
			map.put("ornaCode", ornaCode);
			map.put("headid", headid);
			parameterList.add(map);
		}
		executeBatchDelete("MoveCmd.deleteMoveCmdLineByOrnaCode", parameterList);
	}
	/**
	 * 更新调拨指令单头合计数据
	 * @param headid
	 * @param userid
	 */
	public void updateMoveCmdSumNum(String headid, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", headid);
		condition.put("userid", userid);
		condition.put("date", DateUtil.getCurrentDate18());
		executeUpdate("MoveCmd.updateMoveCmdSumNum", condition);
	}
	/**
	 * 删除调拨指令单行
	 * @param headid
	 */
	public void deleteMoveCmdLine(String headid){
		executeUpdate("MoveCmd.deleteMoveCmdLine", headid);
	}
	/**
	 * 删除调拨指令单头
	 * @param headid
	 */
	public void deleteMoveCmdHead(String headid){
		executeUpdate("MoveCmd.deleteMoveCmdHead", headid);
	}
	/**
	 * 获取调拨指令单行
	 * @param headid
	 * @return
	 */
	public List<MoveCmdLine> getMoveCmdLine(String headid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", headid);
		return executeQueryForList("MoveCmd.getMoveCmdLine", condition);
	}
	/**
	 * 获取调拨指令单头
	 * @param headid
	 * @return
	 */
	public MoveCmdHead getMoveCmdHead(String headid){
		return (MoveCmdHead) executeQueryForObject("MoveCmd.getMoveCmdHead", headid);
	}
	@Override
	public void reviewSuccess(String billid, String userid) {
		String ornaCodes = this.getInvalidOrgOrnaCode(billid);
		if(StringUtil.isNotBlank(ornaCodes)){
			throw new RuntimeException("以下饰品编码已经不在调出组织下:[" + ornaCodes + "]");
		}
		ornaCodes = this.getInvalidStateOrnaCode(billid);
		if(StringUtil.isNotBlank(ornaCodes)){
			throw new RuntimeException("以下饰品编码非有效状态:[" + ornaCodes + "]");
		}
		this.modifyMaterInvalidByMoveCmd(billid);// 修改饰品状态为保留
		updateMoveCmdStatus(billid, userid, DictConstant.BILL_STATUS_REVIEWED);
	}
	@Override
	public void reviewFail(String billid, String userid) {
		updateMoveCmdStatus(billid, userid, DictConstant.BILL_STATUS_SAVE);
	}
	
	public void updateMoveCmdStatus(String headid, String userid, String status){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", headid);
		condition.put("status", status);
		condition.put("userid", userid);
		condition.put("date", DateUtil.getCurrentDate18());
		executeUpdate("MoveCmd.updateMoveCmdHeadStatus", condition);
	}
	
	/**
	 * 关闭调拨指令单
	 * @param headid
	 * @param userid
	 */
	public void closeMoveCmd(String headid, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", headid);
		condition.put("userid", userid);
		condition.put("date", DateUtil.getCurrentDate18());
		executeUpdate("MoveCmd.closeMoveCmd", condition);
	}
	
	/**
	 * 获取饰品信息打印标签
	 * @param headid
	 * @param ornaCode
	 * @return
	 */
	public MaterActive getMaterActiveByOrnaCode(String headid, String ornaCode){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", headid);
		condition.put("ornaCode", ornaCode);
		condition.put("separator", "$");
		return (MaterActive) executeQueryForObject("MoveCmd.getMaterActiveByOrnaCode", condition);
	}
	
	public String getInvalidOrgOrnaCode(String headid){
		return (String)executeQueryForObject("MoveCmd.getInvalidOrgOrnaCode", headid);
	}
	
	public String getInvalidStateOrnaCode(String headid){
		return (String)executeQueryForObject("MoveCmd.getInvalidStateOrnaCode", headid);
	}
	
	/**
	 * 修改饰品为 有效状态
	 * @param headid
	 */
	public void modifyMaterValidByMoveCmd(String headid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", headid);
		condition.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("MoveCmd.modifyMaterValidByMoveCmd", condition);
	}
	
	/**
	 * 修改饰品为 保留状态
	 * @param headid
	 */
	private void modifyMaterInvalidByMoveCmd(String headid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", headid);
		condition.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("MoveCmd.modifyMaterInvalidByMoveCmd", condition);
	}
}