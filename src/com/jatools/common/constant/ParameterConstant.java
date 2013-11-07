package com.jatools.common.constant;

public class ParameterConstant {
	/** 拆包混包重量上限名*/
	public static final String STOCK_PACKAGE_WEIGHT_UP = "stock_package_weight_up";
	/** 拆包混包重量下限名*/
	public static final String STOCK_PACKAGE_WEIGHT_DOWN = "stock_package_weight_down";
	

	/**交接单重量误差上限*/
	public static final String HANDOVER_WEIGHT_UP	= "handover_weight_up";	//
	/**交接单重量误差下限*/
	public static final String HANDOVER_WEIGHT_DOWN	= "handover_weight_down";	//
	/**交接单件数误差上限*/
	public static final String HANDOVER_NUM_UP		= "handover_num_up";	//
	
	/**调拨单成本上限*/
	public static final String MOVE_BILL_SUMCOST_LIMIT = "move_bill_sumcost_limit";
	/**装箱单成本上限*/
	public static final String MOVE_PACK_SUMCOST_LIMIT = "move_pack_sumcost_limit";
	
	/**款式图片根目录*/
	public static final String UTIL_STYLE_PIC_BASE_PATH_OUT = "util_style_pic_base_path";
	/**款式图片根目录 内网地址*/
	public static final String UTIL_STYLE_PIC_BASE_PATH_IN = "util_style_pic_base_path2";
	/**款式图片根目录 定做单上传图片*/
	public static final String UTIL_PURCHASE_CUST_STYLE_PIC_BASE_PATH = "util_purchase_cust_style_pic_base_path";
	/**形态转化、拆石、料提纯的供应商id*/
	public static final String CHANGE_VENDOR_ID = "change_vendor_id";
	
	public static final String DINV_UNITCOST_ONE = "diniv_unitcost_one";
	public static final String DINV_UNITCOST_TWO = "diniv_unitcost_two";
	
	/**是否启用起订量*/
	public static final String ENABLE_PURCHASE_NUM_LIMIT= "enable_purchase_num_limit";
	/**订单配货减库提前天数1：表示入库时间为昨天的可以减库，0：表示入库时间为今天的可以减库*/
	public static final String PUR_DISPATCH_SUB_BEYONDDAYS= "pur_dispatch_sub_beyonddays";
	public static final String QUALITY_ZZ_ID = "quality_zz_id";
	
	/**
	 * 总部组织id
	 */
	public static final String HQ_MAIN_ORG_ID = "hq_main_org_id";


    /**
     * 素金入库单 工费限制
     */
    public static final String MATER_INIV_WORK_PRICE = "mater_iniv_work_price";


    public static final String VERDOR_CHARGE_PRICE = "verdor_charge_price";
    public static final String VERDOR_CHARGE_FARE = "verdor_charge_fare";

    public static final String FIXED_GOLD_PRICE_RANGE = "fixed_gold_price_range";// 素金入库单 成本单价与爱尚金固定金价差异值范围


    public static final String DISPATCH_PRICE_RATE = "dispatch_price_rate";// 订单配货差异比例

}
