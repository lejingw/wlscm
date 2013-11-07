package com.jatools.dao.move.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.move.MoveSalecoefDao;
import com.jatools.vo.move.MoveSalecoefHead;
import com.jatools.vo.move.MoveSalecoefLine;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class MoveSalecoefDaoImpl extends BaseDao implements MoveSalecoefDao {
	/**
	 * 获取调拨销价系数分页数据
	 * @param condition
	 * @return
	 */
	public Pager getMoveSaleCoefPageData(Map<String, String> condition){
		return executeQueryForPager("MoveSalecoef.getPageData", "MoveSalecoef.getTotalCount", condition);
	}
	/**
	 * 保存调拨销价系数
	 * @param head
	 * @return
	 */
	public String saveMoveSalecoefHead(MoveSalecoefHead head){
		return (String) executeInsert("MoveSalecoef.saveMoveSalecoefHead", head);
	}

	/**
	 * 更新调拨销价系数
	 * @param head
	 */
	public void updateMoveSalecoefHead(MoveSalecoefHead head){
		executeUpdate("MoveSalecoef.updateMoveSalecoefHead", head);
	}
	

	/**
	 * 删除调拨销价系数行
	 * @param headid
	 */
	public void deleteMoveSalecoefLineByHeadid(String headid){
		executeUpdate("MoveSalecoef.deleteMoveSalecoefLineByHeadid", headid);
	}

	/**
	 * 保存调拨销价系数行
	 * @param headid
	 */
	public void saveMoveSalecoefLineList(List<MoveSalecoefLine> lineList){
		executeBatchInsert("MoveSalecoef.saveMoveSalecoefLineList", lineList);
	}
	/**
	 * 获取头表数据
	 * @param headid
	 * @return
	 */
	public MoveSalecoefHead getMoveSalecoefHead(String headid){
		return (MoveSalecoefHead) executeQueryForObject("MoveSalecoef.getMoveSalecoefHead", headid);
	}
	/**
	 * 获取行表数据
	 * @param headid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MoveSalecoefLine> getMoveSalecoefLineList(String headid){
		return (List<MoveSalecoefLine>) executeQueryForList("MoveSalecoef.getMoveSalecoefLineList", headid);
	}
	
	/**
	 * 删除调拨销价系数头
	 * @param headid
	 */
	public void deleteMoveSalecoefHead(String headid){
		executeUpdate("MoveSalecoef.deleteMoveSalecoefHead", headid);
	}
	/**
	 * 检查调入组织、大类、小类是否已经存在
	 * @param inOrgId
	 * @param itemClassId
	 * @param ornaClass
	 * @param headid
	 * @return
	 */
	public List<MoveSalecoefHead> checkRepeat(String inOrgId, String itemClassId, String ornaClassId, String styleItemIds, String headid){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("inOrgId", inOrgId);
		condition.put("itemClassId", itemClassId);
		condition.put("ornaClassId", ornaClassId);
		List<String> styleItemList = new ArrayList<String>();
		if(StringUtil.isNotEmpty(styleItemIds)){
			String[] tmpArr = styleItemIds.split(",");
			for(String tmp:tmpArr){
				styleItemList.add(tmp);
			}
		}
		condition.put("styleItemList", styleItemList);
		condition.put("headid", headid);
		return executeQueryForList("MoveSalecoef.checkRepeat", condition);
	}
	
	public void deleteMoveSolecoefHeadByOrgids(String toOrgIds){
		delete("MoveSalecoef.deleteMoveSolecoefHeadByOrgids", toOrgIds);
	}
	
	public void deleteMoveSolecoefLineByOrgids(String toOrgIds){
		delete("MoveSalecoef.deleteMoveSolecoefLineByOrgids", toOrgIds);
	}

	public  void createNewHeadidTempData(String fromOrgId, String toOrgIds){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("fromOrgId", fromOrgId);
		condition.put("toOrgIds", toOrgIds);
		executeInsert("MoveSalecoef.createNewHeadidTempData", condition);
	}
	public void copyMoveSolecoefHead(String fromOrgId, String toOrgIds, String userid){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("fromOrgId", fromOrgId);
		condition.put("toOrgIds", toOrgIds);
		condition.put("userid", userid);
		condition.put("date", DateUtil.getCurrentDate18());
		executeInsert("MoveSalecoef.copyMoveSolecoefHead", condition);
	}

	public  void copyMoveSolecoefLine(String fromOrgId, String toOrgIds, String userid){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("fromOrgId", fromOrgId);
		condition.put("toOrgIds", toOrgIds);
		condition.put("userid", userid);
		condition.put("date", DateUtil.getCurrentDate18());
		executeInsert("MoveSalecoef.copyMoveSolecoefLine", condition);
	}
}
