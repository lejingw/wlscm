package com.jatools.web.dwr.basic;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.manager.basic.SaleChangeLabelManager;
import com.jatools.vo.basic.SaleChangeLabel;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class SaleChangeLabelDwr {
	private static Logger logger = Logger.getLogger(SaleChangeLabelDwr.class);
	
	private SaleChangeLabelManager saleChangeLabelManager;
	
	/**
	 * @param SaleChangeLabel
	 */
	public Map<String, String> saveOrUpdateSaleChangeLabel(SaleChangeLabel saleChangeLabel, HttpServletRequest req){
		Map<String, String> result = new HashMap<String, String>();
		try {
			if(StringUtil.isEmpty(saleChangeLabel.getChangelabelId())){
				saleChangeLabel.setCreatedate(DateUtil.getCurrentDate18());
				saleChangeLabel.setCreateuserid(CommonUtil.getSessionUserId(req));
			}
			saleChangeLabel.setUpdatedate(DateUtil.getCurrentDate18());
			saleChangeLabel.setUpdateuserid(CommonUtil.getSessionUserId(req));
			saleChangeLabelManager.saveOrUpdateSaleChangeLabel(saleChangeLabel);
			result.put("isSuccess", "true");
			result.put("changelabelId", saleChangeLabel.getChangelabelId());
			return result;
		} catch (Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "保存出错");
			}
			return result;
		}
	}
	
	public String deleteSaleChangeLabel(String changelabelId, HttpServletRequest req){
		try {
			if(StringUtil.isNotEmpty(changelabelId)){
				saleChangeLabelManager.deleteSaleChangeLabel(changelabelId);
			}
			return null;
		} catch (Exception e) {
			logger.error(e);
			return "删除失败";
		}
	}

	public void setSaleChangeLabelManager(SaleChangeLabelManager saleChangeLabelManager) {
		this.saleChangeLabelManager = saleChangeLabelManager;
	}
}
