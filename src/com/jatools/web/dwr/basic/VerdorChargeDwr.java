package com.jatools.web.dwr.basic;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.manager.basic.VerdorChargeManager;
import com.jatools.vo.basic.VerdorCharge;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class VerdorChargeDwr {
	private static Logger logger = Logger.getLogger(VerdorChargeDwr.class);
	private VerdorChargeManager verdorChargeManager;
	public static void setLogger(Logger logger) {
		VerdorChargeDwr.logger = logger;
	}
	public void setVerdorChargeManager(VerdorChargeManager verdorChargeManager) {
		this.verdorChargeManager = verdorChargeManager;
	}
	
	/**
	 * 保存修改操作
	 * @param head
	 * @param req
	 * @return
	 */
	public String saveOrUpdateVerdorCharge(VerdorCharge head ,HttpServletRequest req){
		try {
			VerdorCharge cka = verdorChargeManager.getVerdorCharge(head);
			if(cka!=null)
				return "2_该数据已存在";
			if(StringUtil.isEmpty(head.getChargeId())){
				head.setCreateDate(DateUtil.getCurrentDate18());
				head.setCreateId(CommonUtil.getSessionUserId(req));
				head.setStatus("1");
				verdorChargeManager.saveVerdorCharge(head);
			}else{
				VerdorCharge old = verdorChargeManager.getVerdorChargeById(head.getChargeId());
				if(old!=null){
					head.setCreateDate(old.getCreateDate());
					head.setCreateId(old.getCreateId());
					head.setUpdateDate(DateUtil.getCurrentDate18());
					head.setUpdateId(CommonUtil.getSessionUserId(req));
					verdorChargeManager.updateVerdorCharge(head);
				}
			}
			return "1_保存成功";
		} catch (Exception e) {
			logger.error(e);
			return "2_保存出错";
		}
	}
	
	/**
	 * 
	 * @param sc
	 * @return
	 */
	public VerdorCharge getVerdorCharge(VerdorCharge a){
		if(a==null)
			return null;
		else{
			return verdorChargeManager.getVerdorCharge(a);
		}
	}
}
