package com.jatools.manager.common.impl;

import java.util.List;
import java.util.Map;
import com.jatools.dao.common.SelectEmpDao;
import com.jatools.manager.common.SelectEmpManager;
import com.jatools.vo.sys.User;

public class SelectEmpManagerImpl implements SelectEmpManager {
	private SelectEmpDao selectEmpDao;

	public void setSelectEmpDao(SelectEmpDao selectEmpDao) {
		this.selectEmpDao = selectEmpDao;
	}

	/**
	 * 根据组织id获取联邦快递人员
	 * @param orgId
	 * @return
	 */
	public List<Map<String, String>> getExpressReceusers(String orgId){
		return selectEmpDao.getExpressReceusers(orgId);
	}
	/**
	 * 根据orgId获取用户
	 * @param orgId
	 * @return
	 */
	public List<User> getEmpByOrgId(String orgId){
		return selectEmpDao.getEmpByOrgId(orgId);
	}
	public List<User> getUnarchivePurchaseEmp(){
		return selectEmpDao.getUnarchivePurchaseEmp();
	}
}
