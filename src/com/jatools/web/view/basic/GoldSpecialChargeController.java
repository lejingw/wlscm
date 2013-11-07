package com.jatools.web.view.basic;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.basic.ChargeManager;
import com.jatools.manager.basic.GoldSpecialChargeManager;
import com.jatools.vo.basic.Charge;
import com.jatools.vo.basic.GoldSpecialCharge;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.basic.ChargeForm;
import com.jatools.web.form.basic.GoldSpecialChargeForm;
import com.jatools.web.form.basic.OrgGroupForm;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class GoldSpecialChargeController extends BaseMultiActionController {
	private Logger logger = Logger.getLogger(GoldSpecialChargeController.class);

	private GoldSpecialChargeManager goldSpecialChargeManager;

    public void setGoldSpecialChargeManager(GoldSpecialChargeManager goldSpecialChargeManager) {
        this.goldSpecialChargeManager = goldSpecialChargeManager;
    }

    @Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		GoldSpecialChargeForm form = new GoldSpecialChargeForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "weightStart", "weightEnd");
			Pager pager = goldSpecialChargeManager.getGoldSpecialChargePageData(condition);
			condition.put("orgId", CommonUtil.getSessionOrgId(req));
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("basic/gold_special_charge_list", "form", form);
	}
	/**
	 * 页面跳转
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		String billid = CommonUtil.getParameterNull(req, "billid");
        GoldSpecialChargeForm form = new GoldSpecialChargeForm();
		//如果带有groupId参数，则为编辑页面
		if(StringUtil.isNotBlank(billid)){
            GoldSpecialCharge charge = goldSpecialChargeManager.getGoldSpecialCharge(billid);
			form.setGoldSpecialCharge(charge);
			return new ModelAndView("basic/gold_special_charge_edit","form",form);
		}else{
			return new ModelAndView("basic/gold_special_charge_edit","form",form);
		}
	}
	
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "weightStart", "weightEnd");
		condition.put("orgId", CommonUtil.getSessionOrgId(req));
		Pager pager = goldSpecialChargeManager.getGoldSpecialChargePageData(condition);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("素金小克重特殊工费标准");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"createDate"}, new Integer[]{3});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"大类", "小类", "重量始", "重量止", "工费单价", "工费类型", "创建时间", "创建人", "修改时间", "修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"itemClassId", "ornaClassId", "weightStart", "weightEnd", "workPrice", "workType", "createDate", "createId", "updateDate", "updateId"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"itemClassId", "ornaClassId", "createId", "updateId"},
                new CacheSingletonIntf[]{ItemClassCache.getInstance(), OrnaClassCache.getInstance(), UserCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"workType"},
                new String[]{DictConstant.GOLD_SPECIAL_WORD_TYPE});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
}
