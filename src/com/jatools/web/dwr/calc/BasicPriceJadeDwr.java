package com.jatools.web.dwr.calc;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.manager.calc.BasicPriceJadeManager;
import com.jatools.vo.calc.BasicPriceJade;
import com.jatools.web.dwr.move.MoveSalecoefDwr;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class BasicPriceJadeDwr {
	private static Logger logger = Logger.getLogger(MoveSalecoefDwr.class);
	private BasicPriceJadeManager stonePriceManager;
	public static void setLogger(Logger logger) {
		BasicPriceJadeDwr.logger = logger;
	}
	public void setBasicPriceJadeManager(BasicPriceJadeManager stonePriceManager) {
		this.stonePriceManager = stonePriceManager;
	}
	
	/**
	 * 保存修改操作
	 * @param head
	 * @param req
	 * @return
	 */
	public String saveOrUpdateBasicPriceJade(BasicPriceJade head ,HttpServletRequest req){
		try {
			//查找有没有 维护过的
			String startWeight = head.getStartprice()+"";
			String endWeight = head.getEndprice()+"";
			BasicPriceJade sp1 = stonePriceManager.getBasicPriceJade(head);
			head.setStartprice((Double.parseDouble(endWeight)-1));
			BasicPriceJade sp2 = stonePriceManager.getBasicPriceJade(head);
			if(sp1!=null||sp2!=null){
				return "2_保存出错,不能交叉、重复维护。";
			}
			head.setStartprice(Double.parseDouble(startWeight));
			if(StringUtil.isEmpty(head.getBillid())){
				head.setCreatedate(DateUtil.getCurrentDate18());
				head.setCreateuserid(CommonUtil.getSessionUserId(req));
				stonePriceManager.saveBasicPriceJade(head);
			}else{
				BasicPriceJade old = stonePriceManager.getBasicPriceJadeById(head.getBillid());
				if(old!=null){
					head.setCreatedate(old.getCreatedate());
					head.setCreateuserid(old.getCreateuserid());
					head.setUpdatedate(DateUtil.getCurrentDate18());
					head.setUpdateuserid(CommonUtil.getSessionUserId(req));
					stonePriceManager.updateBasicPriceJade(head);
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
	public BasicPriceJade getBasicPriceJade(BasicPriceJade sp){
		if(sp==null)
			return null;
		else{
			return stonePriceManager.getBasicPriceJade(sp);
		}
	}
}
