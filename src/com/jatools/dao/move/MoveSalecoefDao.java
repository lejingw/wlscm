package com.jatools.dao.move;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.move.MoveSalecoefHead;
import com.jatools.vo.move.MoveSalecoefLine;

public interface MoveSalecoefDao {
	/**
	 * 获取调拨销价系数分页数据
	 * @param condition
	 * @return
	 */
	Pager getMoveSaleCoefPageData(Map<String, String> condition);

	/**
	 * 保存调拨销价系数
	 * @param head
	 * @return
	 */
	String saveMoveSalecoefHead(MoveSalecoefHead head);

	/**
	 * 更新调拨销价系数
	 * @param head
	 */
	void updateMoveSalecoefHead(MoveSalecoefHead head);

	/**
	 * 删除调拨销价系数行
	 * @param headid
	 */
	void deleteMoveSalecoefLineByHeadid(String headid);

	/**
	 * 保存调拨销价系数行
	 * @param headid
	 */
	void saveMoveSalecoefLineList(List<MoveSalecoefLine> lineList);

	/**
	 * 获取头表数据
	 * @param headid
	 * @return
	 */
	public MoveSalecoefHead getMoveSalecoefHead(String headid);
	/**
	 * 获取行表数据
	 * @param headid
	 * @return
	 */
	public List<MoveSalecoefLine> getMoveSalecoefLineList(String headid);
	/**
	 * 删除调拨销价系数头
	 * @param headid
	 */
	void deleteMoveSalecoefHead(String headid);
	/**
	 * 检查调入组织、大类、小类是否已经存在
	 * @param inOrgId
	 * @param itemClassId
	 * @param ornaClass
	 * @param headid
	 * @return
	 */
	List<MoveSalecoefHead> checkRepeat(String inOrgId, String itemClassId, String ornaClassId, String styleItemIds, String headid);

	void deleteMoveSolecoefHeadByOrgids(String toOrgIds);
	
	void deleteMoveSolecoefLineByOrgids(String toOrgIds);

	public  void createNewHeadidTempData(String orgid, String toOrgIds);
	
	void copyMoveSolecoefHead(String fromOrgId, String toOrgIds, String userid);

	void copyMoveSolecoefLine(String fromOrgId, String toOrgIds, String userid);
}
