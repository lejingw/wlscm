package com.jatools.dao.move;

import java.util.List;
import java.util.Map;
import com.jatools.common.Pager;
import com.jatools.vo.move.MovePackHead;
import com.jatools.vo.move.MovePackLine;

public interface MovePackDao {

	/**
	 * 获取调拨单分页数据
	 * @param condition
	 * @return
	 */
	public Pager getMovePackPageData(Map<String, String> condition, String billType, String jmFlag, String orgId, String userId);
	/**
	 * 获取调拨单信息
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
	 * 形态转换单
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
	 * 保存调拨单头
	 * @param moveHead
	 * @param userid
	 * @return
	 */
	public String saveMovePackHead(MovePackHead moveHead, String userid);
	/**
	 * 修改调拨单头
	 * @param moveHead
	 * @param userid
	 * @return
	 */
	public void updateMovePackHead(MovePackHead moveHead, String userid);
	/**
	 * 保存调拨单行
	 * @param newLineList
	 * @param headid
	 * @param userid
	 */
	public void saveMovePackLine(List<MovePackLine> newLineList, String headid, String userid);
	/**
	 * 删除调拨单行
	 * @param headid
	 */
	public void deleteMovePackLine(String headid);
	/**
	 * 删除调拨单头
	 * @param headid
	 */
	public void deleteMovePackHead(String headid);
	/**
	 * 获取调拨单行
	 * @param headid
	 * @return
	 */
	public List<MovePackLine> getMovePackLine(String headid);
	/**
	 * 获取调拨单头
	 * @param headid
	 * @return
	 */
	public MovePackHead getMovePackHead(String headid);
	/**
	 * 根据调拨单id删除装箱单行
	 * @param deleteMoveIdList
	 * @param headid
	 * @param userid
	 */
	public void deleteMovePackLineByBillId(List<String> deleteMoveIdList, String headid, String userid);
	/**
	 * 判断单据是否存在时，获取所在的装箱单单号
	 * @param billType
	 * @param headid
	 * @param billno
	 * @return
	 */
	public List<String> getPackNoByBillno(String billType, String jmFlag, String headid, String billno);
	/**
	 * 接收装箱单
	 * @param headid
	 * @param userid
	 */
	public void receiveMovePack(String headid, String userid);
	/**
	 * 维修件装箱单回写快递单号
	 * @param headid
	 * @param expressNo null表示清除
	 */
	public void updateFixBillPackNo(String headid, String planStatus, String expressNo);
	/**
	 * 修改可接收人
	 * @param headid
	 * @param receUserIds
	 */
	public void updateReceUserIds(String headid, String receUserIds, String useid);
	/**
	 * 回写调拨单装箱单号
	 * @param packId
	 * @param packNo
	 */
	public void updateMoveBillPackNo(String packId, String jmFlag, String packNo);
	public void updateExpress(String headid, String expressCharge,
			String expressPremium, String expressMoney);
}
