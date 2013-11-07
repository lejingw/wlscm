package com.jatools.web.dwr.calc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.manager.calc.AccessoryManager;
import com.jatools.manager.calc.PriceHeadManager;
import com.jatools.manager.calc.SbraTypeManager;
import com.jatools.manager.pur.HandoverManager;
import com.jatools.manager.util.CommonManager;
import com.jatools.vo.bd.Analysis;
import com.jatools.vo.bd.ArticleType;
import com.jatools.vo.bd.ItemClass;
import com.jatools.vo.bd.Style;
import com.jatools.vo.calc.Accessory;
import com.jatools.vo.calc.PriceAccLine;
import com.jatools.vo.calc.PriceAcsLine;
import com.jatools.vo.calc.PriceHead;
import com.jatools.vo.calc.PriceSbraLine;
import com.jatools.vo.calc.PriceStoneLine;
import com.jatools.vo.calc.SbraTypeHead;
import com.jatools.vo.calc.SbraTypeLine;
import com.jatools.vo.calc.SpecialCharge;
import com.jatools.vo.out.CashProdAccount;
import com.jatools.vo.pur.HandoverHead;
import com.jatools.vo.pur.PurchaseHead;
import com.jatools.vo.stock.MaterActive;
import com.jatools.vo.stock.MaterIniv;
import com.jatools.vo.stock.MaterNoActive;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.form.basic.StyleForm;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class PriceHeadDwr {
	private Logger logger = Logger.getLogger(PriceHeadDwr.class);
	private PriceHeadManager priceHeadManager;
	private AccessoryManager accessoryManager;
	private HandoverManager handoverManager;
	private CommonManager commonManager;
	
	
	public void setHandoverManager(HandoverManager handoverManager) {
		this.handoverManager = handoverManager;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	public void setPriceHeadManager(PriceHeadManager priceHeadManager) {
		this.priceHeadManager = priceHeadManager;
	}	
	public void setAccessoryManager(AccessoryManager accessoryManager) {
		this.accessoryManager = accessoryManager;
	}
	public void setCommonManager(CommonManager commonManager) {
		this.commonManager = commonManager;
	}
	/**
	 * 删除核价单
	 * @param headid
	 * @return
	 */
	public String deletePriceHead(String id){
		try {
			if(StringUtil.isEmpty(id)){
				return "不能获取核价单id";
			}
			PriceHead old = this.priceHeadManager.getPriceHead(id);
			if(old==null){
				return "单据已被删除,请刷新页面";
			}else if(!"1".equals(old.getState())){
				return "单据状态已被修改,请刷新页面";
			}
			priceHeadManager.deleteLineByHeadid(id);
			return null;
		} catch (Exception e) {
			logger.error(e);
			return "删除出错";
		}
	}
	/**
	 * ID查找辅料
	 * @param id
	 * @return
	 */
	public Accessory getAccessoryById(String id){
		if("".equals(id))
			return null;
		return accessoryManager.getAccessoryById(id);
	}
	/**
	 * 保存或修改数据
	 * @param head
	 * @param lineList
	 */
	public String saveOrUpdatePrice(PriceHead head, List<PriceStoneLine> stoneLine,
			List<PriceSbraLine> sbraLine,List<PriceAcsLine> acsLine,List<PriceAccLine> accLine,
			HttpServletRequest req){
		HttpSession session = req.getSession();
		Analysis A = commonManager.getAnalysis(head.getItemClassId(), head.getOrnaClassId(),
				(head.getAllWeight()==null||head.getAllWeight()==0)?"0":""+head.getAllWeight(),
				(head.getBasicPrice()==null||head.getBasicPrice()==0)?"0":""+head.getBasicPrice(),
				(head.getMainWeight()==null||head.getMainWeight()==0)?"0":""+head.getMainWeight()); 
		if(null!=A){
			if(null==A.getAnalysisId())
				return "0_分析没有维护"; 
			head.setAnalysisArangeId(A.getAnalysisId());
		}else
			return "0_分析没有维护";
		
		if(StringUtil.isEmpty(head.getId())){
			String priceCode = this.priceHeadManager.getEmpInivCalcCode(CommonUtil.getSessionUserId(req), "2");
			head.setWlCode(commonManager.getOrnaCode(priceCode,head.getItemClassId(), head.getOrnaClassId()));
			head.setOrnaBarCode(commonManager.getBarCode(head.getItemClassId(), head.getOrnaClassId()));
			head.setOrgId(CommonUtil.getSessionOrgId(session));
			head.setCreateDate(DateUtil.getCurrentDate18());
			head.setCreateUserId(CommonUtil.getSessionUserId(req));
			head.setNo(commonManager.getBillno(GlobalConstant.BILL_CODE_PRICEHEAD));
			head.setState(DictConstant.BILL_STATUS_SAVE);
		}else{
			PriceHead old = this.priceHeadManager.getPriceHead(head.getId());
			if(old==null){
				return "0_单据已被删除,请返回列表页面";
			}else if(!"1".equals(old.getState())){
				return "0_单据状态已被修改,请刷新页面";
			}
			head.setUpdateDate(DateUtil.getCurrentDate18());
		}
		String result="";
		try {
			result = priceHeadManager.saveOrUpdatePrice(head, stoneLine, sbraLine, acsLine, accLine);
		} catch (Exception e) {
			logger.error(e);
			return "0_"+e.getMessage();
		}
		return "1_保存成功_"+result+"_"+head.getNo();
	}
	
	public String saveOrUpdatePrice(PriceHead head, List<PriceStoneLine> stoneLine,
			List<PriceSbraLine> sbraLine,List<PriceAcsLine> acsLine,List<PriceAccLine> accLine, String deleteStoneLineIds,
			String deleteSbraLineIds,String deleteAcsLineIds,String deleteAccLineIds,
			HttpServletRequest req){
		HttpSession session = req.getSession();
		Analysis A = commonManager.getAnalysis(head.getItemClassId(), head.getOrnaClassId(),
				(head.getAllWeight()==null||head.getAllWeight()==0)?"0":""+head.getAllWeight(),
				(head.getBasicPrice()==null||head.getBasicPrice()==0)?"0":""+head.getBasicPrice(),
				(head.getMainWeight()==null||head.getMainWeight()==0)?"0":""+head.getMainWeight()); 
		if(null!=A){
			if(null==A.getAnalysisId())
				return "0_分析没有维护"; 
			head.setAnalysisArangeId(A.getAnalysisId());
		}else
			return "0_分析没有维护";
		
		if(StringUtil.isEmpty(head.getId())){
			String priceCode = this.priceHeadManager.getEmpInivCalcCode(CommonUtil.getSessionUserId(req), "2");
			head.setWlCode(commonManager.getOrnaCode(priceCode,head.getItemClassId(), head.getOrnaClassId()));
			head.setOrnaBarCode(commonManager.getBarCode(head.getItemClassId(), head.getOrnaClassId()));
			head.setOrgId(CommonUtil.getSessionOrgId(session));
			head.setCreateDate(DateUtil.getCurrentDate18());
			head.setCreateUserId(CommonUtil.getSessionUserId(req));
			head.setNo(commonManager.getBillno(GlobalConstant.BILL_CODE_PRICEHEAD));
			head.setState(DictConstant.BILL_STATUS_SAVE);
		}else{
			PriceHead old = this.priceHeadManager.getPriceHead(head.getId());
			if(old==null){
				return "0_单据已被删除,请返回列表页面";
			}else if(!"1".equals(old.getState())){
				return "0_单据状态已被修改,请刷新页面";
			}
			head.setUpdateDate(DateUtil.getCurrentDate18());
		}
		String result="";
		try {
			result = priceHeadManager.saveOrUpdatePrice(head, stoneLine, sbraLine, acsLine, accLine, 
					deleteStoneLineIds, deleteSbraLineIds,deleteAcsLineIds,deleteAccLineIds,CommonUtil.getSessionUserId(req));
		} catch (Exception e) {
			logger.error(e);
			return "0_"+e.getMessage();
		}
		return "1_保存成功_"+result+"_"+head.getNo();
	}
	
	/**
	 * 获取分析范围
	 * @param itemClassId
	 * @param ornaClassId
	 * @param allWeight
	 * @param basicPrice
	 * @param mainWeight
	 * @return
	 */
	public Analysis getAnalysisArange(String itemClassId,String ornaClassId,String allWeight,String basicPrice,String mainWeight){
		Analysis A = commonManager.getAnalysis(itemClassId, ornaClassId,
				(allWeight==null?"0":allWeight),
				(basicPrice==null?"0":basicPrice),
				(mainWeight==null?"0":mainWeight)); 
		return A;
	}
	/**
	 * 核价交接单列表
	 * @param ininvTypeId 交接单行表下大类的入库类型，状态为接收
	 * @return
	 */
	public List<SelectorOption> getHandoerCalcList(String ininvTypeId,HttpServletRequest req){
		List<SelectorOption> sltList=null;
		try {
			List<HandoverHead> list = priceHeadManager.getHandoerCalcList(ininvTypeId, CommonUtil.getSessionOrgId(req.getSession()));
			sltList = new ArrayList<SelectorOption>();
			for(HandoverHead bc : list){
				sltList.add(new SelectorOption(bc.getBillid()+"_"+bc.getVerdorId()+"_"+bc.getDotype(), bc.getBillno()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sltList;
	}
	/**
	 * 交接单取交接单行表下指定的入库核价类型的大类
	 * @param headId 交接单ID
	 * @param ininvTypeId 入库核价类型
	 * @return
	 */
	public List<SelectorOption> getHandoerItemClassByHeadId(String headId,String ininvTypeId,String articleTypeId,String type){
		List<SelectorOption> sltList=null;
		try {
			List<ItemClass> list = priceHeadManager.getHandoerItemClassByHeadId(headId, ininvTypeId,articleTypeId,type);
			sltList = new ArrayList<SelectorOption>();
			for(ItemClass bc : list){
				sltList.add(new SelectorOption(bc.getItemClassId(), bc.getItemClassDesc()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sltList;
	}
	/**
	 * 其它交接单
	 * @param headId
	 * @return
	 */
	public List<SelectorOption> getOldOrnaCodeByHeadId(String headId){
		List<SelectorOption> sltList=null;
		try {
			List<MaterIniv> list = priceHeadManager.getOldOrnaCodeByHeadId(headId);
			sltList = new ArrayList<SelectorOption>();
			for(MaterIniv bc : list){
				sltList.add(new SelectorOption(bc.getOrnaCode(),bc.getOrnaCode()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sltList;
	}
	
	
	/**
	 * 交接单取交接单行表下指定的入库核价类型的商品类型
	 * @param headId 交接单ID
	 * @param ininvTypeId 入库核价类型
	 * @return
	 */
	public List<SelectorOption> getArticleTypeClassByHeadId(String headId,String ininvTypeId,String itemClassId,String type){
		List<SelectorOption> sltList=null;
		try {
			List<ArticleType> list = priceHeadManager.getArticleTypeClassByHeadId(headId, ininvTypeId,itemClassId,type);
			sltList = new ArrayList<SelectorOption>();
			for(ArticleType bc : list){
				sltList.add(new SelectorOption(bc.getArticleTypeId(), bc.getArticleTypeDesc()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sltList;
	}
	/**
	 * 大小类，供应商下的款式
	 * @param itemClassId
	 * @param ornaClassId
	 * @param custId
	 * @param pid
	 * @return 款式//_大类_小类_款式大类_款式中类_款式小类_款号_是否特殊工费_是否双标签
	 */
//	public List<SelectorOption> getStyleByCalc(String itemClassId,String ornaClassId,
//			String styleItemId,String styleMiddleId,String styleOrnaId,
//			String custId,String pid,String type){
//		List<SelectorOption> sltList=null;
//		try {
//			Map<String, String> condition = new HashMap<String, String>();
//			condition.put("itemClassId", itemClassId);
//			condition.put("ornaClassId", ornaClassId);
//			condition.put("styleItemId", styleItemId);
//			condition.put("styleMiddleId", styleMiddleId);
//			condition.put("styleOrnaId", styleOrnaId);
//			condition.put("custId", custId);
//			condition.put("pid", pid);
//			condition.put("type", type);
//
//			List<Style> list = priceHeadManager.getStyleByCalc(condition);
//			sltList = new ArrayList<SelectorOption>();
//			for(Style bc : list){
//				sltList.add(new SelectorOption(bc.getStyleId(),bc.getStyleName()));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return sltList; 
//	}
	public StyleForm getStyleByCalcWin(String itemClassId,String ornaClassId,
			String styleItemId,String styleMiddleId,String styleOrnaId,
			String custId,String pid,String type, String start, String limit
			,HttpServletRequest req){
		StyleForm form  = new StyleForm();
		String basePath = CommonUtil.getPicBasePath();
		try {
			HandoverHead p = handoverManager.getHandoverHead(pid);
			Map<String, String> condition = new HashMap<String, String>();
			condition.put("itemClassId", itemClassId);
			condition.put("ornaClassId", ornaClassId);
			condition.put("styleItemId", styleItemId);
			condition.put("styleMiddleId", styleMiddleId);
			condition.put("styleOrnaId", styleOrnaId);
			condition.put("custId", p.getVerdorId());
			condition.put("pid", pid);
			condition.put("type", type);
			condition.put("start", start);
			condition.put("limit", limit);
			
			Pager pager = priceHeadManager.getStyleByCalc(condition);
			
			form.setStylePicUrl(basePath);
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return form; 
	}
	
	/**
	 * 入库单下的核价单
	 * @param handoverBillId
	 * @param inivType
	 * @param itemClassId
	 * @param ornaClassId
	 * @param styleId
	 * @param req
	 * @return
	 */
	public List<SelectorOption> loadPriceByIniv(String handoverBillId,String inivType,
			String itemClassId,String ornaClassId,String styleId,String id,HttpServletRequest req){
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		if(handoverBillId==null||null==inivType)
			return null;
		List<PriceHead> list = priceHeadManager.loadPriceByIniv(handoverBillId, inivType, itemClassId, ornaClassId, styleId,id);
		for(PriceHead bc : list){
			sltList.add(new SelectorOption(bc.getId(),bc.getNo()));
		}
		return sltList;
	}
	/**
	 * ID取对象
	 * @param id
	 * @return
	 */
	public PriceHead getPriceById(String id){
		if(id==null)
			return null;
		return priceHeadManager.getPriceHead(id);
	}
	/**
	 * ID取对象
	 * @param id
	 * @return
	 */
	public PriceHead getPriceByNo(String no){
		if(no==null)
			return null;
		return priceHeadManager.getPriceHeadByNo(no);
	}
	/**
	 * 大小类、供应商 、工产款号取款式
	 * @param itemClassId
	 * @param ornaClassId
	 * @param custId
	 * @param toStyle
	 * @return
	 */
	public Style getStyleByToStyle(String itemClassId,String ornaClassId,String custId,String toStyle){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("custId", custId);
		condition.put("toStyle", toStyle);
		condition.put("itemClassId", itemClassId);
		condition.put("ornaClassId", ornaClassId);
		Style s = priceHeadManager.getStyleByToStyle(condition);
		return s; 
	}
	/**
	 * 大小类、分析范围、档级取基础系数
	 * @param itemClassId
	 * @param ornaClassId
	 * @param hlevel
	 * @param alaysisId
	 * @return
	 */
	public String getBasicPriceCoefficient(String itemClassId,String ornaClassId,
			String hlevel,String alaysisId ){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("alaysisId", alaysisId);
		condition.put("hlevel", hlevel);
		condition.put("itemClassId", itemClassId);
		condition.put("ornaClassId", ornaClassId);
		return priceHeadManager.getBasicPriceCoefficient(condition);
	}

	/**
	 * 编码找非现有量
	 * @param ornaCode
	 * @return
	 */
	public MaterNoActive getMaterNoActiveByOrnaCode(String ornaCode,String venderId,String id,HttpServletRequest req){
		if(ornaCode==null||"".equals(ornaCode)||venderId==null||"".equals(venderId))
			return null;
		
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("no", ornaCode);
		condition.put("venderId", venderId);
		condition.put("id", (id==null||"".equals(id))?"0":id);
		CashProdAccount num = this.priceHeadManager.getZUNums(condition);
		MaterNoActive mna = priceHeadManager.getMaterNoActiveByOrnaCode(ornaCode);
		if(null==mna)
			return null;
		
		if(num==null){
			mna.setNowQty(null);
			mna.setSupplierId(null);
		}else{
			mna.setNowQty(num.getNoNums());
			mna.setSupplierId(num.getVoderId());
		}
		return mna;
		
	}
	/**
	 * 编码找现有量
	 * @param ornaCode
	 * @return
	 */
	public MaterActive getMaterActiveByOrnaCode(String ornaCode,String venderId,String id,HttpServletRequest req){
		if(ornaCode==null||"".equals(ornaCode)||venderId==null||"".equals(venderId))
			return null;
		
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("no", ornaCode);
		condition.put("venderId", venderId);
		condition.put("id", (id==null||"".equals(id))?"0":id);
		CashProdAccount num = this.priceHeadManager.getZUNums(condition);
		MaterActive ma = priceHeadManager.getMaterActiveByOrnaCode(ornaCode);
		if(null==ma)
			return null;
		
		if(num==null){
			ma.setNowQty(null);
			ma.setSupplierId(null);
		}else{
			ma.setNowQty(num.getNoNums());
			ma.setSupplierId(num.getVoderId());
		}
		return ma;
		
	}
	/**
	 * 大小类、重量取证书价格
	 * @param itemClassId
	 * @param num
	 * @return
	 */
	public String getCalcCertPrice(String itemClassId,String num) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("itemClassId", itemClassId);
		condition.put("num", num);
		
		return priceHeadManager.getCalcCertPrice(condition);
	}
	/**
	 * 大小类、重量获取钻石核价石头系数 
	 * @param itemClassId
	 * @param num
	 * @return
	 */
	public String getCalcStoneMainCoefficient(String itemClassId,String num) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("itemClassId", itemClassId);
		condition.put("num", num);
		
		return priceHeadManager.getCalcStoneMainCoefficient(condition);
	}
	/**
	 * 取分析范围
	 * @param itemClassId
	 * @param ornaClassId
	 * @param allQty
	 * @param basicPrice
	 * @param mainWeight
	 * @return
	 */
	public Analysis getAnalysis(String itemClassId,String ornaClassId,String allQty,String basicPrice,String mainWeight){
		if("".equals(itemClassId)||"".equals(ornaClassId))
			return null;
		return this.commonManager.getAnalysis(itemClassId, ornaClassId, allQty, basicPrice, mainWeight);
	}
	
	public PriceStoneLine getMainStoneLineByHeadId(String headId){
		return this.priceHeadManager.getMainStoneLineByHeadId(headId);
	}
	
	public String getInivCalcCode(String type,HttpServletRequest req){
		return this.priceHeadManager.getEmpInivCalcCode(CommonUtil.getSessionUserId(req), type);
	}
	public boolean checkCustomizeNo(String no,String hid,HttpServletRequest req){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("billid", hid);
		List<PurchaseHead> list = priceHeadManager.getDZcode(condition);
		boolean check = false;
		for (int i = 0; i < list.size(); i++) {
			PurchaseHead head = list.get(i);
			if(no.equals(head.getBillno())){
				check = true;
				break;
			}
		}
		return check;
	}
	
	public String getUnitPrice(String headId,String itemClassId,String alaysisId,HttpServletRequest req){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headId", headId);
		condition.put("itemClassId", itemClassId);
		condition.put("alaysisId", alaysisId);
		String price = this.priceHeadManager.getUnitPrice(condition);
		return price;
	}

	public String getZUNums(String no,String venderId,HttpServletRequest req){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("no", no);
		condition.put("venderId", venderId);
		if(no==null||"".equals(no))	
			return "0";
		return "0";
	}
	
	public String getVendorCoefficient(String venderId,HttpServletRequest req){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("venderId", venderId);
		return this.priceHeadManager.getVendorCoefficient(condition);
	}
	
	public SpecialCharge getSpecialChargeNum(String weight){
		return this.priceHeadManager.getSpecialChargeNum(weight);
	}
	
	public String getVerdorChargeNum(String verdorId,String styleId){
		return this.priceHeadManager.getVerdorChargeNum(verdorId, styleId);
	}
	
	public String getMaterInivCountByWlCode(String wlCode){
		return this.priceHeadManager.getMaterInivCountByWlCode(wlCode);
	}
	public void updateMaterDZByCode(String wlCode,String dzCode){
		if(!"".equals(dzCode)&&!"".equals(dzCode)){
			this.priceHeadManager.updateMaterInivCustomizeNoByWlCode(dzCode, wlCode);
			this.priceHeadManager.updateMaterActionCustomizeNoByWlCode(dzCode, wlCode);
		}			
	}
}
