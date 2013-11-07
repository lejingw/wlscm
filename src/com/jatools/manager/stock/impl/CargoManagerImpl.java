package com.jatools.manager.stock.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.stock.CargoDao;
import com.jatools.manager.stock.CargoManager;
import com.jatools.vo.stock.PackageMaterActive;

public class CargoManagerImpl implements CargoManager {

	private CargoDao cargoDao;
	
	public void setCargoDao(CargoDao cargoDao) {
		this.cargoDao = cargoDao;
	}

	@Override
	public Pager getCargoMaterPager(Map<String, String> condition) {
		return this.cargoDao.getCargoMaterPager(condition);
	}

	@Override
	public PackageMaterActive getMaterByOrnaCode(String ornaCode) {
		return this.cargoDao.getMaterByOrnaCode(ornaCode);
	}

	@Override
	public PackageMaterActive getMaterByOrnaBarCode(String ornaBarCode) {
		return this.cargoDao.getMaterByOrnaBarCode(ornaBarCode);
	}

}
