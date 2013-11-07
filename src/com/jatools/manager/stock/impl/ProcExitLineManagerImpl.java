package com.jatools.manager.stock.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.stock.ProcExitLineDao;
import com.jatools.manager.stock.ProcExitLineManager;
import com.jatools.vo.stock.ProcExitLine;
import com.jatools.web.util.StringUtil;

public class ProcExitLineManagerImpl implements ProcExitLineManager {

	private ProcExitLineDao procExitLineDao;
	
	public void setProcExitLineDao(ProcExitLineDao ProcExitLineDao) {
		this.procExitLineDao = ProcExitLineDao;
	}

	@Override
	public Pager getProcExitLineData(Map<String, String> condition) {
		return this.procExitLineDao.getProcExitLineData(condition);
	}

	@Override
	public void saveProcExitLine(ProcExitLine procExitLine) {
		this.procExitLineDao.saveProcExitLine(procExitLine);

	}

	@Override
	public ProcExitLine getProcExitLine(String lineid) {
		return this.procExitLineDao.getProcExitLine(lineid);
	}

	@Override
	public void updateProcExitLine(ProcExitLine procExitLine) {
		this.procExitLineDao.updateProcExitLine(procExitLine);

	}
	
	public void deleteProcExitLineByBillid(String billid) {
		this.procExitLineDao.deleteProcExitLineByBillid(billid);

	}

	@Override
	public void saveOrUpdateProcExitLine(ProcExitLine procExitLine) {
		if (StringUtil.isNotBlank(procExitLine.getLineid())) {
			this.procExitLineDao.updateProcExitLine(procExitLine);
		} else {
			this.procExitLineDao.saveProcExitLine(procExitLine);
		}
	}
	
	public List<ProcExitLine> getProcExitLineList(String billid) {
		return this.procExitLineDao.getProcExitLineList(billid);
	}

}
