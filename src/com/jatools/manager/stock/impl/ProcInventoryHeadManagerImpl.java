package com.jatools.manager.stock.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.dao.stock.ProcInventoryHeadDao;
import com.jatools.dao.stock.ProcInventoryLineDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.stock.ProcInventoryHeadManager;
import com.jatools.vo.stock.ProcInventoryHead;
import com.jatools.vo.stock.ProcInventoryLine;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;
/**
 * 盘点单头表Manager 实现类
 * @author ren.ming
 * <br>
 * Created 2011-12-1
 */
public class ProcInventoryHeadManagerImpl extends BaseManager implements ProcInventoryHeadManager {
	
	private ProcInventoryHeadDao procInventoryHeadDao;
	private ProcInventoryLineDao procInventoryLineDao;
	
	@Override
	public Pager getProcInventoryHeadData(Map<String, String> condition) {
		return this.procInventoryHeadDao.getProcInventoryHeadData(condition);
	}

	@Override
	public void saveProcInventoryHead(ProcInventoryHead ProcInventoryHead) {
		this.procInventoryHeadDao.saveProcInventoryHead(ProcInventoryHead);

	}

	@Override
	public ProcInventoryHead getProcInventoryHead(String billid) {
		return this.procInventoryHeadDao.getProcInventoryHead(billid);
	}

	@Override
	public void updateProcInventoryHead(ProcInventoryHead ProcInventoryHead) {
		this.procInventoryHeadDao.updateProcInventoryHead(ProcInventoryHead);

	}
	
	@Override
	public void deleteProcInventoryHead(String billid) {
		asertStatus("jat_proc_inventory_head", "billid", billid, "status", DictConstant.BILL_STATUS_SAVE);
		this.procInventoryLineDao.deleteProcInventoryLineByBillid(billid);
		this.procInventoryHeadDao.deleteProcInventoryHead(billid);
	}
	
	
	public void saveOrUpdateProcInventoryHead(ProcInventoryHead ProcInventoryHead, List<ProcInventoryLine> lines) {
		// 1、先把头表保存
		if (StringUtil.isNotBlank(ProcInventoryHead.getBillid())) {
			asertStatus("jat_proc_inventory_head", "billid", ProcInventoryHead.getBillid(), "status", DictConstant.BILL_STATUS_SAVE);
			this.procInventoryHeadDao.updateProcInventoryHead(ProcInventoryHead);
		} else {
			this.procInventoryHeadDao.saveProcInventoryHead(ProcInventoryHead);
		}
		
		for(ProcInventoryLine line : lines){
			if(StringUtil.isNotBlank(line.getLineid())) {
				line.setUpdateDate(ProcInventoryHead.getUpdateDate());
				line.setUpdateId(ProcInventoryHead.getUpdateId());
				this.procInventoryLineDao.updateProcInventoryLine(line);
			} else {
				line.setStatus(DictConstant.BILL_STATUS_SAVE);
				line.setBillid(ProcInventoryHead.getBillid());
				line.setCreateDate(DateUtil.getCurrentDate18());
				line.setCreateId(ProcInventoryHead.getUpdateId());
				line.setUpdateDate(ProcInventoryHead.getUpdateDate());
				line.setUpdateId(ProcInventoryHead.getUpdateId());
				this.procInventoryLineDao.saveProcInventoryLine(line);
			}
		}
		
		this.procInventoryHeadDao.updateInventorySumCount(ProcInventoryHead.getBillid(), ProcInventoryHead.getUpdateId());
	}

	public void closeInventoryHead(String billid, String userId) {
		asertStatus("jat_proc_inventory_head", "billid", billid, "status", DictConstant.BILL_STATUS_SAVE);
		// TODO : 1、修改状态为关闭
		this.procInventoryHeadDao.modifyProcInventoryHeadStatus(billid, DictConstant.BILL_STATUS_CLOSED, userId);;
	}
	
	public void openInventoryHead(String billid, String userId) {
		asertStatus("jat_proc_inventory_head", "billid", billid, "status", DictConstant.BILL_STATUS_CLOSED);
		boolean isMerged = this.procInventoryHeadDao.asertIsMerged(billid);
		if(isMerged){
			throw new RuntimeException("单据已经被合并到其他单据中，不允许重开");
		}
		// TODO : 1、修改状态为保存
		this.procInventoryHeadDao.modifyProcInventoryHeadStatus(billid, DictConstant.BILL_STATUS_SAVE, userId);;
	}
	
	public boolean changeIsmain(String billid, String orgId, String stockId, boolean ismain) {
		asertStatus("jat_proc_inventory_head", "billid", billid, "status", DictConstant.BILL_STATUS_SAVE);
		boolean isSuccess = Boolean.FALSE;
		// 判断orgId仓库是否已经有有主单  有则不允许设置
		if(ismain) { // 修改为主单
			Integer mainBillCount = this.procInventoryHeadDao.getMainBillCount(orgId, stockId);
			if(null != mainBillCount && mainBillCount.intValue() <= 0) {
				this.procInventoryHeadDao.modifyBillIsMain(billid, "1");
				isSuccess = Boolean.TRUE;
			}
		} else {// 取消主单
			this.procInventoryHeadDao.modifyBillIsMain(billid, "0");
			isSuccess = Boolean.TRUE;
		}
		return isSuccess;
	}
	
