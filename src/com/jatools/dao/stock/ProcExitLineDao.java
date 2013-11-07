package com.jatools.dao.stock;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.stock.ProcExitLine;

/**
 * 退料单原行表Dao
 * @author ren.ming<br>
 * Created 2011-11-28
 */
public interface ProcExitLineDao {

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
	 * @param lineid
	 */
	void deleteProcExitLine(String lineid, String userId);
	
	/**
	 * 删除退料单原行表
	 * @param billid
	 */
	void deleteProcExitLineByBillid(String billid);
	
	/**
	 * 根据单号获取退料单原行表
	 * @param billid
	 * @return
	 */
	List<ProcExitLine> getProcExitLineList(String billid);
	
	void subProdAccountUserNums(String lineid, String userId);
}
