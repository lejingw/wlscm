package com.jatools.web.dwr.sys;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.manager.sys.DictManager;
import com.jatools.vo.sys.Dict;
import com.jatools.vo.sys.DictEntry;
import com.jatools.vo.sys.DictItem;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.cache.DictCache;

public class DictDwr {
	private static Logger logger = Logger.getLogger(DictDwr.class);
	private DictManager dictManager;
	
	public void setDictManager(DictManager dictManager) {
		this.dictManager = dictManager;
	}
	/**
	 * 根据数据字典名称获取数据字典项
	 */
	public List<SelectorOption> getDictsForSlt(String name){
		List<Dict> dictList = DictCache.getInstance().getDicts(name);
		if(null == dictList || dictList.size()<1)
			return null;
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		for(Dict dict : dictList){
			sltList.add(new SelectorOption(dict.getItemKey(), dict.getItemValue()));
		}
		return sltList;
	}
	/**
	 * 删除数据字典
	 * @param entryCode
	 * @param req
	 * @return
	 */
	public String deleteDictEntry(String entryCode, HttpServletRequest req){
		try {
			String userid = CommonUtil.getSessionUserId(req);
			dictManager.deleteDictEntry(entryCode, userid);
			DictCache.getInstance().refresh();
		} catch (Exception e) {
			logger.error(e);
			return "删除数据字典出错";
		}
		return null;
	}
	/**
	 * 修改数据字典
	 * @param entry
	 * @param list
	 * @param req
	 * @return
	 */
	public String updateDictEntry(DictEntry entry, List<DictItem> list, HttpServletRequest req){
		String userid = CommonUtil.getSessionUserId(req);
		dictManager.updateDictEntry(entry, list, userid);
		DictCache.getInstance().refresh();
		return null;
	}
	/**
	 * 保存数据字典
	 * @param entry
	 * @param list
	 * @param req
	 * @return
	 */
	public String saveDictEntry(DictEntry entry, List<DictItem> list, HttpServletRequest req){
		String userid = CommonUtil.getSessionUserId(req);
		dictManager.saveDictEntry(entry, list, userid);
		DictCache.getInstance().refresh();
		return null;
	}
}
