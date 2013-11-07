package com.jatools.web.view.move;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.move.MoveReceiveManager;
import com.jatools.vo.move.MoveBillHead;
import com.jatools.vo.move.MoveBillLine;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.move.MoveReceiveForm;
import com.jatools.web.util.DictUtil;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.view.BaseMultiActionController;
import com.opensymphony.oscache.util.StringUtil;

public class MoveReceiveController extends BaseMultiActionController {
	private String sessionBillType = null;
	private MoveReceiveManager moveReceiveManager;

	public void setMoveReceiveManager(MoveReceiveManager moveReceiveManager) {
		this.moveReceiveManager = moveReceiveManager;
	}

	public String getSessionKey(){
		return super.getSessionKey()+this.sessionBillType;
	}
	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		String billType = CommonUtil.getParameterEmpty(req, "billType");
		String jmFlag = CommonUtil.getParameterEmpty(req, "jmFlag");
		String pageUrl = null;
		if(!DictConstant.YES_OR_NO_YES.equals(jmFlag)){
			jmFlag = DictConstant.YES_OR_NO_NO;
			pageUrl = "move/moveReceive_list";
		}else{
			pageUrl = "move/moveReceive_list_jm";
		}
		
		MoveReceiveForm form = new MoveReceiveForm();
		if(StringUtil.isEmpty(billType)){
			form.setPager(new Pager());
			form.setMessage("不能获取单据类型参数");
			return new ModelAndView("move/moveReceive_list", "form", form);
		}
		sessionBillType=billType+jmFlag;
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "showAllFlag");
		form.setBillType(billType);
		form.setJmFlag(jmFlag);
		form.setCondition(condition);
		Pager pager = moveReceiveManager.getMoveReceivePageData(condition, billType, jmFlag, CommonUtil.getSessionOrgId(req));
		form.setPager(pager);
		return new ModelAndView(pageUrl, "form", form);
	}

	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		String headid = CommonUtil.getParameterNull(req, "headid");
		MoveReceiveForm form = new MoveReceiveForm();
		MoveBillHead head = moveReceiveManager.getMoveBillHead(headid);
		form.setBillType(head.getBillType());
		form.setJmFlag(head.getJmFlag());
		form.setHead(head);
		List<MoveBillLine> lineList = moveReceiveManager.getReceivedMoveBillLine(headid);
		form.setLineList(lineList);
		String pageUrl = null;
		if(!DictConstant.YES_OR_NO_YES.equals(head.getJmFlag())){
			pageUrl = "move/moveReceive_edit";
		}else{
			pageUrl = "move/moveReceive_edit_jm";
		}
		return new ModelAndView(pageUrl, "form", form);
	}
	/**
	 * 显示未接收饰品
	 * @return
	 */
	public ModelAndView unreceive(HttpServletRequest req, HttpServletResponse res){
		String headid = CommonUtil.getParameterNull(req, "headid");
		MoveReceiveForm form = new MoveReceiveForm();
		List<MoveBillLine> lineList = moveReceiveManager.getUnreceiveMoveBillLine(headid);
		form.setLineList(lineList);
		return new ModelAndView("move/win/moveReceive_unreceive", "form", form);
	}
	/**
	 * 显示已入库饰品
	 * @return
	 */
	public ModelAndView inivOrna(HttpServletRequest req, HttpServletResponse res){
		String headid = CommonUtil.getParameterNull(req, "headid");
		MoveReceiveForm form = new MoveReceiveForm();
		List<MoveBillLine> lineList = moveReceiveManager.getInivOrnaMoveBillLine(headid);
		form.setLineList(lineList);
		return new ModelAndView("move/win/moveReceive_inivOrna", "form", form);
	}
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		String billType = CommonUtil.getParameterEmpty(req, "billType");
		String jmFlag = CommonUtil.getParameterEmpty(req, "jmFlag");
		sessionBillType=billType+jmFlag;
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "showAllFlag");
		Pager pager = moveReceiveManager.getMoveReceivePageData(condition, billType, jmFlag, CommonUtil.getSessionOrgId(req));
		
		ExcelData excelData = new ExcelData();//excel数据对象
		excelData.setTitle(DictUtil.getValue(DictConstant.MOVE_BILL_TYPE, billType));
//		excelData.setDataList(dataList);//手工重新查找数据
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"outOrgId", "inOrgId", "createDate", "updateDate","receiveDate"}, new Integer[]{4, 4, 3, 3, 4});//宽度默认值为2
		if(DictConstant.MOVE_BILL_TYPE_DIAOBODAN.equals(billType)){
			excelData.setColumnTitle(new String[]{"单据编号", "调拨类型", "来源单据", "来源单据号", "调出组织", "调出仓库", "调入组织", "调入仓库", "装箱单号", "件数合计", "重量合计", "接收合计", "未接收合计", "所有证书合计", "创建时间", "创建人", "修改时间", "修改人", "状态"});
			excelData.setColumnName(new String[]{"billno", "moveType", "srcBillCode", "srcBillNo", "outOrgId", "outStockId", "inOrgId", "inStockId", "packNo", "sumCount", "sumWeight", "receCount", "noreceCount", "certCount", "createDate", "createId", "updateDate", "updateId", "status"});
		}else if(DictConstant.MOVE_BILL_TYPE_TUIHUODAN.equals(billType)){
			excelData.setColumnTitle(new String[]{"单据编号", "调出组织", "调出仓库", "调入组织", "调入仓库", "48小时退货", "装箱单号", "件数合计", "重量合计", "接收合计", "未接收合计", "所有证书合计", "创建时间", "创建人", "修改时间", "修改人", "状态"});
			excelData.setColumnName(new String[]{"billno", "outOrgId", "outStockId", "inOrgId", "inStockId", "backIn48Flag", "packNo", "sumCount", "sumWeight", "receCount", "noreceCount", "certCount", "createDate", "createId", "updateDate", "updateId", "status"});
		}else if(DictConstant.MOVE_BILL_TYPE_TUICANDAN.equals(billType)){			
			excelData.setColumnTitle(new String[]{"单据编号", "调出组织", "调出仓库", "调入组织", "调入仓库", "装箱单号","件数合计", "重量合计", "接收合计", "未接收合计", "所有证书合计", "创建时间", "创建人", "修改时间", "修改人", "状态"});
			excelData.setColumnName(new String[]{"billno", "outOrgId", "outStockId", "inOrgId", "inStockId", "packNo",  "sumCount", "sumWeight", "receCount", "noreceCount", "certCount","createDate", "createId", "updateDate", "updateId", "status"});
		}
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
