package com.jatools.dao.stock;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.bd.Tag;
import com.jatools.vo.stock.ProcPackageHead;

/**
 * 拆包混包单头表Dao
 * @author ren.ming<br>
 * Created 2011-11-20
 */
public interface ProcPackageHeadDao {

	/**
	 * 获取分页数据
	 * @param condition
	 * @return
	 */
	Pager getProcPackageHeadData(Map<String, String> condition);
	/**
	 * 保存拆包混包单头表
	 * @param procPackageHead
	 */
	void saveProcPackageHead(ProcPackageHead procPackageHead);
	/**
	 * 根据id获取拆包混包单头表
	 * @param billid
	 * @return
	 */
	ProcPackageHead getProcPackageHead(String billid);
	/**
	 * 保存或修改拆包混包单头表
	 * @param procPackageHead
	 */
	void updateProcPackageHead(ProcPackageHead procPackageHead);
	
	/**
	 * 修改头表的状态
	 * @param params
	 */
	public void modifyProcPackageHeadStatus(String billid, String status, String userId);
	/**
	 * 删除拆包混包单头表
	 * @param billid
	 */
	void deleteProcPackageHead(String billid);
	
	/**
	 * 取打印标签数据对象
	 * @param billid
	 * @return
	 */
	List<Tag> getPrintData(String billid);
	
	
	int selectAnalysCount(String oldAnalysId, String newAnalysId);
}
