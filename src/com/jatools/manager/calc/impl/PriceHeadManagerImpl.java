package com.jatools.manager.calc.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.ibm.db2.jcc.t2zos.t;
import com.jatools.common.Pager;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.dao.bd.BdCommonDao;
import com.jatools.dao.calc.PriceHeadDao;
import com.jatools.dao.out.CashProdAccountDao;
import com.jatools.manager.bd.BdCommonManager;
import com.jatools.manager.calc.PriceHeadManager;
import com.jatools.manager.out.CashProdAccountManager;
import com.jatools.manager.pur.HandoverManager;
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
import com.jatools.vo.out.CashProdUser;
import com.jatools.vo.pur.HandoverHead;
import com.jatools.vo.pur.PurchaseHead;
import com.jatools.vo.stock.MaterActive;
import com.jatools.vo.stock.MaterIniv;
import com.jatools.vo.stock.MaterNoActive;
import com.jatools.web.util.BdCommon;
import com.jatools.web.util.DictUtil;
import com.jatools.web.util.StringUtil;

public class PriceHeadManagerImpl implements PriceHeadManager {

	private PriceHeadDao priceHeadDao;
	private BdCommonDao bdCommonDao;
	private CashProdAccountManager cashProdAccountManager;
	private HandoverManager handoverManager;
	
	
	public void setHandoverManager(HandoverManager handoverManager) {
		this.handoverManager = handoverManager;
	}

	public void setCashProdAccountManager(
			CashProdAccountManager cashProdAccountManager) {
		this.cashProdAccountManager = cashProdAccountManager;
	}

	public void setBdCommonDao(BdCommonDao bdCommonDao) {
		this.bdCommonDao = bdCommonDao;
	}

