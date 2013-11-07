package com.jatools.web.dwr.push;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.StockConstant;
import com.jatools.manager.push.PushDispatchManager;
import com.jatools.vo.bd.ItemClass;
import com.jatools.vo.bd.Org;
import com.jatools.vo.bd.OrnaClass;
import com.jatools.vo.move.MoveBillHead;
import com.jatools.vo.stock.DispatchCondition;
import com.jatools.vo.stock.DispatchOrnaInfo;
import com.jatools.vo.stock.DispatchOrnaLog;
import com.jatools.vo.stock.PushDispatchTemp;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class PushDispatchDwr {
	private byte[] lock = new byte[0];
	private byte[] lock2 = new byte[0];
	private PushDispatchManager pushDispatchManager;

	public void setPushDispatchManager(PushDispatchManager pushDispatchManager) {
		this.pushDispatchManager = pushDispatchManager;
	}

	/**
	 * 减库生成采购单(明细)
	 * @return
	 */
	public String subPurchase(String gheadid, HttpSession session){
		String userid = CommonUtil.getSessionUserId(session);
		try {
			pushDispatchManager.subPurchase(gheadid, userid);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	/**
	 * 手工减库生成采购单(明细)
	 * @return
	 */
	public String subPurchase2(String gheadid, HttpSession session){
		String userid = CommonUtil.getSessionUserId(session);
		pushDispatchManager.subPurchase2(gheadid, userid);
		return null;
	}
	/**
	 * 不减库生成采购单
	 * （明细和非明细均可）
	 * @return
	 */
	public String unsubPurchase(String gheadid, HttpSession session){
		String userid = CommonUtil.getSessionUserId(session);
		pushDispatchManager.unsubPurchase(gheadid, userid);
		//根据总部总单生成采购总单头，保存采购总单行
		//生成采购单头、采购单行
		return null;
	}
	
	public List<DispatchCondition> getConditionLine(String headid){
		List<DispatchCondition> list = pushDispatchManager.getConditionLine(headid);
		return list;
	}

	/**
	 * 获取总部总单行中的所有大类
	 * 供生成配货参数和总部总单查询使用
	 * @param gheadid
	 * @return
	 */
	public List<SelectorOption> getGatherLineItemClassForSlt(String gheadid){
		return pushDispatchManager.getGatherLineItemClassForSlt(gheadid);
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
			return pushDispatchManager.getGatherLineOrnaClassForSlt(gheadid, itemClassId);
		return null;
	}
	/**
	 * 获取总部总单的状态
	 * @param gheadid
	 */
	public String getHeadquarterStatus(String gheadid){
		return pushDispatchManager.getHeadquarterStatus(gheadid);
	}
	public String checkDispatchStatus(String gheadid){
		String status = pushDispatchManager.getHeadquarterStatus(gheadid);
		if(DictConstant.BILL_STATUS_CREATE_GATHER_ORDER.equals(status) || DictConstant.BILL_STATUS_CREATE_PURCHASE.equals(status)){
			return null;
		}else{
			if(DictConstant.BILL_STATUS_CLOSED.equals(status)){
				return "总部总单已经关闭不能生成配货参数";
			}
			return "只有已生成总单和已生成采购单状态的总部总单才可以生成配货参数";
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
		String status = pushDispatchManager.getHeadquarterStatus(gheadid);
		if(DictConstant.BILL_STATUS_CREATE_GATHER_ORDER.equals(status) || DictConstant.BILL_STATUS_CREATE_PURCHASE.equals(status)){
			boolean flag = pushDispatchManager.checkDispatchCondition(gheadid, itemClassId, ornaClassId);
			if(!flag){
				return "该配货参数已经被使用";
			}
		}else{
			if(DictConstant.BILL_STATUS_CLOSED.equals(status)){
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
			return pushDispatchManager.saveDispatchCondition(gheadid, itemClassId, ornaClassId, userid);
		} catch (Exception e) {
			e.printStackTrace();
			return "创建配货参数出错";
		}
	}
	/**
	 * 配货
	 * @param code
	 * @param ornaFlag
	 */
	public DispatchOrnaInfo dispatchOrna(String conditionId, String code, boolean ornaFlag, String inOrgId, String isLoveStyle, HttpSession session){
        DispatchOrnaInfo info = null;
        if("1".equals(isLoveStyle)) { // 情侣类 不需要处理减库饰品
            info = pushDispatchManager.getDispatchOrnaInfo(code, ornaFlag);
        } else {
            //先到减库临时表中获取饰品信息，关联现有量表，取有效状态的数据
            info = pushDispatchManager.getSubtempDispatchOrnaInfo(conditionId, code, ornaFlag);
            //若未找到，则到现有量表中查找
            if(null == info){
                info = pushDispatchManager.getDispatchOrnaInfo(code, ornaFlag);
            }
        }
		if(null == info){
			throw new RuntimeException("不能获取饰品信息");
		}
		String currentOrgId = CommonUtil.getSessionOrgId(session);
		if(!currentOrgId.equals(info.getOrgId())){
			throw new RuntimeException("饰品所在网点不为当前用户的登录组织");
		}
		synchronized (lock) {
			DispatchOrnaLog dispatchOrnaLog = pushDispatchManager.getDispatchOrnaLogByOrnaCode(info.getOrnaCode());//取状态为正配货1、已配货2、已调拨3的
			//没有配货记录，则进行配货
			if(null == dispatchOrnaLog){
				//先检查饰品状态，可能是已经配过货，但是还在调拨的，此时饰品状态不为有效
				checkMaterActiveStatus(info);
				DispatchCondition cond = pushDispatchManager.getDispatchConditionById(conditionId);
				if(!cond.getItemClassId().equals(info.getItemClassId())){
					throw new RuntimeException("大类["+ItemClassCache.getInstance().getNameById(info.getItemClassId())+"]不为当前配货参数设置，不能配货");
				}
				if(!cond.getOrnaClassId().equals(info.getOrnaClassId())){
					throw new RuntimeException("小类["+OrnaClassCache.getInstance().getNameById(info.getOrnaClassId())+"]不为当前配货参数设置，不能配货");
				}
				PushDispatchTemp dispatchTemp = pushDispatchManager.getMatchDispatchTemp(info, inOrgId, conditionId);
                if(null == dispatchTemp) {
                    throw new RuntimeException("未找到匹配的门店组织");
                }
				String userid = CommonUtil.getSessionUserId(session);
				dispatchOrnaLog = createDispatchOrnaLog(dispatchTemp, info, userid);//创建配货记录
				//保存配货记录
				String ornaLogId = pushDispatchManager.saveDispatchOrnaLog(dispatchOrnaLog);
				info.setDispatchModel("2");//2配货成功
				info.setOrnaLogId(ornaLogId);
				//显示饰品信息，以及分配到哪个店
				info.setOrgId(dispatchTemp.getOrgId());
			}else{
				info.setDispatchModel("1");//1已配货
				info.setOrnaLogId(dispatchOrnaLog.getId());
				//显示饰品信息，以及分配到哪个店
				info.setOrgId(dispatchOrnaLog.getOrgId());
				//在前台，更别时用户判断是否在当前总单内
				info.setGatherHeadId(dispatchOrnaLog.getGatherHeadId());
			}
		}
		info.setNameByCache();
		return info;
	}
	private DispatchOrnaLog createDispatchOrnaLog(PushDispatchTemp dispatchTemp, DispatchOrnaInfo info, String userid){
		DispatchOrnaLog dispatchOrnaLog = new DispatchOrnaLog();
		dispatchOrnaLog.setOrnaCode(info.getOrnaCode());
		dispatchOrnaLog.setGatherHeadId(dispatchTemp.getGheadid());
		dispatchOrnaLog.setGatherLineId(dispatchTemp.getGlineid());
//		dispatchOrnaLog.setOrderHeadId(dispatchTemp.getOheadid());
//		dispatchOrnaLog.setOrderLineId(dispatchTemp.getOlineid());
		dispatchOrnaLog.setOrgId(dispatchTemp.getOrgId());
		dispatchOrnaLog.setItemClassId(info.getItemClassId());
		dispatchOrnaLog.setOrnaClassId(info.getOrnaClassId());
		dispatchOrnaLog.setCreateDate(DateUtil.getCurrentDate18());
		dispatchOrnaLog.setCreateId(userid);
		dispatchOrnaLog.setUpdateDate(DateUtil.getCurrentDate18());
		dispatchOrnaLog.setUpdateId(userid);
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

		dispatchOrnaLog.setAnalysisId(info.getAnalyseId());
		dispatchOrnaLog.setAnalysisName(info.getAnalyseName());
		dispatchOrnaLog.setQualityId(info.getQualityId());
		dispatchOrnaLog.setBracketcolorId(info.getBracketcolorId());
		
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
        dispatchOrnaLog.setIsPerfectMatch("1");
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
		List<Org> orgList = pushDispatchManager.getDispatchingOrg(conditionId);
		List<SelectorOption> list = new ArrayList<SelectorOption>();
		if(null != orgList && orgList.size()>0){
			for(Org org : orgList){
				list.add(new SelectorOption(org.getOrgId(), org.getOrgName()));
			}
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
		List<Org> orgList = pushDispatchManager.getDispatchedOrg(gheadid);
		List<SelectorOption> list = new ArrayList<SelectorOption>();
		if(null != orgList && orgList.size()>0){
			for(Org org : orgList){
				list.add(new SelectorOption(org.getOrgId(), org.getOrgName()));
			}
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
		List<ItemClass> itemClassList = pushDispatchManager.getPurchaseGatherItemClass(gheadid, orgId);
		List<SelectorOption> list = new ArrayList<SelectorOption>();
		if(null != itemClassList && itemClassList.size()>0){
			for(ItemClass org : itemClassList){
				list.add(new SelectorOption(org.getItemClassId(), org.getItemClassDesc()));
			}
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
		List<OrnaClass> itemClassList = pushDispatchManager.getPurchaseGatherOrnaClass(gheadid, orgId, itemClassId);
		List<SelectorOption> list = new ArrayList<SelectorOption>();
		if(null != itemClassList && itemClassList.size()>0){
			for(OrnaClass org : itemClassList){
				list.add(new SelectorOption(org.getOrnaClassId(), org.getOrnaClassDesc()));
			}
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
		List<ItemClass> itemClassList = pushDispatchManager.getHqlineItemClass(gheadid);
		List<SelectorOption> list = new ArrayList<SelectorOption>();
		if(null != itemClassList && itemClassList.size()>0){
			for(ItemClass org : itemClassList){
				list.add(new SelectorOption(org.getItemClassId(), org.getItemClassDesc()));
			}
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
		List<OrnaClass> itemClassList = pushDispatchManager.getHqlineOrnaClass(gheadid, itemClassId);
		List<SelectorOption> list = new ArrayList<SelectorOption>();
		if(null != itemClassList && itemClassList.size()>0){
			for(OrnaClass org : itemClassList){
				list.add(new SelectorOption(org.getOrnaClassId(), org.getOrnaClassDesc()));
			}
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
		pushDispatchManager.deleteDispatchOrnaLog(ornaLogId, userid);
		return null;
	}
//	/**
//	 * 获取正配数量
//	 */
//	public int getDispatchingNum(String orderLineId){
//		return pushDispatchManager.getDispatchingNum(orderLineId);
//	}
//	/**
//	 * 获取已配数量
//	 */
//	public int getDispatchedNum(String useOrderLineIdFlag, String lineId){
//		return pushDispatchManager.getDispatchedNum(useOrderLineIdFlag, lineId);
//	}
	/**
	 * 记账
	 * @param gheadid
	 * @param conditionId
	 */
	public void markBill(String conditionId, HttpSession session){
		String userid = CommonUtil.getSessionUserId(session);
		pushDispatchManager.markBill(conditionId, userid);
	}
	/**
	 * 正配货变更
	 * @param ornaLogId
	 * @param orderLineId
	 * @param session
	 * @return
	 */
	public String exchangeDispatching(String ornaLogId, String newOrgId, HttpSession session){
		try {
			String userid = CommonUtil.getSessionUserId(session);
			pushDispatchManager.exchangeDispatching(ornaLogId, newOrgId, userid);
		} catch (Exception e) {
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
		List<ItemClass> itemClassList = pushDispatchManager.getMoveItemClass(gheadid);
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
		List<OrnaClass> itemClassList = pushDispatchManager.getMoveOrnaClass(gheadid);
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
		return pushDispatchManager.getMoveOrgForSlt(gheadid);
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
		return pushDispatchManager.getMoveDispatchUserForSlt(gheadid);
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
			List<MoveBillHead> moveBillList = pushDispatchManager.createMoveBill(gheadid, orgIds, itemClassIds, ornaClassIds, dispatchUserIds, orgId, userid);
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
		int count = pushDispatchManager.updateHqOrnaStatus(gheadid, CommonUtil.getSessionUserId(session));
		if(count>0){
			return "已更新总部" + count + "条分配记录的状态为已到店";
		}else{
			return "没有记录可以更新";
		}
	}
//	/**
//	 * 总部总单 匹配率
//	 * @return
//	 */
//	public String getHqlineDispatchRate(String gheadid, String itemClassId, String ornaClassId, String showUnfullOnly, String showUnperfectMatch){
//		String rate = pushDispatchManager.getHqlineDispatchRate(gheadid, itemClassId, ornaClassId, showUnfullOnly, showUnperfectMatch);
//		if(null == rate){
//			rate = "0.00";
//		}
//		return rate;
//	}
//	/**
//	 * 已配货 匹配率
//	 * @return
//	 */
//	public String getDispatchedDispatchRate(String gheadid, String orgId, String itemClassId, String ornaClassId, String showUnfullOnly, String showUnperfectMatch){
//		String rate = pushDispatchManager.getDispatchedDispatchRate(gheadid, orgId, itemClassId, ornaClassId, showUnfullOnly, showUnperfectMatch);
//		if(null == rate){
//			rate = "0.00";
//		}
//		return rate;
//	}
 }
