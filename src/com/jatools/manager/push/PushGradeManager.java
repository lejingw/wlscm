package com.jatools.manager.push;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.push.PushGrade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PushGradeManager {
	/**
	 * 获取分页数据
	 */
	public Pager getPushGradePageData(Map<String, String> condition);
	/**
	 * 保存或修改
	 */
	public void saveOrUpdatePushGrade(PushGrade dn, String userId);
	/**
	 * 检查记录是否存在
	 */
	public List<PushGrade> checkPushGradeRepeat(PushGrade dn);
	/**
	 * 删除
	 */
	public void deletePushGrade(List<String> billIdList);

    public void importExcel(HttpServletRequest req, HttpServletResponse res);
}
