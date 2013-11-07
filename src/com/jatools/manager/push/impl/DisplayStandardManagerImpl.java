package com.jatools.manager.push.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.dao.push.DisplayStandardDao;
import com.jatools.manager.push.DisplayStandardManager;
import com.jatools.vo.bd.Org;
import com.jatools.vo.push.DisplayOrgtypeHead;
import com.jatools.vo.push.DisplayOrgtypeLine;
import com.jatools.vo.push.DisplayStandard;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class DisplayStandardManagerImpl implements DisplayStandardManager {
	private DisplayStandardDao displayStandardDao;

	public void setDisplayStandardDao(DisplayStandardDao displayStandardDao) {
		this.displayStandardDao = displayStandardDao;
	}

	public List<Map> getAllDisplayStandard(){
		return displayStandardDao.getAllDisplayStandard();
	}
	public List<Map> getAllDisplayOrgtype(){
		return displayStandardDao.getAllDisplayOrgtype();
	}
	
	/**
	 * 保存或修改
	 * 
	 * @param head
	 * @param userId
	 */
	public void saveOrUpdateDisplayStandard(String headid,
			List<DisplayStandard> addList, List<DisplayStandard> updateList,
			List<String> deleteIdList, String userId) {
		for(DisplayStandard stand:addList) {
			stand.setHeadid(headid);
			stand.setCreateDate(DateUtil.getCurrentDate18());
			stand.setCreateId(userId);
			stand.setUpdateDate(DateUtil.getCurrentDate18());
			stand.setUpdateId(userId);
			stand.setStatus(DictConstant.BILL_STATUS_SAVE);
			displayStandardDao.saveDisplayStandard(stand);
		}
		for(DisplayStandard stand:updateList) {
			stand.setUpdateDate(DateUtil.getCurrentDate18());
			stand.setUpdateId(userId);
			displayStandardDao.updateDisplayStandard(stand);
		}
		displayStandardDao.deleteDisplayStandard(deleteIdList);
	}
	/**
	 * 保存或修改
	 * 
	 * @param head
	 * @param userId
	 */
	public void saveOrUpdateDisplayStandard2(String headid,
			List<DisplayStandard> addList, List<DisplayStandard> updateList,
			List<String> deleteIdList, String userId) {
		for(DisplayStandard stand:addList) {
			stand.setHeadid(headid);
			stand.setCreateDate(DateUtil.getCurrentDate18());
			stand.setCreateId(userId);
			stand.setUpdateDate(DateUtil.getCurrentDate18());
			stand.setUpdateId(userId);
			stand.setStatus(DictConstant.BILL_STATUS_SAVE);
			displayStandardDao.saveDisplayStandard2(stand);
		}
		for(DisplayStandard stand:updateList) {
			stand.setUpdateDate(DateUtil.getCurrentDate18());
			stand.setUpdateId(userId);
			displayStandardDao.updateDisplayStandard2(stand);
		}
		displayStandardDao.deleteDisplayStandard2(deleteIdList);
	}
	public void deleteDisplayStandardList2(String lineid){
		List<String> deleteIdList = new ArrayList<String>();
		deleteIdList.add(lineid);
		displayStandardDao.deleteDisplayStandard2(deleteIdList);
	}
	public Pager getDisplayOrgTypePageData(Map<String, String> condition){
		return displayStandardDao.getDisplayOrgTypePageData(condition);
	}

	/**
	 * 获取推式陈列标准组织
	 * @param headid
	 * @return
	 */
	public List<Org> getDisplayOrg(String headid){
		return displayStandardDao.getDisplayOrg(headid);
	}

	public List<DisplayOrgtypeLine> getDisplayOrgByHeadid(String headid){
		return displayStandardDao.getDisplayOrgByHeadid(headid);
	}

	public void saveOrUpdateDisplayOrgtype(DisplayOrgtypeHead head, List<DisplayOrgtypeLine> addLineList, List<String> deleteIdList, String userId){
		head.setUpdateDate(DateUtil.getCurrentDate18());
		head.setUpdateId(userId);
		String headid = head.getHeadid();
		if(StringUtil.isNotEmpty(headid)){
			displayStandardDao.updateDisplayOrgtype(head);
			if(null != deleteIdList && deleteIdList.size()>0){
				for(String lineid:deleteIdList){
					displayStandardDao.deleteDisplayOrgtypeLine(lineid);
				}
			}
		}else{
			head.setCreateDate(DateUtil.getCurrentDate18());
			head.setCreateId(userId);
			head.setStatus(DictConstant.BILL_STATUS_SAVE);
			headid = displayStandardDao.saveDisplayOrgtype(head);
		}
		if(null != addLineList && addLineList.size()>0){
			for(DisplayOrgtypeLine line:addLineList){
				List<String> headList = displayStandardDao.checkOrgAvail(headid, line.getOrgId());
				if(headList.size()>0){
					throw new RuntimeException("组织["+OrgCache.getInstance().getNameById(line.getOrgId())+"]已经在以下组织类别中存在:<br>" + headList.toString());
				}
				line.setHeadid(headid);
				line.setCreateDate(DateUtil.getCurrentDate18());
				line.setCreateId(userId);
				line.setStatus(DictConstant.BILL_STATUS_SAVE);
				displayStandardDao.saveDisplayOrgtypeLine(line);
			}
		}
	}

	/**
	 * 获取选择款式分页数据
	 */
	public Pager getSelectStylePageData(String headid, String itemClassId, String ornaClassId,
			String analysisId, String styleItemClassId, String start,
			String limit){
		return displayStandardDao.getSelectStylePageData(headid, itemClassId,
				ornaClassId, analysisId, styleItemClassId, start, limit);
	}
	/**
	 * 畅销款
	 */
	public List<DisplayStandard> getDisplayStandardList(String headid){
		return displayStandardDao.getDisplayStandardList(headid);
	}
	/**
	 * 非畅销款
	 */
	public List<DisplayStandard> getDisplayStandardList2(String headid){
			return displayStandardDao.getDisplayStandardList2(headid);
	}

	public List<Map<String, String>> getRegionOrgs(){
		return displayStandardDao.getRegionOrgs();
	}
	/**
	 * 删除组织类别
	 * @param billIdList
	 */
	public void deleteDisplayOrgtype(List<String> billIdList){
		for(String billId: billIdList){
			displayStandardDao.deleteDisplayStandardByHeadid(billId);
			displayStandardDao.deleteDisplayOrgtypeLineByHeadid(billId);
			displayStandardDao.deleteDisplayOrgtype(billId);
		}
	}
}
