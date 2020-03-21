package com.jlxu.demo.designmode.adapter.demo2;

/**
 * 功能
 * 创建时间：2020年03月21日
 * 文件名称：ConcreteTarget2
 * 版本：1.0.0
 * 最后修改时间：2020/3/21 16:00
 *
 * @auther jlxu
 */
// 具体目标类，只提供普通功能
public class ConcreteTarget2 implements Target2 {
    @Override
    public void request() {
        System.out.println("普通类， 具有普通功能，，，");
    }
}
