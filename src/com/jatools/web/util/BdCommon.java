package com.jatools.web.util;

import com.jatools.web.cache.ArticleTypeCache;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.ParameterConstant;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.ParameterCache;
import com.jatools.web.cache.QualityCache;
import com.jatools.web.cache.StyleClassCache;
import com.jatools.web.cache.StyleThemeCache;
import com.jatools.web.cache.UnitCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.cache.VendorCache;

import java.text.DecimalFormat;

public class BdCommon {
	/**
	 * 获取配置参数值
	 * @param name
	 * @return
	 */
	public static String getParameterValue(String name){
		String tmp = ParameterCache.getInstance().getValue(name);
		if(null == tmp){
			throw new RuntimeException("不能获取系统参数配置值["+name+"]");
		}
		return tmp;
	}
	/**
	 * 根据用户id获取用户名称
	 * 支持用逗号分隔的多个id
	 * @param userIds
	 * @return
	 */
	public static String getUserName(String userIds){
		if(StringUtil.isEmpty(userIds)){
			return null;
		}
		String[] useridArr = userIds.split(",");
		String nameStr = "$";
		for(String userid : useridArr){
			if(!StringUtil.isEmpty(userid)){
				String tmp = UserCache.getInstance().getUserName(userid);
				if(null != tmp){
					nameStr += "," + tmp;
				}
			}
		}
		if("$".equals(nameStr))
			return userIds;
		return nameStr.substring(2);
	}
	
	/**
	 * 根据组织id获取组织名称
	 * 支持用逗号分隔的多个id
	 * @param orgIds
	 * @return
	 */
	public static String getOrgName(String orgIds){
		if(StringUtil.isEmpty(orgIds))
			return null;
		String[] orgIdArr = orgIds.split(",");
		String nameStr = "$";
		for(String orgId : orgIdArr){
			if(!StringUtil.isEmpty(orgId)){
				String tmp = OrgCache.getInstance().getOrgName(orgId);
				if(null != tmp){
					nameStr += "," + tmp;
				}
			}
		}
		if("$".equals(nameStr))
			return orgIds;
		return nameStr.substring(2);
	}
	/**
	 * 根据商品类别id获取商品名称
	 * @param articleTypeIds
	 * @return
	 */
	public static String getArticleTypeDesc(String articleTypeIds){
		String tmp = ArticleTypeCache.getInstance().getArticleTypeName(articleTypeIds);
		if(null != tmp){
			return tmp;
		}
		return articleTypeIds;
	}
	/**
	 * 根据大类id获取大类名称
	 * @param itemClassId
	 * @return
	 */
	public static String getItemClassDesc(String itemClassId){
		String tmp = ItemClassCache.getInstance().getItemClassDesc(itemClassId);
		if(null != tmp){
			return tmp;
		}
		return itemClassId;
	}
	
	public static String getItemClassNames(String itemClassIds){
		String tmp = ItemClassCache.getInstance().getItemClassNames(itemClassIds);
		if(null != tmp){
			return tmp;
		}
		return itemClassIds;
	}
	/**
	 * 根据小类id获取小类名称
	 * @param ornaClassId
	 * @return
	 */
	public static String getOrnaClassDesc(String ornaClassId){
		String tmp = OrnaClassCache.getInstance().getOrnaClass(ornaClassId);
		if(null != tmp){
			return tmp;
		}
		return ornaClassId;
	}
	
