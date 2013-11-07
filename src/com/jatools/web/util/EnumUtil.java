package com.jatools.web.util;


/**
 * 页面渲染所用的枚举变量对应的汉字信息映射
 */
public class EnumUtil {/*

    *//**
     * 获取性别枚举值所对应的变量信息
     * @param sexValue
     * @return
     *//*
    public static String getMessageForSex(String sexValue) {

        if (StringUtil.isBlank(sexValue)) {

            return "";

        }

        if (StringUtil.equalsIgnoreCase(SexTypeEnum.MAN_TYPE, sexValue)) {

            return "男";

        } else {

            return "女";

        }
    }

    *//**
     * 获取客户状态信息
     * @param cusStateValue
     * @return
     *//*
    public static String getMessageForCusState(String cusStateValue) {

        if (StringUtil.isBlank(cusStateValue)) {

            return "";

        }

        if (StringUtil.equalsIgnoreCase(CustomerStateTypeEnum.CM_CLIENT_STATUS_FREEZE,
            cusStateValue)) {

            return "冻结";

        }
        if (StringUtil.equalsIgnoreCase(CustomerStateTypeEnum.CM_CLIENT_STATUS_LOGOUT,
            cusStateValue)) {

            return "销户";

        }
        if (StringUtil.equalsIgnoreCase(CustomerStateTypeEnum.CM_CLIENT_STATUS_LOST, cusStateValue)) {

            return "挂失";

        }
        if (StringUtil.equalsIgnoreCase(CustomerStateTypeEnum.CM_CLIENT_STATUS_NORMAL,
            cusStateValue)) {

            return "正常";

        }
        if (StringUtil.equalsIgnoreCase(CustomerStateTypeEnum.CM_CLIENT_STATUS_RESERVE,
            cusStateValue)) {

            return "预约";

        }

        return " ";

    }

    *//**
     * 获取客户属性信息
     * @param cusProperty
     * @return
     *//*
    public static String getMessageForCusProperty(String cusProperty) {

        if (StringUtil.isBlank(cusProperty)) {

            return "";

        }

        cusProperty = StringUtil.trim(cusProperty);

        if (StringUtil.equalsIgnoreCase(CustomerPropertyTypeEnum.CUSTOMER_PROPERTY_PERSONAL_TYPE,
            cusProperty)) {

            return "个人客户";

        } else {

            return "企业客户";

        }

    }

    *//**
     * 获取客户类型信息
     * @param cusType
     * @return
     *//*
    public static String getMessageForCusType(String cusType) {

        if (StringUtil.isBlank(cusType)) {

            return "";

        }

        if (StringUtil.equalsIgnoreCase(CustomerTypeEnum.RETAIL_INVESTOR_TYPE, cusType)) {

            return "散户";

        } else if (StringUtil.equalsIgnoreCase(CustomerTypeEnum.NORMAL_INVESTOR_TYPE, cusType)) {

            return "中户";

        } else {
            return "大户";
        }

    }

    *//**
     * 获取客户证件信息类型
     * @param cusType
     * @return
     *//*
    public static String getMessageForCardType(String cardType) {

        if (StringUtil.isBlank(cardType)) {

            return "";

        }
        //这样写真搓，有什么较好办法
        if (StringUtil.equalsIgnoreCase(CertificateTypeEnum.IDENTIFICATION_CARD_TYPE, cardType)) {

            return "身份证";

        }

        if (StringUtil.equalsIgnoreCase(CertificateTypeEnum.HUKOUBO_CARD_TYPE, cardType)) {

            return "户口本";

        }

        if (StringUtil.equalsIgnoreCase(CertificateTypeEnum.JUNGUANZHENG_CARD_TYPE, cardType)) {

            return "军官证";

        }

        if (StringUtil.equalsIgnoreCase(CertificateTypeEnum.BENGUOHUZHAO_CARD_TYPE, cardType)) {

            return "护照";

        }

        return "其他证件";
    }

    *//**
     * 获取客户子账户种类信息类型
     * @param cusType
     * @return
     *//*
    public static String getMessageForAccountType(String childAccountType) {

        if (StringUtil.isBlank(childAccountType)) {

            return "";

        }

        if (StringUtil.equalsIgnoreCase(AccountTypeEnum.JI_FEN_TYPE, childAccountType)) {

            return "积分";

        } else if (StringUtil.equalsIgnoreCase(AccountTypeEnum.JIN_YI_TONG_BAO_TYPE,
            childAccountType)) {

            return "金翼通宝";

        } else if (StringUtil.equalsIgnoreCase(AccountTypeEnum.CASH_TYPE, childAccountType)) {

            return "现金";

        } else {

            return "体验卡";

        }

    }

    *//**
     * 获取子账户状态类型
     * @param accountStateType
     * @return
     *//*
    public static String getMessageForSubAccountStateType(String accountStateType) {

        if (StringUtil.isBlank(accountStateType)) {

            return "";

        }

        if (StringUtil.equalsIgnoreCase(SubAccountStateTypeEnum.ACCOUNT_COMMON_TYPE,
            accountStateType)) {

            return "正常";

        } else if (StringUtil.equalsIgnoreCase(SubAccountStateTypeEnum.ACCOUNT_FREEZE_TYPE,
            accountStateType)) {

            return "冻结";

        } else {

            return "未知";

        }

    }

    *//**
     * 获得交易状态message
     * @param tradeStatus
     * @return
     *//*
    public static String getMessageTradeStatusType(String tradeStatus) {

        if (StringUtil.isBlank(tradeStatus)) {

            return "";

        }

        if (StringUtil.equalsIgnoreCase(TradeStatusTypeEnum.TRADE_DOING_TYPE_ENUM, tradeStatus)) {

            return "正在处理";

        } else if (StringUtil.equalsIgnoreCase(TradeStatusTypeEnum.TRADE_SUCCESSFUL_TYPE_ENUM,
            tradeStatus)) {

            return "成功";

        } else if (StringUtil.equalsIgnoreCase(TradeStatusTypeEnum.TRADE_FAIL_TYPE_ENUM,
            tradeStatus)) {

            return "失败";

        } else {

            return "未知";

        }
    }

    *//**
     * 获取账户流水业务标示message
     * @param bizFlag
     * @return
     *//*
    public static String getMessageTradeBizFlagType(String bizFlag) {

        if (StringUtil.isBlank(bizFlag)) {

            return "";

        }

        if (StringUtil.equalsIgnoreCase(TradeBizFlagTypeEnum.TRADE_AMERCE_TYPE_ENUM, bizFlag)) {

            return "购买产品";

        } else if (StringUtil.equalsIgnoreCase(TradeBizFlagTypeEnum.TRADE_BACK_TYPE_ENUM, bizFlag)) {

            return "退还";

        } else if (StringUtil.equalsIgnoreCase(TradeBizFlagTypeEnum.TRADE_BLUE_FILL_TYPE_ENUM,
            bizFlag)) {

            return "蓝补";

        } else if (StringUtil.equalsIgnoreCase(TradeBizFlagTypeEnum.TRADE_RED_FILL_TYPE_ENUM,
            bizFlag)) {

            return "红充";

        } else if (StringUtil.equalsIgnoreCase(TradeBizFlagTypeEnum.TRADE_FILL_IN_TYPE_ENUM,
            bizFlag)) {

            return "充值";

        } else if (StringUtil.equalsIgnoreCase(TradeBizFlagTypeEnum.TRADE_DAI_KOU_TYPE_ENUM,
            bizFlag)) {

            return "代扣";

        } else {

            return "未知";

        }
    }

    *//**
     * 获取产品类型标示message
     * @param productType
     * @return
     *//*
    public static String getMessageProductType(String productKind) {

        if (StringUtil.isBlank(productKind)) {

            return "";

        }

        if (StringUtil.equalsIgnoreCase(ProductKindEnum.PRODUCT_SELF_FOR_JINTONG_TYPE, productKind)) {

            return "综合类";

        } else if (StringUtil
            .equalsIgnoreCase(ProductKindEnum.PRODUCT_THIRD_PART_TYPE, productKind)) {

            return "资讯类";

        } else if (StringUtil.equalsIgnoreCase(ProductKindEnum.PRODUCT_COMBINED_TYPE, productKind)) {

            return "投顾类";

        } else if (StringUtil.equalsIgnoreCase(ProductKindEnum.PRODUCT_SAVE_MONEY, productKind)) {

            return "理财类";

        } else if (StringUtil.equalsIgnoreCase(ProductKindEnum.PRODUCT_FINANCE_KIND, productKind)) {

            return "资管类";

        } else {

            return "其他";

        }
    }

    *//**
     * 获取产品状态message
     * @param productStatus
     * @return
     *//*
    public static String getMessageProductStatus(String productStatus) {

        if (StringUtil.isBlank(productStatus)) {

            return "";

        }

        if (StringUtil.equalsIgnoreCase(ProductStatusEnum.PDT_BOOK_TYPE, productStatus)) {

            return "预售";

        } else if (StringUtil.equalsIgnoreCase(ProductStatusEnum.PDT_IS_SESSING, productStatus)) {

            return "在售";

        } else if (StringUtil.equalsIgnoreCase(ProductStatusEnum.PDT_READY_TO_SELL, productStatus)) {

            return "待售";

        } else if (StringUtil.equalsIgnoreCase(ProductStatusEnum.PPDT_STOPE_SALE_STATUS_TYPE,
            productStatus)) {

            return "停售";

        } else {

            return "未知";

        }
    }

    *//**
     * 付费方式
     * @param deductionType
     * @return
     *//*
    public static String getMessageDeductionType(String deductionType) {

        if (StringUtil.isBlank(deductionType)) {

            return "";

        }

        if (StringUtil.equalsIgnoreCase(DeductionTypeEnum.BEFOREHAND_TYPE, deductionType)) {

            return "预付费";

        } else if (StringUtil.equalsIgnoreCase(DeductionTypeEnum.AFTERHAND_TYPE, deductionType)) {

            return "后付费";

        } else {

            return "未知";

        }
    }

    *//**
     * 获取订单状态信息message
     * @param orderStatu
     * @return
     *//*
    public static String getMessageOrderStatus(String orderState) {

        if (StringUtil.isBlank(orderState)) {

            return "";

        }

        if (StringUtil.equalsIgnoreCase(OrderStatusTypeEnum.ORDER_ALREAD_BUY, orderState)) {

            return "已订购";

        } else if (StringUtil.equalsIgnoreCase(OrderStatusTypeEnum.ORDER_IS_USING, orderState)) {

            return "消费中";

        } else if (StringUtil.equalsIgnoreCase(OrderStatusTypeEnum.ORDER_USED, orderState)) {

            return "消费完成";

        } else {

            return "未知";

        }

    }

    *//**
     * 获取订单支付状态message 
     * @param paymentState
     * @return
     *//*
    public static String getMessagePaymentStatus(String paymentState) {

        if (StringUtil.isBlank(paymentState)) {

            return "";

        }

        if (StringUtil.equalsIgnoreCase(PaymentStatusTypeEnum.ORDER_PAYMENT_TYPE, paymentState)) {

            return "已支付";

        } else if (StringUtil.equalsIgnoreCase(PaymentStatusTypeEnum.ORDER_NO_PAYMENT_TYPE,
            paymentState)) {

            return "未支付";

        } else {

            return "未知";

        }

    }

    *//**
     * 获取是或者否的message信息
     * @param isOrNotType
     * @return
     *//*
    public static String getMessageIsOrNot(String isOrNotType) {

        if (StringUtil.isBlank(isOrNotType)) {

            return "";

        }

        if (StringUtil.equalsIgnoreCase(IsOrNotTypeEnum.IS_TYPE, isOrNotType)) {

            return "是";

        } else {

            return "否";

        }

    }

    *//**
     * 支付方式的message信息
     * @param payType
     * @return
     *//*
    public static String getMessagePaymentType(String payType) {

        if (StringUtil.isBlank(payType)) {

            return "";

        }

        if (StringUtil.equalsIgnoreCase(PayTypeEnum.CASH_PAY_TYPE, payType)) {

            return "现金支付";

        } else if (StringUtil.equalsIgnoreCase(PayTypeEnum.INTEGRABEL_PAY_TYPE, payType)) {

            return "积分支付";

        } else if (StringUtil.equalsIgnoreCase(PayTypeEnum.PAYMENT_BY_FEE_TYPE, payType)) {

            return "提佣支付";

        } else {

            return "未知";

        }
    }

    *//**
     * 现金支付方式message
     * @param cashPayType
     * @return
     *//*
    public static String getMessageCashPayType(String cashPayType) {

        if (StringUtil.isBlank(cashPayType)) {

            return "";

        }
        if (StringUtil.equalsIgnoreCase(CashPayTypeEnum.NETWORK_BANK_PAY_TYPE, cashPayType)) {

            return "网银";

        } else if (StringUtil.equalsIgnoreCase(CashPayTypeEnum.THE_THIRD_PAY_TYPE, cashPayType)) {

            return "第三方";

        } else if (StringUtil.equalsIgnoreCase(CashPayTypeEnum.POSE_MACHINE_PAY_TYPE, cashPayType)) {

            return "pos机刷卡";

        } else {

            return "未知";

        }
    }

    *//**
     * 获取时长单位message信息
     * @param unitType
     * @return
     *//*
    public static String getMessageUnitType(String unitType) {

        if (StringUtil.isBlank(unitType)) {

            return "";

        }

        if (StringUtil.equalsIgnoreCase(unitType, UnitTypeEnum.YEAR_UNIT_TYPE)) {

            return "年";

        } else if (StringUtil.equalsIgnoreCase(unitType, UnitTypeEnum.HALF_YEAR_UNIT_TYPE)) {

            return "半年";

        } else if (StringUtil.equalsIgnoreCase(unitType, UnitTypeEnum.QUARTER_UNIT_TYPE)) {

            return "季度";

        } else if (StringUtil.equalsIgnoreCase(unitType, UnitTypeEnum.MONTH_UNIT_TYPE)) {

            return "月";

        } else if (StringUtil.equalsIgnoreCase(unitType, UnitTypeEnum.WEEK_UNIT_TYPE)) {

            return "周";

        } else if (StringUtil.equalsIgnoreCase(unitType, UnitTypeEnum.DAY_UNIT_TYPE)) {

            return "日";

        } else if (StringUtil.equalsIgnoreCase(unitType, UnitTypeEnum.HOUR_UNIT_TYPE)) {

            return "小时";

        } else if (StringUtil.equalsIgnoreCase(unitType, UnitTypeEnum.TIME_UNIT_TYPE)) {

            return "次";

        } else {

            return "未知";
        }
    }

    *//**
     * 获取续订状态的枚举类型 
     * @param renewalStatu
     * @return
     *//*
    public static String getMessageRenewalStatus(String renewalStatu) {

        if (StringUtil.isBlank(renewalStatu)) {

            return "";

        }

        if (StringUtil.equalsIgnoreCase(renewalStatu, RenewalStatusTypeEnum.NEED_RENEWAL_TYPE)) {

            return "需要";

        } else if (StringUtil.equalsIgnoreCase(renewalStatu,
            RenewalStatusTypeEnum.NOT_NEED_RENEWAL_TYPE)) {

            return "不需要";

        } else if (StringUtil
            .equalsIgnoreCase(renewalStatu, RenewalStatusTypeEnum.HAD_RENEWAL_TYPE)) {

            return "已经续订";

        } else {

            return "未知";

        }
    }

    *//**
     * 获取订购关系是否有效枚举类型message信息 
     * @param orderRelation
     * @return
     *//*
    public static String getMessageOrderRelation(String orderRelation) {

        if (StringUtil.isBlank(orderRelation)) {

            return "";

        }

        if (StringUtil.equalsIgnoreCase(orderRelation,
            OrderRelationTypeEnum.ORDER_RELATION_EFFECT_TYPE)) {

            return "有效";

        } else if (StringUtil.equalsIgnoreCase(orderRelation,
            OrderRelationTypeEnum.ORDER_RELATION_CANCEL_TYPE)) {

            return "终止";

        } else {

            return "未知";

        }
    }

    *//**
     * 获取三方流水表中的业务标识
     * @param thirdSerialBisiFlag
     * @return
     *//*
    public static String getMessageThirdSerialBisiFlag(String thirdSerialBisiFlag) {

        if (StringUtil.isBlank(thirdSerialBisiFlag)) {

            return "";

        }

        if (StringUtil.equalsIgnoreCase(thirdSerialBisiFlag,
            ThirdSerialBisiFlagTypeEnum.BANK_CHECKOFF_TYPE)) {

            return "银行代扣";

        } else if (StringUtil.equalsIgnoreCase(thirdSerialBisiFlag,
            ThirdSerialBisiFlagTypeEnum.THIRD_PAYMENT_TYPE)) {

            return "支付宝支付";

        } else if (StringUtil.equalsIgnoreCase(thirdSerialBisiFlag,
            ThirdSerialBisiFlagTypeEnum.CANCEL_BANK_CHECKOFF_TYPE)) {

            return "银行代扣取消";

        } else if (StringUtil.equalsIgnoreCase(thirdSerialBisiFlag,
            ThirdSerialBisiFlagTypeEnum.SAVE_JINYITONGBAO_TYPE)) {

            return "金翼宝充值";

        } else {

            return "未知";

        }
    }

    public static String getMessageThirdSerialStatus(String thirdSerialStatus) {

        if (StringUtil.isBlank(thirdSerialStatus)) {

            return "";

        }

        if (StringUtil.equalsIgnoreCase(thirdSerialStatus,
            ThirdSerialStatusTypeEnum.NOT_DEALWITH_TYPE)) {

            return "未处理";

        } else if (StringUtil.equalsIgnoreCase(thirdSerialStatus,
            ThirdSerialStatusTypeEnum.DOING_DEALWITH_TYPE)) {

            return "处理中";

        } else if (StringUtil.equalsIgnoreCase(thirdSerialStatus,
            ThirdSerialStatusTypeEnum.SUCCESSFUL_DEALWITH_TYPE)) {

            return "处理成功";

        } else if (StringUtil.equalsIgnoreCase(thirdSerialStatus,
            ThirdSerialStatusTypeEnum.FAILE_DEALWITH_TYPE)) {

            return "处理失败";
        } else {

            return "未知";
        }

    }

    *//**
     * 是否打开积分购买产品的功能入口
     * @return true:打开；false：不打开
     *//*
    public static boolean isOpenIntegralBuyProduct() {

        String integralFlag = PropertyReader
            .getSystemParameterToSystemParamerMap(Global.SYSTEM_PARAMETER_OPEN_INTEGRAL_BUY_PRODUCT);

        if (StringUtil.isNotBlank(integralFlag) && StringUtil.equalsIgnoreCase(integralFlag, "1")) {

            return true;
        } else {

            return false;
        }
    }

    *//**
     * 是否打开现金购买产品的入口
     * @return true:打开；false：不打开
     *//*
    public static boolean isOpenCashBuyProduct() {

        String integralFlag = PropertyReader
            .getSystemParameterToSystemParamerMap(Global.SYSTEM_PARAMETER_OPEN_CASH_BUY_PRODUCT);

        if (StringUtil.isNotBlank(integralFlag) && StringUtil.equalsIgnoreCase(integralFlag, "1")) {

            return true;
        } else {

            return false;
        }

    }

    *//**
     * 是否打卡金翼通宝购买产品的入口
     * @return true:打开；false：不打开
     *//*
    public static boolean isOpenJytbBuyProduct() {

        String integralFlag = PropertyReader
            .getSystemParameterToSystemParamerMap(Global.SYSTEM_PARAMETER_OPEN_JYTB_BUY_PRODUCT);

        if (StringUtil.isNotBlank(integralFlag) && StringUtil.equalsIgnoreCase(integralFlag, "1")) {

            return true;
        } else {

            return false;
        }

    }

    *//**
     * 获取完整文件名字
     * @param fileName
     * @param fileExt
     * @return
     *//*
    public static String getCompleteFileName(String fileName, String fileExt) {

        if (StringUtil.isBlank(fileName) && StringUtil.isBlank(fileExt)) {

            return "";
        }

        StringBuffer fileNameBuffer = new StringBuffer();

        fileNameBuffer.append(fileName).append(".").append(fileExt);

        return fileNameBuffer.toString();

    }

    *//**
     * 获取币种信息
     * @param cusStateValue
     * @return
     *//*
    public static String getMessageMoneyType(String cusStateValue) {

        String name = "";

        if (StringUtil.isBlank(cusStateValue)) {

            return name;

        }

        if (StringUtil.equalsIgnoreCase(MoneyTypeEnum.RMB, cusStateValue)) {

            name = "人民币";

        } else if (StringUtil.equalsIgnoreCase(MoneyTypeEnum.US, cusStateValue)) {

            name = "美元";

        } else if (StringUtil.equalsIgnoreCase(MoneyTypeEnum.HK, cusStateValue)) {

            name = "港币";

        }

        return name;
    }

    *//**
     * 获取业务标识对应的中文名称（股票交易流水）
     * @param cusStateValue
     * @return
     *//*
    public static String getMessageBusinessFlag(String cusStateValue) {

        String name = "";

        if (StringUtil.isBlank(cusStateValue)) {

            return name;

        }

        if (StringUtil.equalsIgnoreCase(BusinessFlagEnum.NORMAL, cusStateValue)) {

            name = "正常";

        } else if (StringUtil.equalsIgnoreCase(BusinessFlagEnum.BUY_UNRECOGNIZED, cusStateValue)) {

            name = "买入未确认";

        } else if (StringUtil.equalsIgnoreCase(BusinessFlagEnum.SELL_UNRECOGNIZED, cusStateValue)) {

            name = "卖出未确认";

        } else if (StringUtil.equalsIgnoreCase(BusinessFlagEnum.FAILURE_TO_BUY, cusStateValue)) {

            name = "买入失败";

        } else if (StringUtil.equalsIgnoreCase(BusinessFlagEnum.PURCHASE_SUCCESSFUL, cusStateValue)) {

            name = "买入成功";

        } else if (StringUtil.equalsIgnoreCase(BusinessFlagEnum.NORMAL_SELLING_SUCCESS,
            cusStateValue)) {

            name = "正常卖出成功";

        } else if (StringUtil.equalsIgnoreCase(BusinessFlagEnum.FAILURE_TO_SELL, cusStateValue)) {

            name = "卖出失败";

        } else if (StringUtil.equalsIgnoreCase(BusinessFlagEnum.RESULTS_INFORMATION_SYSTEM,
            cusStateValue)) {

            name = "系统结息";

        } else if (StringUtil.equalsIgnoreCase(BusinessFlagEnum.CUSTOMER_TAKE_INTEREST,
            cusStateValue)) {

            name = "客户取息";

        } else if (StringUtil.equalsIgnoreCase(BusinessFlagEnum.CUSTOMER_INTEREST_HAS_BEEN_TAKE,
            cusStateValue)) {

            name = "客户已取息（没有重新派息）";

        } else if (StringUtil.equalsIgnoreCase(BusinessFlagEnum.PAYMENT_SELL, cusStateValue)) {

            name = "兑付卖出";

        } else if (StringUtil.equalsIgnoreCase(BusinessFlagEnum.FAILURE_TO_CONFIRM_PAYMENT_SELL,
            cusStateValue)) {

            name = "兑付卖确认失败";

        } else if (StringUtil.equalsIgnoreCase(
            BusinessFlagEnum.PAYMENT_TO_CONFIRM_A_SUCCESSFUL_SALE, cusStateValue)) {

            name = "兑付卖出确认成功";

        }

        return name;
    }

    *//**
     * 获取投资理财收件箱内获取消息的类型中文名称
     * @param cusStateValue
     * @return
     *//*
    public static String getMessageByType(String cusStateValue) {

        String name = "";

        if (StringUtil.isBlank(cusStateValue)) {

            return name;

        }

        if (TZLCInBoxEnum.INSTATION_TYPE.equals(cusStateValue)) {

            name = "消息";

        } else if (TZLCInBoxEnum.NOTICE_TYPE.equals(cusStateValue)) {

            name = "通知";

        } else if (TZLCInBoxEnum.REPORT_TYPE.equals(cusStateValue)) {

            name = "报告";

        }

        return name;
    }

    *//**
     * 获取投资理财收件箱内获取消息的类型数据库的值
     * @param cusStateValue
     * @return
     *//*
    public static String getValueByBusinessCode(String cusStateValue) {

        String name = "";

        if (StringUtil.isBlank(cusStateValue)) {

            return name;

        }

        String[] codes = cusStateValue.split(";");

        if (codes.length >= 1) {

            name = codes[0];

        }

        return name;
    }

    *//**
     * 格式化问卷问题选项内容
     * @param cusStateValue
     * @return
     *//*
    public static List<Map<String, String>> getQuestionItems(String cusStateValue) {

        List<Map<String, String>> itemMapList = new ArrayList<Map<String, String>>();

        if (StringUtil.isBlank(cusStateValue)) {

            return itemMapList;

        }

        String[] items = cusStateValue.split("\n");

        String itemFlag; //选项标识（如"A"，"B"）

        String itemContent;//选项内容

        String[] itemStr;//选项串 ("选项D：爱的")

        for (int i = 0; i < items.length; i++) {

            Map<String, String> itemMap = new HashMap<String, String>();

            itemStr = items[i].split("：");//中文冒号

            itemFlag = itemStr[0].substring(2, 3);

            itemContent = itemStr[1];

            itemMap.put("itemFlag", itemFlag);

            itemMap.put("itemContent", itemContent);

            itemMapList.add(itemMap);

        }

        return itemMapList;
    }

    *//**
     * 获取产品类型message
     * @param productClass
     * @return
     *//*
    public static String getMessageProductClass(String productClass) {

        if (StringUtil.isBlank(productClass)) {

            return "";

        }

        if (StringUtil.equalsIgnoreCase(ProductClassEnum.FUND_PUBLIC_FUND_ETF, productClass)) {

            return "基金-公募-ETF";

        } else if (StringUtil.equalsIgnoreCase(ProductClassEnum.FUND_PUBLIC_FUNDS_BOUND,
            productClass)) {

            return "基金-公募-其他";

        } else if (StringUtil.equalsIgnoreCase(ProductClassEnum.FUND_PUBLIC_FUNDS_CURRENCY,
            productClass)) {

            return "基金-公募-货币";

        } else if (StringUtil
            .equalsIgnoreCase(ProductClassEnum.FUND_PUBLIC_FUNDS_MIX, productClass)) {

            return "基金-公募-混合";

        } else if (StringUtil.equalsIgnoreCase(ProductClassEnum.FUND_PUBLIC_FUNDS_STOCK,
            productClass)) {

            return "基金-公募-股票";

        } else if (StringUtil.equalsIgnoreCase(ProductClassEnum.FUND_PUBLIC_INDEX, productClass)) {

            return "基金-公募-指数";

        } else if (StringUtil.equalsIgnoreCase(ProductClassEnum.FUND_SPECIAL_ACCOUNT_ONE_TO_MANY,
            productClass)) {

            return "基金-专户-一对多";

        } else if (StringUtil.equalsIgnoreCase(ProductClassEnum.FUND_SPECIAL_ACCOUNT_ONE_TO_ONE,
            productClass)) {

            return "基金-专户-一对一";

        } else if (StringUtil.equalsIgnoreCase(ProductClassEnum.INFORMATION_TYPE, productClass)) {

            return "资讯类";

        } else if (StringUtil.equalsIgnoreCase(ProductClassEnum.INVESTEMENT_COMPANY_BIG_UNIT_TYPE,
            productClass)) {

            return "券商理财-大集合";

        } else if (StringUtil.equalsIgnoreCase(ProductClassEnum.INVESTEMENT_COMPANY_SMALL_UNIT,
            productClass)) {

            return "券商理财-小集合";

        } else if (StringUtil.equalsIgnoreCase(ProductClassEnum.INVESTOR_ADVICE_TYPE, productClass)) {

            return "投顾类";

        } else if (StringUtil.equalsIgnoreCase(ProductClassEnum.PE_LIMITED_COOPERATION_TYPE,
            productClass)) {

            return "PE(股权投资)-有限合伙";

        } else if (StringUtil.equalsIgnoreCase(ProductClassEnum.PE_UNIT_TRUST_TYPE, productClass)) {

            return "PE(股权投资)-集合信托";

        } else if (StringUtil.equalsIgnoreCase(ProductClassEnum.REMIND_TYPE, productClass)) {

            return "提醒类";

        } else if (StringUtil.equalsIgnoreCase(ProductClassEnum.SERVICE_TYPE, productClass)) {

            return "服务类";

        } else if (StringUtil.equalsIgnoreCase(ProductClassEnum.SUNSHINE_PRIVATE_MANAGE_TYPE,
            productClass)) {

            return "阳光私募-管理型";

        } else if (StringUtil.equalsIgnoreCase(ProductClassEnum.SUNSHINE_PRIVATE_OTHER,
            productClass)) {

            return "阳光私募-其他";

        } else if (StringUtil.equalsIgnoreCase(ProductClassEnum.SUNSHINE_PRIVATE_STRUCTURE,
            productClass)) {

            return ":阳光私募-结构化";

        } else if (StringUtil.equalsIgnoreCase(ProductClassEnum.TRUST_LANDED_PROPERTY_TYPE,
            productClass)) {

            return "信托-地产融资类";

        } else if (StringUtil.equalsIgnoreCase(ProductClassEnum.TRUST_OTHER_TYPE, productClass)) {

            return "信托-其他";

        } else if (StringUtil.equalsIgnoreCase(ProductClassEnum.TRUST_STOCK_RIGHT_TYPE,
            productClass)) {

            return "信托-股权融资类";

        } else {
            return "未知";
        }
    }

    *//**
     * 获取客户国籍地区
     * @param cusStateValue
     * @return
     *//*
    public static String getCustomerNationality(String cusValue) {

        if (StringUtil.isBlank(cusValue)) {

            return " ";

        }

        if (StringUtil.equalsIgnoreCase(NationalEnum.NATIONAL_CHINA_TYPE, cusValue)) {

            return "中国";

        } else if (StringUtil.equalsIgnoreCase(NationalEnum.NATIONAL_USA_TYPE, cusValue)) {

            return "美国";

        } else {
            return "其他";
        }
    }

    *//**
     * 获取订单录入渠道
     * @param channel
     * @return
     *//*
    public static String getOrderCreateChannel(String channel) {

        if (StringUtil.isBlank(channel)) {

            return " ";

        }

        if (StringUtil.equalsIgnoreCase(CreateChannelEnum.LABOUR_CREATE_CHANNEL_TYPE, channel)) {

            return "人工录入";

        } else if (StringUtil.equalsIgnoreCase(CreateChannelEnum.ONLINE_CREATE_CHANNEL_TYPE,
            channel)) {

            return "线上同步";

        } else {
            return "其他";
        }
    }

    *//**
     * 获取订单订购单位
     * @param channel
     * @return
     *//*
    public static String getOrderUnit(String unitType) {

        if (StringUtil.isBlank(unitType)) {

            return " ";

        }

        if (StringUtil.equalsIgnoreCase(OrderUnitEnum.ORDER_UNIT_DAY_TYPE, unitType)) {

            return "日";

        } else if (StringUtil.equalsIgnoreCase(OrderUnitEnum.ORDER_UNIT_MONTH_TYPE, unitType)) {

            return "月";

        } else if (StringUtil.equalsIgnoreCase(OrderUnitEnum.ORDER_UNIT_TIME_TYPE, unitType)) {

            return "次";

        } else if (StringUtil.equalsIgnoreCase(OrderUnitEnum.ORDER_UNIT_YEAR_TYPE, unitType)) {

            return "年";

        } else {
            return "未知";
        }
    }

    *//**
     * 是否允许自动续订
     * *//*
    public static String getIsAllowAutoRenew(String type) {
        if (StringUtil.isBlank(type)) {
            return "";
        }

        if (StringUtil.equalsIgnoreCase(IsAutoRenew.ALLOW_AUTO_RENEW, type)) {

            return "是";

        } else if (StringUtil.equalsIgnoreCase(IsAutoRenew.NOT_ALLOW_RENEW, type)) {
            return "不是";
        } else {
            return "未知";
        }
    }

    public static String getClassByIndex(Integer index) {

        if (index > 4) {

            return "";

        }

        String[] classArray = { "one", "two", "three", "four", "five" };

        return classArray[index];

    }

    *//**
     * 获取用户请求类型
     * *//*
    public static String getCustomerRequsetType(String type) {

        if (StringUtil.isBlank(type)) {

            return "";

        }

        if (StringUtil.equalsIgnoreCase(RequestTypeEnum.DIAGNOSE_TYPE, type)) {

            return "理财报告申请";

        } else if (StringUtil.equalsIgnoreCase(RequestTypeEnum.COMPLAIN_TYPE, type)) {

            return "投诉";

        } else if (StringUtil.equalsIgnoreCase(RequestTypeEnum.ADD_MSG, type)) {

            return "留言";
        } else {
            return "其他";
        }

    }

    *//**
     * 获取用户请求子类型
     * *//*
    public static String getCustomerRequsetCateg(String categ) {

        if (StringUtil.isBlank(categ)) {

            return "";

        }

        if (StringUtil.equalsIgnoreCase(RequestCategEnum.DIAGNOSE_DAY, categ)) {

            return "账户诊断一日刊";

        } else if (StringUtil.equalsIgnoreCase(RequestCategEnum.DIAGNOSE_WEEK, categ)) {

            return "账户诊断一周刊";

        } else if (StringUtil.equalsIgnoreCase(RequestCategEnum.DIAGNOSE_MONTH, categ)) {

            return "账户诊断一月刊";

        } else if (StringUtil.equalsIgnoreCase(RequestCategEnum.DIAGNOSE_YEAR, categ)) {

            return "账户诊断一年刊";

        } else if (StringUtil.equalsIgnoreCase(RequestCategEnum.DIAGNOSE_NO1, categ)) {

            return "账户诊断1号";

        } else if (StringUtil.equalsIgnoreCase(RequestCategEnum.DIAGNOSE_NO2, categ)) {

            return "账户诊断2号";

        } else if (StringUtil.equalsIgnoreCase(RequestCategEnum.DIAGNOSE_STOCK, categ)) {

            return "浙商证券慧眼识金股票池";

        } else if (StringUtil.equalsIgnoreCase(RequestCategEnum.COMPLAIN_A, categ)) {

            return "投诉类型A";

        } else {

            return "投诉类型B";

        }
    }

    *//**
     * 获取用户请求状态
     * *//*
    public static String getCustomerRequsetStatus(String status) {

        String value = "";

        if (StringUtil.isBlank(status)) {

            value = "";

        }

        if (StringUtil.equalsIgnoreCase(RequestTypeEnum.REQUEST_TYPE_START, status)) {

            value = "发起";

        } else if (StringUtil.equalsIgnoreCase(RequestTypeEnum.REQUEST_TYPE_PROCESS, status)) {

            value = "处理中";

        } else if (StringUtil.equalsIgnoreCase(RequestTypeEnum.REQUEST_TYPE_END, status)) {

            value = "完成";

        }
        return value;

    }

    *//**
     * 获取股票池品种
     * *//*
    public static String getStockpoolKind(String kind) {

        String value = "";

        if (StringUtil.isBlank(kind)) {

            value = "";

        }

        if (StringUtil.equalsIgnoreCase(StockpoolTypeEnum.STOCKPOOL_KIND_STOCK, kind)) {

            value = "股票";

        } else if (StringUtil.equalsIgnoreCase(StockpoolTypeEnum.STOCKPOOL_KIND_FUND, kind)) {

            value = "基金";

        } else if (StringUtil.equalsIgnoreCase(StockpoolTypeEnum.STOCKPOOL_KIND_BOND, kind)) {

            value = "债券";

        } else if (StringUtil.equalsIgnoreCase(StockpoolTypeEnum.STOCKPOOL_KIND_WARRANT, kind)) {

            value = "权证";

        } else if (StringUtil.equalsIgnoreCase(StockpoolTypeEnum.STOCKPOOL_KIND_MIX, kind)) {

            value = "混合";

        }
        return value;

    }

    *//**
     * 获取股票池范围
     * *//*
    public static String getStockpoolRange(String range) {

        String value = "";

        if (StringUtil.isBlank(range)) {

            value = "";

        }

        if (StringUtil.equalsIgnoreCase(StockpoolTypeEnum.STOCKPOOL_RANGE_TRACK, range)) {

            value = "跟踪";

        } else if (StringUtil.equalsIgnoreCase(StockpoolTypeEnum.STOCKPOOL_RANGE_RECOMMEND, range)) {

            value = "推荐";

        } else if (StringUtil.equalsIgnoreCase(StockpoolTypeEnum.STOCKPOOL_RANGE_PUBLISH, range)) {

            value = "发布";

        }
        return value;

    }

    *//**
     * 获取股票池归属
     * *//*
    public static String getStockpoolBelong(String belong) {

        String value = "";

        if (StringUtil.isBlank(belong)) {

            value = "";

        }

        if (StringUtil.equalsIgnoreCase(StockpoolTypeEnum.STOCKPOOL_BELONG_COMPANY, belong)) {

            value = "公司级";

        } else if (StringUtil.equalsIgnoreCase(StockpoolTypeEnum.STOCKPOOL_BELONG_SALESDEPARENT,
            belong)) {

            value = "营业部级";

        } else if (StringUtil.equalsIgnoreCase(StockpoolTypeEnum.STOCKPOOL_BELONG_PERSONAL, belong)) {

            value = "个人";

        } else if (StringUtil.equalsIgnoreCase(StockpoolTypeEnum.STOCKPOOL_BELONG_CUSTOMER, belong)) {

            value = "客户级";

        }
        return value;

    }

    *//**
     * 获取股票池风格
     * *//*
    public static String getStockpoolStyle(String type) {

        String value = "";

        if (StringUtil.isBlank(type)) {

            value = "";

        }

        if (StringUtil.equalsIgnoreCase(StockpoolTypeEnum.STOCKPOOL_TYPE_RADICAL, type)) {

            value = "激进";

        } else if (StringUtil.equalsIgnoreCase(StockpoolTypeEnum.STOCKPOOL_TYPE_STEADY, type)) {

            value = "稳健";

        } else if (StringUtil.equalsIgnoreCase(StockpoolTypeEnum.STOCKPOOL_TYPE_CONSERVATIVE, type)) {

            value = "保守";

        }
        return value;

    }

    *//**
     * 获取市场交易类别
     * *//*
    public static String getExchangeType(String type) {

        String value = "";

        if (StringUtil.isBlank(type)) {

            value = "";

        }

        if (StringUtil.equalsIgnoreCase(StockpoolTypeEnum.MARKET_TYPE_HU_A, type)) {

            value = "沪A";

        } else if (StringUtil.equalsIgnoreCase(StockpoolTypeEnum.MARKET_TYPE_SHEN_A, type)) {

            value = "深A";

        } else if (StringUtil
            .equalsIgnoreCase(StockpoolTypeEnum.MARKET_TYPE_SPECIAL_TRANSFER, type)) {

            value = "特别转让";

        } else if (StringUtil.equalsIgnoreCase(StockpoolTypeEnum.MARKET_TYPE_HU_B_TRANSFER, type)) {

            value = "沪B转让";

        } else if (StringUtil.equalsIgnoreCase(StockpoolTypeEnum.MARKET_TYPE_SHEN_B_TRANSFER, type)) {

            value = "深B转让";

        } else if (StringUtil.equalsIgnoreCase(StockpoolTypeEnum.MARKET_TYPE_HU_B, type)) {

            value = "沪B";

        } else if (StringUtil.equalsIgnoreCase(StockpoolTypeEnum.MARKET_TYPE_SHEN_B, type)) {

            value = "深B";

        } else if (StringUtil.equalsIgnoreCase(StockpoolTypeEnum.MARKET_TYPE_FIXED_INCOME, type)) {

            value = "固定收益";

        } else if (StringUtil.equalsIgnoreCase(StockpoolTypeEnum.MARKET_TYPE_JINHUA_FUND, type)) {

            value = "金华基金";

        }

        return value;

    }

    *//**
     * 获取股票池周期
     * *//*
    public static String getStockpoolLifetime(String lifetime) {

        String value = "";

        if (StringUtil.isBlank(lifetime)) {

            value = "";

        }

        if (StringUtil.equalsIgnoreCase(StockpoolTypeEnum.STOCKPOOL_LIFETIME_LONG, lifetime)) {

            value = "长线";

        } else if (StringUtil.equalsIgnoreCase(StockpoolTypeEnum.STOCKPOOL_LIFETIME_MID, lifetime)) {

            value = "中线";

        } else if (StringUtil
            .equalsIgnoreCase(StockpoolTypeEnum.STOCKPOOL_LIFETIME_SHORT, lifetime)) {

            value = "短线";

        }
        return value;

    }

    *//**
     * 获取投资评级
     * *//*
    public static String getInvestLevel(String type) {

        String value = "";

        if (StringUtil.isBlank(type)) {

            value = "";

        }

        if (StringUtil.equalsIgnoreCase(StockpoolTypeEnum.INVEST_LEVEL_BUY, type)) {

            value = "买入";

        } else if (StringUtil.equalsIgnoreCase(StockpoolTypeEnum.INVEST_LEVEL_HOLD, type)) {

            value = "增持";

        } else if (StringUtil.equalsIgnoreCase(StockpoolTypeEnum.INVEST_LEVEL_NEUTER, type)) {

            value = "中性";

        } else if (StringUtil.equalsIgnoreCase(StockpoolTypeEnum.INVEST_LEVEL_REDUCTION, type)) {

            value = "减持";

        } else if (StringUtil.equalsIgnoreCase(StockpoolTypeEnum.INVEST_LEVEL_SELL, type)) {

            value = "卖出";

        }
        return value;

    }

    *//**
     * 获取客户交流类型
     * *//*
    public static String getCommunicationType(String type) {

        String value = "";

        if (StringUtil.isBlank(type)) {

            value = "";

        }

        if (StringUtil.equalsIgnoreCase(CommunicationTypeEnum.MESSAGE_TYPE, type)) {

            value = "留言";

        } else if (StringUtil.equalsIgnoreCase(CommunicationTypeEnum.SUGGESTION_TYPE, type)) {

            value = "投诉建议";

        } else if (StringUtil.equalsIgnoreCase(CommunicationTypeEnum.COMMENT_TYPE, type)) {

            value = "点评";

        } else if (StringUtil.equalsIgnoreCase(CommunicationTypeEnum.ADVICEBOOK_TYPE, type)) {

            value = "投资建议书";

        } else if (StringUtil.equalsIgnoreCase(CommunicationTypeEnum.DIAGNOSIS_TYPE, type)) {

            value = "账户诊断";

        } else if (StringUtil.equalsIgnoreCase(CommunicationTypeEnum.ADVICE_TYPE, type)) {

            value = "投资建议";

        } else if (StringUtil.equalsIgnoreCase(CommunicationTypeEnum.ANALYSIS_TYPE, type)) {

            value = "理财分析";
        }

        return value;

    }

    *//**
     * 获取客户职业类型
     * *//*
    public static String getCustometJobType(String jobType) {
        String value = "";

        if (StringUtil.isBlank(jobType)) {

            value = "";

        }

        if (StringUtil.equalsIgnoreCase(CustomerJobEnum.CSUTOMER_JOB_FARMER, jobType)) {

            value = "农民";

        } else if (StringUtil.equalsIgnoreCase(CustomerJobEnum.CUSTOME_JOB_ARMER, jobType)) {

            value = "军人";

        } else if (StringUtil.equalsIgnoreCase(CustomerJobEnum.CUSTOMER_JOB_COMPANY_MANAGER,
            jobType)) {

            value = "企事业单位干部";

        } else if (StringUtil
            .equalsIgnoreCase(CustomerJobEnum.CUSTOMER_JOB_COMPANY_WORKER, jobType)) {

            value = "行政企事业单位工人";

        } else if (StringUtil.equalsIgnoreCase(CustomerJobEnum.CUSTOMER_JOB_MANAGER, jobType)) {

            value = "党政 ( 在职，离退休 ) 机关干部";

        } else if (StringUtil.equalsIgnoreCase(CustomerJobEnum.CUSTOMER_JOB_NOJOB, jobType)) {

            value = "无业";

        } else if (StringUtil.equalsIgnoreCase(CustomerJobEnum.CUSTOMER_JOB_SOLE_PROPRIETOR,
            jobType)) {

            value = "个体";
        } else if (StringUtil.equalsIgnoreCase(CustomerJobEnum.CUSTOMER_JOB_STUDY, jobType)) {

            value = "文教科卫专业人员";
        } else {

            value = "其他";

        }

        return value;
    }

    *//**
     * 获取客户学历
     * *//*
    public static String getCustomerDegree(String customerType) {
        String value = "";

        if (StringUtil.isBlank(customerType)) {

            value = "";

        }

        if (StringUtil.equalsIgnoreCase(CustomerDegreeEnum.CSUTOMER_DEGREE_MASTER, customerType)) {

            value = "硕士";

        } else if (StringUtil.equalsIgnoreCase(CustomerDegreeEnum.CUSTOMER_DEGREE_COURSE,
            customerType)) {

            value = "本科";

        } else if (StringUtil.equalsIgnoreCase(CustomerDegreeEnum.CUSTOMER_DEGREE_DOCTOR,
            customerType)) {

            value = "博士";

        } else if (StringUtil.equalsIgnoreCase(CustomerDegreeEnum.CUSTOMER_DEGREE_GIGH_SCHOOL,
            customerType)) {

            value = "高中";

        } else if (StringUtil.equalsIgnoreCase(CustomerDegreeEnum.CUSTOMER_DEGREE_JUNIOR_COLLEGE,
            customerType)) {

            value = "大专";

        } else if (StringUtil.equalsIgnoreCase(CustomerDegreeEnum.CUSTOMER_DEGREE_MIDDLE_SCHOOL,
            customerType)) {

            value = "初中及以下";

        } else if (StringUtil.equalsIgnoreCase(
            CustomerDegreeEnum.CUSTOMER_DEGREE_SECONDARY_TECHNICAL_SCHOOL, customerType)) {

            value = "中专";
        } else {

            value = "其他";
        }

        return value;
    }

    *//**
     * 将产品的风险指数转化为文字信息
     * *//*
    public static String getRiskMsg(String score) {

        if (StringUtil.isBlank(score) || !NumberUtil.isNumber(score)) {
            return "未知";
        }
        int num = NumberUtil.toInt(StringUtil.formatNumber(score));

        if (num < 50) {
            return "低风险";
        }

        if (num >= 50 && num < 70) {
            return "中等风险";
        }

        if (num >= 70 && num <= 100) {
            return "高风险";
        }

        return "未知";

    }

    *//**
     * 获取客户经理等级
     * *//*
    public static String getCmBrokerStfLevel(String level) {
        if (StringUtil.isBlank(level)) {

            return "未知";

        }

        if (StringUtil.equalsIgnoreCase(level, BrokerLevelEnum.BROKER_LEVEL_EIGHT)) {

            return "八级客户经理";

        } else if (StringUtil.equalsIgnoreCase(level, BrokerLevelEnum.BROKER_LEVEL_FIVE)) {

            return "五级客户经理";

        } else if (StringUtil.equalsIgnoreCase(level, BrokerLevelEnum.BROKER_LEVEL_FOUR)) {

            return "四级客户经理";

        } else if (StringUtil.equalsIgnoreCase(level, BrokerLevelEnum.BROKER_LEVEL_HIGH_LEVEL)) {

            return "高级客户经理";

        } else if (StringUtil.equalsIgnoreCase(level, BrokerLevelEnum.BROKER_LEVEL_MAIL_FIRST)) {

            return "首席客户经理";

        } else if (StringUtil.equalsIgnoreCase(level, BrokerLevelEnum.BROKER_LEVEL_ONE)) {

            return "一级客户经理";

        } else if (StringUtil.equalsIgnoreCase(level, BrokerLevelEnum.BROKER_LEVEL_SEVEN)) {

            return "七级客户经理";

        } else if (StringUtil.equalsIgnoreCase(level, BrokerLevelEnum.BROKER_LEVEL_SIX)) {

            return "六级客户经理";

        } else if (StringUtil.equalsIgnoreCase(level, BrokerLevelEnum.BROKER_LEVEL_THREE)) {

            return "三级客户经理";

        } else if (StringUtil.equalsIgnoreCase(level, BrokerLevelEnum.BROKER_LEVEL_TWO)) {

            return "二级客户经理";

        } else {

            return "未知";
        }
    }

*/}
