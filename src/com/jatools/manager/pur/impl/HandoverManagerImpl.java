package com.jatools.manager.pur.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.constant.ParameterConstant;
import com.jatools.common.constant.PurConstant;
import com.jatools.common.constant.StockConstant;
import com.jatools.dao.basic.PriceLockDao;
import com.jatools.dao.out.CashMoneyAccountDao;
import com.jatools.dao.out.CashProdAccountDao;
import com.jatools.dao.pur.HandoverCashDao;
import com.jatools.dao.pur.HandoverChildDao;
import com.jatools.dao.pur.HandoverDao;
import com.jatools.dao.pur.cash.CashHeadDao;
import com.jatools.dao.pur.cash.CashLineDao;
import com.jatools.dao.stock.MaterActiveDao;
import com.jatools.dao.stock.MaterInivDao;
import com.jatools.dao.stock.MaterTransDao;
import com.jatools.dao.stock.ProcChangeHeadDao;
import com.jatools.dao.stock.ProcExitHeadDao;
import com.jatools.dao.stock.ProcExitLineDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.pur.HandoverManager;
import com.jatools.vo.basic.PriceLockHead;
import com.jatools.vo.out.CashMoneyAccount;
import com.jatools.vo.out.CashProdAccount;
import com.jatools.vo.pur.HandoverCash;
import com.jatools.vo.pur.HandoverChild;
import com.jatools.vo.pur.HandoverHead;
import com.jatools.vo.pur.HandoverInStock;
import com.jatools.vo.pur.HandoverLine;
import com.jatools.vo.pur.HandoverPriceStone;
import com.jatools.vo.pur.cash.CashHead;
import com.jatools.vo.pur.cash.CashLine;
import com.jatools.vo.stock.MaterIniv;
import com.jatools.vo.stock.ProcChangeHead;
import com.jatools.vo.stock.ProcExitHead;
import com.jatools.vo.stock.ProcExitLine;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.util.BdCommon;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.ws.remote.WorkflowService;
/**
 * 交接单
 * @author ren.ming
 * Created 2011-12-8
 */
@SuppressWarnings("rawtypes")
public class HandoverManagerImpl extends BaseManager implements HandoverManager {

	private HandoverDao handoverDao;
	private MaterInivDao materInivDao;
	private MaterActiveDao materActiveDao;
	private MaterTransDao materTransDao;
	private HandoverCashDao handoverCashDao;
	private CashProdAccountDao cashProdAccountDao;
	private CashMoneyAccountDao cashMoneyAccountDao;
	private HandoverChildDao handoverChildDao;
	private ProcChangeHeadDao procChangeHeadDao;
	private ProcExitHeadDao procExitHeadDao;
	private ProcExitLineDao procExitLineDao;
	private CashHeadDao cashHeadDao;
	private CommonDao commonDao;
	private CashLineDao cashLineDao;
	private PriceLockDao priceLockDao;
	
