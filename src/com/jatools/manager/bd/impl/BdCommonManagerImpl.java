package com.jatools.manager.bd.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.bd.BdCommonDao;
import com.jatools.manager.bd.BdCommonManager;
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

public class BdCommonManagerImpl implements BdCommonManager {

	private BdCommonDao bdCommonDao;
	
	public void setBdCommonDao(BdCommonDao bdCommonDao) {
		this.bdCommonDao = bdCommonDao;
	}

	/**
	 * 获取所有系统参数配置
	 */
	public List<Parameter> getAllParameters(){
		return bdCommonDao.getAllParameters();
	}
	/**
	 * 获取所有商品类别
	 * @return
	 */
	public List<ArticleType> getAllArticleType(){
		return bdCommonDao.getAllArticleType();
	}
	/**
	 * 获取所有大类
	 * @return
	 */
	public List<ItemClass> getAllItemClass(){
		return bdCommonDao.getAllItemClass();
	}

	/**
	 * 获取所有小类 
	 * @return
	 */
	public List<OrnaClass> getAllOrnaClass(){
		return bdCommonDao.getAllOrnaClass();
	}
	/**
	 * 根据组织类型获取组织树
	 * @return
	 */
	public List<Org> getOrgTree(){
		return bdCommonDao.getOrgTree();
	}

	/**
	 * 获取所有大类小类对应关系数据
	 * @return
	 */
	public List<ItemOrnaClass> getAllItemOrnaClass(){
		return bdCommonDao.getAllItemOrnaClass();
	}
	

	/**
	 * 获取所有款式大类
	 * @return
	 */
	public List<StyleItemClass> getAllStyleItemClass(){
		return bdCommonDao.getAllStyleItemClass();
	}
	public String  getStyleItemCodeById(String styleItemId){
		return this.bdCommonDao.getStyleItemCodeById(styleItemId);
	}
	
	
	
	/**
	 * 获取所有款式中类
	 * @return
	 */
	public List<StyleMiddleClass> getAllStyleMiddleClass(){
		return bdCommonDao.getAllStyleMiddleClass();
	}
	/**
	 * 获取所有款式小类
	 * @return
	 */
	public List<StyleOrnaClass> getAllStyleOrnaClass(){
		return bdCommonDao.getAllStyleOrnaClass();
	}
	/**
	 * 获取所有计量单位
	 * @return
	 */
	public List<Unit> getAllUnit(){
		return bdCommonDao.getAllUnit();
	}

	/**
	 * 获取所有商品材质
	 * @return
	 */
	public List<Quality> getAllQuality(){
		return bdCommonDao.getAllQuality();
	}
	/**
	 * 根据款式id获取款式名称
	 * @param styleId
	 * @return
	 */
	public String getStyleNameById(String styleId){
		return bdCommonDao.getStyleNameById(styleId);
	}

	/**
	 * 根据分析范围id获取分析范围名称
	 * @param analysisId
	 * @return
	 */
	public String getAnalysisNameById(String analysisId){
		return bdCommonDao.getAnalysisNameById(analysisId);
	}
	/**
	 * 根据大类小类获取分析范围
	 * @param itemClassId
	 * @param ornaClassId
	 * @return
	 */
	public List<Analysis> getAnalysis(String itemClassId, String ornaClassId){
		return bdCommonDao.getAnalysis(itemClassId, ornaClassId);
	}

	/**
	 * 根据大类、小类获取款式大类
	 * @param itemClassId
	 * @param ornaClassId
	 * @return
	 */
	public List<StyleItemClass> getStyleItemClass(String itemClassId, String ornaClassId){
		return bdCommonDao.getStyleItemClass(itemClassId, ornaClassId);
	}

	/**
	 * 根据大类小类款式大类获取款式中类
	 * @param styleItemClassId
	 * @return
	 */
	public List<StyleMiddleClass> getStyleMiddleClass(String itemClassId, String ornaClassId, String styleItemClassId){
		return bdCommonDao.getStyleMiddleClass(itemClassId, ornaClassId, styleItemClassId);
	}

