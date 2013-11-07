package com.jatools.manager.basic.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.basic.OrnaDescDao;
import com.jatools.manager.basic.OrnaDescManager;
import com.jatools.vo.basic.OrnaDesc;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class OrnaDescManagerImpl implements OrnaDescManager {

	private OrnaDescDao ornaDescDao;
	
	public void setOrnaDescDao(OrnaDescDao ornaDescDao) {
		this.ornaDescDao = ornaDescDao;
	}

	@Override
	public Pager getOrnaDescPageData(Map<String, String> condition) {
		return this.ornaDescDao.getOrnaDescPageData(condition);
	}


	@Override
	public OrnaDesc getOrnaDesc(String ornadescId) {
		return this.ornaDescDao.getOrnaDesc(ornadescId);
	}


	@Override
	public void deleteOrnaDesc(String ornadescIds) {
		if(StringUtil.isNotBlank(ornadescIds)){
			String ornadescIdArr[] = ornadescIds.split(";");
			for(String ornadescId : ornadescIdArr){
				if(StringUtil.isNotBlank(ornadescId)){
					this.ornaDescDao.deleteOrnaDesc(ornadescId);
				}
			}
		}
	}

	@Override
	public void saveOrUpdateOrnaDesc(OrnaDesc ornaDesc, String userId) {
		int count = this.ornaDescDao.selectOrnaDescCount(ornaDesc);
		if(count >0){
			throw new RuntimeException("保存失败，商品类别-大类-小类-材质 已经存在");
		}
		ornaDesc.setUpdatedate(DateUtil.getCurrentDate18());
		ornaDesc.setUpdateid(userId);
		if(StringUtil.isNotBlank(ornaDesc.getOrnadscId())){
			this.ornaDescDao.updateOrnaDesc(ornaDesc);
		} else {
			ornaDesc.setCreatedate(DateUtil.getCurrentDate18());
			ornaDesc.setCreateid(userId);
			this.ornaDescDao.saveOrnaDesc(ornaDesc);
		}
	}

}
