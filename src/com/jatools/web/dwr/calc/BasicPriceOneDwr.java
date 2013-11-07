package com.jatools.web.dwr.calc;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.manager.calc.BasicPriceOneManager;
import com.jatools.vo.calc.BasicPriceOne;
import com.jatools.web.dwr.move.MoveSalecoefDwr;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class BasicPriceOneDwr {
	private static Logger logger = Logger.getLogger(MoveSalecoefDwr.class);
	private BasicPriceOneManager stonePriceManager;
	public static void setLogger(Logger logger) {
		BasicPriceOneDwr.logger = logger;
	}
	public void setBasicPriceOneManager(BasicPriceOneManager stonePriceManager) {
		this.stonePriceManager = stonePriceManager;
	}
	
	/**
	 * 保存修改操作
	 * @param head
	 * @param req
	 * @return
	 */
	public String saveOrUpdateBasicPriceOne(BasicPriceOne head ,HttpServletRequest req){
		try {
			//查找有没有 维护过的
			String startWeight = head.getStartweight()+"";
			String endWeight = head.getEndweight()+"";
			BasicPriceOne sp1 = stonePriceManager.getBasicPriceOne(head);
			head.setStartweight((Double.parseDouble(endWeight)-1));
			BasicPriceOne sp2 = stonePriceManager.getBasicPriceOne(head);
			if(sp1!=null||sp2!=null){
				return "2_保存出错,不能交叉、重复维护。";
			}
			head.setStartweight(Double.parseDouble(startWeight));
			if(StringUtil.isEmpty(head.getBillid())){
				head.setCreatedate(DateUtil.getCurrentDate18());
				head.setCreateuserid(CommonUtil.getSessionUserId(req));
				stonePriceManager.saveBasicPriceOne(head);
			}else{
				BasicPriceOne old = stonePriceManager.getBasicPriceOneById(head.getBillid());
				if(old!=null){
					head.setCreatedate(old.getCreatedate());
					head.setCreateuserid(old.getCreateuserid());
					head.setUpdatedate(DateUtil.getCurrentDate18());
					head.setUpdateuserid(CommonUtil.getSessionUserId(req));
					stonePriceManager.updateBasicPriceOne(head);
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
	public BasicPriceOne getBasicPriceOne(BasicPriceOne sp){
		if(sp==null)
			return null;
		else{
			return stonePriceManager.getBasicPriceOne(sp);
		}
	}
}
