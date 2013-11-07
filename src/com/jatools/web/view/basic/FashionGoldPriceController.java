package com.jatools.web.view.basic;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.basic.ChargeManager;
import com.jatools.manager.basic.FashionGoldPriceManager;
import com.jatools.vo.basic.Charge;
import com.jatools.vo.basic.FashionGoldPrice;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.basic.ChargeForm;
import com.jatools.web.form.basic.FashionGoldPriceForm;
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

public class FashionGoldPriceController extends BaseMultiActionController {
	private Logger logger = Logger.getLogger(FashionGoldPriceController.class);

	private FashionGoldPriceManager fashionGoldPriceManager;

    public void setFashionGoldPriceManager(FashionGoldPriceManager fashionGoldPriceManager) {
        this.fashionGoldPriceManager = fashionGoldPriceManager;
    }

    @Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		FashionGoldPriceForm form = new FashionGoldPriceForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
			Pager pager = this.fashionGoldPriceManager.getFashionGoldPricePageData(condition);
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("basic/fashion_gold_price_list", "form", form);
	}
	/**
	 * 页面跳转
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
        FashionGoldPriceForm form = new FashionGoldPriceForm();
		//如果带有groupId参数，则为编辑页面
		if(StringUtil.isNotBlank(id)){
            FashionGoldPrice goldPrice = this.fashionGoldPriceManager.getFashionGoldPrice(id);
			form.setGoldPrice(goldPrice);
			return new ModelAndView("basic/fashion_gold_price_edit","form",form);
		}else{
			return new ModelAndView("basic/fashion_gold_price_edit","form",form);
		}
	}
	
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = this.fashionGoldPriceManager.getFashionGoldPricePageData(condition);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("爱尚金固定金价");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"createDate"}, new Integer[]{3});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"大类", "固定金价", "备注", "创建时间", "创建人", "修改时间", "修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"itemClassId", "goldPrice", "memo", "createDate", "createId", "updateDate", "updateId"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"createId", "updateId", "itemClassId"},
					new CacheSingletonIntf[]{UserCache.getInstance(), UserCache.getInstance(), ItemClassCache.getInstance()});
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
