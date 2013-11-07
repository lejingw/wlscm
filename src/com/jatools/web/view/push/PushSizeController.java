package com.jatools.web.view.push;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.push.PushSizeManager;
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

public class PushSizeController extends BaseMultiActionController {
	private PushSizeManager pushSizeManager;

	public void setPushSizeManager(PushSizeManager pushSizeManager) {
		this.pushSizeManager = pushSizeManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		BaseForm form = new BaseForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = pushSizeManager.getPushSizePageData(condition);
		form.setPager(pager);
		return new ModelAndView("push/pushSize_list", "form", form);
	}
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = pushSizeManager.getPushSizePageData(condition);
		
		ExcelData excelData = new ExcelData();//excel数据对象
		excelData.setTitle("尺寸比例");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.setColumnTitle(new String[]{"大类", "小类", "款式大类", "尺寸起", "尺寸止", "尺寸名称", "比例", "创建人", "创建时间", "修改人", "修改时间", "状态"});
		excelData.setColumnName(new String[]{"itemClassId", "ornaClassId", "styleItemClassId","startSizeName", "endSizeName", "sizeName", "rate", "createId", "createDate", "updateId", "updateDate", "status"});
//		excelData.addColumnWidth(new String[]{"startDate", "endDate", "orgId", "itemClassId", "ornaClassId", "analysisName","styleItemClassId", "turnoverNum", "createId", "createDate", "updateId", "updateDate", "status"}, new Integer[]{4, 4, 3, 3, 4});//宽度默认值为2
		//设置对应的缓存数据
		excelData.addSingletonDisplayColumns(new String[]{"itemClassId", "ornaClassId", "styleItemClassId", "createId", "updateId"},
					new CacheSingletonIntf[]{ItemClassCache.getInstance(), OrnaClassCache.getInstance(), StyleItemClassCache.getInstance(), UserCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status"}, new String[]{DictConstant.BILL_STATUS});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);
		util.export(res);
		return null;
	}
}
