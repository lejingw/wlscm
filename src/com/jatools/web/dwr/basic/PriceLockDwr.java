package com.jatools.web.dwr.basic;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.basic.PriceLockManager;
import com.jatools.vo.basic.PriceLockHead;
import com.jatools.vo.basic.PriceLockLine;

public class PriceLockDwr {
	private PriceLockManager priceLockManager;

	public void setPriceLockManager(
			PriceLockManager priceLockManager) {
		this.priceLockManager = priceLockManager;
	}

	/**
	 * 获取饰品信息
	 * @param code
	 * @param ornaFlag
	 * @param orgId
	 * @param stockId
	 * @return
	 */
	public PriceLockLine getMaterActiveInfo(String code, boolean ornaFlag, HttpSession session){
		PriceLockLine line = priceLockManager.getMaterActiveInfo(code, ornaFlag);
		if(null == line){
			throw new RuntimeException("不能获取饰品信息");
		}
		if(!CommonUtil.getSessionOrgId(session).equals(line.getOrgId())){
			throw new RuntimeException("饰品所在网点不为当前登录的财务组织");
		}
		checkMaterActiveStatus(line);
		line.setNames();
		return line;
	}
	/**
	 * 检查饰品是否可以锁定价格
	 * @param materActive
	 */
	private void checkMaterActiveStatus(PriceLockLine info){
		if(!"612".equals(info.getUnitId())){
			throw new RuntimeException("饰品计量单位不为件，不能锁定价格");
		}
		if(DictConstant.BILL_STATUS_MATER_ACTIVE_USED.equals(info.getStatus())){
			throw new RuntimeException("该饰品为保留状态，不能锁定价格");
		}
		if(DictConstant.BILL_STATUS_MATER_ACTIVE_ONWAY.equals(info.getStatus())){
			throw new RuntimeException("该饰品为在途状态，不能锁定价格");
		}
		if(!DictConstant.BILL_STATUS_MATER_ACTIVE_VALID.equals(info.getStatus())){
			throw new RuntimeException("该饰品不为有效状态，不能锁定价格");
		}
	}
	/**
	 * 保存
	 */
	public String savePriceLock(PriceLockHead head, List<String> newOrnaCodeList, List<String> deleteOrnaCodeList, HttpSession session){
		head.setOrgId(CommonUtil.getSessionOrgId(session));
		priceLockManager.savePriceLock(head, newOrnaCodeList, deleteOrnaCodeList, CommonUtil.getSessionUserId(session));
		return null;
	}
	
	/**
	 * 删除
	 */
	public String deletePriceLock(String headid){
		PriceLockHead head = priceLockManager.getPriceLockHead(headid);
		if(null == head){
			return "单据不存在，获取已经删除";
		}
		if(!DictConstant.BILL_STATUS_SAVE.equals(head.getStatus())){
			return "只有保存状态的单据才可以删除";
		}
		priceLockManager.deletePriceLock(headid);
		return null;
	}
	
}
