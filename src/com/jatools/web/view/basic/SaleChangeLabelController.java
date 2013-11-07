package com.jatools.web.view.basic;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.basic.SaleChangeLabelManager;
import com.jatools.vo.basic.SaleChangeLabel;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.basic.SaleChangeLabelForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class SaleChangeLabelController extends BaseMultiActionController {
	private Logger logger = Logger.getLogger(SaleChangeLabelController.class);

	private SaleChangeLabelManager saleChangeLabelManager;

	public void setSaleChangeLabelManager(SaleChangeLabelManager saleChangeLabelManager) {
		this.saleChangeLabelManager = saleChangeLabelManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		SaleChangeLabelForm form = new SaleChangeLabelForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "changelabelReason");
			Pager pager = saleChangeLabelManager.getSaleChangeLabelPageData(condition);
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("basic/saleChangeLabel_list", "form", form);
	}
	
	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		String changelabelId = CommonUtil.getParameterNull(req, "changelabelId");
		SaleChangeLabelForm form = new SaleChangeLabelForm();
		if(StringUtil.isNotBlank(changelabelId)){
			SaleChangeLabel SaleChangeLabel = saleChangeLabelManager.getSaleChangeLabel(changelabelId);
			form.setSaleChangeLabel(SaleChangeLabel);
			return new ModelAndView("basic/saleChangeLabel_edit","form",form);
		}else{
			return new ModelAndView("basic/saleChangeLabel_edit","form",form);
		}
	}
	
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "changelabelReason");
		Pager pager = saleChangeLabelManager.getSaleChangeLabelPageData(condition);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("换标签原因");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"createdate", "updatedate"}, new Integer[]{3, 3});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"原因", "标签种类", "新增金额是否为空", "新增基础价是否为空", "新增特殊工费是否为空", "更新原因是否为空", "备注", "创建时间", "创建人", "修改时间", "修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"changelabelReason", "labelKind", "isincreaseamount","isbasicPrice", "specialWorkPrice", "isupdatecauses", "note", "createdate", "createuserid", "updatedate", "updateuserid",});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"createuserid", "updateuserid"},
					new CacheSingletonIntf[]{UserCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status", "isincreaseamount", "isbasicPrice", "specialWorkPrice", 
				"isupdatecauses", "labelKind"},
					new String[]{DictConstant.BILL_STATUS, DictConstant.YES_OR_NO, DictConstant.YES_OR_NO, DictConstant.YES_OR_NO, 
				DictConstant.YES_OR_NO, DictConstant.LABEL_KIND});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
}
