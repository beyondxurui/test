package com.jlxu.demo.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 功能：测试demo
 * 版本：1.0.0
 * 最后修改时间：2020/1/6 14:50
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class DemoTest {
    //特殊字符转义
    @Test
    public void test() {
        try {
            String decode = URLDecoder.decode("16wdEC%23%25", "utf-8");
            log.debug(decode);
        } catch (UnsupportedEncodingException e) {
            log.error("===> ");
            e.printStackTrace();
        }
    }

    //String的split方法
    @Test
    public void test2() {
        String ss = "dkdfl909rtt";
        String[] split = ss.split("9");
        String s = split[0];
        String s2 = split[1];
        String s3 = split[2];
        log.debug("Spring的split方法的获取数组中的第一个元素：{}", s);    //dkdfl
        log.debug("Spring的split方法的获取数组中的第二个元素: {}", s2);   //0
        log.debug("Spring的split方法的获取数组中的第三个元素: {}", s3);   //rtt
    }

    //异常记录：警告: Runner org.junit.internal.runners.ErrorReportingRunner (used on class com.jlxu.demo.demo.DemoTest) does not support filtering and will therefore be run completely.
    //导包错误：把org.junit.jupiter.api.Test换成org.junit.Test即可



}
