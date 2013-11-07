package com.jatools.manager.stock.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.constant.ParameterConstant;
import com.jatools.common.constant.StockConstant;
import com.jatools.dao.bd.BdCommonDao;
import com.jatools.dao.pur.HandoverChildDao;
import com.jatools.dao.pur.HandoverDao;
import com.jatools.dao.stock.MaterActiveDao;
import com.jatools.dao.stock.ProcPackageHeadDao;
import com.jatools.dao.stock.ProcPackageNewlineDao;
import com.jatools.dao.stock.ProcPackageOldlineDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.stock.ProcPackageHeadManager;
import com.jatools.vo.bd.Style;
import com.jatools.vo.bd.Tag;
import com.jatools.vo.pur.HandoverChild;
import com.jatools.vo.pur.HandoverHead;
import com.jatools.vo.pur.HandoverLine;
import com.jatools.vo.stock.MaterInStock;
import com.jatools.vo.stock.MaterIniv;
import com.jatools.vo.stock.ProcPackageHead;
import com.jatools.vo.stock.ProcPackageNewline;
import com.jatools.vo.stock.ProcPackageOldline;
import com.jatools.vo.stock.StockTransDO;
import com.jatools.web.util.BdCommon;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.ws.remote.WorkflowService;
/**
 * 拆包混包单头表Manager 实现类
 * @author ren.ming
 * <br>
 * Created 2011-11-20
 */
public class ProcPackageHeadManagerImpl extends BaseManager implements ProcPackageHeadManager {
	
	private ProcPackageHeadDao procPackageHeadDao;
	private ProcPackageOldlineDao procPackageOldlineDao;
	private ProcPackageNewlineDao procPackageNewlineDao;
	private MaterActiveDao materActiveDao;
	private HandoverDao handoverDao;
	private CommonDao commonDao;
	private BdCommonDao bdCommonDao;
	
