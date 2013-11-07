package com.jatools.dao.calc.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.calc.PriceHeadDao;
import com.jatools.vo.bd.ArticleType;
import com.jatools.vo.bd.ItemClass;
import com.jatools.vo.bd.Style;
import com.jatools.vo.calc.PriceAccLine;
import com.jatools.vo.calc.PriceAcsLine;
import com.jatools.vo.calc.PriceHead;
import com.jatools.vo.calc.PriceSbraLine;
import com.jatools.vo.calc.PriceStoneLine;
import com.jatools.vo.calc.SpecialCharge;
import com.jatools.vo.out.CashProdAccount;
import com.jatools.vo.out.CashProdUser;
import com.jatools.vo.pur.HandoverHead;
import com.jatools.vo.pur.PurchaseHead;
import com.jatools.vo.stock.MaterActive;
import com.jatools.vo.stock.MaterIniv;
import com.jatools.vo.stock.MaterNoActive;
import com.jatools.web.util.DateUtil;
@SuppressWarnings("unchecked")
public class PriceHeadDaoImpl extends BaseDao implements PriceHeadDao {

	@Override
	public Pager getPriceHeadPageData(Map<String, String> condition) {
		
		return executeQueryForPager("PriceHead.getPageData", "PriceHead.getTotalCount", condition);
	}

	@Override
	public Pager getHandoerCalcListPage(Map<String, String> condition) {
		if("4".equals(condition.get("ininvTypeId"))){
			condition.remove("ininvTypeId");
		}
		return executeQueryForPager("PriceHead.getHandoerCalcListPage", "PriceHead.getHandoerCalcListPageTotalCount", condition);
	}
	
	@Override
	public String savePriceHead(PriceHead head) {
		return (String) executeInsert("PriceHead.saveHead", head);
	}

	@Override
	public void updatePriceHead(PriceHead head) {
		
		executeUpdate("PriceHead.updateHead", head);
	}

	@Override
	public void deleteLineByHeadid(String headid) {
		executeUpdate("PriceHead.deleteAccLineById", headid);
		executeUpdate("PriceHead.deleteAcsLineById", headid);
		executeUpdate("PriceHead.deleteSbraLineById", headid);
		executeUpdate("PriceHead.deleteStoneLineById", headid);
		
	}

	@Override
	public void savePriceAccLine(List<PriceAccLine> lineList) {
		executeBatchInsert("PriceHead.saveAcc", lineList);
	}

	public void updatePriceAccLine(List<PriceAccLine> lineList) {
		executeBatchUpdate("PriceHead.updateAcc", lineList);
	}
	
	@Override
	public void savePriceAcsLine(List<PriceAcsLine> lineList) {
		executeBatchInsert("PriceHead.saveAcs", lineList);
	}

	public void updatePriceAcsLine(List<PriceAcsLine> lineList) {
		executeBatchUpdate("PriceHead.updateAcs", lineList);
	}
	
	@Override
	public void savePriceSbraLine(List<PriceSbraLine> lineList) {
		executeBatchInsert("PriceHead.saveSbra", lineList);
	}
	
	public void updatePriceSbraLine(List<PriceSbraLine> lineList) {
		executeBatchUpdate("PriceHead.updateSbra", lineList);
	}

	@Override
	public void savePriceStoneLine(List<PriceStoneLine> lineList) {
		executeBatchInsert("PriceHead.saveStone", lineList);
	}

	@Override
	public PriceHead getPriceHead(String headid) {
		
		return (PriceHead)executeQueryForObject("PriceHead.PriceHeadById", headid);
	}

	@Override
	public List<PriceAccLine> getPriceAccLineList(String headid) {
		
		return executeQueryForList("PriceHead.PriceAccByHeadId", headid);
	}

	@Override
	public List<PriceAcsLine> getPriceAcsLineList(String headid) {
		
		return executeQueryForList("PriceHead.PriceAcsByHeadId", headid);
	}

	@Override
	public List<PriceSbraLine> getPriceSbraLineList(String headid) {
		
		return executeQueryForList("PriceHead.PriceSbraByHeadId", headid);
	}

	@Override
	public List<PriceStoneLine> getPriceStoneLineList(String headid) {
		
		return executeQueryForList("PriceHead.PriceStoneByHeadId", headid);
	}

	@Override
	public void deletePriceHead(String headid) {
		executeUpdate("PriceHead.deleteHeadById", headid);
		
	}
	
