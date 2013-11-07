package com.jatools.manager.basic.impl;

import java.util.List;
import java.util.Map;
import com.jatools.common.Pager;
import com.jatools.dao.basic.PurEmpArticleTypeDao;
import com.jatools.manager.basic.PurEmpArticleTypeManager;
import com.jatools.vo.util.SelectorOption;

public class PurEmpArticleTypeManagerImpl implements PurEmpArticleTypeManager {
	private PurEmpArticleTypeDao purEmpArticleTypeDao;

	public void setPurEmpArticleTypeDao(PurEmpArticleTypeDao purEmpArticleTypeDao) {
		this.purEmpArticleTypeDao = purEmpArticleTypeDao;
	}

	/**
	 * 获取采购员商品类别分页数据
	 * @return
	 */
	public Pager getPurEmpArticleTypePageData(Map<String, String> condition){
		return purEmpArticleTypeDao.getPurEmpArticleTypePageData(condition);
	}

	/**
	 * 根据组织id获取调出组织
	 * @return
	 */
	public List<SelectorOption> getOrgs(String purEmpId, String empFlag){
		return purEmpArticleTypeDao.getOrgs(purEmpId, empFlag);
	}

	/**
	 * 保存采购员商品类别
	 * @return
	 */
	public void savePurEmpArticleType(String purEmpId, List<String> articleTypeIdList, String empFlag, String userid){
		purEmpArticleTypeDao.deletePurEmpArticleTypeByOrgid(purEmpId, empFlag);
		purEmpArticleTypeDao.savePurEmpArticleType(purEmpId, articleTypeIdList, empFlag, userid);
	}

	/**
	 * 删除采购员商品类别
	 * @return
	 */
	public void deletePurEmpArticleType(List<String> idList){
		purEmpArticleTypeDao.deletePurEmpArticleType(idList);
	}
}