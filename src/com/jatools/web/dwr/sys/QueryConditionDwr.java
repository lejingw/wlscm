package com.jatools.web.dwr.sys;

import java.util.List;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import com.jatools.common.CommonUtil;
import com.jatools.common.QueryConditionUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.sys.QueryConditionManager;
import com.jatools.vo.sys.Dict;
import com.jatools.vo.sys.QueryCondition;
import com.jatools.vo.sys.QueryConditionHead;
import com.jatools.vo.sys.QueryConditionLine;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.cache.DictCache;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class QueryConditionDwr {
	private static Logger logger = Logger.getLogger(QueryConditionDwr.class);
	
	private QueryConditionManager queryConditionManager;

	public void setQueryConditionManager(QueryConditionManager queryConditionManager) {
		this.queryConditionManager = queryConditionManager;
	}
	
	public void saveQueryCondition(QueryConditionHead head, List<QueryConditionLine> addLineArr, List<QueryConditionLine> updateLineArr, List<String> deleteIdArr, HttpSession session){
		String userid = CommonUtil.getSessionUserId(session);
		if(StringUtil.isEmpty(head.getHeadid())){
			head.setCreateId(userid);
			head.setCreateDate(DateUtil.getCurrentDate18());
			head.setStatus(DictConstant.BILL_STATUS_SAVE);
		}
		head.setUpdateId(userid);
		head.setUpdateDate(DateUtil.getCurrentDate18());
		queryConditionManager.saveOrUpdateQueryCondition(head, addLineArr, updateLineArr, deleteIdArr);
	}
	
	public void deleteQueryCondition(String headid){
		queryConditionManager.deleteQueryCondition(headid);
	}
	
	public List<QueryConditionLine> getQueryConditionLine(String headid){
		return queryConditionManager.getQueryConditionLineByheadid(headid);
	}
	
	public List<SelectorOption> getQueryFieldName(String queryCode){
		return queryConditionManager.getQueryFieldName(queryCode);
	}
	/**
	 * inputType	1手工输入、2SQL返回SelectorOption、3JSONArray、4JS Target、5数据字典
	 * @param lineid
	 * @return
	 */
	public String getQueryFieldInfo(String lineid){
		QueryConditionLine line = queryConditionManager.getQueryConditionLine(lineid);
		JSONObject jo = new JSONObject();
		jo.put("fieldType", line.getFieldType());
		jo.put("inputType", line.getInputType());
		Object inputContent = getQueryContentForSlt(line.getInputType(), line.getInputValue());
		jo.put("inputContent", inputContent);
		JSONArray operation = getOperation(line.getFieldType(), line.getInputType());
		jo.put("operation", operation);
		System.out.println(jo.toString());
		return jo.toString();
	}
	private String[] operArr = new String[]{"大于$2${0}>{1}", "大于等于$2${0}>={1}", "小于等于$2${0}<={1}", "小于$2${0}<{1}",	"等于$2${0}={1}",  "不等于$2${0}<>{1}", "为空$1${0} is null", "非空$1${0} is not null", "包含$2$instr({0}, '{1}')>0", "不包含$2$instr({0}, '{1}')=0"};
	private JSONArray getOperation(String fieldType, String inputType) {
		JSONArray ja = new JSONArray();
		if(!"1".equals(inputType)){
			getOper(ja, fieldType, new int[]{4, 5, 6, 7});
			return ja;
		}
		//1字符 2整数 3小数 4渲染 5日期str 6日期date
		if("1".equals(fieldType)){
			getOper(ja, fieldType, new int[]{4, 5, 6, 7, 8, 9});
		}else if("2".equals(fieldType)){
			getOper(ja, fieldType, new int[]{0, 1, 2, 3, 4, 5});
		}else if("3".equals(fieldType)){
			getOper(ja, fieldType, new int[]{0, 1, 2, 3, 4, 5});
		}else if("4".equals(fieldType)){
			getOper(ja, fieldType, new int[]{6, 7, 8, 9});
		}else if("5".equals(fieldType) || "6".equals(fieldType)){
			getOper(ja, fieldType, new int[]{0, 1, 2, 3, 4, 5, 6, 7});
		}
		return ja;
	}
	private void getOper(JSONArray ja, String fieldType, int[] indexs){
		for(int i=0;i<indexs.length;i++){
			JSONObject jo = new JSONObject();
			String[] tmp = operArr[indexs[i]].split("[$]");
			jo.put("value", indexs[i]+"_" + tmp[1]+"_" + fieldType);//index + "_" + n元
			jo.put("text", tmp[0]);
			ja.add(jo);
		}
	}

	private Object getQueryContentForSlt(String inputType, String inputValue){
		if("1".equals(inputType)){//1手工输入
			return null;
		}else if("2".equals(inputType)){//2SQL返回SelectorOption
			List<SelectorOption> list = queryConditionManager.getQueryContentBySqlForSlt(inputValue);
			JSONArray ja = new JSONArray();
			for(SelectorOption so : list){
				JSONObject jo = new JSONObject();
				jo.put("value", so.getValue());
				jo.put("text", so.getText());
				ja.add(jo);
			}
			return ja;
		}else if("3".equals(inputType)){//JSONArray
			JSONArray ja = JSONArray.fromObject(inputValue);
			return ja;
		}else if("4".equals(inputType)){//4JS Target
			return inputValue;
		}else if("5".equals(inputType)){//5数据字典
			List<Dict> list = DictCache.getInstance().getDicts(inputValue);
			JSONArray ja = new JSONArray();
			for(Dict dict : list){
				JSONObject jo = new JSONObject();
				jo.put("value", dict.getItemKey());
				jo.put("text", dict.getItemValue());
				ja.add(jo);
			}
			return ja;
		}else{
			return null;
		}
	}
	
	public String saveQueryToTemp(String queryCode, List<QueryCondition> queryList, HttpSession session){
		String userid = CommonUtil.getSessionUserId(session);
		session.setAttribute("query_condition_" + userid + "_" + queryCode, queryList);
		if(null == queryList || queryList.size()<1){
			session.setAttribute(QueryConditionUtil.QUERY_SQL_SESSION_KEY, "-1");
			return null;
		}
		StringBuffer sql = createQuerySql(queryCode, queryList);
		session.setAttribute(QueryConditionUtil.QUERY_SQL_SESSION_KEY, sql.toString());
		return null;
	}

	private StringBuffer createQuerySql(String queryCode, List<QueryCondition> queryList) {
		List<QueryConditionLine> lineList = queryConditionManager.getQueryConditionLineByQueryCode(queryCode);
		StringBuffer sql = new StringBuffer(" and ( ");
		boolean flag = true;
		for(QueryCondition query : queryList){
			QueryConditionLine line = getQueryConditionLine(lineList, query.getConditionId());
			if(null == line){
				continue;
			}
			if(flag){
				flag = false;
			}else{
				sql.append(" " + nullToEmpty(query.getRelation()));
			}
			sql.append(" " + nullToEmpty(query.getLeftQuot()));
			String oper = operArr[Integer.parseInt(query.getOperIndex().split("_")[0])];
			String[] tmp = oper.split("[$]");
			String express = tmp[2];
			sql.append(getExpress(line, query, express));
			sql.append("");
			sql.append(" " + nullToEmpty(query.getRightQuot()));
		}
		sql.append(" )");
		return sql;
	}
	/**
	 * 0大于$2${0}>{1}
	 * 1大于等于$2${0}>={1}
	 * 2小于等于$2${0}<={1}
	 * 3小于$2${0}<{1}
	 * 4等于$2${0}={1}
	 * 5不等于$2${0}<>{1}
	 * 6为空$1${0} is null
	 * 7非空$1${0} is not null
	 * 8包含$2$instr(','||{0}||',', ',{1},')>0
	 * 9不包含$2$instr(','||{0}||',', ',{1},')=0
	 */
	
	private String getExpress(QueryConditionLine line, QueryCondition query, String express){
		//1字符 2整数 3小数 4渲染 5日期str 6日期date
		String fieldName = line.getFieldName();
		String content = query.getContent();
		if("1".equals(line.getFieldType())){
			//getOper(ja, new int[]{4, 5, 6, 7, 8, 9});
			String oper = query.getOperIndex().split("_")[0];
			if("6".equals(oper) || "7".equals(oper))
				return express.replace("{0}", fieldName);
			if("4".equals(oper) || "5".equals(oper))
				content = "'" + content + "'";
			return express.replace("{0}", fieldName).replace("{1}", content);
		}else if("2".equals(line.getFieldType()) || "3".equals(line.getFieldType())){
			//getOper(ja, new int[]{0, 1, 2, 3, 4, 5});
			return express.replace("{0}", fieldName).replace("{1}", content);
		}else if("4".equals(line.getFieldType())){
			String oper = query.getOperIndex().split("_")[0];
			//getOper(ja, new int[]{6, 7, 8, 9});
			if("6".equals(oper) || "7".equals(oper))
				return express.replace("{0}", fieldName);
			else{
				if("8".equals(oper)){
					return fieldName + " in (" + line.getToIdSql().replace("$", content) + ")";
				}else if("9".equals(oper)){
					return fieldName + " not in (" + line.getToIdSql().replace("$", content) + ")";
				}
			}
		}else if("5".equals(line.getFieldType())){
			//getOper(ja, new int[]{0, 1, 2, 3, 4, 5, 6, 7});
			return express.replace("{0}", "substr("+fieldName+", 1, 10)").replace("{1}", "'" + content + "'");
		}else if("6".equals(line.getFieldType())){
			//getOper(ja, new int[]{0, 1, 2, 3, 4, 5, 6, 7});
			return express.replace("{0}", "to_char("+fieldName+", 'yyyy-mm-dd')").replace("{1}", "'" + content + "'");
		}
		return null;
	}
	
	private QueryConditionLine getQueryConditionLine(List<QueryConditionLine> lineList, String lineid){
		for(QueryConditionLine line : lineList){
			if(lineid.equals(line.getLineid()))
					return line;
		}
		return null;
	}
	private String nullToEmpty(String str){
		if(null == str){
			return "";
		}
		return str;
	}
	
	public String getQuerySql(String queryCode, HttpSession session){
		String userid = CommonUtil.getSessionUserId(session);
		Object obj = session.getAttribute("query_condition_" + userid + "_" + queryCode);
		if(null != obj && obj instanceof List){
			List<QueryCondition> queryList = (List<QueryCondition>)obj;
			if(queryList.size()>0){
				JSONObject jo = new JSONObject();
				jo.put("query_data", queryList);
				String sql = createQuerySql(queryCode, queryList).toString();
				jo.put("sql", sql);
				return jo.toString();
			}else{
				return null;
			}
		}
		return null;
	}
}