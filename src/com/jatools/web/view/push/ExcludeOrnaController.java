package com.jatools.web.view.push;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.push.ExcludeOrnaManager;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.QualityCache;
import com.jatools.web.cache.StyleItemClassCache;
import com.jatools.web.cache.UnitCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.BaseForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.view.BaseMultiActionController;

public class ExcludeOrnaController extends BaseMultiActionController {
	private ExcludeOrnaManager excludeOrnaManager;
	private String order;

	public void setExcludeOrnaManager(ExcludeOrnaManager excludeOrnaManager) {
		this.excludeOrnaManager = excludeOrnaManager;
	}
	public String getSessionKey(){
		return this.getClass().getName() + order;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		BaseForm form = new BaseForm();
		order = "1";
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = excludeOrnaManager.getExcludeOrnaPageData(condition);
		form.setPager(pager);
		return new ModelAndView("push/excludeOrna_list", "form", form);
	}
	public ModelAndView query(HttpServletRequest req, HttpServletResponse res) {
		BaseForm form = new BaseForm();
		order = "2";
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req,
				"orgId", "itemClassId", "ornaClassId", "analysisId", "styleItemClassId", "styleMiddleClassId",
				"styleOrnaClassId", "styleId", "posAmountStart", "posAmountEnd", "lockFlag", "barCode", "ornaCode");
		form.setCondition(condition);
		String flag = CommonUtil.getParameterEmpty(req, "flag");
		if("1".equals(flag)){
			Pager pager = excludeOrnaManager.queryExcludeOrnaPageData(condition);
			form.setPager(pager);
		}else{
			Pager pager = new Pager();
			form.setPager(pager);
		}
		return new ModelAndView("push/excludeOrna_query", "form", form);
	}
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = excludeOrnaManager.getExcludeOrnaPageData(condition);
		
		ExcelData excelData = new ExcelData();//excel数据对象
		excelData.setTitle("排除饰品");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.setColumnTitle(new String[]{"饰品编码", "饰品名称", "状态", "组织", "大类", "小类", "分析范围", "款式", "色级", "净度", "品质", "尺寸", "商品材质", "托架颜色", "计量单位", "创建人", "创建时间"});
		excelData.setColumnName(new String[]{"ornaCode", "ornaDsc", "status", "orgId", "itemClassId", "ornaClassId", "analysisName", "styleName", "colorGradeId", "cleanId", "gradeName", "sizeName", "qualityId", "bracketcolorId", "unitId", "createId", "createDate"});
//		excelData.addColumnWidth(new String[]{"startDate", "endDate", "orgId", "itemClassId", "ornaClassId", "analysisName","styleItemClassId", "turnoverNum", "createId", "createDate", "updateId", "updateDate", "status"}, new Integer[]{4, 4, 3, 3, 4});//宽度默认值为2
		//设置对应的缓存数据
		excelData.addSingletonDisplayColumns(new String[]{"orgId", "itemClassId", "ornaClassId", "styleItemClassId", "qualityId", "unitId", "createId"},
					new CacheSingletonIntf[]{OrgCache.getInstance(), ItemClassCache.getInstance(), OrnaClassCache.getInstance(), StyleItemClassCache.getInstance(), QualityCache.getInstance(), UnitCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status", "colorGradeId", "cleanId", "bracketcolorId"}, new String[]{DictConstant.BILL_STATUS, DictConstant.DIA_COLORGRADE, DictConstant.DIA_CLEAN, DictConstant.BRACKET_COLOR});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);
		util.export(res);
		return null;
	}
}
