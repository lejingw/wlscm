package com.jatools.manager.basic;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.basic.OrgGroup;

/**
 * @author  ren.ming
 * Created 2011.11.16
 * <br>
 *  单位柜组manager
 */
public interface OrgGroupManager {
	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getOrgGroupPageData(Map<String, String> condition);

	/**
	 * 保存单位柜组
	 * @param orgGroup
	 */
	void saveOrgGroup(OrgGroup orgGroup);

	/**
	 * 根据id获取单位柜组
	 * @param groupId
	 * @return
	 */
	OrgGroup getOrgGroup(String groupId);

	/**
	 * 保存或修改单位柜组
	 * @param orgGroup
	 */
	void updateOrgGroup(OrgGroup orgGroup);

	/**
	 * 删除单位柜组
	 * @param groupId
	 */
	void deleteOrgGroup(String groupId);
	
	/**
	 * 保存或修改单位柜组
	 * @param orgGroup
	 */
	public void saveOrUpdateOrgGroup(OrgGroup orgGroup);
	
	
	public void copyOrgGroup(String type, String source, String dest, String userId);

}
