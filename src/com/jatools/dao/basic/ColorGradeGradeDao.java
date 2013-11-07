package com.jatools.dao.basic;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.basic.ColorGradeGrade;

public interface ColorGradeGradeDao {
	/**
	 * 分页
	 * @param condition
	 * @return
	 */
	Pager getColorGradeGradePageData(Map<String,String> condition);
	/**
	 * 保存单据
	 */
	void saveColorGradeGrade(ColorGradeGrade colorGradeGrade);
	/**
	 * 根据ID返回对象
	 * @param vendorId
	 * @return
	 */
	ColorGradeGrade getColorGradeGradeById(String id);
	/**
	 * 保存修改数据
	 * @param vendor
	 */
	void updateColorGradeGrade(ColorGradeGrade colorGradeGrade);
	/**
	 * 根据ID删除单据
	 * @param vendorId
	 */
	void deleteColorGradeGrade(String id);
	/**
	 * 获取颜色品质
	 * @param sc
	 * @return
	 */
	public ColorGradeGrade getColorGradeGrade(ColorGradeGrade a);
}
