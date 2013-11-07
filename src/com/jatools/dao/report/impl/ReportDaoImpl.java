package com.jatools.dao.report.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Global;
import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.report.ReportDao;

public class ReportDaoImpl extends BaseDao implements ReportDao {
	/**
	 * 执行sql查询
	 */
	public List<Map> executeQurey(String sql){
		return (List<Map>)executeQueryForList("Report.executeQurey", sql);
	}
	/**
	 * 执行sql查询获取分页数据
	 */
	public Pager executeQueryPageData(String sql, String start, String limit){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("start", null == start || start.length()<1?Global.PAGE_DEFAULT_START:start);
		condition.put("limit", null == limit || limit.length()<1?Global.PAGE_DEFAULT_LIMIT:limit);
		condition.put("sql", sql);
		return executeQueryForPager("Report.executeQueryPageData", "Report.executeQueryTotalCount", condition);
	}

    @Override
    public void saveOrnaMoveType(String seqId) {
       this.delete("Report.deleteOrnaMoveType", null);
        this.executeInsert("Report.insertOrnaMoveType", seqId);
    }
}
