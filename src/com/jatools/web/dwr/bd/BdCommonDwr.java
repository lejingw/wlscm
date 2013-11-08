package com.jatools.web.dwr.bd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.ParameterConstant;
import com.jatools.manager.bd.BdCommonManager;
import com.jatools.vo.basic.OrnaDesc;
import com.jatools.vo.basic.Vendor;
import com.jatools.vo.bd.Analysis;
import com.jatools.vo.bd.BracketColor;
import com.jatools.vo.bd.Gold;
import com.jatools.vo.bd.ItemClass;
import com.jatools.vo.bd.ItemOrnaClass;
import com.jatools.vo.bd.LabelType;
import com.jatools.vo.bd.Ornadsc;
import com.jatools.vo.bd.Quality;
import com.jatools.vo.bd.Size;
import com.jatools.vo.bd.Style;
import com.jatools.vo.bd.StyleItemClass;
import com.jatools.vo.bd.StyleMiddleClass;
import com.jatools.vo.bd.StyleOrnaClass;
import com.jatools.vo.bd.Summary;
import com.jatools.vo.bd.Wear;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.cache.ArticleTypeCache;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.ItemOrnaClassCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.ParameterCache;
import com.jatools.web.cache.QualityCache;
import com.jatools.web.cache.StyleThemeCache;
import com.jatools.web.cache.UnitCache;
import com.jatools.web.cache.VendorCache;
import com.jatools.web.form.BaseForm;
import com.jatools.web.form.basic.StyleForm;
import com.jatools.web.util.BdCommon;
import com.jatools.web.util.StringUtil;

public class BdCommonDwr {
	private BdCommonManager bdCommonManager;
	
	public void setBdCommonManager(BdCommonManager dbCommonManager) {
		this.bdCommonManager = dbCommonManager;
	}
	/**
	 * 获取系统参数配置值
	 * @param name
	 * @return
	 */
	public String getParameterValue(String name){
		return ParameterCache.getInstance().getValue(name);
	}
	/**
	 * 获取所有商品类别
	 * @return
	 */
	public List<SelectorOption> getArticleTypeForSlt(){
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		for(String articleTypeId : ArticleTypeCache.getInstance().getAllArticleType().keySet()){
			sltList.add(new SelectorOption(articleTypeId, ArticleTypeCache.getInstance().getAllArticleType().get(articleTypeId)));
		}
		return sltList;
	}
	/**
	 * 根据商品类别获取大类
	 */
	public List<SelectorOption> getItemClassForSlt(String articleTypeId){
		if(StringUtil.isEmpty(articleTypeId)){
			return null;
		}
		Map<String, ItemClass> itemClassMap = ItemClassCache.getInstance().getAllItemClass();
		if(null == itemClassMap || itemClassMap.size()<1)
			return null;
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		for(ItemClass itemClass : itemClassMap.values()){
			if(itemClass.getArticleTypeId().equals(articleTypeId)){
				sltList.add(new SelectorOption(itemClass.getItemClassId(), itemClass.getItemClassDesc()));
			}
		}
		return sltList;
	}
	/**
	 * 获取所有大类
	 */
	public List<SelectorOption> getAllItemClassForSlt(){
		Map<String, ItemClass> itemClassMap = ItemClassCache.getInstance().getAllItemClass();
		if(null == itemClassMap || itemClassMap.size()<1)
			return null;
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		for(ItemClass itemClass : itemClassMap.values()){
			sltList.add(new SelectorOption(itemClass.getItemClassId(), itemClass.getItemClassDesc()));
		}
		return sltList;
	}
	/**
	 * 根据大类id，到大类小类关系表中获取小类数据
	 */
	public List<SelectorOption> getOrnaClassByItemClassIdForSlt(String itemClassId){
		List<ItemOrnaClass> list = ItemOrnaClassCache.getInstance().getOrnaClassOrnaByItemClassId(itemClassId);
		if(null == list || list.size()<1)
			return null;
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		for(ItemOrnaClass item : list){
			sltList.add(new SelectorOption(item.getOrnaClassId(), item.getOrnaClassDesc()));
		}
		return sltList;
	}
	/**
	 * 获取所有的商品材质
	 * @return
	 */
	public List<SelectorOption> getAllQualityForSlt(){
		Map<String, String> qualityMap = QualityCache.getInstance().getAllQuality();
		if(null == qualityMap || qualityMap.size()<1)
			return null;
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		for(String key:qualityMap.keySet()){
			sltList.add(new SelectorOption(key, qualityMap.get(key)));
		}
		return sltList;
	}
	
