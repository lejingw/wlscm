package com.jatools.manager.common.impl;

import com.jatools.dao.common.SelectCommonDao;
import com.jatools.manager.common.SelectCommonManager;

public class SelectCommonManagerImpl implements SelectCommonManager {
	private SelectCommonDao selectCommonDao;

	public void setSelectCommonDao(SelectCommonDao selectCommonDao) {
		this.selectCommonDao = selectCommonDao;
	}

}
