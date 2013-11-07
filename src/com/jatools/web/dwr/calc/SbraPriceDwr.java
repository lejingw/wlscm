package com.jatools.web.dwr.calc;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.jatools.common.CommonUtil;
import com.jatools.manager.calc.SbraPriceManager;
import com.jatools.vo.calc.SbraPrice;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class SbraPriceDwr {
	private Logger logger = Logger.getLogger(SbraPriceDwr.class);
	private SbraPriceManager sbraPriceManager;
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	public void setSbraPriceManager(SbraPriceManager sbraPriceManager) {
		this.sbraPriceManager = sbraPriceManager;
	}
	
	/**
	 * 保存修改操作
	 * @param head
	 * @param req
	 * @return
	 */
	public String saveOrUpdateSbraPrice(SbraPrice head ,HttpServletRequest req){
		try {
			SbraPrice ckh = sbraPriceManager.getSbraPriceByQualityId(head.getQualityId());
			if(ckh!=null){
				if(!ckh.getId().equals(head.getId()))
					return "2_该材质已维护市场价";
			}
			if(StringUtil.isEmpty(head.getId())){
				head.setCreateDate(DateUtil.getCurrentDate18());
				head.setCreateUserId(CommonUtil.getSessionUserId(req));
				sbraPriceManager.saveSbraPrice(head);
			}else{
				SbraPrice old = sbraPriceManager.getSbraPriceById(head.getId());
				if(old!=null){
					head.setCreateDate(old.getCreateDate());
					head.setCreateUserId(old.getCreateUserId());
					head.setUpdateDate(DateUtil.getCurrentDate18());
					head.setUpdateUserId(CommonUtil.getSessionUserId(req));
					sbraPriceManager.updateSbraPrice(head);
				}
			}
			return "1_保存成功";
		} catch (Exception e) {
			logger.error(e);
			return "2_保存出错";
		}
	}
	/**
	 * 材质下的市场价
	 * @param qid
	 * @return
	 */
	public SbraPrice getSbraPriceByQualityId(String qid){
		if(qid==null||"".equals(qid))
			return null;
		else{
			return sbraPriceManager.getSbraPriceByQualityId(qid);
		}
	}
}
