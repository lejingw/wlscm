package com.jatools.web.form.sys;

import java.util.HashMap;
import java.util.Map;
import com.jatools.web.cache.ArticleTypeCache;
import com.jatools.web.cache.DictCache;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.ItemOrnaClassCache;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.ParameterCache;
import com.jatools.web.cache.QualityCache;
import com.jatools.web.cache.StyleClassCache;
import com.jatools.web.cache.StyleThemeCache;
import com.jatools.web.cache.UnitCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.cache.VendorCache;
import com.jatools.web.form.BaseForm;

public class CacheForm extends BaseForm {
	private Map<String, String> map = null;
	public Map<String, String> getMap(){
		if(null == map){
			map = new HashMap<String, String>();
			map.put("user", UserCache.getInstance().getRefreshTime());
			map.put("dict", DictCache.getInstance().getRefreshTime());
			map.put("parameter", ParameterCache.getInstance().getRefreshTime());
			map.put("org", OrgCache.getInstance().getRefreshTime());
			map.put("articletype", ArticleTypeCache.getInstance().getRefreshTime());
			map.put("itemClass", ItemClassCache.getInstance().getRefreshTime());
			map.put("ornaClass", OrnaClassCache.getInstance().getRefreshTime());
			map.put("itemOrnaClass", ItemOrnaClassCache.getInstance().getRefreshTime());
			map.put("styleClass", StyleClassCache.getInstance().getRefreshTime());
			map.put("unit", UnitCache.getInstance().getRefreshTime());
			map.put("quality", QualityCache.getInstance().getRefreshTime());
			map.put("vendor", VendorCache.getInstance().getRefreshTime());
			map.put("styleTheme", StyleThemeCache.getInstance().getRefreshTime());
		}
		return map;
	}
}
