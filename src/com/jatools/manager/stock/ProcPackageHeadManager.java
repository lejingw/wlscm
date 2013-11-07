package com.jatools.manager.stock;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.bd.Tag;
import com.jatools.vo.stock.ProcPackageHead;
import com.jatools.vo.stock.ProcPackageNewline;
import com.jatools.vo.stock.ProcPackageOldline;

/**
 * @author  ren.ming<br>
 * Created 2011.11.16
 * <br>
 *  拆包混包单头表manager
 */
public interface ProcPackageHeadManager {
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
	 * 删除拆包混包单头表
	 * @param billid
	 */
	void deleteProcPackageHead(String billid);
	
	/**
	 * 保存或修改拆包混包单头表
	 * @param procPackageHead
	 */
	public void saveOrUpdateProcPackageHead(ProcPackageHead procPackageHead, List<ProcPackageOldline> oldLines, List<ProcPackageNewline> newLines, String ids[]);
	
	/**
	 * 关闭单据 成功结束单据<br>
	 * 1、旧饰品出库<br>
	 * 2、新饰品入库<br>
	 * 3、创建交接单<br>
	 * 4、创建入库单
	 * @param billid 单据编号
	 * @param userId 当前处理人
	 */
	public void closePackageHead(String billid, String needChecked,  String userId);

	/**
	 * 审核单据
	 * @param billid
	 * @param userId
	 */
	void checkBillhead(String billid, String userId);
	
	
	/**
	 * 取打印标签数据对象
	 * @param billid
	 * @return
	 */
	List<Tag> getPrintData(String billid);
	
	int selectAnalysCount(String oldAnalysId, String newAnalysId);
	
	void saveAndCloseProcPackageHead(ProcPackageHead procPackageHead, List<ProcPackageOldline> oldLines, List<ProcPackageNewline> newLines, String ids[], String needChecked);
	
}
