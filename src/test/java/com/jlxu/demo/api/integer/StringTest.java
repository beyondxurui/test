package com.jlxu.demo.api.integer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能：String类方法测试
 * 创建时间：2020年04月01日
 * 文件名称：StringTest
 * 版本：1.0.0
 * 最后修改时间：2020/4/1 16:09
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class StringTest {
    @Test
    public void stringTest() {
        //String的compare
        //比较大小  含义见Integer测试类中    为什么呢？TODO:实现了Comparable<T>接口
        String str = "123";
        String str2 = "123";
        int i = str.compareTo(str2);
        System.out.println(i);
    }
}
