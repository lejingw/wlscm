package com.jatools.web.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jatools.vo.sys.Dict;
import com.jatools.web.cache.DictCache;

public class HtmlUtil {
	public static String printOption(String value, String text, boolean selected){
		if(null == value)	value = "";
		return "<option value='" + value + "' " + (selected?"selected":"") + ">" + text + "</option>";
	}
	/**
	 * 根据参数打印options
	 * @param values
	 * @param texts
	 * @param selectValue
	 * @return
	 */
	public static String printOptions(List<String> values, List<String> texts, boolean showEmptyOption, String selectValue){
		StringBuffer buff = new StringBuffer();
		if(showEmptyOption){
			buff.append("<option value=''>--请选择--</option>");
		}
		if(null != values && null != texts && values.size() == texts.size()){
			for(int i=0;i < values.size();i++){
				buff.append(printOption(values.get(i), texts.get(i), values.get(i).equals(selectValue)));
			}
		}
		return buff.toString();
	}
	/**
	 * 根据数据字典打印options
	 * @param entryCode
	 * @param selectValue
	 * @return
	 */
	public static String printDictOptions(String entryCode, boolean showEmptyOption, String selectValue){
		List<String> values = new ArrayList<String>();
		List<String> texts = new ArrayList<String>();
		List<Dict> list = DictCache.getInstance().getDicts(entryCode);
		if(null != list && list.size()>0){			
			for(Dict dict : list){
				values.add(dict.getItemKey());
				texts.add(dict.getItemValue());
			}
		}
		return printOptions(values, texts, showEmptyOption, selectValue);
	}
	/**
	 * 显示款式名称超链接
	 * @param styleId
	 * @param styleName
	 * @param bigGraph
	 * @return
	 */
	public static String printStyle(String styleId, String styleName, String bigGraph){
		if(StringUtils.isNotEmpty(bigGraph)){
			return "<a href='javascript:viewStyle("+styleId+")'>" + styleName + "</a>";
		}
		return styleName;
	}
}
