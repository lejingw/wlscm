package com.jatools.manager.push.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.dao.bd.BdCommonDao;
import com.jatools.dao.push.PushSizeDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.push.PushSizeManager;
import com.jatools.vo.bd.Size;
import com.jatools.vo.push.PushSize;
import com.jatools.web.util.DateUtil;
import com.opensymphony.oscache.util.StringUtil;

public class PushSizeManagerImpl extends BaseManager implements PushSizeManager {
	private CommonDao commonDao;
	private PushSizeDao pushSizeDao;
	private BdCommonDao bdCommonDao;

	public void setBdCommonDao(BdCommonDao bdCommonDao) {
		this.bdCommonDao = bdCommonDao;
	}

	public void setPushSizeDao(PushSizeDao pushSizeDao) {
		this.pushSizeDao = pushSizeDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	@Override
	public CommonDao getCommonDao() {
		return commonDao;
	}

	public Pager getPushSizePageData(Map<String, String> condition){
		return pushSizeDao.getPushSizePageData(condition);
	}
	
	/**
	 * 保存或修改
	 * 
	 * @param dn
	 * @param userId
	 */
	public void saveOrUpdatePushSize(PushSize dn, String userId) {
		dn.setUpdateDate(DateUtil.getCurrentDate18());
		dn.setUpdateId(userId);
		if (StringUtil.isEmpty(dn.getBillId())) {
			dn.setCreateDate(DateUtil.getCurrentDate18());
			dn.setCreateId(userId);
			dn.setStatus(DictConstant.BILL_STATUS_SAVE);
			pushSizeDao.savePushSize(dn);
		}else{
			pushSizeDao.updatePushSize(dn);
		}
	}
	/**
	 * 检查记录是否存在
	 * @param dn
	 * @return
	 */
	public List<PushSize> checkPushSizeRepeat(PushSize dn){
		return pushSizeDao.checkPushSizeRepeat(dn);
	}
	/**
	 * 删除
	 * @param billId
	 */
	public void deletePushSize(List<String> billIdList){
		pushSizeDao.deletePushSize(billIdList);
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
