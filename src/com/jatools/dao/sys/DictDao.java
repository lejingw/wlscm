package com.jatools.dao.sys;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.sys.Dict;
import com.jatools.vo.sys.DictEntry;
import com.jatools.vo.sys.DictItem;

public interface DictDao {
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
	List<DictItem> getDictItemByEntryCode(String entryCode);/**
	 * 保存数据字典
	 */
	void saveDictEntry(DictEntry entry, String userid);
	/**
	 * 修改数据字典
	 * @param entry
	 */
	void updateDictEntry(DictEntry entry, String userid);
	/**
	 * 删除数据字典
	 * @param entryCode
	 * @param userid
	 */
	void deleteDictEntry(String entryCode, String userid);
	/**
	 * 保存字典项
	 * @param item
	 */
	void saveDictItem(DictItem item,String userid);
	/**
	 * 修改字典项
	 * @param item
	 */
	void updateDictItem(DictItem item,String userid);
	/**
	 * 删除字典项
	 * @param item
	 */
	void deleteDictItem(DictItem item, String userid);
	/**
	 * 删除无效的数据字典行记录
	 * @param entryCode
	 */
	void deleteInvalidDictItem(String entryCode);
}
