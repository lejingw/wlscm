package com.jatools.common;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class QueryConditionUtil {
	public static final String QUERY_SQL_SESSION_KEY = "query_sql_session_key";
	/**
	 * 从session获取值
	 * @param req
	 * @param condition
	 * @param keys
	 */
	public static void getSessionParameter(BaseMultiActionController controller, HttpServletRequest req, Map<String, String> condition, String... keys){
		if(null == keys||keys.length<1){
			return ;
		}
		String conditionSessionKey = getConditionSessionKey(controller);
		//检查是否进行页面查询
		if(isUnQuerys(condition)){//显示页面，从session获取查询条件
			Object obj = req.getSession().getAttribute(conditionSessionKey);
			if(null != obj && obj instanceof Map){
				Map<String, String> ssnCondition = (Map<String, String>)obj;
				for(String key:ssnCondition.keySet()){
					condition.put(key, ssnCondition.get(key));
				}
			}
		}else{
			try {
				//页面查询，保存查询条件到session
				Object obj = req.getSession().getAttribute(QueryConditionUtil.QUERY_SQL_SESSION_KEY);
				if(null != obj  && obj instanceof String){
					if(!"-1".equals(obj.toString())){
						condition.put("_q_sql", (String)obj);
					}else{
						condition.put("_q_sql", null);
					}
					req.getSession().removeAttribute(QueryConditionUtil.QUERY_SQL_SESSION_KEY);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		req.getSession().setAttribute(conditionSessionKey, condition);
	}

    public static void resetCondition(BaseMultiActionController controller, HttpServletRequest req){
        String conditionSessionKey = getConditionSessionKey(controller);
        Map<String, String> condition = (Map<String, String>)req .getSession().getAttribute(conditionSessionKey);
        if(condition != null && condition.containsKey("_q_sql")) {
            condition.put("_q_sql", null);
        }
    }

	private static boolean isUnQuerys(Map<String, String> condition){
		if(StringUtil.isNotEmpty(condition.get("start")) || StringUtil.isNotEmpty(condition.get("limit"))){
			return false;
		}
		return true;
	}
	
	private static String getConditionSessionKey(BaseMultiActionController controller){
		return "query_key_" + controller.getSessionKey();
	}
}
