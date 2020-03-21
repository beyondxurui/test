package com.jlxu.demo.designmode.factory.abstractFactory;

/**
 * 功能:宝马523系列
 * 创建时间：2020年03月21日
 * 文件名称：AbstractFactory
 * 版本：1.0.0
 * 最后修改时间：2020/3/21 19:34
 *
 * @auther jlxu
 */
public class FactoryBMW523 implements AbstractFactory {
    public Engine createEngine() {
        return new EngineB();
    }

    public Aircondition createAircondition() {
        return new AirconditionB();
    }
}
