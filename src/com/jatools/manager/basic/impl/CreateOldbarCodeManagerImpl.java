package com.jatools.manager.basic.impl;

import com.jatools.dao.basic.CreateOldbarCodeDao;
import com.jatools.manager.basic.CreateOldbarCodeManager;

public class CreateOldbarCodeManagerImpl implements CreateOldbarCodeManager {
	private CreateOldbarCodeDao createOldbarCodeDao;

	public void setCreateOldbarCodeDao(CreateOldbarCodeDao createOldbarCodeDao) {
		this.createOldbarCodeDao = createOldbarCodeDao;
	}

	/**
	 * 生成饰品条码
	 * @return
	 */
	public Long generateOldbarCode(String codeType, String year, String month, Long numCount, String userid){
		Long startIndex = createOldbarCodeDao.getStartIndex(codeType, year, month, userid);
		createOldbarCodeDao.updateStartIndex(codeType, year, month, userid, numCount+startIndex);
		return startIndex;
	}
}
