package com.jatools.dao.util;

import java.util.List;
import com.jatools.vo.bd.Analysis;
import com.jatools.vo.bd.Org;
import com.jatools.vo.sys.RightMapping;

public interface CommonDao {
	/**
	 * 查找单个string类型返回值
	 * @param sql
	 * @return
	 */
	public String querySingleString(String sql);
	/**
	 * 获取单据编号
	 */
	public String getBillno(String billCode);
	/**
	 * 根据单据编码获取对应财务关系
	 */
	public String getBillFinance(String billCode);
	
	/**
	 * 获取分析范围
	 * @param itemClassId 大类id
	 * @param ornaClassId 小类id
	 * @param allQty 总重量
	 * @param basicPrice 销价
	 * @param mainWeight 主石重量
	 * @return
	 */
	public Analysis getAnalysis(String itemClassId, String ornaClassId, String allQty, String basicPrice, String mainWeight);
	/**
	 * 生成饰品编码
	 * @return
	 */
	public String getOrnaCode(String inivCode, String itemClassId, String ornaClassId);
	/**
	 * 生成饰品条码
	 * @return
	 */
	public String getBarCode(String itemClassId, String ornaClassId);

	/**
	 * 生成裸石饰品编码
	 * @return
	 */
	public String getOrnaCodeD0(Double avgFenShu);
	/**
	 * 获取工具条权限
	 * @param toolbarCode
	 * @param orgId
	 * @param userid
	 * @return
	 */
	public List<RightMapping> getToolbarRight(String toolbarCode, String orgId, String userid);
	
	/**
	 * 取当前用户所在组织列表
	 * @param userId
	 * @return
	 */
	public List<Org> getCurrentUserOrgList(String userId);

	public List<Org> getCurrentUserJmOrgList(String userId);
}
