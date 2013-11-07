package com.jatools.manager.stock;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.stock.ProcPackageNewline;

/**
 * @author  ren.ming<br>
 * Created 2011.11.16
 * <br>
 *  拆包单新行表manager
 */
public interface ProcPackageNewlineManager {
	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getProcPackageNewlineData(Map<String, String> condition);

	/**
	 * 保存拆包单新行表
	 * @param procPackageNewline
	 */
	void saveProcPackageNewline(ProcPackageNewline procPackageNewline);

	/**
	 * 根据id获取拆包单新行表
	 * @param lineid
	 * @return
	 */
	ProcPackageNewline getProcPackageNewline(String lineid);

	/**
	 * 保存或修改拆包单新行表
	 * @param procPackageNewline
	 */
	void updateProcPackageNewline(ProcPackageNewline procPackageNewline);

	/**
	 * 删除拆包单新行表
	 * @param lineid
	 */
	void deleteProcPackageNewline(String lineid);
	
	/**
	 * 删除拆包单新行表
	 * @param billid
	 */
	void deleteProcPackageNewlineByBillid(String billid);
	
	/**
	 * 保存或修改拆包单新行表
	 * @param procPackageNewline
	 */
	public void saveOrUpdateProcPackageNewline(ProcPackageNewline procPackageNewline);

	/**
	 * 根据单号获取拆包单新行表
	 * @param billid
	 * @return
	 */
	public List<ProcPackageNewline> getProcPackageNewlineList(String billid);
}
