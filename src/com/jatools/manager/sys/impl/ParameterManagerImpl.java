package com.jatools.manager.sys.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.sys.ParameterDao;
import com.jatools.manager.sys.ParameterManager;
import com.jatools.vo.sys.Parameter;
import com.jatools.web.cache.ParameterCache;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class ParameterManagerImpl implements ParameterManager {

	private ParameterDao parameterDao;
	
	public void setParameterDao(ParameterDao parameterDao) {
		this.parameterDao = parameterDao;
	}

	@Override
	public Pager getParameterData(Map<String, String> condition) {
		return this.parameterDao.getParameterData(condition);
	}

	@Override
	public Parameter getParameter(String name) {
		return this.parameterDao.getParameter(name);
	}

	@Override
	public void updateParameter(Parameter parameter, String userId) {
		if(parameter == null || StringUtil.isBlank(parameter.getName())){
			throw new RuntimeException("数据不合法");
		}
		parameter.setUpdateDate(DateUtil.getCurrentDate18());
		parameter.setUpdateId(userId);
		this.parameterDao.updateParameter(parameter);
		ParameterCache.refresh();// 刷新缓存
	}

	@Override
	public void deleteParameter(String name) {
		this.parameterDao.deleteParameter(name);
	}

}
