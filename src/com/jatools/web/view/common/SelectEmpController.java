package com.jatools.web.view.common;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.manager.common.SelectEmpManager;
import com.jatools.vo.bd.Org;
import com.jatools.vo.sys.User;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.form.common.SelectorForm;
import com.jatools.web.view.BaseMultiActionController;

public class SelectEmpController extends BaseMultiActionController{
	
	private SelectEmpManager selectEmpManager;
	
	public void setSelectEmpManager(SelectEmpManager selectEmpManager) {
		this.selectEmpManager = selectEmpManager;
	}
	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		return null;
	}
	/**
	 * 获取数据字典中配置的采购员
	 * 采购模块
	 */
	public ModelAndView getPurchaseEmp(HttpServletRequest req, HttpServletResponse res) {
		String vender = CommonUtil.getParameterEmpty(req, "vender");
		String multiFlag = CommonUtil.getParameterEmpty(req, "multiFlag");
		String selectedValues = CommonUtil.getParameterEmpty(req, "selectedValues");
//		DictCache dictCache = DictCache.getInstance();
		List<User> purchaseEmpUnarchiveList = selectEmpManager.getUnarchivePurchaseEmp();
		Map<String, String> dataMap = new LinkedHashMap<String, String>();
		for(User user : purchaseEmpUnarchiveList){
			if(user.getUsername().indexOf(vender)>-1){
				dataMap.put(user.getUserid(), user.getUsername());
			}
		}
		SelectorForm form = new SelectorForm("true".equals(multiFlag), selectedValues);
		form.setVender(vender);
		form.setDataMap(dataMap);
		return new ModelAndView("common/SelectEmp", "form", form);
	}
	/**
	 * 根据组织id获取组织下的人员
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView getEmpByOrgId(HttpServletRequest req, HttpServletResponse res) {
		String vender = CommonUtil.getParameterEmpty(req, "vender");
		String multiFlag = CommonUtil.getParameterEmpty(req, "multiFlag");
		String selectedValues = CommonUtil.getParameterEmpty(req, "selectedValues");
		String orgId = CommonUtil.getParameterEmpty(req, "orgId");
		List<User> empList = selectEmpManager.getEmpByOrgId(orgId);
		Map<String, String> dataMap = new HashMap<String, String>();
		for(User user : empList){
			if(user.getUsername().indexOf(vender)>-1){
				dataMap.put(user.getUserid(), user.getUsername());
			}
		}
		SelectorForm form = new SelectorForm("true".equals(multiFlag), selectedValues);
		form.setOrgId(orgId);
		form.setVender(vender);
		form.setDataMap(dataMap);
		return new ModelAndView("common/SelectEmp", "form", form);
	}
	/**
	 * 根据组织id获取组织下的人员
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView getEmpByExeOrgId(HttpServletRequest req, HttpServletResponse res) {
		String multiFlag = CommonUtil.getParameterEmpty(req, "multiFlag");
		String selectedValues = CommonUtil.getParameterEmpty(req, "selectedValues");
		Map<String, Org> dataMap = OrgCache.getInstance().getExecuteOrgTree();
		SelectorForm form = new SelectorForm("true".equals(multiFlag), selectedValues);
		form.setDataMap(dataMap);
		return new ModelAndView("common/SelectEmp2", "form", form);
	}
	/**
	 * 根据组织id获取联邦快递人员
	 * 装箱单
	 */
	public ModelAndView getExpressEmps(HttpServletRequest req, HttpServletResponse res) {
		String vender = CommonUtil.getParameterEmpty(req, "vender");
		String orgId = CommonUtil.getParameterEmpty(req, "orgId");
		String multiFlag = CommonUtil.getParameterEmpty(req, "multiFlag");
		String selectedValues = CommonUtil.getParameterEmpty(req, "selectedValues");
		List<Map<String, String>> empList = selectEmpManager.getExpressReceusers(orgId);
		Map<String, String> dataMap = new HashMap<String, String>();
		for(Map<String, String> emp : empList){
			if(emp.get("username").indexOf(vender)>-1){
				dataMap.put(emp.get("userid"), emp.get("username"));
			}
		}
		
		SelectorForm form = new SelectorForm("true".equals(multiFlag), selectedValues);
		form.setOrgId(orgId);
		form.setVender(vender);
		form.setDataMap(dataMap);
		return new ModelAndView("common/SelectEmp", "form", form);
	}
}