	/**
	 * 根据大类小类款式大类款式中类获取款式小类
	 * @param styleMiddleClassId
	 * @return
	 */
	public List<StyleOrnaClass> getStyleOrnaClass(String itemClassId, String ornaClassId, String styleItemClassId, String styleMiddleClassId){
		return bdCommonDao.getStyleOrnaClass(itemClassId, ornaClassId, styleItemClassId, styleMiddleClassId);
	}
	/**
	 * 根据大类小类款式大类、款式中类、款式小类获取款式
	 * @param styleItemClassId
	 * @param styleMiddleClassId
	 * @param styleOrnaClassId
	 * @return
	 */
	public List<Style> getStyle(String itemClassId, String ornaClassId, String styleItemClassId, String styleMiddleClassId, String styleOrnaClassId){
		return bdCommonDao.getStyle(itemClassId, ornaClassId, styleItemClassId, styleMiddleClassId, styleOrnaClassId);
	}

	public List<Style> getStyle(String itemClassId, String ornaClassId, String styleItemClassId){
		return bdCommonDao.getStyle(itemClassId, ornaClassId, styleItemClassId);
	}
	/**
	 * 获取所有供应商
	 * @return
	 */
	public List<Vendor> getAllVendor(){
		return bdCommonDao.getAllVendor();
	}
	/**
	 * 托架材质下的托架颜色
	 * @param id
	 * @return
	 */
	@Override
	public List<BracketColor> getBracketColorByQualityId(String id) {
		// TODO Auto-generated method stub
		return bdCommonDao.getBracketColorByQualityId(id);
	}
	
	/**
	 * 根据供应商ID返回供应商
	 * @param CustId
	 * @return
	 */
	@Override
	public Cust getCustById(String custId){
		return bdCommonDao.getCustById(custId);
	}
	/**
	 * 款式下的材质
	 * @param styleId
	 * @return
	 */
	@Override
	public List<Quality> getQualityByStyleId(String styleId){
		return bdCommonDao.getQualityByStyleId(styleId);
	}
	/**
	 * 款式 材质下的托架颜色
	 * @param styleId
	 * @param qualityId
	 * @return
	 */
	@Override
	public List<BracketColor> getColorByQualityId(String styleId,String qualityId){
		return bdCommonDao.getColorByQualityId(styleId, qualityId);
	}

	@Override
	public Style getStyleByIdAndVendor(String styleId,String vendorId) {
		return bdCommonDao.getStyleByIdAndVendor(styleId ,vendorId);
	}

	@Override
	public String getChargeByNum(String value) {
		// TODO Auto-generated method stub
		return bdCommonDao.getChargeByNum(value);
	}

	@Override
	public String getGoldByCalc(String itemClassId, String ornaClassId,
			String qualityId, String orgId) {
		return bdCommonDao.getGoldByCalc(itemClassId, ornaClassId, qualityId, orgId);
	}
	/**
	 * 大小类计量单位获取标签类型
	 * @param itemClassId
	 * @param ornaClassId
	 * @param unitId
	 * @return
	 */
	public List<LabelType> getLabelTypeByCalc(String itemClassId,String ornaClassId,String unitId){
		return bdCommonDao.getLabelTypeByCalc(itemClassId, ornaClassId, unitId);
	}
	public List<com.jatools.vo.basic.LabelType> getNewLabelTypeByCalc(String itemClassId,String ornaClassId,String unitId,String privateType){
		return bdCommonDao.getNewLabelTypeByCalc(itemClassId, ornaClassId, unitId, privateType);
	}
	/**
	 * 根据款式id获取款式对象信息
	 * @param styleId
	 * @return
	 */
	public Style getStyleById(String styleId){
		return bdCommonDao.getStyleById(styleId);
	}

