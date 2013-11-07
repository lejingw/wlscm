package com.jatools.manager.out.impl;

import java.util.List;
import java.util.Map;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.dao.out.OutStockDao;
import com.jatools.dao.out.OutVendorDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.out.OutStockManager;
import com.jatools.vo.out.OutOrna;
import com.jatools.vo.out.OutStockHead;
import com.jatools.vo.out.OutStockLine;
import com.jatools.vo.out.OutVendorLine;
import com.jatools.web.util.StringUtil;
import com.jatools.ws.remote.WorkflowService;

public class OutStockManagerImpl implements OutStockManager {

	private OutStockDao outStockDao;
	private OutVendorDao outVendorDao;
	private CommonDao commonDao;
	private WorkflowService workflowService;
	
	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}


	public void setOutVendorDao(OutVendorDao outVendorDao) {
		this.outVendorDao = outVendorDao;
	}

	public void setOutStockDao(OutStockDao outStockDao) {
		this.outStockDao = outStockDao;
	}

	public Pager getStockHeadPage(Map<String, String> condition) {
		return outStockDao.getStockHeadPage(condition);
	}

	@Override
	public OutOrna getOrnaInfo(String code, boolean ornaFlag) {
		return outVendorDao.getOrna(code, ornaFlag);
	}

	@Override
	public void saveOrUpdateOutStock(OutStockHead head, List<OutStockLine> lines, String[] delIds) {
		String headId = head.getBillid();
		if (StringUtil.isNotEmpty(headId)) {
			if (delIds != null && delIds.length > 0) {
				String ids = "0";
				for (String id : delIds) {
					ids += "," + id;
					List<OutStockLine> list = outStockDao.getStockLines(headId);
					for (OutStockLine line : list) {
						if (StringUtil.equals(line.getLineid(), id)) {
							outVendorDao.changeMaterStatus(line.getOrnaCode(),true,getBillCodeByDoType(head.getDotype()),head.getBillno());// 恢复现有状态
						}
					}
				}
				outStockDao.deleteLines(ids);
			}
			outStockDao.updateHead(head);
		} else {
			String billno = commonDao.getBillno(getBillCodeByDoType(head.getDotype()));
			head.setBillno(billno);
			headId = outStockDao.insertHead(head);
		}
		if(null != lines){			
			for (OutStockLine line : lines) {
				line.setBillid(headId);
				line.setUpdateDate(head.getUpdateDate());
				line.setUpdateId(head.getUpdateId());
				if (StringUtil.isNotEmpty(line.getLineid()))
					outStockDao.updateLine(line);
				else {
					line.setCreateDate(head.getUpdateDate());
					line.setCreateId(head.getUpdateId());
					outStockDao.insertLine(line);
				}
				outVendorDao.changeMaterStatus(line.getOrnaCode(), false,getBillCodeByDoType(head.getDotype()),head.getBillno());
			}
		}
		if (DictConstant.BILL_STATUS_CLOSED.equals(head.getStatus())) {
			moveOrna(head, false);
		} else if (DictConstant.BILL_STATUS_INVALID.equals(head.getStatus())) {
			moveOrna(head, true);
		}else if(DictConstant.BILL_STATUS_REVIEWING.equals(head.getStatus())){
			workflowService.enterReview(head, head.getUpdateId());//进入审批流
		}
	}

	private void moveOrna(OutStockHead head, boolean noActive) {
		List<OutStockLine> list = outStockDao.getStockLines(head.getBillid());
		int inTrans = 1;// 默认是出库
		for (OutStockLine line : list) {
			outVendorDao.moveOrna(line.getOrnaCode(), noActive);
			if (noActive) {// 入库方向
				outVendorDao.deleteOrnaNo(line.getOrnaCode());
				outVendorDao.changeMaterStatus(line.getOrnaCode(), true,null,null);
				inTrans = -1;// 入库，作废，冲上次写入的记录
			} else {// 出库方向
				inTrans = 1;
				outVendorDao.deleteOrna(line.getOrnaCode());
			}
			saveTransByLine(head, line, inTrans);
		}

	}

	/**
	 * 饰品移动，关闭就移走到非现有量并写事物表、作废回现有量 充事物表
	 * 
	 * @param head
	 * @param noActive
	 */
	private void saveTransByLine(OutStockHead head, OutStockLine line, int inTrans) {
		OutVendorLine vendorLine = new OutVendorLine();
		if (inTrans == -1) {
			if (StringUtil.isNotEmpty(line.getPosCost()))
				vendorLine.setTransCost("" + (0 - Double.parseDouble(line.getPosCost())));
			if (StringUtil.isNotEmpty(line.getPosMoney()))
				vendorLine.setTransMoney("" + (0 - Double.parseDouble(line.getPosMoney())));
			if (StringUtil.isNotEmpty(line.getNowQty()))
				vendorLine.setTransQty("" + (0 - Double.parseDouble(line.getNowQty())));
		} else {
			vendorLine.setTransCost(line.getPosCost());
			vendorLine.setTransMoney(line.getPosMoney());
			vendorLine.setTransQty(line.getNowQty());
		}
		vendorLine.setNowQty(line.getNowQty());
		vendorLine.setPosCost(line.getPosCost());
		vendorLine.setPosMoney(line.getPosMoney());
		vendorLine.setAlaysisCode(line.getAlaysisCode());
		vendorLine.setAllQty(line.getAllQty());
		vendorLine.setItemClassId(line.getItemClassId());
		vendorLine.setOrnaClassId(line.getOrnaClassId());
		vendorLine.setStyleItemClass(line.getStyleItemClass());
		vendorLine.setStyleMiddleClass(line.getStyleMiddleClass());
		vendorLine.setStyleOrnaClass(line.getStyleOrnaClass());
		vendorLine.setStyleId(line.getStyleId());
		vendorLine.setAlaysisId(line.getAlaysisId());
		vendorLine.setOrnaCode(line.getOrnaCode());
		vendorLine.setOrnaBarCode(line.getOrnaBarCode());
		vendorLine.setOrnaDsc(line.getOrnaDsc());
		vendorLine.setMemo(line.getMemo());
		vendorLine.setUnitId(line.getUnitId());
		vendorLine.setGrains(line.getGrains());
		vendorLine.setIsPSale(line.getIsPsale());
		vendorLine.setOrgId(head.getOrgId());
		vendorLine.setBillNo(head.getBillno());
		vendorLine.setUpdateDate(head.getUpdateDate());
		vendorLine.setUpdateId(head.getUpdateId());
		String billType = getBillCodeByDoType(head.getDotype());
		String finance = commonDao.getBillFinance(billType);
		vendorLine.setBillType(billType);
		vendorLine.setFinaceType(finance);
		vendorLine.setCreateDate(head.getDodate());
		vendorLine.setStockId(head.getStockId());
		outVendorDao.saveMaterTrans(vendorLine);
	}

	/**
	 * 0丢失1报废2礼品3营销4余金领用
	 * @param dotype
	 * @return
	 */
	private String getBillCodeByDoType(String dotype) {
		if (StringUtil.equals("0", dotype)) {
			return GlobalConstant.BILL_CODE_DIUSHICHUKU;
		} else if (StringUtil.equals("1", dotype)) {
			return GlobalConstant.BILL_CODE_BAOFEICHUKU;
		} else if (StringUtil.equals("2", dotype)) {
			return GlobalConstant.BILL_CODE_LIPINCHUKU;
		} else if (StringUtil.equals("3", dotype)) {
			return GlobalConstant.BILL_CODE_YINGXIAOCHUKU;
		} else if (StringUtil.equals("4", dotype)) {
			return GlobalConstant.BILL_CODE_YUJINLINGYONG;
		}
		return null;
	}

	@Override
	public OutStockHead getStockHead(String headId) {
		return outStockDao.getStockHead(headId);
	}

	@Override
	public List<OutStockLine> getStockLines(String headId) {
		return outStockDao.getStockLines(headId);
	}

	@Override
	public void delOutStock(String billId) {
		List<OutStockLine> list = outStockDao.getStockLines(billId);
		for (OutStockLine line : list) {
			outVendorDao.changeMaterStatus(line.getOrnaCode(), true,null,null);
		}
		outStockDao.deleteLinesByHeadId(billId);
		outStockDao.deleteHead(billId);
	}
}