	/**
	 * 核价交接单列表
	 * @param ininvTypeId 交接单行表下大类的入库类型，状态为审核完成
	 * @param orgId
	 * @return
	 */
	@Override
	public List<HandoverHead> getHandoerCalcList(String ininvTypeId,String orgId){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("ininvTypeId", ininvTypeId);
		condition.put("orgId", orgId);
		return executeQueryForList("BdCommon.getHandoerCalcList", condition);
	}
	/**
	 * 交接单取交接单行表下指定的入库核价类型的大类
	 * @param headId 交接单ID
	 * @param ininvTypeId 入库核价类型
	 * @return
	 */
	public List<ItemClass> getHandoerItemClassByHeadId(String headId,String ininvTypeId,String articleTypeId,String type){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headId", headId);
		condition.put("type", type);
		condition.put("articleTypeId", articleTypeId);
		if(!"4".equals(ininvTypeId))
			condition.put("ininvTypeId", ininvTypeId);
		return executeQueryForList("BdCommon.getHandoerItemClassByHeadId", condition);
	}
	/**
	 * 交接单取交接单行表下指定的入库核价类型的商品类别
	 * @param headId 交接单ID
	 * @param ininvTypeId 入库核价类型
	 * @return
	 */
	public List<ArticleType> getArticleTypeClassByHeadId(String headId,String ininvTypeId,String itemClassId,String type){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headId", headId);
		condition.put("itemClassId", itemClassId);
		condition.put("type", type);
		condition.put("ininvTypeId", ininvTypeId);
		return executeQueryForList("BdCommon.getArticleTypeClassByHeadId", condition);
	}
	/**
	 * 大小类，供应商下的款式
	 * @param itemClassId
	 * @param ornaClassId
	 * @param custId
	 * @return
	 */
	public Pager getStyleByCalc(Map<String, String> condition ){
		return executeQueryForPager("BdCommon.getStyleByCalc", "BdCommon.getStyleByCalcTotalCount", condition);
	}

	/**
	 * 查找核价单
	 * @param handoverBillId
	 * @param inivType
	 * @param itemClassId
	 * @param ornaClassId
	 * @param styleId
	 * @return
	 */
	@Override
	public List<PriceHead> loadPriceByIniv(String handoverBillId,String inivType,
			String itemClassId,String ornaClassId,String styleId,String id){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("handoverBillId", handoverBillId);
		condition.put("inivType", inivType);
		condition.put("itemClassId", itemClassId);
		condition.put("ornaClassId", ornaClassId);
		condition.put("styleId", styleId);
		condition.put("id", id);
		return executeQueryForList("PriceHead.loadPriceByIniv", condition);
	}

	@Override
	public Style getStyleByToStyle(Map<String, String> condition) {
		return (Style) executeQueryForObject("BdCommon.getStyleByToStyle", condition);
	}

	@Override
	public String getBasicPriceCoefficient(Map<String, String> condition) {
		return (String) executeQueryForObject("Pricehead.getBasicPriceCoefficient", condition);
	}

	@Override
	public Pager loadPriceByInivPage(Map<String, String> condition) {
		return  executeQueryForPager("PriceHead.loadPriceByInivPage", "PriceHead.loadPriceByInivPageTotalCount", condition);
	}

	@Override
	public PriceHead getPriceHeadByNo(String headid) {
		return (PriceHead)executeQueryForObject("PriceHead.PriceHeadByNo", headid);
	}

	@Override
	public MaterNoActive getMaterNoActiveByOrnaCode(String ornaCode) {
		return (MaterNoActive) executeQueryForObject("Pricehead.getMaterNoActiveByOrnaCode", ornaCode);
	}
	@Override
	public MaterActive getMaterActiveByOrnaCode(String ornaCode) {
		return (MaterActive) executeQueryForObject("Pricehead.getMaterActiveByOrnaCode", ornaCode);
	}

	@Override
	public String getCalcCertPrice(Map<String, String> condition) {
		return (String) executeQueryForObject("PriceHead.getCalcCertPrice", condition);
	}

	@Override
	public String getCalcStoneMainCoefficient(Map<String, String> condition) {
		return (String) executeQueryForObject("PriceHead.getCalcStoneMainCoefficient", condition);
	}

	@Override
	public PriceStoneLine getMainStoneLineByHeadId(String headId) {
		return (PriceStoneLine)executeQueryForObject("PriceHead.getMainStoneLineByHeadId", headId);
	}

	@Override
	public String getEmpInivCalcCode(String userId, String type) {
		if("1".equals(type)){
			return (String) executeQueryForObject("PriceHead.getInivCode", userId);
		}else{
			return (String) executeQueryForObject("PriceHead.getCaclCode", userId);
		}
	}

	@Override
	public List<PurchaseHead> getDZcode(Map<String, String> condition) {
		return  executeQueryForList("PriceHead.getDZcode", condition);
	}

	@Override
	public String getUnitPrice(Map<String, String> condition) {
		return (String)executeQueryForObject("PriceHead.getUnitPrice", condition);
	}

