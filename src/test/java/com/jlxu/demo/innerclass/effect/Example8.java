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
public class Example8 extends Example6 {
    private class Inner implements InterfaceTest {
        @Override
        public void test() {
            log.info("interfaceTest");
        }
    }

    public InterfaceTest getInterfaceTest() {
        return new Inner();
    }

}

