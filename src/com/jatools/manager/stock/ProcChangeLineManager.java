package com.jatools.manager.stock;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.stock.ProcChangeLine;

/**
 * @author  ren.ming<br>
 * Created 2011.11.29
 * <br>
 * 形态转换单原行表manager
 */
public interface ProcChangeLineManager {
	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getProcChangeLineData(Map<String, String> condition);

	/**
	 * 保存形态转换单原行表
	 * @param ProcChangeLine
	 */
	void saveProcChangeLine(ProcChangeLine ProcChangeLine);

	/**
	 * 根据id获取形态转换单原行表
	 * @param lineid
	 * @return
	 */
	ProcChangeLine getProcChangeLine(String lineid);

	/**
	 * 保存或修改形态转换单原行表
	 * @param ProcChangeLine
	 */
	void updateProcChangeLine(ProcChangeLine ProcChangeLine);

	/**
	 * 删除形态转换单原行表
	 * @param lineid
	 */
	void deleteProcChangeLine(String lineid);
	
	
	/**
	 * 删除形态转换单原行表
	 * @param billid
	 */
	void deleteProcChangeLineByBillid(String billid);
	
	/**
	 * 保存或修改形态转换单原行表
	 * @param ProcChangeLine
	 */
	public void saveOrUpdateProcChangeLine(ProcChangeLine ProcChangeLine);
	
	/**
	 * 根据单号获取形态转换单原行表
	 * @param billid
	 * @return
	 */
	public List<ProcChangeLine> getProcChangeLineList(String billid);

}
