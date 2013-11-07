package com.jatools.dao.move.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.move.MoveCostcoefDao;
import com.jatools.vo.move.MoveCostcoef;
import com.jatools.vo.util.SelectorOption;

public class MoveCostcoefDaoImpl extends BaseDao implements MoveCostcoefDao {
	/**
	 * 获取调拨销价系数分页数据
	 * @param condition
	 * @return
	 */
	public Pager getMoveCostCoefPageData(Map<String, String> condition){
		return executeQueryForPager("MoveCostcoef.getPageData", "MoveCostcoef.getTotalCount", condition);
	}
	/**
	 * 保存调拨销价系数
	 * @param coef
	 * @return
	 */
	public String saveMoveCostcoef(MoveCostcoef coef){
		return (String) executeInsert("MoveCostcoef.saveMoveCostcoef", coef);
	}
	/**
	 * 更新调拨销价系数
	 * @param head
	 */
	public void updateMoveCostcoef(MoveCostcoef coef){
		executeUpdate("MoveCostcoef.updateMoveCostcoef", coef);
	}
	/**
	 * 获取记录对象
	 * @param coefId
	 * @return
	 */
	public MoveCostcoef getMoveCostcoef(String coefId){
		return (MoveCostcoef) executeQueryForObject("MoveCostcoef.getMoveCostcoef", coefId);
	}
	/**
	 * 根据大类获取小类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getOrnaClassByItemClassIdForSlt(String itemClassId, String coefId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("itemClassId", itemClassId);
		condition.put("coefId", coefId);
		return executeQueryForList("MoveCostcoef.getOrnaClassByItemClassIdForSlt", condition);
	}
	/**
	 * 删除调拨销价系数头
	 * @param coefId
	 */
	public void deleteMoveCostcoef(String coefId){
		delete("MoveCostcoef.deleteMoveCostcoef", coefId);
	}
	
	/**
	 * 检查调大类、小类是否已经存在
	 * @param inOrgId
	 * @param itemClassId
	 * @param ornaClass
	 * @param coefId
	 * @return
	 */
	public Integer checkRepeat(String itemClassId, String ornaClassId, String coefId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("itemClassId", itemClassId);
		condition.put("ornaClassId", ornaClassId);
		condition.put("coefId", coefId);
		return (Integer) executeQueryForObject("MoveCostcoef.checkRepeat", condition);
	}
}
