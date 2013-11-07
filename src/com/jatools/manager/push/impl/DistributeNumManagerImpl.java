package com.jatools.manager.push.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.dao.push.DistributeNumDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.push.DistributeNumManager;
import com.jatools.vo.push.DistributeNum;
import com.jatools.web.util.DateUtil;
import com.opensymphony.oscache.util.StringUtil;

public class DistributeNumManagerImpl extends BaseManager implements DistributeNumManager {
	private CommonDao commonDao;
	private DistributeNumDao distributeNumDao;

	public void setDistributeNumDao(DistributeNumDao distributeNumDao) {
		this.distributeNumDao = distributeNumDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	@Override
	public CommonDao getCommonDao() {
		return commonDao;
	}

	public Pager getDistributeNumPageData(Map<String, String> condition){
		return distributeNumDao.getDistributeNumPageData(condition);
	}
	
	/**
	 * 保存或修改
	 * 
	 * @param dn
	 * @param userId
	 */
	public void saveOrUpdateDistributeNum(DistributeNum dn, String userId) {
		dn.setUpdateDate(DateUtil.getCurrentDate18());
		dn.setUpdateId(userId);
		if (StringUtil.isEmpty(dn.getBillId())) {
			dn.setCreateDate(DateUtil.getCurrentDate18());
			dn.setCreateId(userId);
			dn.setStatus(DictConstant.BILL_STATUS_SAVE);
			distributeNumDao.saveDistributeNum(dn);
		}else{
			distributeNumDao.updateDistributeNum(dn);
		}
	}
	/**
	 * 检查记录是否存在
	 * @param dn
	 * @return
	 */
	public Integer checkDistributeNumRepeat(DistributeNum dn){
		return distributeNumDao.checkDistributeNumRepeat(dn);
	}
	/**
	 * 删除
	 * @param billId
	 */
	public void deleteDistributeNum(List<String> billIdList){
		distributeNumDao.deleteDistributeNum(billIdList);
	}
	/**
	 * 复制
	 */
	public void copyDistributeNum(String srcOrgId, String targetOrgIds, String userId){
		distributeNumDao.deleteDistributeNumByOrgs(targetOrgIds, userId);
		distributeNumDao.copyDistributeNum(srcOrgId, targetOrgIds, userId);
	}
	/**
	 * 导入excel
	 */
	public void saveDistributeNumFromExcel(String seqId, String userId){
		distributeNumDao.saveDistributeNumFromExcel(seqId, userId);
	}
}
