package com.jatools.manager.stock.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.stock.MaterInivDao;
import com.jatools.manager.bd.BdCommonManager;
import com.jatools.manager.stock.MaterInivManager;
import com.jatools.vo.bd.Tag;
import com.jatools.vo.calc.PriceHead;
import com.jatools.vo.stock.MaterIniv;
import com.jatools.web.util.BdCommon;
import com.jatools.web.util.DictUtil;

public class MaterInivManagerImpl implements MaterInivManager {
	
	private MaterInivDao materInivDao;
	private BdCommonManager bdCommonManager;
	
	public void setBdCommonManager(BdCommonManager dbCommonManager) {
		this.bdCommonManager = dbCommonManager;
	}
	public MaterInivDao getMaterInivDao() {
		return materInivDao;
	}

	public void setMaterInivDao(MaterInivDao materInivDao) {
		this.materInivDao = materInivDao;
	}

	@Override
	public Pager getMaterInivPageData(Map<String, String> condition) {
		// TODO Auto-generated method stub
		return materInivDao.getMaterInivPageData(condition);
	}

	@Override
	public String saveMaterIniv(MaterIniv materIniv) {
		if(materIniv.getNuclearBillId()!=null&&!"".equals(materIniv.getNuclearBillId()))
			materInivDao.updatePriceStateByIniv("12", materIniv.getNuclearBillId());
		return  materInivDao.saveMaterIniv(materIniv);
		
	}

	@Override
	public MaterIniv getMaterInivById(String id) {
		return materInivDao.getMaterInivById(id);
	}

	@Override
	public void updateMaterIniv(MaterIniv materIniv) {
		MaterIniv old = getMaterInivById(materIniv.getId());
		if(old.getNuclearBillId()!=null&&!"".equals(old.getNuclearBillId())){
			materInivDao.updatePriceStateByIniv("1", old.getNuclearBillId());
		}
		if(materIniv.getNuclearBillId()!=null&&!"".equals(materIniv.getNuclearBillId())){
			materInivDao.updatePriceStateByIniv("12", materIniv.getNuclearBillId());
		}
		materInivDao.updateMaterIniv(materIniv);
	}

	@Override
	public void deleteMaterIniv(String id) {
		MaterIniv old = getMaterInivById(id);
		if(old.getNuclearBillId()!=null&&!"".equals(old.getNuclearBillId())){
			materInivDao.updatePriceStateByIniv("1", old.getNuclearBillId());
		}
		materInivDao.deleteMaterIniv(id);
	}

	@Override
	public MaterIniv getMaterIniv(MaterIniv a) {
		return materInivDao.getMaterIniv(a);
	}

	@Override
	public String saveOrUpdateMater(MaterIniv mater) {
		if(mater.getId()==null)
			materInivDao.saveMaterIniv(mater);
		else
			materInivDao.updateMaterIniv(mater);
		return mater.getOrnaCode();
	}

	/**
	 * 取交接单行表中的成本单价
	 * @param handoverBillId
	 * @param itemClassId
	 * @return
	 */
	@Override
	public String getHangPriceByIniv(String handoverBillId,String itemClassId ){
		return materInivDao.getHangPriceByIniv(handoverBillId, itemClassId);
	}

