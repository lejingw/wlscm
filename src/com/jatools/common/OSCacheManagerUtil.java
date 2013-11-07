package com.jatools.common;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

/**
 */
public class OSCacheManagerUtil {
    
    private final  Logger        logger                 = Logger
    .getLogger(OSCacheManagerUtil.class);
    private  GeneralCacheAdministrator cacheAdministrator;
    
    private  List<Map<String, String>> branchList;

    public GeneralCacheAdministrator getCacheAdministrator() {
        return cacheAdministrator;
    }

    public void setCacheAdministrator(GeneralCacheAdministrator cacheAdministrator) {
        this.cacheAdministrator = cacheAdministrator;
    }

    public List<Map<String, String>> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<Map<String, String>> branchList) {
        this.branchList = branchList;
    }

    @SuppressWarnings("unchecked")
    public  List<Map<String, String>> getBranchListFromCache(){
        try {
            branchList = (List<Map<String, String>>) cacheAdministrator.getFromCache("branchMapList");
        } catch (NeedsRefreshException e) {
            branchList = (List<Map<String, String>>) e.getCacheContent();
            if(branchList == null){//首次查询为null,抛出NeedsRefreshException
                logger.debug("开始初始化缓存中的营业部列表");
                //调用服务初始化branchList
//                branchList=customerFacade.queryBranchList();
                logger.debug("营业部列表初始化完毕，列表为："+branchList);
            }
            e.printStackTrace();
        }
        
        //刷新缓存
        cacheAdministrator.putInCache("branchMapList", branchList);
        
        return branchList;
    }


}
