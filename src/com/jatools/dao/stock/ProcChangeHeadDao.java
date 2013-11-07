package com.jatools.dao.stock;

import java.util.HashMap;
import java.util.Map;
import com.jatools.common.Pager;
import com.jatools.vo.stock.ProcChangeHead;

/**
 * 形态转换单头表Dao
 * @author ren.ming<br>
 * Created 2011-11-29
 */
public interface ProcChangeHeadDao {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getProcChangeHeadData(Map<String, String> condition);
	/**
	 * 保存形态转换单头表
	 * @param procChangeHead
	 */
	void saveProcChangeHead(ProcChangeHead procChangeHead);
	/**
	 * 根据id获取形态转换单头表
	 * @param billid
	 * @return
	 */
	ProcChangeHead getProcChangeHead(String billid);
	/**
	 * 保存或修改形态转换单头表
	 * @param procChangeHead
	 */
	void updateProcChangeHead(ProcChangeHead procChangeHead);
	
	/**
	 * 修改头表的状态
	 * @param params
	 */
	@SuppressWarnings("rawtypes")
	public void modifyProcChangeHeadStatus(Map params);
	
	public void modifyProcChangeHeadStatus(String billid, String status, String userId);
	/**
	 * 删除形态转换单头表
	 * @param billid
	 */
	void deleteProcChangeHead(String billid);
	
	double getSumMoneyByOrnaCodes(String ornaCodes);

	/**
	 * 装箱单回写快递单号
	 * @param headid 装箱单id
	 * @param expressNo null 表示清除快递单号
	 */
	void updatePackNoFromMovePack(String headid, String billNo, String expressNo);
}
