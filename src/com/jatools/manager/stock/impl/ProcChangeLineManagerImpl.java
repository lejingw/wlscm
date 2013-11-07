package com.jatools.manager.stock.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.stock.ProcChangeLineDao;
import com.jatools.manager.stock.ProcChangeLineManager;
import com.jatools.vo.stock.ProcChangeLine;
import com.jatools.web.util.StringUtil;

public class ProcChangeLineManagerImpl implements ProcChangeLineManager {

	private ProcChangeLineDao ProcChangeLineDao;
	
	public void setProcChangeLineDao(ProcChangeLineDao ProcChangeLineDao) {
		this.ProcChangeLineDao = ProcChangeLineDao;
	}

	@Override
	public Pager getProcChangeLineData(Map<String, String> condition) {
		return this.ProcChangeLineDao.getProcChangeLineData(condition);
	}

	@Override
	public void saveProcChangeLine(ProcChangeLine ProcChangeLine) {
		this.ProcChangeLineDao.saveProcChangeLine(ProcChangeLine);

	}

	@Override
	public ProcChangeLine getProcChangeLine(String lineid) {
		return this.ProcChangeLineDao.getProcChangeLine(lineid);
	}

	@Override
	public void updateProcChangeLine(ProcChangeLine ProcChangeLine) {
		this.ProcChangeLineDao.updateProcChangeLine(ProcChangeLine);

	}

	@Override
	public void deleteProcChangeLine(String lineid) {
		this.ProcChangeLineDao.deleteProcChangeLine(lineid);

	}
	
	public void deleteProcChangeLineByBillid(String billid) {
		this.ProcChangeLineDao.deleteProcChangeLineByBillid(billid);

	}

	@Override
	public void saveOrUpdateProcChangeLine(ProcChangeLine ProcChangeLine) {
		if (StringUtil.isNotBlank(ProcChangeLine.getLineid())) {
			this.ProcChangeLineDao.updateProcChangeLine(ProcChangeLine);
		} else {
			this.ProcChangeLineDao.saveProcChangeLine(ProcChangeLine);
		}
	}
	
	public List<ProcChangeLine> getProcChangeLineList(String billid) {
		return this.ProcChangeLineDao.getProcChangeLineList(billid);
	}

}
