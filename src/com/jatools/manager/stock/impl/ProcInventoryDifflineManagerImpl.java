package com.jatools.manager.stock.impl;

import java.util.List;
import java.util.Map;
import com.jatools.common.Pager;
import com.jatools.dao.stock.ProcInventoryDifflineDao;
import com.jatools.manager.stock.ProcInventoryDifflineManager;
import com.jatools.vo.stock.ProcInventoryDiffline;
import com.jatools.web.util.StringUtil;

public class ProcInventoryDifflineManagerImpl implements ProcInventoryDifflineManager {

	private ProcInventoryDifflineDao procInventoryDifflineDao;
	
	public void setProcInventoryDifflineDao(ProcInventoryDifflineDao procInventoryDifflineDao) {
		this.procInventoryDifflineDao = procInventoryDifflineDao;
	}

	@Override
	public Pager getProcInventoryDifflineData(Map<String, String> condition) {
		return this.procInventoryDifflineDao.getProcInventoryDifflineData(condition);
	}

	@Override
	public void saveProcInventoryDiffline(ProcInventoryDiffline procInventoryDiffline) {
		this.procInventoryDifflineDao.saveProcInventoryDiffline(procInventoryDiffline);

	}

	@Override
	public ProcInventoryDiffline getProcInventoryDiffline(String lineid) {
		return this.procInventoryDifflineDao.getProcInventoryDiffline(lineid);
	}

	@Override
	public void updateProcInventoryDiffline(ProcInventoryDiffline procInventoryDiffline) {
		this.procInventoryDifflineDao.updateProcInventoryDiffline(procInventoryDiffline);

	}

	@Override
	public void deleteProcInventoryDiffline(String lineid) {
		this.procInventoryDifflineDao.deleteProcInventoryDiffline(lineid);

	}
	
	public void deleteProcInventoryDifflineByBillid(String billid) {
		this.procInventoryDifflineDao.deleteProcInventoryDifflineByBillid(billid);

	}

	@Override
	public void saveOrUpdateProcInventoryDiffline(ProcInventoryDiffline procInventoryDiffline) {
		if (StringUtil.isNotBlank(procInventoryDiffline.getLineid())) {
			this.procInventoryDifflineDao.updateProcInventoryDiffline(procInventoryDiffline);
		} else {
			this.procInventoryDifflineDao.saveProcInventoryDiffline(procInventoryDiffline);
		}
	}
	
	public List<ProcInventoryDiffline> getProcInventoryDifflineList(String billid) {
		return this.procInventoryDifflineDao.getProcInventoryDifflineList(billid);
	}

	
	public int getNoStockCount(String billid, String orgId, String stockId) {
		return this.procInventoryDifflineDao.getNoStockCount(billid, orgId, stockId);
	}
}
