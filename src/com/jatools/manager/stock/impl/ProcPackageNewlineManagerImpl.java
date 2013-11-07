package com.jatools.manager.stock.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.stock.ProcPackageNewlineDao;
import com.jatools.manager.stock.ProcPackageNewlineManager;
import com.jatools.vo.stock.ProcPackageNewline;
import com.jatools.web.util.StringUtil;

public class ProcPackageNewlineManagerImpl implements ProcPackageNewlineManager{
	private ProcPackageNewlineDao procPackageNewlineDao;
	
	public void setProcPackageNewlineDao(ProcPackageNewlineDao packageNewlineDao) {
		this.procPackageNewlineDao = packageNewlineDao;
	}

	@Override
	public Pager getProcPackageNewlineData(Map<String, String> condition) {
		return this.procPackageNewlineDao.getProcPackageNewlineData(condition);
	}

	@Override
	public void saveProcPackageNewline(ProcPackageNewline procPackageNewline) {
		this.procPackageNewlineDao.saveProcPackageNewline(procPackageNewline);
	}

	@Override
	public ProcPackageNewline getProcPackageNewline(String lineid) {
		return this.procPackageNewlineDao.getProcPackageNewline(lineid);
	}

	@Override
	public void updateProcPackageNewline(ProcPackageNewline procPackageNewline) {
		this.procPackageNewlineDao.updateProcPackageNewline(procPackageNewline);
	}

	@Override
	public void deleteProcPackageNewline(String lineid) {
		this.procPackageNewlineDao.deleteProcPackageNewline(lineid);
		
	}
	
	public void deleteProcPackageNewlineByBillid(String billid) {
		this.procPackageNewlineDao.deleteProcPackageNewlineByBillid(billid);
		
	}

	@Override
	public void saveOrUpdateProcPackageNewline(
			ProcPackageNewline procPackageNewline) {
		if (StringUtil.isNotBlank(procPackageNewline.getLineid())) {
			this.procPackageNewlineDao.updateProcPackageNewline(procPackageNewline);
		} else {
			this.procPackageNewlineDao.saveProcPackageNewline(procPackageNewline);
		}
	}
	
	public List<ProcPackageNewline> getProcPackageNewlineList(String billid){
		return this.procPackageNewlineDao.getProcPackageNewlineList(billid);
	}

}
