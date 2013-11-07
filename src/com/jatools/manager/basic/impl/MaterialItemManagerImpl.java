package com.jatools.manager.basic.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.basic.MaterialItemDao;
import com.jatools.manager.basic.MaterialItemManager;
import com.jatools.vo.basic.MaterialItem;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class MaterialItemManagerImpl implements MaterialItemManager {

	private MaterialItemDao materialItemDao;
	
	public void setMaterialItemDao(MaterialItemDao materialItemDao) {
		this.materialItemDao = materialItemDao;
	}

	@Override
	public Pager getMaterialItemPageData(Map<String, String> condition) {
		return this.materialItemDao.getMaterialItemPageData(condition);
	}

	@Override
	public void saveOrUpdateMaterialItem(MaterialItem materialItem, String userId) {
		boolean isExists = this.materialItemDao.isExistsmaterial(materialItem);
		if(isExists) {
			throw new RuntimeException("保存失败，原料类型-大类-结算单位 已经存在");
		}
		materialItem.setUpdateDate(DateUtil.getCurrentDate18());
		materialItem.setUpdateId(userId);
		if(StringUtil.isNotBlank(materialItem.getMaterialItemId())){
			this.materialItemDao.updateMaterialItem(materialItem);
		} else {
			materialItem.setCreateDate(DateUtil.getCurrentDate18());
			materialItem.setCreateId(userId);
			this.materialItemDao.saveMaterialItem(materialItem);
		}

	}

	@Override
	public MaterialItem getMaterialItem(String materialItemId) {
		return this.materialItemDao.getMaterialItem(materialItemId);
	}

	@Override
	public void deleteMaterialItem(String materialItemIds) {
		if(StringUtil.isNotBlank(materialItemIds)){
			String itemIdArr[] = materialItemIds.split(";");
			for(String materialItemId : itemIdArr ){
				if(StringUtil.isNotBlank(materialItemId)){
					this.materialItemDao.deleteMaterialItem(materialItemId);
				}
			}
		}
	}
}
