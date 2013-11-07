package com.jatools.manager.stock;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.stock.PackageMaterActive;

public interface CargoManager {

	Pager getCargoMaterPager(Map<String, String> condition);

	PackageMaterActive getMaterByOrnaCode(String ornaCode);

	PackageMaterActive getMaterByOrnaBarCode(String ornaBarCode);
}