	@Override
	public List<Tag> printLabels(String[] ids) {
		List<Tag> listT = new ArrayList<Tag>();
		for (int i = 0; i < ids.length; i++) {
			MaterIniv m = this.materInivDao.getMaterInivById(ids[i]);//入库实体			
			Tag t = new Tag();//打印实体
			t.setLabelType(m.getSmallLabelType());
			t.setOrnaCode(m.getOrnaCode());
			t.setOrnaBarCode(m.getOrnaBarcode());
			t.setOrnaName(m.getOrnaName());
			if(null!=m.getPosAmount()&&!"".equals(m.getPosAmount()))
				t.setPosAmount(Double.parseDouble(m.getPosAmount()));
			if(null!=m.getMainWeight()&&!"".equals(m.getMainWeight()))
				t.setMainWeight(m.getMainWeight());
			if(null!=m.getPartStoneWeight()&&!"".equals(m.getPartStoneWeight()))
				t.setPartStoneWeight(m.getPartStoneWeight());
			if(null!=m.getAllQty()&&!"".equals(m.getAllQty()))
				t.setAllQty(m.getAllQty());
			if(null!=m.getSpecialWeight()&&!"".equals(m.getSpecialWeight()))
				t.setSpecialWeight(m.getSpecialWeight());
			t.setSizeName(bdCommonManager.getSizeNameById(m.getSizeId()));
			t.setQuality(BdCommon.getQualityName(m.getQualityId()));
			t.setMainShape(DictUtil.getValue("diashape",m.getMainShapeId()));
			t.setPartStoneContent(DictUtil.getValue("secstone",m.getPartContent()));
			t.setCleanName(DictUtil.getValue("diaclean",m.getCleanId()));
			t.setColor(DictUtil.getValue("diacolor", m.getColorId()));
			t.setColorGrade(DictUtil.getValue("diacolorgrade", m.getMainColorGradeId()));
			t.setAnalysisArange(bdCommonManager.getAnalysisNameById(m.getAnalysisArangeId()));
			t.setFactoryStyle(m.getFactoryStyleId());
			t.setVenderCode(BdCommon.geVendorSupplierCode(m.getVenderId()));
			t.setStyleName(bdCommonManager.getStyleNameById(m.getStyleId()));
			
			if(null!=m.getStringWorkPrice()&&!"".equals(m.getStringWorkPrice()))
				t.setStringWorkPrice(Double.parseDouble(m.getStringWorkPrice()));
			
			if(m.getItemClassId().equals("146")&&//2012.07.13 修改
					(m.getOrnaClassId().equals("59")||m.getOrnaClassId().equals("65"))){
				
			}else{
				if(null!=m.getSpecialWorkPrice()&&!"".equals(m.getSpecialWorkPrice())){	
					t.setSpecialWorkPrice(m.getSpecialWorkPrice()+"");
				}else{//
					if("117".equals(m.getVenderId())){//供应商为明牌
						t.setSpecialWorkPrice(BdCommon.getParameterValue("verdor_charge_fare_xs"));
					}else{
						t.setSpecialWorkPrice(BdCommon.getParameterValue("verdor_charge_price_xs"));
					}
					
				}
			}
			
			t.setItemClassName(BdCommon.getItemClassDesc(m.getItemClassId()));
			t.setOrnaClassName(BdCommon.getOrnaClassDesc(m.getOrnaClassId()));
			t.setGrian(m.getTotalNum());
			t.setIsMutiPart(m.getIsMutiPart());
			String them = this.bdCommonManager.getStyleThemeName(m.getStyleId());
//			if("时尚千足金".equals(t.getItemClassName())&&null!=them){
//				t.setIdent(m.getIdentId()==null?""+"$"+them:m.getIdentId()+"$"+them);
//			}else 12-05-07修改
				t.setIdent(m.getIdentId()==null?"":m.getIdentId());
			t.setThemeName(them);
			t.setStyleItemCode(this.bdCommonManager.getStyleItemCodeById(m.getStyleItemId()));//12-05-07修改
			t.setHRDCert(m.getHrdCert()==null?"":m.getHrdCert());
			t.setGIACert(m.getGiaCert()==null?"":m.getGiaCert());
			t.setIGICert(m.getIgiCert()==null?"":m.getIgiCert());
			t.setAGSCert(m.getAgsCert()==null?"":m.getAgsCert());
			t.setLuckDsc(m.getLuckyDsc()==null?"":m.getLuckyDsc());
			t.setBornImg(m.getZodiac()==null?"":m.getZodiac());
			listT.add(t);
		}
		
		return listT;
	}


    @Override
    public Double getFashionGoldPrice(String itemClassId) {
        return this.materInivDao.getFashionGoldPrice(itemClassId);
    }
}
