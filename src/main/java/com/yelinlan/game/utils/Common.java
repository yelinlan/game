package com.yelinlan.game.utils;

/**
 * @项目名称: DATA_STRUCTURE
 * @类名称: Common
 * @类描述: 常用方法（全部为静态方法）
 * @创建人: 权义翔
 * @创建时间: 2020/3/16 19:27
 **/
public class Common {
    public Common() {
    }

    /**
     * @return : java.lang.Integer
     * @方法名 : max
     * @创建人 : 权义翔
     * @创建日期 : 2020/3/16 19:34
     * @功能描述 : 最大值
     */
    public static Integer max(Integer a, Integer b) {
        return a > b ? a : b;
    }

    /**
     * @return : java.lang.Integer
     * @方法名 : min
     * @创建人 : 权义翔
     * @创建日期 : 2020/3/16 19:34
     * @功能描述 : 最小值
     */
    public static Integer min(Integer a, Integer b) {
        return a < b ? a : b;
    }

    /**
     * @return : java.lang.Double
     * @方法名 : max
     * @创建人 : 权义翔
     * @创建日期 : 2020/3/16 19:35
     * @功能描述 : max
     */
    public static Double max(Double a, Double b) {
        return a > b ? a : b;
    }

    /**
     * @return : java.lang.Double
     * @方法名 : min
     * @创建人 : 权义翔
     * @创建日期 : 2020/3/16 19:35
     * @功能描述 : min
     */
    public static Double min(Double a, Double b) {
        return a < b ? a : b;
    }

    /**
     * @return : boolean
     * @方法名 : validateParams
     * @创建人 : 权义翔
     * @创建日期 : 2020/3/20 23:00
     * @功能描述 : 验证参数是否为空
     */
    public static boolean validateParams(String... str) {
        for (String s : str) {
            if (StringUtils.isBlank(s)) {
                return false;
            }
        }
        return true;
    }
}