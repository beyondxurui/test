package com.jlxu.demo.designmode.factory.abstractFactory;

/**
 * 功能 汽车部件空调B
 * 创建时间：2020年03月21日
 * 文件名称：Engine
 * 版本：1.0.0
 * 最后修改时间：2020/3/21 19:27
 *
 * @auther ${许金李}
 */
public class AirconditionB implements Aircondition {
    public AirconditionB() {
        System.out.println("制造--> AirconditionB");
    }
}