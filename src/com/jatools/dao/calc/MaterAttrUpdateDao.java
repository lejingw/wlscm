package com.jatools.dao.calc;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.calc.MaterAttrUpdate;
import com.jatools.vo.stock.MaterActive;

public interface MaterAttrUpdateDao {
	/**
	 * 分页
	 * @param condition
	 * @return
	 */
	Pager getMaterAttrUpdatePageData(Map<String,String> conditionMap);
	/**
	 * 保存单据
	 */
	void saveMaterAttrUpdate(MaterAttrUpdate MaterAttrUpdate);
	/**
	 * 根据ID返回对象
	 * @param vendorId
	 * @return
	 */
	MaterAttrUpdate getMaterAttrUpdateById(String id);
	/**
	 * 保存修改数据
	 * @param vendor
	 */
	void updateMaterAttrUpdate(MaterAttrUpdate MaterAttrUpdate);
	void updateMaterActionByMB(MaterAttrUpdate MaterAttrUpdate);
	void updateMaterInivByMB(MaterAttrUpdate MaterAttrUpdate);
	void updatePriceByMB(MaterAttrUpdate MaterAttrUpdate);
	/**
	 * 根据ID删除单据
	 * @param vendorId
	 */
	void deleteMaterAttrUpdate(String id);
	
	MaterActive getMaterByCode(Map<String,String> conditionMap);
	
	void modifyMaterStatus(String billid, String status, String userid);
}
