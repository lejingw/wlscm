package com.jatools.manager.basic;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.util.SelectorOption;

public interface PurEmpArticleTypeManager {

	/**
	 * 获取采购员商品类别分页数据
	 * @return
	 */
	public Pager getPurEmpArticleTypePageData(Map<String, String> condition);

	/**
	 * 根据调入组织id获取调出组织
	 * @return
	 */
	public List<SelectorOption> getOrgs(String purEmpId, String empFlag);

	/**
	 * 保存采购员商品类别
	 */
	public void savePurEmpArticleType(String purEmpId, List<String> articleTypeIdList, String empFlag, String userid);
	/**
	 * 删除采购员商品类别
	 */
	public void deletePurEmpArticleType(List<String> idList);
}
