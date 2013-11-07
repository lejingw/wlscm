package com.jatools.dao.util.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ibatis.sqlmap.client.SqlMapSession;
import com.jatools.dao.BaseDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.dao.util.TransactionAction;
import com.jatools.vo.bd.Analysis;
import com.jatools.vo.bd.Org;
import com.jatools.vo.sys.RightMapping;
import com.jatools.web.util.DateUtil;

@SuppressWarnings("unchecked")
public class CommonDaoImpl extends BaseDao implements CommonDao {
	private static final String BIZ_TYPE_BILLNO = "billNo";//单据编号
	private static final String BIZ_TYPE_ORNA_CODE = "ornaCode";//饰品编码
	private static final String BIZ_TYPE_BAR_CODE = "barCode";//饰品条码
	private static final String BIZ_TYPE_ORNA_CODE_D0 = "barCodeD0";//裸石饰品编码
	/**
	 * 查找单个string类型返回值
	 * @param sql
	 * @return
	 */
	public String querySingleString(String sql){
		return (String) executeQueryForObject("Common.querySingleString", sql);
	}
	/**
	 * 获取单据编号
	 */
	public synchronized String getBillno(final String billCode) {
		return (String)executeOutTransaction(new TransactionAction() {
			public Object executeAction(SqlMapSession session) throws SQLException {
				Map<String, String> map = new HashMap<String, String>();
				String bizDate = DateUtil.formatSdf6(null);
				map.put("bizType", BIZ_TYPE_BILLNO);
				map.put("bizCode", billCode);
				map.put("bizDate", bizDate);
				map.put("billCode", billCode);
				
				Map<String, Object> resultMap = (Map<String, Object>) session.queryForObject("Common.getCurrentBillnoIndex", map);
				if(null == resultMap){
					throw new RuntimeException("单据[编码:"+billCode+"]未配置单据编码规则");
				}
				String billnoRule  = (String)resultMap.get("billnoRule");
				String bizIndex = (String)resultMap.get("bizIndex");
				if (null == bizIndex) {
					map.put("bizIndex", "1");
					session.insert("Common.createIndex", map);
					bizIndex = "0";
				} else {
					session.update("Common.increaseIndex", map);
				}
				return billnoRule + bizDate + formatNum(Integer.valueOf(bizIndex) + 1);
			}
		});
	}
	/**
	 * 格式化数字
	 */
	private String formatNum(int seq) {
		return String.format("%04d", seq);
	}
	
	/**
	 * 根据单据编码获取对应财务关系
	 * @param billCode
	 * @return
	 */
	public String getBillFinance(String billCode){
		String finance = (String)executeQueryForObject("Common.getBillFinance", billCode);
		if(null == finance){
			throw new RuntimeException("单据[编码:"+billCode+"]未配置单据编码规则");
		}
		return finance;
	}

	/**
	 * 获取分析范围
	 * @param itemClassId 大类id
	 * @param ornaClassId 小类id
	 * @param allQty 总重量
	 * @param basicPrice 销价
	 * @param mainWeight 主石重量
	 * @return
	 */
	public Analysis getAnalysis(String itemClassId, String ornaClassId, String allQty, String basicPrice, String mainWeight){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("itemClassId", itemClassId);
		condition.put("ornaClassId", ornaClassId);
		condition.put("allQty", allQty);
		condition.put("basicPrice", basicPrice);
		condition.put("mainWeight", mainWeight);
		return (Analysis)executeQueryForObject("Common.getAnalysis", condition);
	}
	/**
	 * 生成饰品编码
	 * @return
	 */
	public synchronized String getOrnaCode(final String inivCode, final String itemClassId, final String ornaClassId){
		return (String)executeOutTransaction(new TransactionAction() {
			public Object executeAction(SqlMapSession session) throws SQLException {
				Map<String, String> map = new HashMap<String, String>();
				String bizDate = DateUtil.formatSdf9(null);
				map.put("bizType", BIZ_TYPE_ORNA_CODE);
				map.put("bizCode", inivCode + "|" + itemClassId + "|" + ornaClassId);
				map.put("bizDate", bizDate);
				map.put("itemClassId", itemClassId);
				map.put("ornaClassId", ornaClassId);
		
				Map<String, Object> resultMap = (Map<String, Object>) session.queryForObject("Common.getCurrentOrnaCodeIndex", map);
				if(null == resultMap || null == resultMap.get("itemClassCode") || null == resultMap.get("ornaClassCode")){
					throw new RuntimeException("不能获取大类小类编码");
				}
				String itemClassCode  = (String)resultMap.get("itemClassCode");
				String ornaClassCode  = (String)resultMap.get("ornaClassCode");
				String bizIndex = (String)resultMap.get("bizIndex");
				
				if (null == bizIndex) {
					map.put("bizIndex", "1");
					session.insert("Common.createIndex", map);
					bizIndex = "0";
				} else {
					session.update("Common.increaseIndex", map);
				}
				return inivCode + itemClassCode + ornaClassCode + bizDate + formatNum(Integer.valueOf(bizIndex) + 1);
			}
		});
	}
	/**
	 * 生成饰品条码
	 * @return
	 */
	public synchronized String getBarCode(String itemClassId, String ornaClassId){
		return (String)executeOutTransaction(new TransactionAction() {
			public Object executeAction(SqlMapSession session) throws SQLException {
				Map<String, String> map = new HashMap<String, String>();
				String bizDate = DateUtil.formatSdf9(null);
				map.put("bizType", BIZ_TYPE_BAR_CODE);
				map.put("bizCode", "-1");
				map.put("bizDate", bizDate);
		
				Map<String, Object> resultMap = (Map<String, Object>) session.queryForObject("Common.getCurrentBarCodeIndex", map);
				if(null == resultMap){
					throw new RuntimeException("不能获取流水号");
				}
				String bizIndex = (String)resultMap.get("bizIndex");
				if (null == bizIndex) {
					map.put("bizIndex", "1");
					session.insert("Common.createIndex", map);
					return bizDate + String.format("%06d", 1) + "0";
				} else {
					session.update("Common.increaseIndex", map);
					return bizDate + String.format("%06d", Integer.parseInt(bizIndex) + 1) + "0";
				}
			}
		});
	}

