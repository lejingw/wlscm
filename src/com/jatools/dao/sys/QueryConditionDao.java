package com.jatools.dao.sys;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.sys.QueryCondition;
import com.jatools.vo.sys.QueryConditionHead;
import com.jatools.vo.sys.QueryConditionLine;
import com.jatools.vo.util.SelectorOption;

public interface QueryConditionDao {

	public Pager getQueryConditionPageData(Map<String, String> condition);

	public QueryConditionHead getQueryConditionHead(String headid);

	public List<QueryConditionLine> getQueryConditionLineByheadid(String headid);

	public List<QueryConditionLine> getQueryConditionLineByQueryCode(String queryCode);

	public String saveQueryCondition(QueryConditionHead head);

	public void updateQueryCondition(QueryConditionHead head);

	public void deleteQueryConditionLineByHeadid(String headid);
	
	public void deleteQueryConditionHead(String headid);

	public void saveQueryConditionLine(List<QueryConditionLine> lineList);
	
	public void updateQueryConditionLine(List<QueryConditionLine> updateLineArr);
	
	public void deleteQueryConditionLine(List<String> deleteIdArr);

	public List<SelectorOption> getQueryFieldName(String queryCode);

	public QueryConditionLine getQueryConditionLine(String lineid);

	public List<SelectorOption> getQueryContentBySqlForSlt(String sql);

}
