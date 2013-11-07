package com.jatools.manager.push.impl;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.dao.push.PushStyleDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.push.PushStyleManager;
import com.jatools.vo.push.PushStyle;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

import java.util.List;
import java.util.Map;

public class PushStyleManagerImpl extends BaseManager implements PushStyleManager{

	/**
	 * 获取分页数据
	 */
	public Pager getPushStylePageData(Map<String, String> condition){
		return pushStyleDao.getPushStylePageData(condition);
	}
	
	/**
	 * 保存或修改
	 * @param dn
	 * @param userId
	 */
	public void saveOrUpdatePushStyle(PushStyle dn, String userId) {
        if(this.pushStyleDao.checkPushStyleExists(dn)) {
            throw new RuntimeException("款式已经存在");
        }
		dn.setUpdateDate(DateUtil.getCurrentDate18());
		dn.setUpdateId(userId);
		if (StringUtil.isBlank(dn.getId())) {
			dn.setCreateDate(DateUtil.getCurrentDate18());
			dn.setCreateId(userId);
			dn.setStatus(DictConstant.BILL_STATUS_SAVE);
			pushStyleDao.savePushStyle(dn);
		}else{
			pushStyleDao.updatePushStyle(dn);
		}
	}
	/**
	 * 删除
	 * @param billIdList
	 */
	public void deletePushStyle(List<String> billIdList){
		pushStyleDao.deletePushStyle(billIdList);
	}

    @Override
    public PushStyle getPushStyleById(String id) {
        return this.pushStyleDao.getPushStyleById(id);
    }

    private PushStyleDao pushStyleDao;
    private CommonDao commonDao;

    public void setCommonDao(CommonDao commonDao) {
        this.commonDao = commonDao;
    }

    @Override
    public CommonDao getCommonDao() {
        return commonDao;
    }

    public void setPushStyleDao(PushStyleDao PushStyleDao) {
        this.pushStyleDao = PushStyleDao;
    }
}
