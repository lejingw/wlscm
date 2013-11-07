package com.jatools.web.view.basic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.manager.basic.CreateOldbarCodeManager;
import com.jatools.vo.bd.Tag;
import com.jatools.web.form.BaseForm;
import com.jatools.web.form.util.CreateOldbarCodeForm;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class CreateOldbarCodeController extends  BaseMultiActionController{
	private CreateOldbarCodeManager createOldbarCodeManager;

	public void setCreateOldbarCodeManager(CreateOldbarCodeManager createOldbarCodeManager) {
		this.createOldbarCodeManager = createOldbarCodeManager;
	}
	
	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		BaseForm form = new BaseForm();
		return new ModelAndView("basic/createOldbarCode", "form", form);
	}
	
	public ModelAndView printLable(HttpServletRequest req, HttpServletResponse res){
		String preFix = CommonUtil.getParameterEmpty(req, "preFix");
		String startIndex = CommonUtil.getParameterEmpty(req, "startIndex");
		String count = CommonUtil.getParameterEmpty(req, "count");
		CreateOldbarCodeForm form = new CreateOldbarCodeForm();
		if(!StringUtil.isEmpty(preFix) && !StringUtil.isEmpty(startIndex) && !StringUtil.isEmpty(count)){
			JSONArray lines = new JSONArray();
			for(int i=Integer.parseInt(startIndex);i<Integer.parseInt(startIndex) + Integer.parseInt(count);i++){
				Tag tag = new Tag();
				tag.setOrnaCode(preFix + getSerialNo(i));
				tag.setOrnaBarCode(tag.getOrnaCode());
				lines.add(tag);
			}
			JSONObject root = new JSONObject();
			root.put("lines", lines);
			form.setPrintData(root.toString());
		}
		return new ModelAndView("common/printLabel", "form", form);
	}

	private String getSerialNo(int num) {
		String numStr = "";
		for(int i=5-(""+num).length();i>0;i--){
			numStr += "0";
		}
		return numStr + num;
	}
}
