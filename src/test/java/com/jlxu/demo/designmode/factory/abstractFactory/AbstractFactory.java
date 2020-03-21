package com.jlxu.demo.designmode.factory.abstractFactory;

/**
 * 功能:工厂接口
 * 创建时间：2020年03月21日
 * 文件名称：AbstractFactory
 * 版本：1.0.0
 * 最后修改时间：2020/3/21 19:34
 *
 * @auther jlxu
 */
public interface AbstractFactory {
    //创建发动机
    Engine createEngine();

    //创建空调
    Aircondition createAircondition();
}
