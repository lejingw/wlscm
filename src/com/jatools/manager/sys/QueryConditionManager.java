package com.jatools.manager.sys;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.sys.QueryCondition;
import com.jatools.vo.sys.QueryConditionHead;
import com.jatools.vo.sys.QueryConditionLine;
import com.jatools.vo.util.SelectorOption;

public interface QueryConditionManager {

	public Pager getQueryConditionPageData(Map<String, String> condition);

	public QueryConditionHead getQueryConditionHead(String headid);

	public List<QueryConditionLine> getQueryConditionLineByheadid(String headid);

	public void saveOrUpdateQueryCondition(QueryConditionHead head, List<QueryConditionLine> addLineArr, List<QueryConditionLine> updateLineArr, List<String> deleteIdArr);

	public void deleteQueryCondition(String headid);

	public List<SelectorOption> getQueryFieldName(String queryCode);

	public QueryConditionLine getQueryConditionLine(String lineid);

	public List<SelectorOption> getQueryContentBySqlForSlt(String sql);

	public List<QueryConditionLine> getQueryConditionLineByQueryCode(String queryCode);


}
