package com.jatools.dao.basic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.basic.ExpressReceuserDao;
import com.jatools.vo.basic.ExpressReceuser;
import com.jatools.web.util.DateUtil;
import com.opensymphony.oscache.util.StringUtil;

public class ExpressReceuserDaoImpl extends BaseDao implements ExpressReceuserDao {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	public Pager getExpressReceuserPageData(Map<String, String> condition){
		return executeQueryForPager("ExpressReceuser.getExpressReceuserPageData", "ExpressReceuser.getExpressReceuserTotalCount", condition);
	}

	/**
	 * 删除记录
	 * @param receId
	 */
	public void deleteExpressReceuser(String receId){
		delete("ExpressReceuser.deleteExpressReceuser", receId);
	}

	/**
	 * 根据组织获取快递人员
	 */
	public List<ExpressReceuser> getExpressReceuserByOrgId(String orgId){
		return executeQueryForList("ExpressReceuser.getExpressReceuserByOrgId", orgId);
	}
	/**
	 * 保存
	 * @param orgId
	 * @param addUserIds
	 */
	public void saveExpressReceuser(String orgId, String[] addUserIds, String userid){
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		for(String receUser : addUserIds){
			if(!StringUtil.isEmpty(receUser)){
				Map<String, String> map = new HashMap<String, String>();
				map.put("orgId", orgId);
				map.put("receUser", receUser);
				map.put("userid", userid);
				map.put("date", DateUtil.getCurrentDate18());
				list.add(map);
			}
		}
		executeBatchInsert("ExpressReceuser.saveExpressReceuser", list);
	}
}
