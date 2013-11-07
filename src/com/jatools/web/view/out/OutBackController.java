package com.jatools.web.view.out;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.out.OutBackManager;
import com.jatools.vo.out.OutBackHead;
import com.jatools.vo.out.OutBackLine;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.UnitCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.cache.VendorCache;
import com.jatools.web.form.out.OutBackForm;
import com.jatools.web.form.out.OutVendorForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.view.BaseMultiActionController;

public class OutBackController extends BaseMultiActionController {
	private Logger logger = Logger.getLogger(this.getClass());
	private OutBackManager outBackManager;

	public void setOutBackManager(OutBackManager outBackManager) {
		this.outBackManager = outBackManager;
	}


	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {return null;}
	
	private ModelAndView toList(HttpServletRequest req, String url){
		OutBackForm form = new OutBackForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req,"billno","status","stockId","startDate","endDate","showAll");
		condition.put("userId",CommonUtil.getSessionUserId(req));//加载当前用户的数据
		Pager pager = outBackManager.getBackHeadPage(condition);
		form.setPager(pager);
		form.setShowAll(condition.get("showAll"));
		form.setCondition(condition);
		return new ModelAndView(url, "form", form);
		
		
	}
	private ModelAndView toAdd(HttpServletRequest req,String url){
		OutBackForm form = new OutBackForm();
		form.getHead().setOrgId(CommonUtil.getSessionOrgId(req));
//		form.getHead().setMaterialType(materialType);
		form.getHead().setStatus("1");
		return new ModelAndView(url, "form", form);
	}
	private ModelAndView toEdit(HttpServletRequest req,String url,String cancelUrl){
		OutBackForm form = new OutBackForm();
		String headId = CommonUtil.getParameterNull(req, "headid");
		if(null != headId){
			try {
				OutBackHead head =outBackManager.getBackHead(headId);
				List<OutBackLine> list =outBackManager.getBackLines(headId);
				form.setHead(head);
				form.setList(list);
			} catch (Exception e) {
				logger.error(e);
				form.setSuccessfulFlag(false);
				form.setMessage("获取单据数据失败");
				return new ModelAndView(cancelUrl, "form", form);
			}
		}
		return new ModelAndView(url, "form", form);
	}
	/**
	 * 跳转到归还饰品页面
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toBackInfo(HttpServletRequest req, HttpServletResponse res){
		OutBackForm form = new OutBackForm();
		String headId = CommonUtil.getParameterNull(req, "headid");
		if(null != headId){
			try {
				OutBackHead head =outBackManager.getBackHead(headId);
				form.setHead(head);
			}catch (Exception e) {
				logger.error(e);
				form.setSuccessfulFlag(false);
				form.setMessage("获取单据数据失败");
			}
		}
		return new ModelAndView("out/backInfo", "form", form);
	}
	///列表 
	public ModelAndView toOutBackList(HttpServletRequest req, HttpServletResponse res) {
		return toList(req, "out/outBack_list");
	}
	/////新增页面
	public ModelAndView toAddOutBack(HttpServletRequest req, HttpServletResponse res) {
		return toAdd(req,"out/outBack_edit");
	}
	
	//修改页面
	public ModelAndView toEditOutBack(HttpServletRequest req, HttpServletResponse res) {
		return toEdit(req, "out/outBack_edit", "out/outBack_list");
	}

	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req,"billno","status","stockId","startDate","endDate","showAll");
		condition.put("userId",CommonUtil.getSessionUserId(req));//加载当前用户的数据
		Pager pager = outBackManager.getBackHeadPage(condition);
		return baseExportExcel(req, res, "借出还回单", pager);
	}
	
	public static ModelAndView baseExportExcel(HttpServletRequest req, HttpServletResponse res,String title,Pager pager) {
		//----------------------------------------------------------导出excel开始---------------------------------------
        String doType=req.getParameter("dotype");
		//excel数据对象
	 	ExcelData excelData = new ExcelData();	
		excelData.setTitle(title);
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"vendorId","orgId","createDate", "updateDate"}, new Integer[]{4 , 4, 3, 3});//宽度默认值为2
		//添加列标题
        String columnTitles[] = new String[]{"总单编号", "供应商", "库存组织","仓库","状态", "单据日期","总件数", "总重量", "总粒数", "总金额","归还数量","创建时间", "创建人"};
        if("0".equals(doType) || "2".equals(doType)){
            columnTitles = new String[]{"总单编号", "供应商", "库存组织","仓库","状态", "单据日期","总件数", "总重量", "总粒数", "总金额", "归还数量", "是否结算", "是否出票", "创建时间", "创建人"};
        }
		excelData.setColumnTitle(columnTitles);//,,"件数合计", "重量合计", "粒数合计", "金额合计" "修改时间", "修改人", "状态"
		//添加列name
        String columnNames[] = new String[]{"billNo","vendorId", "orgId","stockId", "status", "doDate", "sumCount", "sumWeight", "sumGrains", "saleMoney", "backSum","createDate", "createId"};
        if("0".equals(doType) || "2".equals(doType)){
            columnNames = new String[]{"billNo","vendorId", "orgId","stockId", "status", "doDate", "sumCount", "sumWeight", "sumGrains", "saleMoney", "backSum", "isCheck", "isBill", "createDate", "createId"};
        }
		excelData.setColumnName(columnNames);//, "updateDate", "updateId", "status"
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"vendorId","orgId", "createId"},
					new CacheSingletonIntf[]{VendorCache.getInstance(),OrgCache.getInstance(),UserCache.getInstance()});
//		//设置对应的数据字典
//		excelData.addDictDisplayColumns(new String[]{"moveType", "srcBillCode", "outStockId", "inStockId", "status"},
//					new String[]{DictConstant.MOVE_TYPE, DictConstant.BILL_CODE, DictConstant.INVCODE, DictConstant.INVCODE, DictConstant.BILL_STATUS});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status","stockId", "isCheck", "isBill"},new String[]{DictConstant.BILL_STATUS, DictConstant.INVCODE, DictConstant.YES_OR_NO, DictConstant.YES_OR_NO});
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;
	}
	
	public ModelAndView baseExportExcelLine(HttpServletRequest req, HttpServletResponse res) {
		OutVendorForm form = new OutVendorForm();
		String headId = CommonUtil.getParameterNull(req, "headId");
		
		Pager pager = new Pager();
		pager.setPageData((List)outBackManager.getBackLines(headId));
		form.setPager(pager);
		
		ExcelData excelData = new ExcelData();
		excelData.setTitle("借出还回单");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{}, new Integer[]{});//宽度默认值为2
			
		//添加列标题
		excelData.setColumnTitle(new String[]{"大类", "小类", "款式", "分析范围","饰品编码","饰品名称",
				"计量单位", "现有量", "总重量", "粒数", "销售金额",  "主石重量","配石重量","归还时间","归还说明"});
		//添加列name
		excelData.setColumnName(new String[]{"itemClassId", "ornaClassId", "styleName","alaysisCode","ornaCode",
				"ornaDsc", "unitId", "nowQty", "allQty", "grains", "posMoney", "mainWeight", "partWeight","backDate","backBody"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"itemClassId","ornaClassId","unitId"},
					new CacheSingletonIntf[]{ItemClassCache.getInstance(),OrnaClassCache.getInstance(), UnitCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{},
					new String[]{});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
}
