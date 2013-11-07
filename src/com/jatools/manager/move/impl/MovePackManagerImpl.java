package com.jatools.manager.move.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.dao.move.MoveBillDao;
import com.jatools.dao.move.MovePackDao;
import com.jatools.dao.pur.HandoverDao;
import com.jatools.dao.stock.ProcChangeHeadDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.move.MovePackManager;
import com.jatools.vo.move.MovePackHead;
import com.jatools.vo.move.MovePackLine;
import com.jatools.ws.remote.WorkflowService;
import com.opensymphony.oscache.util.StringUtil;

public class MovePackManagerImpl extends BaseManager implements MovePackManager {
	private MovePackDao movePackDao;
	private MoveBillDao moveBillDao;
	private CommonDao commonDao;
	private HandoverDao handoverDao;
	private ProcChangeHeadDao procChangeHeadDao;
	private WorkflowService workflowService;
	
	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	public CommonDao getCommonDao() {
		return commonDao;
	}
	public void setHandoverDao(HandoverDao handoverDao) {
		this.handoverDao = handoverDao;
	}
	public void setMoveBillDao(MoveBillDao moveBillDao) {
		this.moveBillDao = moveBillDao;
	}
	public void setMovePackDao(MovePackDao movePackDao) {
		this.movePackDao = movePackDao;
	}
	public void setProcChangeHeadDao(ProcChangeHeadDao procChangeHeadDao) {
		this.procChangeHeadDao = procChangeHeadDao;
	}
	/**
	 * 获取装箱单分页数据
	 * @param condition
	 * @return
	 */
	public Pager getMovePackPageData(Map<String, String> condition, String billType, String jmFlag, String orgId, String userId) {
		return movePackDao.getMovePackPageData(condition, billType, jmFlag, orgId, userId);
	}
	/**
	 * 获取调拨单数据
	 * @param moveNo
	 * @return
	 */
	public MovePackLine getMoveBillInfo(String moveNo, String jmFlag) {
		return movePackDao.getMoveBillInfo(moveNo, jmFlag);
	}
	/**
	 * 获取维修单数据
	 * @param fixNo
	 * @return
	 */
	public MovePackLine getFixBillInfo(String fixNo){
		return movePackDao.getFixBillInfo(fixNo);
	}
	/**
	 * 获取交接数据
	 * @param handoverNo
	 * @return
	 */
	public MovePackLine getHandoverBillInfo(String handoverNo){
		return movePackDao.getHandoverBillInfo(handoverNo);
	}
	/**
	 * 获取形态转换单数据
	 * @param procChangeNo
	 * @return
	 */
	public MovePackLine getProcChangeBillInfo(String procChangeNo){
		return movePackDao.getProcChangeBillInfo(procChangeNo);
	}
	/**
	 * 检查调拨单状态是否有效
	 * @param newLineList
	 * @return
	 */
	public List<String> checkBillStatusAvail(List<MovePackLine> newLineList, String billType, String jmFlag, String headid) {
		return movePackDao.checkBillStatusAvail(newLineList, billType, jmFlag, headid);
	}
	/**
	 * 检查调拨单状态是否有效
	 * @param newLineList
	 * @return
	 */
	public List<String> checkBillStatusAvail2(List<MovePackLine> newLineList, String billType, String jmFlag, String status) {
		return movePackDao.checkBillStatusAvail2(newLineList, billType, jmFlag, status);
	}
	/**
	 * 保存或修改装箱单
	 * @param packHead
	 * @param newLineList 添加的行记录
	 * @param deleteBillIdList 删除的行记录
	 * @return
	 */
	public void saveMovePack(MovePackHead packHead, List<MovePackLine> newLineList, List<String> deleteBillIdList, String userid) {
		if (StringUtil.isEmpty(packHead.getHeadid())) {
			//饰品装箱单，可以装调拨单、退货单、退残单
			if(DictConstant.MOVE_PACK_BILL_TYPE_SHIPING.equals(packHead.getBillType())){
				if(DictConstant.YES_OR_NO_YES.equals(packHead.getJmFlag())){
					packHead.setBillno(commonDao.getBillno(GlobalConstant.BILL_CODE_ZHUANGXIANG_ORNA_JM));
				}else{
					packHead.setBillno(commonDao.getBillno(GlobalConstant.BILL_CODE_ZHUANGXIANG_ORNA));
				}
			}else if(DictConstant.MOVE_PACK_BILL_TYPE_WEIXIU.equals(packHead.getBillType())){
				packHead.setBillno(commonDao.getBillno(GlobalConstant.BILL_CODE_ZHUANGXIANG_WEIXIU));
			}else if(DictConstant.MOVE_PACK_BILL_TYPE_JIAOJIE.equals(packHead.getBillType())){
				packHead.setBillno(commonDao.getBillno(GlobalConstant.BILL_CODE_ZHUANGXIANG_JIAOJIE));
			}else
				throw new RuntimeException("单据类别错误");
			packHead.setSendOrgId(packHead.getOutOrgId());
			packHead.setSendUserId(userid);
			packHead.setReceOrgId(packHead.getInOrgId());
			String headid = movePackDao.saveMovePackHead(packHead, userid);
			packHead.setHeadid(headid);
		} else {
			asertStatus("jat_move_pack_head", "headid", packHead.getHeadid(), "status", DictConstant.BILL_STATUS_SAVE);
			if(DictConstant.MOVE_PACK_BILL_TYPE_SHIPING.equals(packHead.getBillType())){
				movePackDao.updateMoveBillPackNo(packHead.getHeadid(), packHead.getJmFlag(), null);
			}else if(DictConstant.MOVE_PACK_BILL_TYPE_JIAOJIE.equals(packHead.getBillType())){
				//交接装箱单删除，清除快递单号
				handoverDao.updatePackNoFromMovePack(packHead.getHeadid(), null, null);
			}else if(DictConstant.MOVE_PACK_BILL_TYPE_XINGTAI.equals(packHead.getBillType())){
				//形态转换装箱单删除，清除快递单号
				procChangeHeadDao.updatePackNoFromMovePack(packHead.getHeadid(), null, null);
			}else if(DictConstant.MOVE_PACK_BILL_TYPE_WEIXIU.equals(packHead.getBillType())){
				//维修件装箱单删除，清除快递单号
				movePackDao.updateFixBillPackNo(packHead.getHeadid(), DictConstant.PLAN_BILL_STATUS_MARKED, null);
			}
			
			movePackDao.updateMovePackHead(packHead, userid);
			movePackDao.deleteMovePackLineByBillId(deleteBillIdList, packHead.getHeadid(), userid);
		}
		movePackDao.saveMovePackLine(newLineList, packHead.getHeadid(), userid);
		if(DictConstant.MOVE_PACK_BILL_TYPE_SHIPING.equals(packHead.getBillType())){
			movePackDao.updateMoveBillPackNo(packHead.getHeadid(), packHead.getJmFlag(), packHead.getBillno());
		}else if(DictConstant.MOVE_PACK_BILL_TYPE_JIAOJIE.equals(packHead.getBillType())){
			//交接装箱单审核，回写快递单号
			handoverDao.updatePackNoFromMovePack(packHead.getHeadid(), packHead.getBillno(), packHead.getExpressNo());
		}else if(DictConstant.MOVE_PACK_BILL_TYPE_XINGTAI.equals(packHead.getBillType())){
			//形态转换装箱单审核，回写快递单号
			procChangeHeadDao.updatePackNoFromMovePack(packHead.getHeadid(), packHead.getBillno(), packHead.getExpressNo());
		}else if(DictConstant.MOVE_PACK_BILL_TYPE_WEIXIU.equals(packHead.getBillType())){
			//维修件装箱单审核，回写快递单号
			movePackDao.updateFixBillPackNo(packHead.getHeadid(), DictConstant.PLAN_BILL_STATUS_PACKED, packHead.getExpressNo());
		}
		if(DictConstant.BILL_STATUS_REVIEWING.equals(packHead.getStatus())){
			workflowService.enterReview(packHead, userid);
		}
	}
	/**
	 * 删除装箱单
	 * @param netIdList
	 * @return
	 */
	public void deleteMovePack(String headid, String userid) {
		asertStatus("jat_move_pack_head", "headid", headid, "status", DictConstant.BILL_STATUS_SAVE);
		MovePackHead packHead = movePackDao.getMovePackHead(headid);
		if(DictConstant.MOVE_PACK_BILL_TYPE_SHIPING.equals(packHead.getBillType())){
			movePackDao.updateMoveBillPackNo(packHead.getHeadid(), packHead.getJmFlag(), null);
		}else if(DictConstant.MOVE_PACK_BILL_TYPE_JIAOJIE.equals(packHead.getBillType())){
			//交接装箱单删除，清除快递单号
			handoverDao.updatePackNoFromMovePack(packHead.getHeadid(), null, null);
		}else if(DictConstant.MOVE_PACK_BILL_TYPE_XINGTAI.equals(packHead.getBillType())){
			//形态转换装箱单删除，清除快递单号
			procChangeHeadDao.updatePackNoFromMovePack(packHead.getHeadid(), null, null);
		}else if(DictConstant.MOVE_PACK_BILL_TYPE_WEIXIU.equals(packHead.getBillType())){
			//维修件装箱单删除，清除快递单号
			movePackDao.updateFixBillPackNo(packHead.getHeadid(), DictConstant.PLAN_BILL_STATUS_MARKED, null);
		}
		movePackDao.deleteMovePackLine(headid);
		movePackDao.deleteMovePackHead(headid);
	}
	/**
	 * 获取装箱单行
	 * @param headid
	 * @return
	 */
	public List<MovePackLine> getMovePackLine(String headid) {
		return movePackDao.getMovePackLine(headid);
	}
	/**
	 * 获取装箱单头
	 * @param headid
	 * @return
	 */
	public MovePackHead getMovePackHead(String headid){
		return movePackDao.getMovePackHead(headid);
	}
	/**
	 * 检查单据编号是否已经存在
	 * @param billType
	 * @param headid
	 * @param billno
	 */
	public void checkBillExists(String billType, String jmFlag, String headid, String billno){
		List<String> packNoList = movePackDao.getPackNoByBillno(billType, jmFlag, headid, billno);
		if(null != packNoList && packNoList.size()>0){
			throw new RuntimeException("装箱单:" + packNoList.toString()+"已经存在此单据["+billno+"]");
		}
	}
	/**
	 * 接收装箱单
	 * @param headid
	 * @param userid
	 */
	public void receiveMovePack(String headid, String userid){
		asertStatus("jat_move_pack_head", "headid", headid, "status", DictConstant.BILL_STATUS_REVIEWED);
		movePackDao.receiveMovePack(headid, userid);
		MovePackHead head = movePackDao.getMovePackHead(headid);
		if(DictConstant.MOVE_PACK_BILL_TYPE_SHIPING.equals(head.getBillType())){
			moveBillDao.receiveMoveBillByPackId(headid, head.getJmFlag(), userid);
		}else if(DictConstant.MOVE_PACK_BILL_TYPE_WEIXIU.equals(head.getBillType())){
			//维修件装箱单接收，状态改为装箱单已接收
			movePackDao.updateFixBillPackNo(head.getHeadid(), DictConstant.PLAN_BILL_STATUS_PACK_RECEIVED, head.getExpressNo());
		}
	}
	/**
	 * 修改可接收人
	 * @param headid
	 * @param receUserIds
	 * @param sessionUserId
	 */
	public void updateReceUserIds(String headid, String receUserIds, String useid){
		asertStatus("jat_move_pack_head", "headid", headid, "status", DictConstant.BILL_STATUS_REVIEWED);
		movePackDao.updateReceUserIds(headid, receUserIds, useid);
	}
	public void updateExpress(String headid, String expressCharge,
			String expressPremium, String expressMoney){
		movePackDao.updateExpress(headid, expressCharge, expressPremium, expressMoney);
	}
}