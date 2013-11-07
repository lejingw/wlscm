package com.jatools.manager.stock.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.stock.ProcInventoryLineDao;
import com.jatools.manager.stock.ProcInventoryLineManager;
import com.jatools.vo.stock.ProcInventoryLine;
import com.jatools.web.util.StringUtil;

public class ProcInventoryLineManagerImpl implements ProcInventoryLineManager {

	private ProcInventoryLineDao procInventoryLineDao;
	
	public void setProcInventoryLineDao(ProcInventoryLineDao ProcInventoryLineDao) {
		this.procInventoryLineDao = ProcInventoryLineDao;
	}

	@Override
	public Pager getProcInventoryLineData(Map<String, String> condition) {
		return this.procInventoryLineDao.getProcInventoryLineData(condition);
	}

	@Override
	public void saveProcInventoryLine(ProcInventoryLine ProcInventoryLine) {
		this.procInventoryLineDao.saveProcInventoryLine(ProcInventoryLine);

	}

	@Override
	public ProcInventoryLine getProcInventoryLine(String lineid) {
		return this.procInventoryLineDao.getProcInventoryLine(lineid);
	}

	@Override
	public void updateProcInventoryLine(ProcInventoryLine ProcInventoryLine) {
		this.procInventoryLineDao.updateProcInventoryLine(ProcInventoryLine);

	}

	@Override
	public void deleteProcInventoryLine(String lineid) {
		this.procInventoryLineDao.deleteProcInventoryLine(lineid);

	}
	
	public void deleteProcInventoryLineByBillid(String billid) {
		this.procInventoryLineDao.deleteProcInventoryLineByBillid(billid);

	}

	@Override
	public void saveOrUpdateProcInventoryLine(ProcInventoryLine procInventoryLine) {
		if (StringUtil.isNotBlank(procInventoryLine.getBillid())) {
			this.procInventoryLineDao.updateProcInventoryLine(procInventoryLine);
		} else {
			this.procInventoryLineDao.saveProcInventoryLine(procInventoryLine);
		}
	}
	
	public List<ProcInventoryLine> getProcInventoryLineList(String billid) {
		return this.procInventoryLineDao.getProcInventoryLineList(billid);
	}

	public boolean isExistOrnaCode(String ornaCode, String billid) {
		return this.procInventoryLineDao.isExistOrnaCode(ornaCode, billid);
	}
}
