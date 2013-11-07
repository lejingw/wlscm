package com.jatools.manager.basic.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.basic.UnsaleOrnaDao;
import com.jatools.manager.basic.UnsaleOrnaManager;
import com.jatools.vo.basic.UnsaleOrna;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class UnsaleOrnaManagerImpl implements UnsaleOrnaManager {

	@Override
	public Pager getUnsaleOrnaPageData(Map<String, String> condition) {
		return this.unsaleOrnaDao.getUnsaleOrnaPageData(condition);
	}

	@Override
	public void saveOrUpdateUnsaleOrna(UnsaleOrna unsaleOrna, String userId) {
		int count = this.unsaleOrnaDao.isExists(unsaleOrna);
		if(count > 0){
			throw new RuntimeException("无法保存，同一个大类起止金额不允许有交叉。");
		}
		unsaleOrna.setUpdateDate(DateUtil.getCurrentDate18());
		unsaleOrna.setUpdateId(userId);
		if(StringUtil.isNotBlank(unsaleOrna.getUnsaleId())){
			this.unsaleOrnaDao.updateUnsaleOrna(unsaleOrna);
		} else {
			unsaleOrna.setCreateDate(DateUtil.getCurrentDate18());
			unsaleOrna.setCreateId(userId);
			this.unsaleOrnaDao.saveUnsaleOrna(unsaleOrna);
		}
	}

	@Override
	public UnsaleOrna getUnsaleOrna(String unsaleId) {
		return this.unsaleOrnaDao.getUnsaleOrna(unsaleId);
	}

	@Override
	public void deleteUnsaleOrna(String unsaleIds) {
		if(StringUtil.isNotBlank(unsaleIds)){
			String idArr[] = unsaleIds.split(";");
			for(String unsaleId : idArr){
				if(StringUtil.isNotBlank(unsaleId)){
					this.unsaleOrnaDao.deleteUnsaleOrna(unsaleId);
				}
			}
		}
	}
	
	private UnsaleOrnaDao unsaleOrnaDao;

	public void setUnsaleOrnaDao(UnsaleOrnaDao unsaleOrnaDao) {
		this.unsaleOrnaDao = unsaleOrnaDao;
	}
	
}
