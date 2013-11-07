
package com.jatools.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.ibatis.sqlmap.client.SqlMapSession;
import com.jatools.common.Pager;
import com.jatools.dao.util.TransactionAction;
/**
 *
 */
public class BaseDao extends SqlMapClientDaoSupport {

    /**
     * 单条添加
     * 
     * @param statementName
     * @param parameterObject
     * 
     * @return 主键，一般是自动生成的sequence id。
     */
    protected Object executeInsert(String statementName, Object parameterObject) {
        return getSqlMapClientTemplate().insert(statementName, parameterObject);
    }

    /**
     * 实际执行批量操作的方法,代理执行插入、更新、删除等操作
     * @param statementName
     * @param parameterList
     * @return 批量执行数
     */
    @SuppressWarnings("rawtypes")
	private int executeBatchOperation(final String statementName, Object parameterList,
                                      final String flag) {
        Long exectuteSucValue = new Long(0);
        final List list = (List) parameterList;
        //如果长度为0.直接返回之
        if (list.size() == 0) {
            return 0;
        }
        exectuteSucValue = (Long) getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
            public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                Long states = new Long(0);
                Logger log = Logger.getLogger(BaseDao.class);
                try {
                    executor.startBatch();
                    for (int i = 0; i < list.size(); i++) {
                        if (flag.equals("update")) {
                            executor.update(statementName, list.get(i));
                        } else if (flag.equals("insert")) {
                            executor.insert(statementName, list.get(i));
                        } else if (flag.equals("delete")) {
                            executor.delete(statementName, list.get(i));
                        }
                    }

                    executor.executeBatch();
                } catch (Exception e) {
                    states = new Long(-1);
                    log.error(e.getMessage(), e);
                }
                return states;

            }
        });
        if (exectuteSucValue.intValue() == -1) {
            throw new RuntimeException();
        }
        return list.size();
    }

    /**
     * 批量更新
     * @param statementName
     * @param parameterList
     * @return 批量执行数
     */
    protected int executeBatchUpdate(final String statementName, Object parameterList) {
        return this.executeBatchOperation(statementName, parameterList, "update");
    }

    /**
     * 批量添加
     * @param statementName
     * @param parameterList
     * @return 批量执行数
     */
    protected int executeBatchInsert(final String statementName, Object parameterList) {
        return this.executeBatchOperation(statementName, parameterList, "insert");
    }

    /**
     * 批量删除
     * @param statementName
     * @param parameterList
     * @return 批量执行数
     */
    protected int executeBatchDelete(final String statementName, Object parameterList) {
        return this.executeBatchOperation(statementName, parameterList, "delete");
    }

    /**
     * 查询
     * 
     * @param statementName
     * @param parameterObject
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
	protected List executeQueryForList(String statementName, Object parameterObject) {
        return getSqlMapClientTemplate().queryForList(statementName, parameterObject);
    }

    /**
     * 根据主键load一条记录
     * 
     * @param statementName
     * @param parameterObject
     * 
     * @return
     */
    protected Object executeQueryForObject(String statementName, Object parameterObject) {
        return getSqlMapClientTemplate().queryForObject(statementName, parameterObject);
    }

    /**
     * update
     * 
     * @param statementName
     * @param parameterObject
     * 
     * @return
     */
    protected int executeUpdate(String statementName, Object parameterObject) {
        return getSqlMapClientTemplate().update(statementName, parameterObject);
    }

    /**
     * delete
     * 
     * @param statementName
     * @param parameterObject
     * 
     * @return
     */
    protected int delete(String statementName, Object parameterObject) {
        return getSqlMapClientTemplate().delete(statementName, parameterObject);
    }

    @SuppressWarnings("unchecked")
	protected Pager executeQueryForPager(String selectStmtName, String countStmtName, Map<String, String> condition){
    	if(null == condition.get("start") || null == condition.get("limit")){
    		throw new RuntimeException("获取分页数据时,不能获取start、limit参数");
    	}
    	Integer totalCount = (Integer) getSqlMapClientTemplate().queryForObject(countStmtName, condition);
    	int start = Integer.valueOf(condition.get("start"));
    	int limit = Integer.valueOf(condition.get("limit"));
    	
    	if(start > totalCount.intValue()) {
    		start = totalCount.intValue()/limit * limit + 1;
    	}
    	condition.put("start", start+"");
    	List<Object> list = getSqlMapClientTemplate().queryForList(selectStmtName, condition);
    	Pager pager = new Pager();
    	pager.setPageData(list);
    	pager.setTotalCount(totalCount);
    	pager.setStart(start);
    	pager.setLimit(limit);
    	return pager;
    }
    /**
     * 独立事务处理
     * @param trans
     * @return
     */
	protected Object executeOutTransaction(TransactionAction trans) {
		Connection conn = null;
		SqlMapSession session = null;
		try {
			SqlMapClient sqlMap = getSqlMapClient();
			javax.sql.DataSource ds = sqlMap.getDataSource();
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			session = sqlMap.openSession(conn);
			Object obj = trans.executeAction(session);
			conn.commit();
			return obj;
		} catch (Exception e) {
			try {
				if (null != conn) {
					conn.rollback();
				}
			} catch (SQLException e1) {
				throw new RuntimeException("执行事务方法失败");
			}
		} finally {
			try {
				if (null != session) {
					session.close();
				}
				if (null != conn) {
					conn.close();
				}
			} catch (SQLException e1) {
				throw new RuntimeException("关闭数据库连接失败");
			}
		}
		return null;
	}
}