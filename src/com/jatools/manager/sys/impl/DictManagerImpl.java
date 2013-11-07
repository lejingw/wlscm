package com.jatools.manager.sys.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.sys.DictDao;
import com.jatools.manager.sys.DictManager;
import com.jatools.vo.sys.Dict;
import com.jatools.vo.sys.DictEntry;
import com.jatools.vo.sys.DictItem;

public class DictManagerImpl implements DictManager {
	private DictDao dictDao;
	
	public void setDictDao(DictDao dictDao) {
		this.dictDao = dictDao;
	}

	/**
	 * 获取所有数据字典项
	 * @return
	 */
	public List<Dict> getAllDictItem(){
		return dictDao.getAllDictItem();
	}
	/**
	 * 根据字典类型获取数据字典
	 * @return
	 */
	public Pager getDictEntryPageData(Map<String, String> condition){
		return dictDao.getDictEntryPageData(condition);
	}

	/**
	 * 根据编码获取数据字典
	 * @param entryCode
	 * @return
	 */
	public DictEntry getDictEntry(String entryCode){
		return dictDao.getDictEntry(entryCode);
	}
	
	/**
	 * 根据编码获取数据字典项
	 * @param entryCode
	 * @return
	 */
	public List<DictItem> getDictItemByEntryCode(String entryCode){
		return dictDao.getDictItemByEntryCode(entryCode);
	}

	/**
	 * 删除数据字典
	 * @param entryCode
	 * @param userid
	 */
	public void deleteDictEntry(String entryCode, String userid){
		dictDao.deleteDictEntry(entryCode, userid);
	}
	
	/**
	 * 修改数据字典
	 * @param entry
	 * @param list
	 * @param userid
	 */
	public void updateDictEntry(DictEntry entry, List<DictItem> list, String userid){
		dictDao.updateDictEntry(entry, userid);
		List<DictItem> oldList = dictDao.getDictItemByEntryCode(entry.getEntryCode());
		dictDao.deleteInvalidDictItem(entry.getEntryCode());//删除无效的数据字典行记录
		Map<String, DictItem> oldKeyMap = new HashMap<String, DictItem>();
		Map<String, DictItem> newKeyMap = new HashMap<String, DictItem>();
		
		for(DictItem item : oldList){
			oldKeyMap.put(item.getItemKey(), item);
		}
		for(DictItem item: list){
			newKeyMap.put(item.getItemKey(), item);
		}
		for(String key : oldKeyMap.keySet()){
			if(newKeyMap.keySet().contains(key)){
				DictItem item = newKeyMap.get(key);
				dictDao.updateDictItem(item, userid);
			}else{
				DictItem item = oldKeyMap.get(key);
				dictDao.deleteDictItem(item, userid);
			}
		}
		for(String key : newKeyMap.keySet()){
			if(!oldKeyMap.keySet().contains(key)){
				dictDao.saveDictItem(newKeyMap.get(key), userid);
			}
		}
	}

	/**
	 * 保存数据字典
	 * @param entry
	 * @param list
	 * @param userid
	 */
	public void saveDictEntry(DictEntry entry, List<DictItem> list, String userid){
		dictDao.saveDictEntry(entry, userid);
		for(DictItem item:list){
			dictDao.saveDictItem(item, userid);
		}
	}
}
