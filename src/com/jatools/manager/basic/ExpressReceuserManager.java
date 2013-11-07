package com.jatools.manager.basic;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.basic.ExpressReceuser;

public interface ExpressReceuserManager {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getExpressReceuserPageData(Map<String, String> condition);

	/**
	 * 删除记录
	 * @param receIds
	 */
	void deleteExpressReceuser(String receIds);

	/**
	 * 根据组织获取快递人员
	 */
	List<ExpressReceuser> getExpressReceuserByOrgId(String orgId);

	/**
	 * 保存
	 * @param orgId
	 * @param addUserIds
	 * @param deleteReceIds
	 */
	void saveExpressReceuser(String orgId, String[] addUserIds, String[] deleteReceIds, String userid);

}
