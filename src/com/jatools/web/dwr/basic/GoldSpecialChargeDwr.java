package com.jatools.web.dwr.basic;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.basic.ChargeManager;
import com.jatools.manager.basic.GoldSpecialChargeManager;
import com.jatools.vo.basic.Charge;
import com.jatools.vo.basic.GoldSpecialCharge;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class GoldSpecialChargeDwr {
	private static Logger logger = Logger.getLogger(GoldSpecialChargeDwr.class);
	
	private GoldSpecialChargeManager goldSpecialChargeManager;
	
	/**
	 * 保存或修改POS对接数据
	 * @param goldSpecialCharge
	 */
	public String saveOrUpdateGoldSpecialCharge(GoldSpecialCharge goldSpecialCharge, HttpServletRequest req){
		try {
            goldSpecialCharge.setStatus(DictConstant.BILL_STATUS_SAVE);
            goldSpecialChargeManager.saveOrUpdateGoldSpecialCharge(goldSpecialCharge, CommonUtil.getSessionUserId(req));
			return null;
		} catch (Exception e) {
			logger.error(e);
            if(StringUtil.isNotBlank(e.getMessage())){
                return e.getMessage();
            }
			return "保存出错";
		}
	}
	
	public String deleteGoldSpecialCharge(String billids, HttpServletRequest req){
		try {
			if(StringUtil.isNotEmpty(billids)){
				this.goldSpecialChargeManager.deleteGoldSpecialCharge(billids);
			}
			return null;
		} catch (Exception e) {
			logger.error(e);
            if(StringUtil.isNotBlank(e.getMessage())){
                return e.getMessage();
            }
			return "删除失败";
		}
	}

    public GoldSpecialCharge getGoldSpecialCharge(String itemClassId, String ornaClassId, String weight) {
        try {
            return this.goldSpecialChargeManager.getGoldSpecialChargeByItemOrna(itemClassId, ornaClassId, weight);
        } catch(Exception e) {
            return null;
        }
    }

    public void setGoldSpecialChargeManager(GoldSpecialChargeManager goldSpecialChargeManager) {
        this.goldSpecialChargeManager = goldSpecialChargeManager;
    }
}
