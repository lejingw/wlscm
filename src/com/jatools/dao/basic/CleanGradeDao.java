package com.jatools.dao.basic;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.basic.CleanGrade;

public interface CleanGradeDao {
	/**
	 * 分页
	 * @param condition
	 * @return
	 */
	Pager getCleanGradePageData(Map<String,String> condition);
	/**
	 * 保存单据
	 */
	void saveCleanGrade(CleanGrade colorGradeGrade);
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
	void updateCleanGrade(CleanGrade colorGradeGrade);
	/**
	 * 根据ID删除单据
	 * @param vendorId
	 */
	void deleteCleanGrade(String id);
	/**
	 * 获取颜色品质
	 * @param sc
	 * @return
	 */
	public CleanGrade getCleanGrade(CleanGrade a);
}
