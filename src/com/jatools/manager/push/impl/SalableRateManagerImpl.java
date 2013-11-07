package com.jatools.manager.push.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.dao.push.SalableRateDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.push.SalableRateManager;
import com.jatools.vo.push.SalableRate;
import com.jatools.web.util.DateUtil;

public class SalableRateManagerImpl  extends BaseManager implements SalableRateManager{
	private SalableRateDao salableRateDao;
	private CommonDao commonDao;
	
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	@Override
	public CommonDao getCommonDao() {
		return commonDao;
	}
	
	public void setSalableRateDao(SalableRateDao salableRateDao) {
		this.salableRateDao = salableRateDao;
	}

	/**
	 * 获取分页数据
	 */
	public Pager getSalableRatePageData(Map<String, String> condition){
		return salableRateDao.getSalableRatePageData(condition);
	}
	
	/**
	 * 保存或修改
	 * 
	 * @param dn
	 * @param userId
	 */
	public void saveOrUpdateSalableRate(SalableRate dn, String userId) {
		dn.setUpdateDate(DateUtil.getCurrentDate18());
		dn.setUpdateId(userId);
		if (!salableRateDao.checkSalableRateExists(dn)) {
			dn.setCreateDate(DateUtil.getCurrentDate18());
			dn.setCreateId(userId);
			dn.setStatus(DictConstant.BILL_STATUS_SAVE);
			salableRateDao.saveSalableRate(dn);
		}else{
			salableRateDao.updateSalableRate(dn);
		}
	}
	/**
	 * 删除
	 * @param billId
	 */
	public void deleteSalableRate(List<String> billIdList){
		salableRateDao.deleteSalableRate(billIdList);
	}
}
