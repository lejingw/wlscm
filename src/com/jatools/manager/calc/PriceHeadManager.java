package com.jatools.manager.calc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.bd.ArticleType;
import com.jatools.vo.bd.ItemClass;
import com.jatools.vo.bd.Style;
import com.jatools.vo.bd.Tag;
import com.jatools.vo.calc.PriceAccLine;
import com.jatools.vo.calc.PriceAcsLine;
import com.jatools.vo.calc.PriceHead;
import com.jatools.vo.calc.PriceSbraLine;
import com.jatools.vo.calc.PriceStoneLine;
import com.jatools.vo.calc.SpecialCharge;
import com.jatools.vo.out.CashProdAccount;
import com.jatools.vo.pur.HandoverHead;
import com.jatools.vo.pur.PurchaseHead;
import com.jatools.vo.stock.MaterActive;
import com.jatools.vo.stock.MaterIniv;
import com.jatools.vo.stock.MaterNoActive;

public interface PriceHeadManager {
	/**
	 * 获取核价分页数据
	 * @param condition
	 * @return
	 */
	Pager getPriceHeadPageData(Map<String, String> condition);
	/**
	 * 获取入库核价交接单分页
	 * @param condition
	 * @return
	 */
	Pager getHandoerCalcListPage(Map<String, String> condition);

	/**
	 * 保存核价
	 * @param head
	 * @return
	 */
	String savePriceHead(PriceHead head);

	/**
	 * 更新核价
	 * @param head
	 */
	void updatePriceHead(PriceHead head);

	/**
	 * 删除核价行
	 * @param headid
	 */
	void deleteLineByHeadid(String headid);

	/**
	 * 保存辅料行
	 * @param headid
	 */
	void savePriceAccLine(List<PriceAccLine> lineList);
	/**
	 * 保存配件行
	 * @param headid
	 */
	void savePriceAcsLine(List<PriceAcsLine> lineList);
	/**
	 * 保存托架行
	 * @param headid
	 */
	void savePriceSbraLine(List<PriceSbraLine> lineList);
	/**
	 * 保存石头行
	 * @param headid
	 */
	void savePriceStoneLine(List<PriceStoneLine> lineList);

	/**
	 * 获取头表数据
	 * @param headid
	 * @return
	 */
	public PriceHead getPriceHead(String headid) throws RuntimeException;
	/**
	 * 根据编号获取头表数据
	 * @param headid
	 * @return
	 */
	public PriceHead getPriceHeadByNo(String headid);
	/**
	 * 获取辅料行表数据
	 * @param headid
	 * @return
	 */
	public List<PriceAccLine> getPriceAccLineList(String headid);
	/**
	 * 获取配件行表数据
	 * @param headid
	 * @return
	 */
	public List<PriceAcsLine> getPriceAcsLineList(String headid);
	/**
	 * 获取托架行表数据
	 * @param headid
	 * @return
	 */
	public List<PriceSbraLine> getPriceSbraLineList(String headid);
	/**
	 * 获取石头行表数据
	 * @param headid
	 * @return
	 */
	public List<PriceStoneLine> getPriceStoneLineList(String headid);
	/**
	 * 删除核价头
	 * @param headid
	 */
	void deletePriceHead(String headid);
	
	/**
	 * 保存或修改数据
	 * @param head
	 * @param lineList
	 */
	String saveOrUpdatePrice(PriceHead head, List<PriceStoneLine> stoneLine,
			List<PriceSbraLine> sbraLine,List<PriceAcsLine> acsLine,List<PriceAccLine> accLine);
	/**
	 * 
	 * @param head
	 * @param stoneLine
	 * @param sbraLine
	 * @param acsLine
	 * @param accLine
	 * @param deleteStoneLineIds
	 * @param userId
	 * @return
	 */
	String saveOrUpdatePrice(PriceHead head, List<PriceStoneLine> stoneLine,
			List<PriceSbraLine> sbraLine,List<PriceAcsLine> acsLine,List<PriceAccLine> accLine, String deleteStoneLineIds,
			String deleteSbraLineIds,String deleteAcsLineIds,String deleteAccLineIds,String userId);
	
