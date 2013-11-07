package com.jatools.manager.common.impl;

import com.jatools.dao.common.SelectStyleDao;
import com.jatools.manager.common.SelectStyleManager;

public class SelectStyleManagerImpl implements SelectStyleManager{
	private SelectStyleDao selectStyleDao;

	public void setSelectStyleDao(SelectStyleDao selectStyleDao) {
		this.selectStyleDao = selectStyleDao;
	}
	
}
