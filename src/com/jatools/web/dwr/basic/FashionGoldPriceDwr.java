package com.jatools.web.dwr.basic;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.basic.ChargeManager;
import com.jatools.manager.basic.FashionGoldPriceManager;
import com.jatools.vo.basic.Charge;
import com.jatools.vo.basic.FashionGoldPrice;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class FashionGoldPriceDwr {
	private static Logger logger = Logger.getLogger(FashionGoldPriceDwr.class);
	
	private FashionGoldPriceManager fashionGoldPriceManager;
	

	public String saveOrUpdateGoldPrice(FashionGoldPrice goldPrice, HttpServletRequest req){
		try {
			this.fashionGoldPriceManager.saveOrUpdateFashionGoldPrice(goldPrice, CommonUtil.getSessionUserId(req));
			return null;
		} catch (Exception e) {
			logger.error(e);
            if(StringUtil.isNotBlank(e.getMessage())) {
                return e.getMessage();
            }
			return "保存出错";
		}
	}
	
	public String deleteGoldPrice(String id, HttpServletRequest req){
		try {
			if(StringUtil.isNotEmpty(id)){
				this.fashionGoldPriceManager.deleteFashionGoldPrice(id);
			}
			return null;
		} catch (Exception e) {
			logger.error(e);
            if(StringUtil.isNotBlank(e.getMessage())) {
                return e.getMessage();
            }
			return "删除失败";
		}
	}

    public void setFashionGoldPriceManager(FashionGoldPriceManager fashionGoldPriceManager) {
        this.fashionGoldPriceManager = fashionGoldPriceManager;
    }
}
