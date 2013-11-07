package com.jatools.web.util;

import java.util.Iterator;
import java.util.List;
import com.jatools.vo.sys.Dict;
import com.jatools.web.cache.DictCache;

public class DictUtil {
	/**
	 * 获取数据字典值
	 * @param name
	 * @param key
	 * @return
	 */
	public static String getValue(String name, String key){
		List<Dict> list = DictCache.getInstance().getDicts(name);
		if(null != list && !list.isEmpty()){
			Iterator<Dict> iter = list.iterator();
			while(iter.hasNext()){
				Dict dict = iter.next();
				if(dict.getItemKey().equals(key)){
					return dict.getItemValue();
				}
			}
		}
		return key;
	}
}
