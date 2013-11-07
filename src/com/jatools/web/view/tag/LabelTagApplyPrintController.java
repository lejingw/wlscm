package com.jatools.web.view.tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.stock.MaterActiveManager;
import com.jatools.manager.tag.LabelTagApplyPrintManager;
import com.jatools.vo.bd.Tag;
import com.jatools.vo.stock.MaterActive;
import com.jatools.vo.tag.LabelTagApplyPrint;
import com.jatools.vo.tag.LabelTagApplyPrintLine;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.stock.ProcPackageHeadForm;
import com.jatools.web.form.tag.LabelTagApplyPrintForm;
import com.jatools.web.util.BdCommon;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class LabelTagApplyPrintController extends BaseMultiActionController {

private Logger logger = Logger.getLogger(LabelTagApplyPrintController.class);
	
	private static final String LIST_VM = "tag/labelTagApplyPrint_list";
	private static final String EDIT_VM = "tag/labelTagApplyPrint_edit";
	private static final String DISEDIT_VM = "tag/labelTagApplyPrint_disedit";
	private static final String SHOW_VM = "tag/labelTagApplyPrint_show";
	private static final String PRINT_VM = "common/printLabelJson";
	private static final String PARAMS[] = {"billno", "orgId", "verdorId", "status", "startDate", "endDate", "dotype", "createId"};
	private static final String LINEPARAMS[] = {"id", "labelType", "orderBy"};
	
	private LabelTagApplyPrintManager labelTagApplyPrintManager;
	private MaterActiveManager materActiveManager; 

	public void setMaterActiveManager(MaterActiveManager materActiveManager) {
		this.materActiveManager = materActiveManager;
	}

	public void setLabelTagApplyPrintManager(LabelTagApplyPrintManager LabelTagApplyPrintManager) {
		this.labelTagApplyPrintManager = LabelTagApplyPrintManager;
	}

	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		LabelTagApplyPrintForm form = new LabelTagApplyPrintForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			condition.put("userId", CommonUtil.getSessionUserId(req));
			Pager pager = labelTagApplyPrintManager.getLabelTagApplyPrintData(condition);
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
		LabelTagApplyPrintForm form = new LabelTagApplyPrintForm();
		String vm = EDIT_VM;
		try {
			if(StringUtil.isNotBlank(id)){
				LabelTagApplyPrint LabelTagApplyPrint = this.labelTagApplyPrintManager.getLabelTagApplyPrint(id);
				form.setLabelTagApplyPrint(LabelTagApplyPrint);
				
				if(!DictConstant.BILL_STATUS_SAVE.equals(LabelTagApplyPrint.getState())){
					vm = SHOW_VM;
					Map<String, String> condition = CommonUtil.getConditionForPage(req, LINEPARAMS);
					condition.put("headid", id);
					Pager pager = this.labelTagApplyPrintManager.getLabelTagApplyPrintLineData(condition);
					beforeView(pager);
					form.setPager(pager);
					dealOrderRule(condition);
					form.setCondition(condition);
				} else {
					List<LabelTagApplyPrintLine> lines = this.labelTagApplyPrintManager.getLabelTagApplyPrintLines(id);
					beforeView(lines);
					form.setLines(lines);
					String curOrgId = CommonUtil.getSessionOrgId(req);
					if(!curOrgId.equals(LabelTagApplyPrint.getOrgId())){
						vm = DISEDIT_VM;
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
		return new ModelAndView(vm, "form", form);
	}
	
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		LabelTagApplyPrintForm form = new LabelTagApplyPrintForm();
		try {
			if(StringUtil.isNotBlank(id)){
				this.labelTagApplyPrintManager.deleteLabelTagApplyPrint(id);
				Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
				Pager pager = labelTagApplyPrintManager.getLabelTagApplyPrintData(condition);
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
	public ModelAndView toPrintLabel(HttpServletRequest req, HttpServletResponse res){
		String headid = CommonUtil.getParameterNull(req, "id");
		String userId = CommonUtil.getParameterNull(req, "_u_i_");
		String labelType = CommonUtil.getParameterNull(req, "labelType");
		String lineids = CommonUtil.getParameterNull(req, "lineids");
		String orderBy = CommonUtil.getParameterNull(req, "orderBy");
		ProcPackageHeadForm form = new ProcPackageHeadForm();
		Map<String, String> condition = new HashMap<String, String>();
		try{
			JSONArray lines = new JSONArray();
			JSONObject root = new JSONObject();
			if(StringUtil.isNotBlank(headid) && StringUtil.isNotBlank(lineids)){
				// TODO : 修改 申请单和打印单的状态为 接收中（头表和行表） 状态必须是 已合并的， 弹出打印页面
				this.labelTagApplyPrintManager.updateLabelApplyStatus(headid, userId);
				List<Tag> printData = this.labelTagApplyPrintManager.getPrintData(headid, labelType, lineids, orderBy);
				
				if(CollectionUtils.isNotEmpty(printData)){
					for(Tag tag : printData){
                        /**
                         * 2012-11-07 需求
                         * 特殊工费为0 置空 特殊工费
                         */
                        if("0".equals(tag.getSpecialWorkPrice())) {
                            tag.setSpecialWorkPrice(null);
                        }
						String ornaCode = tag.getOrnaCode();
						MaterActive m = this.materActiveManager.getMaterActiveByOrnaCodeTagPrint(ornaCode);
						
						if(m.getItemClassId().equals("146")&&//2012.07.13 修改
								(m.getOrnaClassId().equals("59")||m.getOrnaClassId().equals("65"))){
							
						}else{
							if(null!=m.getSpecialWorkPrice()&&!"".equals(m.getSpecialWorkPrice())){	
								//tag.setSpecialWorkPrice(m.getSpecialWorkPrice()+""); 2012-11-07 去除
							}else{//
								if("117".equals(m.getSupplierId())){//供应商为明牌
									tag.setSpecialWorkPrice(BdCommon.getParameterValue("verdor_charge_fare_xs"));
								}else{
									tag.setSpecialWorkPrice(BdCommon.getParameterValue("verdor_charge_price_xs"));
								}
								
							}
						}
						
						/*if(StringUtil.isNotBlank(tag.getIdent()) && tag.getIdent().contains("!")){
							tag.setIdent(tag.getIdent().replace("!", "$"));
						}*/
						JSON line = JSONSerializer.toJSON(tag);
						lines.add(line.toString());
					}
				}
			}
			root.put("lines", lines);
			condition.put("pt", labelType);
			condition.put("params", "-"+labelType);
			form.setPrintData(root.toString());
			form.setCondition(condition);
		} catch (Exception e) {
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		
		return new ModelAndView(PRINT_VM, "form", form);
	}
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
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
	
	private void beforeView(Pager pager){
		List<Object> lines = pager.getPageData();
		if(CollectionUtils.isNotEmpty(lines)){
			for(Object obj : lines){
				LabelTagApplyPrintLine line = (LabelTagApplyPrintLine)obj;
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
	
	
	private void dealOrderRule(Map<String, String> condition){
		String orderBy = condition.get("orderBy");
		if(StringUtil.isNotBlank(orderBy)){
			if(orderBy.contains("DESC")){
				condition.put("orderDESC", "checked");
			} else {
				condition.put("orderASC", "checked");
			}
			if(orderBy.contains("item_class_id")){
				condition.put("item_class_id", "checked");
			} else {
				condition.put("item_class_id", "");
			}
			
			if(orderBy.contains("orna_class_id")){
				condition.put("orna_class_id", "checked");
			} else {
				condition.put("orna_class_id", "");
			}
			
			if(orderBy.contains("orna_code")){
				condition.put("orna_code", "checked");
			} else {
				condition.put("orna_code", "");
			}
			
			if(orderBy.contains("num")){
				condition.put("num", "checked");
			} else {
				condition.put("num", "");
			}
			
			if(orderBy.contains("old_amount")){
				condition.put("old_amount", "checked");
			} else {
				condition.put("old_amount", "");
			}
			
			if(orderBy.contains("new_amount")){
				condition.put("new_amount", "checked");
			} else {
				condition.put("new_amount", "");
			}
			
			if(orderBy.contains("weight")){
				condition.put("weight", "checked");
			} else {
				condition.put("weight", "");
			}
		} else {
			condition.put("orderASC", "checked");
		}
	}
}