	@Override
	public List<Size> getSizeByItemIdAndOrnaId(String itemId, String ornaId) {
		return bdCommonDao.getSizeByItemIdAndOrnaId( itemId,  ornaId);
	}
	/**
	 * 佩戴对象列表
	 * @return
	 */
	public List<Wear> getAllWear(){
		return bdCommonDao.getAllWear();
	}
	/**
	 * 大小类材质获取 饰品名
	 * @param itemClassId
	 * @param ornaClassId
	 * @param qualityId
	 * @return
	 */
	public Ornadsc getOrnadscByItemIdAndOrnaId(String itemClassId,String ornaClassId,String qualityId){
		return bdCommonDao.getOrnadscByItemIdAndOrnaId(itemClassId, ornaClassId, qualityId);
	}
	public OrnaDesc getNewOrnadscByItemIdAndOrnaId(String itemClassId,String ornaClassId,String qualityId){
		return bdCommonDao.getNewOrnadscByItemIdAndOrnaId(itemClassId, ornaClassId, qualityId);
	}

	@Override
	public List<Gold> getGoldByIniv(String itemClassId,String ornaClassId,String orgId) {
		return bdCommonDao.getGoldByIniv(itemClassId, ornaClassId, orgId);
	}

	@Override
	public String getSizeNameById(String sizeId) {
		return bdCommonDao.getSizeNameById(sizeId);
	}

	@Override
	public List<Summary> getAllSummary() {
		return bdCommonDao.getAllSummary();
	}

	@Override
	public List<ItemClass> getItemClassByMaterialType(String materialType) {
		return this.bdCommonDao.getItemClassByMaterialType(materialType);
	}

	@Override
	public String getCoefficientBySS(Map<String, String> condition) {
		return this.bdCommonDao.getCoefficientBySS(condition);
	}
	/**
	 * 获取所有周期类型
	 * @return
	 */
	public List<SelectorOption> getAllCycleTypeForSlt(){
		return this.bdCommonDao.getAllCycleTypeForSlt();
	}
	/**
	 * 获取所有区域
	 * @return
	 */
	public List<SelectorOption> getAllRegionForSlt(){
		return this.bdCommonDao.getAllRegionForSlt();
	}

	@Override
	public List<Quality> getQualityByItemClassId(String itemClassId) {
		// TODO Auto-generated method stub
		return this.bdCommonDao.getQualityByItemClassId(itemClassId);
	}

	@Override
	public String getMinChargeByNum() {
		return this.bdCommonDao.getMinChargeByNum();
	}
	/**
	 * 获取所有主题款
	 * @return
	 */
	public List<SelectorOption> getAllStyleTheme(){
		return this.bdCommonDao.getAllStyleTheme();
	}

	@Override
	public String getStyleThemeName(String styleId) {
		return this.bdCommonDao.getStyleThemeName(styleId);
	}
	public Pager getStyleByCalc(Map<String, String> condition ){
		return this.bdCommonDao.getStyleByCalc(condition);
	}
	/**
	 * 根据获取人员
	 */
	public Pager getExeOrgEmp(String orgId, String start, String limit){
		return this.bdCommonDao.getExeOrgEmp(orgId, start, limit);
	}
	/**
	 * 根据大类小类分析范围获取款式
	 * @param itemClassId
	 * @param ornaClassId
	 * @param analysisId
	 * @return
	 */
	public List<Style> getStyle2(String itemClassId, String ornaClassId, String analysisId){
		return this.bdCommonDao.getStyle2(itemClassId, ornaClassId, analysisId);
	}
	/**
	 * 获取色级品质
	 * @param colorGradeId
	 * @return
	 */
	public SelectorOption getGradeByColorGradeId(String colorGradeId){
		return this.bdCommonDao.getGradeByColorGradeId(colorGradeId);
	}
	/**
	 *  获取净度品质
	 * @param cleanId
	 * @return
	 */
	public SelectorOption getGradeByCleanId(String cleanId){
		return this.bdCommonDao.getGradeByCleanId(cleanId);
	}

	@Override
	public List<SelectorOption> getMaintainClassForSlt(String maintaintype) {
		return this.bdCommonDao.getMaintainClassForSlt(maintaintype);
	}

	@Override
	public String getPtChargeByNum(String value) {
		return this.bdCommonDao.getPtChargeByNum(value);
	}

	@Override
	public String getPtMaxChargeByNum() {
		return this.bdCommonDao.getPtMaxChargeByNum();
	}
}
