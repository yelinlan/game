package com.yelinlan.game.utils;

/**
 * @项目名称: DATA_STRUCTURE
 * @类名称: StringUtils
 * @类描述: 字符串工具包
 * @创建人: 权义翔
 * @创建时间: 2020/3/6 19:40
 **/
public class StringUtils {
    /**
     * @return : java.lang.Boolean
     * @方法名 : isBlank
     * @创建人 : 权义翔
     * @创建日期 : 2020/3/6 19:46
     * @功能描述 : 字符串非空
     */
    public static Boolean isBlank(Object o) {
        if (null == o) {
            return true;
        }
        String str = o.toString();
        str = str.replaceAll("\\s", "");
        if ("".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * @return : Integer
     * @方法名 : toInteger
     * @创建人 : 权义翔
     * @创建日期 : 2020/3/6 19:52
     * @功能描述 : 字符串转整型
     */
    public static Integer toInteger(String num) {
        return Integer.parseInt(num);
    }

//   public static String convertToSup(String s){
//       String temp = "";
//   }

}