package com.jatools.web.view.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.web.cache.ArticleTypeCache;
import com.jatools.web.cache.DictCache;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.ItemOrnaClassCache;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.ParameterCache;
import com.jatools.web.cache.QualityCache;
import com.jatools.web.cache.StyleClassCache;
import com.jatools.web.cache.StyleThemeCache;
import com.jatools.web.cache.UnitCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.cache.VendorCache;
import com.jatools.web.form.sys.CacheForm;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;
import com.jatools.ws.remote.WorkflowService;

public class CacheController extends BaseMultiActionController {
	private static Logger logger = Logger.getLogger(CacheController.class);
//	private WorkflowService workflowService;
//	
//	public void setWorkflowService(WorkflowService workflowService) {
//		this.workflowService = workflowService;
//	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		CacheForm form = new CacheForm();
		form.setMessage("刷新缓存成功");
		return new ModelAndView("sys/refreshCache", "form", form);
	}
	
	/**
	 * 刷新缓存
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView refresh(HttpServletRequest req, HttpServletResponse res) {
		String items = CommonUtil.getParameterEmpty(req, "items");
		CacheForm form = new CacheForm();
		if(StringUtil.isNotEmpty(items)){
			try {
				String[] itemArr = items.split(",");
				for(String item:itemArr){
					refresh(item);
					//刷新加盟系统缓存
//					workflowService.refreshJmCache(item);
				}
				form.setMessage("刷新缓存成功["+items+"]");
			} catch (Exception e) {
				form.setMessage("刷新缓存失败");
				logger.error(e);
			}
		}else{
			form.setMessage("不能获取要刷新的项目");
		}
		return new ModelAndView("sys/refreshCache", "form", form);
	}
	private void refresh(String item){
		if("user".equals(item)){
			UserCache.refresh();
			logger.debug("刷新用户数据成功");
		}else if("dict".equals(item)){
			DictCache.refresh();
			logger.debug("刷新数据字典成功");
		}else if("org".equals(item)){
			OrgCache.refresh();
			logger.debug("刷新组织成功");
		}else if("articletype".equals(item)){
			ArticleTypeCache.refresh();
			logger.debug("刷新商品类别成功");
		}else if("itemClass".equals(item)){
			ItemClassCache.refresh();
			logger.debug("刷新大类成功");
		}else if("ornaClass".equals(item)){
			OrnaClassCache.refresh();
			logger.debug("刷新小类成功");
		}else if("itemOrnaClass".equals(item)){
			ItemOrnaClassCache.refresh();
			logger.debug("刷新大类小类对应关系成功");
		}else if("styleClass".equals(item)){
			StyleClassCache.refresh();
			logger.debug("刷新款式大类、款式中类、款式小类成功");
		} else if("unit".equals(item)){
			UnitCache.refresh();
			logger.debug("刷新计量单位成功");
		} else if("quality".equals(item)){
			QualityCache.refresh();
			logger.debug("刷新商品材质");
		} else if("vendor".equals(item)){
			VendorCache.refresh();
			logger.debug("刷新供应商");
		} else if("parameter".equals(item)){
			ParameterCache.refresh();
			logger.debug("刷新参数配置");
		} else if("styleTheme".equals(item)){
			StyleThemeCache.refresh();
			logger.debug("刷新主题款配置");
		}
	}
}
