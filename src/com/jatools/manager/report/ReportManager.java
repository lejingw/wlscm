package com.jatools.manager.report;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ReportManager {

	/**
	 * 执行sql查询
	 */
	public List<Map> executeQurey(String sql);

	/**
	 * 执行sql查询获取分页数据
	 */
	public Pager executeQueryPageData(String sql, String start, String limit);

    void saveOrnaMoveType(String seqId);

    ModelAndView importOrnaMoveType(HttpServletRequest req, HttpServletResponse res);
}