	/**
	 * 根据大类id获取大类名称
	 * @param itemClassId
	 * @return
	 */
	public String getItemClassDesc(String itemClassId){
		String tmp = ItemClassCache.getInstance().getItemClassDesc(itemClassId);
		if(null != tmp){
			return tmp;
		}
		return itemClassId;
	}
	/**
	 * 根据大类id获取商品类别
	 * @param itemClassId
	 * @return
	 */
	public static String getArticleTypeDescByItemId(String itemClassId){
		String tmp = ItemClassCache.getInstance().getArticleTypeDesc(itemClassId);
		if(null != tmp){
			return tmp;
		}
		return itemClassId;
	}
	/**
	 * 根据商品类别id获取商品名称
	 * @param articleTypeId
	 * @return
	 */
	public String getArticleTypeDesc(String articleTypeId){
		String tmp = ArticleTypeCache.getInstance().getArticleTypeName(articleTypeId);
		if(null != tmp){
			return tmp;
		}
		return articleTypeId;
	}
	
	/**
	 * 根据小类id获取小类名称
	 * @param ornaClassId
	 * @return
	 */
	public String getOrnaClassDesc(String ornaClassId){
		String tmp = OrnaClassCache.getInstance().getOrnaClass(ornaClassId);
		if(null != tmp){
			return tmp;
		}
		return ornaClassId;
	}
	/**
	 * 根据款式id获取款式名称
	 * @param styleId
	 * @return
	 */
	public String getStyleName(String styleId){
		String tmp = bdCommonManager.getStyleNameById(styleId);
		if(null != tmp){
			return tmp;
		}
		return styleId;
	}
	/**
	 * 根据计量单位id获取计量单位名称
	 * @param unitId
	 * @return
	 */
	public String getUnitName(String unitId){
		String unitName = UnitCache.getInstance().getUnitName(unitId);
		if(null != unitName){
			return unitName;
		}
		return unitId;
	}
	/**
	 * 根据分析范围id获取分析范围名称
	 * @param analysisId
	 * @return
	 */
	public String getAnalysisName(String analysisId){
		String tmp = bdCommonManager.getAnalysisNameById(analysisId);
		if(null != tmp){
			return tmp;
		}
		return analysisId;
	}
	/**
	 * 根据大类小类获取分析范围
	 * @param itemClassId
	 * @param ornaClassId
	 * @return
	 */
	public List<SelectorOption> getAnalysisForSlt(String itemClassId, String ornaClassId){
		if(StringUtil.isEmpty(itemClassId) || StringUtil.isEmpty(ornaClassId)){
			return null;
		}
		List<Analysis> list = bdCommonManager.getAnalysis(itemClassId, ornaClassId);
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		for(Analysis analysis : list){
			sltList.add(new SelectorOption(analysis.getAnalysisId(), analysis.getAnalysisDesc()));
		}
		return sltList;
	}
	/**
	 * 根据大类、小类获取款式大类
	 * @param itemClassId
	 * @param ornaClassId
	 */
	public List<SelectorOption> getStyleItemClassForSlt(String itemClassId, String ornaClassId){
		if(StringUtil.isEmpty(itemClassId) || StringUtil.isEmpty(ornaClassId)){
			return null;
		}
		List<StyleItemClass> list = bdCommonManager.getStyleItemClass(itemClassId, ornaClassId);
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		for(StyleItemClass styleItemClass : list){
			sltList.add(new SelectorOption(styleItemClass.getItemClassId(), styleItemClass.getItemClassDesc()));
		}
		return sltList;
	}
    /**
	 * 根据大类、小类获取款式大类
	 * @param itemClassId
	 * @param ornaClassId
	 * @param isLoveStyle 是否情侣类
	 */
	public List<SelectorOption> getPushStyleItemClassForSlt(String itemClassId, String ornaClassId, String isLoveStyle){
		if(StringUtil.isEmpty(itemClassId) || StringUtil.isEmpty(ornaClassId)){
			return null;
		}
        String loveStyleId = "161";
        List<SelectorOption> sltList = new ArrayList<SelectorOption>();
        List<StyleItemClass> list = bdCommonManager.getStyleItemClass(itemClassId, ornaClassId);
        for(StyleItemClass styleItemClass : list){
            if("1".equals(isLoveStyle)) {
                if(loveStyleId.equals(styleItemClass.getItemClassId())) {
                    sltList.add(new SelectorOption(styleItemClass.getItemClassId(), styleItemClass.getItemClassDesc()));
                }
            } else {
                if(!loveStyleId.equals(styleItemClass.getItemClassId())) {
                    sltList.add(new SelectorOption(styleItemClass.getItemClassId(), styleItemClass.getItemClassDesc()));
                }
            }
        }

		return sltList;
	}
	/**
	 * 根据大类小类款式大类获取款式中类
	 * @param styleItemClassId
	 * @return
	 */
	public List<SelectorOption> getStyleMiddleClassForSlt(String itemClassId, String ornaClassId, String styleItemClassId){
		if(StringUtil.isEmpty(styleItemClassId)){
			return null;
		}
		List<StyleMiddleClass> list = bdCommonManager.getStyleMiddleClass(itemClassId, ornaClassId, styleItemClassId);
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		for(StyleMiddleClass styleMiddle : list){
			sltList.add(new SelectorOption(styleMiddle.getMiddleClassId(), styleMiddle.getMiddleClassDesc()));
		}
		return sltList;
	}
	/**
	 * 根据大类小类款式大类款式中类获取款式小类
	 * @param styleMiddleClassId
	 * @return
	 */
	public List<SelectorOption> getStyleOrnaClassForSlt(String itemClassId, String ornaClassId, String styleItemClassId, String styleMiddleClassId){
		if(StringUtil.isEmpty(styleMiddleClassId)){
			return null;
		}
		List<StyleOrnaClass> list = bdCommonManager.getStyleOrnaClass(itemClassId, ornaClassId, styleItemClassId, styleMiddleClassId);
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		for(StyleOrnaClass styleOrna : list){
			sltList.add(new SelectorOption(styleOrna.getOrnaClassId(), styleOrna.getOrnaClassDesc()));
		}
		return sltList;
	}
	/**
	 * 根据大类小类款式大类、款式中类、款式小类获取款式
	 */
	public List<SelectorOption> getStyleForSlt(String itemClassId, String ornaClassId, String styleItemClassId, String styleMiddleClassId, String styleOrnaClassId){
		if(StringUtil.isEmpty(styleItemClassId) || StringUtil.isEmpty(styleMiddleClassId) || StringUtil.isEmpty(styleOrnaClassId)){
			return null;
		}
		List<Style> list = bdCommonManager.getStyle(itemClassId, ornaClassId, styleItemClassId, styleMiddleClassId, styleOrnaClassId);
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		for(Style style : list){
			sltList.add(new SelectorOption(style.getStyleId(), style.getStyleName()));
		}
		return sltList;
	}
	/**
	 * 托架材质下的托架颜色
	 * @param id
	 * @return
	 */
	public List<SelectorOption> getBracketColorByQualityId(String id){
		if(StringUtil.isEmpty(id))
			return null;
		List<BracketColor> list = bdCommonManager.getBracketColorByQualityId(id);
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		for(BracketColor bc : list){
			sltList.add(new SelectorOption(bc.getBracketColorId(), bc.getBracketColorDsc()));
		}
		return sltList;
	}
	
