package com.jatools.web.view.stock;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.manager.stock.OutVendorManager;
import com.jatools.web.form.stock.OutVendorHeadForm;
import com.jatools.web.view.BaseMultiActionController;
/**
 *发料单
 * @author ren.ming
 * <br>
 * Created 2011-11-30
 */
public class ProcExitConditionController extends BaseMultiActionController {
	private Logger logger = Logger.getLogger(ProcExitConditionController.class);

	private OutVendorManager outVendorBillManager;
	
	public void setOutVendorBillManager(OutVendorManager outVendorManager) {
		this.outVendorBillManager = outVendorManager;
	}


	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		OutVendorHeadForm form = new OutVendorHeadForm();
		try {
			String params[] = {"orgId", "vendorId", "billno", "orna_code", "material_type", "item_class_id"};
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, params);
			Pager pager = outVendorBillManager.getOutVendorData(condition);
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("stock/win/procexit_condition", "form", form);
	}
	
}
