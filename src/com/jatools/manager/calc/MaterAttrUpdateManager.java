package com.jatools.manager.calc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jatools.common.Pager;
import com.jatools.vo.calc.MaterAttrUpdate;
import com.jatools.vo.stock.MaterActive;

public interface MaterAttrUpdateManager {
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
	/**
	 * 根据ID删除单据
	 * @param vendorId
	 */
	void deleteMaterAttrUpdate(String id);
	

	MaterActive getMaterByCode(Map<String,String> conditionMap);
	
	String saveUpdateMater(MaterAttrUpdate head ,HttpServletRequest req);
	
	String changeHeadStatus(String stutes,MaterAttrUpdate head ,HttpServletRequest req);
}
