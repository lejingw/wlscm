package com.jatools.web.dwr.calc;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.manager.calc.BasicPriceTwoManager;
import com.jatools.vo.calc.BasicPriceTwo;
import com.jatools.web.dwr.move.MoveSalecoefDwr;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class BasicPriceTwoDwr {
	private static Logger logger = Logger.getLogger(MoveSalecoefDwr.class);
	private BasicPriceTwoManager stonePriceManager;
	public static void setLogger(Logger logger) {
		BasicPriceTwoDwr.logger = logger;
	}
	public void setBasicPriceTwoManager(BasicPriceTwoManager stonePriceManager) {
		this.stonePriceManager = stonePriceManager;
	}
	
	/**
	 * 保存修改操作
	 * @param head
	 * @param req
	 * @return
	 */
	public String saveOrUpdateBasicPriceTwo(BasicPriceTwo head ,HttpServletRequest req){
		try {
			//查找有没有 维护过的
			BasicPriceTwo sp1 = stonePriceManager.getBasicPriceTwo(head);
			if(sp1!=null){
				return "2_保存出错,不能交叉、重复维护。";
			}
			if(StringUtil.isEmpty(head.getBillid())){
				head.setCreatedate(DateUtil.getCurrentDate18());
				head.setCreateuserid(CommonUtil.getSessionUserId(req));
				stonePriceManager.saveBasicPriceTwo(head);
			}else{
				BasicPriceTwo old = stonePriceManager.getBasicPriceTwoById(head.getBillid());
				if(old!=null){
					head.setCreatedate(old.getCreatedate());
					head.setCreateuserid(old.getCreateuserid());
					head.setUpdatedate(DateUtil.getCurrentDate18());
					head.setUpdateuserid(CommonUtil.getSessionUserId(req));
					stonePriceManager.updateBasicPriceTwo(head);
				}
			}
			return "1_保存成功";
		} catch (Exception e) {
			logger.error(e);
			return "2_保存出错";
		}
	}
	/**
	 * 获取主配石市场价
	 * @param sp
	 * @return
	 */
	public BasicPriceTwo getBasicPriceTwo(BasicPriceTwo sp){
		if(sp==null)
			return null;
		else{
			return stonePriceManager.getBasicPriceTwo(sp);
		}
	}
	
	public BasicPriceTwo getTwoPrice(String value){
		if(value==null)
			return null;
		else{
			return stonePriceManager.getTwoPrice(value);
		}
	}
}
