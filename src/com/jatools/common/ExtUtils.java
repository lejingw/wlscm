package com.jatools.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

public class ExtUtils {
    /**
     * 把dataset的每一行组成一个Map，然后多行组成一个Map
     *
     * @param ds key作为主键
     */
//    public static Map<String, Map<String, String>> dataset2mapmap(ARDataSet ds, String key) {
//        Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
//        ds.open();
//        while (!ds.eof()) {
//            Map<String, String> obj = new LinkedHashMap<String, String>();
//            for (int i = 1; i <= ds.getCols(); i++) {
//                String fieldName = ds.getFieldName(i);
//                String fieldValue = ds.valueStr(i);
//                //将.12转化为0.12
//                if (("en_fee".endsWith(fieldName) || "en_index_max".endsWith(fieldName) || "en_index_min".endsWith(fieldName) || "en_index_down".endsWith(fieldName)
//                        || "en_rank_fee".endsWith(fieldName) || "en_rank_fee1".endsWith(fieldName) || "en_rank_fee2".endsWith(fieldName) || "en_rank_fee3".endsWith(fieldName))
//                        && fieldValue.indexOf(".") != -1 && fieldValue != null && fieldValue.length() > 0) {
//                    fieldValue = new Float(fieldValue).toString();
//                }
//                obj.put(fieldName, fieldValue);
//            }
//            if (obj.get(key) == null)
//                return new HashMap<String, Map<String, String>>();
//            map.put(obj.get(key), obj);
//            if (ds.next() == ds.getRows()) {
//                break;
//            }
//        }
//        return map;
//    }

    /**
     * 把dataset的每一行组成一个Map，然后多行组成一个List
     *
     * @param ds
     */
//    public static List<Map<String, String>> dataset2maplist(ARDataSet ds) {
//        List<Map<String, String>> results = new ArrayList<Map<String, String>>();
//        ds.open();
//
//        while (!ds.eof()) {
//            Map<String, String> obj = new LinkedHashMap<String, String>();
//            for (int i = 1; i <= ds.getCols(); i++) {
//                String fieldName = ds.getFieldName(i);
//                String fieldValue = ds.valueStr(i);
//                //将.12转化为0.12
//                if (("en_fee".endsWith(fieldName) || "en_index_max".endsWith(fieldName) || "en_index_min".endsWith(fieldName) || "en_index_down".endsWith(fieldName)
//                        || "en_rank_fee".endsWith(fieldName) || "en_rank_fee1".endsWith(fieldName) || "en_rank_fee2".endsWith(fieldName) || "en_rank_fee3".endsWith(fieldName))
//                        && fieldValue.indexOf(".") != -1 && fieldValue != null && fieldValue.length() > 0) {
//                    fieldValue = new Float(fieldValue).toString();
//                }
//                obj.put(fieldName, fieldValue);
//            }
//            results.add(obj);
//            if (ds.next() == ds.getRows()) {
//                break;
//            }
//        }
//        return results;
//    }

//    @SuppressWarnings("unchecked")
//    public static List<Object> dataset2beanlist(ARDataSet ds) {
//        ds.open();
//        List<Object> results = new ArrayList<Object>();
//        while (!ds.eof()) {
//            //FormBean formBean = new FormBean();
//            //Class clazz = formBean.getClass();
//            for (int i = 1; i <= ds.getCols(); i++) {
//                String fieldName = ds.getFieldName(i);
//                String fieldValue = ds.valueStr(i);
//                //将.12转化为0.12
//                if (("en_fee".endsWith(fieldName) || "en_index_max".endsWith(fieldName) || "en_index_min".endsWith(fieldName) || "en_index_down".endsWith(fieldName)
//                        || "en_rank_fee".endsWith(fieldName) || "en_rank_fee1".endsWith(fieldName) || "en_rank_fee2".endsWith(fieldName) || "en_rank_fee3".endsWith(fieldName))
//                        && fieldValue.indexOf(".") != -1 && fieldValue != null && fieldValue.length() > 0) {
//                    fieldValue = new Float(fieldValue).toString();
//                }
//                String functionName = "set" + String.valueOf(fieldName.charAt(0)).toUpperCase() + fieldName.substring(1);
//                try {
//                    //Method method = clazz.getDeclaredMethod(functionName, String.class);
//                    //method.invoke(formBean, fieldValue);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } 
//            }
//            //results.add(formBean);
//            if (ds.next() == ds.getRows()) {
//                break;
//            }
//        }
//        return results;
//    }

