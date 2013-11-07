package com.jatools.manager.stock;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.stock.ProcPackageOldline;

/**
 * @author  ren.ming<br>
 * Created 2011.11.16
 * <br>
 * 拆包单原行表manager
 */
public interface ProcPackageOldlineManager {
	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getProcPackageOldlineData(Map<String, String> condition);

	/**
	 * 保存拆包单原行表
	 * @param procPackageOldline
	 */
	void saveProcPackageOldline(ProcPackageOldline procPackageOldline);

	/**
	 * 根据id获取拆包单原行表
	 * @param lineid
	 * @return
	 */
	ProcPackageOldline getProcPackageOldline(String lineid);

	/**
	 * 保存或修改拆包单原行表
	 * @param procPackageOldline
	 */
	void updateProcPackageOldline(ProcPackageOldline procPackageOldline);

	/**
	 * 删除拆包单原行表
	 * @param lineid
	 */
	void deleteProcPackageOldline(String lineid);
	
	
	/**
	 * 删除拆包单原行表
	 * @param billid
	 */
	void deleteProcPackageOldlineByBillid(String billid);
	
	/**
	 * 保存或修改拆包单原行表
	 * @param procPackageOldline
	 */
	public void saveOrUpdateProcPackageOldline(ProcPackageOldline procPackageOldline);
	
	/**
	 * 根据单号获取拆包单原行表
	 * @param billid
	 * @return
	 */
	public List<ProcPackageOldline> getProcPackageOldlineList(String billid);

}
