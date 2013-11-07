package com.jatools.web.dwr.calc;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.jatools.common.CommonUtil;
import com.jatools.manager.calc.FashionCoefficientManager;
import com.jatools.vo.calc.FashionCoefficient;
import com.jatools.web.dwr.move.MoveSalecoefDwr;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class FashionCoefficientDwr {
	private static Logger logger = Logger.getLogger(MoveSalecoefDwr.class);
	private FashionCoefficientManager fashionCoefficientManager;
	public static void setLogger(Logger logger) {
		FashionCoefficientDwr.logger = logger;
	}
	public void setFashionCoefficientManager(
			FashionCoefficientManager fashionCoefficientManager) {
		this.fashionCoefficientManager = fashionCoefficientManager;
	}
	/**
	 * 保存修改操作
	 * @param head
	 * @param req
	 * @return
	 */
	public String saveOrUpdateFashionCoefficient(FashionCoefficient head ,HttpServletRequest req){
		try {
			Map<String, String> c = new HashMap<String, String>();
			c.put("itemClassId", head.getItemClassId());
			c.put("fashId", head.getFashId()	);
			c.put("num", head.getPriceStr());
			FashionCoefficient sb1 = fashionCoefficientManager.getFashionCoefficientItemClassId(c);
			c.put("num", (Double.parseDouble(head.getPriceEnd())-1)+"");
			FashionCoefficient sb2 = fashionCoefficientManager.getFashionCoefficientItemClassId(c);
			
			if((sb1!=null&&sb1.getCoefficient()!=null)||
				(sb2!=null&&sb2.getCoefficient()!=null)){
				return "2_保存出错,不能交叉、重复维护。";
			}
			if(StringUtil.isEmpty(head.getFashId())){
				head.setCreateDate(DateUtil.getCurrentDate18());
				head.setCreateId(CommonUtil.getSessionUserId(req));
				fashionCoefficientManager.saveFashionCoefficient(head);
			}else{
				FashionCoefficient old = fashionCoefficientManager.getFashionCoefficientById(head.getFashId());
				if(old!=null){
					head.setCreateDate(old.getCreateDate());
					head.setCreateId(old.getCreateId());
					head.setUpdateDate(DateUtil.getCurrentDate18());
					head.setUpdateId(CommonUtil.getSessionUserId(req));
					fashionCoefficientManager.updateFashionCoefficient(head);
				}
			}
			return "1_保存成功";
		} catch (Exception e) {
			logger.error(e);
			return "2_保存出错";
		}
	}
	/**
	 * 找系数
	 * @param qid
	 * @return
	 */
	public FashionCoefficient getFashionCoefficientItemClassId(String itemClassId,String num){
		if(itemClassId==null||"".equals(itemClassId)||num==null||"".equals(num))
			return null;
		else{
			Map<String, String> c = new HashMap<String, String>();
			c.put("itemClassId", itemClassId);
			c.put("num", num);
			return fashionCoefficientManager.getFashionCoefficientItemClassId(c);
		}
	}
}
