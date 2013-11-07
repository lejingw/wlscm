package com.jatools.dao.basic;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.basic.LabelType;

public interface LabelTypeDao {
	/**
	 * 分页
	 * @param condition
	 * @return
	 */
	Pager getLabelTypePageData(Map<String,String> conditionMap);
	/**
	 * 保存单据
	 */
	void saveLabelType(LabelType LabelType);
	/**
	 * 根据ID返回对象
	 * @param vendorId
	 * @return
	 */
	LabelType getLabelTypeById(String id);
	/**
	 * 保存修改数据
	 * @param vendor
	 */
	void updateLabelType(LabelType LabelType);
	/**
	 * 根据ID删除单据
	 * @param vendorId
	 */
	void deleteLabelType(String id);
	/**
	 * 是否有重复
	 * @param qid
	 * @return
	 */
	boolean checkLabelTypeRepeat(LabelType labelType);
	void copyLabelType(Map<String, String> condition);
}
