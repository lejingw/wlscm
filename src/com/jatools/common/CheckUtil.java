package com.jatools.common;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.ObjectUtils;
import org.apache.log4j.Logger;

import com.jatools.web.util.StringUtil;

/**
 * session 操作util类
 */
public class CheckUtil {

    private static Logger logger = Logger.getLogger(CheckUtil.class.getName());
    
    /**
     * 生成的验证码
     */
    public static final String SESSION_CHECK_CODE = "check_code";
    /**
     * 用户输入的验证码
     */
    public static final String USER_CHECK_CODE    = "user_code";
	//---------------------------------------------------------------------------------    
    
    private static int checkCodeCunt = 4;
    
    private static int passwordNum = 6;
    
//    private static char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',   
//                                    'K', 'L', 'M', 'N',  'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',   
//                                    'X', 'Y', 'Z',  '1', '2', '3', '4', '5', '6', '7', '8', '9' };  
    
    private static char[] codeSequence = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };  
    
    /**
     *  生成随机数  
     */
    private static Random random = new Random();  
    
    /**
     * 产生验证码
     * @return
     */
    public static String createCheckCode() {     
           
        // randomCode记录随机产生的验证码   
        StringBuffer randomCheckCode = new StringBuffer();   
        // 随机产生codeCount个字符的验证码。   
        for (int i = 0; i < checkCodeCunt; i++) {   
            String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);   
            // 将产生的四个随机数组合在一起。   
            randomCheckCode.append(strRand);   
        }   
        // 将四位数字的验证码保存到Session中。   
        return randomCheckCode.toString();        
    }   
    
    /**
     * 产生服务密码
     * @return
     */
    public static String createPassWord() {     
        // randomCode记录随机产生的验证码   
        StringBuffer randomPassWord = new StringBuffer();   
        // 随机产生codeCount个字符的验证码。   
        for (int i = 0; i < passwordNum; i++) {   
            String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);   
            // 将产生的四个随机数组合在一起。   
            randomPassWord.append(strRand);   
        }   
        return randomPassWord.toString();        
    }   

    /**
     * 检测用户输入的校验码
     * @param session
     * @return
     */
    public static final boolean validateCheckCode(HttpSession session) {
        if(null == session){
            return false;
        }
        String userCheckCode = ObjectUtils.toString(session.getAttribute(USER_CHECK_CODE));
        String sessionCheckCode = ObjectUtils.toString(session.getAttribute(SESSION_CHECK_CODE));
        logger.debug("userCheckCode: " + userCheckCode +"-----sessionCheckCode" + sessionCheckCode);
        return StringUtil.isNotEmpty(userCheckCode) && StringUtil.isNotEmpty(sessionCheckCode)
        			&& StringUtil.equalsIgnoreCase(userCheckCode, sessionCheckCode);

    }
}
