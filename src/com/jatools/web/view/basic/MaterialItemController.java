package com.jatools.web.view.basic;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.basic.MaterialItemManager;
import com.jatools.vo.basic.MaterialItem;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.basic.MaterialItemForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class MaterialItemController extends BaseMultiActionController {
	private Logger logger = Logger.getLogger(MaterialItemController.class);

	private static final String PARAMS[] = {"status", "itemClassId", "materialType", "createId", "startDate", "endDate"};
	private MaterialItemManager materialItemManager;

	public void setMaterialItemManager(MaterialItemManager materialItemManager) {
		this.materialItemManager = materialItemManager;
	}
	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		MaterialItemForm form = new MaterialItemForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			condition.put("orgId", CommonUtil.getSessionOrgId(req));
			Pager pager = materialItemManager.getMaterialItemPageData(condition);
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("basic/materialItem_list", "form", form);
	}
	
	/**
	 * 页面跳转
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		String materialItemId = CommonUtil.getParameterNull(req, "materialItemId");
		MaterialItemForm form = new MaterialItemForm();
		if(StringUtil.isNotBlank(materialItemId)){
			MaterialItem materialItem = materialItemManager.getMaterialItem(materialItemId);
			form.setMaterialItem(materialItem);
			return new ModelAndView("basic/materialItem_edit","form",form);
		}else{
			return new ModelAndView("basic/materialItem_edit","form",form);
		}
	}
	
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res){
		String materialItemId = CommonUtil.getParameterNull(req, "materialItemId");
		MaterialItemForm form = new MaterialItemForm();
		if(StringUtil.isNotEmpty(materialItemId)){
			materialItemManager.deleteMaterialItem(materialItemId);
			form.setSuccessfulFlag(true);
			form.setMessage("删除成功");
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			condition.put("orgId", CommonUtil.getSessionOrgId(req));
			Pager pager = materialItemManager.getMaterialItemPageData(condition);
			form.setPager(pager);
		}else{
			form.setSuccessfulFlag(false);
			form.setMessage("不能获取交接数据id");
		}
		return new ModelAndView("basic/materialItem_list", "form", form);
	}
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
		condition.put("orgId", CommonUtil.getSessionOrgId(req));
		Pager pager = materialItemManager.getMaterialItemPageData(condition);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("原料大类关系");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"createDate", "memo"}, new Integer[]{3, 4});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"原料类型", "大类", "结算单位", "备注", "创建时间", "创建人","修改时间", "修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"materialType", "itemClassId", "purUnit", "memo", "createDate", "createId", "updateDate", "updateId"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"createId", "itemClassId", "updateId"},
					new CacheSingletonIntf[]{UserCache.getInstance(), ItemClassCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"materialType", "purUnit"},
					new String[]{DictConstant.MATERIAL_TYPE, DictConstant.PURUNIT});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
}
