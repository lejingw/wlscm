package com.jatools.web.view.pur;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.PurConstant;
import com.jatools.manager.pur.PurGatherManager;
import com.jatools.vo.pur.PurGatherHead;
import com.jatools.vo.pur.PurGatherLine;
import com.jatools.vo.pur.PurchaseLineSession;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.ArticleTypeCache;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.pur.PurGatherForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class PurGatherController extends BaseMultiActionController {
	private Logger logger = Logger.getLogger(PurGatherController.class);
	private PurGatherManager purGatherManager;
	private String listKey = null;
	
	public void setPurGatherManager(
			PurGatherManager purGatherManager) {
		this.purGatherManager = purGatherManager;
	}

	public String getSessionKey(){
		return super.getSessionKey() + listKey;
	}
	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		listKey = "1";
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = purGatherManager.getPurGatherPageData(condition);
		PurGatherForm form = new PurGatherForm();
		form.setPager(pager);
		return new ModelAndView("pur/purGather_list", "form", form);
	}
	
	public ModelAndView list2(HttpServletRequest req, HttpServletResponse res) {
		listKey = "2";
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = purGatherManager.getPurGatherPageData(condition);
		PurGatherForm form = new PurGatherForm();
		form.setPager(pager);
		return new ModelAndView("pur/purGather_list2", "form", form);
	}
	/**
	 * 查看采购总单
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toView(HttpServletRequest req, HttpServletResponse res) {
		String headid = CommonUtil.getParameterNull(req, "headid");
		if(StringUtil.isEmpty(headid)){
			if(StringUtil.isEmpty(headid)){
				logger.error("不能获取采购单id");
				return new ModelAndView(new RedirectView("pur/purGather_list"));
			}
		}
		PurGatherForm form = new PurGatherForm();
		PurGatherHead head = purGatherManager.getPurGatherHead(headid);
		form.setHead(head);
		Map<String, String> condition = CommonUtil.getConditionForPage(req, "reviewflag", "headid","itemClassId", "ornaClassId", "analysisId", "styleItemClassId", "styleMiddleClassId", "styleOrnaClassId", "styleId");
		Pager pager = purGatherManager.getPurGatherLinePageData(headid, condition);
		form.setPager(pager);
		form.setCondition(condition);
		return new ModelAndView("pur/purGather_view", "form", form);
	}
	/**
	 * 查看采购总单
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toView2(HttpServletRequest req, HttpServletResponse res) {
		String headid = CommonUtil.getParameterNull(req, "headid");
		if(StringUtil.isEmpty(headid)){
			if(StringUtil.isEmpty(headid)){
				logger.error("不能获取采购单id");
				return new ModelAndView(new RedirectView("pur/purGather_list2"));
			}
		}
		PurGatherForm form = new PurGatherForm();
		PurGatherHead head = purGatherManager.getPurGatherHead(headid);
		form.setHead(head);
		Map<String, String> condition = CommonUtil.getConditionForPage(req, "itemClassId", "ornaClassId", "analysisId", "styleItemClassId", "styleMiddleClassId", "styleOrnaClassId", "styleId", "dispatchOverFlag");
		Pager pager = purGatherManager.getPurGatherLinePageData(headid, condition);
		form.setPager(pager);
		form.setCondition(condition);
		return new ModelAndView("pur/purGather_view2", "form", form);
	}
	/**
	 * 生成采购单
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView createPurchase(HttpServletRequest req, HttpServletResponse res) {
		String headid = CommonUtil.getParameterNull(req, "headid");
		if(StringUtil.isEmpty(headid)){
			if(StringUtil.isEmpty(headid)){
				logger.error("不能获取采购单id");
				return new ModelAndView(new RedirectView("pur/purGather_list2"));
			}
		}
		PurGatherForm form = new PurGatherForm();
		PurGatherHead head = purGatherManager.getPurGatherHead(headid);
		form.setHead(head);
		Map<String, String> condition = CommonUtil.getConditionForPage2(req, "itemClassId", "ornaClassId", "analysisId", "styleItemClassId", "styleMiddleClassId", "styleOrnaClassId", "styleId", "dispatchOverFlag", "vendorId", "dodate", "purEmpids", "bizType", "memo");
		if(StringUtil.isEmpty(condition.get("dispatchOverFlag"))){
			condition.put("dispatchOverFlag", "1");
			req.getSession().removeAttribute(PurConstant.NUM_CURRENT_DISPATCH_SESSION_KEY);
		}
		Pager pager = purGatherManager.getPurGatherLinePageData(headid, condition);
		setNumCurrentDispatchFromSession(pager, req.getSession());
		form.setPager(pager);
		form.setCondition(condition);
		return new ModelAndView("pur/purGather_createPurchase", "form", form);
	}

	private void setNumCurrentDispatchFromSession(Pager pager, HttpSession session) {
		Object obj = session.getAttribute(PurConstant.NUM_CURRENT_DISPATCH_SESSION_KEY);
		if(null != obj && obj instanceof List){
			List<PurchaseLineSession> set = (List<PurchaseLineSession>) obj;
			List<Object> list = pager.getPageData();
			for(Object data : list){
				PurGatherLine line = (PurGatherLine)data;
				for(PurchaseLineSession pls : set){
					if(line.getLineid().equals(pls.getGlineid())){
						line.setNumCurrentDispatch(pls.getNumCurrentDispatch());
					}
				}
			}
		}
	}

	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		listKey = "1".equals(CommonUtil.getParameterEmpty(req, "exportExcelFlag"))?"1":"2";
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "billno", "regionId", "cycleTypeId", "orderType", "orderKind", "status");
		Pager pager = purGatherManager.getPurGatherPageData(condition);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("采购总单");
//		excelData.setDataList(dataList);//手工重新查找数据
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"createDate"}, new Integer[]{4});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"总单编号", "区域", "周期类型", "商品类别", "购物类型", "要货分类", "是否保留", "采购总数",  "创建人", "创建时间", "状态"});
		//添加列name
		excelData.setColumnName(new String[]{"billno", "regionName", "cycleTypeName", "articleTypeIds", "orderType", "orderKind", "usedFlag", "sumNum", "createId", "createDate", "status"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"createId", "articleTypeIds"}, new CacheSingletonIntf[]{UserCache.getInstance(), ArticleTypeCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"orderType", "orderKind", "status", "usedFlag"}, new String[]{DictConstant.ORDER_TYPE, DictConstant.ORDER_KIND, DictConstant.BILL_STATUS, DictConstant.YES_OR_NO});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;
	}
}
