package com.jatools.web.dwr.calc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.jatools.common.CommonUtil;
import com.jatools.manager.calc.SbraCoefficientManager;
import com.jatools.vo.bd.Quality;
import com.jatools.vo.calc.SbraCoefficient;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.dwr.move.MoveSalecoefDwr;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class SbraCoefficientDwr {
	private static Logger logger = Logger.getLogger(MoveSalecoefDwr.class);
	private SbraCoefficientManager sbraCoefficientManager;
	public static void setLogger(Logger logger) {
		SbraCoefficientDwr.logger = logger;
	}
	public void setSbraCoefficientManager(
			SbraCoefficientManager sbraCoefficientManager) {
		this.sbraCoefficientManager = sbraCoefficientManager;
	}
	/**
	 * 保存修改操作
	 * @param head
	 * @param req
	 * @return
	 */
	public String saveOrUpdateSbraCoefficient(SbraCoefficient head ,HttpServletRequest req){
		try {
			Map<String, String> c = new HashMap<String, String>();
			c.put("qid", head.getQualityId());
			c.put("itemClassId", head.getItemClassId());
			c.put("id", head.getId()	);
			c.put("num", head.getStartWeight());
			SbraCoefficient sb1 = sbraCoefficientManager.getSbraCoefficientByQualityId(c);
			c.put("num", head.getEndWeight());
			SbraCoefficient sb2 = sbraCoefficientManager.getSbraCoefficientByQualityId(c);
			
			if((sb1!=null&&sb1.getCoefficient()!=null)||
				(sb2!=null&&sb2.getCoefficient()!=null)){
				return "2_保存出错,不能交叉、重复维护。";
			}
			if(StringUtil.isEmpty(head.getId())){
				head.setCreateDate(DateUtil.getCurrentDate18());
				head.setCreateUserId(CommonUtil.getSessionUserId(req));
				sbraCoefficientManager.saveSbraCoefficient(head);
			}else{
				SbraCoefficient old = sbraCoefficientManager.getSbraCoefficientById(head.getId());
				if(old!=null){
					head.setCreateDate(old.getCreateDate());
					head.setCreateUserId(old.getCreateUserId());
					head.setUpdateDate(DateUtil.getCurrentDate18());
					head.setUpdateUserId(CommonUtil.getSessionUserId(req));
					sbraCoefficientManager.updateSbraCoefficient(head);
				}
			}
			return "1_保存成功";
		} catch (Exception e) {
			logger.error(e);
			return "2_保存出错";
		}
	}
	/**
	 * 材质找系数
	 * @param qid
	 * @return
	 */
	public SbraCoefficient getSbraCoefficientByQualityId(String itemClassId,String qid,String num){
		if(qid==null||"".equals(qid)||num==null||"".equals(num))
			return null;
		else{
			Map<String, String> c = new HashMap<String, String>();
			c.put("itemClassId", itemClassId);
			c.put("qid", qid);
			c.put("num", num);
			return sbraCoefficientManager.getSbraCoefficientByQualityId(c);
		}
	}
	
	/**
	 * 大类找材质
	 * @param qid
	 * @return
	 */
	public List<SelectorOption> getQualityByItemClassId(String itemClassId){
		if(itemClassId==null||"".equals(itemClassId))
			return null;
		else{
			List<Quality> list = sbraCoefficientManager.getQualityByItemClassId(itemClassId);
			
			List<SelectorOption> sltList = new ArrayList<SelectorOption>();
			for(Quality q:list){
				sltList.add(new SelectorOption(q.getQualityId(), q.getQualityName()));
			}
			return sltList;
		}
	}
}