	/**
	 * 核价交接单列表
	 * @param ininvTypeId 交接单行表下大类的入库类型，状态为审核完成
	 * @param orgId
	 * @return
	 */
	List<HandoverHead> getHandoerCalcList(String ininvTypeId,String orgId);
	/**
	 * 交接单取交接单行表下指定的入库核价类型的大类
	 * @param headId 交接单ID
	 * @param ininvTypeId 入库核价类型
	 * @return
	 */
	List<ItemClass> getHandoerItemClassByHeadId(String headId,String ininvTypeId,String articleTypeId,String type);

	List<ArticleType> getArticleTypeClassByHeadId(String headId,String ininvTypeId,String itemClassId,String type);
	/**
	 * 大小类，供应商下的款式
	 * @param itemClassId
	 * @param ornaClassId
	 * @param CustId
	 * @return
	 */
	Pager getStyleByCalc(Map<String, String> condition);
	/**
	 * 查找核价单
	 * @param handoverBillId
	 * @param inivType
	 * @param itemClassId
	 * @param ornaClassId
	 * @param styleId
	 * @return
	 */
	List<PriceHead> loadPriceByIniv(String handoverBillId,String inivType,
			String itemClassId,String ornaClassId,String styleId,String id);
	/**
	 * 大小类、供应商 、工产款号取款式
	 * @param condition
	 * @return
	 */
	Style getStyleByToStyle(Map<String, String> condition);
	/**
	 * 大小类、分析范围、档级取基础系数
	 * @param condition
	 * @return
	 */
	String getBasicPriceCoefficient(Map<String, String> condition);
	/**
	 * 入库选择核价单列表
	 * @param Pager
	 * @return
	 */
	public Pager loadPriceByInivPage(Map<String, String> condition);
	/**
	 * 编码找非现有量
	 * @param ornaCode
	 * @return
	 */
	MaterNoActive getMaterNoActiveByOrnaCode(String ornaCode);/**
	 * 编码找现有量
	 * @param ornaCode
	 * @return
	 */
	MaterActive getMaterActiveByOrnaCode(String ornaCode);
	/**
	 * 大小类、重量取证书价格
	 * @param condition
	 * @return
	 */
	String getCalcCertPrice(Map<String, String> condition);
	/**
	 * 大小类、重量获取钻石核价石头系数 
	 * @param condition
	 * @return
	 */
	String getCalcStoneMainCoefficient(Map<String, String> condition);
	/**
	 * HeadId 获取主石石头行表
	 * @param headId
	 * @return
	 */
	PriceStoneLine getMainStoneLineByHeadId(String headId);
	/**
	 * 入库核价编码
	 * @param userId
	 * @param type
	 * @return
	 */
	String getEmpInivCalcCode(String userId,String type);
	/**
	 * 标签打印
	 * @param ids
	 * @return
	 */
	List<Tag> printLabels(String[] ids);
	List<MaterIniv> getOldOrnaCodeByHeadId(String headId);
	
	List<MaterIniv> getOldOrnaCodeByHeadId(String headId, String ornaCode);

	List<PurchaseHead> getDZcode(Map<String, String> condition);

	String getUnitPrice(Map<String, String> condition);

	CashProdAccount getZUNums(Map<String, String> condition);
	List<CashProdAccount>getStoneNum(Map<String, String> condition);
	List<PriceStoneLine>getPriceStoneNum(Map<String, String> condition);
	String getVendorCoefficient(Map<String, String> condition);
	/**
	 * 特殊工费（不为高工艺）
	 * @param weight
	 * @return
	 */
	SpecialCharge getSpecialChargeNum(String weight);

	String getMaterInivCountByWlCode(String wlCode);
	
	void updateMaterInivCustomizeNoByWlCode(String dzCode,String WlCode);
	void updateMaterActionCustomizeNoByWlCode(String dzCode,String WlCode);

	String getVerdorChargeNum(String verdorId,String styleId);
}
