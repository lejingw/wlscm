package com.jatools.dao.bd;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
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

public interface BdCommonDao {
	/**
	 * 获取所有系统参数配置
	 */
	public List<Parameter> getAllParameters();
	/**
	 * 获取所有商品类别
	 * @return
	 */
	List<ArticleType> getAllArticleType();
	/**
	 * 获取所有大类
	 * @return
	 */
	List<ItemClass> getAllItemClass();
	/**
	 * 获取所有小类
	 * @return
	 */
	List<OrnaClass> getAllOrnaClass();
	/**
	 * 根据组织类型获取组织树
	 * @return
	 */
	List<Org> getOrgTreeByType(String orgType);
	/**
	 * 获取所有大类小类对应关系数据
	 * @return
	 */
	List<ItemOrnaClass> getAllItemOrnaClass();

	/**
	 * 获取所有款式大类
	 * @return
	 */
	List<StyleItemClass> getAllStyleItemClass();
	/**
	 * ID取款式大类编码 
	 * @return
	 */
	String getStyleItemCodeById(String styleItemId);
	/**
	 * 获取所有款式中类
	 * @return
	 */
	List<StyleMiddleClass> getAllStyleMiddleClass();
	/**
	 * 获取所有款式小类
	 * @return
	 */
	List<StyleOrnaClass> getAllStyleOrnaClass();
	/**
	 * 获取所有计量单位
	 * @return
	 */
	List<Unit> getAllUnit();
	/**
	 * 获取所有商品材质
	 * @return
	 */
	List<Quality> getAllQuality();

	/**
	 * 根据款式id获取款式名称
	 * @param styleId
	 * @return
	 */
	String getStyleNameById(String styleId);

	/**
	 * 根据分析范围id获取分析范围名称
	 * @param analysisId
	 * @return
	 */
	String getAnalysisNameById(String analysisId);
	
	/**
	 * 根据大类小类获取分析范围
	 * @param itemClassId
	 * @param ornaClassId
	 * @return
	 */
	List<Analysis> getAnalysis(String itemClassId, String ornaClassId);

	/**
	 * 根据大类、小类获取款式大类
	 * @param itemClassId
	 * @param ornaClassId
	 * @return
	 */
	List<StyleItemClass> getStyleItemClass(String itemClassId, String ornaClassId);

	/**
	 * 根据大类小类款式大类获取款式中类
	 * @param styleItemClassId
	 * @return
	 */
	List<StyleMiddleClass> getStyleMiddleClass(String itemClassId, String ornaClassId, String styleItemClassId);

	/**
	 * 根据大类小类款式大类款式中类获取款式小类
	 * @param styleMiddleClassId
	 * @return
	 */
	List<StyleOrnaClass> getStyleOrnaClass(String itemClassId, String ornaClassId, String styleItemClassId, String styleMiddleClassId);
	/**
	 * 根据大类小类款式大类、款式中类、款式小类获取款式
	 * @param styleItemClassId
	 * @param styleMiddleClassId
	 * @param styleOrnaClassId
	 * @return
	 */
	List<Style> getStyle(String itemClassId, String ornaClassId, String styleItemClassId, String styleMiddleClassId, String styleOrnaClassId);
	List<Style> getStyle(String itemClassId, String ornaClassId, String styleItemClassId);
	/**
	 * 获取所有供应商
	 * @return
	 */
	List<Vendor> getAllVendor();
	
	/**
	 * 托架材质下的托架颜色
	 * @param id
	 * @return
	 */
	List<BracketColor> getBracketColorByQualityId(String id);
	