	/**
	 * 根据供应商ID返回供应商名
	 * @param custId
	 * @return
	 */
	public String getCustById(String custId){
		if(custId==null)
			return null;
		else{
			return bdCommonManager.getCustById(custId).getCustName();
		}
	}
	/**
	 * 款式下的材质
	 * @param styleId
	 * @return
	 */
	public List<SelectorOption> getQualityByStyleId(String styleId){
		if(StringUtil.isEmpty(styleId))
			return null;
		List<Quality> list = bdCommonManager.getQualityByStyleId(styleId);
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		for(Quality bc : list){
			sltList.add(new SelectorOption(bc.getQualityId(), bc.getQualityName()));
		}
		return sltList;
	}
	/**
	 * 款式 材质下的托架颜色
	 * @param styleId
	 * @param qualityId
	 * @return
	 */
	public List<SelectorOption> getColorByQualityId(String styleId,String qualityId){
		if(StringUtil.isEmpty(styleId))
			return null;
		List<BracketColor> list = bdCommonManager.getColorByQualityId(styleId, qualityId);
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		for(BracketColor bc : list){
			sltList.add(new SelectorOption(bc.getBracketColorId(), bc.getBracketColorDsc()));
		}
		return sltList;
	}
	/**
	 * 根据款式ID得到款式对象
	 * @param styleId
	 * @return
	 */
	public Style getStyleByIdAndVendor(String styleId,String vendorId){
		if(styleId==null||"".equals(styleId)){
			return null;
		}else{
			Style style = bdCommonManager.getStyleByIdAndVendor(styleId,vendorId);
			return style;
		}
	}
	/**
	 * 取特殊工费
	 * @param value
	 * @param type
	 * @return
	 */
	public String getChargeByNum(String value,String type){
		if(StringUtil.isEmpty(value))
			value = "";
        String result = bdCommonManager.getChargeByNum(value);
        if(StringUtil.isEmpty(result) && "1".equals(type))
            result = bdCommonManager.getMinChargeByNum();
        return result;
	}
	/**
	 * pt950取特殊工费
	 * @param value
	 * @return
	 */
	public String getPtChargeByNum(String value,String type){
		if(StringUtil.isBlank(value))
			value   ="";
        String result = bdCommonManager.getPtChargeByNum(value);
        if(StringUtil.isEmpty(result) &&"1".equals(type))
            result = bdCommonManager.getPtMaxChargeByNum();
        return result;
	}
	/**
	 * 查找金价
	 * @param itemClassId
	 * @param ornaClassId
	 * @param qualityId
	 * @param req
	 * @return
	 */
	public String getGoldByCalc(String itemClassId,String ornaClassId,String qualityId,HttpServletRequest req){
		String value = bdCommonManager.getGoldByCalc(itemClassId, ornaClassId, qualityId, CommonUtil.getSessionOrgId(req.getSession()));
		return value;
	}
	/**
	 * 大小类计量单位获取标签类型
	 * @param itemClassId
	 * @param ornaClassId
	 * @param unitId
	 * @return
	 */
	public List<SelectorOption> getLabelTypeByCalc(String itemClassId,String ornaClassId,String unitId){
		if(StringUtil.isEmpty(itemClassId)||StringUtil.isEmpty(ornaClassId)||StringUtil.isEmpty(unitId))
			return null;
		List<LabelType> list = bdCommonManager.getLabelTypeByCalc(itemClassId, ornaClassId, unitId);
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		for(LabelType bc : list){
			sltList.add(new SelectorOption(bc.getLabelTypeName()+"_"+bc.getIsDblLabel(), bc.getLabelTypeName()));
		}
		return sltList;
	}
	/**新
	 * 大小类计量单位获取标签类型
	 * @param itemClassId
	 * @param ornaClassId
	 * @param unitId
	 * @return
	 */
	public List<SelectorOption> getNewLabelTypeByCalc(String itemClassId,String ornaClassId,String unitId,String qualityId,String privateType){
		String isdf = "1";
		String qid = BdCommon.getParameterValue(ParameterConstant.QUALITY_ZZ_ID);//当材质为ZZ，按是否镶嵌为否去找，反之为是  12.03.30
		if(qid.equals(qualityId))
			isdf="0";
		if(StringUtil.isEmpty(itemClassId)||StringUtil.isEmpty(ornaClassId)||StringUtil.isEmpty(unitId))
			return null;
		List<com.jatools.vo.basic.LabelType> list = bdCommonManager.getNewLabelTypeByCalc(itemClassId, ornaClassId, unitId,privateType);
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		for(com.jatools.vo.basic.LabelType bc : list){
			if(isdf.equals(bc.getBracketFlag()))
				sltList.add(new SelectorOption(bc.getLabelTypeName()+"_"+bc.getDblLabelFlag(), bc.getLabelTypeName()));
		}
		return sltList;
	}
	
