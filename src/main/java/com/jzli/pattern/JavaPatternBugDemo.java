package com.jzli.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2018/8/16
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：正则表达式有时候会导致程序卡死，尽量减少使用
 * ========================================================
 */
public class JavaPatternBugDemo {
    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        System.out.println("begin!");
        //Pattern pattern = Pattern.compile("^([+-]?((0[xX](\\p{XDigit}+))|(((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)([eE][+-]?(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)))|[n|N]?'([^']*(?:'')*[^']*)*')");
        //可以简化成下面这样的，注意  \p、XDigit、Digit之类的是java regex才有的语法
        Pattern pattern = Pattern.compile("^('([^']*(?:'')*[^']*)*')");
        Matcher matcher = pattern.matcher("'%)) order by ANGEBOT.ID");
        System.out.println(matcher.find());
        long end = System.currentTimeMillis();
        System.out.println((end - begin) / 1000 + "s used!");
        System.out.println("finished!");
    }
}
