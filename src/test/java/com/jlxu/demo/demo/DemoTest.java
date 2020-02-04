package com.jlxu.demo.demo;

import com.jlxu.demo.model.TRegion;
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


    //2020-2-4
    //字节码是怎么样的？
    @Test
    public void test3() {
        TRegion tRegion = new TRegion();
        Class<?> aClass1 = null;
        try {
            aClass1 = Class.forName("com.jlxu.demo.model.TRegion");
        } catch (ClassNotFoundException e) {
            log.error("区域类的全路径不存在");
            e.printStackTrace();
        }
        Class<?> aClass = tRegion.getClass();
        Class<TRegion> tRegionClass = TRegion.class;
        //打印字节码
        log.debug("第一种====>Class类的forName方法获取区域类的字节码：{}", aClass1);//class com.jlxu.demo.model.TRegion
        log.debug("第二种====>对象的getClass方法获取区域类的字节码：{}", aClass);//class com.jlxu.demo.model.TRegion
        log.debug("第三种====>类的class属性获取区域类的字节码：{}", tRegionClass);//class com.jlxu.demo.model.TRegion
        //PS:通过和反射有关    如Spring框架中bean的加载    泛型中的？：通配符
    }

    //java特殊符号组合的含义
    //    \t  \n    \r   \\s
    @Test
    public void test4() {
        log.debug("第一种：{}", "ddddd\tddd");//ddddd	ddd  相当于tab（缩进）
//        log.debug("第二种：{}", "ddddd\nddd");
        //第二种：ddddd
        //ddd
        log.debug("第三种：{}", "ddddd\rddd");//ddd
        log.debug("第三种（演变）：{}", "dddd" + "\r" + "ddd");//ddd
        log.debug("第四种：{}", "ddddd\\sddd");//ddddd\sddd
        //PS：自己理解的换行和回车和实际不一样    实际：回车变成两行了（见第二种）；换行变成只显示第二行
    }

}


