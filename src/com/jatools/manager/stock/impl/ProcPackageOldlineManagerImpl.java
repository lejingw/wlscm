package com.jatools.manager.stock.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.stock.ProcPackageOldlineDao;
import com.jatools.manager.stock.ProcPackageOldlineManager;
import com.jatools.vo.stock.ProcPackageOldline;
import com.jatools.web.util.StringUtil;

public class ProcPackageOldlineManagerImpl implements ProcPackageOldlineManager {

	private ProcPackageOldlineDao procPackageOldlineDao;
	
	public void setProcPackageOldlineDao(ProcPackageOldlineDao procPackageOldlineDao) {
		this.procPackageOldlineDao = procPackageOldlineDao;
	}

	@Override
	public Pager getProcPackageOldlineData(Map<String, String> condition) {
		return this.procPackageOldlineDao.getProcPackageOldlineData(condition);
	}

	@Override
	public void saveProcPackageOldline(ProcPackageOldline procPackageOldline) {
		this.procPackageOldlineDao.saveProcPackageOldline(procPackageOldline);

	}

	@Override
	public ProcPackageOldline getProcPackageOldline(String lineid) {
		return this.procPackageOldlineDao.getProcPackageOldline(lineid);
	}

	@Override
	public void updateProcPackageOldline(ProcPackageOldline procPackageOldline) {
		this.procPackageOldlineDao.updateProcPackageOldline(procPackageOldline);

	}

	@Override
	public void deleteProcPackageOldline(String lineid) {
		this.procPackageOldlineDao.deleteProcPackageOldline(lineid);

	}
	
	public void deleteProcPackageOldlineByBillid(String billid) {
		this.procPackageOldlineDao.deleteProcPackageOldlineByBillid(billid);

	}

	@Override
	public void saveOrUpdateProcPackageOldline(
			ProcPackageOldline procPackageOldline) {
		if (StringUtil.isNotBlank(procPackageOldline.getLineid())) {
			this.procPackageOldlineDao.updateProcPackageOldline(procPackageOldline);
		} else {
			this.procPackageOldlineDao.saveProcPackageOldline(procPackageOldline);
		}
	}
	
	public List<ProcPackageOldline> getProcPackageOldlineList(String billid) {
		return this.procPackageOldlineDao.getProcPackageOldlineList(billid);
	}

}
