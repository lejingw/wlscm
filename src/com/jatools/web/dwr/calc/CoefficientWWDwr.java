package com.jatools.web.dwr.calc;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.manager.calc.CoefficientWWManager;
import com.jatools.vo.calc.CoefficientWW;
import com.jatools.vo.calc.StonePrice;
import com.jatools.web.dwr.move.MoveSalecoefDwr;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.DictUtil;
import com.jatools.web.util.StringUtil;

public class CoefficientWWDwr {
	private static Logger logger = Logger.getLogger(MoveSalecoefDwr.class);
	private CoefficientWWManager coefficientWWManager;
	public static Logger getLogger() {
		return logger;
	}
	public static void setLogger(Logger logger) {
		CoefficientWWDwr.logger = logger;
	}
	public CoefficientWWManager getCoefficientWWManager() {
		return coefficientWWManager;
	}
	public void setCoefficientWWManager(CoefficientWWManager coefficientWWManager) {
		this.coefficientWWManager = coefficientWWManager;
	}
	
	/**
	 * 保存修改操作
	 * @param head
	 * @param req
	 * @return
	 */
	public String saveOrUpdateCoefficientWW(CoefficientWW head ,HttpServletRequest req){
		try {
			//查找有没有 维护过的
			String startWeight = head.getStartWeight();
			String endWeight = head.getEndWeight();
			head.setType("1");
			CoefficientWW sp1 = coefficientWWManager.getCoefficientWW(head);
			head.setStartWeight(endWeight);
			head.setType("2");
			CoefficientWW sp2 = coefficientWWManager.getCoefficientWW(head);
			if((sp1!=null&&sp1.getCoefficient()!=null)||(sp2!=null&&sp2.getCoefficient()!=null)){
				return "2_保存出错,不能交叉、重复维护。";
			}
			head.setStartWeight(startWeight);
			if(StringUtil.isEmpty(head.getId())){
				head.setCreateDate(DateUtil.getCurrentDate18());
				head.setCreateUserId(CommonUtil.getSessionUserId(req));
				coefficientWWManager.saveCoefficientWW(head);
			}else{
				CoefficientWW old = coefficientWWManager.getCoefficientWWById(head.getId());
				if(old!=null){
					head.setCreateDate(old.getCreateDate());
					head.setCreateUserId(old.getCreateUserId());
					head.setUpdateDate(DateUtil.getCurrentDate18());
					head.setUpdateUserId(CommonUtil.getSessionUserId(req));
					coefficientWWManager.updateCoefficientWW(head);
				}
			}
			return "1_保存成功";
		} catch (Exception e) {
			logger.error(e);
			return "2_保存出错";
		}
	}
	
	/**
	 * 获取总系数
	 * @param sc
	 * @return
	 */
	public CoefficientWW getCoefficientWW(CoefficientWW a,String allWeight,String avgWeight,String allWidthTaxPrice){
		if(a==null)
			return null;
		else{
			a.setType("1");
			//任务单 973: 采购总系数 2012.07.30 
			String value = DictUtil.getValue("coefficien_unit", a.getItemClassId());
			String test = "";
			if("1".equals(value)){//单位（1总重量、2主石重量、3总成本）
				a.setStartWeight(allWeight);
				test = "总重量";
			}else if("2".equals(value)){
				a.setStartWeight(avgWeight);
				test = "主石重量";
			}else if("3".equals(value)){
				a.setStartWeight(allWidthTaxPrice);
				test = "总成本";
			}else
				throw new RuntimeException("没有维护总系数单位");
			if(a.getStartWeight()==null)
				throw new RuntimeException("总系数单位为："+test+", 值为NULL");
			return coefficientWWManager.getCoefficientWW(a);
		}
	}
}
