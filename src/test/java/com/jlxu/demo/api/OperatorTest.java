package com.jlxu.demo.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能：java运算符
 * 创建时间：2020年03月25日
 * 文件名称：OperatorTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/25 23:51
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class OperatorTest {
    @Test
    public void test() {
        int i = -1 << 29;//-536870912  2的29次方 加上 负号
        int i1 = 2 << 29;//1073741824  2的29次方 乘以 2
        int i2 = 3 << 29;//1610612736  2的29次方 乘以 3
        System.out.println(i);
        System.out.println(i1);
        System.out.println(i2);
    }
}
