package com.jatools.dao.bd.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.bd.BdCommonDao;
import com.jatools.vo.basic.OrnaDesc;
import com.jatools.vo.basic.Vendor;
import com.jatools.vo.bd.Analysis;
import com.jatools.vo.bd.ArticleType;
import com.jatools.vo.bd.BracketColor;
import com.jatools.vo.bd.Cust;
import com.jatools.vo.bd.Gold;
import com.jatools.vo.bd.ItemClass;
import com.jatools.vo.bd.ItemOrnaClass;
import com.jatools.vo.bd.LabelType;
import com.jatools.vo.bd.Org;
import com.jatools.vo.bd.OrnaClass;
import com.jatools.vo.bd.Ornadsc;
import com.jatools.vo.bd.Quality;
import com.jatools.vo.bd.Size;
import com.jatools.vo.bd.Style;
import com.jatools.vo.bd.StyleItemClass;
import com.jatools.vo.bd.StyleMiddleClass;
import com.jatools.vo.bd.StyleOrnaClass;
import com.jatools.vo.bd.Summary;
import com.jatools.vo.bd.Unit;
import com.jatools.vo.bd.Wear;
import com.jatools.vo.sys.Parameter;
import com.jatools.vo.util.SelectorOption;

@SuppressWarnings("unchecked")
public class BdCommonDaoImpl extends BaseDao implements BdCommonDao {
	/**
	 * 获取所有系统参数配置
	 */
	public List<Parameter> getAllParameters(){
		return executeQueryForList("BdCommon.getAllParameters", null);
	}
	/**
	 * 获取所有商品类别
	 * @return
	 */
	public List<ArticleType> getAllArticleType(){
		return executeQueryForList("BdCommon.getAllArticleType", null);
	}
	/**
	 * 获取所有大类
	 * @return
	 */
	public List<ItemClass> getAllItemClass(){
		return executeQueryForList("BdCommon.getAllItemClass", null);
	}

	/**
	 * 获取所有小类
	 * @return
	 */
	public List<OrnaClass> getAllOrnaClass(){
		return executeQueryForList("BdCommon.getAllOrnaClass", null);
	}
	/**
	 * 根据组织类型获取组织树
	 * @return
	 */
	public List<Org> getOrgTreeByType(String orgType){
		return executeQueryForList("BdCommon.getOrgTreeByType", orgType);
	}

	/**
	 * 获取所有大类小类对应关系数据
	 * @return
	 */
	public List<ItemOrnaClass> getAllItemOrnaClass(){
		return executeQueryForList("BdCommon.getAllItemOrnaClass", null);
	}

	/**
	 * 获取所有款式大类
	 * @return
	 */
	public List<StyleItemClass> getAllStyleItemClass(){
		return executeQueryForList("BdCommon.getAllStyleItemClass", null);
	}
	public String  getStyleItemCodeById(String styleItemId){
		List<StyleItemClass> list = executeQueryForList("BdCommon.getAllStyleItemClass", null);
		for (StyleItemClass b :  list) {
			if(styleItemId.equals(b.getItemClassId()))
				return b.getItemClassCode();
		}
		return null;
	}
	/**
	 * 获取所有款式中类
	 * @return
	 */
	public List<StyleMiddleClass> getAllStyleMiddleClass(){
		return executeQueryForList("BdCommon.getAllStyleMiddleClass", null);
	}
	/**
	 * 获取所有款式小类
	 * @return
	 */
	public List<StyleOrnaClass> getAllStyleOrnaClass(){
		return executeQueryForList("BdCommon.getAllStyleOrnaClass", null);
	}
	/**
	 * 获取所有计量单位
	 * @return
	 */
	public List<Unit> getAllUnit(){
		return executeQueryForList("BdCommon.getAllUnit", null);
	}
	/**
	 * 获取所有商品材质
	 * @return
	 */
	public List<Quality> getAllQuality(){
		return executeQueryForList("BdCommon.getAllQuality", null);
	}

