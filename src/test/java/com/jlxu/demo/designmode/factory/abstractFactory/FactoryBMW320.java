package com.jlxu.demo.designmode.factory.abstractFactory;

/**
 * 功能:为宝马320系列生产配件
 * 创建时间：2020年03月21日
 * 文件名称：AbstractFactory
 * 版本：1.0.0
 * 最后修改时间：2020/3/21 19:34
 *
 * @auther jlxu
 */
public class FactoryBMW320 implements AbstractFactory {
    public Engine createEngine() {
        return new EngineA();
    }

    public Aircondition createAircondition() {
        return new AirconditionA();
    }
}
