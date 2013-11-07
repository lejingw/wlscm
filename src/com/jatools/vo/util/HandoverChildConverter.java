package com.jatools.vo.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.jatools.vo.pur.HandoverChild;
import com.jatools.web.util.StringUtil;

public class HandoverChildConverter {
	private final static String CHILDID = "childid";
	private final static String LINEID = "lineid";
	private final static String ALAYSISID = "alaysisId";
	private final static String STONENUM = "stoneNum";
	private final static String UNITPRICE = "unitPrice";
	private final static String EMPTY = "";

	
	public static HandoverChild JSONTOHandoverChild(String jsonStr){
		if(StringUtil.isNotBlank(jsonStr)){
			JSONObject json = JSONObject.fromObject(jsonStr);
			return JSONTOHandoverChild(json);
		}
		return null;
	}
	
	public static List<HandoverChild> JSONArrayTOHandoverChildList(String jsonArray){
		List<HandoverChild> list = new ArrayList<HandoverChild>();
		if(StringUtil.isNotBlank(jsonArray)){
			JSONArray array = JSONArray.fromObject(jsonArray);
			if(array != null && array.size() >0){
				for(int i=0;i<array.size();i++){
					JSONObject json = array.getJSONObject(i);
					HandoverChild child = JSONTOHandoverChild(json);
					if(child != null){
						list.add(child);
					}
				}
			}
		}
		return list;
	}
	
	public static String handoverChildToJSONObject(HandoverChild child){
		JSONObject json = new JSONObject();
		if(child != null){
			json = handoverChildTOJSONObject(child);
		}
		return json.toString();
	}
	
	public static String handoverChildListTOJSONArray(List<HandoverChild> childList){
		JSONArray array = new JSONArray();
		if(CollectionUtils.isNotEmpty(childList)){
			for(HandoverChild child : childList){
				JSONObject json = handoverChildTOJSONObject(child);
				array.add(json);
			}
		}
		return array.toString();
	}
	
	private static HandoverChild JSONTOHandoverChild(JSONObject json){
		if(null == json) return null;
		HandoverChild child = new HandoverChild();
		if(json.has(HandoverChildConverter.CHILDID)){
			child.setChildid(json.getString(HandoverChildConverter.CHILDID));
		}
		if(json.has(HandoverChildConverter.LINEID)){
			child.setLineid(json.getString(HandoverChildConverter.LINEID));
		}
		if(json.has(HandoverChildConverter.ALAYSISID)){
			child.setAlaysisId(json.getString(HandoverChildConverter.ALAYSISID));
		}
		if(json.has(HandoverChildConverter.STONENUM)){
			child.setStoneNum(json.getString(HandoverChildConverter.STONENUM));
		}
		if(json.has(HandoverChildConverter.UNITPRICE)){
			child.setUnitPrice(json.getString(HandoverChildConverter.UNITPRICE));
		}
		return child;
	}
	
	private static JSONObject handoverChildTOJSONObject(HandoverChild child){
		JSONObject json = new JSONObject();
		if(child != null){
			json.put(HandoverChildConverter.CHILDID, formatEmpty(child.getChildid()));
			json.put(HandoverChildConverter.LINEID, formatEmpty(child.getLineid()));
			json.put(HandoverChildConverter.ALAYSISID, formatEmpty(child.getAlaysisId()));
			if(StringUtil.isNotBlank(child.getStoneNum())){
				json.put(HandoverChildConverter.STONENUM, StringUtil.formatDouble(child.getStoneNum(), 6));
			} else {
				json.put(HandoverChildConverter.STONENUM, HandoverChildConverter.EMPTY);
			}
			if(StringUtil.isNotBlank(child.getUnitPrice())){
				json.put(HandoverChildConverter.UNITPRICE, StringUtil.formatDouble(child.getUnitPrice(), 6));
			} else {
				json.put(HandoverChildConverter.UNITPRICE, HandoverChildConverter.EMPTY);
			}
		}
		return json;
	}
	
	private static String formatEmpty(String str){
		if(StringUtil.isNotBlank(str)) return str;
		else return HandoverChildConverter.EMPTY;
	}
	
	public static void main(String[] args) {
		System.err.println(HandoverChildConverter.handoverChildListTOJSONArray(null));
		
		HandoverChild child = new HandoverChild();
		child.setStoneNum(".3");
		System.err.println(HandoverChildConverter.handoverChildToJSONObject(child));
		
		String jsonStr = "{\"childId\":\"1\",\"lineid\":\"1\",\"alaysisId\":\"\",\"stoneNum\":\"0.31\",\"unitPrice\":\"\"}";
		child = HandoverChildConverter.JSONTOHandoverChild(jsonStr);
		System.err.println(child.getChildid()+"|"+child.getStoneNum());
	}
}