	public List<SelectorOption> getAllUnit(){
		Map<String, String> allUnit = UnitCache.getInstance().getAllUnit();
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		for(String key : allUnit.keySet()){
			sltList.add(new SelectorOption(key, allUnit.get(key)));
		}
		return sltList;
	}
	/**
	 * 获取所有供应商
	 * @return
	 */
	public List<SelectorOption> getAllVendor(){
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		Map<String, Vendor> allVendorMap = VendorCache.getInstance().getAllVendor();
		for(String key : allVendorMap.keySet()){
			Vendor vendor = allVendorMap.get(key);
			sltList.add(new SelectorOption(vendor.getVendorId(), vendor.getVendorName()));
		}
		return sltList;
	}
	/**
	 * 大小类的尺寸
	 * @param itemId
	 * @param ornaId
	 * @return
	 */
	public List<SelectorOption> getSizeByItemIdAndOrnaId(String itemId,String ornaId){
		if(StringUtil.isEmpty(itemId)||StringUtil.isEmpty(ornaId))
			return null;
		List<Size> list = bdCommonManager.getSizeByItemIdAndOrnaId(itemId, ornaId);
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		for(Size bc : list){
			sltList.add(new SelectorOption(bc.getSizeId(), bc.getSizeName()));
		}
		return sltList;
	}
	/**
	 * 佩戴对象列表
	 * @return
	 */
	public List<SelectorOption> getAllWear(){
		List<Wear> list = bdCommonManager.getAllWear();
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		
		for(Wear bc : list){
			sltList.add(new SelectorOption(bc.getWearId(), bc.getWearName()));
		}
		return sltList;
	}
	
