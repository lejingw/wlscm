package com.jatools.manager.push.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.dao.bd.BdCommonDao;
import com.jatools.dao.push.WearSizeDao;
import com.jatools.manager.push.WearSizeManager;
import com.jatools.vo.bd.Size;
import com.jatools.vo.push.WearSize;
import com.jatools.web.util.DateUtil;
import com.opensymphony.oscache.util.StringUtil;

public class WearSizeManagerImpl implements WearSizeManager {
	private WearSizeDao wearSizeDao;

	public void setWearSizeDao(WearSizeDao wearSizeDao) {
		this.wearSizeDao = wearSizeDao;
	}
	private BdCommonDao bdCommonDao;

	public void setBdCommonDao(BdCommonDao bdCommonDao) {
		this.bdCommonDao = bdCommonDao;
	}

	public Pager getWearSizePageData(Map<String, String> condition){
		return wearSizeDao.getWearSizePageData(condition);
	}
	
	/**
	 * 保存或修改
	 * 
	 * @param dn
	 * @param userId
	 */
	public void saveOrUpdateWearSize(WearSize dn, String userId) {
		dn.setUpdateDate(DateUtil.getCurrentDate18());
		dn.setUpdateId(userId);
		if (StringUtil.isEmpty(dn.getBillId())) {
			dn.setCreateDate(DateUtil.getCurrentDate18());
			dn.setCreateId(userId);
			dn.setStatus(DictConstant.BILL_STATUS_SAVE);
			wearSizeDao.saveWearSize(dn);
		}else{
			wearSizeDao.updateWearSize(dn);
		}
	}
	/**
	 * 检查记录是否存在
	 * @param dn
	 * @return
	 */
	public List<WearSize> checkWearSizeRepeat(WearSize dn){
		return wearSizeDao.checkWearSizeRepeat(dn);
	}
	/**
	 * 删除
	 * @param billId
	 */
	public void deleteWearSize(List<String> billIdList){
		wearSizeDao.deleteWearSize(billIdList);
	}
	/**
	 * 根据大类小类获取尺寸
	 * @param itemId
	 * @param ornaId
	 * @return
	 */
	public List<Size> getSizeByItemIdAndOrnaId(String itemId, String ornaId){
		return bdCommonDao.getSizeByItemIdAndOrnaId(itemId, ornaId);
	}

}