	/**
	 * 生成裸石饰品编码
裸石编码：大类ID156
根据平均粒数--传入的参数
平均粒数重量=总重量/总粒数
参数=平均粒数重量*100
K30000+4位流水号				30000
B30000+4位流水号				30000
S3000+5位流水号				3000
300+7位流水号					300
030+7位流水号					30
003+7位流水号					3
以上信息加上流水号满足10位
	 * @return
	 */
	public synchronized String getOrnaCodeD0(final Double avgFenShu){
		return (String)executeOutTransaction(new TransactionAction() {
			public Object executeAction(SqlMapSession session) throws SQLException {
				if(avgFenShu>=10000){
					throw new RuntimeException("参数控制[<平均分数>]:<超出范围>");
				}
				String avgNum = "" + new Double(avgFenShu*100).intValue();
				Map<String, String> map = new HashMap<String, String>();
				map.put("bizType", BIZ_TYPE_ORNA_CODE_D0);
				map.put("bizCode", "" + avgNum);
				map.put("bizDate", "-1");
		
				Map<String, Object> resultMap = (Map<String, Object>)session.queryForObject("Common.getCurrentBarCodeIndex", map);
				if(null == resultMap){
					throw new RuntimeException("不能获取流水号");
				}
				String bizIndex = (String)resultMap.get("bizIndex");
				if (null == bizIndex) {
					map.put("bizIndex", "1");
					session.insert("Common.createIndex", map);
					return formateD0(avgNum, 1);
				} else {
					session.update("Common.increaseIndex", map);
					return formateD0(avgNum, Integer.parseInt(bizIndex) + 1);
				}
			}
		});
	}
	private String formateD0(String avgNum, int bizIndex){
		if(avgNum.length() == 6){
			return "K" + avgNum.subSequence(0, 5) + String.format("%04d", bizIndex);
		}
		if(avgNum.length() == 5){
			return "B" + avgNum + String.format("%04d", bizIndex);
		}
		if(avgNum.length() == 4){
			return "S" + avgNum + String.format("%05d", bizIndex);
		}
		if(avgNum.length() == 3){
			return avgNum + String.format("%07d", bizIndex);
		}
		if(avgNum.length() == 2){
			return "0" + avgNum + String.format("%07d", bizIndex);
		}
		if(avgNum.length() == 1){
			return "00" + avgNum + String.format("%07d", bizIndex);
		}
		return null;
	}

	/**
	 * 获取工具条权限
	 * @param toolbarCode
	 * @param orgId
	 * @param userid
	 * @return
	 */
	public List<RightMapping> getToolbarRight(String toolbarCode, String orgId, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("toolbarCode", toolbarCode);
		condition.put("orgId", orgId);
		condition.put("userid", userid);
		return executeQueryForList("Common.getToolbarRight", condition);
	}
	
	@Override
	public List<Org> getCurrentUserOrgList(String userId) {
		return (List<Org>)executeQueryForList("Common.getCurrentUserOrgList", userId);
	}

    public List<Org> getCurrentUserJmOrgList(String userId) {
        return (List<Org>)executeQueryForList("Common.getCurrentUserJmOrgList", userId);
    }
}
