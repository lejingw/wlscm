package com.jatools.dao.calc;

import java.util.Map;
import com.jatools.common.Pager;
import com.jatools.vo.calc.Accessory;

public interface AccessoryDao {
	/**
	 * 分页
	 * @param condition
	 * @return
	 */
	Pager getAccessoryPageData(Map<String,String> condition);
	/**
	 * 保存单据
	 */
	void saveAccessory(Accessory accessory);
	/**
	 * 根据ID返回对象
	 * @param vendorId
	 * @return
	 */
	Accessory getAccessoryById(String id);
	/**
	 * 保存修改数据
	 * @param vendor
	 */
	void updateAccessory(Accessory accessory);
	/**
	 * 根据ID删除单据
	 * @param vendorId
	 */
	void deleteAccessory(String id);
	/**
	 * 获取总系数
	 * @param sc
	 * @return
	 */
	public Accessory getAccessory(Accessory a);
}
