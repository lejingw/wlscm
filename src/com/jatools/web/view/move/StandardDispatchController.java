package com.jatools.web.view.move;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.move.StandardDispatchManager;
import com.jatools.manager.util.ExcelUtilManager;
import com.jatools.vo.move.StandardDispatch;
import com.jatools.vo.move.StandardDispatchOrg;
import com.jatools.vo.move.StandardDispatchOrna;
import com.jatools.vo.stock.DispatchCondition;
import com.jatools.vo.stock.DispatchOrnaLog;
import com.jatools.vo.stock.DispatchTemp;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.StyleItemClassCache;
import com.jatools.web.cache.StyleMiddleClassCache;
import com.jatools.web.cache.StyleOrnaClassCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.BaseForm;
import com.jatools.web.form.move.StandardDispatchForm;
import com.jatools.web.form.stock.DispatchOrnaForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class StandardDispatchController extends BaseMultiActionController {
	private StandardDispatchManager standardDispatchManager;
	private ExcelUtilManager excelUtilManager;
	private byte[] lock = new byte[0];
	public void setExcelUtilManager(ExcelUtilManager excelUtilManager) {
		this.excelUtilManager = excelUtilManager;
	}

	public void setStandardDispatchManager(
			StandardDispatchManager standardDispatchManager) {
		this.standardDispatchManager = standardDispatchManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		BaseForm form = new BaseForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = standardDispatchManager.getStandardDispatchPageData(condition);
		form.setPager(pager);
		return new ModelAndView("move/standardDispatch_list", "form", form);
	}

	/**
	 * 选择配货参数
	 */
	public ModelAndView condition(HttpServletRequest req, HttpServletResponse res) {
		String gheadid = CommonUtil.getParameterEmpty(req, "gheadid");
		// 获取配货参数
		List<DispatchCondition> list = standardDispatchManager.getDispatchCondition(gheadid);
		StandardDispatchForm form = new StandardDispatchForm();
		form.setGheadid(gheadid);
		form.setList(list);
		return new ModelAndView("stock/win/dispatchOrna_condition", "form", form);
	}
	/**
	 * 进入订单配货页面
	 */
	public ModelAndView dispatch(HttpServletRequest req, HttpServletResponse res) {
		String gheadid = CommonUtil.getParameterEmpty(req, "gheadid");
		String conditionId = CommonUtil.getParameterEmpty(req, "conditionId");
		StandardDispatchForm form = new StandardDispatchForm();
		form.setGheadid(gheadid);
		form.setConditionId(conditionId);
		if(!"-1".equals(gheadid)){
			StandardDispatch ghead = standardDispatchManager.getStandardDispatch(gheadid);
			form.setGhead(ghead);
		}
		if(!"-1".equals(conditionId)){
			DispatchCondition disCondition = standardDispatchManager.getDispatchConditionById(conditionId);
			form.setDisCondition(disCondition);
		}
		return new ModelAndView("move/standardDispatch", "form", form);
	}
	public ModelAndView view(HttpServletRequest req, HttpServletResponse res) {
		String gheadid = CommonUtil.getParameterEmpty(req, "gheadid");
		Map<String, String> condition = CommonUtil.getConditionForPage2(req, "itemClassId", "ornaClassId", "analysisId", "styleItemClassId", "styleMiddleClassId", "styleOrnaClassId", "styleId");
		StandardDispatchForm form = new StandardDispatchForm();
		StandardDispatch ghead = standardDispatchManager.getStandardDispatch(gheadid);
		form.setGhead(ghead);
		Pager pager = standardDispatchManager.getShowDispatchedPageData(gheadid, condition);
		form.setGheadid(gheadid);
		form.setPager(pager);
		form.setCondition(condition);
		return new ModelAndView("move/standardDispatch_view", "form", form);
	}
	public ModelAndView showDispatched(HttpServletRequest req, HttpServletResponse res) {
		String gheadid = CommonUtil.getParameterEmpty(req, "gheadid");
		Map<String, String> condition = CommonUtil.getConditionForPage2(req, "conditionId");
		StandardDispatchForm form = new StandardDispatchForm();
		Pager pager = standardDispatchManager.getShowDispatchedPageData(gheadid, condition);
		form.setGheadid(gheadid);
		form.setPager(pager);
		form.setCondition(condition);
		return new ModelAndView("move/standardDispatch_orna", "form", form);
	}
	public ModelAndView dispatching(HttpServletRequest req, HttpServletResponse res) {
		String gheadid = CommonUtil.getParameterEmpty(req, "gheadid");
		String ornaCode = CommonUtil.getParameterEmpty(req, "ornaCode");
		if(StringUtil.isEmpty(gheadid) || StringUtil.isEmpty(ornaCode))
			return new ModelAndView("move/standardDispatch_org", "form", null);
		String userid = CommonUtil.getSessionUserId(req);
		StandardDispatchForm form = new StandardDispatchForm();
		form.setGheadid(gheadid);
		form.setOrnaCode(ornaCode);
        try {
            synchronized (lock) {
                //没有配货记录，则进行配货
                Map<String, String> condition = CommonUtil.getConditionForPage2(req);
                Pager pager = standardDispatchManager.getMatchDispatchOrgPageData(ornaCode, condition);
                form.setPager(pager);
                DispatchOrnaLog dispatchOrnaLog = standardDispatchManager.getDispatchOrnaLogByOrnaCode(ornaCode);//取状态为正配货1、已配货2、已调拨3的
                if(null == dispatchOrnaLog){
                    String dispatchOrgId = CommonUtil.getParameterEmpty(req, "dispatchOrgId");
                    if(StringUtil.isEmpty(dispatchOrgId)){
                        List<StandardDispatchOrg> list = pager.getPageData();
                        if(null != list && list.size()>0){
                            Iterator<StandardDispatchOrg> iter = list.iterator();
                            boolean dispatchFlag = false;
                            while(iter.hasNext()){
                                StandardDispatchOrg org = iter.next();
                                if(!"0".equals(org.getDispatchFlag())){
                                    org.setDispatchNum(""+(Integer.parseInt(org.getDispatchNum())+1));
                                    form.setDispatchOrgId(org.getOrgId());
                                    form.setDispatchModel("2");// 1已配货、2配货成功、3配货未成功
                                    standardDispatchManager.saveDispatchOrnaLog(gheadid, ornaCode, org.getOrgId(), org.getDispatchFlag(), userid);
                                    dispatchFlag = true;
                                    break;
                                }
                            }
                            if(!dispatchFlag){
                                form.setDispatchOrgId("-1");
                                form.setDispatchModel("3");// 1已配货、2配货成功、3配货未成功
                            }
                        } else {
                            form.setDispatchOrgId("-1");
                            form.setDispatchModel("3");// 1已配货、2配货成功、3配货未成功
                        }
                    }else{
                        form.setDispatchOrgId(dispatchOrgId);
                        form.setDispatchModel("-1".equals(dispatchOrgId)?"3":"2");// 1已配货、2配货成功、3配货未成功
                    }
                }else{
                    form.setDispatchOrgId(dispatchOrnaLog.getOrgId());
                    form.setDispatchModel("1");// 1已配货、2配货成功、3配货未成功
                }
            }
        }catch (Exception e) {
            form.setDispatchOrgId("-1");
            form.setDispatchModel("4");
        }
		return new ModelAndView("move/standardDispatch_org", "form", form);
	}

	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = standardDispatchManager.getStandardDispatchPageData(condition);
		
		ExcelData excelData = new ExcelData();//excel数据对象
		excelData.setTitle("标准配货");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.setColumnTitle(new String[]{"单据编号", "是否锁定", "锁定人员", "创建人", "创建时间", "修改人", "修改时间", "状态"});
		excelData.setColumnName(new String[]{"billno", "lockFlag", "lockUserIds", "createId", "createDate", "updateId", "updateDate", "status"});
		//设置对应的缓存数据
		excelData.addSingletonDisplayColumns(
				new String[] { "lockUserIds", "createId", "updateId" },
				new CacheSingletonIntf[] { UserCache.getInstance(), UserCache.getInstance(), UserCache.getInstance() });
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"lockFlag", "status"}, new String[]{DictConstant.YES_OR_NO, DictConstant.BILL_STATUS});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);
		util.export(res);
		return null;
	}
}
