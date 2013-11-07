package com.jatools.manager.calc.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.calc.AccessoryDao;
import com.jatools.manager.calc.AccessoryManager;
import com.jatools.vo.calc.Accessory;

public class AccessoryManagerImpl implements AccessoryManager {

	private AccessoryDao accessoryDao;
	
	public AccessoryDao getAccessoryDao() {
		return accessoryDao;
	}

	public void setAccessoryDao(AccessoryDao accessoryDao) {
		this.accessoryDao = accessoryDao;
	}

	@Override
	public Pager getAccessoryPageData(Map<String, String> condition) {
		return accessoryDao.getAccessoryPageData(condition);
	}

	@Override
	public void saveAccessory(Accessory accessory) {
		accessoryDao.saveAccessory(accessory);
	}

	@Override
	public Accessory getAccessoryById(String id) {
		return accessoryDao.getAccessoryById(id);
	}

	@Override
	public void updateAccessory(Accessory accessory) {
		accessoryDao.updateAccessory(accessory);
	}

	@Override
	public void deleteAccessory(String id) {
		accessoryDao.deleteAccessory(id);
	}

	@Override
	public Accessory getAccessory(Accessory a) {
		return accessoryDao.getAccessory(a);
	}

}
