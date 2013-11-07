package com.jatools.dao.calc.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.calc.AccessoryDao;
import com.jatools.vo.calc.Accessory;

public class AccessoryDaoImpl extends BaseDao implements AccessoryDao {

	@Override
	public Pager getAccessoryPageData(Map<String, String> condition) {
		// TODO Auto-generated method stub
		return executeQueryForPager("Accessory.getAccessoryAllPageData", "Accessory.getAccessoryToalCount", condition);
	}

	@Override
	public void saveAccessory(Accessory accessory) {
		// TODO Auto-generated method stub
		executeInsert("Accessory.saveAccessory", accessory);	
	}

	@Override
	public Accessory getAccessoryById(String id) {
		// TODO Auto-generated method stub
		Accessory accessory = (Accessory)executeQueryForObject("Accessory.getAccessoryById", id);
		return accessory;
	}

	@Override
	public void updateAccessory(Accessory accessory) {
		executeUpdate("Accessory.updateAccessory", accessory);
	}

	@Override
	public void deleteAccessory(String id) {
		executeUpdate("Accessory.deleteAccessory", id);
	}

	/**
	 * 获取总系数
	 * @param sc
	 * @return
	 */
	public Accessory getAccessory(Accessory a){
		return (Accessory) executeQueryForObject("Accessory.getAccessory", a);
	}
}
