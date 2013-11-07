package com.jatools.manager.common;

import java.util.List;
import java.util.Map;

import com.jatools.vo.sys.User;

public interface SelectEmpManager {

	/**
	 * 根据组织id获取联邦快递人员
	 * @param orgId
	 * @return
	 */
	public List<Map<String, String>> getExpressReceusers(String orgId);

	/**
	 * 根据orgId获取用户
	 * @param orgId
	 * @return
	 */
	public List<User> getEmpByOrgId(String orgId);

	public List<User> getUnarchivePurchaseEmp();

}
