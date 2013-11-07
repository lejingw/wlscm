package com.jatools.manager.move.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.dao.move.DispatchWayDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.move.DispatchWayManager;
import com.jatools.vo.move.DispatchWay;
import com.jatools.web.util.DateUtil;
import com.opensymphony.oscache.util.StringUtil;

public class DispatchWayManagerImpl extends BaseManager implements
		DispatchWayManager {
	private CommonDao commonDao;
	private DispatchWayDao dispatchWayDao;

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public void setDispatchWayDao(DispatchWayDao dispatchWayDao) {
		this.dispatchWayDao = dispatchWayDao;
	}


	public Pager getDispatchStylePageData(Map<String, String> condition){
		return dispatchWayDao.getDispatchStylePageData(condition);
	}

	public Pager getDispatchPricePageData(Map<String, String> condition){
		return dispatchWayDao.getDispatchPricePageData(condition);
	}

	/**
	 * 保存或修改
	 */
	public void saveOrUpdateDispatchStyle(DispatchWay dn, String userId) {
		dn.setUpdateDate(DateUtil.getCurrentDate18());
		dn.setUpdateId(userId);
		if (StringUtil.isEmpty(dn.getBillId())) {
			dn.setCreateDate(DateUtil.getCurrentDate18());
			dn.setCreateId(userId);
			dn.setStatus(DictConstant.BILL_STATUS_SAVE);
			dispatchWayDao.saveDispatchStyle(dn);
		} else {
			dispatchWayDao.updateDispatchStyle(dn);
		}
	}

	/**
	 * 检查记录是否存在
	 */
	public List<DispatchWay> checkDispatchStyleRepeat(DispatchWay dn) {
		return dispatchWayDao.checkDispatchStyleRepeat(dn);
	}

	/**
	 * 删除
	 */
	public void deleteDispatchStyle(List<String> billIdList) {
		dispatchWayDao.deleteDispatchStyle(billIdList);
	}
	
	/**
	 * 导入excel
	 */
	public void saveDispatchStyleFromExcel(String seqId, String userId){
		dispatchWayDao.saveDispatchStyleFromExcel(seqId, userId);
	}
	//-----------------------------------------------
	/**
	 * 保存或修改
	 */
	public void saveOrUpdateDispatchPrice(DispatchWay dn, String userId) {
		dn.setUpdateDate(DateUtil.getCurrentDate18());
		dn.setUpdateId(userId);
		if (StringUtil.isEmpty(dn.getBillId())) {
			dn.setCreateDate(DateUtil.getCurrentDate18());
			dn.setCreateId(userId);
			dn.setStatus(DictConstant.BILL_STATUS_SAVE);
			dispatchWayDao.saveDispatchPrice(dn);
		} else {
			dispatchWayDao.updateDispatchPrice(dn);
		}
	}

	/**
	 * 检查记录是否存在
	 */
	public List<DispatchWay> checkDispatchPriceRepeat(DispatchWay dn) {
		return dispatchWayDao.checkDispatchPriceRepeat(dn);
	}

	/**
	 * 删除
	 */
	public void deleteDispatchPrice(List<String> billIdList) {
		dispatchWayDao.deleteDispatchPrice(billIdList);
	}
	
	/**
	 * 导入excel
	 */
	public void saveDispatchPriceFromExcel(String seqId, String userId){
		dispatchWayDao.saveDispatchPriceFromExcel(seqId, userId);
	}

    @Override
    public Pager getDispatchStyleReportPageData(Map<String, String> condition) {
        return this.dispatchWayDao.getDispatchStyleReportPageData(condition);
    }

    @Override
    public Pager getDispatchPriceReportPageData(Map<String, String> condition) {
        return this.dispatchWayDao.getDispatchPriceReportPageData(condition);
    }
}