	/**
	 * 根据供应商ID返回供应商
	 * @param custId
	 * @return
	 */
	Cust getCustById(String custId);
	/**
	 * 款式下的材质
	 * @param styleId
	 * @return
	 */
	List<Quality> getQualityByStyleId(String styleId);
	/**
	 * 款式 材质下的托架颜色
	 * @param styleId
	 * @param qualityId
	 * @return
	 */
	List<BracketColor> getColorByQualityId(String styleId,String qualityId);
	/**
	 * 根据款式ID得到款式对象
	 * @param styleId
	 * @return
	 */
	Style getStyleByIdAndVendor(String styleId,String custId);
	/**
	 * 取特殊工费
	 * @param value
	 * @return
	 */
	String getChargeByNum(String value);
	String getMinChargeByNum();
	/**
	 * pt950取特殊工费
	 * @param value
	 * @return
	 */
	String getPtChargeByNum(String value);
	String getPtMaxChargeByNum();
	/**
	 * 查找金价
	 * @param itemClassId
	 * @param ornaClassId
	 * @param qualityId
	 * @param orgId
	 * @return
	 */
	String getGoldByCalc(String itemClassId,String ornaClassId,String qualityId,String orgId);
	/**
	 * 大小类计量单位获取标签类型
	 * @param itemClassId
	 * @param ornaClassId
	 * @param unitId
	 * @return
	 */
	List<LabelType> getLabelTypeByCalc(String itemClassId,String ornaClassId,String unitId);
	List<com.jatools.vo.basic.LabelType> getNewLabelTypeByCalc(String itemClassId,String ornaClassId,String unitId,String privateType);
	
	/**
	 * 根据款式id获取款式对象信息
	 * @param styleId
	 * @return
	 */
	public Style getStyleById(String styleId);
	/**
	 * 大小类的尺寸
	 * @param itemId
	 * @param ornaId
	 * @return
	 */
	List<Size> getSizeByItemIdAndOrnaId(String itemId,String ornaId);
	/**
	 * 佩戴对象列表
	 * @return
	 */
	List<Wear> getAllWear();
	/**
	 * 大小类材质获取 饰品名
	 * @param itemClassId
	 * @param ornaClassId
	 * @param qualityId
	 * @return
	 */
	Ornadsc getOrnadscByItemIdAndOrnaId(String itemClassId,String ornaClassId,String qualityId);
	OrnaDesc getNewOrnadscByItemIdAndOrnaId(String itemClassId,String ornaClassId,String qualityId);
	/**
	 * 查找价格属性组
	 * @param itemClassId
	 * @param ornaClassId
	 * @param orgId
	 * @return
	 */
	List<Gold> getGoldByIniv(String itemClassId,String ornaClassId,String orgId);
	/**
	 * 尺寸ID找Name
	 * @param sizeId
	 * @return
	 */
	String getSizeNameById(String sizeId);
	/**
	 * 全部常用摘要
	 * @return
	 */
	List<Summary> getAllSummary();
	
	/**
	 * 根据原料类型查询 大类
	 * @param materialType
	 * @return
	 */
	List<ItemClass> getItemClassByMaterialType(String materialType);
	/**
	 * 大类找时尚千足金系数
	 * @param condition
	 * @return
	 */
	String getCoefficientBySS(Map<String, String> condition);
	/**
	 * 获取所有周期类型
	 * @return
	 */
	List<SelectorOption> getAllCycleTypeForSlt();
	/**
	 * 获取所有区域
	 * @return
	 */
	List<SelectorOption> getAllRegionForSlt();
	List<Quality> getQualityByItemClassId(String itemClassId);
	/**
	 * 获取所有主题款
	 * @return
	 */
	List<SelectorOption> getAllStyleTheme();
	String getStyleThemeName(String styleId);
	Pager getStyleByCalc(Map<String, String> condition );
	/**
	 * 根据行政组织获取人员
	 */
	Pager getExeOrgEmp(String orgId, String start, String limit);
	/**
	 * 根据大类小类分析范围获取款式
	 * @param itemClassId
	 * @param ornaClassId
	 * @param analysisId
	 * @return
	 */
	List<Style> getStyle2(String itemClassId, String ornaClassId, String analysisId);
	/**
	 *  原料类型取耗料
	 * @return
	 */
	List<SelectorOption> getMaintainClassForSlt(String maintaintype);
	/**
	 * 获取色级品质
	 * @param colorGradeId
	 * @return
	 */
	SelectorOption getGradeByColorGradeId(String colorGradeId);
	/**
	 *  获取净度品质
	 * @param cleanId
	 * @return
	 */
	SelectorOption getGradeByCleanId(String cleanId);

}