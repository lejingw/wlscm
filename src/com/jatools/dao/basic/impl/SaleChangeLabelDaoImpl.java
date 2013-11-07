package com.jatools.dao.basic.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.basic.SaleChangeLabelDao;
import com.jatools.vo.basic.SaleChangeLabel;
/**
 * @author  ren.ming
 */
public class SaleChangeLabelDaoImpl extends BaseDao implements SaleChangeLabelDao{
	
	public Pager getSaleChangeLabelPageData(Map<String, String> condition){
		Pager pager = executeQueryForPager("SaleChangeLabel.getSaleChangeLabelPageData", "SaleChangeLabel.getSaleChangeLabelTotalCount", condition);
		return pager;
	}
	
	
	public void saveSaleChangeLabel(SaleChangeLabel saleChangeLabel){
		executeInsert("SaleChangeLabel.saveSaleChangeLabel", saleChangeLabel);
	}
	
	
	public SaleChangeLabel getSaleChangeLabel(String changelabelId){
		SaleChangeLabel rule = (SaleChangeLabel) executeQueryForObject("SaleChangeLabel.getSaleChangeLabel", changelabelId);
		return rule;
	}
	
	
	public void updateSaleChangeLabel(SaleChangeLabel saleChangeLabel){
		executeUpdate("SaleChangeLabel.updateSaleChangeLabel", saleChangeLabel);
	}
	
	
	public void deleteSaleChangeLabel(String changelabelId){
		executeUpdate("SaleChangeLabel.deleteSaleChangeLabel", changelabelId);
	}
	
	public int isExistsChangeLabel(SaleChangeLabel saleChangeLabel){
		Integer res = (Integer)executeQueryForObject("SaleChangeLabel.isExistsChangeLabel", saleChangeLabel);
		if(res != null){
			return res.intValue();
		}
		return 0;
	}
}
