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
import com.jatools.manager.out.ConsumeManager;
import com.jatools.vo.out.Consume;
import com.jatools.vo.out.ConsumeLine;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.cache.VendorCache;
import com.jatools.web.form.out.ConsumForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.view.BaseMultiActionController;
@SuppressWarnings("rawtypes")
public class ConsumeController extends BaseMultiActionController {
	private Logger logger = Logger.getLogger(ConsumeController.class);
	private ConsumeManager consumeManager;
	public void setConsumeManager(ConsumeManager consumeManager) {
		this.consumeManager = consumeManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		ConsumForm form = new com.jatools.web.form.out.ConsumForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req,"status");
			Pager pager = consumeManager.getConsumePageData(condition);
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("out/consume_list","form",form);//转跳页面
	}
	
	/**
	 * 转跳编辑页面
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEditConsum(HttpServletRequest req, HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "billid");
		ConsumForm form = new ConsumForm();
		if(null != id){
			Consume ven = consumeManager.getConsumeById(id);
			List<ConsumeLine> lines = this.consumeManager.getLines(id);
			form.setConsume(ven);
			form.setLines(lines);
			return new ModelAndView("out/consume_edit","form",form);
		}else{
			return new ModelAndView("out/consume_edit","form",form);
		}
	}
	

	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req,"status");
		Pager pager = consumeManager.getConsumePageData(condition);
		ExcelData excelData = new ExcelData();
		excelData.setTitle("维修耗料单");
		excelData.setPager(pager);
		excelData.addColumnType(new String[]{"amount"}, new Class[]{Double.class});
		//添加列标题  
		excelData.setColumnTitle(new String[]{"状态", "单据编号","组织","供应商", "金额","创建时间","创建人","修改时间","修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"status", "billno", "orgId","vendorId", "amount","createdate", "createid","updatedate","updateid"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"createid","updateid","orgId", "vendorId"},
					new CacheSingletonIntf[]{UserCache.getInstance(), UserCache.getInstance(), OrgCache.getInstance(), VendorCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status"}, new String[]{DictConstant.BILL_STATUS});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
}
