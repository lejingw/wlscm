package com.jatools.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

/**
 * 功能说明: 对于获取属性(针对日期类型)扩展<br>
 * 系统版本: v1.0 <br>	
 * 开发人员: liuyh@hundsun.com <br>
 * 开发时间: 2011-5-13
 * <br>
 */
public class PropertyUtil {
	
	/**
	 * 功能说明: 获取属性值的方法，提供对Date属性的扩展<br>
	 * 			如果存在TemporalType标注，并且为DATE类型,则转换成"yyyy-MM-dd"<br>
	 * 			其余转换成"yyyy-MM-dd HH:mm:ss"格式<br>
	 * 			为空则返回""<br>
	 * eg:<br>
	 * 		存在值，未标注TemporalType    	  	
	 * 				getProperty(bean,"adate")     		:    2011-05-13 12:48:05
	 * 		存在值，标注@Temporal(TemporalType.DATE)  	  	
	 * 				getProperty(bean,"adate")     		:    2011-05-13
	 * 		存在值，标注@Temporal(TemporalType.TIMESTAMP)  	  	
	 * 				getProperty(bean,"adate")     		:    2011-05-13 12:54:28
			不存在值 
					getProperty(bean,"bdate");          ：   ""
		    不存在get方法 	 NoSuchMethodException
	 * @param aBean
	 * @param aName
	 * @return
	 * @throws Exception
	 */
	public static String getProperty(Object aBean, String aName) throws Exception {
		Class<?> fieldClazz = PropertyUtils.getPropertyType(aBean, aName);
		
		if(fieldClazz == java.util.Date.class) {
			return getDateProperty(aBean, aName);
		} else if (fieldClazz == double.class || fieldClazz == java.lang.Double.class) {
			// 对于double类型属性的处理
			String strValue = BeanUtils.getProperty(aBean, aName);
			Double doubleValue = 0.0;
			if (StringUtil.isNotBlank(strValue)) {
				doubleValue = Double.parseDouble(strValue);
			}
			// 保留小数点后两位，并四舍五入
			return String.valueOf(new BigDecimal(doubleValue).setScale(8, RoundingMode.HALF_UP));
		} else { 
			return BeanUtils.getProperty(aBean, aName);
		}
	}
	
	/**
	 * 获取属性值
	 * 若不存在get方法，则返回null
	 * @param aBean
	 * @param aName
	 * @return
	 * @throws Exception
	 */
	public static String getReadableProperty(Object aBean, String aName) throws Exception {
		if (!PropertyUtils.isReadable(aBean, aName)) {
			return null;
		}
		return getProperty(aBean, aName);
	}

	/**
	 * 功能说明: 设置Bean属性时对Date类型的扩展<br>
	 * 			 如果传入的aParam为null，则该属性不会赋值<br>
	 * eg: <br>
	 *     bdate为java.util.Date类型 <br>
	 * 	   setProperty(bean,"bdate",19870112)               :    Mon Jan 12 00:00:00 CST 1987<br>
	 * 	   setProperty(bean,"bdate","19870112")             :    Mon Jan 12 00:00:00 CST 1987<br>
	 *     setProperty(bean,"bdate","2010-12-12 05:04:03")  :    "2010-12-12 05:04:03"<br>
	 *     setProperty(bean,"bdate","2010-12-12")           :    Sun Dec 12 00:00:00 CST 2010<br>
	 *     setProperty(bean,"bdate",null)           		:    null<br>
	 *     
	 *     nowritDate不存在set方法时
	 *     setProperty(bean,"nowritDate",null)           	:    null<br>
	 *     setProperty(bean,"nowritDate","2010-12-12")      :    null<br>
	 *     setProperty(bean,"nowritDate","2010-12-12 05:04:03"): null<br>
	 * @param aBean
	 * @param aName
	 * 			属性名称
	 * @param aParam
	 * 			参数
	 * @throws Exception
	 */
	public static void setProperty(Object aBean, String aName, Object aParam) throws Exception {
		Class<?> fieldClazz = PropertyUtils.getPropertyType(aBean, aName);  
		
		if( fieldClazz != java.util.Date.class || aParam instanceof java.util.Date) { // 非日期类型属性 或者Date类型属性,并且参数为Date类型
			BeanUtils.setProperty(aBean, aName, aParam);
		} else if( aParam != null && !StringUtils.isBlank(aParam.toString())) {		  // 日期类型属性，参数为字符串	
			BeanUtils.setProperty(aBean, aName, DateUtil.formatSdf1(aParam.toString()));
		}
	}
	
	/**
	 * 功能说明: 将aBean的aName属性值(该属性为Date类型)转换成String<br>
	 * @param aBean
	 * 			要转换的Date对象
	 * @param aName
	 * 			要转换的属性名称
	 * @return
	 * 			(String)如果属性值为null则返回""
	 * @throws Exception
	 */
	private static String getDateProperty(Object aBean, String aName) throws Exception {
		Object value = PropertyUtils.getProperty(aBean, aName);
		if(value == null) 
			return "";
		return DateUtil.formatSdf1((Date)value);
	}
}
