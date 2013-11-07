package com.jatools.manager.stock;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.stock.ProcChangeHead;
import com.jatools.vo.stock.ProcChangeLine;

/**
 * @author  ren.ming<br>
 * Created 2011.11.28
 * <br>
 *  形态转换单头表manager
 */
public interface ProcChangeHeadManager {
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
	 * 删除形态转换单头表
	 * @param billid
	 */
	void deleteProcChangeHead(String billid);
	
	/**
	 * 保存或修改形态转换单头表
	 * @param ProcChangeHead
	 */
	public void saveOrUpdateProcChangeHead(ProcChangeHead ProcChangeHead, List<ProcChangeLine> lines, String ids);
	
	public void saveAndCheckProcChangeHead(ProcChangeHead ProcChangeHead, List<ProcChangeLine> lines, String ids);
	
	/**
	 * 关闭单据 成功结束单据<br>
	 * 1、旧饰品出库<br>
	 * 2、新饰品入库<br>
	 * 3、创建交接单<br>
	 * 4、创建入库单
	 * @param billid 单据编号
	 * @param userId 当前处理人
	 */
	public void closeChangeHead(String billid, String userId);
	
	/**
	 * 审核单据
	 * @param billid
	 * @param userId
	 */
	public void checkChangeHead(String billid, String userId);
	

}
