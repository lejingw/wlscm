package com.jatools.manager.stock;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.stock.ProcExitLine;

/**
 * @author  ren.ming<br>
 * Created 2011.11.16
 * <br>
 * 退料单原行表manager
 */
public interface ProcExitLineManager {
	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getProcExitLineData(Map<String, String> condition);

	/**
	 * 保存退料单原行表
	 * @param procExitLine
	 */
	void saveProcExitLine(ProcExitLine procExitLine);

	/**
	 * 根据id获取退料单原行表
	 * @param lineid
	 * @return
	 */
	ProcExitLine getProcExitLine(String lineid);

	/**
	 * 保存或修改退料单原行表
	 * @param procExitLine
	 */
	void updateProcExitLine(ProcExitLine procExitLine);

	
	
	/**
	 * 删除退料单原行表
	 * @param billid
	 */
	void deleteProcExitLineByBillid(String billid);
	
	/**
	 * 保存或修改退料单原行表
	 * @param procExitLine
	 */
	public void saveOrUpdateProcExitLine(ProcExitLine procExitLine);
	
	/**
	 * 根据单号获取退料单原行表
	 * @param billid
	 * @return
	 */
	public List<ProcExitLine> getProcExitLineList(String billid);

}
