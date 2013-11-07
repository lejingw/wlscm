package com.jatools.web.dwr.push;

import com.jatools.common.CommonUtil;
import com.jatools.manager.push.SalableManager;
import com.jatools.vo.push.Salable;
import com.jatools.web.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SalableDwr {
	private SalableManager salableManager;
	
	public void setSalableManager(SalableManager SalableManager) {
		this.salableManager = SalableManager;
	}


	public String saveOrUpdateSalable(Salable dn, HttpServletRequest req){
        try {
            String userId = CommonUtil.getSessionUserId(req);
            salableManager.saveOrUpdateSalable(dn, userId);
            return null;
        } catch (Exception e) {
            if(StringUtil.isNotBlank(e.getMessage())) {
                return e.getMessage();
            } else {
                return "保存失败";
            }
        }
	}
	
	public String deleteSalable(List<String> billIdList){
        try {
            salableManager.deleteSalable(billIdList);
            return null;
        } catch (Exception e) {
            if(StringUtil.isNotBlank(e.getMessage())) {
                return e.getMessage();
            } else {
                return "删除失败";
            }
        }
	}
}