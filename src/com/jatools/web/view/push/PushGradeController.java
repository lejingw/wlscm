package com.jatools.web.view.push;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jatools.common.ExcelUtil;
import com.jatools.common.excel.*;
import com.jatools.manager.util.ExcelUtilManager;
import com.jatools.vo.util.ExcelRowData;
import com.jatools.web.util.StringUtil;
import org.springframework.web.servlet.ModelAndView;
import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.push.PushGradeManager;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.StyleItemClassCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.BaseForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.view.BaseMultiActionController;

public class PushGradeController extends BaseMultiActionController {
	private PushGradeManager pushGradeManager;

	public void setPushGradeManager(PushGradeManager pushGradeManager) {
		this.pushGradeManager = pushGradeManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		BaseForm form = new BaseForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = pushGradeManager.getPushGradePageData(condition);
		form.setPager(pager);
		return new ModelAndView("push/pushGrade_list", "form", form);
	}
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = pushGradeManager.getPushGradePageData(condition);
		
		ExcelData excelData = new ExcelData();//excel数据对象
		excelData.setTitle("下单品质");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.setColumnTitle(new String[]{"大类", "小类", "分析范围", "色级", "净度", "品质", "比例", "创建人", "创建时间", "修改人", "修改时间", "状态"});
		excelData.setColumnName(new String[]{"itemClassId", "ornaClassId", "analysisName","colorGradeId", "cleanId", "gradeName", "rate", "createId", "createDate", "updateId", "updateDate", "status"});
//		excelData.addColumnWidth(new String[]{"startDate", "endDate", "orgId", "itemClassId", "ornaClassId", "analysisName","styleItemClassId", "turnoverNum", "createId", "createDate", "updateId", "updateDate", "status"}, new Integer[]{4, 4, 3, 3, 4});//宽度默认值为2
		//设置对应的缓存数据
		excelData.addSingletonDisplayColumns(new String[]{"orgId", "itemClassId", "ornaClassId", "styleItemClassId", "createId", "updateId"},
					new CacheSingletonIntf[]{OrgCache.getInstance(), ItemClassCache.getInstance(), OrnaClassCache.getInstance(), StyleItemClassCache.getInstance(), UserCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"colorGradeId", "cleanId", "status"}, new String[]{DictConstant.DIA_COLORGRADE,DictConstant.DIA_CLEAN,DictConstant.BILL_STATUS});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);
		util.export(res);
		return null;
	}

    public ModelAndView importExcel(HttpServletRequest req, HttpServletResponse res) {
        this.pushGradeManager.importExcel(req, res);
        return null;
    }

}
