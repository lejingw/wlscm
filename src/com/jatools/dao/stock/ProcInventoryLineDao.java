package com.jatools.dao.stock;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.stock.ProcInventoryLine;

/**
 * 盘点单原行表Dao
 * @author ren.ming<br>
 * Created 2011-12-1
 */
public interface ProcInventoryLineDao {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getProcInventoryLineData(Map<String, String> condition);
	/**
	 * 保存盘点单原行表
	 * @param procInventoryLine
	 */
	void saveProcInventoryLine(ProcInventoryLine procInventoryLine);
	/**
	 * 根据id获取盘点单原行表
	 * @param lineid
	 * @return
	 */
	ProcInventoryLine getProcInventoryLine(String lineid);
	/**
	 * 保存或修改盘点单原行表
	 * @param procInventoryLine
	 */
	void updateProcInventoryLine(ProcInventoryLine procInventoryLine);
	/**
	 * 删除盘点单原行表
	 * @param lineid
	 */
	void deleteProcInventoryLine(String lineid);
	
	/**
	 * 删除盘点单原行表
	 * @param billid
	 */
	void deleteProcInventoryLineByBillid(String billid);
	
	/**
	 * 根据单号获取盘点单原行表
	 * @param billid
	 * @return
	 */
	List<ProcInventoryLine> getProcInventoryLineList(String billid);
	
	/**
	 * 判断饰品是否已经存在与行表中
	 * @param ornaCode
	 * @param billid
	 * @return
	 */
	public boolean isExistOrnaCode(String ornaCode, String billid);
}
