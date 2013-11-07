package com.jatools.web.cas;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.jatools.common.CommonUtil;

public class WlscmCasFilter implements Filter{
	private static Logger logger = Logger.getLogger(WlscmCasFilter.class);
	private final String casAuthenticationFilterName = "CasAuthenticationFilter";
	private Filter filter = null;
	private String[] ignorePath = null;
	private String[] ignorePath2 = new String[]{"tablecloth.css", "tablecloth.js", "printLabelJson.js"};

	public void destroy() {
		if(null != filter)
			filter.destroy();
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		if(!checkIgnore(arg0)){
			filter.doFilter(arg0, arg1, arg2);
		}else{
			arg2.doFilter(arg0, arg1);
		}
	}
	
	private boolean checkIgnore(ServletRequest arg0){
		HttpServletRequest request =(HttpServletRequest) arg0;
		String printLableFlag = CommonUtil.getParameterEmpty(request, "_printflag");
		if("1".equals(printLableFlag)){
			return true;
		}
		String url = request.getRequestURL().toString();
		//标签模板文件
		if(url.endsWith(".btw")){
			return true;
		}
		for(int i=0;i<ignorePath.length;i++){
			String tmp=ignorePath[i].replace("*","[\\W\\w]+" );
			if(url.matches("[\\W\\w]+"+tmp+"[?]?[\\W\\w]*")){
				return true;
			}
		}
		for(int i=0;i<ignorePath2.length;i++){
			String tmp=ignorePath2[i].replace("*","[\\W\\w]+" );
			if(url.matches("[\\W\\w]+"+tmp+"[?]?[\\W\\w]*")){
				return true;
			}
		}
		return false;
	}

	public void init(FilterConfig config) throws ServletException {
		try {
			String  tmp = config.getInitParameter("ignore.Path");
			ignorePath=tmp.equals("")?null:tmp.split(",");

			String className = config.getInitParameter(casAuthenticationFilterName);
			filter = (Filter) Class.forName(className).newInstance();
			filter.init(config);
		} catch (Exception e) {
			logger.error("init filter[" + casAuthenticationFilterName + "] failed...");
		}
	}
}