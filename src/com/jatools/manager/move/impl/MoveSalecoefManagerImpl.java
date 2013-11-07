package com.jatools.manager.move.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.move.MoveSalecoefDao;
import com.jatools.manager.move.MoveSalecoefManager;
import com.jatools.vo.move.MoveSalecoefHead;
import com.jatools.vo.move.MoveSalecoefLine;
import com.jatools.web.util.StringUtil;

public class MoveSalecoefManagerImpl implements MoveSalecoefManager {
	private MoveSalecoefDao moveSalecoefDao;
	

	public void setMoveSalecoefDao(MoveSalecoefDao moveSalecoefDao) {
		this.moveSalecoefDao = moveSalecoefDao;
	}

	/**
	 * 获取调拨销价系数分页数据
	 * @param condition
	 * @return
	 */
	public Pager getMoveSaleCoefPageData(Map<String, String> condition){
		return  moveSalecoefDao.getMoveSaleCoefPageData(condition);
	}
	

	/**
	 * 保存或修改调拨销价系数
	 * @param head
	 * @param lineList
	 */
	public void saveOrUpdateMoveSalecoef(MoveSalecoefHead head, List<MoveSalecoefLine> lineList){
		String headid = head.getHeadid();
		if(StringUtil.isEmpty(headid)){
			headid = moveSalecoefDao.saveMoveSalecoefHead(head);
		}else{
			moveSalecoefDao.updateMoveSalecoefHead(head);
			moveSalecoefDao.deleteMoveSalecoefLineByHeadid(headid);
		}
		for(MoveSalecoefLine line : lineList){
			line.setHeadid(headid);
		}
		moveSalecoefDao.saveMoveSalecoefLineList(lineList);
	}
	/**
	 * 获取头表数据
	 * @param headid
	 * @return
	 */
	public MoveSalecoefHead getMoveSalecoefHead(String headid){
		return moveSalecoefDao.getMoveSalecoefHead(headid);
	}
	/**
	 * 获取行表数据
	 * @param headid
	 * @return
	 */
	public List<MoveSalecoefLine> getMoveSalecoefLineList(String headid){
		return moveSalecoefDao.getMoveSalecoefLineList(headid);
	}
	/**
	 * 删除调拨销价系数
	 * @param headidArr
	 * @return
	 */
	public void deleteMoveSalecoef(String[] headidArr){
		for(String headid : headidArr){			
			moveSalecoefDao.deleteMoveSalecoefLineByHeadid(headid);
			moveSalecoefDao.deleteMoveSalecoefHead(headid);
		}
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
		return moveSalecoefDao.checkRepeat(inOrgId, itemClassId, ornaClassId, styleItemIds, headid);
	}
	/**
	 * 复制数据
	 * @param fromOrgId
	 * @param toOrgIds
	 * @param sessionUserId
	 */
	public void copyData(String fromOrgId, String toOrgIds, String userid){
		moveSalecoefDao.deleteMoveSolecoefLineByOrgids(toOrgIds);
		moveSalecoefDao.deleteMoveSolecoefHeadByOrgids(toOrgIds);
		//利用临时表，生成临时数据
		moveSalecoefDao.createNewHeadidTempData(fromOrgId, toOrgIds);
		moveSalecoefDao.copyMoveSolecoefHead(fromOrgId, toOrgIds, userid);
		moveSalecoefDao.copyMoveSolecoefLine(fromOrgId, toOrgIds, userid);
	}
}
