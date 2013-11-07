package com.jatools.dao.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jatools.dao.BaseDao;
import com.jatools.dao.common.SelectEmpDao;
import com.jatools.vo.sys.User;

@SuppressWarnings("unchecked")
public class SelectEmpDaoImpl extends BaseDao implements SelectEmpDao {
	/**
	 * 根据组织获取快递人员
	 * @param orgId
	 * @param emptypeId
	 * @return
	 */
	public List<Map<String, String>> getExpressReceusers(String orgId){
		return executeQueryForList("SelectEmp.getExpressReceusers", orgId);
	}

	/**
	 * 根据组织id和人员类别获取人员
	 * @param orgId
	 * @param emptypeId
	 * @return
	 */
	public List<Map<String, String>> getEmpByOrgAndEmptype(String orgId, String emptypeId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("orgId", orgId);
		condition.put("emptypeId", emptypeId);
		return executeQueryForList("SelectEmp.getEmpByOrgAndEmptype", condition);
	}

	/**
	 * 根据orgId获取用户
	 * @param orgId
	 * @return
	 */
	public List<User> getEmpByOrgId(String orgId){
		return executeQueryForList("SelectEmp.getEmpByOrgId", orgId);
	}
	public List<User> getUnarchivePurchaseEmp(){
		return executeQueryForList("SelectEmp.getUnarchivePurchaseEmp", null);
	}
}
