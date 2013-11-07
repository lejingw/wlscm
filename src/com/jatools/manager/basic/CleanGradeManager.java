package com.jatools.manager.basic;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.basic.CleanGrade;

public interface CleanGradeManager {
	/**
	 * 分页
	 * @param condition
	 * @return
	 */
	Pager getCleanGradePageData(Map<String,String> condition);
	/**
	 * 保存单据
	 */
	void saveCleanGrade(CleanGrade CleanGrade);
	/**
	 * 根据ID返回对象
	 * @param vendorId
	 * @return
	 */
	CleanGrade getCleanGradeById(String id);
	/**
	 * 保存修改数据
	 * @param vendor
	 */
	void updateCleanGrade(CleanGrade CleanGrade);
	/**
	 * 根据ID删除单据
	 * @param vendorId
	 */
	void deleteCleanGrade(String id);
	/**
	 * 获取总系数
	 * @param sc
	 * @return
	 */
	public CleanGrade getCleanGrade(CleanGrade a);
}