	private WorkflowService workflowService;
	
	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}

	@Override
	public Pager getProcPackageHeadData(Map<String, String> condition) {
		return this.procPackageHeadDao.getProcPackageHeadData(condition);
	}

	@Override
	public void saveProcPackageHead(ProcPackageHead procPackageHead) {
		this.procPackageHeadDao.saveProcPackageHead(procPackageHead);

	}

	@Override
	public ProcPackageHead getProcPackageHead(String billid) {
		return this.procPackageHeadDao.getProcPackageHead(billid);
	}

	@Override
	public void updateProcPackageHead(ProcPackageHead procPackageHead) {
		this.procPackageHeadDao.updateProcPackageHead(procPackageHead);

	}
	
	@Override
	public void deleteProcPackageHead(String billid) {
		asertStatus("jat_proc_package_head", "billid", billid, "status", DictConstant.BILL_STATUS_SAVE);
		List<ProcPackageOldline> oldLines = this.procPackageOldlineDao.getProcPackageOldlineList(billid);
		
		for(ProcPackageOldline oldLine : oldLines) {// 修改现有量表数据状态
			this.materActiveDao.markMaterActiveValid(oldLine.getOrnaCode());
		}
		this.procPackageNewlineDao.deleteProcPackageNewlineByBillid(billid);
		this.procPackageOldlineDao.deleteProcPackageOldlineByBillid(billid);
		this.procPackageHeadDao.deleteProcPackageHead(billid);
	}
	
	
	public void saveOrUpdateProcPackageHead(ProcPackageHead procPackageHead, List<ProcPackageOldline> oldLines, List<ProcPackageNewline> newLines, String ids[]) {
		// 1、先把头表保存
		if (StringUtil.isNotBlank(procPackageHead.getBillid())) {
			asertStatus("jat_proc_package_head", "billid", procPackageHead.getBillid(), "status", DictConstant.BILL_STATUS_SAVE);
			this.procPackageHeadDao.updateProcPackageHead(procPackageHead);
		} else {
			this.procPackageHeadDao.saveProcPackageHead(procPackageHead);
		}
		
		if (!ArrayUtils.isEmpty(ids) && ids.length > 0) {
			String oldIds[] = ids[0].split(";");
			if(!ArrayUtils.isEmpty(oldIds)) {
				// TODO 删除 原饰品行表数据 ren.ming 完成
				for(String oldId: oldIds) {
					if(StringUtil.isNotBlank(oldId)) {
						String lineid = oldId.split(":")[0];
						String ornaCode = oldId.split(":")[1];
						if(StringUtil.isNotBlank(lineid) && StringUtil.isNotBlank(ornaCode)) {
							this.procPackageOldlineDao.deleteProcPackageOldline(lineid);
							this.materActiveDao.markMaterActiveValid(ornaCode);
						}
					}
				}
			}
			// TODO 删除 新饰品行表数据 ren.ming 完成
			if (ids.length > 1) {
				String newIds[] = ids[1].split(";");
				if(!ArrayUtils.isEmpty(newIds)) {
					for(String newId : newIds) {
						if(StringUtil.isNotBlank(newId)) {
							this.procPackageNewlineDao.deleteProcPackageNewline(newId);
						}
					}
				}
			}
		}
		long sumCost = 0L;
		long sumPosMoney = 0L;
		long sumInivCost = 0L;
		Double sumWeight = 0d;
		for(ProcPackageOldline oldLine : oldLines) {
			if(StringUtil.isNotBlank(oldLine.getStyleId())) {
				Style style = bdCommonDao.getStyleById(oldLine.getStyleId());
				if(style != null) {// 取款式 大类 中类 小类 id  
					oldLine.setStyleitemclass(style.getStyleitemclass());
					oldLine.setStylemiddleclass(style.getStylemiddleclass());
					oldLine.setStyleornaclass(style.getStyleornaclass());
				}
			}
			
			if(StringUtil.isBlank(oldLine.getLineid())) {
				// 保存
				oldLine.setBillid(procPackageHead.getBillid());
				oldLine.setCreateDate(DateUtil.getCurrentDate18());
				oldLine.setCreateId(procPackageHead.getCreateId());
				oldLine.setStatus(DictConstant.BILL_STATUS_SAVE);
				oldLine.setUpdateDate(DateUtil.getCurrentDate18());
				oldLine.setUpdateId(procPackageHead.getUpdateId());
				
				this.procPackageOldlineDao.saveProcPackageOldline(oldLine);
				
				this.materActiveDao.markMaterActiveUsed(oldLine.getOrnaCode(), GlobalConstant.BILL_CODE_CHAIHUNBAO, procPackageHead.getBillno());
			} else {
				oldLine.setUpdateDate(DateUtil.getCurrentDate18());
				oldLine.setUpdateId(procPackageHead.getUpdateId());
				this.procPackageOldlineDao.updateProcPackageOldline(oldLine);
			}
			if(StringUtil.isNotBlank(oldLine.getPosCost())){
				sumCost = sumCost + StringUtil.formatDoubleToLong(Double.valueOf(oldLine.getPosCost()));
			}
			if(StringUtil.isNotBlank(oldLine.getPosMoney())){
				sumPosMoney = sumPosMoney + StringUtil.formatDoubleToLong(Double.valueOf(oldLine.getPosMoney()));
			}
			if(StringUtil.isNotBlank(oldLine.getInivCost())){
				sumInivCost = sumInivCost + StringUtil.formatDoubleToLong(Double.valueOf(oldLine.getInivCost()));
			}
			if(StringUtil.isNotBlank(oldLine.getNowQty())){
				sumWeight = sumWeight + Double.valueOf(oldLine.getNowQty());
			}
		}
		
		int len = newLines.size();
		int i=0;
		List<Long> otherCost = new ArrayList<Long>();
		List<Long> otherPosMoney = new ArrayList<Long>();
		List<Long> otherInivCost = new ArrayList<Long>();
		for(ProcPackageNewline newLine : newLines) {
			long posCost = 0L;
			long posMoney = 0L;
			long inivCost = 0L;
			if(len > 1){
				if(i < len-1){
					if(StringUtil.isNotBlank(newLine.getAllQty()) && sumWeight > 0){
						posCost = StringUtil.formatDoubleToLong(sumCost*Double.valueOf(newLine.getAllQty())/(sumWeight*1000000));
						posMoney = StringUtil.formatDoubleToLong(sumPosMoney*Double.valueOf(newLine.getAllQty())/(sumWeight*1000000));
						inivCost = StringUtil.formatDoubleToLong(sumInivCost*Double.valueOf(newLine.getAllQty())/(sumWeight*1000000));
					}
				} else {
					posCost = sumCost;
					posMoney = sumPosMoney;
					inivCost = sumInivCost;
					for(Long cost : otherCost){
						posCost = posCost - cost;
					}
					for(Long money : otherPosMoney){
						posMoney = posMoney - money;
					}
					for(Long iniv : otherInivCost){
						inivCost = inivCost - iniv;
					}
				}
			} else {
				posCost = sumCost;
				posMoney = sumPosMoney;
				inivCost = sumInivCost;
			}
			i++;
			otherCost.add(posCost);
			otherPosMoney.add(posMoney);
			otherInivCost.add(inivCost);
			newLine.setPosCost(StringUtil.formatDouble(posCost/1000000.0, 6));
			newLine.setPosMoney(StringUtil.formatDouble(posMoney/1000000.0, 6));
			newLine.setInivCost(StringUtil.formatDouble(inivCost/1000000.0, 6));
			if(StringUtil.isBlank(newLine.getLineid())) {
				newLine.setBillid(procPackageHead.getBillid());
				newLine.setCreateDate(DateUtil.getCurrentDate18());
				newLine.setCreateId(procPackageHead.getCreateId());
				newLine.setStatus(DictConstant.BILL_STATUS_SAVE);
				newLine.setUpdateDate(DateUtil.getCurrentDate18());
				newLine.setUpdateId(procPackageHead.getUpdateId());
				
				newLine.setOrnaCode(this.createOrnaCode(newLine));
				
				this.procPackageNewlineDao.saveProcPackageNewline(newLine);
			} else {
				newLine.setUpdateDate(DateUtil.getCurrentDate18());
				newLine.setUpdateId(procPackageHead.getUpdateId());
				this.procPackageNewlineDao.updateProcPackageNewline(newLine);
			}
		}
//		if("1".equals(procPackageHead.getNeedChecked())){
//			workflowService.enterReview(procPackageHead, procPackageHead.getUpdateId());
//		}
	}

	private String createOrnaCode(ProcPackageNewline newLine){
		double allQty = 0;
		double grains = 0;
		String newOrnaCode = "";
		if(StringUtil.isNotBlank(newLine.getAllQty())){
			allQty = Double.valueOf(newLine.getAllQty());
		}
		if(StringUtil.isNotBlank(newLine.getGrains())){
			grains = Double.valueOf(newLine.getGrains());
		}
		if(grains > 0){
			newOrnaCode = commonDao.getOrnaCodeD0(allQty/grains);
		}else{
			newOrnaCode = commonDao.getOrnaCodeD0(allQty);
		}
		return newOrnaCode;
	}
	
	public void closePackageHead(String billid, String needChecked, String userId) {
		asertStatus("jat_proc_package_head", "billid", billid, "status", new String[]{DictConstant.BILL_STATUS_SAVE, DictConstant.BILL_STATUS_REVIEWED});
		ProcPackageHead procPackageHead = this.procPackageHeadDao.getProcPackageHead(billid);
		if(StringUtil.isNotBlank(needChecked) && "1".equals(needChecked)){
			// 提交审批流程
			workflowService.enterReview(procPackageHead, procPackageHead.getUpdateId());
			this.procPackageHeadDao.modifyProcPackageHeadStatus(billid, DictConstant.BILL_STATUS_REVIEWING, userId);
			return;
		}
		
		List<ProcPackageOldline> oldLines = this.procPackageOldlineDao.getProcPackageOldlineList(billid);
		List<ProcPackageNewline> newLines = this.procPackageNewlineDao.getProcPackageNewlineList(billid);
		
		/*1、操作单据状态 2、原饰品现有量表出库 3、出库事务 4、补交接单头行 5、入库记录表：JAT_MATER_INIV 	6、新现有量入库 	7、新入库事务	 */
		Double AllCost = 0D; // 总成本
		// TODO : 1、修改状态为关闭
		this.procPackageHeadDao.modifyProcPackageHeadStatus(billid, DictConstant.BILL_STATUS_CLOSED, userId);
		// TODO : 2、 出库
		
		String transFinance = commonDao.getBillFinance(GlobalConstant.BILL_CODE_RUKUDAN);
		String transSourceBill = GlobalConstant.BILL_CODE_CHAIHUNBAO;// 拆混包
		StockTransDO stockTransDO = new StockTransDO();// 入库事务
		stockTransDO.setTransFinance(transFinance);
		stockTransDO.setTransSourceBill(transSourceBill);
		stockTransDO.setUserId(userId);
		
		String ornaCode = "";
		int ornaIdx = 0;
		int idx = 0;
		if(oldLines != null) {
			for(ProcPackageOldline oldLine : oldLines) {
				this.materActiveDao.moveMaterActive2NoActive(oldLine.getOrnaCode());// 饰品从现有量表移入非现有量表
				
				// TODO : 3、出库事务
				this.procPackageOldlineDao.outStockInsertTrans(oldLine.getLineid(), GlobalConstant.BILL_CODE_CHAIHUNBAO, transFinance, userId);

				// 计算总成本
				if(StringUtil.isNotBlank(oldLine.getPosCost())) {
					AllCost = AllCost + Double.valueOf(oldLine.getPosCost());
				}
				if(!StockConstant.PACKAGE_TYPE_1.equals(procPackageHead.getDotype())){
					stockTransDO.setOrnaCodeSource(oldLine.getOrnaCode());
				}
				if("1".equals(oldLine.getNewVal())){
					ornaCode = oldLine.getOrnaCode();
					ornaIdx = idx;
				}
				idx++;
			}
		}
		ProcPackageOldline oldLine = oldLines.get(ornaIdx);
		stockTransDO.setIsPsale(oldLine.getIsPsale());
		stockTransDO.setItemClassId(oldLine.getItemClassId());
		stockTransDO.setOrnaClassId(oldLine.getOrnaClassId());
		stockTransDO.setOrnaDsc(oldLine.getOrnaDsc());
		stockTransDO.setStyleId(oldLine.getStyleId());
		stockTransDO.setStyleitemclass(oldLine.getStyleitemclass());
		stockTransDO.setStylemiddleclass(oldLine.getStylemiddleclass());
		stockTransDO.setStyleornaclass(oldLine.getStyleornaclass());
		stockTransDO.setUnitId(oldLine.getUnitId());
		
		if(StringUtil.isBlank(ornaCode)){
			ornaCode = oldLine.getOrnaCode();
		}
		// TODO : 4、交接单头行表
		HandoverHead handoverHead = new HandoverHead();// 头表
		String billno = commonDao.getBillno(GlobalConstant.BILL_CODE_JIAOJIEDAN);
		handoverHead.setBillno(billno);
		String vendorId = BdCommon.getParameterValue(ParameterConstant.CHANGE_VENDOR_ID);
		handoverHead.setVerdorId(vendorId);
		handoverHead.setSourceId(procPackageHead.getBillid());// 来源单据id
		handoverHead.setSourceNo(procPackageHead.getBillno());// 来源单据单号
		handoverHead.setSourceType(GlobalConstant.BILL_CODE_CHAIHUNBAO);// 来源单据类型
		handoverHead.setOrgId(procPackageHead.getOrgId());// 组织id
		handoverHead.setStatus(DictConstant.BILL_STATUS_CLOSED);
		handoverHead.setDodate(procPackageHead.getDodate());
		handoverHead.setDotype(DictConstant.BILL_JJ_ZYZH);
		handoverHead.setCreateId(userId);
		handoverHead.setCreateDate(DateUtil.getCurrentDate18());
		handoverHead.setCreateId(userId);
		handoverHead.setUpdateDate(DateUtil.getCurrentDate18());
		handoverHead.setUpdateId(userId);
		this.handoverDao.insertHandoverHead(handoverHead);
		
		String itemClassId = oldLines.get(0).getItemClassId();
		newLines = this.procPackageNewlineDao.getProcPackageNewlineList(billid);
		this.mergeHandlines(itemClassId, procPackageHead.getDotype(), newLines, handoverHead, userId);
		for (ProcPackageNewline newLine : newLines) {
			//交接单行表
//			HandoverLine handoverLine = new HandoverLine(); // 行表
//			handoverLine.setBillid(handoverHead.getBillid());
//			handoverLine.setItemClassId(itemClassId);
//			
//			if(StockConstant.PACKAGE_TYPE_2.equals(procPackageHead.getDotype())) { // 原料分割 重量
//				handoverLine.setUnitNo(GlobalConstant.BILL_JJ_KG);
//				handoverLine.setHangNum(newLine.getAllQty());
//				handoverLine.setYesNum(newLine.getAllQty());
//			} else {// 拆混包  粒数
//				handoverLine.setUnitNo(GlobalConstant.BILL_JJ_GRAIN);
//				handoverLine.setHangNum(newLine.getGrains());
//				handoverLine.setYesNum(newLine.getGrains());
//			}
//			handoverLine.setCreateId(userId);
//			this.handoverDao.insertHandoverLine(handoverLine);
			
			// TODO : 5、 现有量入库
			MaterInStock inStock = new MaterInStock();
			inStock.setAlaysisId(newLine.getAnalysisArangeId());
			inStock.setAllQty(newLine.getAllQty());
			inStock.setNowQty(newLine.getAllQty());
			inStock.setOldOrnaCode(ornaCode);
			inStock.setOrnaBarcode(newLine.getOrnaBarcode());
			inStock.setOrnaCode(newLine.getOrnaCode());
			inStock.setState(DictConstant.BILL_STATUS_MATER_ACTIVE_VALID);
			inStock.setStockId(StockConstant.STOCK_NEW_ID);
			inStock.setStoneNowNum(newLine.getGrains());
			inStock.setStoneTotalNum(newLine.getGrains());
			inStock.setRealTotalCost(newLine.getPosCost());
			inStock.setUpdateDate(DateUtil.getCurrentDate10());
			inStock.setUpdateTime(DateUtil.getCurrentDate18());
			inStock.setQltyId(newLine.getPoundsDiff());
			inStock.setInivCost(newLine.getInivCost());
			this.materActiveDao.packageInStock(inStock);
			
			
			// TODO : 6、入库 入库单：JAT_MATER_INIV
			MaterIniv materIniv = new MaterIniv();
			String inivNo = commonDao.getBillno(GlobalConstant.BILL_CODE_RUKUDAN);
			materIniv.setNo(inivNo);
			materIniv.setOrnaCode(newLine.getOrnaCode());
			materIniv.setCreateuserid(userId);
			materIniv.setSrcBillId(handoverHead.getBillid());
			materIniv.setSrcBillNo(handoverHead.getBillno());
			materIniv.setSrcBillCode(GlobalConstant.BILL_CODE_JIAOJIEDAN);
			materIniv.setHandoverBillId(handoverHead.getBillid());
			materIniv.setCreatedate(DateUtil.getCurrentDate18());
			materIniv.setUpdatedate(DateUtil.getCurrentDate18());
			materIniv.setInivDate(DateUtil.getCurrentDate18());
			this.procPackageNewlineDao.insertInivFromMater(materIniv);
			
			// TODO : 7、 入库事务
			stockTransDO.setLineid(newLine.getLineid());
			stockTransDO.setCreateDate(DateUtil.getCurrentDate18());
			stockTransDO.setTransDate(DateUtil.getCurrentDate10());
			stockTransDO.setTransSourceNo(procPackageHead.getBillno());
			this.procPackageNewlineDao.inStockInsertTrans(stockTransDO);
		}
		
		// 判断现有量是否有误差，如果有误差则生成差异新行
		this.createNewDiff(billid, oldLines, newLines, userId);
	}
	
	/**
	 * 对于拆包混包 大类小类都是一样的，所以只会生成一行交接单行表
	 * 如过大类是 原料钻石 需要插入交接单孙表
	 * @param newLines
	 * @param handoverHead
	 * @param userId
	 */
	private void mergeHandlines(String itemClassId, String dotype, List<ProcPackageNewline> newLines, HandoverHead handoverHead, String userId){
		double allQty = 0d;
		double allGrains = 0d;
		Map<String, HandoverChild> childs = new HashMap<String, HandoverChild>();
		for (ProcPackageNewline newLine : newLines) {
			//交接单行表
			allQty = allQty + Double.valueOf(newLine.getAllQty());
			allGrains = allGrains + Double.valueOf(newLine.getGrains());
			String analys = newLine.getAnalysisArangeId();
			HandoverChild child;
			if(childs.containsKey(analys)){
				child = childs.get(analys);
				if(StockConstant.PACKAGE_TYPE_2.equals(dotype)) { // 原料分割 重量
					child.setStoneNum(StringAdd(child.getStoneNum() , newLine.getAllQty()));
				} else {
					child.setStoneNum(StringAdd(child.getStoneNum() , newLine.getGrains()));
				}
			} else {
				child = new HandoverChild();
				child.setAlaysisId(analys);
				child.setCreateDate(DateUtil.getCurrentDate18());
				child.setCreateId(userId);
				if(StockConstant.PACKAGE_TYPE_2.equals(dotype)) { // 原料分割 重量
					child.setStoneNum(StringUtil.formatDouble(newLine.getAllQty(), 4));
				} else {
					child.setStoneNum(StringUtil.formatDouble(newLine.getGrains(), 4));
				}
				child.setUpdateDate(DateUtil.getCurrentDate18());
				child.setUpdateId(userId);
			}
			childs.put(analys, child);
		}
		
		HandoverLine handoverLine = new HandoverLine(); // 行表
		handoverLine.setBillid(handoverHead.getBillid());
		handoverLine.setItemClassId(itemClassId);
		
		if(StockConstant.PACKAGE_TYPE_2.equals(dotype)) { // 原料分割 重量
			handoverLine.setUnitNo(GlobalConstant.BILL_JJ_KG);
			handoverLine.setHangNum(StringUtil.formatDouble(allQty, 4));
			handoverLine.setYesNum(StringUtil.formatDouble(allQty, 4));
		} else {// 拆混包  粒数
			handoverLine.setUnitNo(GlobalConstant.BILL_JJ_GRAIN);
			handoverLine.setHangNum(StringUtil.formatDouble(allGrains, 4));
			handoverLine.setYesNum(StringUtil.formatDouble(allGrains, 4));
		}
		handoverLine.setCreateId(userId);
		this.handoverDao.insertHandoverLine(handoverLine);
		
		// 如果是原料钻石 则需要插入孙表
		if(DictConstant.ITEM_CLASS_YUANLIAOZUANSHI.equals(itemClassId)){
			for(HandoverChild child : childs.values()){
				child.setLineid(handoverLine.getLineid());
				this.handoverChildDao.saveHandoverChild(child);
			}
		}
	}
	
	private String StringAdd(String str1, String str2){
		if(StringUtil.isBlank(str1)) str1 = "0";
		if(StringUtil.isBlank(str2)) str2 = "0";
		double res = Double.valueOf(str1.trim()) + Double.valueOf(str2.trim());
		return StringUtil.formatDouble(res, 4);
	}
	
	private void createNewDiff(String billid, List<ProcPackageOldline> oldLines, List<ProcPackageNewline> newLines, String userId){
		// 判断是否有成本误差，如果有则生成差异新行
		Double sumOldWeight = 0d;
		Double sumNewWeight = 0d;
		for(ProcPackageOldline oldLine : oldLines){
			if(StringUtil.isNotBlank(oldLine.getNowQty())){
				sumOldWeight = sumOldWeight + Double.valueOf(oldLine.getNowQty());
			}
		}
		for(ProcPackageNewline newLine : newLines){
			if(StringUtil.isNotBlank(newLine.getAllQty())){
				sumNewWeight = sumNewWeight + Double.valueOf(newLine.getAllQty());
			}
		}
		long sumOldWeightLong = StringUtil.formatDoubleToLong(sumOldWeight);
		long sumNewWeightLong = StringUtil.formatDoubleToLong(sumNewWeight);
		if(sumOldWeightLong != sumNewWeightLong){
			ProcPackageNewline newLine = new ProcPackageNewline();
			String alQty = StringUtil.formatDouble(sumOldWeight-sumNewWeight, 6);
			newLine.setAllQty(alQty);
			newLine.setAnalysisArangeId(oldLines.get(0).getAlaysisId());
			newLine.setBillid(billid);
			newLine.setCreateDate(DateUtil.getCurrentDate18());
			newLine.setCreateId(userId);
			newLine.setGrains("0");
			newLine.setOrnaBarcode("0000000000");
			newLine.setOrnaCode("0000000000");
			newLine.setPosCost("0");
			newLine.setPosMoney("0");
			newLine.setStatus(DictConstant.BILL_STATUS_SAVE);
			newLine.setUpdateDate(DateUtil.getCurrentDate18());
			newLine.setUpdateId(userId);
			newLine.setPoundsDiff("1");
			this.procPackageNewlineDao.saveProcPackageNewline(newLine);
		}
	}
	/**
	 * 审核
	 */
	public void checkBillhead(String billid, String userId){
		this.procPackageHeadDao.modifyProcPackageHeadStatus(billid, DictConstant.BILL_STATUS_REVIEWING, userId);
		
		ProcPackageHead procPackageHead = procPackageHeadDao.getProcPackageHead(billid);
		//进入审批
		workflowService.enterReview(procPackageHead, userId);
	}
	
	
	public void setProcPackageOldlineDao(ProcPackageOldlineDao procPackageOldlineDao) {
		this.procPackageOldlineDao = procPackageOldlineDao;
	}

	public void setProcPackageNewlineDao(ProcPackageNewlineDao procPackageNewlineDao) {
		this.procPackageNewlineDao = procPackageNewlineDao;
	}

	public void setProcPackageHeadDao(ProcPackageHeadDao procPackageHeadDao) {
		this.procPackageHeadDao = procPackageHeadDao;
	}

	public void setMaterActiveDao(MaterActiveDao materActiveDao) {
		this.materActiveDao = materActiveDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public void setBdCommonDao(BdCommonDao bdCommonDao) {
		this.bdCommonDao = bdCommonDao;
	}

	public void setHandoverDao(HandoverDao handoverDao) {
		this.handoverDao = handoverDao;
	}


	@Override
	public List<Tag> getPrintData(String billid) {
		return this.procPackageHeadDao.getPrintData(billid);
	}

	@Override
	public int selectAnalysCount(String oldAnalysId, String newAnalysId) {
		return this.procPackageHeadDao.selectAnalysCount(oldAnalysId, newAnalysId);
	}

	public CommonDao getCommonDao() {
		return this.commonDao;
	}

	@Override
	public void saveAndCloseProcPackageHead(ProcPackageHead procPackageHead, List<ProcPackageOldline> oldLines, List<ProcPackageNewline> newLines, String[] ids, String needChecked) {
		if(StringUtil.isNotBlank(procPackageHead.getBillid())){
			String status = this.getBillStatus("jat_proc_package_head", "billid", procPackageHead.getBillid(), "status");
			if(DictConstant.BILL_STATUS_SAVE.equals(status)){
				this.saveOrUpdateProcPackageHead(procPackageHead, oldLines, newLines, ids);
			}
		} else {
			this.saveOrUpdateProcPackageHead(procPackageHead, oldLines, newLines, ids);
		}
		
		this.closePackageHead(procPackageHead.getBillid(), needChecked, procPackageHead.getUpdateId());
	}
	
	private HandoverChildDao handoverChildDao;

	public void setHandoverChildDao(HandoverChildDao handoverChildDao) {
		this.handoverChildDao = handoverChildDao;
	}
	
}