	/**
	 * 大小类材质获取 饰品名
	 * @param itemClassId
	 * @param ornaClassId
	 * @param qualityId
	 * @return
	 */
	public Ornadsc getOrnadscByItemIdAndOrnaId(String itemClassId,String ornaClassId,String qualityId){
		if(StringUtil.isEmpty(itemClassId)||StringUtil.isEmpty(ornaClassId))
			return null;
		Ornadsc o = bdCommonManager.getOrnadscByItemIdAndOrnaId(itemClassId, ornaClassId, qualityId);
		return o;
	}
	public OrnaDesc getNewOrnadscByItemIdAndOrnaId(String itemClassId,String ornaClassId,String qualityId){
		if(StringUtil.isEmpty(itemClassId)||StringUtil.isEmpty(ornaClassId))
			return null;
		OrnaDesc o = bdCommonManager.getNewOrnadscByItemIdAndOrnaId(itemClassId, ornaClassId, qualityId);
		return o;
	}
	/**
	 * 查找价格属性组 
	 * @param itemClassId
	 * @param ornaClassId
	 * @param unitId
	 * @return
	 */
	public List<SelectorOption> getGoldByIniv(String itemClassId,String ornaClassId,String unitId,String allowInivType,HttpServletRequest req){
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		
		if("2".equals(allowInivType)){
			sltList.add(new SelectorOption("A", "A"));
			return sltList;
		}else{
			if(unitId.equals("612")){//件
				sltList.add(new SelectorOption("J", "J"));
				return sltList;
			}else{
				if(StringUtil.isEmpty(itemClassId)||StringUtil.isEmpty(ornaClassId))
					return null;
				List<Gold> list = bdCommonManager.getGoldByIniv(itemClassId, ornaClassId, CommonUtil.getSessionOrgId(req.getSession()));
				for(Gold bc : list){
					if(!"J".equals(bc.getGroup()))
						sltList.add(new SelectorOption(bc.getGroup(), bc.getGroup()));
				}
				return sltList;
			}
		}
	}
	
	public List<SelectorOption> getAllSummary(){
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		List<Summary> list = bdCommonManager.getAllSummary();
		for(Summary bc : list){
			sltList.add(new SelectorOption(bc.getSummaryId(), bc.getSummaryName()));
		}
		return sltList;
	}
	
	public List<SelectorOption> getItemClassByMaterialType(String materialType){
		List<ItemClass> itemClassList = this.bdCommonManager.getItemClassByMaterialType(materialType);
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		for(ItemClass itemClass : itemClassList){
			sltList.add(new SelectorOption(itemClass.getItemClassId(), itemClass.getItemClassDesc()));
		}
		return sltList;
	}
	public String getCoefficientBySS(String itemClassId,String num){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("itemClassId", itemClassId);
		condition.put("num", num);
		return this.bdCommonManager.getCoefficientBySS(condition);
	}
	/**
	 * 获取所有周期类型
	 * @return
	 */
	public List<SelectorOption> getAllCycleTypeForSlt(){
		List<SelectorOption> sltList = this.bdCommonManager.getAllCycleTypeForSlt();
		return sltList;
	}
	/**
	 * 获取所有区域
	 * @return
	 */
	public List<SelectorOption> getAllRegionForSlt(){
		List<SelectorOption> sltList = this.bdCommonManager.getAllRegionForSlt();
		return sltList;
	}
	public List<SelectorOption> getQualityByItemClassId(String itemClassId){
		List<Quality> itemClassList = this.bdCommonManager.getQualityByItemClassId(itemClassId);
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		for(Quality itemClass : itemClassList){
			sltList.add(new SelectorOption(itemClass.getQualityId(), itemClass.getQualityName()));
		}
		return sltList;
	}
	/**
	 * 获取所有主题款
	 * @return
	 */
	public List<SelectorOption> getAllStyleTheme(){
		Map<String, String> map = StyleThemeCache.getInstance().getAllStyleTheme();
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		for(String key : map.keySet()){
			sltList.add(new SelectorOption(key, map.get(key)));
		}
		return sltList;
	}
	
