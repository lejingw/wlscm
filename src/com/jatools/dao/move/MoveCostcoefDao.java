package com.jatools.dao.move;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.move.MoveCostcoef;
import com.jatools.vo.util.SelectorOption;

public interface MoveCostcoefDao {
	/**
	 * 获取调拨销价系数分页数据
	 * @param condition
	 * @return
	 */
	Pager getMoveCostCoefPageData(Map<String, String> condition);

	/**
	 * 保存调拨销价系数
	 * @param head
	 * @return
	 */
	String saveMoveCostcoef(MoveCostcoef head);

	/**
	 * 更新调拨销价系数
	 * @param head
	 */
	void updateMoveCostcoef(MoveCostcoef head);
	/**
	 * 获取记录对象
	 * @param coefId
	 * @return
	 */
	public MoveCostcoef getMoveCostcoef(String coefId);
	/**
	 * 根据大类获取小类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getOrnaClassByItemClassIdForSlt(String itemClassId, String coefId);

	/**
	 * 删除调拨成本系数
	 * @param coefId
	 */
	void deleteMoveCostcoef(String coefId);
	/**
	 * 检查大类、小类是否已经存在
	 * @param inOrgId
	 * @param itemClassId
	 * @param ornaClass
	 * @param coefId
	 * @return
	 */
	Integer checkRepeat(String itemClassId, String ornaClassId, String coefId);
}
