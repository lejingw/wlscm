package com.jatools.web.dwr.stock;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.StockConstant;
import com.jatools.manager.stock.DispatchOrnaManager;
import com.jatools.vo.bd.ItemClass;
import com.jatools.vo.bd.Org;
import com.jatools.vo.bd.OrnaClass;
import com.jatools.vo.move.MoveBillHead;
import com.jatools.vo.stock.DispatchOrnaInfo;
import com.jatools.vo.stock.DispatchOrnaLog;
import com.jatools.vo.stock.DispatchTemp;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class DispatchOrnaDwr {
	private static Logger logger = Logger.getLogger(DispatchOrnaDwr.class);
	private byte[] lock = new byte[0];
	private byte[] lock2 = new byte[0];
	private DispatchOrnaManager dispatchOrnaManager;
	
	public void setDispatchOrnaManager(DispatchOrnaManager dispatchOrnaManager) {
		this.dispatchOrnaManager = dispatchOrnaManager;
	}

	/**
	 * 获取总部总单行中的所有大类
	 * 供生成配货参数和总部总单查询使用
	 * @param gheadid
	 * @return
	 */
	public List<SelectorOption> getGatherLineItemClassForSlt(String gheadid){
		return dispatchOrnaManager.getGatherLineItemClassForSlt(gheadid);
	}
	
	/**
	 * 根据大类
	 * 获取总部总单行中的所有小类
	 * 供生成配货参数和总部总单查询使用
	 * @param gheadid
	 * @return
	 */
	public List<SelectorOption> getGatherLineOrnaClassForSlt(String gheadid, String itemClassId){
		if(StringUtil.isNotEmpty(itemClassId))
			return dispatchOrnaManager.getGatherLineOrnaClassForSlt(gheadid, itemClassId);
		return null;
	}
	/**
	 * 获取总部总单的状态
	 * @param gheadid
	 */
	public String getHeadquarterStatus(String gheadid){
		return dispatchOrnaManager.getHeadquarterStatus(gheadid);
	}
	public String checkDispatchStatus(String gheadid){
		String status = dispatchOrnaManager.getHeadquarterStatus(gheadid);
		if(DictConstant.PLAN_BILL_STATUS_GATHERED.equals(status) || DictConstant.PLAN_BILL_STATUS_PURCHASED.equals(status)){
			return null;
		}else{
			if(DictConstant.PLAN_BILL_STATUS_CLOSED.equals(status)){
				return "总部总单已经关闭不能生成配货参数";
			}
			return "只有已汇总和已生成采购单状态的总部总单才可以生成配货参数";
		}
	}
	/**
	 * 检查配货参数是否已经存在
	 * @param gheadid
	 * @param itemClassId
	 * @param ornaClassId
	 * @return
	 */
	public String checkDispatchCondition(String gheadid, String itemClassId, String ornaClassId){
		String status = dispatchOrnaManager.getHeadquarterStatus(gheadid);
		if(DictConstant.PLAN_BILL_STATUS_GATHERED.equals(status) || DictConstant.PLAN_BILL_STATUS_PURCHASED.equals(status)){
			boolean flag = dispatchOrnaManager.checkDispatchCondition(gheadid, itemClassId, ornaClassId);
			if(!flag){
				return "该配货参数已经被使用";
			}
		}else{
			if(DictConstant.PLAN_BILL_STATUS_CLOSED.equals(status)){
				return "总部总单已经关闭不能生成配货参数";
			}
			return "只有已汇总和已生成采购单状态的总部总单才可以生成配货参数";
		}
		return null;
	}
	/**
	 * 创建配货参数
	 * @param gheadid
	 * @param itemClassId
	 * @param ornaClassId
	 * @return
	 */
	public String saveDispatchCondition(String gheadid, String itemClassId, String ornaClassId, HttpSession session){
		try {
			String userid = CommonUtil.getSessionUserId(session);
			checkDispatchCondition(gheadid, itemClassId, ornaClassId);
			//创建配货参数，并生成配货临时数据
			return dispatchOrnaManager.saveDispatchCondition(gheadid, itemClassId, ornaClassId, userid);
		} catch (Exception e) {
			logger.error(e);
			return "创建配货参数出错";
		}
	}
	/**
	 * 配货
	 * @param code
	 * @param ornaFlag
	 */
	public DispatchOrnaInfo dispatchOrna(String conditionId, String code, boolean ornaFlag, String inOrgId, boolean styleFlag, String styleFlag2, boolean colorGradeFlag, boolean cleanFlag, HttpSession session){
		//先到减库临时表中获取饰品信息，关联现有量表，取有效状态的数据
		DispatchOrnaInfo info = dispatchOrnaManager.getSubtempDispatchOrnaInfo(conditionId, code, ornaFlag);
		//若未找到，则到现有量表中查找
		if(null == info){
			info = dispatchOrnaManager.getDispatchOrnaInfo(code, ornaFlag);
		}
		if(null == info){
			throw new RuntimeException("不能获取饰品信息");
		}
		String currentOrgId = CommonUtil.getSessionOrgId(session);
		if(!currentOrgId.equals(info.getOrgId())){
			throw new RuntimeException("饰品所在网点不为当前用户的登录组织");
		}
		synchronized (lock) {
			DispatchOrnaLog dispatchOrnaLog = dispatchOrnaManager.getDispatchOrnaLogByOrnaCode(info.getOrnaCode());//取状态为正配货1、已配货2、已调拨3的
			//没有配货记录，则进行配货
			if(null == dispatchOrnaLog){
				//先检查饰品状态，可能是已经配过货，但是还在调拨的，此时饰品状态不为有效
				checkMaterActiveStatus(info);
				DispatchTemp dispatchTemp = dispatchOrnaManager.getMatchDispatchTemp(info, styleFlag, styleFlag2, colorGradeFlag, cleanFlag, inOrgId, conditionId);
				if(null == dispatchTemp){
					info.setDispatchModel("3");// 1已配货、2配货成功、3配货未成功
				}else{
					dispatchOrnaLog = createDispatchOrnaLog(dispatchTemp, info);//创建配货记录
					dispatchOrnaLog.setIsPerfectMatch(styleFlag&&colorGradeFlag&&cleanFlag?"1":"0");//是否模糊匹配
					dispatchOrnaLog.setMatchRuleDsc(getMatchRuleDsc(styleFlag, styleFlag2, colorGradeFlag, cleanFlag));//取消匹配规则内容描述
					//保存配货记录
					String ornaLogId = dispatchOrnaManager.saveDispatchOrnaLog(dispatchOrnaLog);
					info.setOrnaLogId(ornaLogId);
					info.setOrderLineId(dispatchOrnaLog.getOrderLineId());
					info.setOrgId(dispatchTemp.getOrgId());
					info.setDispatchModel("2");// 1已配货、2配货成功、3配货未成功
				}
			}else{
				info.setDispatchModel("1");// 1已配货、2配货成功、3配货未成功
				info.setOrnaLogId(dispatchOrnaLog.getId());
				info.setOrderLineId(dispatchOrnaLog.getOrderLineId());
				//显示饰品信息，以及分配到哪个店
				info.setOrgId(dispatchOrnaLog.getOrgId());
				//在前台，更别时用户判断是否在当前总单内
				info.setGatherHeadId(dispatchOrnaLog.getGatherHeadId());
			}
		}
		info.setNameByCache();
		return info;
	}
	
	private String getMatchRuleDsc(boolean styleFlag, String styleFlag2, boolean colorGradeFlag, boolean cleanFlag) {
		String tmp = "";
		if(!styleFlag){
			tmp += ("1".equals(styleFlag2)?"款式大类":("2".equals(styleFlag2)?"款式中类":("3".equals(styleFlag2)?"款式小类":"款式不作")))+"匹配;";
		}
		if(!colorGradeFlag){
			tmp += "色级不做匹配;";
		}
		if(!cleanFlag){
			tmp += "净度不做匹配;";
		}
		return tmp;
	}

	private DispatchOrnaLog createDispatchOrnaLog(DispatchTemp dispatchTemp, DispatchOrnaInfo info){
		DispatchOrnaLog dispatchOrnaLog = new DispatchOrnaLog();
		dispatchOrnaLog.setOrnaCode(info.getOrnaCode());
		dispatchOrnaLog.setGatherHeadId(dispatchTemp.getGheadid());
		dispatchOrnaLog.setGatherLineId(dispatchTemp.getGlineid());
		dispatchOrnaLog.setOrderHeadId(dispatchTemp.getOheadid());
		dispatchOrnaLog.setOrderLineId(dispatchTemp.getOlineid());
		dispatchOrnaLog.setOrgId(dispatchTemp.getOrgId());
		dispatchOrnaLog.setItemClassId(info.getItemClassId());
		dispatchOrnaLog.setOrnaClassId(info.getOrnaClassId());
		dispatchOrnaLog.setCreateDate(DateUtil.getCurrentDate18());
		dispatchOrnaLog.setCreateId(dispatchTemp.getUserId());
		dispatchOrnaLog.setUpdateDate(DateUtil.getCurrentDate18());
		dispatchOrnaLog.setUpdateId(dispatchTemp.getUserId());
		dispatchOrnaLog.setStatus(DictConstant.BILL_STATUS_SAVE);
		//---其他信息
		dispatchOrnaLog.setOrnaDsc(info.getOrnaDsc());
		dispatchOrnaLog.setUnitId(info.getUnitId());
		dispatchOrnaLog.setStyleId(info.getStyleId());
		dispatchOrnaLog.setStyleName(info.getStyleName());
		dispatchOrnaLog.setColorGradeId(info.getColorGradeId());
		dispatchOrnaLog.setCleanId(info.getCleanId());
		dispatchOrnaLog.setMainWeight(info.getMainWeight());
		dispatchOrnaLog.setPartWeight(info.getPartWeight());
		dispatchOrnaLog.setSizeId(info.getSizeId());
		dispatchOrnaLog.setSizeName(info.getSizeName());
		dispatchOrnaLog.setBasicPrice(info.getBasicPrice());
		dispatchOrnaLog.setPosAmount(info.getPosAmount());
		dispatchOrnaLog.setNowQty(info.getNowQty());
		dispatchOrnaLog.setAllQty(info.getAllQty());
		dispatchOrnaLog.setRealTotalCost(info.getRealTotalCost());

		dispatchOrnaLog.setAnalysisId(dispatchTemp.getAnalyseId());
		dispatchOrnaLog.setAnalysisName(dispatchTemp.getAnalyseName());
		dispatchOrnaLog.setQualityId(dispatchTemp.getQualityId());
		dispatchOrnaLog.setBracketcolorId(dispatchTemp.getBracketcolorId());
		
		//若是在购物结束时间前配货的,则给配货数量加1,否则给过时配货数量加1
		if(StringUtil.isEmpty(dispatchTemp.getOrderEndDate()) || DateUtil.getCurrentDate10().compareTo(dispatchTemp.getOrderEndDate())<=0){
			dispatchOrnaLog.setOldFlag(StockConstant.DISPATCH_ORNA_OLDFLAG_NEW);
		}else{
			dispatchOrnaLog.setOldFlag(StockConstant.DISPATCH_ORNA_OLDFLAG_OLD);
		}
		if(null != info.getSubtempId()){//如果包含减库临时表记录id信息，则表示减库配货，否则表正常配货
			dispatchOrnaLog.setSubtempId(info.getSubtempId());
			dispatchOrnaLog.setDispatchFlag(StockConstant.DISPATCH_FLAG_SUB_TEMP);//减库配货
		}else{
			dispatchOrnaLog.setDispatchFlag(StockConstant.DISPATCH_FLAG_DISPATCH);//正常配货
		}
		return dispatchOrnaLog;
	}

	/**
	 * 检查饰品是否可以配货
	 * @param materActive
	 */
	private void checkMaterActiveStatus(DispatchOrnaInfo info){
		if(DictConstant.BILL_STATUS_MATER_ACTIVE_USED.equals(info.getStatus())){
			throw new RuntimeException("该饰品为保留状态，不能配货");
		}
		if(DictConstant.BILL_STATUS_MATER_ACTIVE_ONWAY.equals(info.getStatus())){
			throw new RuntimeException("该饰品为在途状态，不能配货");
		}
		if(!DictConstant.BILL_STATUS_MATER_ACTIVE_VALID.equals(info.getStatus())){
			throw new RuntimeException("该饰品不为有效状态，不能配货");
		}
		if(DictConstant.YES_OR_NO_YES.equals(info.getMaterialFlag())){
			throw new RuntimeException("该饰品为备货，不能配货");
		}
		if(!DictConstant.INVCODE_ORNA.equals(info.getStockId())){
			throw new RuntimeException("该饰品仓库不为饰品库，不能配货");
		}
	}
	
	/**
	 * 获取正配货分配店
	 * @param conditionId
	 * @return
	 */
	public List<SelectorOption> getDispatchingOrgForSlt(String conditionId){
		List<Org> orgList = dispatchOrnaManager.getDispatchOrg(conditionId);
		if(null == orgList || orgList.size()<1){
			return null;
		}
		List<SelectorOption> list = new ArrayList<SelectorOption>();
		for(Org org : orgList){
			list.add(new SelectorOption(org.getOrgId(), org.getOrgName()));
		}
		return list;
	}
	/**
	 * 获取已配货分配店
	 * @param conditionId
	 * @return
	 */
	public List<SelectorOption> getDispatchedOrgForSlt(String gheadid){
		if(StringUtil.isEmpty(gheadid)){
			return null;
		}
		List<Org> orgList = dispatchOrnaManager.getPurchaseGatherOrg(gheadid);
		if(null == orgList || orgList.size()<1){
			return null;
		}
		List<SelectorOption> list = new ArrayList<SelectorOption>();
		for(Org org : orgList){
			list.add(new SelectorOption(org.getOrgId(), org.getOrgName()));
		}
		return list;
	}
	/**
	 * 获取已配货大类
	 * @param conditionId
	 * @return
	 */
	public List<SelectorOption> getDispatchedItemClassForSlt(String gheadid, String orgId){
		if(StringUtil.isEmpty(gheadid)){
			return null;
		}
		List<ItemClass> itemClassList = dispatchOrnaManager.getPurchaseGatherItemClass(gheadid, orgId);
		if(null == itemClassList || itemClassList.size()<1){
			return null;
		}
		List<SelectorOption> list = new ArrayList<SelectorOption>();
		for(ItemClass org : itemClassList){
			list.add(new SelectorOption(org.getItemClassId(), org.getItemClassDesc()));
		}
		return list;
	}
	/**
	 * 获取已配货小类
	 * @param conditionId
	 * @return
	 */
	public List<SelectorOption> getDispatchedOrnaClassForSlt(String gheadid, String orgId, String itemClassId){
		if(StringUtil.isEmpty(gheadid)){
			return null;
		}
		List<OrnaClass> itemClassList = dispatchOrnaManager.getPurchaseGatherOrnaClass(gheadid, orgId, itemClassId);
		if(null == itemClassList || itemClassList.size()<1){
			return null;
		}
		List<SelectorOption> list = new ArrayList<SelectorOption>();
		for(OrnaClass org : itemClassList){
			list.add(new SelectorOption(org.getOrnaClassId(), org.getOrnaClassDesc()));
		}
		return list;
	}
	/**
	 * 获取总部总单大类
	 * @param gheadid
	 * @return
	 */
	public List<SelectorOption> getHqlineItemClassForSlt(String gheadid){
		if(StringUtil.isEmpty(gheadid)){
			return null;
		}
		List<ItemClass> itemClassList = dispatchOrnaManager.getHqlineItemClass(gheadid);
		if(null == itemClassList || itemClassList.size()<1){
			return null;
		}
		List<SelectorOption> list = new ArrayList<SelectorOption>();
		for(ItemClass org : itemClassList){
			list.add(new SelectorOption(org.getItemClassId(), org.getItemClassDesc()));
		}
		return list;
	}
	/**
	 * 获取总部总单小类
	 * @param conditionId
	 * @return
	 */
	public List<SelectorOption> getHqlineOrnaClassForSlt(String gheadid, String itemClassId){
		if(StringUtil.isEmpty(gheadid)){
			return null;
		}
		List<OrnaClass> itemClassList = dispatchOrnaManager.getHqlineOrnaClass(gheadid, itemClassId);
		if(null == itemClassList || itemClassList.size()<1){
			return null;
		}
		List<SelectorOption> list = new ArrayList<SelectorOption>();
		for(OrnaClass org : itemClassList){
			list.add(new SelectorOption(org.getOrnaClassId(), org.getOrnaClassDesc()));
		}
		return list;
	}
	/**
	 * 删除配货记录
	 * @param logId
	 * @return
	 */
	public String deleteDispatchLog(String ornaLogId, HttpSession session){
		String userid = CommonUtil.getSessionUserId(session);
		dispatchOrnaManager.deleteDispatchOrnaLog(ornaLogId, userid);
		return null;
	}
	/**
	 * 获取正配数量
	 */
	public int getDispatchingNum(String orderLineId){
		return dispatchOrnaManager.getDispatchingNum(orderLineId);
	}
	/**
	 * 获取已配数量
	 */
	public int getDispatchedNum(String useOrderLineIdFlag, String lineId){
		return dispatchOrnaManager.getDispatchedNum(useOrderLineIdFlag, lineId);
	}
	/**
	 * 记账
	 * @param gheadid
	 * @param conditionId
	 */
	public void markBill(String conditionId, HttpSession session){
		String userid = CommonUtil.getSessionUserId(session);
		dispatchOrnaManager.markBill(conditionId, userid);
	}
	/**
	 * 正配货变更
	 * @param ornaLogId
	 * @param orderLineId
	 * @param session
	 * @return
	 */
	public String exchangeDispatching(String ornaLogId, String orderLineId, HttpSession session){
		try {
			String userid = CommonUtil.getSessionUserId(session);
			dispatchOrnaManager.exchangeDispatching(ornaLogId, orderLineId, userid);
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
		return null;
	}
	/**
	 * 生成调拨单获取大类
	 * @param gheadid
	 * @return
	 */
	public List<SelectorOption> getMoveItemClassForSlt(String gheadid){
		if(StringUtil.isEmpty(gheadid)){
			return null;
		}
		List<ItemClass> itemClassList = dispatchOrnaManager.getMoveItemClass(gheadid);
		if(null == itemClassList || itemClassList.size()<1){
			return null;
		}
		List<SelectorOption> list = new ArrayList<SelectorOption>();
		for(ItemClass org : itemClassList){
			list.add(new SelectorOption(org.getItemClassId(), org.getItemClassDesc()));
		}
		return list;
	}
	/**
	 * 生成调拨单获取小类
	 * @param gheadid
	 * @return
	 */
	public List<SelectorOption> getMoveOrnaClassForSlt(String gheadid){
		if(StringUtil.isEmpty(gheadid)){
			return null;
		}
		List<OrnaClass> itemClassList = dispatchOrnaManager.getMoveOrnaClass(gheadid);
		if(null == itemClassList || itemClassList.size()<1){
			return null;
		}
		List<SelectorOption> list = new ArrayList<SelectorOption>();
		for(OrnaClass org : itemClassList){
			list.add(new SelectorOption(org.getOrnaClassId(), org.getOrnaClassDesc()));
		}
		return list;
	}
	/**
	 * 获取调拨单分配店
	 * @param gheadid
	 * @return
	 */
	public List<SelectorOption> getMoveOrgForSlt(String gheadid){
		if(StringUtil.isEmpty(gheadid)){
			return null;
		}
		return dispatchOrnaManager.getMoveOrgForSlt(gheadid);
	}
	/**
	 * 获取调拨单配货员
	 * @param gheadid
	 * @return
	 */
	public List<SelectorOption> getMoveDispatchUserForSlt(String gheadid){
		if(StringUtil.isEmpty(gheadid)){
			return null;
		}
		return dispatchOrnaManager.getMoveDispatchUserForSlt(gheadid);
	}
	/**
	 * 减库生成采购单(明细)
	 * @return
	 */
	public String subPurchase(String gheadid, HttpSession session){
		String userid = CommonUtil.getSessionUserId(session);
		dispatchOrnaManager.subPurchase(gheadid, userid);
		return null;
	}
	/**
	 * 手工减库生成采购单(明细)
	 * @return
	 */
	public String subPurchase2(String gheadid, HttpSession session){
		String userid = CommonUtil.getSessionUserId(session);
		dispatchOrnaManager.subPurchase2(gheadid, userid);
		return null;
	}
	/**
	 * 不减库生成采购单
	 * （明细和非明细均可）
	 * @return
	 */
	public String unsubPurchase(String gheadid, HttpSession session){
		String userid = CommonUtil.getSessionUserId(session);
		dispatchOrnaManager.unsubPurchase(gheadid, userid);
		//根据总部总单生成采购总单头，保存采购总单行
		//生成采购单头、采购单行
		return null;
	}
	/**
	 * 生成调拨单
	 * @param gheadid
	 * @param orgIds
	 * @param itemClassIds
	 * @param ornaClassIds
	 * @param dispatchUserIds
	 * @param session
	 * @return
	 */
	public List<MoveBillHead> createMoveBill(String gheadid, String orgIds, String itemClassIds, String ornaClassIds, String dispatchUserIds, HttpSession session){
		String userid = CommonUtil.getSessionUserId(session);
		String orgId = CommonUtil.getSessionOrgId(session);
		synchronized (lock2) {
			List<MoveBillHead> moveBillList = dispatchOrnaManager.createMoveBill(gheadid, orgIds, itemClassIds, ornaClassIds, dispatchUserIds, orgId, userid);
			return moveBillList;
		}
	}
	/**
	 * 改总部状态为已到店
	 * @param gheadid
	 * @param session
	 * @return
	 */
	public String updateHqOrnaStatus(String gheadid, HttpSession session){
		int count = dispatchOrnaManager.updateHqOrnaStatus(gheadid, CommonUtil.getSessionUserId(session));
		if(count>0){
			return "已更新总部" + count + "条分配记录的状态为已到店";
		}else{
			return "没有记录可以更新";
		}
	}
	/**
	 * 总部总单 匹配率
	 * @return
	 */
	public String getHqlineDispatchRate(String gheadid, String itemClassId, String ornaClassId, String showUnfullOnly, String showUnperfectMatch){
		String rate = dispatchOrnaManager.getHqlineDispatchRate(gheadid, itemClassId, ornaClassId, showUnfullOnly, showUnperfectMatch);
		if(null == rate){
			rate = "0.00";
		}
		return rate;
	}
	/**
	 * 已配货 匹配率
	 * @return
	 */
	public String getDispatchedDispatchRate(String gheadid, String orgId, String itemClassId, String ornaClassId, String showUnfullOnly, String showUnperfectMatch){
		String rate = dispatchOrnaManager.getDispatchedDispatchRate(gheadid, orgId, itemClassId, ornaClassId, showUnfullOnly, showUnperfectMatch);
		if(null == rate){
			rate = "0.00";
		}
		return rate;
	}
}
