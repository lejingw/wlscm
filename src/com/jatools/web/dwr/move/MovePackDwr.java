package com.jatools.web.dwr.move;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.ParameterConstant;
import com.jatools.manager.common.SelectEmpManager;
import com.jatools.manager.move.MovePackManager;
import com.jatools.vo.move.MovePackHead;
import com.jatools.vo.move.MovePackLine;
import com.jatools.web.cache.DictCache;
import com.jatools.web.cache.ParameterCache;

public class MovePackDwr {
	private static Logger logger = Logger.getLogger(MovePackDwr.class);
	private MovePackManager movePackManager;
	private SelectEmpManager selectEmpManager;

	public void setSelectEmpManager(SelectEmpManager selectEmpManager) {
		this.selectEmpManager = selectEmpManager;
	}

	public void setMovePackManager(MovePackManager movePackManager) {
		this.movePackManager = movePackManager;
	}
	/**
	 * 获取饰品信息
	 * @param code
	 * @param ornaFlag
	 * @param orgId
	 * @param stockId
	 * @return
	 */
	public MovePackLine getMoveBillInfo(String billType, String jmFlag, String headid, String moveNo, String outOrgId, String inOrgId){
		movePackManager.checkBillExists(billType, jmFlag, headid, moveNo);
		MovePackLine line = movePackManager.getMoveBillInfo(moveNo, jmFlag);
		if(null == line){
			throw new RuntimeException("不能获取调拨单信息");
		}
		if(DictConstant.YES_OR_NO_YES.equals(jmFlag)){
			if(!DictConstant.BILL_STATUS_REVIEWED.equals(line.getStatus())){
				throw new RuntimeException("单据状态不为审批完成，不能做加盟装箱单");
			}
		}else{
			if(!DictConstant.BILL_STATUS_REVIEWED.equals(line.getStatus())){
				throw new RuntimeException("调拨单状态不为审批完成");
			}
		}
		if(!outOrgId.equals(line.getOutOrgId())){
			throw new RuntimeException("调拨单调出组织不为当前登录组织");
		}
		if(!inOrgId.equals(line.getInOrgId())){
			throw new RuntimeException("调拨单调入组织不为当前选择的调入组织");
		}
		line.setNames();
		return line;
	}
	/**
	 * 获取维修单信息
	 * @param fixNo
	 * @param outOrgId
	 * @param inOrgId
	 * @return
	 */
	public MovePackLine getFixBillInfo(String billType, String jmFlag, String headid, String fixNo, String outOrgId, String inOrgId){
		movePackManager.checkBillExists(billType, jmFlag, headid, fixNo);
		MovePackLine line = movePackManager.getFixBillInfo(fixNo);
		if(null == line){
			throw new RuntimeException("不能获取维修单信息");
		}
		if(!DictConstant.BILL_STATUS_MARK.equals(line.getStatus())){
			throw new RuntimeException("维修单不为记账状态");
		}
		if(!outOrgId.equals(line.getOutOrgId())){
			throw new RuntimeException("维修单调出组织不为当前登录组织");
		}
		if(!inOrgId.equals(line.getInOrgId())){
			throw new RuntimeException("维修单调入组织不为当前选择的调入组织");
		}
		line.setNames();
		return line;
	}
	/**
	 * 获取交接单信息
	 * @param fixNo
	 * @param outOrgId
	 * @param inOrgId
	 * @return
	 */
	public MovePackLine getHandoverBillInfo(String billType, String jmFlag, String headid, String handoverNo, String outOrgId){
		movePackManager.checkBillExists(billType, jmFlag, headid, handoverNo);
		MovePackLine line = movePackManager.getHandoverBillInfo(handoverNo);
		if(null == line){
			throw new RuntimeException("不能获取交接单信息");
		}
		if(!"1".equals(line.getIsPack())){
			throw new RuntimeException("交接单["+handoverNo+"]是否装箱为“否”，不能装箱");
		}
		if(!DictConstant.BILL_STATUS_REVIEWED.equals(line.getStatus())){
			throw new RuntimeException("交接单状态不为审批完成");
		}
		if(!outOrgId.equals(line.getOutOrgId())){
			throw new RuntimeException("交接单调出组织不为当前登录组织");
		}
		line.setNames();
		return line;
	}
	/**
	 * 形态转换单
	 * @param billType
	 * @param headid
	 * @param procChangeNo
	 * @param outOrgId
	 * @return
	 */
	public MovePackLine getProcChangeBillInfo(String billType, String jmFlag, String headid, String procChangeNo, String outOrgId){
		movePackManager.checkBillExists(billType, jmFlag, headid, procChangeNo);
		MovePackLine line = movePackManager.getProcChangeBillInfo(procChangeNo);
		if(null == line){
			throw new RuntimeException("不能获取形态转换单信息");
		}
		if(!"1".equals(line.getIsPack())){
			throw new RuntimeException("形态转换单["+procChangeNo+"]是否装箱为“否”，不能装箱");
		}
		if(!DictConstant.BILL_STATUS_REVIEWED.equals(line.getStatus())){
			throw new RuntimeException("形态转换单状态不为审批完成");
		}
		if(!outOrgId.equals(line.getOutOrgId())){
			throw new RuntimeException("形态转换单调出组织不为当前登录组织");
		}
		line.setNames();
		return line;
	}
	public String getSumCostLimit(){
		String sumCostLimit = ParameterCache.getInstance().getValue(ParameterConstant.MOVE_PACK_SUMCOST_LIMIT);
		return sumCostLimit;
	}
	/**
	 * 检查单据是否已经被其他装箱单使用
	 * @param newLineList
	 * @return
	 */
	public String checkBillAvail(List<MovePackLine> newLineList, String billType, String jmFlag, String headid, double sumCost){
		if(null != newLineList && newLineList.size()>0){
			List<String> tmpList = movePackManager.checkBillStatusAvail(newLineList, billType, jmFlag, headid);
			if(null!=tmpList && tmpList.size()>0){
				return "以下单据已经被其他装箱单使用：<br>" + tmpList;
			}
			if(DictConstant.MOVE_PACK_BILL_TYPE_SHIPING.equals(billType)){
				List<String> list = movePackManager.checkBillStatusAvail2(newLineList, billType, jmFlag, DictConstant.BILL_STATUS_REVIEWED);
				if(null!=list && list.size()>0){
					return "以下单据状态不为"+DictCache.getInstance().getValue(DictConstant.BILL_STATUS, DictConstant.BILL_STATUS_REVIEWED)+"：<br>" + list;
				}
			}
		}
		double sumCostLimit = Double.parseDouble(ParameterCache.getInstance().getValue(ParameterConstant.MOVE_PACK_SUMCOST_LIMIT));
		if(sumCostLimit<sumCost){
			return "装箱单总成本已经超上限[" + sumCostLimit + "]";
		}
		return null;
	}
	/**
	 * 保存或修改装箱单
	 * @param packHead
	 * @param newBillIdList 添加的行记录
	 * @param deleteBillIdList 删除的行记录
	 * @return
	 */
	public String saveMovePack(MovePackHead packHead, List<MovePackLine> newLineList, List<String> deleteBillIdList, HttpSession session){
		movePackManager.saveMovePack(packHead, newLineList, deleteBillIdList, CommonUtil.getSessionUserId(session));
		return null;
	}
	/**
	 * 删除装箱
	 * @param headid
	 * @return
	 */
	public String deleteMovePack(String headid, HttpSession session){
		movePackManager.deleteMovePack(headid, CommonUtil.getSessionUserId(session));
		return null;
	}
	/**
	 * 接收装箱单
	 * @param headid
	 * @return
	 */
	public String receiveMovePack(String headid, HttpSession session){
		movePackManager.receiveMovePack(headid, CommonUtil.getSessionUserId(session));
		return null;
	}
	
	public String getExpressEmps(String orgId){
		List<Map<String, String>> empList = selectEmpManager.getExpressReceusers(orgId);
		List<String> useridList = new ArrayList<String>();
		List<String> usernameList = new ArrayList<String>();
		for(Map<String, String> emp : empList){
			useridList.add(emp.get("userid"));
			usernameList.add(emp.get("username"));
		}
		JSONObject jo = new JSONObject();
		jo.put("userids", useridList);
		jo.put("usernames", usernameList);
		return jo.toString();
	}
	/**
	 * 修改可接收人
	 * @param headid
	 * @param receUserIds
	 * @param session
	 * @return
	 */
	public String updateReceUserIds(String headid, String receUserIds, HttpSession session){
		movePackManager.updateReceUserIds(headid, receUserIds, CommonUtil.getSessionUserId(session));
		return null;
	}
	/**
	 * 修改快递费
	 */
	public String updateExpress(String headid, String expressCharge, String expressPremium, String expressMoney){
		movePackManager.updateExpress(headid, expressCharge, expressPremium, expressMoney);
		return null;
	}
}
