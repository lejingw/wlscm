package com.jatools.dao.move.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.intf.ReviewActionIntf;
import com.jatools.dao.BaseDao;
import com.jatools.dao.move.MoveBillDao;
import com.jatools.vo.move.MoveBillHead;
import com.jatools.vo.move.MoveBillLine;
import com.jatools.vo.stock.MaterActive;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

@SuppressWarnings("unchecked")
public class MoveBillDaoImpl extends BaseDao implements MoveBillDao, ReviewActionIntf {

	/**
	 * 获取调拨单分页数据
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Pager getMoveBillPageData(Map<String, String> condition, String billType, String jmFlag, String orgId, String userid){
		condition.put("billType", billType);
		condition.put("jmFlag", jmFlag);
		condition.put("orgId", orgId);
		condition.put("userId", userid);
		return executeQueryForPager("MoveBill.getMoveBillPageData", "MoveBill.getMoveBillTotalCount", condition);
	}
	/**
	 * 获取现有量表信息
	 * @param code
	 * @param ornaFlag
	 * @return
	 */
	public MoveBillLine getMaterActiveInfo(String code, boolean ornaFlag, String billType, String jmFlag, String inOrgId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("code", code);
		condition.put("ornaFlag", ornaFlag?"1":"0");
		condition.put("billType", billType);
		condition.put("jmFlag", jmFlag);
		condition.put("inOrgId", inOrgId);
		MoveBillLine line = (MoveBillLine)executeQueryForObject("MoveBill.getMaterActiveInfo", condition);
		if(!(""+line.getPosAmount()).equals(""+line.getNewPosCost())){
			line.setPrintLabel(DictConstant.YES_OR_NO_YES);
		}
		return line;
	}
	/**
	 * 检查饰品状态是否有效
	 * @param ornaCodeList
	 * @return
	 */
	public List<String> checkOrnaStatusAvail(List<String> ornaCodeList){
		String tmp = listToStr(ornaCodeList);
		return executeQueryForList("MoveBill.checkOrnaStatusAvail", tmp.substring(2));
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
	 * 保存调拨单头
	 * @param moveHead
	 * @param userid
	 * @return
	 */
	public String saveMoveBillHead(MoveBillHead moveHead, String userid){
		moveHead.setCreateDate(DateUtil.getCurrentDate18());
		moveHead.setCreateId(userid);
		moveHead.setUpdateDate(DateUtil.getCurrentDate18());
		moveHead.setUpdateId(userid);
		return (String) executeInsert("MoveBill.saveMoveBillHead", moveHead);
	}
	/**
	 * 修改调拨单头
	 * @param moveHead
	 * @param userid
	 * @return
	 */
	public void updateMoveBillHead(MoveBillHead moveHead, String userid){
		moveHead.setUpdateDate(DateUtil.getCurrentDate18());
		moveHead.setUpdateId(userid);
		executeUpdate("MoveBill.updateMoveBillHead", moveHead);
	}
	/**
	 * 保存调拨单行
	 * @param newOrnaCodeList
	 * @param headid
	 * @param userid
	 */
	public void saveMoveBillLine(List<String> newOrnaCodeList, String headid, String billType, String jmFlag, String inOrgId, String userid){
		List<Map<String, String>> parameterList = new ArrayList<Map<String,String>>();
		for(String ornaCode : newOrnaCodeList){
			Map<String, String> map = new HashMap<String, String>();
			map.put("inOrgId", inOrgId);
			map.put("ornaCode", ornaCode);
			map.put("headid", headid);
			map.put("billType", billType);
			map.put("jmFlag", jmFlag);
			map.put("userid", userid);
			map.put("date", DateUtil.getCurrentDate18());
			//加盟退货单，需要获取 是否免费换货（以当前时间为准），免费换货剩余天数（以当前时间为准）、创建时间
			if(DictConstant.YES_OR_NO_YES.equals(jmFlag) && DictConstant.MOVE_BILL_TYPE_TUIHUODAN.equals(billType)){
				int freeLeftDays = getFreeReturnLeftDays(ornaCode);
				map.put("freeReturnFlag", freeLeftDays>=0?DictConstant.YES_OR_NO_YES:DictConstant.YES_OR_NO_NO);
				map.put("freeLeftDays", ""+freeLeftDays);
			}
			parameterList.add(map);
		}
		executeBatchInsert("MoveBill.saveMoveBillLine", parameterList);
	}
	/**
	 * 删除调拨单行
	 * @param newOrnaCodeList
	 * @param headid
	 * @param userid
	 */
	public void deleteMoveBillLineByOrnaCode(List<String> deleteOrnaCodeList, String headid, String userid){
		List<Map<String, String>> parameterList = new ArrayList<Map<String,String>>();
		for(String ornaCode : deleteOrnaCodeList){
			Map<String, String> map = new HashMap<String, String>();
			map.put("ornaCode", ornaCode);
			map.put("headid", headid);
			parameterList.add(map);
		}
		executeBatchDelete("MoveBill.deleteMoveBillLineByOrnaCode", parameterList);
	}
	/**
	 * 更新调拨单头合计数据
	 * @param headid
	 * @param userid
	 */
	public void updateMoveBillSumNum(String headid, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", headid);
		condition.put("userid", userid);
		condition.put("date", DateUtil.getCurrentDate18());
		executeUpdate("MoveBill.updateMoveBillSumNum", condition);
	}
	/**
	 * 删除调拨单行
	 * @param headid
	 */
	public void deleteMoveBillLine(String headid){
		executeUpdate("MoveBill.deleteMoveBillLine", headid);
	}
	/**
	 * 删除调拨单头
	 * @param headid
	 */
	public void deleteMoveBillHead(String headid){
		executeUpdate("MoveBill.deleteMoveBillHead", headid);
	}
	/**
	 * 获取调拨单行
	 * @param headid
	 * @return
	 */
	public List<MoveBillLine> getMoveBillLine(String headid, String printLabelType){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", headid);
		condition.put("printLabelType", printLabelType);
		return executeQueryForList("MoveBill.getMoveBillLine", condition);
	}
	/**
	 * 获取调拨单头
	 * @param headid
	 * @return
	 */
	public MoveBillHead getMoveBillHead(String headid){
		return (MoveBillHead) executeQueryForObject("MoveBill.getMoveBillHead", headid);
	}
	@Override
	public void reviewSuccess(String billid, String userid) {
		updateMoveBillStatusByReview(billid, userid, true);
	}
	@Override
	public void reviewFail(String billid, String userid) {
		updateMoveBillStatusByReview(billid, userid, false);
	}
	/**
	 * 修改调拨单状态
	 * @param headid
	 * @param userid
	 * @param status
	 */
	private void updateMoveBillStatusByReview(String headid, String userid, boolean successFlag) {
		MoveBillHead head = getMoveBillHead(headid);
		String status = null;
		String createDate = null;
		if (successFlag) {
			//加盟调拨单要生成加盟销售结算单，状态改为已生成结算单之后，才可以继续流程
			//加盟的、调拨类型、调出组织不是加盟店
			if (DictConstant.YES_OR_NO_YES.equals(head.getJmFlag()) && DictConstant.MOVE_BILL_TYPE_DIAOBODAN.equals(head.getBillType())
					&& !DictConstant.YES_OR_NO_YES.equals(OrgCache.getInstance().getOrgById(head.getOutOrgId()).getJmFlag())) {
				status = DictConstant.BILL_STATUS_NO_ESTIMATE;
			} else {
				status = DictConstant.BILL_STATUS_REVIEWED;
			}
			//当加盟退货单审批通过完成后，把退货单头表的创建时间更新为当前时间
			if(DictConstant.YES_OR_NO_YES.equals(head.getJmFlag()) && DictConstant.MOVE_BILL_TYPE_TUIHUODAN.equals(head.getBillType())){
				createDate = DateUtil.getCurrentDate18();
			}
		} else {
			status = DictConstant.BILL_STATUS_SAVE;
		}
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", headid);
		condition.put("status", status);
		condition.put("updateId", userid);
		condition.put("updateDate", DateUtil.getCurrentDate18());
		condition.put("createDate", createDate);
		executeUpdate("MoveBill.updateMoveBillStatusByReview", condition);
	}
	public void updateMoveBillStatus(String headid, String userid, String status){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", headid);
		condition.put("status", status);
		condition.put("userid", userid);
		condition.put("date", DateUtil.getCurrentDate18());
		executeUpdate("MoveBill.updateMoveBillHeadStatus", condition);
	}

	/**
	 * 开始接收饰品调拨单
	 * @param packHeadId
	 * @param userid
	 */
	public void receiveMoveBillByPackId(String packHeadId, String jmFlag, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("packHeadId", packHeadId);
		condition.put("status", DictConstant.BILL_STATUS_RECEIVING);
		condition.put("jmFlag", jmFlag);
		condition.put("userid", userid);
		condition.put("date", DateUtil.getCurrentDate18());
		executeUpdate("MoveBill.receiveMoveBillByPackId", condition);
	}
	/**
	 * 检查是否配置调拨网络
	 * @param outOrgId
	 * @param inOrgId
	 * @return
	 */
	public Long getMoveNetCount(String outOrgId, String inOrgId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("outOrgId", outOrgId);
		condition.put("inOrgId", inOrgId);
		return (Long)executeQueryForObject("MoveBill.getMoveNetCount", condition);
	}
	/**
	 * 移库单、柜组调拨单接收
	 * @param billid
	 * @param userid
	 */
	public void receiveMoveBillHead(String headid, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", headid);
		condition.put("status", DictConstant.BILL_STATUS_CLOSED);
		condition.put("userid", userid);
		condition.put("date", DateUtil.getCurrentDate18());
		executeUpdate("MoveBill.receiveMoveBillHead", condition);
	}
	/**
	 * 移库单、柜组调拨单接收
	 * @param billid
	 * @param userid
	 */
	public void receiveMoveBillLine(String headid, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", headid);
		condition.put("status", DictConstant.BILL_STATUS_RECEIVED);
		condition.put("userid", userid);
		condition.put("date", DateUtil.getCurrentDate18());
		executeUpdate("MoveBill.receiveMoveBillLine", condition);
	}
	/**
	 * 关闭调拨单
	 * @param headid
	 * @param userid
	 */
	public void closeMoveBill(String headid, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", headid);
		condition.put("userid", userid);
		condition.put("date", DateUtil.getCurrentDate18());
		executeUpdate("MoveBill.closeMoveBill", condition);
	}
	/**
	 * 来自订单配货的调拨，改配货饰品状态为已到店
	 * @param headid
	 * @param userid
	 */
	public void updateDispatchOrnaStatus(String headid, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", headid);
		condition.put("userid", userid);
		condition.put("date", DateUtil.getCurrentDate18());
		executeUpdate("MoveBill.updateDispatchOrnaStatus", condition);
	}
	/**
	 * 获取调拨统计数据
	 * @param headid
	 * @return
	 */
	public List<MoveBillLine> getMoveBillLineStatForPrint(String headid){
		return executeQueryForList("MoveBill.getMoveBillLineStatForPrint", headid);
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
		return (MaterActive) executeQueryForObject("MoveBill.getMaterActiveByOrnaCode", condition);
	}
	@Override
	public void updateNorececount(String headid, String lineid, String userId) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", headid);
		condition.put("lineid", lineid);
		condition.put("updateId", userId);
		condition.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("MoveBill.updateNorececount", condition);
	}

