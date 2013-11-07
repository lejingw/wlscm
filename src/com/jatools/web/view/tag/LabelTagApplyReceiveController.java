package com.jatools.web.view.tag;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.jatools.manager.tag.LabelTagApplyPrintManager;
import com.jatools.vo.tag.LabelTagApplyPrint;
import com.jatools.vo.tag.LabelTagApplyPrintLine;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.tag.LabelTagApplyPrintForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class LabelTagApplyReceiveController extends BaseMultiActionController {

private Logger logger = Logger.getLogger(LabelTagApplyReceiveController.class);
	
	private static final String REC_LIST_VM = "tag/labelTagApplyReceive_list";
	private static final String REC_EDIT_VM = "tag/labelTagApplyReceive_edit";
	private static final String PARAMS[] = {"billno", "orgId", "verdorId", "status", "startDate", "endDate", "dotype", "createId"};
	
	private LabelTagApplyPrintManager labelTagApplyPrintManager;

	public void setLabelTagApplyPrintManager(LabelTagApplyPrintManager LabelTagApplyPrintManager) {
		this.labelTagApplyPrintManager = LabelTagApplyPrintManager;
	}

	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		LabelTagApplyPrintForm form = new LabelTagApplyPrintForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			condition.put("userId", CommonUtil.getSessionUserId(req));
			condition.put("status", DictConstant.BILL_STATUS_RECEIVING);
			Pager pager = labelTagApplyPrintManager.getLabelTagApplyPrintData(condition);
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView(REC_LIST_VM, "form", form);
	}
	
	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		LabelTagApplyPrintForm form = new LabelTagApplyPrintForm();
		try {
			if(StringUtil.isNotBlank(id)){
				LabelTagApplyPrint LabelTagApplyPrint = this.labelTagApplyPrintManager.getLabelTagApplyPrint(id);
				List<LabelTagApplyPrintLine> lines = new ArrayList<LabelTagApplyPrintLine>();
				
				String recType = LabelTagApplyPrint.getLabelReceiveType();
				if(StringUtil.isNotBlank(recType)){
					Map<String, String> params = new HashMap<String, String>();
					params.put("headid", LabelTagApplyPrint.getId());
					if(DictConstant.LABEL_TAG_TYPE_1.equals(recType)){
						params.put("status", DictConstant.BILL_STATUS_RECEIVING);
					} else {
						params.put("status", DictConstant.BILL_STATUS_RECEIVED);
					}
					lines = this.labelTagApplyPrintManager.getLabelTagApplyPrintLines(params);
				}
				beforeView(lines);
				form.setLabelTagApplyPrint(LabelTagApplyPrint);
				form.setLines(lines);
			} else {
				form.setSuccessfulFlag(Boolean.FALSE);
				form.setMessage("加载失败");
			}
		} catch (Exception e) {
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取数据出错");
		}
		return new ModelAndView(REC_EDIT_VM, "form", form);
	}
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
		condition.put("userId", CommonUtil.getSessionUserId(req));
		condition.put("status", DictConstant.BILL_STATUS_RECEIVING);
		Pager pager = labelTagApplyPrintManager.getLabelTagApplyPrintData(condition);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("标签打印单");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"orgId", "createdate"}, new Integer[]{4, 3});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"单据编号", "组织", "打印类型", "件数合计",  "接收合计", "接收方式", "接收人员", "创建时间", "创建人", "修改时间", "修改人", "状态"});
		//添加列name
		excelData.setColumnName(new String[]{"no", "orgId", "printType", "numTotal", "receiveNum", "labelReceiveType", "receiveUserid", "createdate", "createuserid", "updatedate", "updateuserid", "state"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"orgId", "createuserid", "receiveUserid", "updateuserid"},
					new CacheSingletonIntf[]{OrgCache.getInstance(), UserCache.getInstance(), UserCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"state", "labelReceiveType"},
					new String[]{DictConstant.BILL_STATUS, DictConstant.LABEL_TAG_APPLY_PRINT});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
	
	private void beforeView(List<LabelTagApplyPrintLine> lines){
		if(CollectionUtils.isNotEmpty(lines)){
			for(LabelTagApplyPrintLine line : lines){
				if(StringUtil.isNotBlank(line.getNum())){
					line.setNum(StringUtil.formatDouble(line.getNum(), 4));
				}
				if(StringUtil.isNotBlank(line.getWeight())){
					line.setWeight(StringUtil.formatDouble(line.getWeight(), 4));
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
