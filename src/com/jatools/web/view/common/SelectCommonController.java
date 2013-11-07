package com.jatools.web.view.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import com.jatools.common.CommonUtil;
import com.jatools.manager.common.SelectCommonManager;
import com.jatools.vo.basic.Vendor;
import com.jatools.vo.sys.Dict;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.cache.ArticleTypeCache;
import com.jatools.web.cache.VendorCache;
import com.jatools.web.form.common.SelectorForm;
import com.jatools.web.view.BaseMultiActionController;

public class SelectCommonController extends BaseMultiActionController {

	private SelectCommonManager selectCommonManager;

	public void setSelectCommonManager(SelectCommonManager selectCommonManager) {
		this.selectCommonManager = selectCommonManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		String multiFlag = CommonUtil.getParameterEmpty(req, "multiFlag");
		String selectedValues = CommonUtil.getParameterEmpty(req, "selectedValues");
		SelectorForm form = new SelectorForm("true".equals(multiFlag), selectedValues);
		return new ModelAndView("common/SelectVendor", "form", form);
	}
	/**
	 * 选择供应商
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView selectVendor(HttpServletRequest req, HttpServletResponse res) {
		String vender = CommonUtil.getParameterEmpty(req, "vender");
		String multiFlag = CommonUtil.getParameterEmpty(req, "multiFlag");
		String selectedValues = CommonUtil.getParameterEmpty(req, "selectedValues");
		SelectorForm form = new SelectorForm("true".equals(multiFlag), selectedValues);
		Map<String, Vendor> allVendorMap = VendorCache.getInstance().getAllVendor();
		Map<String, String> dataMap = new LinkedHashMap<String, String>();
		for(String key : allVendorMap.keySet()){
			Vendor vendor = allVendorMap.get(key);
			if(vendor.getVendorName().indexOf(vender)>-1){				
				dataMap.put(vendor.getVendorId(), vendor.getVendorName().trim());
			}
		}
		form.setVender(vender);
		form.setDataMap(dataMap);
		return new ModelAndView("common/SelectVendor", "form", form);
	}
	/**
	 * 选择商品类别
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView selectArticleType(HttpServletRequest req, HttpServletResponse res) {
//		String vender = CommonUtil.getParameterEmpty(req, "vender");
		String multiFlag = CommonUtil.getParameterEmpty(req, "multiFlag");
		String selectedValues = CommonUtil.getParameterEmpty(req, "selectedValues");
		SelectorForm form = new SelectorForm("true".equals(multiFlag), selectedValues);
		Map<String, String> dataMap = ArticleTypeCache.getInstance().getAllArticleType();
		form.setDataMap(dataMap);
		return new ModelAndView("common/SelectStyleClass", "form", form);
	}

	
}