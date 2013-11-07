package com.jatools.manager.out.impl;

import java.util.List;
import java.util.Map;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.dao.out.OutVendorDao;
import com.jatools.manager.out.OutVendorManager;
import com.jatools.vo.out.OutOrna;
import com.jatools.vo.out.OutVendorHead;
import com.jatools.vo.out.OutVendorLine;
import com.jatools.vo.pur.PurchaseHead;
import com.jatools.vo.stock.MaterActive;
import com.jatools.vo.stock.MaterNoActive;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.ws.remote.WorkflowService;

public class OutVendorManagerImpl implements OutVendorManager {
	private OutVendorDao outVendorDao;
	private WorkflowService workflowService;
	
	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}

	public void setOutVendorDao(OutVendorDao outVendorDao) {
		this.outVendorDao = outVendorDao;
	}
	public Pager getVendorHeadPage(Map<String, String> condition) {
		return outVendorDao.getVendorHeadPage(condition);
	}
	public Pager getReportSaleVendorHeadPage(Map<String, String> condition) {
		return outVendorDao.getReportSaleVendorHeadPage(condition);
	}
	public Pager getReportOutVendorHeadPage(Map<String, String> condition) {
		return outVendorDao.getReportOutVendorHeadPage(condition);
	}
	public Pager getReportSendVendorHeadPage(Map<String, String> condition) {
		return outVendorDao.getReportSendVendorHeadPage(condition);
	}
	@Override
	public OutOrna getOrnaInfo(String code, boolean ornaFlag) {
		return outVendorDao.getOrna(code, ornaFlag);
	}
	@Override
	public void saveOrUpdatePubSale(OutVendorHead head,
			List<OutVendorLine> lines, String[] delIds) {
//		head.setDoType("0");
		String headId =head.getHeadId();
		if(StringUtil.isNotEmpty(headId)){
			if(delIds!=null&&delIds.length>0){
				String ids="0";
				for(String id : delIds)
					ids+=","+id;
				outVendorDao.deleteLines(ids);
			}
			outVendorDao.updateHead(head);
		}else{
			headId=outVendorDao.insertHead(head);
		}
		for(OutVendorLine line : lines){
			line.setHeadId(headId);
			line.setUpdateDate(head.getUpdateDate());
			line.setUpdateId(head.getUpdateId());
			if(StringUtil.isNotEmpty(line.getLineId()))
			outVendorDao.updateLine(line);
			else{
				line.setCreateDate(head.getUpdateDate());
				line.setCreateId(head.getUpdateId());
				outVendorDao.insertLine(line);
			}
			outVendorDao.changeMaterStatus(line.getOrnaCode(),false,getBillTypeBydoType(Integer.parseInt(head.getDoType())),head.getBillNo());
			
		}
		if(DictConstant.BILL_STATUS_REVIEWING.equals(head.getStatus())){
			workflowService.enterReview(head, head.getUpdateId());//进入审批流
		}	
			
	}
	private String getBillTypeBydoType(int dotype){
		String billType="";
		switch(dotype){
		case 0:
			billType="DG";
			break;
		case 1:
			billType="DX";
			break;
		case 2:
			billType="WW";
			break;
		}
		return billType;
	}
	@Override
	public OutVendorHead getVendorHead(String headId) {
		return outVendorDao.getVendorHead(headId);
	}
	@Override
	public List<OutVendorLine> getVendorLines(String headId) {
		return outVendorDao.getVendorLines(headId);
	}
	@Override
	public void deleteVendor(String headId) {
		List<OutVendorLine> list=outVendorDao.getVendorLines(headId);
		for(OutVendorLine line : list){
			outVendorDao.changeMaterStatus(line.getOrnaCode(),true,null,null);
		}
		outVendorDao.deleteLinesByHeadId(headId);
		outVendorDao.deleteHead(headId);
	}

	private void moveOrna(OutVendorHead head,boolean noActive){
		List<OutVendorLine> list=outVendorDao.getVendorLines(head.getHeadId());
//		head=outVendorDao.getVendorHead(head.getHeadId());
		if(StringUtil.isEmpty(head.getSaleMoney()))
			head.setSaleMoney(head.getSumMoney());
		if(noActive){
			if(StringUtil.isNotEmpty(head.getSaleMoney()))
				head.setSaleMoney(""+(0-Double.parseDouble(head.getSaleMoney())));
			head.setSumWeight(""+(0-Double.parseDouble(head.getSumWeight())));
		}
		switch(Integer.parseInt(head.getDoType())){
		case 0:
			head.setBillType("DG");
			break;
		case 1:
			head.setBillType("DX");
			break;
		case 2:
			head.setBillType("WW");
			break;
		}
		String finaceType=outVendorDao.getFinaceType(head.getBillType());
		if(!"2".equals(head.getDoType())||("2".equals(head.getDoType())&&"A0".equals(head.getMaterialType())&&StringUtil.isNotEmpty(head.getSaleMoney()))) {//委外当发料类型为金原料也要写资金台帐
			head.setLossMoney(outVendorDao.getLossMoney(head.getVendorId(), head.getSaleMoney()));//取余额
			if(!"0".equals(head.getSaleMoney())&&!"0.0".equals(head.getSaleMoney()))
				outVendorDao.saveCashMoneyAccount(head);
		}
			
		for(OutVendorLine line : list){
			outVendorDao.moveOrna(line.getOrnaCode(), noActive);
			if(noActive){
				outVendorDao.deleteOrnaNo(line.getOrnaCode());
//				outVendorDao.changeMaterStatus(line.getOrnaCode(),true);
				if(StringUtil.isNotEmpty(line.getPosCost()))
					line.setTransCost(""+(0-Double.parseDouble(line.getPosCost())));
				if(StringUtil.isNotEmpty(line.getPosMoney()))
					line.setTransMoney(""+(0-Double.parseDouble(line.getPosMoney())));
				if(StringUtil.isNotEmpty(line.getNowQty()))
					line.setTransQty(""+(0-Double.parseDouble(line.getNowQty())));
			}
			else{
				line.setTransCost(line.getPosCost());
				line.setTransMoney(line.getPosMoney());
				line.setTransQty(line.getNowQty());
				outVendorDao.deleteOrna(line.getOrnaCode());
			}
			line.setOrgId(head.getOrgId());
			line.setBillNo(head.getBillNo());
			line.setUpdateDate(head.getUpdateDate());
			line.setUpdateId(head.getUpdateId());
			line.setBillType(head.getBillType());
			line.setFinaceType(finaceType);
			line.setCreateDate(head.getDoDate());
			line.setStockId(head.getStockId());
			outVendorDao.saveMaterTrans(line);
			if("2".equals(head.getDoType())){
				if(noActive&&StringUtil.isNotEmpty(line.getAllQty()))
					line.setAllQty(""+(0-Double.parseDouble(line.getAllQty())));
				line.setCashUnit(outVendorDao.getUnitFromMaterial(head.getMaterialType(), line.getItemClassId()));
				if("J".equals(line.getCashUnit())){
					line.setAllQty("1");
//				}else if("K".equals(line.getCashUnit())){
				}else if("CT".equals(line.getCashUnit())){
					line.setAllQty(line.getGrains());
				}
				outVendorDao.saveCashProdAccount(head, line);
				if(noActive)
					outVendorDao.changeProdChecked(head.getBillNo());
			}
		}
	}
	@Override
	public void changeHeadStatus(OutVendorHead head) {
		outVendorDao.changeHeadStatus(head);
		String status=head.getStatus();
		if(DictConstant.BILL_STATUS_CLOSED.equals(status))
			moveOrna(head,false);
		else if(DictConstant.BILL_STATUS_DISCARD.equals(status))
			moveOrna(head,true);
			
			
	}
	@Override
	public String getItemClassByMaterial(String materialType) {
		if(StringUtil.isEmpty(materialType))return "";
		return outVendorDao.getItemClassByMaterial(materialType);
	}

	@Override
	public List<PurchaseHead> getPurchaseByVendorId(String vendorId) {
		return outVendorDao.getPurchaseByVendorId(vendorId);
	}

	@Override
	public List<MaterNoActive> getPrintLineByNoMater(String id) {
		return outVendorDao.getPrintLineByNoMater(id);
	}

	@Override
	public List<MaterActive> getPrintLineByMater(String id) {
		return outVendorDao.getPrintLineByMater(id);
	}
	
	public void cashCharge(String billid, String saleMoney, String userId){
		// 查询原工费
		String oldCharge1 = "0";
		OutVendorHead head = this.outVendorDao.getVendorHead(billid);
		if(head != null ){
			if(StringUtil.isNotBlank(head.getSaleMoney())){
				oldCharge1 = head.getSaleMoney();
			} else if(StringUtil.isNotBlank(head.getSumMoney())){
				oldCharge1 = head.getSumMoney();
			}
		}
		if(StringUtil.isBlank(saleMoney)){
			saleMoney = "0";
		}
		double oldCharge = Double.valueOf(oldCharge1);
		double newCharge = Double.valueOf(saleMoney);
		String msg = "金额";
		switch(Integer.parseInt(head.getDoType())){
		case 0:
			head.setBillType("DG");
			head.setDoType("-1");
			break;
		case 1:
			head.setBillType("DX");
			head.setSumMoney(saleMoney);
			break;
		case 2:
			head.setBillType("WW");
			head.setDoType("1");
			msg = "加工费";
			break;
		}
		if(Math.abs(oldCharge-newCharge)<=0.0001){
			throw new RuntimeException(msg+"无变化，无需保存");
		}
		if(oldCharge > 0){
			// 冲销原台账
			if(!"2".equals(head.getDoType())||("2".equals(head.getDoType())&&"A0".equals(head.getMaterialType()))) {
				head.setLossMoney(outVendorDao.getLossMoney(head.getVendorId(), (0-oldCharge)+""));
				head.setSaleMoney((0-oldCharge)+"");
				outVendorDao.saveCashMoneyAccount(head);
			}
		}
		if(newCharge > 0){
			// 写一笔新台账
			if(!"2".equals(head.getDoType())||("2".equals(head.getDoType())&&"A0".equals(head.getMaterialType()))) {
				head.setLossMoney(outVendorDao.getLossMoney(head.getVendorId(), newCharge+""));
				head.setSaleMoney(newCharge+"");
				outVendorDao.saveCashMoneyAccount(head);
			}
		}
		// 修改头信息
		head.setSaleMoney(saleMoney);
		head.setUpdateDate(DateUtil.getCurrentDate18());
		head.setUpdateId(userId);
		this.outVendorDao.cashCharge(head);
	}

    @Override
    public OutVendorHead getVendorHeadByBillno(String billno) {
        return this.outVendorDao.getVendorHeadByBillno(billno);
    }
}
