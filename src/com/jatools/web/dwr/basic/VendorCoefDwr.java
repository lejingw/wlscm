package com.jatools.web.dwr.basic;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.manager.basic.VendorCoefManager;
import com.jatools.vo.basic.VendorCoef;
import com.jatools.web.dwr.basic.VendorCoefDwr;
import com.jatools.web.dwr.move.MoveSalecoefDwr;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class VendorCoefDwr {
	private static Logger logger = Logger.getLogger(MoveSalecoefDwr.class);
	private VendorCoefManager vendorCoefManager;
	public static void setLogger(Logger logger) {
		VendorCoefDwr.logger = logger;
	}
	public void setVendorCoefManager(
			VendorCoefManager vendorCoefManager) {
		this.vendorCoefManager = vendorCoefManager;
	}
	/**
	 * 保存修改操作
	 * @param head
	 * @param req
	 * @return
	 */
	public String saveOrUpdateVendorCoef(VendorCoef head ,HttpServletRequest req){
		try {
			Map<String, String> c = new HashMap<String, String>();
			c.put("vendorId", head.getVendorId());
			c.put("coefId", head.getCoefId());
			String rc = vendorCoefManager.getVendorCoefC(c);
			if(!"0".equals(rc)){
				return "2_保存出错,供应商不允许重复。";
			}
			if(StringUtil.isEmpty(head.getCoefId())||null==head.getCoef()){
				head.setCreateDate(DateUtil.getCurrentDate18());
				head.setCreateId(CommonUtil.getSessionUserId(req));
				vendorCoefManager.saveVendorCoef(head);
			}else{
				VendorCoef old = vendorCoefManager.getVendorCoefById(head.getCoefId());
				if(old!=null){
					head.setCreateDate(old.getCreateDate());
					head.setCreateId(old.getCreateId());
					head.setUpdateDate(DateUtil.getCurrentDate18());
					head.setUpdateId(CommonUtil.getSessionUserId(req));
					vendorCoefManager.updateVendorCoef(head);
				}else{
					return "2_保存出错,单据以被删除";
				}
			}
			return "1_保存成功";
		} catch (Exception e) {
			logger.error(e);
			return "2_保存出错";
		}
	}
}
