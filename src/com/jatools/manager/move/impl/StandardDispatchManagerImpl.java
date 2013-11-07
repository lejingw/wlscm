package com.jatools.manager.move.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.dao.move.MoveBillDao;
import com.jatools.dao.move.StandardDispatchDao;
import com.jatools.dao.stock.DispatchOrnaDao;
import com.jatools.dao.stock.MaterActiveDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.move.StandardDispatchManager;
import com.jatools.vo.bd.Org;
import com.jatools.vo.move.MoveBillHead;
import com.jatools.vo.move.StandardDispatch;
import com.jatools.vo.move.StandardDispatchOrna;
import com.jatools.vo.stock.DispatchCondition;
import com.jatools.vo.stock.DispatchOrnaLog;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.util.DateUtil;

public class StandardDispatchManagerImpl extends BaseManager implements
		StandardDispatchManager {
	private CommonDao commonDao;
	private StandardDispatchDao standardDispatchDao;
	private DispatchOrnaDao dispatchOrnaDao;
	private MaterActiveDao materActiveDao;
	private MoveBillDao moveBillDao;

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public void setStandardDispatchDao(StandardDispatchDao standardDispatchDao) {
		this.standardDispatchDao = standardDispatchDao;
	}

	public void setDispatchOrnaDao(DispatchOrnaDao dispatchOrnaDao) {
		this.dispatchOrnaDao = dispatchOrnaDao;
	}

	public void setMaterActiveDao(MaterActiveDao materActiveDao) {
		this.materActiveDao = materActiveDao;
	}

	public void setMoveBillDao(MoveBillDao moveBillDao) {
		this.moveBillDao = moveBillDao;
	}

	public Pager getStandardDispatchPageData(Map<String, String> condition){
		return standardDispatchDao.getStandardDispatchPageData(condition);
	}

	public List<DispatchCondition> getDispatchCondition(String gheadid) {
		return dispatchOrnaDao.getDispatchCondition(gheadid, GlobalConstant.BILL_CODE_STANDARD_DISPATCH);
	}
	public DispatchCondition getDispatchConditionById(String conditionId){
		return dispatchOrnaDao.getDispatchConditionById(conditionId);
	}
	public StandardDispatch getStandardDispatch(String gheadid){
		return standardDispatchDao.getStandardDispatch(gheadid);
	}
	public boolean checkDispatchCondition(String gheadid, String itemClassId, String ornaClassId){
		return dispatchOrnaDao.checkDispatchCondition(gheadid, itemClassId, ornaClassId, GlobalConstant.BILL_CODE_STANDARD_DISPATCH);
	}
	public String[] saveDispatchCondition(String gheadid, String itemClassId, String ornaClassId, String userid){
		if("-1".equals(gheadid)){
			StandardDispatch sd = new StandardDispatch();
			sd.setBillno(commonDao.getBillno(GlobalConstant.BILL_CODE_STANDARD_DISPATCH));
			sd.setCreateDate(DateUtil.getCurrentDate18());
			sd.setCreateId(userid);
			sd.setUpdateId(userid);
			sd.setUpdateDate(DateUtil.getCurrentDate18());
			sd.setStatus(DictConstant.BILL_STATUS_SAVE);
			gheadid = standardDispatchDao.saveStandardDispatch(sd);
		}
		DispatchCondition disCondition = new DispatchCondition();
		disCondition.setCreateDate(DateUtil.getCurrentDate18());
		disCondition.setCreateId(userid);
		disCondition.setGheadid(gheadid);
		disCondition.setItemClassId(itemClassId);
		disCondition.setOrnaClassId(ornaClassId);
		disCondition.setUpdateDate(DateUtil.getCurrentDate18());
		disCondition.setUpdateId(userid);
		disCondition.setStatus(DictConstant.BILL_STATUS_SAVE);
		disCondition.setSrcBillCode(GlobalConstant.BILL_CODE_STANDARD_DISPATCH);
		String conditionId = dispatchOrnaDao.saveDispatchCondition(disCondition);
		return new String[]{gheadid, conditionId};
	}
	public void releaseLock(String conditionId, String userid){
		//删除配货参数
		dispatchOrnaDao.deleteDispatchCondition(conditionId, userid);
	}
	
	public StandardDispatchOrna getStandardDispatchOrnaInfo(String code, boolean ornaFlag){
		return standardDispatchDao.getStandardDispatchOrnaInfo(code, ornaFlag);
	}
	public DispatchOrnaLog getDispatchOrnaLogByOrnaCode(String ornaCode){
		return dispatchOrnaDao.getDispatchOrnaLogByOrnaCode(ornaCode, GlobalConstant.BILL_CODE_STANDARD_DISPATCH);
	}
	public Pager getMatchDispatchOrgPageData(String ornaCode, Map<String, String> condition){
		return standardDispatchDao.getMatchDispatchOrgPageData(ornaCode, condition);
	}
	public void saveDispatchOrnaLog(String gheadid, String ornaCode, String orgId, String dispatchFlag, String userid){
		String ornaLogId = standardDispatchDao.saveDispatchOrnaLog(gheadid, ornaCode, orgId, dispatchFlag, userid);
		//修改饰品现有量状态为901保留
//		materActiveDao.markMaterActiveUsed(ornaCode, GlobalConstant.BILL_CODE_STANDARD_DISPATCH, ornaLogId);
		materActiveDao.markMaterActiveUsedByStandardDistatch(ornaCode, GlobalConstant.BILL_CODE_STANDARD_DISPATCH, gheadid);
	}
	public Pager getShowDispatchedPageData(String gheadid, Map<String, String> condition){
		return standardDispatchDao.getShowDispatchedPageData(gheadid, condition);
	}
	public void deleteDispatchOrnaLog(String ornaLogId, String userid){
		DispatchOrnaLog ornaLog = dispatchOrnaDao.getDispatchOrnaLogById(ornaLogId);
		dispatchOrnaDao.deleteDispatchOrnaLog(ornaLogId, userid);//逻辑删除，将状态改为0
		materActiveDao.markMaterActiveValid(ornaLog.getOrnaCode());//改饰品状态为有效
	}
	public void cancelDispatch(String gheadid, String ornaCode, String userid){
		standardDispatchDao.deleteDispatchOrnaLog(gheadid, ornaCode, userid);//逻辑删除，将状态改为0
		materActiveDao.markMaterActiveValid(ornaCode);//改饰品状态为有效
	}
	public void exchangeDispatching(String gheadid, String ornaCode, String orgId, String userid){
		standardDispatchDao.exchangeDispatching(gheadid, ornaCode, orgId, userid);
	}

	public void checkStandardDispatchDelete(String gheadid){
		StandardDispatch sd = standardDispatchDao.getStandardDispatch(gheadid);
		if(!DictConstant.BILL_STATUS_SAVE.equals(sd.getStatus())){
			throw new RuntimeException("单据["+sd.getBillno()+"]不为保存状态不能删除和生成调拨单");
		}
		List<DispatchCondition> dcList = getDispatchCondition(gheadid);
		if(dcList.size()>0){
			throw new RuntimeException("单据["+sd.getBillno()+"]有配货参数锁定，不能删除和生成调拨单");
		}
	}

	public void deleteStandardDispatch(List<String> gheadidList, String userid){
		for(String gheadid:gheadidList){
			materActiveDao.markMaterActiveValidByStandardDispatch(gheadid);//改饰品状态为有效
			standardDispatchDao.deleteDispatchOrnaLogByGheadid(gheadid, userid);//逻辑删除，将状态改为0
			standardDispatchDao.deleteStandardDispatch(gheadid, userid);
		}
	}
	public List<MoveBillHead> createMoveBill(List<String> gheadidList, String outOrgId, String userid){
		String gheadids = "$";
		for(String gheadid : gheadidList){
			gheadids +="," + gheadid;
		}
		List<MoveBillHead> moveBillList = new ArrayList<MoveBillHead>();
		if(!"$".equals(gheadids)){
			gheadids = gheadids.substring(2);
			List<String> inOrgList = standardDispatchDao.getCreateMovebillInorgs(gheadids);
			String moveHeadIds = "$";
			for(String inOrgId : inOrgList){
				List<String> newOrnaCodeList = standardDispatchDao.getOrnaClodeFromMove(gheadids, inOrgId);
				if(newOrnaCodeList.size()<1){
					continue;
				}
				Org org = OrgCache.getInstance().getOrgById(inOrgId);
				//保存调拨单头
				MoveBillHead moveHead = saveMoveBillHead(outOrgId, userid, inOrgId, org.getJmFlag());
				moveBillDao.saveMoveBillLine(newOrnaCodeList, moveHead.getHeadid(), moveHead.getBillType(), org.getJmFlag(), moveHead.getInOrgId(), userid);
				moveBillDao.updateMoveBillSumNum(moveHead.getHeadid(), userid);
				moveBillDao.updateNorececount(moveHead.getHeadid(), null, userid);
				moveHead.setSumCount("" + newOrnaCodeList.size());
				moveHead.setValues();
				moveBillList.add(moveHead);
				
				moveHeadIds += "," + moveHead.getHeadid();
			}
			//状态改为已调拨3
			standardDispatchDao.updateDispatchOrnaStatusToMoved(gheadids, userid);
			if(!"$".equals(moveHeadIds)){
				moveHeadIds = moveHeadIds.substring(2);
				//修改现拥有量表中的引用单据编码和单据编号段
				materActiveDao.updateBillCodeFromDispatch(moveHeadIds);
			}
			standardDispatchDao.closeStandardDispatch(gheadids, userid);
		}
		return moveBillList;
	}
	/**
	 * 保存调拨单头
	 * @param gheadid
	 * @param outOrgId
	 * @param userid
	 * @param gatherHeadBillno
	 * @param inOrgId
	 * @return
	 */
	private MoveBillHead saveMoveBillHead(String outOrgId, String userid, String inOrgId, String jmFlag) {
		MoveBillHead moveHead = new MoveBillHead();
		moveHead.setBillno(commonDao.getBillno(DictConstant.YES_OR_NO_YES.equals(jmFlag)?GlobalConstant.BILL_CODE_DIAOBODAN_JM:GlobalConstant.BILL_CODE_DIAOBODAN));
		moveHead.setMoveType(DictConstant.MOVE_TYPE_DISPATCH);//配货调拨
		moveHead.setSrcBillCode(GlobalConstant.BILL_CODE_STANDARD_DISPATCH);//
		moveHead.setSrcBillId(null);//
		moveHead.setSrcBillNo(null);//
		moveHead.setDodate(DateUtil.getCurrentDate10());
		moveHead.setOutOrgId(outOrgId);
		moveHead.setInOrgId(inOrgId);
		moveHead.setOutStockId(DictConstant.INVCODE_ORNA);//设置为饰品库
		moveHead.setInStockId(DictConstant.INVCODE_ORNA);//设置为饰品库
		moveHead.setMemo("标准配货生成调拨单");
		moveHead.setCreateDate(DateUtil.getCurrentDate18());
		moveHead.setCreateId(userid);
		moveHead.setUpdateDate(DateUtil.getCurrentDate18());
		moveHead.setUpdateId(userid);
		if(DictConstant.YES_OR_NO_YES.equals(jmFlag)){
			moveHead.setStatus(DictConstant.BILL_STATUS_NO_ESTIMATE);//加盟调拨单的状态为未生成结算单
		}else{
			moveHead.setStatus(DictConstant.BILL_STATUS_REVIEWED);//审批完成
		}
		moveHead.setBillType(DictConstant.MOVE_BILL_TYPE_DIAOBODAN);
		moveHead.setJmFlag(jmFlag);
		
		String headid = moveBillDao.saveMoveBillHead(moveHead, userid);
		moveHead.setHeadid(headid);
		return moveHead;
	}
	/**
	 * 获取大类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getItemClassForSlt(String headid){
		return standardDispatchDao.getItemClassForSlt(headid);
	}
	/**
	 * 获取小类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getOrnaClassForSlt(String headid, String itemClassId){
		return standardDispatchDao.getOrnaClassForSlt(headid, itemClassId);
	}
	/**
	 * 获取分析范围
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getAnalysisForSlt(String headid, String itemClassId, String ornaClassId){
		return standardDispatchDao.getAnalysisForSlt(headid, itemClassId, ornaClassId);
	}
	/**
	 * 获取款式大类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleItemClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisArange){
		return standardDispatchDao.getStyleItemClassForSlt(headid, itemClassId, ornaClassId, analysisArange);
	}
	/**
	 * 获取款式中类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleMiddleClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisArange, String styleItemClassId){
		return standardDispatchDao.getStyleMiddleClassForSlt(headid, itemClassId, ornaClassId, analysisArange, styleItemClassId);
	}
	/**
	 * 获取款式小类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleOrnaClassForSlt(String headid, String itemClassId, String ornaClassId, String analysisArange, String styleItemClassId, String styleMiddleClassId){
		return standardDispatchDao.getStyleOrnaClassForSlt(headid, itemClassId, ornaClassId, analysisArange, styleItemClassId, styleMiddleClassId);
	}
	/**
	 * 获取款式
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleIdForSlt(String headid, String itemClassId, String ornaClassId, String analysisArange, String styleItemClassId, String styleMiddleClassId, String styleOrnaClassId){
		return standardDispatchDao.getStyleIdForSlt(headid, itemClassId, ornaClassId, analysisArange, styleItemClassId, styleMiddleClassId, styleOrnaClassId);
	}
}
