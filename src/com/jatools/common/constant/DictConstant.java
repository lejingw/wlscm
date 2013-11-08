package com.jatools.common.constant;

public class DictConstant {
	/**是否**/
	public static final String YES_OR_NO										= "yesorno";
	public static final String YES_OR_NO_YES									= "1";
	public static final String YES_OR_NO_NO										= "0";
	/**存储过程调用成功标志**/
	public static final String CALL_PROCEDURE_SUCCESS_FLAG						= "0";
	/**单据状态**/
	public static final String BILL_STATUS										= "status";
	public static final String BILL_STATUS_INVALID								= "0"; //失效
	public static final String BILL_STATUS_SAVE									= "1"; //保存
	public static final String BILL_STATUS_MARK									= "2"; //记账
	public static final String BILL_STATUS_UN_REVIEW							= "3"; //未审批
	public static final String BILL_STATUS_REVIEWING							= "4"; //审批中
	public static final String BILL_STATUS_REVIEWED								= "5"; //审批完成
	public static final String BILL_STATUS_CLOSED_PART							= "6"; //归档
}
