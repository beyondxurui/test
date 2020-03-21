package com.jlxu.demo.designmode.factory.simple;

/**
 * 功能 简单工厂模式
 * 创建时间：2020年03月21日
 * 文件名称：FactoryBM
 * 版本：1.0.0
 * 最后修改时间：2020/3/21 17:47
 *
 * @auther jlxu
 */
public class SimpleFactory {
    public BM createBM(String type) {
        switch (type) {
            case "bm320":
                return new BM320();
            case "bm523":
                return new BM523();
            default:
                break;
        }
        return null;
    }
}
