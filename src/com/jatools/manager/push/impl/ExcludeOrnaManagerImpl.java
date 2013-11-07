package com.jatools.manager.push.impl;

import java.util.List;
import java.util.Map;

import org.bouncycastle.crypto.RuntimeCryptoException;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.dao.push.ExcludeOrnaDao;
import com.jatools.manager.push.ExcludeOrnaManager;
import com.jatools.vo.push.ExcludeOrna;
import com.jatools.web.util.DateUtil;

public class ExcludeOrnaManagerImpl implements ExcludeOrnaManager {
	private ExcludeOrnaDao excludeOrnaDao;

	public void setExcludeOrnaDao(ExcludeOrnaDao excludeOrnaDao) {
		this.excludeOrnaDao = excludeOrnaDao;
	}

	/**
	 * 获取分页数据
	 */
	public Pager getExcludeOrnaPageData(Map<String, String> condition){
		return excludeOrnaDao.getExcludeOrnaPageData(condition);
	}
	/**
	 * 保存
	 */
	public void saveExcludeOrna(String ornaCode, String userId) {
			ExcludeOrna dn = new ExcludeOrna();
			dn.setOrnaCode(ornaCode);
			dn.setCreateDate(DateUtil.getCurrentDate18());
			dn.setCreateId(userId);
			dn.setStatus(DictConstant.BILL_STATUS_SAVE);
			excludeOrnaDao.saveExcludeOrna(dn);
	}
	/**
	 * 保存或修改
	 */
	public void saveExcludeOrna(List<String> ornaCodeList, String userId){
		for(String ornaCode:ornaCodeList){
			if(checkExcludeOrnaRepeat(ornaCode)>0){
				throw new RuntimeException("该饰品编码["+ornaCode+"]已经存在"); 
			}
			ExcludeOrna dn = new ExcludeOrna();
			dn.setOrnaCode(ornaCode);
			dn.setCreateDate(DateUtil.getCurrentDate18());
			dn.setCreateId(userId);
			dn.setStatus(DictConstant.BILL_STATUS_SAVE);
			excludeOrnaDao.saveExcludeOrna(dn);
		}
	}
	/**
	 * 检查记录是否存在
	 */
	public Integer checkExcludeOrnaRepeat(String ornaCode){
		return excludeOrnaDao.checkExcludeOrnaRepeat(ornaCode);
	}
	/**
	 * 删除
	 */
	public void deleteExcludeOrna(List<String> billIdList){
		excludeOrnaDao.deleteExcludeOrna(billIdList);
	}
	/**
	 * 根据编码获取现有量信息
	 */
	public ExcludeOrna getMaterActiveByCode(String code, boolean ornaFlag){
		ExcludeOrna orna = excludeOrnaDao.getMaterActiveByCode(code, ornaFlag);
		return orna;
	}
	public Pager queryExcludeOrnaPageData(Map<String, String> condition){
		return excludeOrnaDao.queryExcludeOrnaPageData(condition);
	}
}
