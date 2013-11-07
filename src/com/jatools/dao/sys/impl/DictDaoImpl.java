package com.jatools.dao.sys.impl;

import java.util.List;
import java.util.Map;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.dao.BaseDao;
import com.jatools.dao.sys.DictDao;
import com.jatools.vo.sys.Dict;
import com.jatools.vo.sys.DictEntry;
import com.jatools.vo.sys.DictItem;
import com.jatools.web.util.DateUtil;

public class DictDaoImpl extends BaseDao implements DictDao {
	/**
	 * 获取所有数据字典项
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Dict> getAllDictItem(){
		List<Dict> list = executeQueryForList("Dict.getAllDictItem", null);
		return list;
	}

	/**
	 * 根据字典类型获取数据字典
	 * @return
	 */
	public Pager getDictEntryPageData(Map<String, String> condition){
		return executeQueryForPager("Dict.getDictEntryPageData", "Dict.getDictEntryTotalCount", condition);
	}

	/**
	 * 根据编码获取数据字典
	 * @param entryCode
	 * @return
	 */
	public DictEntry getDictEntry(String entryCode){
		return (DictEntry)executeQueryForObject("Dict.getDictEntry", entryCode);
	}

	/**
	 * 根据编码获取数据字典项
	 * @param entryCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<DictItem> getDictItemByEntryCode(String entryCode){
		return (List<DictItem>)executeQueryForList("Dict.getDictItemByEntryCode", entryCode);
	}
	
	/**
	 * 保存数据字典
	 */
	public void saveDictEntry(DictEntry entry, String userid){
		entry.setCreateDate(DateUtil.getCurrentDate18());
		entry.setCreateId(userid);
		entry.setUpdateDate(DateUtil.getCurrentDate18());
		entry.setUpdateId(userid);
		entry.setStatus(DictConstant.BILL_STATUS_SAVE);
		executeUpdate("Dict.saveDictEntry", entry);
	}
	/**
	 * 修改数据字典
	 * @param entry
	 */
	public void updateDictEntry(DictEntry entry, String userid){
		entry.setUpdateDate(DateUtil.getCurrentDate18());
		entry.setUpdateId(userid);
		entry.setStatus(DictConstant.BILL_STATUS_SAVE);
		executeUpdate("Dict.updateDictEntry", entry);
	}
	/**
	 * 删除数据字典,软删除
	 * @param entryCode
	 * @param userid
	 */
	public void deleteDictEntry(String entryCode, String userid){
		DictEntry entry = (DictEntry)executeQueryForObject("Dict.getDictEntry", entryCode);
		entry.setUpdateId(userid);
		entry.setUpdateDate(DateUtil.getCurrentDate18());
		entry.setStatus(DictConstant.BILL_STATUS_INVALID);
		executeUpdate("Dict.updateDictEntry", entry);
	}
	/**
	 * 保存字典项
	 * @param item
	 */
	public void saveDictItem(DictItem item,String userid){
		item.setCreateDate(DateUtil.getCurrentDate18());
		item.setCreateId(userid);
		item.setUpdateDate(DateUtil.getCurrentDate18());
		item.setUpdateId(userid);
		item.setStatus(DictConstant.BILL_STATUS_SAVE);
		executeInsert("Dict.saveDictItem", item);
	}
	/**
	 * 修改字典项
	 * @param item
	 */
	public void updateDictItem(DictItem item,String userid){
		item.setUpdateDate(DateUtil.getCurrentDate18());
		item.setUpdateId(userid);
		item.setStatus(DictConstant.BILL_STATUS_SAVE);
		executeUpdate("Dict.updateDictItem", item);
	}
	/**
	 * 删除字典项
	 * @param item
	 */
	public void deleteDictItem(DictItem item, String userid){
		item.setUpdateId(userid);
		item.setUpdateDate(DateUtil.getCurrentDate18());
		item.setStatus(DictConstant.BILL_STATUS_INVALID);
		executeUpdate("Dict.updateDictItem", item);
	}
	/**
	 * 删除无效的数据字典行记录
	 * @param entryCode
	 */
	public void deleteInvalidDictItem(String entryCode){
		delete("Dict.deleteInvalidDictItem", entryCode);
	}
}
