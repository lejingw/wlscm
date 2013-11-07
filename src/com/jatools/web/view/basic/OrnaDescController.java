package com.jatools.web.view.basic;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.basic.OrnaDescManager;
import com.jatools.vo.basic.OrnaDesc;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.ArticleTypeCache;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.QualityCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.basic.OrgGroupForm;
import com.jatools.web.form.basic.OrnaDescForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class OrnaDescController extends BaseMultiActionController {
	private Logger logger = Logger.getLogger(OrnaDescController.class);

	private final static String VM_EDIT = "basic/ornaDesc_edit";
	private final static String VM_LIST = "basic/ornaDesc_list";
	private final static String FORM_KEY = "form";
	private final static String PARAMS[] = {"itemClassId", "ornaClassId", "articleTypeId", "qualityId"};
	
	private OrnaDescManager ornaDescManager;

	public void setOrnaDescManager(OrnaDescManager ornaDescManager) {
		this.ornaDescManager = ornaDescManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		OrgGroupForm form = new OrgGroupForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			Pager pager = this.ornaDescManager.getOrnaDescPageData(condition);
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView(VM_LIST, FORM_KEY, form);
	}
	/**
	 * 页面跳转
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		String ornadscId = CommonUtil.getParameterNull(req, "ornadscId");
		OrnaDescForm form = new OrnaDescForm();
		if(StringUtil.isNotBlank(ornadscId)){
			OrnaDesc ornaDesc = this.ornaDescManager.getOrnaDesc(ornadscId);
			form.setOrnaDesc(ornaDesc);
			return new ModelAndView(VM_EDIT,FORM_KEY,form);
		}else{
			return new ModelAndView(VM_EDIT, FORM_KEY,form);
		}
	}
	
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res){
		String ornadscId = CommonUtil.getParameterNull(req, "ornadscId");
		OrnaDescForm form = new OrnaDescForm();
		if(StringUtil.isNotEmpty(ornadscId)){
			this.ornaDescManager.deleteOrnaDesc(ornadscId);
			form.setSuccessfulFlag(true);
			form.setMessage("删除成功");
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			Pager pager = this.ornaDescManager.getOrnaDescPageData(condition);
			form.setPager(pager);
		}else{
			form.setSuccessfulFlag(false);
			form.setMessage("不能获取交接数据id");
		}
		return new ModelAndView(VM_LIST, FORM_KEY, form);
	}
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPage(req, PARAMS);
		
		Pager pager = this.ornaDescManager.getOrnaDescPageData(condition);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("饰品名称管理");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"createdate"}, new Integer[]{3});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"商品类别", "大类", "小类", "材质", "饰品名称", "创建时间", "创建人", "修改时间", "修改人", "状态"});
		//添加列name
		excelData.setColumnName(new String[]{"articleTypeId", "itemClassId", "ornaClassId", "qualityId", "ornaDsc", "createdate", "createid", "updatedate", "updateid", "status"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"createid", "itemClassId", "ornaClassId", "articleTypeId", "qualityId", "updateid"},
					new CacheSingletonIntf[]{UserCache.getInstance(), ItemClassCache.getInstance(), OrnaClassCache.getInstance(), ArticleTypeCache.getInstance(), QualityCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status"},	new String[]{DictConstant.BILL_STATUS});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
	
}
