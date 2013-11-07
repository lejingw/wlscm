package com.jatools.dao.basic;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.util.SelectorOption;

public interface PurEmpArticleTypeDao {

	/**
	 * 获取采购员商品类别分页数据
	 * @param condition
	 * @return
	 */
	public Pager getPurEmpArticleTypePageData(Map<String, String> condition);

	/**
	 * 根据调入组织id获取调出组织
	 */
	public List<SelectorOption> getOrgs(String orgid, String empFlag);

	/**
	 * 保存采购员商品类别
	 * @param inOrgId
	 * @param outOrgIdList
	 */
	public void savePurEmpArticleType(String inOrgId, List<String> outOrgIdList, String empFlag, String userid);
	/**
	 * 删除采购员商品类别
	 * @param netIdList
	 */
	public void deletePurEmpArticleType(List<String> netIdList);
	/**
	 * 根据组织删除采购员商品类别
	 * @param netIdList
	 */
	public void deletePurEmpArticleTypeByOrgid(String orgid, String empFlag);
}
