package com.jlxu.demo.innerclass.effect;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能 ：内部类可以很好的实现隐藏（一般的非内部类，是不允许有 private 与 protected 权限的，但内部类可以）；
 * 创建时间：2020年03月05日
 * 文件名称：Example
 * 版本：1.0.0
 * 最后修改时间：2020/3/5 23:26
 *
 * @auther jlxu
 */
@Slf4j
public class Example {
    //    public class Inner implements InterfaceTest {  TODO:可以Example.Inner访问的
    private class Inner implements InterfaceTest {
        @Override
        public void test() {
            log.info("i am jlxu");
        }
    }

    //    获取内部类（通过方法获取实例）
    public InterfaceTest getInnerInstance() {// TODO:注意写法
        return new Inner();//TODO:new 内部类返回的是接口对象
    }
}

