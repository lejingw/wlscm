package com.jatools.web.view.move;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.move.MoveCmdManager;
import com.jatools.vo.move.MoveCmdHead;
import com.jatools.vo.move.MoveCmdLine;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.move.MoveCmdForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.view.BaseMultiActionController;
@SuppressWarnings("rawtypes")
public class MoveCmdController extends BaseMultiActionController {
	private MoveCmdManager moveCmdManager;

	public void setMoveCmdManager(MoveCmdManager MoveCmdManager) {
		this.moveCmdManager = MoveCmdManager;
	}

	
	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		String pageUrl = "move/moveCmd_list";
		MoveCmdForm form = new MoveCmdForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "showAllFlag");
		form.setCondition(condition);
		Pager pager = moveCmdManager.getMoveCmdPageData(condition, CommonUtil.getSessionOrgId(req), CommonUtil.getSessionUserId(req));
		form.setPager(pager);
		return new ModelAndView(pageUrl, "form", form);
	}
	
	public ModelAndView selectMoveCmd(HttpServletRequest req, HttpServletResponse res){
		String pageUrl = "move/moveCmd_select";
		String params[] = new String[]{"outStockId", "outOrgId", "inOrgId", "inStockId", "billno", "moveId"};
		MoveCmdForm form = new MoveCmdForm();
		Map<String, String> condition = CommonUtil.getConditionForPage(req, params);
		form.setCondition(condition);
		Pager pager = moveCmdManager.getMoveCmdPageDataByMoveBill(condition);
		form.setPager(pager);
		return new ModelAndView(pageUrl, "form", form);
	}

	public ModelAndView toAdd(HttpServletRequest req, HttpServletResponse res) {
		String pageUrl = "move/moveCmd_edit";
		MoveCmdForm form = new MoveCmdForm();
		return new ModelAndView(pageUrl, "form", form);
	}

	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		String headid = CommonUtil.getParameterNull(req, "headid");
		MoveCmdForm form = new MoveCmdForm();
		MoveCmdHead head = moveCmdManager.getMoveCmdHead(headid);
		form.setHead(head);
		List<MoveCmdLine> lineList = moveCmdManager.getMoveCmdLine(headid);
		form.setLineList(lineList);
		String pageUrl = "move/moveCmd_edit";
		return new ModelAndView(pageUrl, "form", form);
	}
	
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "showAllFlag");
		Pager pager = moveCmdManager.getMoveCmdPageData(condition, CommonUtil.getSessionOrgId(req), CommonUtil.getSessionUserId(req));
		
		ExcelData excelData = new ExcelData();//excel数据对象
		excelData.setTitle("调拨指令单");
//		excelData.setDataList(dataList);//手工重新查找数据
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"outOrgId", "inOrgId", "createDate", "updateDate","receiveDate"}, new Integer[]{4, 4, 3, 3, 4});//宽度默认值为2
	
		excelData.setColumnTitle(new String[]{"单据编号", "业务日期", "调拨类型", "来源单据", "来源单据号", "调出组织", "调出仓库", "调入组织", "调入仓库", "装箱单号", "件数合计", "重量合计", "接收合计", "未接收合计", "所有证书合计", "创建时间", "创建人", "修改时间", "修改人", "状态"});
		excelData.setColumnName(new String[]{"billno", "dodate", "moveType", "srcBillCode", "srcBillNo", "outOrgId", "outStockId", "inOrgId", "inStockId", "packNo", "sumCount", "sumWeight", "receCount", "noreceCount", "certCount", "createDate", "createId", "updateDate", "updateId", "status"});
		
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"outOrgId", "inOrgId", "createId", "updateId", "receiveEmpId"},
					new CacheSingletonIntf[]{OrgCache.getInstance(), OrgCache.getInstance(), UserCache.getInstance(), UserCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"moveType", "srcBillCode", "outStockId", "inStockId", "status", "outGroup",  "inGroup", "backIn48Flag"},
					new String[]{DictConstant.MOVE_TYPE, DictConstant.BILL_CODE, DictConstant.INVCODE, DictConstant.INVCODE, DictConstant.BILL_STATUS, DictConstant.GROUPS, DictConstant.GROUPS, DictConstant.YES_OR_NO});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);
		util.export(res);
		return null;
	}
}