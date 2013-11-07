package com.jatools.dao.calc.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.calc.SbraTypeDao;
import com.jatools.vo.bd.Quality;
import com.jatools.vo.calc.SbraTypeHead;
import com.jatools.vo.calc.SbraTypeLine;

public class SbraTypeDaoImpl extends BaseDao implements SbraTypeDao{
	/**
	 * 获取配件类型分页数据
	 * @param condition
	 * @return
	 */
	public Pager getSbraTypePageData(Map<String, String> condition){
		return executeQueryForPager("SbraType.getPageData", "SbraType.getTotalCount", condition);
	}
	/**
	 * 保存配件类型
	 * @param head
	 * @return
	 */
	public String saveSbraTypeHead(SbraTypeHead head){
		return (String)executeInsert("SbraType.saveSbraTypeHead", head);
	}

	/**
	 * 更新配件类型
	 * @param head
	 */
	public void updateSbraTypeHead(SbraTypeHead head){
		executeUpdate("SbraType.updateSbraTypeHead", head);
	}
	

	/**
	 * 删除配件类型行
	 * @param headid
	 */
	public void deleteSbraTypeLineByHeadid(String headid){
		executeUpdate("SbraType.deleteSbraTypeLineByHeadid", headid);
	}

	/**
	 * 保存配件类型行
	 * @param headid
	 */
	public void saveSbraTypeLineList(List<SbraTypeLine> lineList){
		executeBatchInsert("SbraType.saveSbraTypeLineList", lineList);
	}
	/**
	 * 获取头表数据
	 * @param headid
	 * @return
	 */
	public SbraTypeHead getSbraTypeHead(String headid){
		return (SbraTypeHead) executeQueryForObject("SbraType.getSbraTypeHead", headid);
	}
	/**
	 * 获取行表数据
	 * @param headid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SbraTypeLine> getSbraTypeLineList(String headid){
		return (List<SbraTypeLine>) executeQueryForList("SbraType.getSbraTypeLineList", headid);
	}
	
	/**
	 * 删除配件类型头
	 * @param headid
	 */
	public void deleteSbraTypeHead(String headid){
		executeUpdate("SbraType.deleteSbraTypeHead", headid);
	}
	@Override
	public List<SbraTypeHead> getSbraTypeHeadList() {
		// TODO Auto-generated method stub
		return executeQueryForList("SbraType.getSbraTypeHeadList", "");
	}
	/**
	 * 获取配件类型下的材质
	 * @param headid
	 * @return
	 */
	public List<Quality> getSbraTypeLineSelectList(String headid){
		return executeQueryForList("SbraType.getSbraTypeLineSelectList", headid);
	}
}
