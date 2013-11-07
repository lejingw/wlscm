package com.jatools.manager.stock.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.dao.stock.MaterActiveDao;
import com.jatools.dao.stock.ProcInventoryDiffheadDao;
import com.jatools.dao.stock.ProcInventoryDifflineDao;
import com.jatools.dao.stock.ProcInventoryHeadDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.stock.ProcInventoryDiffheadManager;
import com.jatools.vo.stock.ProcInventoryDiffhead;
import com.jatools.vo.stock.ProcInventoryDiffline;
import com.jatools.vo.stock.ProcInventoryHead;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;
/**
 * 盘点差异单头表Manager 实现类
 * @author ren.ming
 * <br>
 * Created 2011-12-1
 */
public class ProcInventoryDiffheadManagerImpl implements ProcInventoryDiffheadManager {
	
	private ProcInventoryDiffheadDao procInventoryDiffheadDao;
	private ProcInventoryDifflineDao procInventoryDifflineDao;
	private ProcInventoryHeadDao procInventoryHeadDao;
	private MaterActiveDao materActiveDao;
	private CommonDao commonDao;
	
	@Override
	public Pager getProcInventoryDiffheadData(Map<String, String> condition) {
		return this.procInventoryDiffheadDao.getProcInventoryDiffheadData(condition);
	}

	@Override
	public void saveProcInventoryDiffhead(ProcInventoryDiffhead procInventoryDiffhead) {
		this.procInventoryDiffheadDao.saveProcInventoryDiffhead(procInventoryDiffhead);

	}

	@Override
	public ProcInventoryDiffhead getProcInventoryDiffhead(String billid) {
		return this.procInventoryDiffheadDao.getProcInventoryDiffhead(billid);
	}

	@Override
	public void updateProcInventoryDiffhead(ProcInventoryDiffhead procInventoryDiffhead) {
		this.procInventoryDiffheadDao.updateProcInventoryDiffhead(procInventoryDiffhead);

	}
	
	@Override
	public void deleteProcInventoryDiffhead(String billid) {
		List<ProcInventoryDiffline> oldLines = this.procInventoryDifflineDao.getProcInventoryDifflineList(billid);
		
		for(ProcInventoryDiffline oldLine : oldLines) {// 修改现有量表数据状态
			this.materActiveDao.markMaterActiveValid(oldLine.getOrnaCode());
		}
		this.procInventoryDifflineDao.deleteProcInventoryDifflineByBillid(billid);
		this.procInventoryDiffheadDao.deleteProcInventoryDiffhead(billid);
	}
	
	
	public void saveOrUpdateProcInventoryDiffhead(ProcInventoryDiffhead procInventoryDiffhead, List<ProcInventoryDiffline> lines, String ids[]) {
		// 1、先把头表保存
		if (StringUtil.isNotBlank(procInventoryDiffhead.getBillid())) {
			this.procInventoryDiffheadDao.updateProcInventoryDiffhead(procInventoryDiffhead);
		} else {
			this.procInventoryDiffheadDao.saveProcInventoryDiffhead(procInventoryDiffhead);
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
							this.procInventoryDifflineDao.deleteProcInventoryDiffline(lineid);
							// TODO 修改现有量表数据状态为 900 ren.ming 完成
							
							this.materActiveDao.markMaterActiveValid(ornaCode);
						}
					}
				}
			}
		}
		
	}

	public void closeBill(String billid, String userId) {
		// TODO : 1、修改状态为关闭
		
		this.procInventoryDiffheadDao.modifyProcInventoryDiffheadStatus(billid, DictConstant.BILL_STATUS_CLOSED, userId);
	}
	
	public void createDiffBill(String srcBillid, String userId) {
		if(StringUtil.isNotBlank(srcBillid)){
			// 1、插入差异单头信息
			String billno = commonDao.getBillno(GlobalConstant.BILL_CODE_PANDIANCHAYI);
			String billid = this.procInventoryDiffheadDao.getBillid();
			this.procInventoryDiffheadDao.insertProcInventoryDiffhead(srcBillid, billid, billno, userId);
			
			// 2、插入盘亏行
			this.procInventoryDifflineDao.insertDiffLinesByInvent(srcBillid, billid, userId);
			
			// 3、插入盘盈行
			this.procInventoryDifflineDao.insertDiffSignLines(srcBillid, billid, userId);
			
			// 4、修改头的行计数和成本
			this.procInventoryDiffheadDao.updateDiffHeadInfo(billid, userId);
			
		} else {
			throw new RuntimeException("创建差异单失败");
		}
	}
	
	/**
	 * <pre>
	 * 由于执行效率偏低所以已经对此方法进行了改进
	 * 请参考 <a>createDiffBill</a>方法
	 * </pre>
	 * @deprecated
	 * @param billid
	 * @param userId
	 */
	public void createDiffBillOld(String billid, String userId) {
		ProcInventoryHead inventHead = this.procInventoryHeadDao.getProcInventoryHead(billid);
		if(null != inventHead ) {
			ProcInventoryDiffhead diffHead = new ProcInventoryDiffhead();
			String billno = commonDao.getBillno(GlobalConstant.BILL_CODE_PANDIANCHAYI);
			int noStockCount =  this.procInventoryDifflineDao.getNoStockCount(billid, inventHead.getOrgId(), inventHead.getStockId());
			
			diffHead.setBillno(billno);
			diffHead.setDodate(DateUtil.getCurrentDate10());
			diffHead.setCreateDate(DateUtil.getCurrentDate18());
			diffHead.setCreateId(userId);
			diffHead.setDodate(DateUtil.getCurrentDate10());
			diffHead.setGroups(inventHead.getGroups());
			diffHead.setOrgId(inventHead.getOrgId());
			diffHead.setSourceNo(inventHead.getBillno());
			diffHead.setStockId(inventHead.getStockId());
			diffHead.setSumCost(inventHead.getSumCost());
			diffHead.setSumCount(noStockCount + "");
			diffHead.setUpdateDate(DateUtil.getCurrentDate18());
			diffHead.setUpdateId(userId);
			diffHead.setStatus(DictConstant.BILL_STATUS_CLOSED);
			this.procInventoryDiffheadDao.saveProcInventoryDiffhead(diffHead);
			
			Map<String, String> params = new HashMap<String, String>();
			params.put("billid", diffHead.getBillid());
			params.put("oldBillid", inventHead.getBillid());
			params.put("createId", userId);
			params.put("updateId", userId);
			params.put("orgId", diffHead.getOrgId());	
			params.put("stockId", diffHead.getStockId());
			params.put("createDate", DateUtil.getCurrentDate18());
			params.put("updateDate", DateUtil.getCurrentDate18());
			this.procInventoryDifflineDao.insertDiffLines(params);
			
			// 创建盘盈 行表
			this.procInventoryDifflineDao.insertDiffSignLines(billid, diffHead.getBillid(), userId);
		}
	}

	public void setProcInventoryDiffheadDao(ProcInventoryDiffheadDao procInventoryDiffheadDao) {
		this.procInventoryDiffheadDao = procInventoryDiffheadDao;
	}

	public void setMaterActiveDao(MaterActiveDao materActiveDao) {
		this.materActiveDao = materActiveDao;
	}

	
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	
	public void setProcInventoryHeadDao(ProcInventoryHeadDao procInventoryHeadDao) {
		this.procInventoryHeadDao = procInventoryHeadDao;
	}
	
	public void setProcInventoryDifflineDao(ProcInventoryDifflineDao procInventoryDifflineDao) {
		this.procInventoryDifflineDao = procInventoryDifflineDao;
	}
}
