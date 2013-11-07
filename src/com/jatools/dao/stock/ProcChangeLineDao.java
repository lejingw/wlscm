package com.jatools.dao.stock;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.stock.ProcChangeLine;

/**
 * 形态转换单原行表Dao
 * @author ren.ming<br>
 * Created 2011-11-28
 */
public interface ProcChangeLineDao {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getProcChangeLineData(Map<String, String> condition);
	/**
	 * 保存拆包单原行表
	 * @param ProcChangeLine
	 */
	void saveProcChangeLine(ProcChangeLine ProcChangeLine);
	/**
	 * 根据id获取拆包单原行表
	 * @param lineid
	 * @return
	 */
	ProcChangeLine getProcChangeLine(String lineid);
	/**
	 * 保存或修改拆包单原行表
	 * @param ProcChangeLine
	 */
	void updateProcChangeLine(ProcChangeLine ProcChangeLine);
	/**
	 * 删除拆包单原行表
	 * @param lineid
	 */
	void deleteProcChangeLine(String lineid);
	
	/**
	 * 删除拆包单原行表
	 * @param billid
	 */
	void deleteProcChangeLineByBillid(String billid);
	
	/**
	 * 根据单号获取拆包单原行表
	 * @param billid
	 * @return
	 */
	List<ProcChangeLine> getProcChangeLineList(String billid);
}
