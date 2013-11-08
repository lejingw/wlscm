package com.jatools.web.dwr.stock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.constant.ParameterConstant;
import com.jatools.manager.bd.BdCommonManager;
import com.jatools.manager.stock.MaterActiveManager;
import com.jatools.manager.stock.ProcPackageHeadManager;
import com.jatools.manager.util.CommonManager;
import com.jatools.vo.bd.Analysis;
import com.jatools.vo.stock.PackageMaterActive;
import com.jatools.vo.stock.ProcPackageHead;
import com.jatools.vo.stock.ProcPackageNewline;
import com.jatools.vo.stock.ProcPackageOldline;
import com.jatools.vo.sys.Dict;
import com.jatools.web.cache.DictCache;
import com.jatools.web.util.BdCommon;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

/**
 * 拆包混包
 * @author ren.ming
 * Created 2011-11-24
 */
public class PackageDwr {
	private static Logger logger = Logger.getLogger(PackageDwr.class);
	
	private MaterActiveManager materActiveManager;
	private CommonManager commonManager;
	private BdCommonManager bdCommonManager;
	private ProcPackageHeadManager procPackageHeadManager;
	
	public void setProcPackageHeadManager(
			ProcPackageHeadManager procPackageHeadManager) {
		this.procPackageHeadManager = procPackageHeadManager;
	}
	public void setBdCommonManager(BdCommonManager dbCommonManager) {
		this.bdCommonManager = dbCommonManager;
	}
	public void setMaterActiveManager(MaterActiveManager materActiveManager) {
		this.materActiveManager = materActiveManager;
	}
	
	public void setCommonManager(CommonManager commonManager) {
		this.commonManager = commonManager;
	}

	/**
	 * 根据编码获取饰品现有量<br>
	 * @param ornaCode 编码
	 */
	public Map<String, Object> getMaterActiveByOrnaCode(String ornaCode, String orgId, String stockId, String dotype) {
		PackageMaterActive materActive = this.materActiveManager.getPackageMAByOrnaCode(ornaCode, orgId, stockId, dotype);
		return this.checkMater(materActive, orgId, stockId, dotype);
	}
	
	/**
	 * 根据条码获取饰品现有量<br>
	 * @param barCode 条码
	 * @param orgId 组织id
	 */
	public Map<String, Object> getMaterActiveByBarCode(String barCode, String orgId, String stockId, String dotype) {
		PackageMaterActive materActive = this.materActiveManager.getPackageMAByBarCode(barCode, orgId, stockId, dotype);
		return this.checkMater(materActive, orgId, stockId, dotype);
	}
	
	private Map<String, Object> checkMater(PackageMaterActive materActive, String orgId, String stockId, String dotype) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != materActive){
			if(orgId.equals(materActive.getOrgId())){
				if(DictConstant.BILL_STATUS_MATER_ACTIVE_VALID.equals(materActive.getState())){
					if(stockId.equals(materActive.getStockId())){
						if("2".equals(dotype)){ // 原料分割
							List<Dict> cutDict = DictCache.getInstance().getDicts("materialcutitem");
							if(this.checkDict(cutDict, materActive)){
								result.put("isSuccess", "true");
								result.put("data", this.dealMaterActive(materActive));
							} else {
								result.put("isSuccess", "false");
								result.put("msg", "饰品不允许原料分割");
							}
						} else {// 拆混包
							List<Dict> packDict = DictCache.getInstance().getDicts("packageitem");
							if(this.checkDict(packDict, materActive)){
								result.put("isSuccess", "true");
								result.put("data", this.dealMaterActive(materActive));
							} else {
								result.put("isSuccess", "false");
								result.put("msg", "饰品不允许拆混包");
							}
						}
					} else {
						result.put("isSuccess", "false");
						result.put("msg", "饰品不属于当前子库");
					}
				} else {
					result.put("isSuccess", "false");
					result.put("msg", "饰品已在使用中");
				}
			} else {
				result.put("isSuccess", "false");
				result.put("msg", "饰品不属于当前组织");
			}
		} else {
			result.put("isSuccess", "false");
			result.put("msg", "饰品不存在");
		}
		return result;
	}
	
	private boolean checkDict(List<Dict> dictList, PackageMaterActive mater){
		boolean isExists = Boolean.FALSE;
		for(Dict dict : dictList){
			if(dict.getItemKey().equals(mater.getItemClassId())){
				isExists = Boolean.TRUE;
				break;
			}
		}
		return isExists;
	}
	/**
	 * 金额和重量的小数处理以及一些id转化为名称
	 * @param packageMA
	 * @return
	 */
	private PackageMaterActive dealMaterActive(PackageMaterActive packageMA) {
		if(packageMA == null) return  packageMA;
		
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
			packageMA.setPosAmount("");
		}
