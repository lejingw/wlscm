package com.jatools.web.view.push;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.push.PushStyleManager;
import com.jatools.vo.push.PushStyle;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.*;
import com.jatools.web.form.push.PushStyleForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class PushStyleController extends BaseMultiActionController {
	private PushStyleManager pushStyleManager;

	public void setPushStyleManager(PushStyleManager PushStyleManager) {
		this.pushStyleManager = PushStyleManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		PushStyleForm form = new PushStyleForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = pushStyleManager.getPushStylePageData(condition);
		form.setPager(pager);
		return new ModelAndView("push/push_style_list", "form", form);
	}

	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		PushStyleForm form = new PushStyleForm();
        String id = CommonUtil.getParameterEmpty(req, "id");
        if(StringUtil.isNotBlank(id)) {
            PushStyle PushStyle = this.pushStyleManager.getPushStyleById(id);
            form.setPushStyle(PushStyle);
        }
        return new ModelAndView("push/push_style_edit", "form", form);
	}

	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = pushStyleManager.getPushStylePageData(condition);
		ExcelData excelData = new ExcelData();
		excelData.setTitle("推式款式配对");
		excelData.setPager(pager);
		excelData.setColumnTitle(new String[]{"大类", "小类", "款式大类", "款式1", "款式2",  "创建人", "创建时间", "修改人", "修改时间", "状态"});
		excelData.setColumnName(new String[]{"itemClassId", "ornaClassId", "styleItemClassId", "styleName1", "styleName2", "createId", "createDate", "updateId", "updateDate", "status"});
		//设置对应的缓存数据
		excelData.addSingletonDisplayColumns(new String[]{"createId", "updateId", "itemClassId", "ornaClassId","styleItemClassId" },
					new CacheSingletonIntf[]{UserCache.getInstance(), UserCache.getInstance(), ItemClassCache.getInstance(), OrnaClassCache.getInstance(), StyleItemClassCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status"}, new String[]{DictConstant.BILL_STATUS});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);
		util.export(res);
		return null;
	}
}
