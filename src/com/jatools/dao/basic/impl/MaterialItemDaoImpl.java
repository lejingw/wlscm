package com.jatools.dao.basic.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.basic.MaterialItemDao;
import com.jatools.vo.basic.MaterialItem;

public class MaterialItemDaoImpl extends BaseDao implements MaterialItemDao {

	public Pager getMaterialItemPageData(Map<String, String> condition){
		Pager pager = executeQueryForPager("MaterialItem.getMaterialItemPageData", "MaterialItem.getMaterialItemTotalCount", condition);
		return pager;
	}
	
	
	public void saveMaterialItem(MaterialItem materialItem){
		executeInsert("MaterialItem.saveMaterialItem", materialItem);
	}
	
	
	public MaterialItem getMaterialItem(String materialItemId){
		MaterialItem rule = (MaterialItem) executeQueryForObject("MaterialItem.getMaterialItem", materialItemId);
		return rule;
	}
	
	
	public void updateMaterialItem(MaterialItem materialItem){
		executeUpdate("MaterialItem.updateMaterialItem", materialItem);
	}
	
	
	public void deleteMaterialItem(String materialItemId){
		executeUpdate("MaterialItem.deleteMaterialItem", materialItemId);
	}


	@Override
	public boolean isExistsmaterial(MaterialItem MaterialItem) {
		Integer count = (Integer)this.executeQueryForObject("MaterialItem.selectCount", MaterialItem);
		if(count != null && count.intValue() >0){
			return true;
		}
		return false;
	}
}
