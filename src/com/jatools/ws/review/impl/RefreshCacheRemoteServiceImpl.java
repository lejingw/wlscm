package com.jatools.ws.review.impl;

import com.jatools.web.cache.ArticleTypeCache;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.ItemOrnaClassCache;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.QualityCache;
import com.jatools.web.cache.StyleClassCache;
import com.jatools.web.cache.StyleThemeCache;
import com.jatools.web.cache.UnitCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.cache.VendorCache;
import com.jatools.ws.review.RefreshCacheRemoteService;

public class RefreshCacheRemoteServiceImpl implements RefreshCacheRemoteService {
//	private WorkflowService workflowService;
//	
//	public void setWorkflowService(WorkflowService workflowService) {
//		this.workflowService = workflowService;
//	}

//		user			用户数据
//		org			组织
//		itemClass		大类
//		ornaClass		小类
//		itemOrnaClass		大类小类对应关系
//		styleClass		款式大类、款式中类、款式小类
//		unit			计量单位
//		quality			商品材质
//		vendor			供应商
	@Override
	public String refreshCache(String msgKey) {
		if("user".equals(msgKey)){UserCache.refresh();}
		else if("org".equals(msgKey)){OrgCache.refresh();}
		else if("articletype".equals(msgKey)){ArticleTypeCache.refresh();}
		else if("itemClass".equals(msgKey)){ItemClassCache.refresh();}
		else if("ornaClass".equals(msgKey)){OrnaClassCache.refresh();}
		else if("itemOrnaClass".equals(msgKey)){ItemOrnaClassCache.refresh();}
		else if("styleClass".equals(msgKey)){StyleClassCache.refresh();}
		else if("unit".equals(msgKey)){UnitCache.refresh();}
		else if("quality".equals(msgKey)){QualityCache.refresh();}
		else if("vendor".equals(msgKey)){VendorCache.refresh();}
		else if("styleTheme".equals(msgKey)){StyleThemeCache.refresh();}
		//刷新加盟系统缓存
//		if(null != workflowService){
//			workflowService.refreshJmCache(msgKey);
//		}
		return "SUCCESS";
	}
}
