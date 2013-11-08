package com.jatools.web.view.common;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.manager.move.MoveNetManager;
import com.jatools.manager.push.DisplayStandardManager;
import com.jatools.manager.push.GatherOrderManager;
import com.jatools.manager.util.CommonManager;
import com.jatools.vo.bd.Org;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.form.common.SelectorForm;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class SelectOrgController extends BaseMultiActionController{

	private CommonManager commonManager;
	private MoveNetManager moveNetManager;
	private GatherOrderManager gatherOrderManager;
	private DisplayStandardManager displayStandardManager;
	
	
	public void setDisplayStandardManager(
			DisplayStandardManager displayStandardManager) {
		this.displayStandardManager = displayStandardManager;
	}

	public void setGatherOrderManager(GatherOrderManager gatherOrderManager) {
		this.gatherOrderManager = gatherOrderManager;
	}

	public void setMoveNetManager(MoveNetManager moveNetManager) {
		this.moveNetManager = moveNetManager;
	}

	public void setCommonManager(CommonManager commonManager) {
		this.commonManager = commonManager;
	}
	
	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		return null;
	}
	/**
	 * 获取组织数据
	 */
	public ModelAndView getOrgTree(HttpServletRequest req, HttpServletResponse res) {
		String multiFlag = CommonUtil.getParameterEmpty(req, "multiFlag");
		String selectedValues = CommonUtil.getParameterEmpty(req, "selectedValues");
		Map<String, Org> dataMap = OrgCache.getInstance().getOrgTree();
		SelectorForm form = new SelectorForm("true".equals(multiFlag), selectedValues);
		form.setDataMap(dataMap);
		return new ModelAndView("common/SelectOrg", "form", form);
	}
	
	public ModelAndView getCurrentOrgTree(HttpServletRequest req, HttpServletResponse res) {
		String multiFlag = CommonUtil.getParameterEmpty(req, "multiFlag");
		String selectedValues = CommonUtil.getParameterEmpty(req, "selectedValues");
		String userId = CommonUtil.getParameterEmpty(req, "curUserId");
		SelectorForm form = new SelectorForm("true".equals(multiFlag), selectedValues);
		Map<String, Org> dataMap = new LinkedHashMap<String, Org>();
		if(StringUtil.isNotBlank(userId)){
			List<Org> orgList = commonManager.getCurrentUserOrgList(userId);
			for(Org org: orgList){
				dataMap.put(org.getOrgId(), org);
			}
		}
		form.setDataMap(dataMap);
		return new ModelAndView("common/SelectOrg", "form", form);
	}

	public ModelAndView getInOrgByMoveNet(HttpServletRequest req, HttpServletResponse res){
		String outOrgId = CommonUtil.getSessionOrgId(req);
		String multiFlag = CommonUtil.getParameterEmpty(req, "multiFlag");
		String selectedValues = CommonUtil.getParameterEmpty(req, "selectedValues");
		SelectorForm form = new SelectorForm("true".equals(multiFlag), selectedValues);
		List<Org> list = moveNetManager.getInOrgByMoveNet(outOrgId);
		Map<String, Org> dataMap = new LinkedHashMap<String, Org>();
		for (Org org : list) {
			dataMap.put(org.getOrgId(), org);
		}
		form.setDataMap(dataMap);
		return new ModelAndView("common/SelectOrg", "form", form);
	}
	/**
	 * 根据推式总单id获取区域下的组织
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView getOrgByGatherOrder(HttpServletRequest req, HttpServletResponse res){
		String gatherOrderHeadId = CommonUtil.getParameterEmpty(req, "gatherOrderHeadId");
		String multiFlag = CommonUtil.getParameterEmpty(req, "multiFlag");
		String selectedValues = CommonUtil.getParameterEmpty(req, "selectedValues");
		SelectorForm form = new SelectorForm("true".equals(multiFlag), selectedValues);
		List<Org> list = gatherOrderManager.getOrgByGatherOrder(gatherOrderHeadId);
		Map<String, Org> dataMap = new LinkedHashMap<String, Org>();
		for (Org org : list) {
			dataMap.put(org.getOrgId(), org);
		}
		form.setDataMap(dataMap);
		return new ModelAndView("common/SelectOrg", "form", form);
	}
	/**
	 * 获取推式陈列标准组织
	 */
	public ModelAndView getDisplayOrg(HttpServletRequest req, HttpServletResponse res){
		String headid = CommonUtil.getParameterEmpty(req, "headid");
		String multiFlag = CommonUtil.getParameterEmpty(req, "multiFlag");
		String selectedValues = CommonUtil.getParameterEmpty(req, "selectedValues");
		SelectorForm form = new SelectorForm("true".equals(multiFlag), selectedValues);
		List<Org> list = displayStandardManager.getDisplayOrg(headid);
		Map<String, Org> dataMap = new LinkedHashMap<String, Org>();
		for (Org org : list) {
			dataMap.put(org.getOrgId(), org);
		}
		form.setDataMap(dataMap);
		return new ModelAndView("common/SelectOrg", "form", form);
	}
	public ModelAndView getInJmOrgByMoveNet(HttpServletRequest req, HttpServletResponse res){
		String outOrgId = CommonUtil.getSessionOrgId(req);
		String multiFlag = CommonUtil.getParameterEmpty(req, "multiFlag");
		String selectedValues = CommonUtil.getParameterEmpty(req, "selectedValues");
		SelectorForm form = new SelectorForm("true".equals(multiFlag), selectedValues);
		List<Org> list = moveNetManager.getInJmOrgByMoveNet(outOrgId);
		Map<String, Org> dataMap = new LinkedHashMap<String, Org>();
		for (Org org : list) {
			dataMap.put(org.getOrgId(), org);
		}
		form.setDataMap(dataMap);
		return new ModelAndView("common/SelectOrg", "form", form);
	}
	public ModelAndView getInJmOrgFromPack(HttpServletRequest req, HttpServletResponse res){
		String outOrgId = CommonUtil.getSessionOrgId(req);
		String multiFlag = CommonUtil.getParameterEmpty(req, "multiFlag");
		String selectedValues = CommonUtil.getParameterEmpty(req, "selectedValues");
		SelectorForm form = new SelectorForm("true".equals(multiFlag), selectedValues);
		List<Org> list = moveNetManager.getInJmOrgFromPack(outOrgId);
		Map<String, Org> dataMap = new LinkedHashMap<String, Org>();
		for (Org org : list) {
			dataMap.put(org.getOrgId(), org);
		}
		form.setDataMap(dataMap);
		return new ModelAndView("common/SelectOrg", "form", form);
	}

    public ModelAndView getCurrentUserJmOrgList(HttpServletRequest req, HttpServletResponse res){
        String userId = CommonUtil.getSessionUserId(req);
        String multiFlag = CommonUtil.getParameterEmpty(req, "multiFlag");
        String selectedValues = CommonUtil.getParameterEmpty(req, "selectedValues");
        SelectorForm form = new SelectorForm("true".equals(multiFlag), selectedValues);
        List<Org> list = commonManager.getCurrentUserJmOrgList(userId);
        Map<String, Org> dataMap = new LinkedHashMap<String, Org>();
        for (Org org : list) {
            dataMap.put(org.getOrgId(), org);
        }
        form.setDataMap(dataMap);
        return new ModelAndView("common/SelectOrg", "form", form);
    }
}