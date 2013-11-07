package com.jatools.manager.push.impl;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.dao.push.SalableDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.push.SalableManager;
import com.jatools.vo.push.Salable;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

import java.util.List;
import java.util.Map;

public class SalableManagerImpl extends BaseManager implements SalableManager{

	/**
	 * 获取分页数据
	 */
	public Pager getSalablePageData(Map<String, String> condition){
		return salableDao.getSalablePageData(condition);
	}
	
	/**
	 * 保存或修改
	 * @param dn
	 * @param userId
	 */
	public void saveOrUpdateSalable(Salable dn, String userId) {
        if(this.salableDao.checkSalableExists(dn)) {
            throw new RuntimeException("畅销款已经存在");
        }
		dn.setUpdateDate(DateUtil.getCurrentDate18());
		dn.setUpdateId(userId);
		if (StringUtil.isBlank(dn.getId())) {
			dn.setCreateDate(DateUtil.getCurrentDate18());
			dn.setCreateId(userId);
			dn.setStatus(DictConstant.BILL_STATUS_SAVE);
			salableDao.saveSalable(dn);
		}else{
			salableDao.updateSalable(dn);
		}
	}
	/**
	 * 删除
	 * @param billIdList
	 */
	public void deleteSalable(List<String> billIdList){
		salableDao.deleteSalable(billIdList);
	}

    @Override
    public Salable getSalableById(String id) {
        return this.salableDao.getSalableById(id);
    }

    private SalableDao salableDao;
    private CommonDao commonDao;

    public void setCommonDao(CommonDao commonDao) {
        this.commonDao = commonDao;
    }

    @Override
    public CommonDao getCommonDao() {
        return commonDao;
    }

    public void setSalableDao(SalableDao SalableDao) {
        this.salableDao = SalableDao;
    }
}
