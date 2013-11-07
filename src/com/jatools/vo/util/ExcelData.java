package com.jatools.vo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.jatools.common.Pager;
import com.jatools.common.PropertyUtil;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.DictCache;

public class ExcelData {
	private String reg = "^(-?((0?\\.[0-9]+)|([1-9][0-9]*\\.[0-9]+)|([1-9]+[0-9]*)))|0$";
	private Pattern pattern = java.util.regex.Pattern.compile(reg);
	
	private String title;
	private String[] columnTitle;
	private String[] columnName;
	private List<Map> dataList;
	private Pager pager;
	private Map<String, Integer> columnWidthMap = new HashMap<String, Integer>();
	private Map<String, String> dictColumnMap = new HashMap<String, String>();
	private Map<String, CacheSingletonIntf> singletonColumnMap = new HashMap<String, CacheSingletonIntf>();
	private Map<String, Class> columnTypeMap = new HashMap<String, Class>();

	private boolean isNumeric(String str){
		return pattern.matcher(str).matches();
	}
	
	public List<Map<String, Object>> getData() {
		List<Map<String, String>> data = null;
		if (null != dataList) {
			data = getStringDataFromDataList();
		} else if(null != pager){
			data = getStringDataFromPager();
		}else{
			throw new RuntimeException("不能获取excel数据信息");
		}
		translateData(data);
		calcColumnType(data);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for(Map<String, String> item : data){
			Map<String, Object> dataMap = new HashMap<String, Object>();
			for(int i=0;i< getColumnName().length;i++){
				String key = getColumnName()[i];
				String val = item.get(key);
				if(null == val) continue;
				Object obj = null;
				if(String.class.equals(columnTypeMap.get(key))){
					obj = val;
				}else if(Double.class.equals(columnTypeMap.get(key))){
					obj = new Double(val);
				}else if(Integer.class.equals(columnTypeMap.get(key))){
					obj = new Integer(val);
				}
				dataMap.put(key, obj);
			}
			list.add(dataMap);
		}
		return list;
	}
	
	private void calcColumnType(List<Map<String, String>> data) {
		for(int i=0;i< getColumnName().length;i++){
			String key = getColumnName()[i];
			if(columnTypeMap.containsKey(key))	continue;
			Class cls = Double.class;
			for (Map<String, String> item : data) {
				String val = item.get(key);
				if(null == val) continue;
				if(!isNumeric(val)){
					cls = String.class;
					break;
				}
			}
			columnTypeMap.put(key, cls);
		}
	}

	private void translateData(List<Map<String, String>> data){
		for (Map<String, String> item : data) {
			for(int i=0;i< getColumnName().length;i++){
				String key = getColumnName()[i];
				String val = item.get(key);
				if(null == val) continue;
				if(singletonColumnMap.containsKey(key)){
					val = singletonColumnMap.get(key).getNameById(val);
					item.put(key, val);
				}else if(dictColumnMap.containsKey(key)){
					val = DictCache.getInstance().getValue(dictColumnMap.get(key), val);
					item.put(key, val);
				}
			}
		}
	}
	
	private List<Map<String, String>> getStringDataFromPager() {
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		for(Object object : pager.getPageData()){
			Map dataMap = new HashMap();
			for(short i=0;i< getColumnName().length;i++){
				try {
					String key = columnName[i];
					String val = null;
					if(object instanceof Map){
						Object obj = ((Map)object).get(key);
						if(null != obj){
							val = obj.toString();
						}
					}else{
						val = PropertyUtil.getReadableProperty(object, key);
					}
					dataMap.put(key, val);
				}catch(Exception e){}
			}
			data.add(dataMap);
		}
		return data;
	}
	
	private List<Map<String, String>> getStringDataFromDataList() {
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		for (Map item : dataList) {
			Map<String, String> dataMap = new HashMap<String, String>();
			for (short i = 0; i < columnName.length; i++) {
				Object obj = item.get(columnName[i]);
				if (null != obj) {
					dataMap.put(columnName[i], obj.toString());
				} else {
					dataMap.put(columnName[i], null);
				}
			}
			data.add(dataMap);
		}
		return data;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String[] getColumnName() {
		return columnName;
	}
	
	public void setColumnName(String... columnName) {
		this.columnName = columnName;
	}
	
	public List<Map> getDataList() {
		return dataList;
	}
	
	public void setDataList(List<Map> dataList) {
		this.dataList = dataList;
	}
	
	public String[] getColumnTitle() {
		return columnTitle;
	}
	
	public void setColumnTitle(String... columnTitle) {
		this.columnTitle = columnTitle;
	}
	
	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public Map<String, Integer> getColumnWidthMap() {
		return columnWidthMap;
	}

	public Map<String, String> getDictColumnMap() {
		return dictColumnMap;
	}

	public Map<String, CacheSingletonIntf> getSingletonColumnMap() {
		return singletonColumnMap;
	}

	public void addColumnWidth(String[] names, Integer[] entryCode) {
		if(null != names && names.length>0){
			for(int i=0;i<names.length;i++){
				columnWidthMap.put(names[i], entryCode[i]);
			}
		}
	}
	
	public void addDictDisplayColumns(String[] names, String[] entryCode) {
		if(null != names && names.length>0){
			for(int i=0;i<names.length;i++){
				dictColumnMap.put(names[i], entryCode[i]);
			}
		}
	}
	
	public void addSingletonDisplayColumns(String[] names, CacheSingletonIntf[] cacheInstances) {
		if(null != names && names.length>0){
			for(int i=0;i<names.length;i++){
				singletonColumnMap.put(names[i], cacheInstances[i]);
			}
		}
	}
	public void addColumnType(String[] names, Class[] classArr) {
		if(null != names && names.length>0){
			for(int i=0;i<names.length;i++){
				columnTypeMap.put(names[i], classArr[i]);
			}
		}
	}
//	public static void main(String[] args) {
//	String reg = "^(-?((0?\\.[0-9]+)|([1-9][0-9]*\\.[0-9]+)|([1-9]+[0-9]*)))|0$";
//	Pattern p = java.util.regex.Pattern.compile(reg);
//	String str = "00";
//	System.out.println(p.matcher(str).matches());
//}
}