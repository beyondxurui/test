package com.jlxu.demo.api.integer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能：Integer类方法测试
 * 创建时间：2020年04月01日
 * 文件名称：StringTest
 * 版本：1.0.0
 * 最后修改时间：2020/4/1 16:09
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class IntegerTest {
    @Test
    public void stringTest() {

        //Integer的compare 和compareTo方法
        //含义：比较大小   前面大于则1  等于则0  小于则-1
        //异同   异：一个是类方法，一个是实例方法     同：效果是一样的都是比较大小可见源码
        Integer int1 = 12;
        int int2 = 2;
        Integer int3 = 23;
        int compare = Integer.compare(int2, int3);
        System.out.println("compare is : " + compare);
        int i = int1.compareTo(int2);
        System.out.println("i is : " + i);
    }
}
