package com.jatools.manager.move;

import java.util.List;
import java.util.Map;
import com.jatools.common.Pager;
import com.jatools.vo.move.MovePackHead;
import com.jatools.vo.move.MovePackLine;

public interface MovePackManager {

	/**
	 * 获取装箱单分页数据
	 * @param condition
	 * @return
	 */
	public Pager getMovePackPageData(Map<String, String> condition, String billType, String jmFlag, String orgId, String userId);
	/**
	 * 获取调拨单数据
	 * @param moveNo
	 * @return
	 */
	public MovePackLine getMoveBillInfo(String moveNo, String jmFlag);
	/**
	 * 获取维修单数据
	 * @param fixNo
	 * @return
	 */
	public MovePackLine getFixBillInfo(String fixNo);
	/**
	 * 获取交接数据
	 * @param handoverNo
	 * @return
	 */
	public MovePackLine getHandoverBillInfo(String handoverNo);
	/**
	 * 获取形态转换单数据
	 * @param procChangeNo
	 * @return
	 */
	public MovePackLine getProcChangeBillInfo(String procChangeNo);
	/**
	 * 检查调拨单状态是否有效
	 * @param newLineList
	 * @return
	 */
	public List<String> checkBillStatusAvail(List<MovePackLine> newLineList, String billType, String jmFlag, String headid);
	public List<String> checkBillStatusAvail2(List<MovePackLine> newLineList, String billType, String jmFlag, String status);
	/**
	 * 保存或修改装箱单
	 * @param packHead
	 * @param newMoveIdList 添加的行记录
	 * @param deleteMoveIdList 删除的行记录
	 * @return
	 */
	public void saveMovePack(MovePackHead packHead, List<MovePackLine> newMoveIdList, List<String> deleteMoveIdList, String userid);
	/**
	 * 删除装箱单
	 * @param headid
	 */
	public void deleteMovePack(String headid, String userid);
	/**
	 * 获取装箱单行
	 * @param headid
	 * @return
	 */
	public List<MovePackLine> getMovePackLine(String headid);
	/**
	 * 获取装箱单头
	 * @param headid
	 * @return
	 */
	public MovePackHead getMovePackHead(String headid);
	/**
	 * 检查单据编号是否已经存在
	 * @param billType
	 * @param headid
	 * @param billno
	 */
	public void checkBillExists(String billType, String jmFlag, String headid, String billno);
	/**
	 * 接收装箱单
	 * @param headid
	 * @param userid
	 */
	public void receiveMovePack(String headid, String userid);
	/**
	 * 修改可接收人
	 * @param headid
	 * @param receUserIds
	 * @param sessionUserId
	 */
	public void updateReceUserIds(String headid, String receUserIds, String sessionUserId);
	public void updateExpress(String headid, String expressCharge,
			String expressPremium, String expressMoney);
}
