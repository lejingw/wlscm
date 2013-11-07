package com.jatools.web.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * spring容器初始化完毕刷新事件侦听器
 */
public class ContextRefreshedEventListener implements ApplicationListener {

//    ProductUtilDwr productUtilDwr ;
//    
//    CustomerFacade customerFacade;
//    
//    CommonUtilDwr  commonUtilDwr;
	
//	private DictDwr dictDwr;
	
    
//    public void setDictDwr(DictDwr dictDwr) {
//		this.dictDwr = dictDwr;
//	}


	/**
     * 侦听spring容器发布初始化完毕事件，更新容器事件
     */
    public void onApplicationEvent(ApplicationEvent contextEvent) {
        
        if (contextEvent instanceof ContextRefreshedEvent) {
//        	dictDwr.init();
//            productUtilDwr.initProtocolsAndImagesOfProducts();
            //commonUtilDwr.initCitiesMap();
            //customerFacade.initQuestionsMapCache();
        }
        
    }

//    public ProductUtilDwr getProductUtilDwr() {
//        return productUtilDwr;
//    }
//
//    public void setProductUtilDwr(ProductUtilDwr productUtilDwr) {
//        this.productUtilDwr = productUtilDwr;
//    }
//
//    public CustomerFacade getCustomerFacade() {
//        return customerFacade;
//    }
//
//    public void setCustomerFacade(CustomerFacade customerFacade) {
//        this.customerFacade = customerFacade;
//    }
//
//    public CommonUtilDwr getCommonUtilDwr() {
//        return commonUtilDwr;
//    }
//
//    public void setCommonUtilDwr(CommonUtilDwr commonUtilDwr) {
//        this.commonUtilDwr = commonUtilDwr;
//    }
}
