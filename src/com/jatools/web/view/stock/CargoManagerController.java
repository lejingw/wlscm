package com.jatools.web.view.stock;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.manager.bd.BdCommonManager;
import com.jatools.manager.stock.CargoManager;
import com.jatools.vo.stock.PackageMaterActive;
import com.jatools.web.form.stock.OutVendorHeadForm;
import com.jatools.web.util.BdCommon;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;
/**
 * 原料货位维护
 * @author ren.ming
 * <br>
 * Created 2011-12-2
 */
public class CargoManagerController extends BaseMultiActionController {
	private Logger logger = Logger.getLogger(CargoManagerController.class);

	private CargoManager cargoManager;
	private BdCommonManager bdCommonManager;
	
	//private String[] paramsAll = {"orgId", "itemClassId", "ornaClassId", "analysisId", "styleItemClass", "styleMiddleClass", "styleOrnaClass", "styleId", "isCode", "ornaCode", "ornaBarCode", "isMaterial"};
	private String[] params1 = {"orgId", "itemClassId", "ornaClassId", "analysisId", "styleItemClass", "styleMiddleClass", "styleOrnaClass", "styleId", "isCode", "isMaterial"};
	//private String[] params2 = {"orgId", "ornaCode", "ornaBarCode", "isCode"};
	
	public void setCargoManager(CargoManager cargoManager) {
		this.cargoManager = cargoManager;
	}
	public void setBdCommonManager(BdCommonManager bdCommonManager) {
		this.bdCommonManager = bdCommonManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		OutVendorHeadForm form = new OutVendorHeadForm();
		try {
			String orgId = CommonUtil.getSessionOrgId(req) ;
			String isCode = CommonUtil.getParameterEmpty(req, "isCode");
			Pager pager = new Pager();
			Map<String, String> condition = CommonUtil.getConditionForPage(req, params1);
			condition.put("orgId",orgId);
			if(StringUtil.isNotBlank(isCode) && !"1".equals(isCode)){
				pager = cargoManager.getCargoMaterPager(condition);
				dealMaterActive(pager);
			}
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("stock/cargoManager", "form", form);
	}
	
	/**
	 * 金额和重量的小数处理以及一些id转化为名称
	 * @param packageMA
	 * @return
	 */
	private void dealMaterActive(Pager pager) {
		if(null != pager && !CollectionUtils.isEmpty(pager.getPageData())) {
			for(Object obj : pager.getPageData()) {
				PackageMaterActive packageMA = (PackageMaterActive)obj;
				if(packageMA == null) return ;
				
				if(StringUtil.isNotBlank(packageMA.getMainWeight())) {
					packageMA.setMainWeight(Double.valueOf(packageMA.getMainWeight())+"");
				} else {
					packageMA.setMainWeight("0");
				}
				if(StringUtil.isNotBlank(packageMA.getPartWeight())) {
					packageMA.setPartWeight(Double.valueOf(packageMA.getPartWeight())+"");
				} else {
					packageMA.setPartWeight("0");
				}
				if(StringUtil.isNotBlank(packageMA.getAllQty())) {
					packageMA.setAllQty(Double.valueOf(packageMA.getAllQty())+"");
				}else {
					packageMA.setAllQty("0");
				}
				if(StringUtil.isNotBlank(packageMA.getPosAmount())) {
					packageMA.setPosAmount(Double.valueOf(packageMA.getPosAmount())+"");
				}else {
					packageMA.setPosAmount("0");
				}
				if(StringUtil.isNotBlank(packageMA.getStoneNowNum())) {
					packageMA.setStoneNowNum(Double.valueOf(packageMA.getStoneNowNum())+"");
				}else {
					packageMA.setStoneNowNum("0");
				}
				
				String itemClassName = BdCommon.getItemClassDesc(packageMA.getItemClassId());
				packageMA.setItemClassName(itemClassName);
				String ornaClassName = BdCommon.getOrnaClassDesc(packageMA.getOrnaClassId());
				packageMA.setOrnaClassName(ornaClassName);
				String styleName = this.bdCommonManager.getStyleNameById(packageMA.getStyleId());
				packageMA.setStyleName(styleName);
				String analysisName = this.bdCommonManager.getAnalysisNameById(packageMA.getAlaysisId());
				packageMA.setAlaysisName(analysisName);
				String saleUnitName = BdCommon.getUnitName(packageMA.getSaleUnitId());
				packageMA.setSaleUnitName(saleUnitName);
			}
		}
	}
}
