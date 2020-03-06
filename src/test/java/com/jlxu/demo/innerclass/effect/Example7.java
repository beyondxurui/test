package com.jlxu.demo.innerclass.effect;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能 ：
 * 创建时间：2020年03月05日
 * 文件名称：Example
 * 版本：1.0.0
 * 最后修改时间：2020/3/5 23:26
 *
 * @auther jlxu
 */
@Slf4j
public class Example7 extends Example6 implements InterfaceTest {
    public void test() {//有两个 怎么知道是哪一个呢？难不成还要走debug。肯定用内部类方式啊
        log.info("Test3");
    }
}

