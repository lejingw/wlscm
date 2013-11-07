package com.jatools.manager.sys.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.sys.QueryConditionDao;
import com.jatools.manager.sys.QueryConditionManager;
import com.jatools.vo.sys.QueryCondition;
import com.jatools.vo.sys.QueryConditionHead;
import com.jatools.vo.sys.QueryConditionLine;
import com.jatools.vo.util.SelectorOption;
import com.opensymphony.oscache.util.StringUtil;

public class QueryConditionManagerImpl implements QueryConditionManager{
	private QueryConditionDao queryConditionDao;

	public void setQueryConditionDao(QueryConditionDao queryConditionDao) {
		this.queryConditionDao = queryConditionDao;
	}
	public Pager getQueryConditionPageData(Map<String, String> condition){
		return queryConditionDao.getQueryConditionPageData(condition);
	}
	@Override
	public QueryConditionHead getQueryConditionHead(String headid) {
		return queryConditionDao.getQueryConditionHead(headid);
	}
	@Override
	public List<QueryConditionLine> getQueryConditionLineByheadid(String headid) {
		return queryConditionDao.getQueryConditionLineByheadid(headid);
	}
	public List<QueryConditionLine> getQueryConditionLineByQueryCode(String queryCode){
		return queryConditionDao.getQueryConditionLineByQueryCode(queryCode);
	}
	@Override
	public void saveOrUpdateQueryCondition(QueryConditionHead head, List<QueryConditionLine> addLineArr, List<QueryConditionLine> updateLineArr, List<String> deleteIdArr) {
		String headid = head.getHeadid();
		if(StringUtil.isEmpty(headid)){
			headid = queryConditionDao.saveQueryCondition(head);
		}else{
			queryConditionDao.updateQueryCondition(head);
			queryConditionDao.updateQueryConditionLine(updateLineArr);
			queryConditionDao.deleteQueryConditionLine(deleteIdArr);
		}
		for(QueryConditionLine line : addLineArr){
			line.setHeadid(headid);
		}
		queryConditionDao.saveQueryConditionLine(addLineArr);
	}
	public void deleteQueryCondition(String headid){
		queryConditionDao.deleteQueryConditionLineByHeadid(headid);
		queryConditionDao.deleteQueryConditionHead(headid);
	}
	public List<SelectorOption> getQueryFieldName(String queryCode){
		return queryConditionDao.getQueryFieldName(queryCode);
	}
	public QueryConditionLine getQueryConditionLine(String lineid){
		return queryConditionDao.getQueryConditionLine(lineid);
	}
	public List<SelectorOption> getQueryContentBySqlForSlt(String sql){
		return queryConditionDao.getQueryContentBySqlForSlt(sql);
	}
}
