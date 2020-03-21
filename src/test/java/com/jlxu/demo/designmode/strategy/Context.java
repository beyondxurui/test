package com.jlxu.demo.designmode.strategy;

/**
 * 功能
 * 创建时间：2020年03月21日
 * 文件名称：Context
 * 版本：1.0.0
 * 最后修改时间：2020/3/21 21:55
 *
 * @auther ${许金李}
 */
//环境类
public class Context {
    private final Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void operate() {
        strategy.operate();
    }
}
