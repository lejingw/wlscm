package com.jatools.dao.basic;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.basic.OrgGroup;

/**
 * @author  ren.ming<br>
 * Created 2011.11.16
 * <br>
 *  单位柜组dao
 */
public interface OrgGroupDao {
	public final static String ORG = "org";
	public final static String ITEM_CLASS = "itemClass";
	public final static String ORNA_CLASS = "ornaClass";
	/**
	 * 获取分页数据
	 * 
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

	int selectOrgGroupCount(OrgGroup orgGroup);
	
	void copyOrgGroup(String type, String source, String dest, String userId);
}
