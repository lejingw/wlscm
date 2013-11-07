package com.jatools.web.view.tag;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.tag.LabelTagApplyManager;
import com.jatools.vo.tag.LabelTagApply;
import com.jatools.vo.tag.LabelTagApplyLine;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.tag.LabelTagApplyForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class LabelTagApplyController extends BaseMultiActionController {

private Logger logger = Logger.getLogger(LabelTagApplyController.class);
	
	private static final String LIST_VM = "tag/labelTagApply_list";
	private static final String EDIT_VM = "tag/labelTagApply_edit";
	private static final String SHOW_VM = "tag/labelTagApply_show";
	private static final String PARAMS[] = {"billno", "orgId", "verdorId", "status", "startDate", "endDate", "dotype", "createId", "ornaCode", "printNo"};
	
	private LabelTagApplyManager labelTagApplyManager;

	public void setLabelTagApplyManager(LabelTagApplyManager labelTagApplyManager) {
		this.labelTagApplyManager = labelTagApplyManager;
	}

	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		LabelTagApplyForm form = new LabelTagApplyForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			condition.put("userId", CommonUtil.getSessionUserId(req));
			Pager pager = labelTagApplyManager.getLabelTagApplyData(condition);
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView(LIST_VM, "form", form);
	}
	
	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		LabelTagApplyForm form = new LabelTagApplyForm();
		String vm_path = EDIT_VM;
		try {
			if(StringUtil.isNotBlank(id)){
				LabelTagApply labelTagApply = this.labelTagApplyManager.getLabelTagApply(id);
				List<LabelTagApplyLine> lines = this.labelTagApplyManager.getLabelTagApplyLines(id);
				beforeView(lines);
				form.setLabelTagApply(labelTagApply);
				form.setLines(lines);
				if(labelTagApply != null){
					String curOrgId = CommonUtil.getSessionOrgId(req);
					if(curOrgId.equals(labelTagApply.getOrgId())){
						vm_path = EDIT_VM;
					} else {
						vm_path = SHOW_VM;
					}
				}
			} else {
				form.setSuccessfulFlag(Boolean.FALSE);
				form.setMessage("加载失败");
			}
		} catch (Exception e) {
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取数据出错");
		}
		return new ModelAndView(vm_path, "form", form);
	}
	
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		LabelTagApplyForm form = new LabelTagApplyForm();
		try {
			if(StringUtil.isNotBlank(id)){
				this.labelTagApplyManager.deleteLabelTagApply(id);
				Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
				condition.put("userId", CommonUtil.getSessionUserId(req));
				Pager pager = labelTagApplyManager.getLabelTagApplyData(condition);
				form.setPager(pager);
				form.setCondition(condition);
			} else {
				form.setSuccessfulFlag(Boolean.FALSE);
				form.setMessage("加载失败");
			}
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取数据出错");
		}
		return new ModelAndView(LIST_VM, "form", form);
	}
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
		condition.put("userId", CommonUtil.getSessionUserId(req));
		Pager pager = labelTagApplyManager.getLabelTagApplyData(condition);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("标签申请单");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"orgId", "createdate"}, new Integer[]{4, 3, 4});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"单据编号", "组织", "件数合计", "打印单号", "换标签原因", "创建人", "创建时间", "修改时间", "状态"});
		//添加列name
		excelData.setColumnName(new String[]{"no", "orgId", "numTotal", "printNo", "changelabelName",  "createuserid", "createdate", "updatedate", "state"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"orgId", "createuserid"},
					new CacheSingletonIntf[]{OrgCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"state"},
					new String[]{DictConstant.BILL_STATUS});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}

	private void beforeView(List<LabelTagApplyLine> lines){
		if(CollectionUtils.isNotEmpty(lines)){
			for(LabelTagApplyLine line : lines){
				if(StringUtil.isNotBlank(line.getNum())){
					line.setNum(StringUtil.formatDouble(line.getNum(), 4));
				}
				if(StringUtil.isNotBlank(line.getWeighr())){
					line.setWeighr(StringUtil.formatDouble(line.getWeighr(), 4));
				}
				if(StringUtil.isNotBlank(line.getOldBasicPrice())){
					line.setOldBasicPrice(StringUtil.formatDouble(line.getOldBasicPrice(), 4));
				}
				if(StringUtil.isNotBlank(line.getNewBasicPrice())){
					line.setNewBasicPrice(StringUtil.formatDouble(line.getNewBasicPrice(), 4));
				}
				if(StringUtil.isNotBlank(line.getOldAmount())){
					line.setOldAmount(StringUtil.formatDouble(line.getOldAmount(), 4));
				}
				if(StringUtil.isNotBlank(line.getNewAmount())){
					line.setNewAmount(StringUtil.formatDouble(line.getNewAmount(), 4));
				}
				if(StringUtil.isNotBlank(line.getOldSpecialWorkPrice())){
					line.setOldSpecialWorkPrice(StringUtil.formatDouble(line.getOldSpecialWorkPrice(), 4));
				}
				if(StringUtil.isNotBlank(line.getNewSpecialWorkPrice())){
					line.setNewSpecialWorkPrice(StringUtil.formatDouble(line.getNewSpecialWorkPrice(), 4));
				}
			}
		}
	}
	
	
}
