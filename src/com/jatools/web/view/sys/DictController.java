package com.jatools.web.view.sys;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.manager.sys.DictManager;
import com.jatools.vo.sys.DictEntry;
import com.jatools.vo.sys.DictItem;
import com.jatools.web.form.sys.DictForm;
import com.jatools.web.view.BaseMultiActionController;

public class DictController extends BaseMultiActionController {
	private String listKey;
	private DictManager dictManager;

	public void setDictManager(DictManager dictManager) {
		this.dictManager = dictManager;
	}

	public String getSessionKey(){
		return super.getSessionKey() + listKey;
	}
	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		String entryType = CommonUtil.getParameterEmpty(req, "entryType");
		DictForm form = new DictForm();
		form.setEntryType(entryType);
		listKey = entryType;
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "entryType");
		if("1".equals(entryType)){//系统数据字典
			Pager pager = dictManager.getDictEntryPageData(condition);
			form.setPager(pager);
		}else if("2".equals(entryType)){//业务数据字典
			Pager pager = dictManager.getDictEntryPageData(condition);
			form.setPager(pager);
		}else{
			form.setMessage("参数不正确");
		}
        form.setCondition(condition);
		return new ModelAndView("sys/dict_list", "form", form);
	}

	public ModelAndView toAdd(HttpServletRequest req, HttpServletResponse res) {
		String entryType = CommonUtil.getParameterEmpty(req, "entryType");
		DictForm form = new DictForm();
		form.setEntryType(entryType);
		if(!"1".equals(entryType) && !"2".equals(entryType)){//业务数据字典
			form.setMessage("参数不正确");
			return new ModelAndView("sys/dict_list", "form", form);
		}
		form.setAction("save");
		return new ModelAndView("sys/dict_edit", "form", form);
	}
	
	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		String entryType = CommonUtil.getParameterEmpty(req, "entryType");
		DictForm form = new DictForm();
		form.setEntryType(entryType);
		if(!"1".equals(entryType) && !"2".equals(entryType)){//业务数据字典
			form.setMessage("参数不正确");
			return new ModelAndView("sys/dict_list", "form", form);
		}
		String entryCode = CommonUtil.getParameterEmpty(req, "entryCode");
		DictEntry entry = dictManager.getDictEntry(entryCode);
		if(null == entry){
			form.setMessage("不能根据编码["+entryCode+"]获取数据字典");
			return new ModelAndView("sys/dict_list", "form", form);
		}
		form.setEntry(entry);
		List<DictItem> list = dictManager.getDictItemByEntryCode(entryCode);
		form.setList(list);
		form.setAction("update");
		return new ModelAndView("sys/dict_edit", "form", form);
	}
	
	public ModelAndView toEditCoeff(HttpServletRequest req, HttpServletResponse res) {
		String entryType = CommonUtil.getParameterEmpty(req, "entryType");
		DictForm form = new DictForm();
		form.setEntryType(entryType);
		if(!"1".equals(entryType) && !"2".equals(entryType)){//业务数据字典
			form.setMessage("参数不正确");
			return new ModelAndView("sys/dict_list", "form", form);
		}
		String entryCode = CommonUtil.getParameterEmpty(req, "entryCode");
		DictEntry entry = dictManager.getDictEntry(entryCode);
		if(null == entry){
			form.setMessage("不能根据编码["+entryCode+"]获取数据字典");
			return new ModelAndView("sys/dict_list", "form", form);
		}
		form.setEntry(entry);
		List<DictItem> list = dictManager.getDictItemByEntryCode(entryCode);
		form.setList(list);
		form.setAction("update");
		return new ModelAndView("basic/dict_coefficien_unit", "form", form);
	}
}
