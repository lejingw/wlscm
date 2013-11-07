package com.jatools.web.view.move;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.move.MovePackManager;
import com.jatools.vo.move.MovePackHead;
import com.jatools.vo.move.MovePackLine;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.move.MovePackForm;
import com.jatools.web.util.DictUtil;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.view.BaseMultiActionController;
import com.opensymphony.oscache.util.StringUtil;

public class MovePackController extends BaseMultiActionController {
	private String sessionBillType = null;
	private MovePackManager movePackManager;

	public void setMovePackManager(MovePackManager movePackManager) {
		this.movePackManager = movePackManager;
	}

	public String getSessionKey(){
		return super.getSessionKey()+this.sessionBillType;
	}
	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		String billType = CommonUtil.getParameterEmpty(req, "billType");
		String jmFlag = CommonUtil.getParameterEmpty(req, "jmFlag");
		if(!DictConstant.YES_OR_NO_YES.equals(jmFlag)){
			jmFlag = "0";
		}
		MovePackForm form = new MovePackForm();
		if(StringUtil.isEmpty(billType)){
			form.setPager(new Pager());
			form.setMessage("不能获取单据类型参数");
			return new ModelAndView("move/movePack_list", "form", form);
		}
		sessionBillType=billType+jmFlag;
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "showAllFlag");
		form.setBillType(billType);
		form.setJmFlag(jmFlag);
		form.setCondition(condition);
		Pager pager = movePackManager.getMovePackPageData(condition, billType, jmFlag, CommonUtil.getSessionOrgId(req), CommonUtil.getSessionUserId(req));
		form.setPager(pager);
		String url = null;
		if(DictConstant.YES_OR_NO_YES.equals(jmFlag)){
			url = "move/movePack_list_jm";
		}else{
			url = "move/movePack_list";
		}
		return new ModelAndView(url, "form", form);
	}
	
	public ModelAndView toAdd(HttpServletRequest req, HttpServletResponse res) {
		String billType = CommonUtil.getParameterNull(req, "billType");
		String jmFlag = CommonUtil.getParameterEmpty(req, "jmFlag");
		if(!DictConstant.YES_OR_NO_YES.equals(jmFlag)){
			jmFlag = "0";
		}
		MovePackForm form = new MovePackForm();
		form.setBillType(billType);
		form.setJmFlag(jmFlag);
		String url = null;
		if(DictConstant.YES_OR_NO_YES.equals(jmFlag)){
			url = "move/movePack_edit_jm";
		}else{
			url = "move/movePack_edit";
		}
		return new ModelAndView(url, "form", form);
	}

	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		String headid = CommonUtil.getParameterNull(req, "headid");
		MovePackForm form = new MovePackForm();
		MovePackHead head = movePackManager.getMovePackHead(headid);
		form.setBillType(head.getBillType());
		form.setJmFlag(head.getJmFlag());
		form.setHead(head);
		List<MovePackLine> lineList = movePackManager.getMovePackLine(headid);
		form.setLineList(lineList);
		String url = null;
		if(DictConstant.YES_OR_NO_YES.equals(head.getJmFlag())){
			url = "move/movePack_edit_jm";
		}else{
			url = "move/movePack_edit";
		}
		return new ModelAndView(url, "form", form);
	}
	
	public ModelAndView updateExpress(HttpServletRequest req, HttpServletResponse res) {
		String headid = CommonUtil.getParameterNull(req, "headid");
		MovePackForm form = new MovePackForm();
		MovePackHead head = movePackManager.getMovePackHead(headid);
		form.setHead(head);
		return new ModelAndView("move/win/movePack_updateExpress", "form", form);
	}

	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		String billType = CommonUtil.getParameterEmpty(req, "billType");
		String jmFlag = CommonUtil.getParameterEmpty(req, "jmFlag");
		if(!DictConstant.YES_OR_NO_YES.equals(jmFlag)){
			jmFlag = "0";
		}
		sessionBillType=billType + jmFlag;
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "showAllFlag");
		Pager pager = movePackManager.getMovePackPageData(condition, billType, jmFlag, CommonUtil.getSessionOrgId(req), CommonUtil.getSessionUserId(req));
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle(DictUtil.getValue(DictConstant.MOVE_PACK_BILL_TYPE, billType));
//		excelData.setDataList(dataList);//手工重新查找数据
		excelData.setPager(pager);//直接利用分页pager对象
		if(DictConstant.YES_OR_NO_YES.equals(jmFlag)){
			excelData.addColumnWidth(new String[]{"receiveDate", "createDate", "updateDate", "estimateNos"}, new Integer[]{4, 4, 4, 8});//宽度默认值为2
			//添加列标题
			excelData.setColumnTitle(new String[]{"单据编号", "发运方式", "快递单号", "快递费合计", "销售结算单号", "调出组织", "调入组织", "总件数", "预计到货日期", "包裹毛重", "接收人", "接收时间", "结算单号", "创建时间", "创建人", "修改时间", "修改人", "状态"});
			//添加列name
			excelData.setColumnName(new String[]{"billno", "expressMode", "expressNo", "expressMoney", "saleEstimateNo", "outOrgId", "inOrgId", "sumCount", "planEndDate", "grossWeight", "receiveEmpId", "receiveDate", "estimateNos", "createDate", "createId", "updateDate", "updateId", "status"});
		}else{
			excelData.addColumnWidth(new String[]{"receiveDate", "createDate", "updateDate"}, new Integer[]{4, 4, 4});//宽度默认值为2
			//添加列标题
			excelData.setColumnTitle(new String[]{"单据编号", "发运方式", "快递单号", "调出组织", "调入组织", "总件数", "预计到货日期", "包裹毛重", "接收人", "接收时间", "创建时间", "创建人", "修改时间", "修改人", "状态"});
			//添加列name
			excelData.setColumnName(new String[]{"billno", "expressMode", "expressNo", "outOrgId", "inOrgId", "sumCount", "planEndDate", "grossWeight", "receiveEmpId", "receiveDate", "createDate", "createId", "updateDate", "updateId", "status"});
		}
		excelData.addColumnType(new String[]{"expressNo"}, new Class[]{String.class});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"outOrgId", "inOrgId", "receiveEmpId", "createId", "updateId"},
					new CacheSingletonIntf[]{OrgCache.getInstance(), OrgCache.getInstance(), UserCache.getInstance(), UserCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"expressMode", "status"},
					new String[]{DictConstant.MOVE_PACK_EXPRESS_MODE, DictConstant.BILL_STATUS});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;
	}
	
	public ModelAndView printbill(HttpServletRequest req, HttpServletResponse res) {
		String headid = CommonUtil.getParameterNull(req, "headid");
		MovePackForm form = new MovePackForm();
		MovePackHead head = movePackManager.getMovePackHead(headid);
		form.setBillType(head.getBillType());
		form.setJmFlag(head.getJmFlag());
		form.setHead(head);
		List<MovePackLine> lineList = movePackManager.getMovePackLine(headid);
		form.setLineList(lineList);
		String url = "move/win/movePack_print";
		return new ModelAndView(url, "form", form);
	}
}