	@Override
	public CashProdAccount getZUNums(Map<String, String> condition) {
		return (CashProdAccount)executeQueryForObject("PriceHead.getZUNums", condition);
	}

	@Override
	public List<CashProdAccount> getStoneNum(Map<String, String> condition) {
		return executeQueryForList("PriceHead.getStoneNum", condition);
	}

	@Override
	public List<PriceStoneLine> getPriceStoneNum(Map<String, String> condition) {
		return executeQueryForList("Pricehead.getPriceStoneNum", condition);
	}

	@Override
	public String getVendorCoefficient(Map<String, String> condition) {
		// TODO Auto-generated method stub
		return (String)executeQueryForObject("PriceHead.getVendorCoefficient", condition);
	}

	@Override
	public List<CashProdAccount> getAccountByCode(String code) {
		return executeQueryForList("PriceHead.getAccountByCode", code);
	}

	@Override
	public void saveAccountUser(CashProdUser user) {
		executeInsert("PriceHead.saveAccountUser", user);
	}

	@Override
	public void deleteUserById(String id) {
		executeUpdate("PriceHead.deleteUserById", id);
	}

	@Override
	public void updateUserNumsById(String id) {
		executeUpdate("PriceHead.updateUserNumsById", id);
	}

	@Override
	public SpecialCharge getSpecialChargeNum(String weight) {
		return (SpecialCharge)executeQueryForObject("PriceHead.getSpecialChargeNum", weight);
	}

	@Override
	public String getProdUseraNumBySid(String id) {
		return (String) executeQueryForObject("PriceHead.getProdUseraNumBySid", id);
	}

	@Override
	public List<MaterIniv> getTLCodeById(String headId) {
		return this.getTLCodeById(headId, null);
	}

	@Override
	public List<MaterIniv> getLTXTCodeById(String headId) {
		return this.getLTXTCodeById(headId, null);
	}
	
	@Override
	public List<MaterIniv> getTLCodeById(String headId, String ornaCode) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("billid", headId);
		condition.put("ornaCode", ornaCode);
		return executeQueryForList("PriceHead.getTLCodeById", condition);
	}

	
	@Override
	public List<MaterIniv> getLTXTCodeById(String headId, String ornaCode) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("billid", headId);
		condition.put("ornaCode", ornaCode);
		return executeQueryForList("PriceHead.getLTXTCodeById", condition);
	}
	
	@Override
	public String getMaterInivCountByWlCode(String wlCode) {
		return (String) executeQueryForObject("PriceHead.getMaterInivCountByWlCode", wlCode);
	}

	@Override
	public void updateMaterInivCustomizeNoByWlCode(String dzCode, String WlCode) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("dzCode", dzCode);
		condition.put("wlCode", WlCode);
		executeUpdate("PriceHead.updateMaterInivCustomizeNoByWlCode", condition);
	}

	@Override
	public void updateMaterActionCustomizeNoByWlCode(String dzCode,
			String WlCode) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("dzCode", dzCode);
		condition.put("wlCode", WlCode);
		executeUpdate("PriceHead.updateMaterActionCustomizeNoByWlCode", condition);
	}

	@Override
	public String getVerdorChargeNum(String verdorId, String styleId) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("verdorId", verdorId);
		condition.put("styleId", styleId);
		return (String) executeQueryForObject("PriceHead.getVerdorChargeNum", condition);
	}
	
	@Override
	public void savePriceStoneLine(PriceStoneLine line) {
		executeInsert("PriceHead.saveStone", line);
	}
	
	@Override
	public void updatePriceStoneLine(PriceStoneLine line) {
		executeUpdate("PriceHead.updateStone", line);
	}
	
	public void deleteStoneLineByLineId(String lineId){
		executeUpdate("PriceHead.deleteStoneLineByLineId", lineId);
	}
	public void deleteSbraLineByLineId(String lineId){
		executeUpdate("PriceHead.deleteSbraLineByLineId", lineId);
	}
	public void deleteAccLineByLineId(String lineId){
		executeUpdate("PriceHead.deleteAccLineByLineId", lineId);
	}
	public void deleteAcsLineByLineId(String lineId){
		executeUpdate("PriceHead.deleteAcsLineByLineId", lineId);
	}
	
	public void insertProdAccountUser(String priceLineId, String prId, String ornaCode, String userNum, String userId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("lineId", priceLineId);
		params.put("prId", prId);
		params.put("ornaCode", ornaCode);
		params.put("userNum", userNum);
		params.put("createId", userId);
		params.put("updateId", userId);
		params.put("createDate", DateUtil.getCurrentDate18());
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeInsert("PriceHead.insertProdAccountUser", params);
	}
}
