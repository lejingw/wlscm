package com.jatools.web.dwr.calc;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.manager.calc.CoefficientManager;
import com.jatools.vo.calc.Coefficient;
import com.jatools.vo.calc.StonePrice;
import com.jatools.web.dwr.move.MoveSalecoefDwr;
import com.jatools.web.util.BdCommon;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.DictUtil;
import com.jatools.web.util.StringUtil;

public class CoefficientDwr {
	private static Logger logger = Logger.getLogger(MoveSalecoefDwr.class);
	private CoefficientManager coefficientManager;
	public static Logger getLogger() {
		return logger;
	}
	public static void setLogger(Logger logger) {
		CoefficientDwr.logger = logger;
	}
	public CoefficientManager getCoefficientManager() {
		return coefficientManager;
	}
	public void setCoefficientManager(CoefficientManager coefficientManager) {
		this.coefficientManager = coefficientManager;
	}
	
	/**
	 * 保存修改操作
	 * @param head
	 * @param req
	 * @return
	 */
	public String saveOrUpdateCoefficient(Coefficient head ,HttpServletRequest req){
		try {
			//查找有没有 维护过的
			String startWeight = head.getStartWeight();
			String endWeight = head.getEndWeight();
			head.setType("1");
			Coefficient sp1 = coefficientManager.getCoefficient(head);
			head.setStartWeight(endWeight);
			head.setType("2");
			Coefficient sp2 = coefficientManager.getCoefficient(head);
			if((sp1!=null&&sp1.getCoefficient()!=null)||(sp2!=null&&sp2.getCoefficient()!=null)){
				return "2_保存出错,不能交叉、重复维护。";
			}
			head.setStartWeight(startWeight);
//			String s = this.coefficientManager.getMinNum(head);
//			String e = this.coefficientManager.getMaxNum(head);
//			Double startWeight = Double.parseDouble(s==null||"".equals(s)?"0":s);
//			Double endWeight = Double.parseDouble(e==null||"".equals(e)?"0":e);;
//			if((Double.parseDouble(head.getStartWeight())>=startWeight&&
//			  Double.parseDouble(head.getStartWeight())<endWeight)||
//			  (Double.parseDouble(head.getEndWeight())>=startWeight&&
//			  Double.parseDouble(head.getEndWeight())<endWeight)){
//				return "2_保存出错,不能交叉、重复维护。";
//			}
			if(StringUtil.isEmpty(head.getId())){
				head.setCreateDate(DateUtil.getCurrentDate18());
				head.setCreateUserId(CommonUtil.getSessionUserId(req));
				coefficientManager.saveCoefficient(head);
			}else{
				Coefficient old = coefficientManager.getCoefficientById(head.getId());
				if(old!=null){
					head.setCreateDate(old.getCreateDate());
					head.setCreateUserId(old.getCreateUserId());
					head.setUpdateDate(DateUtil.getCurrentDate18());
					head.setUpdateUserId(CommonUtil.getSessionUserId(req));
					coefficientManager.updateCoefficient(head);
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
	public Coefficient getCoefficient(Coefficient a,String allWeight,String avgWeight,String allWidthTaxPrice){
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
			
			return coefficientManager.getCoefficient(a);
		}
	}
}
