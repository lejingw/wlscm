package com.jatools.web.listener;

import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.jatools.common.Global;
import com.jatools.web.util.StringUtil;
/**
 * servletcontext 监听器
 * 
 */
public class WlscmServletContextListener implements ServletContextListener {

	private static final Logger log = Logger.getLogger(WlscmServletContextListener.class);

    public void contextDestroyed(ServletContextEvent event) {
    }
    
    /**
     * servletContext 初始化时候做同步配置文件工作
     */
    public void contextInitialized(ServletContextEvent event) {
    	Properties global = new Properties();
    	try {
    		InputStream is = getClass().getResourceAsStream("/global.properties");
    		global.load(is);
        } catch (Exception e) {
            log.debug("加载属性文件出错:/global.properties", e);
        }
    	if(StringUtil.isNotBlank(global.getProperty("system.contextpath"))){
    		Global.CONTEXT = global.getProperty("system.contextpath", "wlscm").trim();
    	}
    	if(StringUtil.isNotBlank(global.getProperty("sso.enable"))){
    		Global.ENABLE_SSO_FLAG = Boolean.valueOf(global.getProperty("sso.enable", "false").trim());
    	}
        if(StringUtil.isNotBlank(global.getProperty("remote.review.enable"))){
            Global.ENABLE_REMOTE_REVIEW_FLAG = Boolean.valueOf(global.getProperty("remote.review.enable", "false").trim());
        }
        if(StringUtil.isNotBlank(global.getProperty("local.address"))){
            String ipPort = global.getProperty("local.address", "127.0.0.1:80").trim();
            String[] tmp = ipPort.split("[:]");
            Global.LOCAL_ADDR_IP = tmp[0];
            if(tmp.length>1){
            	Global.LOCAL_ADDR_PORT = tmp[1];
            }else{
            	Global.LOCAL_ADDR_PORT = "80";
            }
        }
        Global.springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
    }
}
