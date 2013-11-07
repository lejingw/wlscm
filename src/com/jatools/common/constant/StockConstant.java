package com.jatools.common.constant;

public final class StockConstant {
	/**分析单位名称**/
	public static final String ANALYSIS_UNIT_NAME_JZ = "JZ";//金重
	public static final String ANALYSIS_UNIT_NAME_CT = "CT";//克拉
	public static final String ANALYSIS_UNIT_NAME_XJ = "XJ";//销价
	
	public static final String DISPATCH_ORNA_OLDFLAG_OLD = "1";//过时分配
	public static final String DISPATCH_ORNA_OLDFLAG_NEW = "0";//未过时分配
	
	/**配货饰品记录类型**/
	public static final String DISPATCH_FLAG_MANUAL_SUB = "2";//手工减库配货（即先正常配货1，再手工减库生成采购单，减掉的配货记录）
	public static final String DISPATCH_FLAG_DISPATCH = "1";//正常配货
	public static final String DISPATCH_FLAG_SUB_TEMP = "0";//减库配货
	
	/*仓库id*/
	public static final String STOCK_NEW_ID = "1";// 新饰品库
	public static final String STOCK_OLD_ID = "2";// 旧饰品库
	
	/*拆包混包 业务类型*/
	public static final String PACKAGE_TYPE_0 = "0";// 拆包
	public static final String PACKAGE_TYPE_1 = "1";// 混包
	public static final String PACKAGE_TYPE_2 = "2";// 原料分割
	
	/*台账类型*/
	public static final String PROD_ACCOUNT_TYPE_1 = "1";// 委外发料
	public static final String PROD_ACCOUNT_TYPE_2 = "-1";// 交接退料
	
	public static final double WEIGHT_UP = -0.1;// 重量上限
	public static final double WEIGHT_DOWN = 0.1;// 重量下限
}
