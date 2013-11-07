package com.jatools.common.intf;

public interface ReviewBill {
	/**
	 * 审批单据编码
	 * 在com.jatools.common.constant.GlobalConstant中配置
	 */
	public String getBillCode();
	/**
	 * 单据id
	 */
	public String getBillid();
	/**
	 * 单据编号
	 * 通过commonDao自动生成
	 */
	public String getBillno();
	/**
	 * 单据展示页面url
	 */
	public String getPageUrl();
	
	/**
	 * 配置dao的spring bean的id
	 * 需要实现接口ReviewActionIntf
	 * @return
	 */
	public String getBeanName();
	/**
	 * 获取组织id
	 * @return
	 */
	public String getOrgId();
}
