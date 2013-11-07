package com.jatools.manager.move.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.dao.move.SpecialDiamDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.move.SpecialDiamManager;
import com.jatools.vo.move.SpecialDiam;
import com.jatools.web.util.DateUtil;
import com.opensymphony.oscache.util.StringUtil;

public class SpecialDiamManagerImpl extends BaseManager implements
		SpecialDiamManager {
	private CommonDao commonDao;
	private SpecialDiamDao specialDiamDao;

	public void setSpecialDiamDao(SpecialDiamDao specialDiamDao) {
		this.specialDiamDao = specialDiamDao;
	}


	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	@Override
	public CommonDao getCommonDao() {
		return commonDao;
	}

	/**
	 * 保存或修改
	 * 
	 * @param dn
	 * @param userId
	 */
	public void saveOrUpdateSpecialDiam(SpecialDiam dn, String userId) {
		dn.setUpdateDate(DateUtil.getCurrentDate18());
		dn.setUpdateId(userId);
		if (StringUtil.isEmpty(dn.getBillId())) {
			dn.setCreateDate(DateUtil.getCurrentDate18());
			dn.setCreateId(userId);
			dn.setStatus(DictConstant.BILL_STATUS_SAVE);
			specialDiamDao.saveSpecialDiam(dn);
		} else {
			specialDiamDao.updateSpecialDiam(dn);
		}
	}

	/**
	 * 检查记录是否存在
	 * 
	 * @param dn
	 * @return
	 */
	public List<SpecialDiam> checkSpecialDiamRepeat(SpecialDiam dn) {
		return specialDiamDao.checkSpecialDiamRepeat(dn);
	}

	/**
	 * 删除
	 * 
	 * @param billId
	 */
	public void deleteSpecialDiam(List<String> billIdList) {
		specialDiamDao.deleteSpecialDiam(billIdList);
	}

	public Pager getSpecialDiamPageData(Map<String, String> condition) {
		return specialDiamDao.getSpecialDiamPageData(condition);
	}
	public Pager getSpecialDiamOrgPageData(Map<String, String> condition) {
		return specialDiamDao.getSpecialDiamOrgPageData(condition);
	}
	/**
	 * 导入excel
	 */
	public void saveSpecialDiamFromExcel(String seqId, String userId){
		specialDiamDao.saveSpecialDiamFromExcel(seqId, userId);
	}
	public void saveOrUpdateSpecialDiamOrg(String orgId, String orgType, String userId){
		if(specialDiamDao.isExisteSpecialDiamOrg(orgId)){
			specialDiamDao.updateSpecialDiamOrg(orgId, orgType, userId);
		}else{
			specialDiamDao.saveSpecialDiamOrg(orgId, orgType, userId);
		}
	}
	public void deleteSpecialDiamOrg(String orgId){
		specialDiamDao.deleteSpecialDiamOrg(orgId);
	}
}
