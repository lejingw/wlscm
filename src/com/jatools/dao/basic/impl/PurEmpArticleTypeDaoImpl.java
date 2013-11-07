package com.jatools.dao.basic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.dao.BaseDao;
import com.jatools.dao.basic.PurEmpArticleTypeDao;
import com.jatools.vo.basic.PurEmpArticleType;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.util.DateUtil;

public class PurEmpArticleTypeDaoImpl extends BaseDao implements PurEmpArticleTypeDao {
	/**
	 * 获取采购员商品类别分页数据
	 * @param condition
	 * @return
	 */
	public Pager getPurEmpArticleTypePageData(Map<String, String> condition){
		return executeQueryForPager("PurEmpArticleType.getPurEmpArticleTypePageData", "PurEmpArticleType.getPurEmpArticleTypeTotalCount", condition);
	}
	/**
	 * 根据调入组织id获取调出组织
	 * @return
	 */
	public List<SelectorOption> getOrgs(String purEmpId, String empFlag){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("purEmpId", purEmpId);
		condition.put("empFlag", empFlag);
		return executeQueryForList("PurEmpArticleType.getOrgs", condition);
	}
	/**
	 * 保存采购员商品类别
	 */
	public void savePurEmpArticleType(String purEmpId, List<String> articleTypeIdList, String empFlag, String userid){
		List<PurEmpArticleType> parameterList = new ArrayList<PurEmpArticleType>();
		for(String articleTypeId : articleTypeIdList){
			PurEmpArticleType purEmpArticleType = new PurEmpArticleType();
			if("1".equals(empFlag)){				
				purEmpArticleType.setPurEmpId(purEmpId);
				purEmpArticleType.setArticleTypeId(articleTypeId);
			}else{				
				purEmpArticleType.setArticleTypeId(purEmpId);
				purEmpArticleType.setPurEmpId(articleTypeId);
			}
			purEmpArticleType.setCreateDate(DateUtil.getCurrentDate18());
			purEmpArticleType.setCreateId(userid);
			purEmpArticleType.setStatus(DictConstant.BILL_STATUS_SAVE);
			parameterList.add(purEmpArticleType);
		}
		executeBatchInsert("PurEmpArticleType.savePurEmpArticleType", parameterList);
	}
	/**
	 * 删除采购员商品类别
	 */
	public void deletePurEmpArticleType(List<String> idList){
		executeBatchDelete("PurEmpArticleType.deletePurEmpArticleType", idList);
	}
	/**
	 * 根据组织删除采购员商品类别
	 */
	public void deletePurEmpArticleTypeByOrgid(String purEmpId, String empFlag){
		Map<String, String> map = new HashMap<String, String>();
		map.put("purEmpId", purEmpId);
		map.put("empFlag", empFlag);
		delete("PurEmpArticleType.deletePurEmpArticleTypeByOrgid", map);
	}
}
