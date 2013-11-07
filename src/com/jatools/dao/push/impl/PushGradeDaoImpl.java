package com.jatools.dao.push.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.push.PushGradeDao;
import com.jatools.vo.push.PushGrade;
import com.jatools.web.util.DateUtil;

public class PushGradeDaoImpl extends BaseDao implements PushGradeDao{
	/**
	 * 获取分页数据
	 */
	public Pager getPushGradePageData(Map<String, String> condition){
		return executeQueryForPager("PushGrade.getPushGradePageData", "PushGrade.getPushGradeTotalCount", condition);
	}
	@Override
	public void savePushGrade(PushGrade dn) {
		executeInsert("PushGrade.savePushGrade", dn);
	}

	@Override
	public void updatePushGrade(PushGrade dn) {
		executeUpdate("PushGrade.updatePushGrade", dn);
	}
	
	public List<PushGrade> checkPushGradeRepeat(PushGrade dn) {
		return executeQueryForList("PushGrade.checkPushGradeRepeat", dn);
	}

	/**
	 * 删除
	 * @param billIdList
	 */
	public void deletePushGrade(List<String> billIdList){
		executeBatchDelete("PushGrade.deletePushGrade", billIdList);
	}

    @Override
    public void savePushGradeFromExcel(String seqId, String userId) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("seqId", seqId);
        map.put("userId", userId);
        map.put("date", DateUtil.getCurrentDate18());
        executeInsert("PushGrade.savePushGradeFromExcel", map);
    }
}
