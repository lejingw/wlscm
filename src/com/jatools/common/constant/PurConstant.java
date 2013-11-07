package com.jatools.common.constant;

public final class PurConstant {
	/**业务类型**/
	public static final String BIZ_TYPE_CG = "0";//0采购
	public static final String BIZ_TYPE_WW = "1";//1委外
	
	/**要货分类**/
	public static final String ORDER_KIND_ZHENGCHANG="1";//正常下单
	public static final String ORDER_KIND_FEIMINGXI = "3";//非明细下单
	
	/**采购单类型**/
	public static final String BILL_TYPE_MINGXI = "0";//明细订单
	public static final String BILL_TYPE_FEIMINGXI = "1";//非明细订单
	public static final String BILL_TYPE_DINGZUO = "2";//订做单
	
	/**交接单 行表 大类 id 原料钻石*/
	public static final String HANDOVER_LINE_ITEMCLASSID = "156";//订做单
	
	/**计划要货汇总单，生成采购单方式*/
	public static final String HQ_GATHER_HEAD_DOTYPE_SUB = "1";//1减库生成采购总单
	public static final String HQ_GATHER_HEAD_DOTYPE_UNSUB = "2";//2不减库生成采购总单
	public static final String HQ_GATHER_HEAD_DOTYPE_MANUALSUB = "3";//3手工减库生成采购总单 
	
	/**当前分解数量在session中的key*/
	public final static String NUM_CURRENT_DISPATCH_SESSION_KEY = "numCurrentDispatchSessionKey";
//	/**撤销数量在session中的key*/
//	public final static String NUM_CANCEL_SESSION_KEY = "numCancelSessionKey";
	
	public final static String PURCHASE_HEAD_MANUAL_YES = "1";//手工生成
	public final static String PURCHASE_HEAD_MANUAL_NO = "0";//自动生成
}
