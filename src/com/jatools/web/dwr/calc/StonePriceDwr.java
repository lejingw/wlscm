package com.jatools.web.dwr.calc;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.manager.calc.StonePriceManager;
import com.jatools.vo.calc.StonePrice;
import com.jatools.web.dwr.move.MoveSalecoefDwr;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class StonePriceDwr {
	private static Logger logger = Logger.getLogger(MoveSalecoefDwr.class);
	private StonePriceManager stonePriceManager;
	public static void setLogger(Logger logger) {
		StonePriceDwr.logger = logger;
	}
	public void setStonePriceManager(StonePriceManager stonePriceManager) {
		this.stonePriceManager = stonePriceManager;
	}
	
	/**
	 * 保存修改操作
	 * @param head
	 * @param req
	 * @return
	 */
	public String saveOrUpdateStonePrice(StonePrice head ,HttpServletRequest req){
		try {
			//查找有没有 维护过的
			boolean res = stonePriceManager.existStonePrice(head);
			if(res){
				return "保存失败,不能交叉、重复维护。";
			}
			if(StringUtil.isEmpty(head.getId())){
				head.setCreateDate(DateUtil.getCurrentDate18());
				head.setCreateUserId(CommonUtil.getSessionUserId(req));
				stonePriceManager.saveStonePrice(head);
			}else{
				StonePrice old = stonePriceManager.getStonePriceById(head.getId());
				if(old!=null){
					head.setCreateDate(old.getCreateDate());
					head.setCreateUserId(old.getCreateUserId());
					head.setUpdateDate(DateUtil.getCurrentDate18());
					head.setUpdateUserId(CommonUtil.getSessionUserId(req));
					stonePriceManager.updateStonePrice(head);
				}
			}
			return null;
		} catch (Exception e) {
			logger.error(e);
            if(StringUtil.isNotBlank(e.getMessage())) {
                return e.getMessage();
            }
			return "保存出错";
		}
	}

    /**
    * 获取主配石市场价
    * @param sp
    * @return
    */
   public StonePrice getStonePrice(StonePrice sp){
           if(sp==null)
                      return null;
           else{
                   return stonePriceManager.getStonePrice(sp);
              }
       }
}