	/**
	 * 生成加盟销售结算单
	 * @param headid
	 */
	public String generateSaleEstimate(String headid, String userid){
		Map<String, String> params = new HashMap<String, String>();
		params.put("p_sourceid", headid);
		params.put("p_typeid", null);
		params.put("p_userid", userid);
		params.put("p_date", DateUtil.getCurrentDate18());
		params.put("p_result", null);
		executeInsert("MoveBill.generateSaleEstimate", params);
		return params.get("p_result");
	}
	/**
	 * 生成加盟退货结算单
	 * @param headid
	 */
	public String generateReturnEstimate(String headid, String billType, String userid){
		Map<String, String> params = new HashMap<String, String>();
		params.put("p_sourceid", headid);
		//来源单据类型1退货单2退残单
		params.put("p_typeid", DictConstant.MOVE_BILL_TYPE_TUIHUODAN.equals(billType)?"1":(DictConstant.MOVE_BILL_TYPE_TUICANDAN.equals(billType)?"2":null));
		params.put("p_userid", userid);
		params.put("p_date", DateUtil.getCurrentDate18());
		params.put("p_result", null);
		executeInsert("MoveBill.generateReturnEstimate", params);
		return params.get("p_result");
	}
	/**
	 * 生成红字加盟销售结算单
	 * @param headid
	 * @param userid
	 */
	public String backRedSaleEstimate(String headid, String userid){
		Map<String, String> params = new HashMap<String, String>();
		params.put("p_sourceid", headid);
		params.put("p_typeid", null);
		params.put("p_userid", userid);
		params.put("p_date", DateUtil.getCurrentDate18());
		params.put("p_result", null);
		executeInsert("MoveBill.backRedSaleEstimate", params);
		return params.get("p_result");
	}

	public int getFreeReturnLeftDays(String ornaCode){
		Integer leftDays = (Integer)executeQueryForObject("MoveBill.getFreeReturnLeftDays", ornaCode);
		if(null == leftDays){
			throw new RuntimeException("加盟系统没有维护，饰品[" + ornaCode + "]对应的退货大类批次时间");
		}
		return leftDays;
	}
}