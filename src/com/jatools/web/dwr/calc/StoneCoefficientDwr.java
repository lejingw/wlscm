package com.jatools.web.dwr.calc;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.jatools.common.CommonUtil;
import com.jatools.manager.calc.StoneCoefficientManager;
import com.jatools.vo.calc.StoneCoefficient;
import com.jatools.web.dwr.move.MoveSalecoefDwr;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class StoneCoefficientDwr {
	private static Logger logger = Logger.getLogger(MoveSalecoefDwr.class);
	private StoneCoefficientManager stoneCoefficientManager;
	public static void setLogger(Logger logger) {
		StoneCoefficientDwr.logger = logger;
	}
	public void setStoneCoefficientManager(
			StoneCoefficientManager stoneCoefficientManager) {
		this.stoneCoefficientManager = stoneCoefficientManager;
	}
	
	/**
	 * 保存修改操作
	 * @param head
	 * @param req
	 * @return
	 */
	public String saveOrUpdateStoneCoefficient(StoneCoefficient head ,HttpServletRequest req){
		try {
			StoneCoefficient cks = stoneCoefficientManager.getStoneCoefficient(head);
			if(cks!=null)
				return "2_不能重复维护";
			if(StringUtil.isEmpty(head.getId())){
				head.setCreateDate(DateUtil.getCurrentDate18());
				head.setCreateUserId(CommonUtil.getSessionUserId(req));
				stoneCoefficientManager.saveStoneCoefficient(head);
			}else{
				StoneCoefficient old = stoneCoefficientManager.getStoneCoefficientById(head.getId());
				if(old!=null){
					head.setCreateDate(old.getCreateDate());
					head.setCreateUserId(old.getCreateUserId());
					head.setUpdateDate(DateUtil.getCurrentDate18());
					head.setUpdateUserId(CommonUtil.getSessionUserId(req));
					stoneCoefficientManager.updateStoneCoefficient(head);
				}
			}
			return "1_保存成功";
		} catch (Exception e) {
			logger.error(e);
			return "2_保存出错";
		}
	}
	/**
	 * 获取石头系数
	 * @param sc
	 * @return
	 */
	public StoneCoefficient getStoneCoefficient(StoneCoefficient sc){
		if(sc==null)
			return null;
		else{
			return stoneCoefficientManager.getStoneCoefficient(sc);
		}
	}
}
