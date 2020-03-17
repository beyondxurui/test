package com.jlxu.demo.api.packagingtype;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能：包装类型 Api学习
 * 创建时间：2020年03月17日
 * 文件名称：PackageTypeTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/17 14:17
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class PackageTypeTest {
    @Test
    public void packageTypeTest() {
        //类型转化选中  参考地址  https://www.cnblogs.com/DreamDrive/p/4133556.html
        //1）Integer类型转String
        //valueOf 和+    产生一个对象还算是多个对象
        String s = String.valueOf(2);
        //2）String转Integer
        Integer.parseInt("23");  // 不占用内存
        Integer.valueOf("23");     //占内存
        //ps  具体见参考地址和源码

    }
}
