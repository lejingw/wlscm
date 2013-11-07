package com.jatools.web.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.jatools.common.CommonUtil;
import com.jatools.common.Global;
import com.jatools.web.cache.OrgCache;

/**
 * 
 */
public class StringUtil extends StringUtils {

	public static String getContextPath(){
		return Global.CONTEXT;
	}
	
	/**
	 * 对于必输字段，在字段空间尾部显示红色星号
	 * @return
	 */
	public static String star(){
		return "<font color='red'>*</font>";
	}
	
	/**
	 * 下拉框空选项
	 * @return
	 */
	public static String emptyOption(){
		return "<option value=''>--请选择--</option>";
	}
	
	/**
	 * 获取登录用户id
	 * @param session
	 * @return
	 */
	public static String getUserId(HttpSession session){
		return CommonUtil.getSessionUserId(session);
	}
	/**
	 * 获取登录用户name
	 * @param session
	 * @return
	 */
	public static String getUserName(HttpSession session){
		return CommonUtil.getSessionUserName(session);
	}
	/**
	 * 获取登录组织id
	 * @param session
	 * @return
	 */
	public static String getOrgId(HttpSession session){
		return CommonUtil.getSessionOrgId(session);
	}
	/**
	 * 获取登录组织name
	 * @param session
	 * @return
	 */
	public static String getOrgName(HttpSession session){
		return OrgCache.getInstance().getOrgName(CommonUtil.getSessionOrgId(session));
	}
	
	public static Object getFromSession(HttpSession session, String name){
		if(null != session){
			return session.getAttribute(name);
		}
		return null;
	}
	
    /**
     * 按指定的分割符，分割字符串
     * @param src
     * @param separator
     * @return
     */
    public static String[] boxOffString(String src, String separator) {
        if (StringUtil.isBlank(src)) {
            return new String[] {};
        }
        if (StringUtil.isBlank(separator)) {
            return new String[] { src };
        }
        return StringUtils.split(src, separator);
    }

    /**
     * 返回searchStr在str中第一次出现的位置，返回值用字符串类型
     * @param str
     * @param searchStr
     * @return
     */
    public static String indexOfString(String str, String searchStr) {
        return String.valueOf(indexOf(str, searchStr));
    }

    /**
     * 取整数
     * @param number
     * @return
     */
    public static String formatNumber(String number) {
        if (StringUtil.isNotBlank(number) && number.indexOf(".") != -1) {
            number = number.substring(0, number.indexOf("."));
        }
        return number;
    }

    /**
     * 将UTF-8编码的字符串转化
     * */
    public static String getUtfStr(String str){
        if(StringUtil.isBlank(str)){
            return "";
        }
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 用UTF-8格式编码
     */
    public static String encodeStr(String str){
    	if(StringUtil.isBlank(str)){
    		return "";
    	}
        try {
	    	return URLEncoder.encode(str, "UTF-8");
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    }
	    return "";
    }
    
    /**
     * <pre>
     * str1 = null str2 = null => true
     * str1 = null str2 = "" => true
     * str1 = "" str2 = null => true
     * str1 = "" str2 = "" => true
     * str1 = "abc" str2 = "abc" => true
     * </pre>
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equalsIgnoreNull(String str1, String str2){
    	if(StringUtil.isBlank(str1)) return StringUtil.isBlank(str2);
    	else {
    		return str1.equals(str2);
    	}
    }
    
    /**
     * Double 类型数据 进行 4舍五入 并转化为字符串
     * @param d 
     * @param num 保留小数位数
     * @param roundingMode 模式
     * @return
     */
	public static String formatDouble(Double d, int num, int roundingMode) {
		if(null == d || num < 0){
			return null;
		}
		BigDecimal b = new BigDecimal(d);
		double d1 = b.setScale(num, roundingMode).doubleValue();
		if(num == 0){
			return Double.valueOf(d1).longValue()+"";
		}
		return Double.toString(d1);
	}
	
	/**
	 * Double 类型数据 进行 4舍五入 并转化为字符串
	 * @param d
	 * @param num 保留小数位数
	 * @return
	 */
	public static String formatDouble(Double d, int num) {
		return StringUtil.formatDouble(d, num, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * Double 类型数据 进行 4舍五入 并转化为字符串
	 * @param d
	 * @param num 保留小数位数
	 * @return
	 */
	public static String formatDouble(String d, int num) {
		if(StringUtil.isBlank(d)){
			return "";
		}
		String tmp = StringUtil.formatDouble(Double.valueOf(d), num, BigDecimal.ROUND_HALF_UP);
		if(tmp.endsWith(".0")){
			tmp = tmp.substring(0, tmp.indexOf('.'));
		}
		return tmp;
	}
	
	/**
	 * 把double类型数据 扩大100万倍 并4舍五入取整数部分
	 * @param d
	 * @return
	 */
	public static long formatDoubleToLong(Double d) {
		String dString = StringUtil.formatDouble(d*1000000, 0);
		if(StringUtil.isNotBlank(dString)){
			return Double.valueOf(dString).longValue();
		}
		return 0;
	}
	
	public static String subString(String str, int n){
		if(StringUtil.isBlank(str)){
			return "";
		} else {
			if(str.length() <= n){
				return str;
			} else {
				return str.substring(0, n)+"...";
			}
		}
	}
}
