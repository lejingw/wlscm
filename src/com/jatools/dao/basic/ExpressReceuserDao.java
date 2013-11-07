package com.jatools.dao.basic;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.basic.ExpressReceuser;

public interface ExpressReceuserDao {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getExpressReceuserPageData(Map<String, String> condition);

	/**
	 * 删除记录
	 * @param receId
	 */
	void deleteExpressReceuser(String receId);
	/**
	 * 根据组织获取快递人员
	 */
	List<ExpressReceuser> getExpressReceuserByOrgId(String orgId);

	/**
	 * 保存
	 * @param orgId
	 * @param addUserIds
	 */
	void saveExpressReceuser(String orgId, String[] addUserIds, String userid);
}
