package com.jatools.dao.sys;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.sys.Parameter;

public interface ParameterDao {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getParameterData(Map<String, String> condition);
	
	Parameter getParameter(String name);

	void updateParameter(Parameter parameter);
	
	void deleteParameter(String name);
}
