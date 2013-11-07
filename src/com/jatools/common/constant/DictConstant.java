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
	public static final String BILL_STATUS_CLOSED_PART							= "7"; //部分关闭
	public static final String BILL_STATUS_CLOSED								= "8"; //关闭
	public static final String BILL_STATUS_CLOSING								= "9"; //预关闭
	public static final String BILL_STATUS_RECEIVED								= "10"; //已接收
	public static final String BILL_STATUS_INSTOCK								= "11"; //已入库
	public static final String BILL_STATUS_REFERENCE							= "12"; //已引用
	public static final String BILL_STATUS_DISCARD								= "13"; //已作废
	public static final String BILL_STATUS_RECEIVING							= "14"; //接收中
	public static final String BILL_STATUS_MERGED								= "15"; //已合并
	public static final String BILL_STATUS_CANCEL								= "16"; //已取消
	public static final String BILL_STATUS_PACKED								= "17"; //已装箱
	public static final String BILL_STATUS_REVOKE								= "18"; //撤销
	
	public static final String BILL_STATUS_NO_DED_MONEY							= "19";//未扣款
	public static final String BILL_STATUS_DED_MONEYED							= "20";//已扣款
	public static final String BILL_STATUS_NO_RETURN_MONEY						= "21";//未退款
	public static final String BILL_STATUS_RETURN_MONEYED						= "22";//已退款
	public static final String BILL_STATUS_NO_REC_MONEY							= "23";//未收款
	public static final String BILL_STATUS_REC_MONEYED							= "24";//已收款
	/**支持加盟系统引入状态**/
	public static final String BILL_STATUS_ESTIMATED							= "25";//已生成结算单
	public static final String BILL_STATUS_NO_ESTIMATE							= "26";//未生成结算单
	/**支持推式模块引入状态**/
	public static final String BILL_STATUS_CREATE_GATHER_ORDER					= "34"; //已生成总单
	public static final String BILL_STATUS_CREATE_PURCHASE						= "35"; //已生成采购单
	public static final String BILL_STATUS_CUST_EVAL							= "36"; //已估价
	public static final String BILL_STATUS_PUR_REVIEWED							= "37"; //采购岗审批同意
	public static final String BILL_STATUS_MOVED								= "39"; //已生成调拨单
	
	/**现有量表状态**/
	public static final String BILL_STATUS_MATER_ACTIVE_VALID					= "900"; //有效
	public static final String BILL_STATUS_MATER_ACTIVE_USED					= "901"; //保留
	public static final String BILL_STATUS_MATER_ACTIVE_ONWAY					= "902"; //在途
	public static final String BILL_STATUS_MATER_ACTIVE_INVALID					= "903"; //失效
	
	/**饰品调拨单调拨类型**/
	public static final String MOVE_TYPE										= "movetype";
	public static final String MOVE_TYPE_MANUAL									= "1";//手工调拔
	public static final String MOVE_TYPE_DISPATCH								= "2";//配货调拨
	public static final String MOVE_TYPE_RETURN									= "3";//退货调拨
	public static final String MOVE_TYPE_RETURNFAIL								= "4";//退残调拨
	/**调拨单类型**/
	public static final String MOVE_BILL_TYPE									= "movebilltype";
	public static final String MOVE_BILL_TYPE_DIAOBODAN							= "1";//调拨单
	public static final String MOVE_BILL_TYPE_TUIHUODAN							= "2";//退货单
	public static final String MOVE_BILL_TYPE_TUICANDAN							= "3";//退残单
	public static final String MOVE_BILL_TYPE_YIKUDAN							= "4";//移库单
	public static final String MOVE_BILL_TYPE_GUIZUDIAOBODAN					= "5";//柜组调拨单
	/**装箱单类型**/
	public static final String MOVE_PACK_BILL_TYPE								= "packbilltype";
	public static final String MOVE_PACK_BILL_TYPE_SHIPING						= "1";//饰品装箱单
	public static final String MOVE_PACK_BILL_TYPE_WEIXIU						= "2";//维修装箱单
	public static final String MOVE_PACK_BILL_TYPE_JIAOJIE						= "3";//交接装箱单
	public static final String MOVE_PACK_BILL_TYPE_XINGTAI						= "4";//形态转换装箱单
	/**仓库**/
	public static final String INVCODE											= "invcode";
	public static final String INVCODE_ORNA										= "1";//饰品库
	public static final String INVCODE_OLD_ORNA									= "2";//旧品库
	
	/**计划系统单据状态**/
	public static final String PLAN_BILL_STATUS									= "planstatus";
	public static final String PLAN_BILL_STATUS_SAVE							= "1";//保存
	public static final String PLAN_BILL_STATUS_MARKED							= "2";//记账
	public static final String PLAN_BILL_STATUS_CLOSED							= "8";//关闭
	public static final String PLAN_BILL_STATUS_PACKED							= "38";//已装箱 
	public static final String PLAN_BILL_STATUS_PACK_RECEIVED					= "40";//装箱单已接收
	public static final String PLAN_BILL_STATUS_PLAN_REVIEWED					= "44";//计划岗审批同意
	public static final String PLAN_BILL_STATUS_PUR_REVIEWED					= "45";//采购岗审批同意
	public static final String PLAN_BILL_STATUS_GATHERED						= "55";//已汇总
	public static final String PLAN_BILL_STATUS_PURCHASED						= "69";//已生成采购单
	public static final String BILL_STATUS_APPLY_DELETE    						= "70";//已申请删除

	/**单据编码**/
	public static final String BILL_CODE										= "billcode";
	
	/**单据编码**/
	public static final String PURCHASE_EMP										= "purchaseEmp";
	
	/**品质等级**/
	public static final String GRADE_LEVEL										= "gradelevel";
	
	/**钻石色级**/
	public static final String DIA_COLORGRADE									= "diacolorgrade";
	
	/**钻石净度**/
	public static final String DIA_CLEAN										= "diaclean";
	
	/**钻石形状**/
	public static final String DIA_SHAPE										= "diashape";
	
	/**钻石颜色**/
	public static final String DIA_COLOR										= "diacolor";
	
	/**钻石切工**/
	public static final String DIA_CUT											= "diacut";
	
	/**钻石荧光**/
	public static final String DIA_FLUOR										= "diafluor";
	
	/**钻石抛光**/
	public static final String DIA_POLI											= "diapoli";
	
	/**钻石底尖**/
	public static final String DIA_VERTEX										= "diavertex";
	
	/**钻石腰围**/
	public static final String DIA_WAIS											= "diawais";
	
	/**钻石对称性**/
	public static final String DIA_SYMM											= "diasymm";
	
	/**钻石修饰度**/
	public static final String DIA_MODI											= "diamodi";
	/**交接单业务类型  采购**/
	public static final String BILL_JJ_CG										= "0"; //采购
	/**交接单业务类型 委外**/
	public static final String BILL_JJ_WW										= "1"; //委外
	
	public static final String BILL_JJ_ZYZH										= "2"; //自有转换
	/**交接退料**/
	public static final String BILL_JJ_TL										= "-1"; //
	/**委外发料**/
	public static final String BILL_JJ_FL										= "1"; //
	/**不合格台账中原料类型**/
	public static final String BILL_INVALID_PROD_MTYPE							= "N0"; //
	/**资金台账 业务类型  ： 应收**/
	public static final String BILL_JJ_YS										= "-1"; //
	/**资金台账 业务类型  ： 应付**/
	public static final String BILL_JJ_YF										= "1"; //

	/**总部财务组织**/
	public static final String HQ_ORGS											= "hqorgs";
	
	/**购物类型**/
	public static final String ORDER_TYPE										= "ordertype";
	public static final String ORDER_TYPE_ZHENGCHANGE							= "1";//正常购物
	public static final String ORDER_TYPE_JINJIGOUWU							= "2";//紧急购物
	public static final String ORDER_TYPE_DINGZUODAN							= "3";//定做单
	public static final String ORDER_TYPE_TUISHIXIADAN							= "4";//推式下单
	
	/**要货分类**/
	public static final String ORDER_KIND										= "orderkind";
	public static final String ORDER_KIND_ZHENGCHANG							= "1";//正常下单
	public static final String ORDER_KIND_TAOZHUANG								= "2";//套装下单
	public static final String ORDER_KIND_FEIMINGXI								= "3";//非明细下单
	public static final String ORDER_KIND_TUISHI								= "4";//推式下单
	/**配货执行功能**/
	public static final String DISPATCH_DO_TYPE									= "dispatchdotype";
	/**采购业务类型**/
	public static final String  PUR_BILL_TYPE									= "purbilltype";
	/**采购单类型**/
	public static final String  PUR_BIZ_TYPE									= "purbiztype";
	/**快递发运方式**/
	public static final String MOVE_PACK_EXPRESS_MODE							= "movepackexpressmode";
	/**柜组**/
	public static final String GROUPS											= "groups";
	
	/**交接单 收票状态**/
	public static final String HANDOVER_IS_BILL									= "1";
	/**交接单 未收票状态**/
	public static final String HANDOVER_IS_NOT_BILL								= "0";
	
	/**对公销售单 出票状态**/
	public static final String OUT_VENDOR_IS_BILL								= "1";
	/**对公销售单 未出票状态**/
	public static final String OUT_VENDOR_IS_NOT_BILL							= "0";
	
	/**原料类型**/
	public static final String MATERIAL_TYPE									= "materialtype";
	/**结算单位**/
	public static final String PURUNIT											= "purunit";
	/**托架颜色**/
	public static final String BRACKET_COLOR									= "bracketcolor";
	
	/**扫描未更换的标签**/
	public static final String LABEL_TAG_TYPE_1									= "1";
	/**扫描已更换的标签**/
	public static final String LABEL_TAG_TYPE_2									= "2";
	/**是否锁定价格**/
	public static final String PRICE_LOCK										= "1";
	public static final String PRICE_NO_LOCK									= "0";
	
	/**拆包混包业务类型**/
	public static final String PACKAGE_TYPE										= "packagetype";
	/**标签打印 接收方式**/
	public static final String LABEL_TAG_APPLY_PRINT							= "labelTagApplyPrint";
	/**原料钻石**/
	public static final String ITEM_CLASS_YUANLIAOZUANSHI						= "156";
	/**标签种类**/
	public static final String LABEL_KIND										= "labelkind";
	/**资金台账 业务类型**/
	public static final String MONEY_DO_TYPE									= "money_dotype";
	/**调整单业务类型**/
	public static final String CHANGE_DO_TYPE									= "change_dotype";
	/**调整单单据类型**/
	public static final String CHANGE_BILL_TYPE									= "change_bill_type";
	/**货品台账 业务类型**/
	public static final String PROD_DOTYPE										= "prod_dotype";
	
	/**推式配货比例权重**/
	public static final String PUSH_DISPATCH_WEIGHTS							= "pushDispatchWeights";
	public static final String PUSH_DISPATCH_WEIGHTS_STOCK_NUM					= "stockNumWeights";//库存数量权重
	public static final String PUSH_DISPATCH_WEIGHTS_GRADE_NUM					= "gradeNumWeights";//品质数量权重
	public static final String PUSH_DISPATCH_WEIGHTS_STYLE_NUM					= "styleNumWeights";//款式数量权重
	public static final String SPECIAL_DIAM_ORG_TYPE							= "specialDiamOrgType";//款式数量权重

	public static final String GOLD_SPECIAL_WORD_TYPE							= "gold_spe_word_type";//素金小克重特殊工费类型
}