	public List<SelectorOption> getAllOrnaClassForSlt(String itemClassId){
		List<ItemOrnaClass> list = null;
		if(StringUtil.isNotBlank(itemClassId)){
			list = ItemOrnaClassCache.getInstance().getOrnaClassOrnaByItemClassId(itemClassId);
		} else {
			list = ItemOrnaClassCache.getInstance().getAllOrnaClassOrna();
		}
		if(null == list || list.size()<1)
			return null;
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		for(ItemOrnaClass item : list){
			sltList.add(new SelectorOption(item.getOrnaClassId(), item.getOrnaClassDesc()));
		}
		return sltList;
	}
	
	public StyleForm getStyleByCalcWin(String itemClassId,String ornaClassId,
			String styleItemId,String styleMiddleId,String styleOrnaId,
			String start, String limit,HttpServletRequest req){
		StyleForm form  = new StyleForm();
		String basePath = CommonUtil.getPicBasePath();
		try {
			Map<String, String> condition = new HashMap<String, String>();
			condition.put("itemClassId", itemClassId);
			condition.put("ornaClassId", ornaClassId);
			condition.put("styleItemId", styleItemId);
			condition.put("styleMiddleId", styleMiddleId);
			condition.put("styleOrnaId", styleOrnaId);
			condition.put("start", start);
			condition.put("limit", limit);
			
			Pager pager = bdCommonManager.getStyleByCalc(condition);
			
			form.setStylePicUrl(basePath);
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return form; 
	}
	
	/**
	 * 根据获取人员
	 */
	public BaseForm getExeOrgEmp(String orgId, String start, String limit){
		BaseForm form = new BaseForm();
		Pager pager = bdCommonManager.getExeOrgEmp(orgId, start, limit);
		form.setPager(pager);
		return form;
	}
	/**
	 * 根据大类小类分析范围获取款式下拉框数据
	 * @param itemClassId
	 * @param ornaClassId
	 * @param analysisId
	 * @return
	 */
	public List<SelectorOption> getStyleForSlt2(String itemClassId, String ornaClassId, String analysisId){
		if(StringUtil.isEmpty(itemClassId) || StringUtil.isEmpty(ornaClassId) || StringUtil.isEmpty(analysisId)){
			return null;
		}
		List<Style> list = bdCommonManager.getStyle2(itemClassId, ornaClassId, analysisId);
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		for(Style style : list){
			sltList.add(new SelectorOption(style.getStyleId(), style.getStyleName()));
		}
		return sltList;
	}
	
	/**
	 * 原料类型取耗料
	 * @return
	 */
	public List<SelectorOption> getMaintainClassForSlt(String maintaintype){
		if(StringUtil.isEmpty(maintaintype) || StringUtil.isEmpty(maintaintype)){
			return null;
		}
		return this.bdCommonManager.getMaintainClassForSlt(maintaintype);
	}
	/**
	 * 获取色级品质
	 * @param colorGradeId
	 * @return
	 */
	public SelectorOption getGradeByColorGradeId(String colorGradeId){
		SelectorOption so = bdCommonManager.getGradeByColorGradeId(colorGradeId);
		return so;
	}
	/**
	 * 获取净度品质
	 * @param cleanId
	 * @return
	 */
	public SelectorOption getGradeByCleanId(String cleanId){
		SelectorOption so = bdCommonManager.getGradeByCleanId(cleanId);
		return so;
	}


    /**
     * 根据大类小类款式大类、款式中类、款式小类获取款式
     */
    public List<SelectorOption> getStyleForSlt3(String itemClassId, String ornaClassId, String styleItemClassId){
        if(StringUtil.isEmpty(styleItemClassId)){
            return null;
        }
        List<Style> list = bdCommonManager.getStyle(itemClassId, ornaClassId, styleItemClassId);
        List<SelectorOption> sltList = new ArrayList<SelectorOption>();
        for(Style style : list){
            sltList.add(new SelectorOption(style.getStyleId(), style.getStyleName()));
        }
        return sltList;
    }
}