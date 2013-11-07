package com.jatools.dao.sys.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.sys.ParameterDao;
import com.jatools.vo.sys.Parameter;

public class ParameterDaoImpl extends BaseDao implements ParameterDao {

	@Override
	public Pager getParameterData(Map<String, String> condition) {
		return this.executeQueryForPager("Parameter.getParameterPageData", "Parameter.getParameterTotalCount", condition);
	}

	@Override
	public Parameter getParameter(String name) {
		return (Parameter)this.executeQueryForObject("Parameter.getParameter", name);
	}

	@Override
	public void updateParameter(Parameter parameter) {
		this.executeUpdate("Parameter.updateParameter", parameter);

	}

	@Override
	public void deleteParameter(String name) {
		this.executeUpdate("Parameter.deleteParameter", name);
	}

}
