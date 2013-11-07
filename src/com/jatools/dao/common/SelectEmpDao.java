package com.jatools.dao.common;

import java.util.List;
import java.util.Map;

import com.jatools.vo.sys.User;

public interface SelectEmpDao {

	/**
	 * 根据组织获取快递人员
	 * @param orgId
	 * @param emptypeId
	 * @return
	 */
	public List<Map<String, String>> getExpressReceusers(String orgId);
	/**
	 * 根据组织id和人员类别获取人员
	 * @param orgId
	 * @param emptypeId
	 * @return
	 */
	public List<Map<String, String>> getEmpByOrgAndEmptype(String orgId, String emptypeId);

	/**
	 * 根据orgId获取用户
	 * @param orgId
	 * @return
	 */
	public List<User> getEmpByOrgId(String orgId);
	public List<User> getUnarchivePurchaseEmp();
}
