package com.jlxu.demo.designmode.factory.abstractFactory;

/**
 * 功能 汽车部件发动机A
 * 创建时间：2020年03月21日
 * 文件名称：Engine
 * 版本：1.0.0
 * 最后修改时间：2020/3/21 19:27
 *
 * @auther ${许金李}
 */
public class EngineA implements Engine {
    public EngineA(){
        System.out.println("制造-->EngineA");
    }
}
