package com.jatools.manager.basic.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.basic.SaleChangeLabelDao;
import com.jatools.manager.basic.SaleChangeLabelManager;
import com.jatools.vo.basic.SaleChangeLabel;
import com.jatools.web.util.StringUtil;

/**
 * @author  ren.ming
 */
public class SaleChangeLabelManagerImpl implements SaleChangeLabelManager {
	
	private SaleChangeLabelDao saleChangeLabelDao;

	public void setSaleChangeLabelDao(SaleChangeLabelDao saleChangeLabelDao) {
		this.saleChangeLabelDao = saleChangeLabelDao;
	}


	public Pager getSaleChangeLabelPageData(Map<String, String> condition){
		return saleChangeLabelDao.getSaleChangeLabelPageData(condition);
	}
	
	public SaleChangeLabel getSaleChangeLabel(String changelabelId){
		return saleChangeLabelDao.getSaleChangeLabel(changelabelId);
	}
	
	public void deleteSaleChangeLabel(String changelabelIds){
		if(StringUtil.isNotBlank(changelabelIds)){
			String changelabelIdArr[] = changelabelIds.split(";");
			for(String changelabelId : changelabelIdArr){
				if(StringUtil.isNotBlank(changelabelId)){
					saleChangeLabelDao.deleteSaleChangeLabel(changelabelId);
				}
			}
		}
	}
	
	
	public void saveOrUpdateSaleChangeLabel(SaleChangeLabel saleChangeLabel){
		String changelabelId = saleChangeLabel.getChangelabelId();
		int count = this.saleChangeLabelDao.isExistsChangeLabel(saleChangeLabel);
		if(count > 0){
			throw new RuntimeException("换标签原因已经存在");
		}
		if(StringUtil.isEmpty(changelabelId)){
			saleChangeLabelDao.saveSaleChangeLabel(saleChangeLabel);
		}else{
			saleChangeLabelDao.updateSaleChangeLabel(saleChangeLabel);
		}
	}
}