	private WorkflowService workflowService;
	
	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}
	public void setPriceLockDao(PriceLockDao priceLockDao) {
		this.priceLockDao = priceLockDao;
	}
	public void setCashLineDao(CashLineDao cashLineDao) {
		this.cashLineDao = cashLineDao;
	}
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	public void setCashHeadDao(CashHeadDao cashHeadDao) {
		this.cashHeadDao = cashHeadDao;
	}
	public void setProcExitLineDao(ProcExitLineDao procExitLineDao) {
		this.procExitLineDao = procExitLineDao;
	}
	public void setProcExitHeadDao(ProcExitHeadDao procExitHeadDao) {
		this.procExitHeadDao = procExitHeadDao;
	}
	public void setProcChangeHeadDao(ProcChangeHeadDao procChangeHeadDao) {
		this.procChangeHeadDao = procChangeHeadDao;
	}
	public void setHandoverChildDao(HandoverChildDao handoverChildDao) {
		this.handoverChildDao = handoverChildDao;
	}
	public void setCashMoneyAccountDao(CashMoneyAccountDao cashMoneyAccountDao) {
		this.cashMoneyAccountDao = cashMoneyAccountDao;
	}
	public void setCashProdAccountDao(CashProdAccountDao cashProdAccountDao) {
		this.cashProdAccountDao = cashProdAccountDao;
	}
	public void setHandoverCashDao(HandoverCashDao handoverCashDao) {
		this.handoverCashDao = handoverCashDao;
	}
	public void setHandoverDao(HandoverDao handoverDao) {
		this.handoverDao = handoverDao;
	}
	public void setMaterInivDao(MaterInivDao materInivDao) {
		this.materInivDao = materInivDao;
	}
	public void setMaterActiveDao(MaterActiveDao materActiveDao) {
		this.materActiveDao = materActiveDao;
	}
	public void setMaterTransDao(MaterTransDao materTransDao) {
		this.materTransDao = materTransDao;
	}
	
	public void saveOrUpdateHandoverHead(HandoverHead handoverHead, List<HandoverLine> lines, List<HandoverCash> cashLines, String ids[]) {
		this.saveUpdateCheckHandoverHead(handoverHead, lines, cashLines, ids, false);
	}
	
	
	public void saveAndCheckHandoverHead(HandoverHead handoverHead, List<HandoverLine> lines, List<HandoverCash> cashLines, String ids[]) {
		handoverHead.setStatus(DictConstant.BILL_STATUS_REVIEWING);
		this.saveUpdateCheckHandoverHead(handoverHead, lines, cashLines, ids, true);
	}
	
	private void saveUpdateCheckHandoverHead(HandoverHead handoverHead, List<HandoverLine> lines, List<HandoverCash> cashLines, String ids[], boolean isCheck){
		if(StringUtil.isNotBlank(handoverHead.getBillid())) {
			asertStatus("jat_pur_handover_head", "billid", handoverHead.getBillid(), "status", DictConstant.BILL_STATUS_SAVE);
			this.handoverDao.updateHandoverHead(handoverHead);
		} else {
			this.handoverDao.saveHandoverHead(handoverHead);
			if(StringUtil.isNotBlank(handoverHead.getSourceId()) && GlobalConstant.BILL_CODE_CAIGOUDAN.equals(handoverHead.getSourceType())){
				String srcIds[] = handoverHead.getSourceId().split(",");
				if(!ArrayUtils.isEmpty(srcIds)){
					for(String srcId : srcIds){
						this.handoverDao.modifyPurchaseStatus(srcId, DictConstant.BILL_STATUS_REFERENCE, handoverHead.getUpdateId());
					}
				}
			}
		}
		
		for(HandoverLine line : lines ) {
			this.saveOrUpdateHandLine(handoverHead, line);
		}
		
		for(HandoverCash cash : cashLines) {
			cash.setUpdateDate(DateUtil.getCurrentDate18());
			cash.setUpdateId(handoverHead.getUpdateId());
			if(StringUtil.isNotBlank(cash.getLineid())){
				this.handoverCashDao.updateHandoverCash(cash);
			} else {
				cash.setBillid(handoverHead.getBillid());
				cash.setCreateDate(DateUtil.getCurrentDate18());
				cash.setCreateId(handoverHead.getUpdateId());
				this.handoverCashDao.saveHandoverCash(cash);
			}
		}
		
		if(!ArrayUtils.isEmpty(ids)) {
			if(StringUtil.isNotBlank(ids[0])){
				String lineIds[] = ids[0].split(";");
				for(String lineid : lineIds){
					if(StringUtil.isNotBlank(lineid)){
						this.handoverDao.deleteHandoverLine(lineid);
						this.handoverChildDao.deleteHandoverChildByLineid(lineid);
					}
				}
			}
			if(ids.length >1){
				if(StringUtil.isNotBlank(ids[1])){
					String cashIds[] = ids[1].split(";");
					for(String cashId : cashIds){
						this.handoverCashDao.deleteHandoverCash(cashId);
					}
				}
			}
		}
		if(isCheck){
			workflowService.enterReview(handoverHead, handoverHead.getUpdateId());
		}
	}
	
	private void saveOrUpdateHandLine(HandoverHead handoverHead, HandoverLine line){
		line.setUpdateDate(DateUtil.getCurrentDate18());
		line.setUpdateId(handoverHead.getUpdateId());
		if(StringUtil.isNotBlank(line.getLineid())) {
			this.handoverDao.updateHandoverLine(line);
			// 删除需要删除的孙表
			if(StringUtil.isNotBlank(line.getDeleteChildIds())){
				String childIds [] = line.getDeleteChildIds().split(";");
				for(String childId : childIds){
					if(StringUtil.isNotBlank(childId)){
						this.handoverChildDao.deleteHandoverChild(childId);
					}
				}
			}
			// TODO 孙表的处理
			if(PurConstant.HANDOVER_LINE_ITEMCLASSID.equals(line.getItemClassId())){// 原料大类
				if(CollectionUtils.isNotEmpty(line.getChildren())){
					for(HandoverChild child : line.getChildren()){
						child.setUpdateDate(DateUtil.getCurrentDate18());
						child.setUpdateId(handoverHead.getUpdateId());
						if(StringUtil.isNotBlank(child.getChildid())){
							this.handoverChildDao.updateHandoverChild(child);
						} else {
							child.setCreateDate(DateUtil.getCurrentDate18());
							child.setCreateId(handoverHead.getUpdateId());
							this.handoverChildDao.saveHandoverChild(child);
						}
					}
				}
			} else{
				// 删除所有的孙表
				this.handoverChildDao.deleteHandoverChildByLineid(line.getLineid());
			}
		} else {
			line.setBillid(handoverHead.getBillid());
			line.setCreateDate(DateUtil.getCurrentDate18());
			line.setCreateId(handoverHead.getUpdateId());
			this.handoverDao.saveHandoverLine(line);
			
			// TODO 孙表的处理 
			if(PurConstant.HANDOVER_LINE_ITEMCLASSID.equals(line.getItemClassId())){// 原料大类
				if(CollectionUtils.isNotEmpty(line.getChildren())){
					for(HandoverChild child : line.getChildren()){
						child.setLineid(line.getLineid());
						child.setCreateDate(DateUtil.getCurrentDate18());
						child.setCreateId(handoverHead.getUpdateId());
						child.setUpdateDate(DateUtil.getCurrentDate18());
						child.setUpdateId(handoverHead.getUpdateId());
						this.handoverChildDao.saveHandoverChild(child);
					}
				}
			}
		}
	}
	
	/**其他交接单 保存使用*/
	public void saveOrUpdateOtherHandoverHead(HandoverHead handoverHead, List<HandoverLine> lines, String ids) {
		this.saveUpdateCheckOtherHandover(handoverHead, lines, ids, false);
	}
	
	/**其他交接单 保存使用*/
	public void saveAndCheckOtherHandoverHead(HandoverHead handoverHead, List<HandoverLine> lines, String ids) {
		handoverHead.setStatus(DictConstant.BILL_STATUS_REVIEWING);
		this.saveUpdateCheckOtherHandover(handoverHead, lines, ids, true);
	}
	
	private void saveUpdateCheckOtherHandover(HandoverHead handoverHead, List<HandoverLine> lines, String ids, boolean isCheck){
		if(StringUtil.isNotBlank(handoverHead.getBillid())) {
			asertStatus("jat_pur_handover_head", "billid", handoverHead.getBillid(), "status", DictConstant.BILL_STATUS_SAVE);
			this.handoverDao.updateHandoverHead(handoverHead);
		} else {
			handoverHead.setDotype(DictConstant.BILL_JJ_ZYZH);
			handoverHead.setVerdorId(BdCommon.getParameterValue(ParameterConstant.CHANGE_VENDOR_ID));
			this.handoverDao.saveHandoverHead(handoverHead);
		}
		if(StringUtil.isNotBlank(ids)) {
			String idArray[] = ids.split(";");
			if(!ArrayUtils.isEmpty(idArray)) {
				for(String lineid : idArray) {
					this.handoverDao.deleteHandoverLine(lineid);
					this.handoverChildDao.deleteHandoverChildByLineid(lineid);
				}
			}
		}
		for(HandoverLine line : lines ) {
			this.saveOrUpdateHandLine(handoverHead, line);
		}
		if(isCheck){
			workflowService.enterReview(handoverHead, handoverHead.getUpdateId());
		}
	}
	
	public void saveOrUpdateCash(HandoverHead head, List<HandoverCash> cashs, String ids, String userId) {
		// 修改交接单 结算状态
		this.handoverDao.modifyHandoverCashStatus(head.getBillid(), userId);
		
		if(null!=head && CollectionUtils.isNotEmpty(cashs)) {
			// TODO 写台账 dotype=0:资金台账   dotype=1：货品台账
			if(StringUtil.isNotBlank(ids)){
				String idArray[] = ids.split(";");
				for(String id : idArray){
					if(StringUtil.isNotBlank(id)){
						HandoverCash cash = this.handoverCashDao.getHandoverCash(id);
						if(DictConstant.BILL_JJ_WW.equals(head.getDotype())){
							// 冲销货品台账
							if(StringUtil.isNotBlank(cash.getItemClassId())){
								// 1、修改 货品台账 为 已销账
								String materialType = this.handoverDao.getMaterialTypeByItemClassId(cash.getItemClassId());
								int res = this.cashProdAccountDao.selectCashProdAccountCount(head.getBillno(), cash.getItemClassId(), DictConstant.BILL_JJ_TL, materialType);
								if(res > 0){
									this.cashProdAccountDao.modifyCashProdAccountChecked(head.getBillno(), cash.getItemClassId(), userId, DictConstant.BILL_JJ_TL, materialType);
									// 2、写入 一笔负货品台账 并 标记为已销账
									CashProdAccount prod = new CashProdAccount();
									prod.setBillNums("-"+cash.getMoneys());
									prod.setCashNums("-"+cash.getMoneys());
									prod.setNoNums("0");
									prod.setDotype(DictConstant.BILL_JJ_TL);
									prod.setIsChecked("1");
									prod.setMaterialType(materialType);
									prod.setItemClassId(cash.getItemClassId());
									createCashProdAccount2(head, cash, prod, userId, cash.getItemClassId());
									this.cashProdAccountDao.saveCashProdAccount(prod);
								}
							} else {
								// 冲销资金台账
								if(StringUtil.isNotBlank(cash.getMoneys())){
									double money = Double.valueOf(cash.getMoneys());
									int res = this.cashMoneyAccountDao.selectCashMoneyAccountCount(head.getBillno(), DictConstant.BILL_JJ_YF);
									if(money > 0 && res > 0){
										CashMoneyAccount moneyAcc = new CashMoneyAccount();
										moneyAcc.setDotype(DictConstant.BILL_JJ_YF);
										moneyAcc.setMoneys(BigDecimal.valueOf(-money));
										createCashMoneyAccount(head, moneyAcc, userId);
										this.cashMoneyAccountDao.saveCashMoneyAccount(moneyAcc);
									}
								}
							}
						} else {
							// 冲销资金台账
							if(StringUtil.isNotBlank(cash.getMoneys())){
								double money = Double.valueOf(cash.getMoneys());
								int res = this.cashMoneyAccountDao.selectCashMoneyAccountCount(head.getBillno(), DictConstant.BILL_JJ_YF);
								if(money > 0 && res > 0){
									CashMoneyAccount moneyAcc = new CashMoneyAccount();
									moneyAcc.setDotype(DictConstant.BILL_JJ_YF);
									moneyAcc.setMoneys(BigDecimal.valueOf(-money));
									createCashMoneyAccount(head, moneyAcc, userId);
									this.cashMoneyAccountDao.saveCashMoneyAccount(moneyAcc);
								}
							}
						}
						this.handoverCashDao.deleteHandoverCash(id);
					}
				}
			}
			for(HandoverCash cash : cashs) {
				cash.setUpdateDate(DateUtil.getCurrentDate18());
				cash.setUpdateId(userId);
				double moneys = 0;
				double moneyOld = 0;
				if(StringUtil.isNotBlank(cash.getMoneys())){
					moneys = Double.valueOf(cash.getMoneys());
				}
				if(StringUtil.isNotBlank(cash.getMoneysOld())){
					moneyOld = Double.valueOf(cash.getMoneysOld());
				}
				String itemClassId = cash.getItemClassId();
				String itemClassIdOld = cash.getItemClassIdOld();
				if(StringUtil.isNotBlank(cash.getLineid())){
					if(DictConstant.BILL_JJ_WW.equals(head.getDotype())){
						if(StringUtil.isNotBlank(itemClassId) && itemClassId.equals(itemClassIdOld)){// 新大类和原大类都不为空 则和以前保存一样
							// 1、修改 货品台账 为 已销账
							String materialType = this.handoverDao.getMaterialTypeByItemClassId(cash.getItemClassIdOld());
							int res = this.cashProdAccountDao.selectCashProdAccountCount(head.getBillno(), cash.getItemClassIdOld(), DictConstant.BILL_JJ_TL, materialType);
							boolean isnew = false;
							if(res > 0){// 之前有写过台账
								if((moneys != moneyOld) || (!cash.getItemClassId().equals(cash.getItemClassIdOld()))){// 金额或者大类有变化 需要写入冲销原台账
									if(StringUtil.isNotBlank(cash.getItemClassIdOld())){
										if(res > 0){
											this.cashProdAccountDao.modifyCashProdAccountChecked(head.getBillno(), cash.getItemClassIdOld(), userId, DictConstant.BILL_JJ_TL, materialType);
											// 2、写入 一笔负货品台账 并 标记为已销账
											CashProdAccount prod = new CashProdAccount();
											prod.setBillNums("-"+cash.getMoneysOld());
											prod.setCashNums("-"+cash.getMoneysOld());
											prod.setNoNums("0");
											prod.setDotype(DictConstant.BILL_JJ_TL);
											prod.setIsChecked("1");
											prod.setMaterialType(materialType);
											prod.setItemClassId(cash.getItemClassIdOld());
											createCashProdAccount2(head, cash, prod, userId, cash.getItemClassIdOld());
											this.cashProdAccountDao.saveCashProdAccount(prod);
										}
									}
									isnew = true;
								}
							} else {
								isnew = true;
							}
							if(isnew){
								// 3、写入一笔 新货品台账
								materialType = this.handoverDao.getMaterialTypeByItemClassId(cash.getItemClassId());
								cash.setMaterialType(materialType);
								if(moneys > 0 && StringUtil.isNotBlank(materialType)){
									CashProdAccount prod = new CashProdAccount();
									prod.setBillNums(cash.getMoneys());
									prod.setNoNums(cash.getMoneys());
									prod.setCashNums("0");
									prod.setDotype(DictConstant.BILL_JJ_TL);
									prod.setIsChecked("0");
									prod.setMaterialType(materialType);
									prod.setItemClassId(cash.getItemClassId());
									createCashProdAccount2(head, cash, prod, userId, cash.getItemClassId());
									this.cashProdAccountDao.saveCashProdAccount(prod);
								}
							}
							
						} else if (StringUtil.isBlank(itemClassId) && StringUtil.isBlank(itemClassIdOld)){ // 新大类和原大类都为空 金额 不相同则写入资金台账
							int res = this.cashMoneyAccountDao.selectCashMoneyAccountCount(head.getBillno(), DictConstant.BILL_JJ_YF);
							boolean isnew = false;
							if(res > 0){
								if(moneys != moneyOld){// 写冲销 再写入新台账
									if(moneyOld > 0){
										CashMoneyAccount moneyAcc = new CashMoneyAccount();
										moneyAcc.setDotype(DictConstant.BILL_JJ_YF);
										moneyAcc.setMoneys(BigDecimal.valueOf(-moneyOld));
										createCashMoneyAccount(head, moneyAcc, userId);
										this.cashMoneyAccountDao.saveCashMoneyAccount(moneyAcc);
									}
									isnew = true;
								}
							}else {
								isnew = true;
							}
							if(isnew){
								if(moneys >0){// 心如新资金台账
									CashMoneyAccount moneyAcc = new CashMoneyAccount();
									moneyAcc.setDotype(DictConstant.BILL_JJ_YF);
									moneyAcc.setMoneys(BigDecimal.valueOf(moneys));
									createCashMoneyAccount(head, moneyAcc, userId);
									this.cashMoneyAccountDao.saveCashMoneyAccount(moneyAcc);
								}
							}
						} else {
							if(StringUtil.isBlank(itemClassId)){
								String materialType = this.handoverDao.getMaterialTypeByItemClassId(cash.getItemClassIdOld());
								int res = this.cashProdAccountDao.selectCashProdAccountCount(head.getBillno(), cash.getItemClassIdOld(), DictConstant.BILL_JJ_TL, materialType);
								if(res > 0){
									this.cashProdAccountDao.modifyCashProdAccountChecked(head.getBillno(), cash.getItemClassIdOld(), userId, DictConstant.BILL_JJ_TL, materialType);
									// 2、写入 一笔负货品台账 并 标记为已销账
									CashProdAccount prod = new CashProdAccount();
									prod.setBillNums("-"+cash.getMoneysOld());
									prod.setCashNums("-"+cash.getMoneysOld());
									prod.setNoNums("0");
									prod.setDotype(DictConstant.BILL_JJ_TL);
									prod.setIsChecked("1");
									prod.setMaterialType(materialType);
									prod.setItemClassId(cash.getItemClassIdOld());
									createCashProdAccount2(head, cash, prod, userId, cash.getItemClassIdOld());
									this.cashProdAccountDao.saveCashProdAccount(prod);
								}
								// 写入新资金台账
								if(moneys >0){// 心如新资金台账
									CashMoneyAccount moneyAcc = new CashMoneyAccount();
									moneyAcc.setDotype(DictConstant.BILL_JJ_YF);
									moneyAcc.setMoneys(BigDecimal.valueOf(moneys));
									createCashMoneyAccount(head, moneyAcc, userId);
									this.cashMoneyAccountDao.saveCashMoneyAccount(moneyAcc);
								}
							} else {// 冲销资金台账 写入资金台账
								int res = this.cashMoneyAccountDao.selectCashMoneyAccountCount(head.getBillno(), DictConstant.BILL_JJ_YF);
								if(moneyOld > 0 && res > 0){
									CashMoneyAccount moneyAcc = new CashMoneyAccount();
									moneyAcc.setDotype(DictConstant.BILL_JJ_YF);
									moneyAcc.setMoneys(BigDecimal.valueOf(-moneyOld));
									createCashMoneyAccount(head, moneyAcc, userId);
									this.cashMoneyAccountDao.saveCashMoneyAccount(moneyAcc);
								}
								String materialType = this.handoverDao.getMaterialTypeByItemClassId(cash.getItemClassId());
								cash.setMaterialType(materialType);
								if(moneys > 0 && StringUtil.isNotBlank(materialType)){
									CashProdAccount prod = new CashProdAccount();
									prod.setBillNums(cash.getMoneys());
									prod.setNoNums(cash.getMoneys());
									prod.setCashNums("0");
									prod.setDotype(DictConstant.BILL_JJ_TL);
									prod.setIsChecked("0");
									prod.setMaterialType(materialType);
									prod.setItemClassId(cash.getItemClassId());
									createCashProdAccount2(head, cash, prod, userId, cash.getItemClassId());
									this.cashProdAccountDao.saveCashProdAccount(prod);
								}
							}
						}
					} else {
						// 查询是否已经写过台账
						int res = this.cashMoneyAccountDao.selectCashMoneyAccountCount(head.getBillno(), DictConstant.BILL_JJ_YF);
						boolean isnew = false;
						if(res >0){
							if(moneys != moneyOld){// 如果金额有修改过, 才需要写入台账
								if(moneyOld > 0 ){
									CashMoneyAccount moneyAcc = new CashMoneyAccount();
									moneyAcc.setDotype(DictConstant.BILL_JJ_YF);
									moneyAcc.setMoneys(BigDecimal.valueOf(-moneyOld));
									createCashMoneyAccount(head, moneyAcc, userId);
									this.cashMoneyAccountDao.saveCashMoneyAccount(moneyAcc);
								}
								isnew = true;
							}
						} else {
							isnew = true;
						}
						if(isnew){
							// 2、写入一笔 新资金台账
							if(moneys > 0){
								CashMoneyAccount moneyAcc = new CashMoneyAccount();
								moneyAcc.setDotype(DictConstant.BILL_JJ_YF);
								moneyAcc.setMoneys(BigDecimal.valueOf(moneys));
								createCashMoneyAccount(head, moneyAcc, userId);
								this.cashMoneyAccountDao.saveCashMoneyAccount(moneyAcc);
							}
						}
					}
					// 修改结算行表
					this.handoverCashDao.updateHandoverCash(cash);
				} else {
					if(DictConstant.BILL_JJ_WW.equals(head.getDotype())){
						// 1、写入一笔 新货品台账
						if(moneys > 0){
							if(StringUtil.isNotBlank(itemClassId)){// 货品台账
								String materialType = this.handoverDao.getMaterialTypeByItemClassId(itemClassId);
								cash.setMaterialType(materialType);
								if(StringUtil.isNotBlank(materialType)){
									CashProdAccount prod = new CashProdAccount();
									prod.setBillNums(cash.getMoneys());
									prod.setCashNums("0");
									prod.setNoNums(cash.getMoneys());
									prod.setDotype(DictConstant.BILL_JJ_TL);
									prod.setIsChecked("0");
									prod.setMaterialType(materialType);
									prod.setItemClassId(cash.getItemClassId());
									createCashProdAccount2(head, cash, prod, userId, cash.getItemClassId());
									this.cashProdAccountDao.saveCashProdAccount(prod);
								}
							}else{// 资金台账
								CashMoneyAccount moneyAcc = new CashMoneyAccount();
								moneyAcc.setDotype(DictConstant.BILL_JJ_YF);
								moneyAcc.setMoneys(BigDecimal.valueOf(moneys));
								createCashMoneyAccount(head, moneyAcc, userId);
								this.cashMoneyAccountDao.saveCashMoneyAccount(moneyAcc);
							}
						}
					} else {
						// 1、写入一笔 新资金台账
						if(StringUtil.isNotBlank(cash.getMoneys())){
							if(moneys > 0){
								CashMoneyAccount moneyAcc = new CashMoneyAccount();
								moneyAcc.setDotype(DictConstant.BILL_JJ_YF);
								moneyAcc.setMoneys(BigDecimal.valueOf(moneys));
								createCashMoneyAccount(head, moneyAcc, userId);
								this.cashMoneyAccountDao.saveCashMoneyAccount(moneyAcc);
							}
						}
					}
					// 保存结算行表
					cash.setCreateDate(DateUtil.getCurrentDate18());
					cash.setCreateId(userId);
					this.handoverCashDao.saveHandoverCash(cash);
				}
			}
		} else {
			throw new RuntimeException("结算失败");
		}
	}

	public void modifyNoNum(HandoverHead head , List<HandoverLine> lines, String userId) {
		// 只写货品台账
		if(null != head && CollectionUtils.isNotEmpty(lines)){
			for(HandoverLine line : lines){
				// 修改合格数量和金额
				modifyLineYes(line);
				line.setUpdateDate(DateUtil.getCurrentDate18());
				line.setUpdateId(userId);
				this.handoverDao.updateHandoverLine(line);
				double noNumLast = 0, noNumLastOld = 0, noNumNow = 0, noNumNowOld =0;
				if(StringUtil.isNotBlank(line.getNoNumLast())){
					noNumLast = Double.valueOf(line.getNoNumLast());
				}
				if(StringUtil.isNotBlank(line.getNoNumLastOld())){
					noNumLastOld = Double.valueOf(line.getNoNumLastOld());
				}
				if(StringUtil.isNotBlank(line.getNoNumNow())){
					noNumNow = Double.valueOf(line.getNoNumNow());
				}
				if(StringUtil.isNotBlank(line.getNoNumNowOld())){
					noNumNowOld = Double.valueOf(line.getNoNumNowOld());
				}
				
				// 处理前次不合格数量
				int res = this.cashProdAccountDao.selectCashProdAccountCount(head.getBillno(), line.getItemClassId(), DictConstant.BILL_JJ_TL, DictConstant.BILL_INVALID_PROD_MTYPE);
				boolean isnew = false;
				if(res >0){
					if(noNumLast != noNumLastOld){ // 说明 前次不合格数量 有变动 需要写台账
						if(noNumLastOld >0 ){
							// 修改前次不合格台账为 已 销账
							this.cashProdAccountDao.modifyCashProdAccountChecked(head.getBillno(), line.getItemClassId(), userId, DictConstant.BILL_JJ_TL, DictConstant.BILL_INVALID_PROD_MTYPE);
							// 写负的 前次不合格 台账
							CashProdAccount prod = new CashProdAccount();
							prod.setBillNums("-"+line.getNoNumLastOld());
							prod.setCashNums("-"+line.getNoNumLastOld());
							prod.setNoNums("0");
							prod.setDotype(DictConstant.BILL_JJ_TL);
							prod.setIsChecked("1");
							prod.setMaterialType(DictConstant.BILL_INVALID_PROD_MTYPE);
							createCashProdAccount(head, line, prod, userId);
							this.cashProdAccountDao.saveCashProdAccount(prod);
							isnew = true;
						}
					}
				} else {
					isnew = true;
				}
				if(isnew){
					// 写新的 前次不合格台账
					if(noNumLast>0){
						CashProdAccount prod = new CashProdAccount();
						prod.setBillNums(line.getNoNumLast());
						prod.setCashNums("0");
						prod.setNoNums(line.getNoNumLast());
						prod.setDotype(DictConstant.BILL_JJ_TL);
						prod.setIsChecked("0");
						prod.setMaterialType(DictConstant.BILL_INVALID_PROD_MTYPE);
						createCashProdAccount(head, line, prod, userId);
						this.cashProdAccountDao.saveCashProdAccount(prod);
					}
				}
				// 处理本次不合格数量
				// 查询 是否已经写入过 本次不合格台账
				res = this.cashProdAccountDao.selectCashProdAccountCount(head.getBillno(), line.getItemClassId(), DictConstant.BILL_JJ_FL, DictConstant.BILL_INVALID_PROD_MTYPE);
				isnew = false;
				if(res >0){
					if(noNumNowOld >0){
						// 修改 之前 保存 本次不合格台账为 已 销账
						this.cashProdAccountDao.modifyCashProdAccountChecked(head.getBillno(), line.getItemClassId(), userId, DictConstant.BILL_JJ_FL, DictConstant.BILL_INVALID_PROD_MTYPE);
						
						// 写负的 本次不合格 台账
						CashProdAccount prod = new CashProdAccount();
						prod.setBillNums("-"+noNumNowOld);
						prod.setCashNums("-"+noNumNowOld);
						prod.setNoNums("0");
						prod.setDotype(DictConstant.BILL_JJ_FL);
						prod.setIsChecked("1");
						prod.setMaterialType(DictConstant.BILL_INVALID_PROD_MTYPE);
						createCashProdAccount(head, line, prod, userId);
						this.cashProdAccountDao.saveCashProdAccount(prod);
						isnew = true;
					}
				} else {
					isnew = true;
				}
				
				if(isnew){
					// 写新的 本次不合格台账
					if(noNumNow >0){
						CashProdAccount prod = new CashProdAccount();
						prod.setBillNums(line.getNoNumNow());
						prod.setCashNums("0");
						prod.setNoNums(line.getNoNumNow());
						prod.setDotype(DictConstant.BILL_JJ_FL);
						prod.setIsChecked("0");
						prod.setMaterialType(DictConstant.BILL_INVALID_PROD_MTYPE);
						createCashProdAccount(head, line, prod, userId);
						this.cashProdAccountDao.saveCashProdAccount(prod);
					}
				}
			}
		} else {
			throw new RuntimeException("修改不合格失败");
		}
	}
	/**提交审核*/
	public void checkBillhead(String billid, String userId) {
		asertStatus("jat_pur_handover_head", "billid", billid, "status", DictConstant.BILL_STATUS_SAVE);
		this.handoverDao.modifyHandoverHeadStatus(billid, DictConstant.BILL_STATUS_REVIEWING, userId);
		
		HandoverHead handoverHead = handoverDao.getHandoverHead(billid);
		//进入审批
		workflowService.enterReview(handoverHead, userId);
	}
	
	public void receiveBillHead(String billid, List<HandoverLine> lines, String userId){
		asertStatus("jat_pur_handover_head", "billid", billid, "status", DictConstant.BILL_STATUS_REVIEWED);
		this.handoverDao.receiveHandover(billid, userId);

		for(HandoverLine line : lines){
			// 1、计算合格数量和合格金额
			double noNumLast = 0;
			if(StringUtil.isNotBlank(line.getNoNumLast())){
				noNumLast = Double.valueOf(line.getNoNumLast());
			}
			modifyLineYes(line);
			line.setUpdateDate(DateUtil.getCurrentDate18());
			line.setUpdateId(userId);
			this.handoverDao.updateHandoverLine(line);
			
			if(noNumLast>0){
				// TODO 写入前次不合格数量台账
				HandoverHead head = this.handoverDao.getHandoverHead(billid);
				CashProdAccount prod = new CashProdAccount();
				prod.setBillNums(noNumLast+"");
				prod.setCashNums("0");
				prod.setNoNums(noNumLast+"");
				prod.setDotype(DictConstant.BILL_JJ_TL);
				prod.setIsChecked("0");
				prod.setMaterialType(DictConstant.BILL_INVALID_PROD_MTYPE);
				createCashProdAccount(head, line, prod, userId);
				this.cashProdAccountDao.saveCashProdAccount(prod);
			}
		}
	}
	
	public void receiveNoBillHead(String billid, String userId){
		asertStatus("jat_pur_handover_head", "billid", billid, "status", DictConstant.BILL_STATUS_REVIEWED);
		this.handoverDao.modifyHandoverHeadStatus(billid, DictConstant.BILL_STATUS_SAVE, userId);
	}
	
	public boolean closeBillHead(String billid, String userId){
		asertStatus("jat_pur_handover_head", "billid", billid, "status", DictConstant.BILL_STATUS_RECEIVED);
		List<HandoverInStock> stockList = this.handoverDao.getInStockList(billid);
		boolean isSuccess = true;
		Map<String, String> diffMap = new HashMap<String, String>();
		for(HandoverInStock instock : stockList){
			if(PurConstant.HANDOVER_LINE_ITEMCLASSID.equals(instock.getItemClassId())){
				// 原料钻石 
				List<HandoverInStock> stockList2 = this.handoverDao.getInStockListByYL(billid);
				for(HandoverInStock stock : stockList2){
					isSuccess = checkStockNum(stock, diffMap);
					if(!isSuccess) {
						break;
					}
				}
				if(isSuccess && instock.getStockNum() != null){
					diffMap.put(instock.getItemClassId(), (instock.getNum() - instock.getStockNum())+"");
				}
			} else {
				isSuccess  = checkStockNum(instock, diffMap);
				if(!isSuccess){
					break;
				}
			}
		}
		if(isSuccess){
			for(Entry<String, String> diff : diffMap.entrySet()){
				this.handoverDao.modifyDiff(billid, userId, diff.getValue(), diff.getKey());
			}
			this.handoverDao.modifyHandoverHeadStatus(billid, DictConstant.BILL_STATUS_CLOSED, userId);
		}
		return isSuccess;
	}
	
	private boolean checkStockNum(HandoverInStock instock, Map<String, String> diffMap){
		boolean isSuccess = true;
		String msg = "";
		if(GlobalConstant.BILL_JJ_JIAN.equals(instock.getUnitId())){// 件：计算数量差
			String numUp = BdCommon.getParameterValue(ParameterConstant.HANDOVER_NUM_UP);
			double numUpD = Double.valueOf(numUp);
			double inStock = 0;// 入库数量
			if(instock.getStockCount() != null){
				inStock = instock.getStockCount();
			}
			
			double yesNum = instock.getNum();// 总数量
			if(instock.getNoNum() != null){
				yesNum = yesNum - instock.getNoNum();// 计算合格数量
			}
			if((yesNum - inStock) > numUpD || (yesNum - inStock) < 0){
				isSuccess = Boolean.FALSE;
				msg = "件数不在误差范围[0 - "+numUpD+"]内，不允许关闭";
			}else {
				if(!PurConstant.HANDOVER_LINE_ITEMCLASSID.equals(instock.getItemClassId())){
					diffMap.put(instock.getItemClassId(), (yesNum-instock.getStockCount())+"");
				}
			}
			
		} else if(GlobalConstant.BILL_JJ_GRAIN.equals(instock.getUnitId())){
			double handGrans = instock.getNum();
			double inGrains = instock.getStockGrain();
			if(handGrans != inGrains){
				isSuccess = Boolean.FALSE;
				msg = "交接单粒数和入库粒数不一样，不允许关闭";
			}
		} else {// 计算重量差
			//  如果为克的，用磅差除以入库件数和范围比较，小于可以过否则不让 
			
			String weightUp = BdCommon.getParameterValue(ParameterConstant.HANDOVER_WEIGHT_UP);
			String weightDown = BdCommon.getParameterValue(ParameterConstant.HANDOVER_WEIGHT_DOWN);
			double weightUpD = Double.valueOf(weightUp);
			double weightDownD = Double.valueOf(weightDown);
			double stockWeight = 0;// 入库重量
			if(instock.getStockWeight() != null){
				stockWeight = instock.getStockWeight();
			}
			
			double yesWeight = instock.getNum();// 合格质重量
			if(instock.getNoNum() != null){
				yesWeight = yesWeight - instock.getNoNum();
			}
			if(yesWeight >0){
				if(instock.getStockCount()>0){
					Double count = Double.valueOf(instock.getStockCount());
					double diff = (yesWeight - stockWeight)/count;
					diff = Double.valueOf(StringUtil.formatDouble(diff, 4));
					if(!(diff >= weightUpD && diff <= weightDownD)){
						isSuccess = Boolean.FALSE;
						msg = "重量不在误差范围["+weightUpD+" - "+weightDownD+"]内，不允许关闭";
					} else {
						if(!PurConstant.HANDOVER_LINE_ITEMCLASSID.equals(instock.getItemClassId())){
							diffMap.put(instock.getItemClassId(), (yesWeight-instock.getStockWeight())+"");
						}
					}
				} else {
					isSuccess = Boolean.FALSE;
					msg = "尚未进行过任何入库，不允许关闭单据";
				}
			}
		}
		if(!isSuccess){
			throw new RuntimeException(msg);
		}
		return isSuccess;
	}
	
	/**
	 * 生成入库单成本单价段修改
		1：判断入库单原料成本核算方式(COST_UNIT)如果为空或为NULL
			
		1) 成本单价=总成本/(计量单位(UNIT_ID)为克取重量(ALL_QTY)、为件取1)
			
		
		2：如果原料成本核算方式(COST_UNIT)有值
		1)原料成本核算方式(COST_UNIT)为粒(值为1)
		成本单价=总成本/总粒数(TOTAL_NUM)
		2)原料成本核算方式(COST_UNIT)为克拉(值为2)
		成本单价=总成本/总重量(ALL_QTY)
		3)原料成本核算方式(COST_UNIT)为包(值为3)
		成本单价=总成本
	 */
	public boolean  closeOtherBillHead(String billid, String userId, String orgId) {
		asertStatus("jat_pur_handover_head", "billid", billid, "status", DictConstant.BILL_STATUS_REVIEWED);
		// 判断入库数量是否在允许的范围内
		List<HandoverInStock> stockList = this.handoverDao.getOtherInStockList(billid);
		boolean isSuccess = true;
		Map<String, String> diffMap = new HashMap<String, String>();
		for(HandoverInStock instock : stockList){
			if(PurConstant.HANDOVER_LINE_ITEMCLASSID.equals(instock.getItemClassId())){
				// 原料钻石 
				List<HandoverInStock> stockList2 = this.handoverDao.getOtherInStockListByYL(billid);
				for(HandoverInStock stock : stockList2){
					isSuccess = checkStockNum(stock, diffMap);
					if(!isSuccess) {
						break;
					}
				}
				if(isSuccess && instock.getStockNum() != null){
					diffMap.put(instock.getItemClassId(), (instock.getNum() - instock.getStockNum())+"");
				}
			} else {
				isSuccess  = checkStockNum(instock, diffMap);
				if(!isSuccess){
					break;
				}
			}
		}
		if(isSuccess){
			//1、交接单的处理
			//2、来源单据的处理
			//3、出库  + 事务
			//4、入库  + 事务
			this.handoverDao.modifyHandoverHeadStatus(billid, DictConstant.BILL_STATUS_CLOSED, userId);
			
			// 判断来源单据是否为空 或者是否是CM  这样的交接单只需要做入库处理
			HandoverHead head = this.handoverDao.getHandoverHead(billid);
			// 对于拆石单 原料转换单  提纯单 修改入库单 进行成本均摊
			if(StringUtil.isNotEmpty(head.getSourceType()) && !GlobalConstant.BILL_CODE_CAIGOUDAN.equals(head.getSourceType())) {
				if(isChangeBill(head.getSourceType())){
					ProcChangeHead changeHead = this.procChangeHeadDao.getProcChangeHead(head.getSourceId());
					long sumCost = 0;
					if(StringUtil.isNotBlank(changeHead.getSumCost())){
						sumCost = StringUtil.formatDoubleToLong(Double.valueOf(changeHead.getSumCost()));
					}
					
					List<MaterIniv> inivList = this.handoverDao.getMaterInivList(billid);
					if(CollectionUtils.isNotEmpty(inivList)){
						long inivCostSum = 0;
						for(MaterIniv iniv : inivList){
							if(iniv.getTotalCost() != null){
								inivCostSum = inivCostSum + StringUtil.formatDoubleToLong(iniv.getTotalCost());
							}
						}
						if(sumCost != inivCostSum){
							int len = inivList.size();
							int i=0;
							List<Long> otherCost = new ArrayList<Long>();
							for(MaterIniv iniv : inivList){
								long unitCost = 0;
								long totalCost = 0;
								//double nowQty = 0d;// 现有量
								double oldTotalCost = 0d;// 原总成本
								double sumWeight = 0d;// 总重量
								double grains = 0d;
//								if(StringUtil.isNotBlank(iniv.getQuantity())){
//									nowQty = Double.valueOf(iniv.getQuantity());
//								}
								if(iniv.getWeight() != null){
									sumWeight = iniv.getWeight();
								}
								if(iniv.getTotalCost() != null){
									oldTotalCost = iniv.getTotalCost();
								}
								if(StringUtil.isNotBlank(iniv.getTotalNum())) {
									grains = Double.valueOf(iniv.getTotalNum());
								}
								if(len > 1){
									if(i < len-1){
										if(inivCostSum >0){
											totalCost = StringUtil.formatDoubleToLong(sumCost*oldTotalCost/inivCostSum);
										} else {
											totalCost = 0;
										}
									} else {
										totalCost = sumCost;
										for(Long cost : otherCost){
											totalCost = totalCost - cost;
										}
									}
								} else {
									totalCost = sumCost;
								}
								
								if(StringUtil.isBlank(iniv.getCostUnit())){
									int allowInivType = 0;
									if(StringUtil.isNotBlank(iniv.getAllowInivType())){
										allowInivType = Integer.valueOf(iniv.getAllowInivType());
									}
									if(allowInivType == 1){// 入库类型为1 
										if(sumWeight > 0){
											unitCost = StringUtil.formatDoubleToLong(totalCost/(sumWeight*1000000));
										}
									}else {// 入库类型不为1
										if("609".equals(iniv.getUnitId())){// 计量单位 为：克
											unitCost = StringUtil.formatDoubleToLong(totalCost/(sumWeight*1000000));
										}
										if("612".equals(iniv.getUnitId())){
											unitCost = totalCost;
										}
									}
								} else if("1".equals(iniv.getCostUnit())) {
									unitCost = StringUtil.formatDoubleToLong(totalCost/(grains*1000000));
								} else if("2".equals(iniv.getCostUnit())){
									unitCost = StringUtil.formatDoubleToLong(totalCost/(sumWeight*1000000));
								} else {
									unitCost = totalCost;
								}
								
								/*int allowInivType = 0;
								if(StringUtil.isNotBlank(iniv.getAllowInivType())){
									allowInivType = Integer.valueOf(iniv.getAllowInivType());
								}
								if(allowInivType == 1){
									if(sumWeight > 0){
										unitCost = StringUtil.formatDoubleToLong(totalCost/(sumWeight*1000000));
									}
								} else {
									if(nowQty > 0){
										unitCost = StringUtil.formatDoubleToLong(totalCost/(nowQty*1000000));
									}
								}*/
								
								otherCost.add(totalCost);
								i++;
								iniv.setCostPrice(Double.valueOf(StringUtil.formatDouble(unitCost/1000000.0, 6)));
								iniv.setTotalCost(Double.valueOf(StringUtil.formatDouble(totalCost/1000000.0, 6)));
								iniv.setUpdatedate(DateUtil.getCurrentDate18());
								this.handoverDao.modifyMaterInivOldTotalCost(iniv.getId());
								this.handoverDao.modifyMaterInivNewTotalCost(iniv);
							}
						}
					} else {
						throw new RuntimeException("尚未创建入库单，不允许关闭单据");
					}
				}
			}
			
			
			// TODO 1、修改入库单状态 为 保留
			this.materInivDao.updateBillStatusBySourceId(DictConstant.BILL_STATUS_SAVE, DictConstant.BILL_STATUS_MATER_ACTIVE_USED, userId, head.getBillid());
			
			// TODO 2、首先进行 入库到现有量表
			this.materActiveDao.inStockFormIniv(head.getBillid(), GlobalConstant.BILL_CODE_JIAOJIEDAN);
			
			// 创建价格锁定单
			this.createPriceLockBill(billid, userId, orgId);
			
			// TODO 3、写入库事务
			this.materTransDao.insertFromMaterIniv(head.getBillid(), GlobalConstant.BILL_CODE_JIAOJIEDAN, userId, head.getSourceType(), head.getSourceNo());

			// 修改核价单行表核价状态
			this.modifyCalc(billid, userId);
			
			// TODO 4、修改入库单状态 为 已入库
			this.materInivDao.updateBillStatusBySourceId(DictConstant.BILL_STATUS_MATER_ACTIVE_USED, DictConstant.BILL_STATUS_INSTOCK, userId, head.getBillid());
			
			// 只有其他交接单时 才需要进行出库操作处理
			if(StringUtil.isNotEmpty(head.getSourceType()) && !GlobalConstant.BILL_CODE_CAIGOUDAN.equals(head.getSourceType())) {
				if(GlobalConstant.BILL_CODE_TUILIAO.equals(head.getSourceType())){
					/** 退料单产生的交接单 */
					// 创建一笔负的台账 并 创建 结算单
					ProcExitHead exitHead = this.procExitHeadDao.getProcExitHead(head.getSourceId());
					List<ProcExitLine> lines = this.procExitLineDao.getProcExitLineList(head.getSourceId());
					
					if(exitHead != null && CollectionUtils.isNotEmpty(lines)){
						// 写货品台账
						//this.procExitHeadDao.insertProdccountByExit(exitHead.getBillid(), userId);
						
						if(!(DictConstant.BILL_STATUS_REVIEWED.equals(exitHead.getStatus()) || DictConstant.BILL_STATUS_CLOSING.equals(exitHead.getStatus()))){
							throw new RuntimeException("退料单尚未审核完毕， 不允许关闭");
						}
						for(ProcExitLine line : lines){
							
							// 取 对应的 发料单生成的台账
							CashProdAccount prodAccount2 = this.cashProdAccountDao.getCashProdAccount(line.getBillno(), line.getOrnaNo(), "0");
							if(prodAccount2 != null){
								double noNums = 0;// 未核销数量
								double userNums = 0;
								double exitNums = 0;// 本次核销数量
								if(StringUtil.isNotBlank(prodAccount2.getNoNums())){
									noNums = Double.valueOf(prodAccount2.getNoNums());
								}
								if(StringUtil.isNotBlank(prodAccount2.getUserNums())){
									userNums = Double.valueOf(prodAccount2.getUserNums());
								}
								if(StringUtil.isNotBlank(line.getExitNums())){
									exitNums =  Double.valueOf(line.getExitNums());
								}
								if(0 > noNums-userNums || userNums < exitNums){
									throw new RuntimeException("关闭失败, 实际可退料数量为["+(noNums-userNums+exitNums)+"], 退料数量为["+exitNums+"]");
								}
								
								// 修改 退料单生成的台账 为 已经核销完成
								CashProdAccount prodAccount = new CashProdAccount();
								prodAccount.setBillType(GlobalConstant.BILL_CODE_TUILIAO);
								prodAccount.setBillNo(exitHead.getBillno());
								prodAccount.setBillDate(exitHead.getDodate());
								prodAccount.setOrgId(exitHead.getOrgId());
								prodAccount.setDotype(DictConstant.BILL_JJ_TL);
								prodAccount.setBillNums(line.getExitNums());
								prodAccount.setCashNums(line.getExitNums());
								prodAccount.setNoNums("0");
								prodAccount.setIsChecked("1");
								prodAccount.setVoderId(prodAccount2.getVoderId());
								prodAccount.setMaterialType(prodAccount2.getMaterialType());
								prodAccount.setItemClassId(prodAccount2.getItemClassId());
								prodAccount.setCashUnit(prodAccount2.getCashUnit());
								prodAccount.setCreateId(userId);
								prodAccount.setCreateDate(DateUtil.getCurrentDate18());
								prodAccount.setUpdateDate(DateUtil.getCurrentDate18());
								prodAccount.setUpdateId(userId);
								prodAccount.setMemo(line.getOrnaNo());
								prodAccount.setStatus(DictConstant.BILL_STATUS_MARK);
								this.cashProdAccountDao.saveCashProdAccount(prodAccount);
								
								// 创建 结算单
								String billNo = commonDao.getBillno(GlobalConstant.BILL_CODE_JISUANDAN);
								CashHead cashHead = new CashHead();
								cashHead.setBillDate(DateUtil.getCurrentDate10());
								if(StringUtil.isNotBlank(line.getExitNums())){
									cashHead.setCashNums(Double.valueOf(line.getExitNums()));
								} else {
									cashHead.setCashNums(0d);
								}
								cashHead.setBillNo(billNo);
								cashHead.setCreateDate(DateUtil.getCurrentDate18());
								cashHead.setCreateId(userId);
								cashHead.setDotype(StockConstant.PROD_ACCOUNT_TYPE_2);
								cashHead.setOrgId(orgId);
								cashHead.setStatus(DictConstant.BILL_STATUS_CLOSED);
								cashHead.setUpdateDate(DateUtil.getCurrentDate18());
								cashHead.setUpdateId(userId);
								cashHead.setVoderId(exitHead.getVendorId());
								this.cashHeadDao.saveCashHead(cashHead);
								
								// 结算单行表
								CashLine cashLine = new CashLine();
								cashLine.setBillType(StockConstant.PROD_ACCOUNT_TYPE_2);
								cashLine.setItemClassId(prodAccount.getItemClassId());
								//cashLine.setArticleId(articleId);
								cashLine.setCashId(cashHead.getCashId());
								cashLine.setCreateDate(DateUtil.getCurrentDate18());
								cashLine.setCreateId(userId);
								cashLine.setCurNums(Double.valueOf(prodAccount.getNoNums()));
								cashLine.setLessNums(Double.valueOf(line.getExitNums()));
								cashLine.setPrId(prodAccount.getPrId());
								cashLine.setStatus(DictConstant.BILL_STATUS_CLOSED);
								cashLine.setUpdateDate(DateUtil.getCurrentDate18());
								cashLine.setUpdateId(userId);
								this.cashLineDao.saveCashLine(cashLine);
								
								CashLine cashLine2 = new CashLine();
								//cashLine2.setBillNo(billNo);
								cashLine2.setBillType(StockConstant.PROD_ACCOUNT_TYPE_1);
								cashLine2.setCashId(cashHead.getCashId());
								cashLine2.setItemClassId(prodAccount2.getItemClassId());
								cashLine2.setCreateDate(DateUtil.getCurrentDate18());
								cashLine2.setCreateId(userId);
								
								cashLine2.setCurNums(exitNums);
								cashLine2.setLessNums(noNums);
								cashLine2.setPrId(prodAccount2.getPrId());
								cashLine2.setStatus(DictConstant.BILL_STATUS_CLOSED);
								cashLine2.setUpdateDate(DateUtil.getCurrentDate18());
								cashLine2.setUpdateId(userId);
								this.cashLineDao.saveCashLine(cashLine2);
								
								boolean isCheck = false;
								if(exitNums == noNums){
									isCheck = true;
								}
								
								
								// 修改发料单 生成的台账的数据
								double BillNums = 0;
								if(StringUtil.isNotBlank(prodAccount2.getBillNums())){
									BillNums = Double.valueOf(prodAccount2.getBillNums());
								}
								if(isCheck){
									prodAccount2.setCashNums(BillNums+"");
									prodAccount2.setNoNums("0");
									prodAccount2.setIsChecked("1");
								} else {
									prodAccount2.setCashNums((BillNums-noNums+exitNums)+"");
									prodAccount2.setNoNums((noNums-exitNums)+"");
									prodAccount2.setIsChecked("0");
								}
								prodAccount2.setUpdateDate(DateUtil.getCurrentDate18());
								prodAccount2.setUpdateId(userId);
								if(noNums == 0){
									prodAccount2.setNoNums("0");
									prodAccount2.setIsChecked("1");
								}
								prodAccount.setStatus(DictConstant.BILL_STATUS_MARK);
								this.cashProdAccountDao.updateCashProdAccount(prodAccount2);
								this.procExitLineDao.subProdAccountUserNums(line.getLineid(), userId);
							} else {
								throw new RuntimeException("关闭单据失败, 无法创建退料结算单据");
							}
						}
						// 退料单 无需出库 只需要 关闭 退料单
						this.procExitHeadDao.modifyProcExitHeadStatus(head.getSourceId(), DictConstant.BILL_STATUS_CLOSED, userId);
						
					} else {
						throw new RuntimeException("关闭单据失败");
					}
				} else if(isChangeBill(head.getSourceType())) { // 形态转换|料提纯|拆石
					// TODO 4、出库 现有量到非现有量
					this.materActiveDao.moveActive2NoActiveForChange(head.getSourceId());
					// TODO 5、形态转换|料提纯|拆石 行表出库写事务
					this.materTransDao.insertFromChangeLine(head.getSourceId(), head.getSourceType(), userId);
					// 关闭来源单据 形态转换|料提纯|拆石
					this.procChangeHeadDao.modifyProcChangeHeadStatus(head.getSourceId(), DictConstant.BILL_STATUS_CLOSED, userId);
				}
			}
		}
		return isSuccess;
	}
	
	/**
	 * 判断单据是否是 形态转换  拆石  提纯
	 * @param sourceType
	 * @return
	 */
	private boolean isChangeBill(String sourceType){
		if(GlobalConstant.BILL_CODE_XINGTAI.equals(sourceType) || GlobalConstant.BILL_CODE_TICHUN.equals(sourceType) || 
				GlobalConstant.BILL_CODE_CHAISHI.equals(sourceType))
			return true;
		
		return false;
	}
	
	public void materInStock(HandoverHead head, String userId, String orgId){
		// TODO 1、修改入库单状态 为 保留
		this.materInivDao.updateBillStatusBySourceId(DictConstant.BILL_STATUS_SAVE, DictConstant.BILL_STATUS_MATER_ACTIVE_USED, userId, head.getBillid());

		// TODO 2、首先进行 入库到现有量表
		this.materActiveDao.inStockFormIniv(head.getBillid(), GlobalConstant.BILL_CODE_JIAOJIEDAN);
		
		// TODO 3、写入库事务
		this.materTransDao.insertFromMaterIniv(head.getBillid(), GlobalConstant.BILL_CODE_JIAOJIEDAN, userId, GlobalConstant.BILL_CODE_JIAOJIEDAN, head.getBillno());
		
		// 修改核价单
		this.modifyCalc(head.getBillid(), userId);
		
		// 创建价格锁定单
		this.createPriceLockBill(head.getBillid(), userId, orgId);

		// TODO 4、修改入库单状态 为 已入库
		this.materInivDao.updateBillStatusBySourceId(DictConstant.BILL_STATUS_MATER_ACTIVE_USED, DictConstant.BILL_STATUS_INSTOCK, userId, head.getBillid());
		
	}
	
	private void modifyCalc(String handoverId, String userId){
		//this.handoverDao.updateProdAccountByCalc(handoverId, userId);
		// 查询满足条件的核价单的数量
		List<CashProdAccount> accounts = this.handoverDao.selectProdAccountByHandId(handoverId);
		if(CollectionUtils.isNotEmpty(accounts)){
			for(CashProdAccount prod : accounts){
				double billNums = 0;
				double noNums = 0;
				double userNums = 0;
				double calcNums = 0;
				if(StringUtil.isNotBlank(prod.getBillNums())){
					billNums = Double.valueOf(prod.getBillNums());
				}
				if(StringUtil.isNotBlank(prod.getNoNums())){
					noNums = Double.valueOf(prod.getNoNums());
				}
				if(StringUtil.isNotBlank(prod.getUserNums())){
					userNums = Double.valueOf(prod.getUserNums());
				}
				if(prod.getCalcNums() != null){
					calcNums = prod.getCalcNums();
				}
				if(userNums > noNums || calcNums > userNums){
					throw new RuntimeException("核销失败，核销数量不允许大于实际可核销数量");
				}
				if(noNums >0){
					if(calcNums == noNums){
						prod.setCashNums(prod.getBillNums());
						prod.setNoNums("0");
						prod.setIsChecked("1");
					} else {
						double cashNums = billNums - noNums + calcNums;
						prod.setCashNums(StringUtil.formatDouble(cashNums, 6));
						prod.setNoNums(StringUtil.formatDouble(noNums - calcNums, 6));
					}
				} else {
					throw new RuntimeException("台账已没有可核销的数量");
				}
				prod.setUpdateDate(DateUtil.getCurrentDate18());
				prod.setUpdateId(userId);
				this.cashProdAccountDao.updateCashProdAccount(prod);
			}
			// TODO 修改台账使用量
			this.handoverDao.subProdAccountUserNumsByHandId(handoverId, userId);
			
			// 通过核价单 插入台账
			this.handoverDao.insertProdAccountByCalc(handoverId, userId);
			
			// 修改核价单 已核销状态
			this.handoverDao.modifyCalcByHandover(handoverId, userId);
		}
	}
	
	@SuppressWarnings("unused")
	private void modifyCalc2(String handoverId, String userId){
		//this.handoverDao.updateProdAccountByCalc(handoverId, userId);
		// 查询满足条件的核价单的数量
		List<HandoverPriceStone> priceStones = this.handoverDao.selectPriceStonesByHandover(handoverId);
		List<CashProdAccount> accounts = this.handoverDao.selectProdAccountByHandover(handoverId);
		Map<String, List<CashProdAccount>> acountMap = new HashMap<String, List<CashProdAccount>>();
		if(CollectionUtils.isNotEmpty(accounts)){// 对货品台账进行分组
			for(CashProdAccount account : accounts){
				String key = new StringBuffer(account.getMemo().trim()).append("_").append(account.getVoderId().trim()).toString();
				List<CashProdAccount> accountList = null;
				if(acountMap.containsKey(key)){
					accountList = acountMap.get(key);
				} else {
					accountList = new ArrayList<CashProdAccount>();
				}
				accountList.add(account);
				acountMap.put(key, accountList);
			}
		}
		if(CollectionUtils.isNotEmpty(priceStones)){
			for(HandoverPriceStone stone : priceStones){
				String key = new StringBuffer(stone.getOrnaCode().trim()).append("_").append(stone.getVendorId().trim()).toString();
				List<CashProdAccount> accountList = acountMap.get(key);
				if(stone.getStoneNum() != null && stone.getStoneNum() >0){
					if(CollectionUtils.isNotEmpty(accountList)){
						double stoneNum = stone.getStoneNum();
						for(CashProdAccount prod : accountList){
							if(stoneNum <= 0){
								break;// 如果已经核销完成 则推出循环
							}
							double noNums = 0d;
							double billNums = 0d;
							if(StringUtil.isNotBlank(prod.getNoNums())){
								noNums = Double.valueOf(prod.getNoNums());
							}
							if(StringUtil.isNotBlank(prod.getBillNums())){
								billNums = Double.valueOf(prod.getBillNums());
							}
							if(noNums >0){
								if(stoneNum >= noNums){
									prod.setCashNums(prod.getBillNums());
									prod.setNoNums("0");
									prod.setIsChecked("1");
									stoneNum = stoneNum - noNums;
								} else {
									noNums = noNums - stoneNum;
									double cashNums = billNums - noNums;
									prod.setCashNums(StringUtil.formatDouble(cashNums, 6));
									prod.setNoNums(StringUtil.formatDouble(noNums, 6));
									stoneNum = 0d;
								}
							} else {// 台账已经核销完成
								prod.setCashNums(prod.getBillNums());
								prod.setIsChecked("1");
								prod.setNoNums("0");
							}
							prod.setUpdateDate(DateUtil.getCurrentDate18());
							prod.setUpdateId(userId);
							this.cashProdAccountDao.updateCashProdAccount(prod);
						}
						if(stoneNum >0){
							throw new RuntimeException("核销失败，退料数量不允许大于发料数量");
						}
					} else {
						throw new RuntimeException("核销失败，委外发料台账数据不存在");
					}
				}
			}
		}
		
		this.handoverDao.insertProdAccountByCalc(handoverId, userId);
		this.handoverDao.modifyCalcByHandover(handoverId, userId);
	}
	
	public List<HandoverInStock> getInStockList(String billid){
		return this.handoverDao.getInStockList(billid);
	}
	
	public List<HandoverInStock> getInStockListByYL(String billid){
		return this.handoverDao.getInStockListByYL(billid);
	}
	
	/**
	 * 创建价格锁定单
	 * @param handoverId 交接单id
	 * @param userId
	 * @param orgId
	 */
	private void createPriceLockBill(String handoverId, String userId, String orgId){
		int lockCount = this.handoverDao.getInivPriceLockCount(handoverId);
		if(lockCount > 0){
			// 判断 入库单是否价格锁定  如果是的话 ： 1、把现有量状态改成 引用状态 2、创建价格锁定单据
			//this.handoverDao.modifyMaterInvalidByHandoverId(handoverId);// 修改现有量状态
			// 创建 价格锁定头表
			PriceLockHead lockHead = new PriceLockHead();
			String billno = commonDao.getBillno(GlobalConstant.BILL_CODE_PRICE_LOCK);
			lockHead.setBillno(billno);
			lockHead.setCreateDate(DateUtil.getCurrentDate18());
			lockHead.setCreateId(userId);
			lockHead.setLockFlag(DictConstant.PRICE_LOCK);
			lockHead.setLockReason("锁定的入库单自动生成");
			lockHead.setOrgId(orgId);
			lockHead.setStatus(DictConstant.BILL_STATUS_REVIEWING);
			lockHead.setSumCount(lockCount+"");
			lockHead.setUpdateDate(DateUtil.getCurrentDate18());
			lockHead.setUpdateId(userId);
			this.priceLockDao.savePriceLockHead(lockHead, userId);
			// 创建行表
			this.handoverDao.insertPriceLockLineFromIniv(handoverId, lockHead.getBillid());
			workflowService.enterReview(lockHead, userId);
		}
	}
	
	/**
	 * 修改行表中的 合格数量和金额
	 * @param line
	 */
	private void modifyLineYes(HandoverLine line){
		double hangNum = 0;
		double noNumNow = 0;
		double hangPrice = 0;
		double yesNum = 0;
		if(StringUtil.isNotBlank(line.getHangNum())){
			hangNum = Double.valueOf(line.getHangNum());
		}
		if(StringUtil.isNotBlank(line.getNoNumNow())){
			noNumNow = Double.valueOf(line.getNoNumNow());
		}
		if(StringUtil.isNotBlank(line.getHangPrice())){
			hangPrice = Double.valueOf(line.getHangPrice());
		}
		yesNum = hangNum-noNumNow;
		line.setYesNum(yesNum + "");
		line.setYesMoney((yesNum*hangPrice)+"");
	}
	
	private void createCashProdAccount(HandoverHead head, HandoverLine line, CashProdAccount prod, String userId){
		String cashUnit = this.handoverDao.getCashUnitByItemClassId(line.getItemClassId(), prod.getMaterialType());
		if(StringUtil.isNotBlank(cashUnit)){
			prod.setCashUnit(cashUnit);
		}
		prod.setBillDate(head.getDodate());
		prod.setBillNo(head.getBillno());
		prod.setBillType(GlobalConstant.BILL_CODE_JIAOJIEDAN);
		//prod.setCashUnit(line.getUnitNo());
		prod.setCreateDate(DateUtil.getCurrentDate18());
		prod.setCreateId(userId);
		prod.setItemClassId(line.getItemClassId());
		prod.setOrgId(head.getOrgId());
		prod.setStatus(DictConstant.BILL_STATUS_MARK);
		prod.setVoderId(head.getVerdorId());
		prod.setUpdateId(userId);
		prod.setUpdateDate(DateUtil.getCurrentDate18());
	}
	
	private void createCashProdAccount(HandoverHead head, HandoverLine line, CashProdAccount prod, String userId, String itemClassId){
		String cashUnit = this.handoverDao.getCashUnitByItemClassId(itemClassId, prod.getMaterialType());
		if(StringUtil.isNotBlank(cashUnit)){
			prod.setCashUnit(cashUnit);
		}
		prod.setBillDate(head.getDodate());
		prod.setBillNo(head.getBillno());
		prod.setBillType(GlobalConstant.BILL_CODE_JIAOJIEDAN);
		//prod.setCashUnit(line.getUnitNo());
		prod.setCreateDate(DateUtil.getCurrentDate18());
		prod.setCreateId(userId);
		prod.setItemClassId(itemClassId);
		prod.setOrgId(head.getOrgId());
		prod.setStatus(DictConstant.BILL_STATUS_MARK);
		prod.setVoderId(head.getVerdorId());
		prod.setUpdateId(userId);
		prod.setUpdateDate(DateUtil.getCurrentDate18());
	}
	private void createCashProdAccount2(HandoverHead head, HandoverCash cash, CashProdAccount prod, String userId, String itemClassId){
		// TODO 台账的计量单位
		String cashUnit = this.handoverDao.getCashUnitByItemClassId(itemClassId, prod.getMaterialType());
		if(StringUtil.isNotBlank(cashUnit)){
			prod.setCashUnit(cashUnit);
		}
		prod.setBillDate(head.getDodate());
		prod.setBillNo(head.getBillno());
		prod.setBillType(GlobalConstant.BILL_CODE_JIAOJIEDAN);
		prod.setCreateDate(DateUtil.getCurrentDate18());
		prod.setCreateId(userId);
		//prod.setItemClassId(cash.getItemClassId());
		prod.setOrgId(head.getOrgId());
		prod.setStatus(DictConstant.BILL_STATUS_MARK);
		prod.setVoderId(head.getVerdorId());
		prod.setUpdateId(userId);
		prod.setUpdateDate(DateUtil.getCurrentDate18());
	}
	/**
	 * 修改 资金台账数据
	 * @param head
	 * @param cash
	 * @param moneyAcc
	 * @param userId
	 */
	private void createCashMoneyAccount(HandoverHead head, CashMoneyAccount moneyAcc, String userId){
		moneyAcc.setBillDate(head.getDodate());
		moneyAcc.setBillNo(head.getBillno());
		moneyAcc.setBillType(GlobalConstant.BILL_CODE_JIAOJIEDAN);
		moneyAcc.setCreateDate(DateUtil.getCurrentDate18());
		moneyAcc.setCreateId(BigDecimal.valueOf(Double.valueOf(userId)));
		moneyAcc.setOrgId(BigDecimal.valueOf(Double.valueOf(head.getOrgId())));
		moneyAcc.setStatus(BigDecimal.valueOf(Double.valueOf(DictConstant.BILL_STATUS_MARK)));
		moneyAcc.setVoderId(BigDecimal.valueOf(Double.valueOf(head.getVerdorId())));
		moneyAcc.setUpdateId(BigDecimal.valueOf(Double.valueOf(userId)));
		moneyAcc.setUpdateDate(DateUtil.getCurrentDate18());
	}
	
	public void modifyHandoverHeadStatus(String billid, String status, String userId){
		this.handoverDao.modifyHandoverHeadStatus(billid, status, userId);
	}
	@Override
	public Pager getHandoverHeadData(Map<String, String> condition) {
		return this.handoverDao.getHandoverHeadData(condition);
	}
	@Override
	public Pager getReportHandHandoverHeadData(Map<String, String> condition) {
		return this.handoverDao.getReportHandHandoverHeadData(condition);
	}
	@Override
	public Pager getReportOutHandoverHeadData(Map<String, String> condition) {
		return this.handoverDao.getReportOutHandoverHeadData(condition);
	}
	
	public Pager getOtherHandoverHeadData(Map<String, String> condition) {
		return this.handoverDao.getOtherHandoverHeadData(condition);
	}

	@Override
	public void saveHandoverHead(HandoverHead handoverHead) {
		this.handoverDao.saveHandoverHead(handoverHead);
	}

	@Override
	public HandoverHead getHandoverHead(String billid) {
		return this.handoverDao.getHandoverHead(billid);
	}

	@Override
	public void updateHandoverHead(HandoverHead handoverHead) {
		this.handoverDao.updateHandoverHead(handoverHead);
	}
	
	@Override
	public void modifyHandoverHeadStatus(Map params) {
		this.handoverDao.modifyHandoverHeadStatus(params);
	}

	@Override
	public void deleteHandoverHead(String billid, String userId) {
		asertStatus("jat_pur_handover_head", "billid", billid, "status", DictConstant.BILL_STATUS_SAVE);
		this.handoverDao.modifyPurchaseStatusByHandoverId(billid, DictConstant.BILL_STATUS_MARK, userId);
		this.handoverDao.deleteHandoverHead(billid);
		this.handoverDao.deleteHandoverLineByBillid(billid);
		this.handoverCashDao.deleteHandoverCashByBillid(billid);
	}

	public HandoverHead getHandoverHeadBySrcBillId(String srcBillid, String srcType){
		return this.handoverDao.getHandoverHeadBySrcBillId(srcBillid, srcType);
	}

    @Override
    public HandoverHead getHandoverHeadByBillno(String billno) {
        return this.handoverDao.getHandoverHeadByBillno(billno);
    }

    // =====================================================================================================
	// ------------------------------------------------Handove Line-----------------------------------------
	
	@Override
	public Pager getHandoverLineData(Map<String, String> condition) {
		return this.getHandoverLineData(condition);
	}

	@Override
	public List<HandoverLine> getHandoverLineList(String billid) {
		return this.handoverDao.getHandoverLineList(billid);
	}

	@Override
	public void saveHandoverLine(HandoverLine handoverLine) {
		this.handoverDao.saveHandoverLine(handoverLine);
	}

	@Override
	public HandoverLine getHandoverLine(String lineid) {
		return this.handoverDao.getHandoverLine(lineid);
	}

	@Override
	public void updateHandoverLine(HandoverLine handoverLine) {
		this.handoverDao.updateHandoverLine(handoverLine);
	}

	@Override
	public void modifyHandoverLineStatus(Map params) {
		this.handoverDao.modifyHandoverLineStatus(params);
	}

	@Override
	public void deleteHandoverLine(String lineid) {
		this.handoverDao.deleteHandoverLine(lineid);
		this.handoverChildDao.deleteHandoverChildByLineid(lineid);
	}
	
	public List<SelectorOption> getAnalysisList(){
		return this.handoverDao.getAnalysisList();
	}
	
	public Pager getPurchaseList(Map<String, String> condition){
		return this.handoverDao.getPurchaseList(condition);
	}
	@Override
	public List<HandoverInStock> getOtherInStockList(String billid) {
		return this.handoverDao.getOtherInStockList(billid);
	}
	
	public List<HandoverInStock> getOtherInStockListByYL(String billid) {
		return this.handoverDao.getOtherInStockListByYL(billid);
	}
	
	
	@Override
	public Map<String, String> changeHandoverLine(HandoverHead head, List<HandoverLine> lines, String deleteLineIds, String userId) {
		Map<String, String> result = new HashMap<String, String>();
		/**
		 * 首先比较修改时间和数据库是否一致，如果不一致 说明已经有人在此单据打开之后修改过，
		 * 必须提醒用户刷新单据，之后再做修改
		 */
		if(this.handoverDao.checkHandoverModify(head.getBillid(), head.getUpdateDate())){
			// 判断当前单据是否需要写台账 审批完毕 不写台账  接收：写台账
			// 其他交接单不写台账
			if(GlobalConstant.BILL_CODE_CAIGOUDAN.equals(head.getSourceType()) && DictConstant.BILL_STATUS_RECEIVED.equals(head.getStatus())){
				if(StringUtil.isNotBlank(deleteLineIds)){
					String lineIds[] = deleteLineIds.split(",");
					for(String lineid : lineIds){
						HandoverLine line = this.getHandoverLine(lineid);
						double noNumLast = 0, noNumNow = 0;
						if(StringUtil.isNotBlank(line.getNoNumLast())){
							noNumLast = Double.valueOf(line.getNoNumLast());
						}
						if(StringUtil.isNotBlank(line.getNoNumNow())){
							noNumNow = Double.valueOf(line.getNoNumNow());
						}
						// 处理前次不合格数量
						int res = this.cashProdAccountDao.selectCashProdAccountCount(head.getBillno(), line.getItemClassId(), DictConstant.BILL_JJ_TL, DictConstant.BILL_INVALID_PROD_MTYPE);
						if(res >0){
							if(noNumLast >0 ){
								// 修改前次不合格台账为 已 销账
								this.cashProdAccountDao.modifyCashProdAccountChecked(head.getBillno(), line.getItemClassId(), userId, DictConstant.BILL_JJ_TL, DictConstant.BILL_INVALID_PROD_MTYPE);
								// 写负的 前次不合格 台账
								CashProdAccount prod = new CashProdAccount();
								prod.setBillNums("-"+noNumLast);
								prod.setCashNums("-"+noNumLast);
								prod.setDotype(DictConstant.BILL_JJ_TL);
								prod.setIsChecked("1");
								prod.setMaterialType(DictConstant.BILL_INVALID_PROD_MTYPE);
								createCashProdAccount(head, line, prod, userId);
								this.cashProdAccountDao.saveCashProdAccount(prod);
							}
						} 
						// 处理本次不合格数量
						// 查询 是否已经写入过 本次不合格台账
						res = this.cashProdAccountDao.selectCashProdAccountCount(head.getBillno(), line.getItemClassId(), DictConstant.BILL_JJ_FL, DictConstant.BILL_INVALID_PROD_MTYPE);
						if(res >0){
							if(noNumNow >0){
								// 修改 之前 保存 本次不合格台账为 已 销账
								this.cashProdAccountDao.modifyCashProdAccountChecked(head.getBillno(), line.getItemClassId(), userId, DictConstant.BILL_JJ_FL, DictConstant.BILL_INVALID_PROD_MTYPE);
								
								// 写负的 本次不合格 台账
								CashProdAccount prod = new CashProdAccount();
								prod.setBillNums("-"+noNumNow);
								prod.setCashNums("-"+noNumNow);
								prod.setDotype(DictConstant.BILL_JJ_FL);
								prod.setIsChecked("1");
								prod.setMaterialType(DictConstant.BILL_INVALID_PROD_MTYPE);
								createCashProdAccount(head, line, prod, userId);
								this.cashProdAccountDao.saveCashProdAccount(prod);
							}
						}
					}
				}
				for(HandoverLine line : lines){
					double noNumLast = 0, noNumNow = 0;
					if(StringUtil.isNotBlank(line.getNoNumLast())){
						noNumLast = Double.valueOf(line.getNoNumLast());
					}
					if(StringUtil.isNotBlank(line.getNoNumNow())){
						noNumNow = Double.valueOf(line.getNoNumNow());
					}
					if(noNumLast >0 && !line.getItemClassId().equals(line.getOldItemClassId())){
						// 处理前次不合格数量
						int res = this.cashProdAccountDao.selectCashProdAccountCount(head.getBillno(), line.getOldItemClassId(), DictConstant.BILL_JJ_TL, DictConstant.BILL_INVALID_PROD_MTYPE);
						if(res >0){
							// 修改前次不合格台账为 已 销账
							this.cashProdAccountDao.modifyCashProdAccountChecked(head.getBillno(), line.getOldItemClassId(), userId, DictConstant.BILL_JJ_TL, DictConstant.BILL_INVALID_PROD_MTYPE);
							// 写负的 前次不合格 台账
							CashProdAccount prod = new CashProdAccount();
							prod.setBillNums("-"+noNumLast);
							prod.setCashNums("-"+noNumLast);
							prod.setDotype(DictConstant.BILL_JJ_TL);
							prod.setIsChecked("1");
							prod.setMaterialType(DictConstant.BILL_INVALID_PROD_MTYPE);
							createCashProdAccount(head, line, prod, userId, line.getOldItemClassId());
							this.cashProdAccountDao.saveCashProdAccount(prod);
						}
						
						CashProdAccount prod = new CashProdAccount();
						prod.setBillNums(noNumLast+"");
						prod.setCashNums("0");
						prod.setNoNums(noNumLast+"");
						prod.setDotype(DictConstant.BILL_JJ_TL);
						prod.setIsChecked("0");
						prod.setMaterialType(DictConstant.BILL_INVALID_PROD_MTYPE);
						createCashProdAccount(head, line, prod, userId);
						this.cashProdAccountDao.saveCashProdAccount(prod);
					}
					
					if(noNumNow > 0 && !line.getItemClassId().equals(line.getOldItemClassId())){
						// 处理本次不合格数量
						// 查询 是否已经写入过 本次不合格台账
						int res = this.cashProdAccountDao.selectCashProdAccountCount(head.getBillno(), line.getOldItemClassId(), DictConstant.BILL_JJ_FL, DictConstant.BILL_INVALID_PROD_MTYPE);
						if(res >0){
							if(noNumNow >0){
								// 修改 之前 保存 本次不合格台账为 已 销账
								this.cashProdAccountDao.modifyCashProdAccountChecked(head.getBillno(), line.getOldItemClassId(), userId, DictConstant.BILL_JJ_FL, DictConstant.BILL_INVALID_PROD_MTYPE);
								// 写负的 本次不合格 台账
								CashProdAccount prod = new CashProdAccount();
								prod.setBillNums("-"+noNumNow);
								prod.setCashNums("-"+noNumNow);
								prod.setDotype(DictConstant.BILL_JJ_FL);
								prod.setIsChecked("1");
								prod.setMaterialType(DictConstant.BILL_INVALID_PROD_MTYPE);
								createCashProdAccount(head, line, prod, userId, line.getOldItemClassId());
								this.cashProdAccountDao.saveCashProdAccount(prod);
							}
						}
						CashProdAccount prod = new CashProdAccount();
						prod.setBillNums(line.getNoNumNow());
						prod.setCashNums("0");
						prod.setNoNums(line.getNoNumNow());
						prod.setDotype(DictConstant.BILL_JJ_FL);
						prod.setIsChecked("0");
						prod.setMaterialType(DictConstant.BILL_INVALID_PROD_MTYPE);
						createCashProdAccount(head, line, prod, userId);
						this.cashProdAccountDao.saveCashProdAccount(prod);
					}
				}
			}
			// ------------------------------------------------------------------------------
			// -----------------------------------修改数据-----------------------------------
			// 先备份行信息
			this.handoverDao.backupHandoverLine(head.getBillid(), userId);
			// 备份孙表
			this.handoverDao.backupHandoverChild(head.getBillid(), userId);
			
			this.handoverDao.changeHandoverHead(head, userId);
			if(StringUtil.isNotBlank(deleteLineIds)){
				String lineIds[] = deleteLineIds.split(",");
				for(String lineid : lineIds){
					this.handoverChildDao.deleteHandoverChildByLineid(lineid);
					this.handoverDao.deleteHandoverLine(lineid);
				}
			}
			for(HandoverLine line : lines){
				this.handoverDao.changeHandoverLine(line, userId);
				// 删除需要删除的孙表
				if(StringUtil.isNotBlank(line.getDeleteChildIds())){
					String childIds [] = line.getDeleteChildIds().split(";");
					for(String childId : childIds){
						if(StringUtil.isNotBlank(childId)){
							this.handoverChildDao.deleteHandoverChild(childId);
						}
					}
				}
				if(CollectionUtils.isNotEmpty(line.getChildren()) && DictConstant.ITEM_CLASS_YUANLIAOZUANSHI.equals(line.getItemClassId())){
					for(HandoverChild child : line.getChildren()){
						child.setUpdateDate(DateUtil.getCurrentDate18());
						child.setUpdateId(userId);
						if(StringUtil.isBlank(child.getLineid())){
							child.setLineid(line.getLineid());
						}
						if(StringUtil.isNotBlank(child.getChildid())){
							child.setLineid(line.getLineid());
							this.handoverChildDao.updateHandoverChild(child);
						} else {
							child.setCreateDate(DateUtil.getCurrentDate18());
							child.setCreateId(userId);
							this.handoverChildDao.saveHandoverChild(child);
						}
					}
				} else {
					this.handoverChildDao.deleteHandoverChildByLineid(line.getLineid());
				}
			}
			result.put("isSuccess", "true");
		} else {
			result.put("isSuccess", "false");
			result.put("msg", "单据并非最新数据，请重新打开单据再进行修改");
		}
		return result;
	}

	public CommonDao getCommonDao() {
		return commonDao;
	}
	
	@Override
	public Pager getHandoverLineRecData(Map<String, String> condition) {
		return this.handoverDao.getHandoverLineRecData(condition);
	}
	@Override
	public Pager getHandoverChildRecData(Map<String, String> condition) {
		return this.handoverChildDao.getHandoverChildHistoryData(condition);
	}
	
	@Override
	public void insertLineByExit(String exitBillid, String handoverId,
			String userId) {
		this.handoverDao.insertLineByExit(exitBillid, handoverId, userId);
	}
}