//		if(StringUtil.isNotBlank(packageMA.getRealTotalCost())) {
//			packageMA.setRealTotalCost(Double.valueOf(packageMA.getRealTotalCost())+"");
//		}else {
//			packageMA.setRealTotalCost("0");
//		}
		
		if(StringUtil.isNotBlank(packageMA.getNowQty())) {
			packageMA.setNowQty(Double.valueOf(packageMA.getNowQty())+"");
		}else {
			packageMA.setNowQty("0");
		}
		
		String itemClassName = BdCommon.getItemClassDesc(packageMA.getItemClassId());
		packageMA.setItemClassName(itemClassName);
		String ornaClassName = BdCommon.getOrnaClassDesc(packageMA.getOrnaClassId());
		packageMA.setOrnaClassName(ornaClassName);
//		String styleName = this.bdCommonManager.getStyleNameById(packageMA.getStyleId());
//		packageMA.setStyleName(styleName);
		String analysisName = this.bdCommonManager.getAnalysisNameById(packageMA.getAlaysisId());
		packageMA.setAlaysisName(analysisName);
		String saleUnitName = BdCommon.getUnitName(packageMA.getSaleUnitId());
		packageMA.setSaleUnitName(saleUnitName);
		return packageMA;
	}
	
	/**
	 * 取分析范围
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	public Analysis getAnalysis(Map params) {
		String allQty = (String)params.get("allQty");
		String mainWeight = (String)params.get("mainWeight");
		String basicPrice = (String)params.get("basePrice");
		String itemClassId = (String)params.get("itemClassId");
		String ornaClassId = (String)params.get("ornaClassId");
		Analysis analysis = this.commonManager.getAnalysis(itemClassId, ornaClassId, allQty, basicPrice, mainWeight);
		return analysis;
	}
	
	public String checkWeightValid(String oldWeight, String newWeight) {
		try{
			if(StringUtil.isNotBlank(newWeight) && StringUtil.isNotBlank(oldWeight)){
				double newWei = Double.valueOf(newWeight);
				double oldWei = Double.valueOf(oldWeight);
				double upWeight = Double.valueOf(BdCommon.getParameterValue(ParameterConstant.STOCK_PACKAGE_WEIGHT_UP));
				double downWeight = Double.valueOf(BdCommon.getParameterValue(ParameterConstant.STOCK_PACKAGE_WEIGHT_DOWN));
				double res = Double.valueOf(StringUtil.formatDouble((newWei-oldWei), 4));
				if(!(res >= upWeight && res <= downWeight)){
					return "新重量合计与原重量合计误差只允许在 [" + upWeight + " ~ " + downWeight + "]之间";
				}
			}
		} catch (Exception e) {
			logger.equals(e);
			return "重量格式不合法";
		}
		return null;
	}
	public String saveOrUpdatePackage(ProcPackageHead packageHead, List<ProcPackageOldline> oldLines, List<ProcPackageNewline> newLines, String ids[], HttpServletRequest req) {
		try{
			if(StringUtil.isEmpty(packageHead.getBillid())){
				packageHead.setCreateDate(DateUtil.getCurrentDate18());
				packageHead.setCreateId(CommonUtil.getSessionUserId(req));
				packageHead.setStatus(DictConstant.BILL_STATUS_SAVE);
				String billNo = commonManager.getBillno(GlobalConstant.BILL_CODE_CHAIHUNBAO);
				packageHead.setBillno(billNo);
			}
			packageHead.setUpdateDate(DateUtil.getCurrentDate18());
			packageHead.setUpdateId(CommonUtil.getSessionUserId(req));
			// 给新饰品生成 编码和条码
			String itemClassId = oldLines.get(0).getItemClassId();
			String ornaClassId = oldLines.get(0).getOrnaClassId();
			for(ProcPackageNewline newLine : newLines) {
				if( StringUtil.isBlank(newLine.getLineid())) {
					String newBarCode = commonManager.getBarCode(itemClassId, ornaClassId);
					newLine.setOrnaBarcode(newBarCode);
				}
			}
			procPackageHeadManager.saveOrUpdateProcPackageHead(packageHead, oldLines, newLines, ids);
			return null;
		} catch (Exception e) {
			logger.error(e);
			if(StringUtil.isNotBlank(e.getMessage())){
				return e.getMessage();
			}
			return "保存出错";
		}
	}
	
	public Map<String, String> saveAndClosePackage(ProcPackageHead packageHead, List<ProcPackageOldline> oldLines, List<ProcPackageNewline> newLines, String ids[], String needChecked, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try{
			if(StringUtil.isEmpty(packageHead.getBillid())){
				packageHead.setCreateDate(DateUtil.getCurrentDate18());
				packageHead.setCreateId(CommonUtil.getSessionUserId(req));
				packageHead.setStatus(DictConstant.BILL_STATUS_SAVE);
				String billNo = commonManager.getBillno(GlobalConstant.BILL_CODE_CHAIHUNBAO);
				packageHead.setBillno(billNo);
			}
			packageHead.setUpdateDate(DateUtil.getCurrentDate18());
			packageHead.setUpdateId(CommonUtil.getSessionUserId(req));
			// 给新饰品生成 编码和条码
			String itemClassId = oldLines.get(0).getItemClassId();
			String ornaClassId = oldLines.get(0).getOrnaClassId();
			for(ProcPackageNewline newLine : newLines) {
				if( StringUtil.isBlank(newLine.getLineid())) {
					String newBarCode = commonManager.getBarCode(itemClassId, ornaClassId);
					newLine.setOrnaBarcode(newBarCode);
				}
			}
			procPackageHeadManager.saveAndCloseProcPackageHead(packageHead, oldLines, newLines, ids, needChecked);
			result.put("isSuccess", "true");
			return result;
		} catch (Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "处理失败");
			}
			return result;
		}
	}
	
	public String deletePackage(String packageId, HttpServletRequest req) {
		try{
			if(StringUtil.isNotEmpty(packageId)){
				this.procPackageHeadManager.deleteProcPackageHead(packageId);
			}
			return null;
		} catch (Exception e) {
			logger.error(e);
			if(StringUtil.isNotBlank(e.getMessage())){
				return e.getMessage();
			}
			return "保存出错";
		}
	}
	/**
	 * 关闭单据
	 * @param billid
	 * @param req
	 * @return
	 */
	public String closePackageHead(String billid, String needChecked,  HttpServletRequest req) {
		try{
			this.procPackageHeadManager.closePackageHead(billid, needChecked, CommonUtil.getSessionUserId(req));
		} catch(Exception e){
			logger.error(e);
			if(StringUtil.isNotBlank(e.getMessage())){
				return e.getMessage();
			}
			return "关闭失败";
		}
		return null;
	}
	
	
	/**
	 * 审核单据
	 * @param billid
	 * @param req
	 * @return
	 */
	public Map<String, String> checkBill(String billid, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.procPackageHeadManager.checkBillhead(billid, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
		} catch(Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "审核失败");
			}
		}
		return result;
	}
	
	public String checkAnalysis(String oldAnalysId, String newAnalysId, HttpServletRequest req){
		try {
			int count = this.procPackageHeadManager.selectAnalysCount(oldAnalysId, newAnalysId);
			if(count == 1){
				return "true";
			} 
			return "false";
		} catch (Exception e){
			logger.error("PackageDwr.checkAnalysis error![oldAnalysId:"+oldAnalysId+", newAnalysId:"+newAnalysId+"]");
			return "false";
		}
	}
}
