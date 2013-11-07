package com.jatools.dao.stock.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.stock.CargoDao;
import com.jatools.vo.stock.PackageMaterActive;

public class CargoDaoImpl extends BaseDao implements CargoDao {

	@Override
	public Pager getCargoMaterPager(Map<String, String> condition) {
		return executeQueryForPager("CargoManager.getCargoMaterPageData", "CargoManager.getCargoMaterTotalCount", condition);
	}

	@Override
	public PackageMaterActive getMaterByOrnaCode(String ornaCode) {
		return (PackageMaterActive)executeQueryForObject("CargoManager.getMaterByOrnaCode", ornaCode);
	}

	@Override
	public PackageMaterActive getMaterByOrnaBarCode(String ornaBarCode) {
		return (PackageMaterActive)executeQueryForObject("CargoManager.getMaterByOrnaBarCode", ornaBarCode);
	}
}
