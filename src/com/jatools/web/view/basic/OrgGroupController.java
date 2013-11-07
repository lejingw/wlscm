package com.jatools.web.view.basic;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jatools.common.constant.ParameterConstant;
import com.jatools.web.cache.*;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.basic.OrgGroupManager;
import com.jatools.vo.basic.OrgGroup;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.form.basic.OrgGroupForm;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class OrgGroupController extends BaseMultiActionController {
	private Logger logger = Logger.getLogger(OrgGroupController.class);

	//private final static String MAIN_ORG_ID = "49901";
	private OrgGroupManager orgGroupManager;

	public void setOrgGroupManager(OrgGroupManager orgGroupManager) {
		this.orgGroupManager = orgGroupManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		OrgGroupForm form = new OrgGroupForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "orgId", "itemClassId", "ornaClassId");
			this.checkOrgId(condition, req);
			Pager pager = orgGroupManager.getOrgGroupPageData(condition);
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("basic/orgGroup_list", "form", form);
	}
	/**
	 * 页面跳转
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		String groupId = CommonUtil.getParameterNull(req, "groupId");
		OrgGroupForm form = new OrgGroupForm();
		//如果带有groupId参数，则为编辑页面
		if(null != groupId){
			OrgGroup orgGroup = orgGroupManager.getOrgGroup(groupId);
			form.setOrgGroup(orgGroup);
			return new ModelAndView("basic/orgGroup_edit","form",form);
		}else{
			return new ModelAndView("basic/orgGroup_edit","form",form);
		}
	}
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res){
		String groupId = CommonUtil.getParameterNull(req, "groupId");
		OrgGroupForm form = new OrgGroupForm();
		if(StringUtil.isNotEmpty(groupId)){
			orgGroupManager.deleteOrgGroup(groupId);
			form.setSuccessfulFlag(true);
			form.setMessage("删除成功");
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
			this.checkOrgId(condition, req);
			Pager pager = orgGroupManager.getOrgGroupPageData(condition);
			form.setPager(pager);
		}else{
			form.setSuccessfulFlag(false);
			form.setMessage("不能获取交接数据id");
		}
		return new ModelAndView("basic/orgGroup_list", "form", form);
	}
	/**
	 * 保存单据
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView saveOrUpdate(HttpServletRequest req, HttpServletResponse res) {
		OrgGroup orgGroup = CommonUtil.request2Object(req, OrgGroup.class);
		String userid = CommonUtil.getSessionUserId(req);
		orgGroup.setUpdateDate(DateUtil.getCurrentDate18());
		orgGroup.setUpdateId(userid);
		OrgGroupForm form = new OrgGroupForm();
		if(null == orgGroup.getGroupId()){
			orgGroup.setCreateDate(DateUtil.getCurrentDate18());
			orgGroup.setCreateId(userid);
			orgGroup.setStatus(DictConstant.BILL_STATUS_SAVE);
			orgGroupManager.saveOrgGroup(orgGroup);//保存单据
			
		}else{
			orgGroupManager.updateOrgGroup(orgGroup);//修改单据
		}
		form.setSuccessfulFlag(true);
		form.setMessage("保存成功");
		
		return new ModelAndView(new RedirectView("orgGroup.vm"), "form", form);
	}
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "orgId", "itemClassId", "ornaClassId");
		this.checkOrgId(condition, req);
		Pager pager = orgGroupManager.getOrgGroupPageData(condition);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("单位柜组");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"createDate"}, new Integer[]{3});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"组织", "大类", "小类", "柜组", "创建时间", "创建人", "修改时间", "修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"orgId", "itemClassId", "ornaClassId", "groups", "createDate", "createId", "updateDate", "updateId"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"createId", "orgId", "itemClassId", "ornaClassId", "updateId"},
					new CacheSingletonIntf[]{UserCache.getInstance(), OrgCache.getInstance(), ItemClassCache.getInstance(), OrnaClassCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{},
					new String[]{});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
	
	private void checkOrgId(Map<String, String> condition, HttpServletRequest req){
		String curOrgId = CommonUtil.getSessionOrgId(req);
        String Main_Org_Id = ParameterCache.getInstance().getNameById(ParameterConstant.HQ_MAIN_ORG_ID);
		if(!Main_Org_Id.equals(curOrgId)){
			condition.put("orgId", curOrgId);
		} else {
            condition.put("orgId", Main_Org_Id);
        }
	}
}