	public static String getOrnaClassNames(String ornaClassIds){
		String tmp = OrnaClassCache.getInstance().getOrnaClassNames(ornaClassIds);
		if(null != tmp){
			return tmp;
		}
		return ornaClassIds;
	}
	/**
	 * 根据款式大类ids，获取款式大类名称，支持多个，用逗号隔开
	 * @param styleItemClassIds
	 * @return
	 */
	public static String getStyleItemClassNames(String styleItemClassIds){
		return StyleClassCache.getInstance().getStyleItemClassDescByIds(styleItemClassIds);
	}
	/**
	 * 根据款式中类ids，获取款式大类名称，支持多个，用逗号隔开
	 * @param styleMiddleClassIds
	 * @return
	 */
	public static String getStyleMiddleClassNames(String styleMiddleClassIds){
		return StyleClassCache.getInstance().getStyleMiddleClassDescByIds(styleMiddleClassIds);
	}
	/**
	 * 根据款式小类ids，获取款式小类名称，支持多个，用逗号隔开
	 * @param styleOrnaClassIds
	 * @return
	 */
	public static String getStyleOrnaClassNames(String styleOrnaClassIds){
		return StyleClassCache.getInstance().getStyleOrnaClassDescByIds(styleOrnaClassIds);
	}
	/**
	 * 根据商品材质id，获取商品材质名称
	 */
	public static String getQualityName(String qualityId){
		return QualityCache.getInstance().getQualityName(qualityId);
	}
	/**
	 * 根据计量单位id,获取计量单位名称
	 * @param unitId
	 * @return
	 */
	public static String getUnitName(String unitId){
		return UnitCache.getInstance().getUnitName(unitId);
	}
	/**
	 * 根据供应商id获取供应商名称
	 * @param vendorId
	 * @return
	 */
	public static String getVendorName(String vendorId){
		return VendorCache.getInstance().getVendorName(vendorId);
	}
	/**
	 * 根据供应商id获取供应商代码
	 * @param vendorId
	 * @return
	 */
	public static String geVendorSupplierCode(String vendorId){
		return VendorCache.getInstance().getVendorSupplierCode(vendorId);
	}
	/**
	 * 获取单据状态名称
	 * @param status
	 * @return
	 */
	public static String getStatusName(String status) {
		return DictUtil.getValue(DictConstant.BILL_STATUS, status);
	}
	
	/**修改 交接单 行表的孙表 json串*/
	public static String resetLineChild(String value){
		StringBuffer res = new StringBuffer();
		if(StringUtil.isNotBlank(value)){
			res.append("'").append(value).append("'");
		}
    	return res.toString();
    }
	/**
	 * 获取主题款名称
	 * @param styleThemeId
	 * @return
	 */
	public static String getStyleThemeName(String styleThemeId){
		return StyleThemeCache.getInstance().getStyleThemeName(styleThemeId);
	}
	
	public static String getStyleThemeNames(String styleThemeIds){
		return StyleThemeCache.getInstance().getStyleThemeNames(styleThemeIds);
	}
	
	public static String getMainOrg(){
		String mainOrg = ParameterCache.getInstance().getValue(ParameterConstant.HQ_MAIN_ORG_ID);
		if(StringUtil.isNotBlank(mainOrg)){
			return mainOrg;
		}
		return "49901";
	}

    /**
     * 素金入库单 特殊工费限制
     * @return
     */
    public static String getMaterInivWorkPrice() {
        String wp = ParameterCache.getInstance().getValue(ParameterConstant.MATER_INIV_WORK_PRICE);
        if(StringUtil.isBlank(wp)) {
            return "60";
        }
        return wp;
    }

    /**
     * 特殊工费单价
     * @return
     */
    public static String getVerdorChargePrice(){
        return ParameterCache.getInstance().getValue(ParameterConstant.VERDOR_CHARGE_PRICE);
    }

    /**
     * 特殊工费加价
     * @return
     */
    public static String getVerdorChargeFare(){
        return ParameterCache.getInstance().getValue(ParameterConstant.VERDOR_CHARGE_FARE);
    }

    /**
     * 素金入库单 成本单价与爱尚金固定金价差异值范围
     * @return
     */
    public static String getFixedGoldPriceRange(){
        String res = ParameterCache.getInstance().getValue(ParameterConstant.FIXED_GOLD_PRICE_RANGE);
        if(StringUtil.isNotBlank(res)) {
            return res;
        } else {
            return "30";
        }
    }

    public static String getDispatchPriceRate(){
        String res = ParameterCache.getInstance().getValue(ParameterConstant.DISPATCH_PRICE_RATE);
        if(StringUtil.isNotBlank(res)) {
            DecimalFormat decimalFormat = new DecimalFormat("0.####");
            Double resDouble = new Double(res);
            return decimalFormat.format(resDouble/100);
        } else {
            return "0.1";
        }
    }
}
