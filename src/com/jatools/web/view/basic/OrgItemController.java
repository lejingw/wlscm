package com.jatools.web.view.basic;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.manager.basic.OrgItemManager;
import com.jatools.vo.basic.OrgItem;
import com.jatools.web.form.basic.OrgItemForm;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class OrgItemController extends BaseMultiActionController {

	private OrgItemManager orgItemManager;
	private Logger logger = Logger.getLogger(OrgItemController.class);
	
	public OrgItemManager getOrgItemManager() {
		return orgItemManager;
	}
	public void setOrgItemManager(OrgItemManager orgItemManager) {
		this.orgItemManager = orgItemManager;
	}
	/**
	 * 分页显示
	 */
	@Override
	public ModelAndView doPerform(HttpServletRequest req,
			HttpServletResponse res) {
		OrgItemForm form = new OrgItemForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "itemClassId","orgId");
			Pager pager = orgItemManager.getOrgItemPageData(condition);
			form.setPager(pager);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("basic/orgItem_list","form",form);//转跳页面
	}
	/**
	 * 删除
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView deleteOrgItem(HttpServletRequest req,
			HttpServletResponse res) {
		OrgItemForm form = new OrgItemForm();
		String orgItemId = CommonUtil.getParameterNull(req, "orgItemId");
		try {
			if(StringUtil.isNotEmpty(orgItemId)){
				orgItemManager.deleteOrgItem(orgItemId);
				form.setSuccessfulFlag(true);
				form.setMessage("删除成功");
				Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
				Pager pager = orgItemManager.getOrgItemPageData(condition);
				form.setPager(pager);
			}else{
				form.setSuccessfulFlag(false);
				form.setMessage("不能获取单据供应商id");
			}
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("basic/orgItem_list","form",form);//转跳页面
	}
	public ModelAndView toEditOrgItem(HttpServletRequest req,
		HttpServletResponse res) {
		String orgItemId = CommonUtil.getParameterNull(req, "orgItemId");
		OrgItemForm form = new OrgItemForm();
		//如果带有vendorId参数，则为编辑页面
		if(null != orgItemId){
			OrgItem oi = orgItemManager.getOrgItemById(orgItemId);
			List<String> list = orgItemManager.getItemByOrgId(oi.getOrgId());
			form.setOi(oi);
			form.setItems(list);
			return new ModelAndView("basic/orgItem_edit","form",form);
		}else{
			return new ModelAndView("basic/orgItem_edit","form",form);
		}
		
	}

}