    /**
     * 只返回指定的位置，指定条数的ARDateSet，然后dataset的每一行组成一个Map，然后多行组成一个List
     *
     * @param ds
     * @param start 定位串
     * @param limit 返回条数
     */
//    public static List<Map<String, String>> dataset2maplist(ARDataSet ds, int start, int limit) {
//        List<Map<String, String>> results = new ArrayList<Map<String, String>>();
//        ds.open();
//        ds.go(start + 1);
//        //while (!ds.eof()) {
//        for (int k = 0; k < limit && !ds.eof(); k++) {
//            Map<String, String> obj = new LinkedHashMap<String, String>();
//            for (int i = 1; i <= ds.getCols(); i++) {
//                String fieldName = ds.getFieldName(i);
//                String fieldValue = ds.valueStr(i);
//                //将.12转化为0.12
//                if (("en_fee".endsWith(fieldName) || "en_index_max".endsWith(fieldName) || "en_index_min".endsWith(fieldName) || "en_index_down".endsWith(fieldName)
//                        || "en_rank_fee".endsWith(fieldName) || "en_rank_fee1".endsWith(fieldName) || "en_rank_fee2".endsWith(fieldName) || "en_rank_fee3".endsWith(fieldName))
//                        && fieldValue.indexOf(".") != -1 && fieldValue != null && fieldValue.length() > 0) {
//                    fieldValue = new Float(fieldValue).toString();
//                }
//                obj.put(fieldName, fieldValue);
//            }
//            results.add(obj);
//            if (ds.next() == ds.getRows()) {
//                break;
//            }
//        }
//        return results;
//    }

    /*
      * 只把dataset的每一行中指定的字段打入Map中，然后多行组成一个List，常用语创建Commbo下拉框
      * 作者：renyl
      * 日期：2009-09-01
      * @param ds
      * @param fields 需要打入Map的字段
      * @param includekeys 需要被包含
      * @param noincludekeys 不需要被包含
      *
      * */
//    public static List<Map<String, String>> dataset2maplist(ARDataSet ds, List<String> fields, String includekeys, String noincludekeys, String includefieldvalue) {
//        List<Map<String, String>> results = new ArrayList<Map<String, String>>();
//        Set<String> includekeysset = null;
//        Set<String> noincludekeysset = new HashSet<String>();
//        if (includekeys != null && !"".equals(includekeys.trim())) {
//            includekeysset = new HashSet<String>();
//            String[] includekeysarr = includekeys.split(",");
//            for (String key : includekeysarr) {
//                includekeysset.add(key);
//            }
//        }
//        if (noincludekeys != null && !"".equals(noincludekeys.trim())) {
//            String[] noincludekeysarr = noincludekeys.split(",");
//            for (String key : noincludekeysarr) {
//                noincludekeysset.add(key);
//            }
//        }
//        ds.open();
//        while (!ds.eof()) {
//            Map<String, String> obj = new LinkedHashMap<String, String>();
//            for (int i = 1; i <= ds.getCols(); i++) {
//                String fieldName = ds.getFieldName(i);
//                if (fields.contains(fieldName)) {
//                    String fieldValue = ds.valueStr(i);
//                    obj.put(fieldName, fieldValue);
//                }
//            }
//            if (includefieldvalue == null || "".equals(includefieldvalue.trim()) || obj.get(includefieldvalue.trim()) == null)
//                results.add(obj);
//            else if (obj.get(includefieldvalue.trim()) != null && !noincludekeysset.contains(obj.get(includefieldvalue.trim())) && (includekeysset == null || includekeysset.contains(obj.get(includefieldvalue.trim()))))
//                results.add(obj);
//
//            if (ds.next() == ds.getRows()) {
//                break;
//            }
//        }
//        return results;
//    }

    /**
     * 只把dataset的每一行中指定的字段打入Map中，然后多行组成一个List，常用语创建Commbo下拉框
     *
     * @param ds
     * @param fields 需要打入Map的字段
     */
//    public static List<Map<String, String>> dataset2maplist(ARDataSet ds, List<String> fields) {
//        return dataset2maplist(ds, fields, "", "", "");
//    }

    /**
     * 只取ARDataSet的第一行，然后生成一个Map
     * 对这类的ARDataSet，通常是操作类的（非多记录的查询），也只有一条记录返回
     *
     * @param ds
     * @return
     */
//    public static Map<String, String> dataset2map(ARDataSet ds) {
//        ds.open();
//        Map<String, String> map = new LinkedHashMap<String, String>();
//        if (ds.getRecords().length == 0) {
//            return map;
//        }
//        for (int i = 1; i <= ds.getCols(); i++) {
//            String fieldName = ds.getFieldName(i);
//            String fieldValue = ds.valueStr(i);
//            map.put(fieldName, fieldValue);
//        }
//        return map;
//    }

    /**
     * ARDataSet to string
     *
     * @param ds
     * @return
     */
//    public static StringBuffer dataset2string(ARDataSet ds) {
//        StringBuffer str = new StringBuffer();
//        if (ds == null) {
//            return str;
//        }
//        ds.open();
//        while (!ds.eof()) {
//            for (int i = 1; i <= ds.getCols(); i++) {
//                str.append(ds.getFieldName(i));
//                str.append("=");
//                str.append(ds.valueStr(i));
//                str.append(",");
//            }
//            if (ds.next() == ds.getRows()) {
//                break;
//            }
//        }
//        return str;
//    }

