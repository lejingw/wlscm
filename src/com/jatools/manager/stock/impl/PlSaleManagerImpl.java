package com.jatools.manager.stock.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.dao.stock.MaterActiveDao;
import com.jatools.dao.stock.PlSaleDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.stock.PlSaleManager;
import com.jatools.vo.stock.PlSaleHead;
import com.jatools.vo.stock.PlSaleLine;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.ws.remote.WorkflowService;

public class PlSaleManagerImpl extends BaseManager implements PlSaleManager {

	@SuppressWarnings("rawtypes")
	@Override
	public Pager getPlSaleData(Map<String, String> condition) {
		return this.plSaleDao.getPlSaleData(condition);
	}

	@Override
	public void saveOrUpdatePlSaleHead(PlSaleHead PlSaleHead,
			List<PlSaleLine> lines, String deleteIds, String userId) {
		this.saveUpdateCheckPlSaleHead(PlSaleHead, lines, deleteIds, userId, false);
	}

	@Override
	public void saveAndCheckPlSaleHead(PlSaleHead PlSaleHead,
			List<PlSaleLine> lines, String deleteIds, String userId) {
		this.saveUpdateCheckPlSaleHead(PlSaleHead, lines, deleteIds, userId, true);
	}
	
	private void saveUpdateCheckPlSaleHead(PlSaleHead PlSaleHead, List<PlSaleLine> lines, String deleteIds, String userId, boolean isCheck){
		PlSaleHead.setUpdateDate(DateUtil.getCurrentDate18());
		PlSaleHead.setUpdateId(userId);
		if(StringUtil.isNotBlank(PlSaleHead.getBillid())){
			asertStatus("jat_plsale_head", "billid", PlSaleHead.getBillid(), "status", DictConstant.BILL_STATUS_SAVE);
			this.plSaleDao.updatePlSaleHead(PlSaleHead);
		} else {
			PlSaleHead.setBillno(commonDao.getBillno(GlobalConstant.BILL_PL_SALE));
			PlSaleHead.setCreateDate(DateUtil.getCurrentDate18());
			PlSaleHead.setCreateId(userId);
			this.plSaleDao.savePlSaleHead(PlSaleHead);
		}
		if(StringUtil.isNotBlank(deleteIds)){
			String lineids[] = deleteIds.split(";");
			for(String lineid : lineids){
				if(StringUtil.isNotBlank(lineid)){
					this.plSaleDao.deleteLineById(lineid);
				}
			}
		}
		
		if(CollectionUtils.isNotEmpty(lines)){
			for(PlSaleLine line : lines){
				line.setUpdateDate(DateUtil.getCurrentDate18());
				line.setUpdateId(userId);
				if(StringUtil.isNotBlank(line.getLineid())){
					this.plSaleDao.updateLine(line);
				} else {
                    boolean isExists = this.plSaleDao.isExistsOrnaCode(PlSaleHead.getBillid(), line.getOrnaCode());
                    if(isExists) {
                        throw new RuntimeException("饰品编码[" + line.getOrnaCode()+"]已经存在");
                    }
                    isExists = this.plSaleDao.isExistsInOtherBill(PlSaleHead.getBillid(), line.getOrnaCode());
                    if(isExists) {
                        throw new RuntimeException("饰品编码[" + line.getOrnaCode()+"]已经在其他单据中存在");
                    }
					line.setCreateDate(DateUtil.getCurrentDate18());
					line.setCreateId(userId);
					line.setBillid(PlSaleHead.getBillid());
					this.plSaleDao.insertLine(line);
				}
			}
		}
		
		if(isCheck){
			this.plSaleDao.modifyPlSaleHeadStatus(PlSaleHead.getBillid(), DictConstant.BILL_STATUS_REVIEWING, userId);
			this.workflowService.enterReview(PlSaleHead, userId);
		} else {
			this.plSaleDao.modifyPlSaleHeadStatus(PlSaleHead.getBillid(), DictConstant.BILL_STATUS_SAVE, userId);
		}
		
	}
	
	@Override
	public PlSaleHead getPlSaleHead(String billid) {
		return this.plSaleDao.getPlSaleHead(billid);
	}

	@Override
	public void deletePlSaleHead(String billid) {
		asertStatus("jat_plsale_head", "billid", billid, "status", DictConstant.BILL_STATUS_SAVE);
		this.plSaleDao.deleteLineByBillid(billid);
		this.plSaleDao.deletePlSaleHead(billid);
	}

	@Override
	public List<PlSaleLine> getLines(String billid) {
		return plSaleDao.getLines(billid);
	}

	
	public void closePlSale(String billid, String userId) {
		asertStatus("jat_plsale_head", "billid", billid, "status", DictConstant.BILL_STATUS_REVIEWED);
		this.materActiveDao.updateConsignByPlSale(billid, true);
		this.plSaleDao.modifyPlSaleHeadStatus(billid, DictConstant.BILL_STATUS_CLOSED, userId);
	}

    public void destroyPlSale(String billid, String userId) {
        asertStatus("jat_plsale_head", "billid", billid, "status", DictConstant.BILL_STATUS_CLOSED);
        this.materActiveDao.updateConsignByPlSale(billid, false);
        this.plSaleDao.modifyPlSaleHeadStatus(billid, DictConstant.BILL_STATUS_DISCARD, userId);
    }
	
	private CommonDao commonDao;
	private PlSaleDao plSaleDao;
	private WorkflowService workflowService;
	private MaterActiveDao materActiveDao;
	
	
	public void setMaterActiveDao(MaterActiveDao materActiveDao) {
		this.materActiveDao = materActiveDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	@Override
	public CommonDao getCommonDao() {
		return commonDao;
	}
	
	public void setPlSaleDao(PlSaleDao plSaleDao) {
		this.plSaleDao = plSaleDao;
	}
	
	
	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}

    @Override
    public boolean isExistsOrnaCode(String billid, String ornaCode) {
        return this.plSaleDao.isExistsOrnaCode(billid, ornaCode);
    }

    @Override
    public boolean isExistsInOtherBill(String billid, String ornaCode) {
        return this.plSaleDao.isExistsInOtherBill(billid, ornaCode);
    }
}