	/**
	 * 根据款式id获取款式名称
	 * @param styleId
	 * @return
	 */
	public String getStyleNameById(String styleId){
		return (String)executeQueryForObject("BdCommon.getStyleNameById", styleId);
	}
	/**
	 * 根据分析范围id获取分析范围名称
	 * @param analysisId
	 * @return
	 */
	public String getAnalysisNameById(String analysisId){
		return (String)executeQueryForObject("BdCommon.getAnalysisNameById", analysisId);
	}
	/**
	 * 根据大类小类获取分析范围
	 * @param itemClassId
	 * @param ornaClassId
	 * @return
	 */
	public List<Analysis> getAnalysis(String itemClassId, String ornaClassId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("itemClassId", itemClassId);
		condition.put("ornaClassId", ornaClassId);
		return executeQueryForList("BdCommon.getAnalysis", condition);
	}

	/**
	 * 根据大类、小类获取款式大类
	 * @param itemClassId
	 * @param ornaClassId
	 * @return
	 */
	public List<StyleItemClass> getStyleItemClass(String itemClassId, String ornaClassId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("itemClassId", itemClassId);
		condition.put("ornaClassId", ornaClassId);
		return executeQueryForList("BdCommon.getStyleItemClass", condition);
	}

	/**
	 * 根据大类小类款式大类获取款式中类
	 * @param styleItemClassId
	 * @return
	 */
	public List<StyleMiddleClass> getStyleMiddleClass(String itemClassId, String ornaClassId, String styleItemClassId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("itemClassId", itemClassId);
		condition.put("ornaClassId", ornaClassId);
		condition.put("styleItemClassId", styleItemClassId);
		return executeQueryForList("BdCommon.getStyleMiddleClass", condition);
	}

	/**
	 * 根据大类小类款式大类款式中类获取款式小类
	 * @param styleMiddleClassId
	 * @return
	 */
	public List<StyleOrnaClass> getStyleOrnaClass(String itemClassId, String ornaClassId, String styleItemClassId, String styleMiddleClassId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("itemClassId", itemClassId);
		condition.put("ornaClassId", ornaClassId);
		condition.put("styleItemClassId", styleItemClassId);
		condition.put("styleMiddleClassId", styleMiddleClassId);
		return executeQueryForList("BdCommon.getStyleOrnaClass", condition);
	}
	/**
	 * 根据款式大类、款式中类、款式小类获取款式
	 * @param styleItemClassId
	 * @param styleMiddleClassId
	 * @param styleOrnaClassId
	 * @return
	 */
	public List<Style> getStyle(String itemClassId, String ornaClassId, String styleItemClassId, String styleMiddleClassId, String styleOrnaClassId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("itemClassId", itemClassId);
		condition.put("ornaClassId", ornaClassId);
		condition.put("styleItemClassId", styleItemClassId);
		condition.put("styleMiddleClassId", styleMiddleClassId);
		condition.put("styleOrnaClassId", styleOrnaClassId);
		return executeQueryForList("BdCommon.getStyle", condition);
	}
	public List<Style> getStyle(String itemClassId, String ornaClassId, String styleItemClassId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("itemClassId", itemClassId);
		condition.put("ornaClassId", ornaClassId);
		condition.put("styleItemClassId", styleItemClassId);
		return executeQueryForList("BdCommon.getStyle3", condition);
	}
	/**
	 * 获取所有供应商
	 * @return
	 */
	public List<Vendor> getAllVendor(){
		return executeQueryForList("BdCommon.getAllVendor", null);
	}
	/**
	 * 托架材质下的托架颜色
	 * @param id
	 * @return
	 */
	@Override
	public List<BracketColor> getBracketColorByQualityId(String id) {
		// TODO Auto-generated method stub
		return executeQueryForList("BdCommon.getBracketColorByQualityId", id);
	}
	
