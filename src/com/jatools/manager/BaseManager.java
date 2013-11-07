package com.jatools.manager;

import com.jatools.common.constant.DictConstant;
import com.jatools.dao.util.CommonDao;
import com.jatools.web.util.DictUtil;
import com.jatools.web.util.StringUtil;

public abstract class BaseManager {
	public abstract CommonDao getCommonDao();
	/**
	 * 获取单据状态
	 * @return
	 */
	protected String getBillStatus(String tableName, String idField, String idValue, String statusField){
		if(StringUtil.isEmpty(statusField)){
			statusField = "status";
		}
		String sql = "select " + statusField + " as \"status\" from " + tableName + " where " + idField + " = " + idValue;
		String status = getCommonDao().querySingleString(sql);
		return status;
	}
	/**
	 * 判断状态
	 */
	protected void asertStatus(String tableName, String idField, String idValue, String statusField, String asertStatus){
		String status = getBillStatus(tableName, idField, idValue, statusField);
		asertStatus(status, asertStatus);
	}
	/**
	 * 判断状态
	 */
	protected void asertStatus(String realStatus, String asertStatus){
		if(StringUtil.isNotEmpty(realStatus)){
			if(!realStatus.equals(asertStatus)){
				throw new RuntimeException("操作不能继续，单据状态要求为[" + DictUtil.getValue(DictConstant.BILL_STATUS, asertStatus) + "],实际值为[" + DictUtil.getValue(DictConstant.BILL_STATUS, realStatus) + "]");
			}
		}else{
			throw new RuntimeException("不能获取单据状态信息，请检查单据是否已经删除");
		}
	}
	
	/**
	 * 判断状态
	 */
	protected void asertStatus(String tableName, String idField, String idValue, String statusField, String... asertStatusArr){
		String status = getBillStatus(tableName, idField, idValue, statusField);
		asertStatus(status, asertStatusArr);
	}
	
	/**
	 * 判断状态
	 */
	protected void asertStatus(String realStatus, String... asertStatusArr){
		if(StringUtil.isNotEmpty(realStatus)){
			boolean isCon = Boolean.FALSE;
			if(null != asertStatusArr && asertStatusArr.length > 0){
				StringBuffer statusList = new StringBuffer();
				for(String asertStatus : asertStatusArr){
					if(statusList.length() > 0){
						statusList.append(",").append(DictUtil.getValue(DictConstant.BILL_STATUS, asertStatus));
					} else {
						statusList.append(DictUtil.getValue(DictConstant.BILL_STATUS, asertStatus));
					}
					if(realStatus.equals(asertStatus) && !isCon){
						isCon = Boolean.TRUE;
						break;
					}
				}
				if(!isCon){
					throw new RuntimeException("操作不能继续，单据状态要求为[" + statusList.toString() + "],实际值为[" + DictUtil.getValue(DictConstant.BILL_STATUS, realStatus) + "]");
				}
			} else {
				throw new RuntimeException("系统异常");
			}
		}else{
			throw new RuntimeException("不能获取单据状态信息，请检查单据是否已经删除");
		}
	}
}
