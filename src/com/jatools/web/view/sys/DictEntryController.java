package com.jatools.web.view.sys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.sys.DictManager;
import com.jatools.vo.sys.DictEntry;
import com.jatools.vo.sys.DictItem;
import com.jatools.web.form.sys.DictForm;
import com.jatools.web.view.BaseMultiActionController;

public class DictEntryController extends BaseMultiActionController {
	private DictManager dictManager;

	public void setDictManager(DictManager dictManager) {
		this.dictManager = dictManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		String entryCode = CommonUtil.getParameterEmpty(req, "entryCode");
		DictForm form = new DictForm();
		form.setEntryType(entryCode);
		if (DictConstant.PURCHASE_EMP.equals(entryCode)) {// 采购员
			return editPurEmp(entryCode, form);
		} else {
			form.setMessage("参数不正确");
		}
		return null;
	}

	private ModelAndView editPurEmp(String entryCode, DictForm form) {
		DictEntry entry = dictManager.getDictEntry(entryCode);
		form.setEntry(entry);
		List<DictItem> list = dictManager.getDictItemByEntryCode(entryCode);
		form.setList(list);
		return new ModelAndView("sys/dictPurEmp_edit", "form", form);
	}
}