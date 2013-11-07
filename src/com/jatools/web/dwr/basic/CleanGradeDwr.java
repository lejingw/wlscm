package com.jatools.web.dwr.basic;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.manager.basic.CleanGradeManager;
import com.jatools.vo.basic.CleanGrade;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class CleanGradeDwr {
	private static Logger logger = Logger.getLogger(CleanGradeDwr.class);
	private CleanGradeManager cleanGradeManager;
	public static void setLogger(Logger logger) {
		CleanGradeDwr.logger = logger;
	}
	public void setCleanGradeManager(CleanGradeManager cleanGradeManager) {
		this.cleanGradeManager = cleanGradeManager;
	}
	
	/**
	 * 保存修改操作
	 * @param head
	 * @param req
	 * @return
	 */
	public String saveOrUpdateCleanGrade(CleanGrade head ,HttpServletRequest req){
		try {
			CleanGrade cka = cleanGradeManager.getCleanGrade(head);
			if(cka!=null)
				return "2_该净度有维护品质";
			if(StringUtil.isEmpty(head.getId())){
				head.setCreateDate(DateUtil.getCurrentDate18());
				head.setCreateId(CommonUtil.getSessionUserId(req));
				head.setStatus("1");
				cleanGradeManager.saveCleanGrade(head);
			}else{
				CleanGrade old = cleanGradeManager.getCleanGradeById(head.getId());
				if(old!=null){
					head.setCreateDate(old.getCreateDate());
					head.setCreateId(old.getCreateId());
					head.setUpdateDate(DateUtil.getCurrentDate18());
					head.setUpdateId(CommonUtil.getSessionUserId(req));
					cleanGradeManager.updateCleanGrade(head);
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
	public CleanGrade getCleanGrade(CleanGrade a){
		if(a==null)
			return null;
		else{
			return cleanGradeManager.getCleanGrade(a);
		}
	}
}
