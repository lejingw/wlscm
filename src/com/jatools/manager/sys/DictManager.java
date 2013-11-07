package com.jatools.manager.sys;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.sys.Dict;
import com.jatools.vo.sys.DictEntry;
import com.jatools.vo.sys.DictItem;

public interface DictManager {

	/**
	 * 获取所有数据字典项
	 * @return
	 */
	List<Dict> getAllDictItem();

	/**
	 * 根据字典类型获取数据字典
	 * @return
	 */
	Pager getDictEntryPageData(Map<String, String> condition);

	/**
	 * 根据编码获取数据字典
	 * @param entryCode
	 * @return
	 */
	DictEntry getDictEntry(String entryCode);

	/**
	 * 根据编码获取数据字典项
	 * @param entryCode
	 * @return
	 */
	List<DictItem> getDictItemByEntryCode(String entryCode);

	/**
	 * 删除数据字典
	 * @param entryCode
	 * @param userid
	 */
	void deleteDictEntry(String entryCode, String userid);

	/**
	 * 修改数据字典
	 * @param entry
	 * @param list
	 * @param userid
	 */
	void updateDictEntry(DictEntry entry, List<DictItem> list, String userid);

	/**
	 * 保存数据字典
	 * @param entry
	 * @param list
	 * @param userid
	 */
	void saveDictEntry(DictEntry entry, List<DictItem> list, String userid);
}
