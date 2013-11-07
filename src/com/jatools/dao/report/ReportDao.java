package com.jatools.dao.report;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;

public interface ReportDao {
	/**
	 * 执行sql查询
	 */
	List<Map> executeQurey(String sql);
	/**
	 * 执行sql查询获取分页数据
	 */
	Pager executeQueryPageData(String sql, String start, String limit);

    void saveOrnaMoveType(String seqId);
}
