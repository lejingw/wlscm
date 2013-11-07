package com.jatools.manager.move;

import java.util.List;
import java.util.Map;
import com.jatools.common.Pager;
import com.jatools.vo.move.MoveCostcoef;
import com.jatools.vo.util.SelectorOption;

public interface MoveCostcoefManager {

	/**
	 * 获取调拨销价系数分页数据
	 * @param condition
	 * @return
	 */
	Pager getMoveCostCoefPageData(Map<String, String> condition);

	/**
	 * 保存或修改调拨销价系数
	 * @param coef
	 * @param lineList
	 */
	void saveOrUpdateMoveCostcoef(MoveCostcoef coef, String userid);

	/**
	 * 获取记录对象
	 * @param coefId
	 * @return
	 */
	MoveCostcoef getMoveCostcoef(String coefId);
	
	/**
	 * 根据大类获取小类
	 * @param itemClassId
	 * @return
	 */
	List<SelectorOption> getOrnaClassByItemClassIdForSlt(String itemClassId, String coefId);
	/**
	 * 删除调拨销价系数
	 * @param coefIdArr
	 * @return
	 */
	void deleteMoveCostcoef(String[] coefIdArr);
}
