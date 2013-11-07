package com.jatools.manager.basic.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.jatools.common.Pager;
import com.jatools.dao.basic.ExpressReceuserDao;
import com.jatools.manager.basic.ExpressReceuserManager;
import com.jatools.vo.basic.ExpressReceuser;
import com.opensymphony.oscache.util.StringUtil;

public class ExpressReceuserManagerImpl implements ExpressReceuserManager {
	private ExpressReceuserDao expressReceuserDao;
	public void setExpressReceuserDao(ExpressReceuserDao expressReceuserDao) {
		this.expressReceuserDao = expressReceuserDao;
	}

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	public Pager getExpressReceuserPageData(Map<String, String> condition){
		return expressReceuserDao.getExpressReceuserPageData(condition);
	}

	/**
	 * 删除记录
	 * @param receIds
	 */
	public void deleteExpressReceuser(String receIds){
		if(null == receIds || StringUtil.isEmpty(receIds)){
			return ;
		}
		for(String receId : receIds.split(",")){
			if(StringUtils.isNotEmpty(receId)){
				expressReceuserDao.deleteExpressReceuser(receId);
			}
		}
	}

	/**
	 * 根据组织获取快递人员
	 */
	public List<ExpressReceuser> getExpressReceuserByOrgId(String orgId){
		return expressReceuserDao.getExpressReceuserByOrgId(orgId);
	}

	/**
	 * 保存
	 * @param orgId
	 * @param addUserIds
	 * @param deleteReceIds
	 */
	public void saveExpressReceuser(String orgId, String[] addUserIds, String[] deleteReceIds, String userid){
		expressReceuserDao.saveExpressReceuser(orgId, addUserIds, userid);
		for(String receId : deleteReceIds){
			if(!StringUtil.isEmpty(receId)){
				expressReceuserDao.deleteExpressReceuser(receId);
			}
		}
	}
}