	public boolean mergeBills(String billids, String userId) {
		boolean isSuccess = Boolean.FALSE;
		String mainBillid = "";
		List<String> oldBillidList = new ArrayList<String>();
		if(StringUtil.isNotBlank(billids)) {
			String billidMains[] = billids.split(";");
			for(String billidMain : billidMains){
				if(StringUtil.isNotBlank(billidMain)){
					String billMain[] = billidMain.split(":");
					asertStatus("jat_proc_inventory_head", "billid", billMain[0], "status", DictConstant.BILL_STATUS_SAVE);
					if(!ArrayUtils.isEmpty(billMain) && billMain.length ==2) {
						if("1".equals(billMain[1])){
							mainBillid = billMain[0];
						} else {// 非主单行表
							String billid =  billMain[0];
							oldBillidList.add(billid);
						}
					}
				}
			}
		}
		
		if(StringUtil.isNotBlank(mainBillid) && CollectionUtils.isNotEmpty(oldBillidList)) {
			//StringBuffer oldBillIds = new StringBuffer();
			// 关闭其他单据
			for(String oldBillid : oldBillidList){
				/*if(oldBillIds.length()>0){
					oldBillIds.append(",").append(oldBillid);
				} else {
					oldBillIds.append(oldBillid);
				}*/
				this.procInventoryHeadDao.modifyProcInventoryHeadStatus(oldBillid, DictConstant.BILL_STATUS_CLOSED, userId);
				this.procInventoryHeadDao.mergeInventory(mainBillid, oldBillid, userId);
			}
			
			this.procInventoryHeadDao.updateInventorySumCount(mainBillid, userId);
			isSuccess = Boolean.TRUE;
		}
		return isSuccess;
	}
	
	/*public boolean mergeBills(String billids, String userId) {
		boolean isSuccess = Boolean.FALSE;
		String mainBillid = "";
		List<ProcInventoryLine> allLines = new ArrayList<ProcInventoryLine>();
		List<String> oldBillids = new ArrayList<String>();
		List<ProcInventoryLine> mainLines = new ArrayList<ProcInventoryLine>();// 主单所有行
		List<String> ornaCdeList = new ArrayList<String>();// 已经存在的饰品编码
		if(StringUtil.isNotBlank(billids)) {
			String billidMains[] = billids.split(";");
			for(String billidMain : billidMains){
				if(StringUtil.isNotBlank(billidMain)){
					String billMain[] = billidMain.split(":");
					if(!ArrayUtils.isEmpty(billMain) && billMain.length ==2) {
						if("1".equals(billMain[1])){
							mainBillid = billMain[0];
							mainLines.addAll(this.procInventoryLineDao.getProcInventoryLineList(mainBillid));
						} else {// 非主单行表
							String billid =  billMain[0];
							oldBillids.add(billid);
							allLines.addAll(this.procInventoryLineDao.getProcInventoryLineList(billid));
						}
					}
				}
			}
		}
		for(ProcInventoryLine line : mainLines){
			ornaCdeList.add(line.getOrnaCode());
		}
		if(StringUtil.isNotBlank(mainBillid)) {
			ProcInventoryHead head = this.getProcInventoryHead(mainBillid);
			int mainCount = Integer.valueOf(head.getSumCount());
			for(ProcInventoryLine newLine : allLines) { // 转移行表到 主单上
				if(!ornaCdeList.contains(newLine.getOrnaCode())){// 饰品未盘点过
					newLine.setOldBillid(newLine.getBillid());
					newLine.setBillid(mainBillid);
					newLine.setUpdateDate(DateUtil.getCurrentDate18());
					newLine.setUpdateId(userId);
					this.procInventoryLineDao.updateProcInventoryLine(newLine);
					mainCount = mainCount + 1;
					ornaCdeList.add(newLine.getOrnaCode());
				}
			}
			// 关闭其他单据
			for(String oldBillid : oldBillids){
				this.procInventoryHeadDao.modifyProcInventoryHeadStatus(oldBillid, DictConstant.BILL_STATUS_CLOSED, userId);
			}
			head.setSumCount(mainCount+"");
			head.setUpdateDate(DateUtil.getCurrentDate18());
			head.setUpdateId(userId);
			head.setDodate(head.getDodate().split(" ")[0]);
			this.procInventoryHeadDao.updateProcInventoryHead(head);
			isSuccess = Boolean.TRUE;
		}
		return isSuccess;
	}*/
	
	
	
	public void setProcInventoryLineDao(ProcInventoryLineDao ProcInventoryLineDao) {
		this.procInventoryLineDao = ProcInventoryLineDao;
	}


	public void setProcInventoryHeadDao(ProcInventoryHeadDao ProcInventoryHeadDao) {
		this.procInventoryHeadDao = ProcInventoryHeadDao;
	}

	private CommonDao commonDao;
	
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	@Override
	public CommonDao getCommonDao() {
		return this.commonDao;
	}
}
