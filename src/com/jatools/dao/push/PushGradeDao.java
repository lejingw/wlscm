package com.jatools.dao.push;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.push.PushGrade;

public interface PushGradeDao {
	/**
	 * 获取分页数据
	 */
	public Pager getPushGradePageData(Map<String, String> condition);
	/**
	 * 保存
	 * @param dn
	 */
	public void savePushGrade(PushGrade dn);
	/**
	 * 修改
	 * @param dn
	 */
	public void updatePushGrade(PushGrade dn);

	/**
	 * 检查记录是否存在
	 * @param dn
	 */
	public List<PushGrade> checkPushGradeRepeat(PushGrade dn);
	/**
	 * 删除
	 * @param billIdList
	 */
	public void deletePushGrade(List<String> billIdList);

    void savePushGradeFromExcel(String seqId, String userId);
}
