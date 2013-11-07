package com.jatools.web.dwr.basic;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.manager.basic.ColorGradeGradeManager;
import com.jatools.vo.basic.ColorGradeGrade;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class ColorGradeGradeDwr {
	private static Logger logger = Logger.getLogger(ColorGradeGradeDwr.class);
	private ColorGradeGradeManager colorGradeGradeManager;
	public static void setLogger(Logger logger) {
		ColorGradeGradeDwr.logger = logger;
	}
	public void setColorGradeGradeManager(ColorGradeGradeManager colorGradeGradeManager) {
		this.colorGradeGradeManager = colorGradeGradeManager;
	}
	
	/**
	 * 保存修改操作
	 * @param head
	 * @param req
	 * @return
	 */
	public String saveOrUpdateColorGradeGrade(ColorGradeGrade head ,HttpServletRequest req){
		try {
			ColorGradeGrade cka = colorGradeGradeManager.getColorGradeGrade(head);
			if(cka!=null)
				return "2_该色级有维护品质";
			if(StringUtil.isEmpty(head.getId())){
				head.setCreateDate(DateUtil.getCurrentDate18());
				head.setCreateId(CommonUtil.getSessionUserId(req));
				head.setStatus("1");
				colorGradeGradeManager.saveColorGradeGrade(head);
			}else{
				ColorGradeGrade old = colorGradeGradeManager.getColorGradeGradeById(head.getId());
				if(old!=null){
					head.setCreateDate(old.getCreateDate());
					head.setCreateId(old.getCreateId());
					head.setUpdateDate(DateUtil.getCurrentDate18());
					head.setUpdateId(CommonUtil.getSessionUserId(req));
					colorGradeGradeManager.updateColorGradeGrade(head);
				}
			}
			return "1_保存成功";
		} catch (Exception e) {
			logger.error(e);
			return "2_保存出错";
		}
	}
	
	/**
	 * 
	 * @param sc
	 * @return
	 */
	public ColorGradeGrade getColorGradeGrade(ColorGradeGrade a){
		if(a==null)
			return null;
		else{
			return colorGradeGradeManager.getColorGradeGrade(a);
		}
	}
}
