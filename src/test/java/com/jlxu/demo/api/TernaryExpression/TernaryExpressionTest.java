package com.jlxu.demo.api.TernaryExpression;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能：三元表达式使用
 * 创建时间：2020年03月22日
 * 文件名称：ThreadPoolTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/22 18:09
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class TernaryExpressionTest {
    //三元表达式
    @Test
    public void threadPoolTest() {
        int a = 1;
        int b = 2;
        Boolean flag = a > b ? true : false; //  判断形
        boolean flag1 = true;
        Boolean fala2 = flag1 ? true : false;//  结果形
    }
}
