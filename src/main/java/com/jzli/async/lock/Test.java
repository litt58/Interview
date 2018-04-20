package com.jzli.async.lock;

import java.math.BigDecimal;

/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2018/4/19
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
public class Test {

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal(2L);
        BigDecimal b = new BigDecimal(3L);
        System.out.println(a.subtract(b));

        String line = "\uFEFF01|194|2|2.00|1|1.00";
        line = "02|0001|00003004420|测试客户216483|00002010925|测试客户149950||00|D||||325290000012|CNY|1.00|01|11111111111111111112|||000000|交易成功|1";
        if (line.startsWith("02")) {
            System.out.println("02");
        }


        BigDecimal decimal = new BigDecimal("1");
        System.out.println(decimal);
        BigDecimal setScale = decimal.setScale(2);
        System.out.println(setScale);

        BigDecimal setScale1 = decimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(setScale1);

        String str = "02|0001|00003004420|测试客户216483|00002010925|测试客户149950||00|D||||325290000012|CNY|1.00|01|11111111111111111112|||000000|交易成功|1";
        String[] split = str.split("\\|");
        System.out.println(split.length);
        for (String s : split) {
            System.out.println(s);
        }

    }
}