    /**
     * 获取当前操作系统名称.
     * return 操作系统名称 例如:windows xp,linux 等.
     */
    public static String getOSName() {
        return System.getProperty("os.name").toLowerCase();
    }

    /**
     * 获取unix网卡的mac地址.
     * 非windows的系统默认调用本方法获取.如果有特殊系统请继续扩充新的取mac地址方法.
     * @return mac地址
     */
    public static String getLinuxMACAddress() {
        String mac = null;
        BufferedReader bufferedReader = null;   
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("ifconfig eth0");// linux下的命令，一般取eth0作为本地主网卡 显示信息中包含有mac地址信息
            bufferedReader = new BufferedReader(new InputStreamReader(process
                    .getInputStream()));
            String line = null;
            int index = -1;
            while ((line = bufferedReader.readLine()) != null) {
                index = line.toLowerCase().indexOf("hwaddr");// 寻找标示字符串[hwaddr]
                if (index >= 0) {// 找到了
                    mac = line.substring(index +"hwaddr".length()+ 1).trim();//  取出mac地址并去除2边空格
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            bufferedReader = null;
            process = null;
        }

        return mac;
    }

    /**
     * 获取widnows网卡的mac地址.
     * @return mac地址
     */
    public static String getWindowsMACAddress() {
        String mac = null;
        BufferedReader bufferedReader = null;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("ipconfig /all");// windows下的命令，显示信息中包含有mac地址信息
            bufferedReader = new BufferedReader(new InputStreamReader(process
                    .getInputStream()));
            String line = null;
            int index = -1;
            while ((line = bufferedReader.readLine()) != null) {
            	String title = line.toLowerCase();
            	if(!"".equals(title)) {
	                index = title.indexOf("physical address");// 寻找标示字符串[physical address]
	                if(!(index >= 0)) {
	                	index = title.indexOf("物理地址");
	                }
	                if (index >= 0) {// 找到了
	                    index = line.indexOf(":");// 寻找":"的位置
	                    if (index>=0) {
	                        mac = line.substring(index + 1).trim();//  取出mac地址并去除2边空格
	                    }
	                    break;
	                }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            bufferedReader = null;
            process = null;
        }

        return mac;
    }


    /**
     * @param remotePcIP
     * @return
     * @deprecated 取物理地址
     */
    public static String getMacAddress(String remotePcIP) {
        String str = "";
        String macAddress = "";
        try {
            Process pp = Runtime.getRuntime().exec("nbtstat   -A   " + remotePcIP);
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            while ((str = input.readLine()) != null) {
                if (str.indexOf("MAC Address") > 1) {
                    macAddress = str.substring(
                            str.indexOf("MAC Address") + 14, str.length());
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return macAddress;
    }

    /**
     * 从Map中记录请求参数日志信息
     *
     * @param logger
     * @param map
     */
    public static void makeLog(Logger logger, Map<String, String> map, String type) {
        if (map == null) {
            return;
        }
        StringBuffer str = new StringBuffer();
        Set<String> set = map.keySet();
        for (String key : set) {
            String value = map.get(key);
            str.append(key).append("=").append(value).append(", ");
        }
        if ("in".equalsIgnoreCase(type)) {
            String functionId = map.get("function_id");
            logger.debug((functionId != null ? "功能" + functionId : "") + "输入参数: " + str);
        } else {
            logger.debug("输出: " + str);
        }
    }

    public static StringBuffer listmap2string(Map<String, String> map) {
        StringBuffer str = new StringBuffer();
        if (map == null) {
            return str;
        }
        Set<String> set = map.keySet();
        for (String key : set) {
            String value = map.get(key);
            str.append(key).append("=").append(value).append(", ");
        }
        return str;
    }

    /**
     * 将List <Map<String, String>> records 转化成Json字符串，
     * 通常是先将dataset转化成List <Map<String, String>> list然后再转化成Json字符串
     *
     * @param headName
     * @param records
     * @return
     */
    public static String maplist2JsonString(String headName, List<Map<String, String>> records) {
        StringBuffer json = new StringBuffer();
        json.append(headName).append(": [ ");
        for (Map<String, String> data : records) {
            if (data.keySet().size() > 0) {
                json.append("{ ");
                for (String key : data.keySet()) {
                    String value = data.get(key);
                    json.append(key).append(": '").append(value).append("',");
                }
                json.deleteCharAt(json.length() - 1);
                json.append("},");
            }
        }
        json.deleteCharAt(json.length() - 1);
        json.append("]");
        return json.toString();
    }
}