	/**
	 * 根据供应商ID返回供应商
	 * @param custId
	 * @return
	 */
	public Cust getCustById(String custId){
		return (Cust) executeQueryForObject("BdCommon.getCustById", custId);
	}
	/**
	 * 款式下的材质
	 * @param styleId
	 * @return
	 */
	@Override
	public List<Quality> getQualityByStyleId(String styleId){
		return  executeQueryForList("BdCommon.getQualityByStyleId", styleId);
	}
	/**
	 * 款式 材质下的托架颜色
	 * @param styleId
	 * @param qualityId
	 * @return
	 */
	@Override
	public List<BracketColor> getColorByQualityId(String styleId,String qualityId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("styleId", styleId);
		condition.put("qualityId", qualityId);
		return  executeQueryForList("BdCommon.getColorByStyleId", condition );
	}
	@Override
	public Style getStyleByIdAndVendor(String styleId,String vendorId) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("styleId", styleId);
		condition.put("vendorId", vendorId);
		return (Style)executeQueryForObject("BdCommon.getStyleByIdAndVendor", condition);
	}
	@Override
	public String getChargeByNum(String value) {
		return (String) executeQueryForObject("BdCommon.getChargeByNum", value); 
	}
	@Override
	public String getGoldByCalc(String itemClassId, String ornaClassId,
			String qualityId, String orgId) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("itemClassId", itemClassId);
		condition.put("ornaClassId", ornaClassId);
		condition.put("qualityId", qualityId);
		condition.put("orgId", orgId);
		return (String)executeQueryForObject("BdCommon.getGoldByCalc", condition); 
	}
	/**
	 * 大小类计量单位获取标签类型
	 * @param itemClassId
	 * @param ornaClassId
	 * @param unitId
	 * @return
	 */
	public List<LabelType> getLabelTypeByCalc(String itemClassId,String ornaClassId,String unitId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("itemClassId", itemClassId);
		condition.put("ornaClassId", ornaClassId);
		condition.put("unitId", unitId);
		return executeQueryForList("BdCommon.getLabelTypeByCalc", condition);
	}
	public List<com.jatools.vo.basic.LabelType> getNewLabelTypeByCalc(String itemClassId,String ornaClassId,String unitId,String privateType){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("itemClassId", itemClassId);
		condition.put("ornaClassId", ornaClassId);
		condition.put("unitId", unitId);
		condition.put("privateType", privateType);
		return executeQueryForList("BdCommon.getNewLabelTypeByCalc", condition);
	}
	/**
	 * 根据款式id获取款式对象信息
	 * @param styleId
	 * @return
	 */
	public Style getStyleById(String styleId) {
		return (Style)executeQueryForObject("BdCommon.getStyleById", styleId);
	}
	@Override
	public List<Size> getSizeByItemIdAndOrnaId(String itemId, String ornaId) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("itemClassId", itemId);
		condition.put("ornaClassId", ornaId);
		return executeQueryForList("BdCommon.getSizeByItemIdAndOrnaId", condition);
	}
	/**
	 * 佩戴对象列表
	 * @return
	 */
	public  List<Wear> getAllWear(){
		return executeQueryForList("BdCommon.getAllWear", "");
	}
	
	/**
	 * 大小类材质获取 饰品名
	 * @param itemClassId
	 * @param ornaClassId
	 * @param qualityId
	 * @return
	 */
	public Ornadsc getOrnadscByItemIdAndOrnaId(String itemClassId,String ornaClassId,String qualityId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("itemClassId", itemClassId);
		condition.put("ornaClassId", ornaClassId);
		condition.put("qualityId", qualityId);
		return (Ornadsc)executeQueryForObject("BdCommon.getOrnadscByItemIdAndOrnaId", condition);
	}
	public OrnaDesc getNewOrnadscByItemIdAndOrnaId(String itemClassId,String ornaClassId,String qualityId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("itemClassId", itemClassId);
		condition.put("ornaClassId", ornaClassId);
		condition.put("qualityId", qualityId);
		return (OrnaDesc)executeQueryForObject("BdCommon.getNewOrnadscByItemIdAndOrnaId", condition);
	}
	@Override
	public List<Gold> getGoldByIniv(String itemClassId,String ornaClassId,String orgId) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("itemClassId", itemClassId);
		condition.put("ornaClassId", ornaClassId);
		condition.put("orgId", orgId);
		return executeQueryForList("BdCommon.getGoldByIniv", condition);
	}
	@Override
	public String getSizeNameById(String sizeId) {
		
		return (String)executeQueryForObject("BdCommon.getSizeNameById", sizeId);
	}
	@Override
	public List<Summary> getAllSummary() {
		return executeQueryForList("BdCommon.getAllSummay", "");
	}
	
	public List<ItemClass> getItemClassByMaterialType(String materialType){
		return (List<ItemClass>)executeQueryForList("BdCommon.getItemClassByMaterialType", materialType);
	}
	@Override
	public String getCoefficientBySS(Map<String, String> condition) {
		return (String)executeQueryForObject("BdCommon.getCoefficientBySS", condition);
	}
	/**
	 * 获取所有周期类型
	 * @return
	 */
	public List<SelectorOption> getAllCycleTypeForSlt(){
		return (List<SelectorOption>)executeQueryForList("BdCommon.getAllCycleTypeForSlt", null);
	}
	/**
	 * 获取所有区域
	 * @return
	 */
	public List<SelectorOption> getAllRegionForSlt(){
		return (List<SelectorOption>)executeQueryForList("BdCommon.getAllRegionForSlt", null);
	}
	@Override
	public List<Quality> getQualityByItemClassId(String itemClassId) {
		return (List<Quality>) executeQueryForList("BdCommon.getQualityByItemClassId", itemClassId);
	}
	@Override
	public String getMinChargeByNum() {
		return (String)executeQueryForObject("BdCommon.getMinChargeByNum", "");
	}
	/**
	 * 获取所有主题款
	 * @return
	 */
	public List<SelectorOption> getAllStyleTheme(){
		return (List<SelectorOption>)executeQueryForList("BdCommon.getAllStyleTheme", null);
	}
	@Override
	public String getStyleThemeName(String styleId) {
		return (String)executeQueryForObject("BdCommon.getStyleThemeName", styleId);
	}
	
	public Pager getStyleByCalc(Map<String, String> condition ){
		return executeQueryForPager("BdCommon.getAllStyleByCalc", "BdCommon.getAllStyleByCalcTotalCount", condition);
	}
	

	/**
	 * 根据行政组织获取人员
	 */
	public Pager getExeOrgEmp(String orgId, String start, String limit){
		Map<String, String> condition  = new HashMap<String, String>();
		condition.put("orgId", orgId);
		condition.put("start", start);
		condition.put("limit", limit);
		return executeQueryForPager("BdCommon.getExeOrgEmpPageData", "BdCommon.getExeOrgEmpTotalCount", condition);
	}
	/**
	 * 根据大类小类分析范围获取款式
	 * @param itemClassId
	 * @param ornaClassId
	 * @param analysisId
	 * @return
	 */
	public List<Style> getStyle2(String itemClassId, String ornaClassId, String analysisId){
		Map<String, String> condition  = new HashMap<String, String>();
		condition.put("itemClassId", itemClassId);
		condition.put("ornaClassId", ornaClassId);
		condition.put("analysisId", analysisId);
		return executeQueryForList("BdCommon.getStyle2", condition);
	}
	@Override
	public List<SelectorOption> getMaintainClassForSlt(String maintaintype) {
		return (List<SelectorOption>)executeQueryForList("BdCommon.getMaintainClassForSlt", maintaintype);
	}
	/**
	 * 获取色级品质
	 * @param colorGradeId
	 * @return
	 */
	public SelectorOption getGradeByColorGradeId(String colorGradeId){
		return (SelectorOption) executeQueryForObject("BdCommon.getGradeByColorGradeId", colorGradeId);
	}
	/**
	 *  获取净度品质
	 * @param cleanId
	 * @return
	 */
	public SelectorOption getGradeByCleanId(String cleanId){
		return (SelectorOption) executeQueryForObject("BdCommon.getGradeByCleanId", cleanId);
	}
	@Override
	public String getPtChargeByNum(String value) {
		return (String) executeQueryForObject("BdCommon.getPtChargeByNum", value); 
	}
	@Override
	public String getPtMaxChargeByNum() {
		return (String)executeQueryForObject("BdCommon.getPtMinChargeByNum", "");
	}
}
