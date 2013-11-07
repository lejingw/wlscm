package com.jatools.dao.sys.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.sys.QueryConditionDao;
import com.jatools.vo.sys.QueryCondition;
import com.jatools.vo.sys.QueryConditionHead;
import com.jatools.vo.sys.QueryConditionLine;
import com.jatools.vo.util.SelectorOption;

public class QueryConditionDaoImpl extends BaseDao implements QueryConditionDao {

	@Override
	public Pager getQueryConditionPageData(Map<String, String> condition) {
		return executeQueryForPager("QueryCondition.getQueryConditionPageData", "QueryCondition.getQueryConditionTotalCount", condition);
	}
	
	public QueryConditionHead getQueryConditionHead(String headid){
		return (QueryConditionHead) executeQueryForObject("QueryCondition.getQueryConditionHead", headid);
	}

	public List<QueryConditionLine> getQueryConditionLineByheadid(String headid) {
		return executeQueryForList("QueryCondition.getQueryConditionLineByheadid", headid);
	}
	
	public List<QueryConditionLine> getQueryConditionLineByQueryCode(String queryCode) {
		return executeQueryForList("QueryCondition.getQueryConditionLineByQueryCode", queryCode);
	}

	public String saveQueryCondition(QueryConditionHead head){
		return (String) executeInsert("QueryCondition.saveQueryCondition", head);
	}

	public void updateQueryCondition(QueryConditionHead head){
		executeUpdate("QueryCondition.updateQueryCondition", head);
	}

	public void deleteQueryConditionLineByHeadid(String headid){
		delete("QueryCondition.deleteQueryConditionLineByHeadid", headid);
	}
	
	public void deleteQueryConditionHead(String headid){
		delete("QueryCondition.deleteQueryConditionHead", headid);
	}

	public void saveQueryConditionLine(List<QueryConditionLine> lineList){
		executeBatchInsert("QueryCondition.saveQueryConditionLine", lineList);
	}
	
	public void updateQueryConditionLine(List<QueryConditionLine> updateLineArr){
		executeBatchUpdate("QueryCondition.updateQueryConditionLine", updateLineArr);
	}
	
	public void deleteQueryConditionLine(List<String> deleteIdArr){
		executeBatchDelete("QueryCondition.deleteQueryConditionLine", deleteIdArr);
	}
	public List<SelectorOption> getQueryFieldName(String queryCode){
		return executeQueryForList("QueryCondition.getQueryFieldName", queryCode);
	}
	public QueryConditionLine getQueryConditionLine(String lineid){
		return (QueryConditionLine) executeQueryForObject("QueryCondition.getQueryConditionLine", lineid);
	}
	public List<SelectorOption> getQueryContentBySqlForSlt(String sql){
		return executeQueryForList("QueryCondition.getQueryContentBySqlForSlt", sql);
	}
}