	public void setPriceHeadDao(PriceHeadDao priceHeadDao) {
		this.priceHeadDao = priceHeadDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager getPriceHeadPageData(Map<String, String> condition) {
		return priceHeadDao.getPriceHeadPageData(condition);
	}

	@Override
	public String savePriceHead(PriceHead head) {
		
		return priceHeadDao.savePriceHead(head);
	}

	@Override
	public void updatePriceHead(PriceHead head) {
		priceHeadDao.updatePriceHead(head);
		
	}

	@Override
	public void deleteLineByHeadid(String headid) {
		this.cashProdAccountManager.subAccountUserNumByPriceHeadId(headid);
		this.cashProdAccountManager.deleteAccountUserByPriceHeadId(headid);
		//this.deleteAccount(headid);
		//priceHeadDao.updateUserNumsById(headid);//修改台账使用量
		//priceHeadDao.deleteUserById(headid);//删除台账孙表
		priceHeadDao.deleteLineByHeadid(headid);
		priceHeadDao.deletePriceHead(headid);
	}

	@Override
	public void savePriceAccLine(List<PriceAccLine> lineList) {
		priceHeadDao.savePriceAccLine(lineList);
		
	}

	@Override
	public void savePriceAcsLine(List<PriceAcsLine> lineList) {
		priceHeadDao.savePriceAcsLine(lineList);
		
	}

	@Override
	public void savePriceSbraLine(List<PriceSbraLine> lineList) {
		priceHeadDao.savePriceSbraLine(lineList);
		
	}

	@Override
	public void savePriceStoneLine(List<PriceStoneLine> lineList) {
		priceHeadDao.savePriceStoneLine(lineList);
		
	}

	@Override
	public PriceHead getPriceHead(String headid) {
		
		return priceHeadDao.getPriceHead(headid);
	}

	@Override
	public List<PriceAccLine> getPriceAccLineList(String headid) {
		
		return priceHeadDao.getPriceAccLineList(headid);
	}

	@Override
	public List<PriceAcsLine> getPriceAcsLineList(String headid) {
		
		return priceHeadDao.getPriceAcsLineList(headid);
	}

	@Override
	public List<PriceSbraLine> getPriceSbraLineList(String headid) {
		
		return priceHeadDao.getPriceSbraLineList(headid);
	}

	@Override
	public List<PriceStoneLine> getPriceStoneLineList(String headid) {
		
		return priceHeadDao.getPriceStoneLineList(headid);
	}

	@Override
	public void deletePriceHead(String headid) {
		priceHeadDao.deletePriceHead(headid);
	}
	
	private void makeAccount(List<PriceStoneLine> stoneLine,PriceHead head,String hid,String type) throws RuntimeException{
		for (int i = 0; i < stoneLine.size(); i++) {
			PriceStoneLine sl = stoneLine.get(i);
			if("1".equals(sl.getPrivateType())){
				//取得饰品对应该台账的最大可用量
				Map<String, String> condition = new HashMap<String, String>();
				condition.put("no", sl.getLuodanCode());
				condition.put("id", hid);
				CashProdAccount cpa = this.priceHeadDao.getZUNums(condition);
				//再次判断行粒数不用大于最大可用量
				if(Double.parseDouble(cpa.getNoNums())<Double.parseDouble(sl.getStoneNums()))
					throw new RuntimeException("台账数据已被修改不能保存");
				//根据饰品拿到所有台账数据
				List<CashProdAccount> lista = priceHeadDao.getAccountByCode(sl.getLuodanCode());
				Double num = Double.parseDouble(sl.getStoneNums());
				//循环写台账孙表 和 修改台账使用量
				for (int j = 0; j < lista.size(); j++) {
					CashProdAccount ca = lista.get(j);
					//本台账可使用粒量
					Double noNums = Double.parseDouble(ca.getNoNums())-Double.parseDouble(ca.getUserNums());
					System.out.println("ID:"+ca.getPrId()+"; NUMS:"+ca.getNoNums()+"; USERS:"+ca.getUserNums()+"; MAX:"+num);
					//写台账孙表
					CashProdUser olduser = new CashProdUser();
					olduser.setPrId(ca.getPrId());
					olduser.setSourceId(hid);
					olduser.setSourceType(GlobalConstant.BILL_CODE_PRICEHEAD);
					olduser.setOrnaNo(sl.getLuodanCode());
					olduser.setState("1");
					olduser.setCreateId(head.getCreateUserId());
					olduser.setCreateDate(head.getCreateDate());
					olduser.setUpdateId(head.getCreateUserId());
					olduser.setUpdateDate(head.getCreateDate());
					if(num>=noNums){
						olduser.setUserNums(noNums+"");
						if(num==noNums)
							num = null;
						else
							num = num - noNums;
					}else{
						olduser.setUserNums(num+"");
						num = null;
					}
					this.priceHeadDao.saveAccountUser(olduser);
					
					//修改台账使用量
					CashProdAccount oldca = cashProdAccountManager.getCashProdAccount(ca.getPrId());
					oldca.setUserNums((Double.parseDouble(oldca.getUserNums())+Double.parseDouble(olduser.getUserNums()))+"");
					cashProdAccountManager.updateCashProdAccountUserNums(oldca);
					if(num==null)
						break;
				}
			}
		}
	}
	
	public void deleteAccount(String hid){
		String snum = this.priceHeadDao.getProdUseraNumBySid(hid);
		if(snum==null)
			return;
		List<PriceStoneLine> olestone = this.priceHeadDao.getPriceStoneLineList(hid);
		for (int i = 0; i < olestone.size(); i++) {
			PriceStoneLine sl = olestone.get(i);
			Double num = Double.parseDouble(sl.getStoneNums());//核价行使用量
			if("1".equals(sl.getPrivateType())){
				//根据饰品拿到所有台账数据
				List<CashProdAccount> lista = priceHeadDao.getAccountByCode(sl.getLuodanCode());
				for (int j = 0; j < lista.size(); j++) {//循环修改使用量
					CashProdAccount ca = lista.get(j);
					if(!"0".equals(ca.getUserNums())){//有使用量才消除
						if(num>=Double.parseDouble(ca.getUserNums())){//行使用量大于记录用量 时
							CashProdAccount ucpa = this.cashProdAccountManager.getCashProdAccount(ca.getPrId());
							ucpa.setUserNums("0");
							this.cashProdAccountManager.updateCashProdAccountUserNums(ucpa);
							if(num==Double.parseDouble(ca.getUserNums()))
								break;
							num = num - Double.parseDouble(ca.getUserNums());
						}else{
							CashProdAccount ucpa = this.cashProdAccountManager.getCashProdAccount(ca.getPrId());
							ucpa.setUserNums((Double.parseDouble(ca.getUserNums())-num)+"");
							this.cashProdAccountManager.updateCashProdAccountUserNums(ucpa);
							break;
						}
					}
				}
			}
		}
	}
	
	
	public String saveOrUpdatePrice(PriceHead head,
			List<PriceStoneLine> stoneLine, List<PriceSbraLine> sbraLine,
			List<PriceAcsLine> acsLine, List<PriceAccLine> accLine) throws RuntimeException{
		String hid = head.getId();
		String result = "";
		if(null==hid||"".equals(hid)){
			hid = priceHeadDao.savePriceHead(head);
			this.makeAccount(stoneLine, head, hid,"insert");
		}else{
			this.deleteAccount(hid);
			//priceHeadDao.updateUserNumsById(hid);//修改台账使用量
			priceHeadDao.deleteUserById(hid);//删除台账孙表
			priceHeadDao.deleteLineByHeadid(hid);
			priceHeadDao.updatePriceHead(head);
			this.makeAccount(stoneLine, head, hid,"update");
		}
		for (PriceAccLine acc : accLine) {
			acc.setHeadId(hid);
		}
		for (PriceAcsLine acs : acsLine) {
			acs.setHeadId(hid);
		}
		for (PriceSbraLine sbra : sbraLine) {
			sbra.setHeadId(hid);
		}
		for (PriceStoneLine stone : stoneLine) {
			stone.setHeadId(hid);
		}
		if(stoneLine!=null&&stoneLine.size()>0)
			priceHeadDao.savePriceStoneLine(stoneLine);
		if(sbraLine!=null&&sbraLine.size()>0)
			priceHeadDao.savePriceSbraLine(sbraLine);
		if(acsLine!=null&&acsLine.size()>0)
			priceHeadDao.savePriceAcsLine(acsLine);
		if(accLine!=null&&accLine.size()>0)
			priceHeadDao.savePriceAccLine(accLine);
		
		return hid;
	}
	

	// -----------------------------------------------------------------------------------------------------------------------
	
	public String saveOrUpdatePrice(PriceHead head, List<PriceStoneLine> stoneLine, List<PriceSbraLine> sbraLine,
			List<PriceAcsLine> acsLine, List<PriceAccLine> accLine, String deleteStoneIds,
			String deleteSbraLineIds,String deleteAcsLineIds,String deleteAccLineIds,String userId) throws RuntimeException{
		if(StringUtil.isBlank(head.getId())){
			priceHeadDao.savePriceHead(head);
		}else{
			priceHeadDao.updatePriceHead(head);
		}
		
		if(sbraLine!=null&&sbraLine.size()>0){
			List<PriceSbraLine> addList = new ArrayList<PriceSbraLine>();
			List<PriceSbraLine> updateList = new ArrayList<PriceSbraLine>();
			for (PriceSbraLine sbra : sbraLine) {
				sbra.setHeadId(head.getId());
				if(StringUtil.isNotBlank(sbra.getId())){
					updateList.add(sbra);
				} else {
					addList.add(sbra);
				}
			}
			priceHeadDao.savePriceSbraLine(addList);
			priceHeadDao.updatePriceSbraLine(updateList);
		}
		
		if(acsLine!=null&&acsLine.size()>0){
			List<PriceAcsLine> addList = new ArrayList<PriceAcsLine>();
			List<PriceAcsLine> updateList = new ArrayList<PriceAcsLine>();
			for (PriceAcsLine acs : acsLine) {
				acs.setHeadId(head.getId());
				if(StringUtil.isNotBlank(acs.getId())){
					updateList.add(acs);
				} else {
					addList.add(acs);
				}
			}
			priceHeadDao.savePriceAcsLine(addList);
			priceHeadDao.updatePriceAcsLine(updateList);
		}
		if(accLine!=null&&accLine.size()>0){
			List<PriceAccLine> addList = new ArrayList<PriceAccLine>();
			List<PriceAccLine> updateList = new ArrayList<PriceAccLine>();
			for (PriceAccLine acc : accLine) {
				acc.setHeadId(head.getId());
				if(StringUtil.isNotBlank(acc.getId())){
					updateList.add(acc);
				} else {
					addList.add(acc);
				}
			}
			priceHeadDao.savePriceAccLine(addList);
			priceHeadDao.updatePriceAccLine(updateList);
		}
		// 处理 删除行 核价台账
		if(StringUtil.isNotBlank(deleteStoneIds)){
			String delStoneIdArr[] = deleteStoneIds.split(";");
			for(String deleteId : delStoneIdArr){
				if(StringUtil.isNotBlank(deleteId)){
					this.cashProdAccountManager.subAccountUserNumByPriceLineId(deleteId);
					this.cashProdAccountManager.deleteAccountUserByPriceLineId(deleteId);
					this.priceHeadDao.deleteStoneLineByLineId(deleteId);
				}
			}
		}
		if(StringUtil.isNotBlank(deleteSbraLineIds)){
			String delSbraIdArr[] = deleteSbraLineIds.split(";");
			for(String deleteId : delSbraIdArr){
				if(StringUtil.isNotBlank(deleteId)){
					this.priceHeadDao.deleteSbraLineByLineId(deleteId);
				}
			}
		}
		if(StringUtil.isNotBlank(deleteAcsLineIds)){
			String delStoneIdArr[] = deleteAcsLineIds.split(";");
			for(String deleteId : delStoneIdArr){
				if(StringUtil.isNotBlank(deleteId)){
					this.priceHeadDao.deleteAcsLineByLineId(deleteId);
				}
			}
		}
		if(StringUtil.isNotBlank(deleteAccLineIds)){
			String delStoneIdArr[] = deleteAccLineIds.split(";");
			for(String deleteId : delStoneIdArr){
				if(StringUtil.isNotBlank(deleteId)){
					this.priceHeadDao.deleteAccLineByLineId(deleteId);
				}
			}
		}
		for (PriceStoneLine stone : stoneLine) {
			stone.setHeadId(head.getId());
		}
		// 处理新增行和修改行台账
		this.dealProdAccountUser(stoneLine, userId);
		return head.getId();
	}
	
	private void dealProdAccountUser(List<PriceStoneLine> stoneLineList, String userId) {
		if(CollectionUtils.isNotEmpty(stoneLineList)){
			for(PriceStoneLine line : stoneLineList){
				if(StringUtil.isNotBlank(line.getId())){
					if(line.getLuodanCode()!=null&&!line.getLuodanCode().equals(line.getOldOrnaCode())){// 饰品编码更换
						// TODO 1、修改台账使用量
						this.cashProdAccountManager.subAccountUserNumByPriceLineId(line.getId());
						// TODO 2、先删除原台长使用量
						this.cashProdAccountManager.deleteAccountUserByPriceLineId(line.getId());
						// TODO 3、创建新使用量
						this.addProdAccount(line, userId);
						// TODO 4、修改台账使用量
						this.cashProdAccountManager.addAccountUserNumByPriceLineId(line.getId());
						
					} else if(StringUtil.isNotBlank(line.getOldStoneNum())){ // 核价数量修改
						double stoneNum = Double.valueOf(line.getStoneNums());
						double oldStoneNum = Double.valueOf(line.getOldStoneNum());
						if(stoneNum != oldStoneNum){
							// TODO 1、修改台账使用量
							this.cashProdAccountManager.subAccountUserNumByPriceLineId(line.getId());
							// TODO 2、先删除原台长使用量
							this.cashProdAccountManager.deleteAccountUserByPriceLineId(line.getId());
							// TODO 3、创建新使用量
							this.addProdAccount(line, userId);
							// TODO 4、修改台账使用量
							this.cashProdAccountManager.addAccountUserNumByPriceLineId(line.getId());
						}
					}
					// TODO 修改主配石行
					this.priceHeadDao.updatePriceStoneLine(line);
				} else {// 新增行
					// TODO 1、保存主配石行
					this.priceHeadDao.savePriceStoneLine(line);
					// TODO 2、创建新使用量
					this.addProdAccount(line, userId);
					// TODO 3、修改台账使用量
					this.cashProdAccountManager.addAccountUserNumByPriceLineId(line.getId());
				}
			}
		}
	}
	
	private void addProdAccount(PriceStoneLine line, String userId) {
		List<CashProdAccount> prodAccountList = priceHeadDao.getAccountByCode(line.getLuodanCode());
		if(CollectionUtils.isNotEmpty(prodAccountList)){
			double stoneNum = 0;
			if(StringUtil.isNotBlank(line.getStoneNums())){
				stoneNum = Double.valueOf(line.getStoneNums());
			}
			if(stoneNum <= 0) throw new RuntimeException("核价数量必须大于0");
			
			for(CashProdAccount account : prodAccountList){
				double noNums = 0, userNums = 0, realNum = 0;
				if(StringUtil.isNotBlank(account.getNoNums())){
					noNums = Double.valueOf(account.getNoNums());
				}
				if(StringUtil.isNotBlank(account.getUserNums())){
					userNums = Double.valueOf(account.getUserNums());
				}
				if(noNums <=0 || noNums-userNums <=0){
					continue;
				}
				realNum = noNums-userNums;
				if(stoneNum <= realNum){
					this.priceHeadDao.insertProdAccountUser(line.getId(), account.getPrId(), line.getLuodanCode(), stoneNum+"", userId);
					stoneNum = stoneNum - realNum;
					break;
				} else {
					this.priceHeadDao.insertProdAccountUser(line.getId(), account.getPrId(), line.getLuodanCode(), realNum+"", userId);
					stoneNum = stoneNum - realNum;
				}
			}
			if(stoneNum > 0){
				throw new RuntimeException("实际可核销的饰品台账不够用");
			}
		} else {
			//throw new RuntimeException("不存在可以核销的饰品台账数据");
		}
	}
	// -----------------------------------------------------------------------------------------------------------------------
	
	/**
	 * 核价交接单列表
	 * @param ininvTypeId 交接单行表下大类的入库类型，状态为审核完成
	 * @param orgId
	 * @return
	 */
	@Override
	public List<HandoverHead> getHandoerCalcList(String ininvTypeId,String orgId){
		return priceHeadDao.getHandoerCalcList(ininvTypeId,orgId);
	}
	/**
	 * 交接单取交接单行表下指定的入库核价类型的大类
	 * @param headId 交接单ID
	 * @param ininvTypeId 入库核价类型
	 * @return
	 */
	public List<ItemClass> getHandoerItemClassByHeadId(String headId,String ininvTypeId,String articleTypeId,String type){
		return priceHeadDao.getHandoerItemClassByHeadId(headId, ininvTypeId,articleTypeId,type);
	}
	public List<ArticleType> getArticleTypeClassByHeadId(String headId,String ininvTypeId,String itemClassId,String type){
		return priceHeadDao.getArticleTypeClassByHeadId(headId, ininvTypeId,itemClassId ,type);
	}
	/**
	 * 大小类，供应商下的款式
	 * @param itemClassId
	 * @param ornaClassId
	 * @param CustId
	 * @return
	 */
	public Pager getStyleByCalc(Map<String, String> condition){
		return priceHeadDao.getStyleByCalc(condition);
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
		return priceHeadDao.loadPriceByIniv(handoverBillId, inivType, itemClassId, ornaClassId, styleId,id);
	}

	@Override
	public Pager getHandoerCalcListPage(Map<String, String> condition) {
		return priceHeadDao.getHandoerCalcListPage(condition);
	}

	@Override
	public Style getStyleByToStyle(Map<String, String> condition) {
		return priceHeadDao.getStyleByToStyle(condition);
	}

	@Override
	public String getBasicPriceCoefficient(Map<String, String> condition) {
		return priceHeadDao.getBasicPriceCoefficient(condition);
	}

	@Override
	public Pager loadPriceByInivPage(Map<String, String> condition) {
		return priceHeadDao.loadPriceByInivPage(condition);
	}

	@Override
	public PriceHead getPriceHeadByNo(String headid) {
		return priceHeadDao.getPriceHeadByNo(headid);
	}

	@Override
	public MaterNoActive getMaterNoActiveByOrnaCode(String ornaCode) {
		return priceHeadDao.getMaterNoActiveByOrnaCode(ornaCode);
	}
	@Override
	public MaterActive getMaterActiveByOrnaCode(String ornaCode) {
		return priceHeadDao.getMaterActiveByOrnaCode(ornaCode);
	}

	@Override
	public String getCalcCertPrice(Map<String, String> condition) {
		return priceHeadDao.getCalcCertPrice(condition);
	}

	@Override
	public String getCalcStoneMainCoefficient(Map<String, String> condition) {
		return priceHeadDao.getCalcStoneMainCoefficient(condition);
	}

	@Override
	public PriceStoneLine getMainStoneLineByHeadId(String headId) {
		return priceHeadDao.getMainStoneLineByHeadId(headId);
	}

	@Override
	public String getEmpInivCalcCode(String userId, String type) {
		return priceHeadDao.getEmpInivCalcCode(userId, type);
	}

	@Override
	public List<Tag> printLabels(String[] ids) {
		List<Tag> listT = new ArrayList<Tag>();
		for (int i = 0; i < ids.length; i++) {
			PriceHead m = this.priceHeadDao.getPriceHead(ids[i]);
			//MaterIniv m = this.priceHeadDao.getMaterInivById(ids[i]);//入库实体			
			Tag t = new Tag();//打印实体
			t.setLabelType(m.getLabelId());
			t.setOrnaCode(m.getWlCode());
			t.setOrnaBarCode(m.getOrnaBarCode());
			t.setOrnaName("");
			t.setPosAmount(m.getBasicPrice());
			t.setMainWeight(m.getMainWeight());
			t.setPartStoneWeight(m.getSecondWeight());
			t.setAllQty(m.getAllWeight());
			//t.setSpecialWeight();
			//t.setSizeName();
			t.setQuality(BdCommon.getQualityName(m.getQualityId()));
			t.setMainShape(DictUtil.getValue("diashape",m.getDmShapeId()));
			t.setPartStoneContent(DictUtil.getValue("secstone",m.getSecondDsc()));
			t.setCleanName(DictUtil.getValue("diaclean",m.getCleanId()));
			t.setColor(DictUtil.getValue("diacolor", m.getColorId()));
			t.setColorGrade(DictUtil.getValue("diacolorgrade", m.getColorGreadId()));
			t.setAnalysisArange(bdCommonDao.getAnalysisNameById(m.getAnalysisArangeId()));
			t.setThemeName(m.getThemeCoefficient());
			t.setFactoryStyle(m.getFactoryStyleId());
			t.setVenderCode(BdCommon.geVendorSupplierCode(m.getVender()));
			//t.setStringWorkPrice();

			if(m.getItemClassId().equals("146")&&//2012.07.13 修改
					(m.getOrnaClassId().equals("59")||m.getOrnaClassId().equals("65"))){
				
			}else{
				if(null!=m.getSpecialWorkPrice()&&!"".equals(m.getSpecialWorkPrice())){	
					t.setSpecialWorkPrice(m.getSpecialWorkPrice()+"");
				}else{
					if(null!=m.getSpecialWorkPrice()&&!"".equals(m.getSpecialWorkPrice())){	
						t.setSpecialWorkPrice(m.getSpecialWorkPrice()+"");
					}else{//
						if("117".equals(m.getVender())){//供应商为明牌
							t.setSpecialWorkPrice(BdCommon.getParameterValue("verdor_charge_fare_xs"));
						}else{
							t.setSpecialWorkPrice(BdCommon.getParameterValue("verdor_charge_price_xs"));
						}
						
					}
				}
			}
			
			t.setItemClassName(BdCommon.getItemClassDesc(m.getItemClassId()));
			t.setOrnaClassName(BdCommon.getOrnaClassDesc(m.getOrnaClassId()));
			t.setGrian(m.getAllNumber());
			//t.setIsMutiPart();
			t.setIdent(m.getIdentId()==null?"":m.getIdentId());
			t.setStyleItemCode(this.bdCommonDao.getStyleItemCodeById(m.getStyleItemId()));//12-05-07修改
			t.setHRDCert(m.getHrdCert()==null?"":m.getHrdCert());
			t.setGIACert(m.getGiaCert()==null?"":m.getGiaCert());
			t.setIGICert(m.getIgiCert()==null?"":m.getIgiCert());
			t.setAGSCert(m.getAgsCert()==null?"":m.getAgsCert());
			//t.setLuckDsc();
			//t.setBornImg();
			listT.add(t);
		}
		
		return listT;
	}

	@Override
	public List<PurchaseHead> getDZcode(Map<String, String> condition) {
		return this.priceHeadDao.getDZcode(condition);
	}

	@Override
	public String getUnitPrice(Map<String, String> condition) {
		return this.priceHeadDao.getUnitPrice(condition);
	}

	@Override
	public CashProdAccount getZUNums(Map<String, String> condition) {
		// TODO Auto-generated method stub
		return this.priceHeadDao.getZUNums(condition);
	}

	@Override
	public List<CashProdAccount> getStoneNum(Map<String, String> condition) {
		// TODO Auto-generated method stub
		return this.priceHeadDao.getStoneNum(condition);
	}

	@Override
	public List<PriceStoneLine> getPriceStoneNum(Map<String, String> condition) {
		// TODO Auto-generated method stub
		return this.priceHeadDao.getPriceStoneNum(condition);
	}

	@Override
	public String getVendorCoefficient(Map<String, String> condition) {
		// TODO Auto-generated method stub
		return this.priceHeadDao.getVendorCoefficient(condition);
	}

	@Override
	public SpecialCharge getSpecialChargeNum(String weight) {
		return this.priceHeadDao.getSpecialChargeNum(weight);
	}

	@Override
	public List<MaterIniv> getOldOrnaCodeByHeadId(String headId) {
		HandoverHead hhead = this.handoverManager.getHandoverHead(headId);
		if(hhead==null)
			return null;
		else if("TL".equals(hhead.getSourceType())){//TL 退料单 JAT_PROC_EXIT_HEAD
			return this.priceHeadDao.getTLCodeById(hhead.getSourceId());
		}else if("LT".equals(hhead.getSourceType())||//LT 料提纯  JAT_PROC_CHANGE_HEAD 
				"XT".equals(hhead.getSourceType())){// XT 形态转换单  JAT_PROC_CHANGE_HEAD
			return this.priceHeadDao.getLTXTCodeById(hhead.getSourceId());
		}
		return null;
		
	}
	
	@Override
	public List<MaterIniv> getOldOrnaCodeByHeadId(String headId, String ornaCode) {
		HandoverHead hhead = this.handoverManager.getHandoverHead(headId);
		if(hhead==null)
			return null;
		else if("TL".equals(hhead.getSourceType())){//TL 退料单 JAT_PROC_EXIT_HEAD
			return this.priceHeadDao.getTLCodeById(hhead.getSourceId(), ornaCode);
		}else if("LT".equals(hhead.getSourceType())||//LT 料提纯  JAT_PROC_CHANGE_HEAD 
				"XT".equals(hhead.getSourceType())){// XT 形态转换单  JAT_PROC_CHANGE_HEAD
			return this.priceHeadDao.getLTXTCodeById(hhead.getSourceId(), ornaCode);
		}
		return null;
		
	}

	@Override
	public String getMaterInivCountByWlCode(String wlCode) {
		return this.priceHeadDao.getMaterInivCountByWlCode(wlCode);
	}

	@Override
	public void updateMaterInivCustomizeNoByWlCode(String dzCode, String WlCode) {
		this.priceHeadDao.updateMaterInivCustomizeNoByWlCode(dzCode, WlCode);
	}

	@Override
	public void updateMaterActionCustomizeNoByWlCode(String dzCode,String WlCode) {
		this.priceHeadDao.updateMaterActionCustomizeNoByWlCode(dzCode, WlCode);
	}

	@Override
	public String getVerdorChargeNum(String verdorId, String styleId) {
		return this.priceHeadDao.getVerdorChargeNum(verdorId, styleId);
	}
}
