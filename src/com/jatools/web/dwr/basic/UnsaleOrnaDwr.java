package com.jatools.web.dwr.basic;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.manager.basic.UnsaleOrnaManager;
import com.jatools.vo.basic.UnsaleOrna;
import com.jatools.web.util.StringUtil;

public class UnsaleOrnaDwr {

private static Logger logger = Logger.getLogger(UnsaleOrnaDwr.class);
	
	private UnsaleOrnaManager unsaleOrnaManager;
	
	/**
	 * 保存或修改数据
	 * @param orgGroup
	 */
	public String saveOrUpdateUnsaleOrna(UnsaleOrna unsaleOrna, HttpServletRequest req){
		try {
			this.unsaleOrnaManager.saveOrUpdateUnsaleOrna(unsaleOrna, CommonUtil.getSessionUserId(req));
			return null;
		} catch (Exception e) {
			logger.error(e);
			if(StringUtil.isNotBlank(e.getMessage())){
				return e.getMessage();
			}
			return "保存出错";
		}
	}
	
	public String deleteUnsaleOrna(String unsaleIds, HttpServletRequest req){
		try {
			if(StringUtil.isNotEmpty(unsaleIds)){
				this.unsaleOrnaManager.deleteUnsaleOrna(unsaleIds);
			}
			return null;
		} catch (Exception e) {
			logger.error(e);
			return "删除失败";
		}
	}

	public void setUnsaleOrnaManager(UnsaleOrnaManager unsaleOrnaManager) {
		this.unsaleOrnaManager = unsaleOrnaManager;
	}

	
}
