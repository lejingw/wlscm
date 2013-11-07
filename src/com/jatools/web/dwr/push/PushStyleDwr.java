package com.jatools.web.dwr.push;

import com.jatools.common.CommonUtil;
import com.jatools.manager.push.PushStyleManager;
import com.jatools.vo.push.PushStyle;
import com.jatools.web.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PushStyleDwr {
	private PushStyleManager pushStyleManager;
	
	public void setPushStyleManager(PushStyleManager PushStyleManager) {
		this.pushStyleManager = PushStyleManager;
	}


	public String saveOrUpdatePushStyle(PushStyle dn, HttpServletRequest req){
        try {
            String userId = CommonUtil.getSessionUserId(req);
            pushStyleManager.saveOrUpdatePushStyle(dn, userId);
            return null;
        } catch (Exception e) {
            if(StringUtil.isNotBlank(e.getMessage())) {
                return e.getMessage();
            } else {
                return "保存失败";
            }
        }
	}
	
	public String deletePushStyle(List<String> billIdList){
        try {
            pushStyleManager.deletePushStyle(billIdList);
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