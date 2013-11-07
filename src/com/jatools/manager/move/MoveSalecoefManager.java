package com.jatools.manager.move;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.move.MoveSalecoefHead;
import com.jatools.vo.move.MoveSalecoefLine;

public interface MoveSalecoefManager {

	/**
	 * 获取调拨销价系数分页数据
	 * @param condition
	 * @return
	 */
	Pager getMoveSaleCoefPageData(Map<String, String> condition);

	/**
	 * 保存或修改调拨销价系数
	 * @param head
	 * @param lineList
	 */
	void saveOrUpdateMoveSalecoef(MoveSalecoefHead head, List<MoveSalecoefLine> lineList);
	/**
	 * 获取头表数据
	 * @param headid
	 * @return
	 */
	MoveSalecoefHead getMoveSalecoefHead(String headid);
	/**
	 * 获取行表数据
	 * @param headid
	 * @return
	 */
	List<MoveSalecoefLine> getMoveSalecoefLineList(String headid);
	/**
	 * 删除调拨销价系数
	 * @param headidArr
	 * @return
	 */
	void deleteMoveSalecoef(String[] headidArr);

	/**
	 * 检查调入组织、大类、小类是否已经存在
	 * @param inOrgId
	 * @param itemClassId
	 * @param ornaClass
	 * @param headid
	 * @return
	 */
	List<MoveSalecoefHead> checkRepeat(String inOrgId, String itemClassId, String ornaClassId, String styleItemIds, String headid);
	/**
	 * 复制数据
	 * @param fromOrgId
	 * @param toOrgIds
	 * @param sessionUserId
	 */
	void copyData(String fromOrgId, String toOrgIds, String sessionUserId);
}
