package com.jatools.common.intf;

public interface ReviewActionIntf {
	/**
	 * 审批通过
	 * @param billId
	 * @param userid
	 * 通常将状态改为 DictConstant.BILL_STATUS_REVIEWED
	 */
	public void reviewSuccess(String billid, String userid);
	/**
	 * 审批不通过
	 * @param billId
	 * @param userid
	 * 通常将状态改为 DictConstant.BILL_STATUS_SAVE
	 */
	public void reviewFail(String billid, String userid);
}
