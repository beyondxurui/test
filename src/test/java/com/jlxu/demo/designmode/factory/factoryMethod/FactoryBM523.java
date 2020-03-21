package com.jlxu.demo.designmode.factory.factoryMethod;

/**
 * 功能
 * 创建时间：2020年03月21日
 * 文件名称：FactoryBM
 * 版本：1.0.0
 * 最后修改时间：2020/3/21 17:47
 *
 * @auther jlxu
 */
public class FactoryBM523 implements FactoryBM {
    public BM createBM() {
        return new BM523();
    }
}
