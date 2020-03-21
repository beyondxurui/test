package com.jlxu.demo.designmode.strategy;

/**
 * 功能：
 * 创建时间：2020年03月21日
 * 文件名称：Strategy
 * 版本：1.0.0
 * 最后修改时间：2020/3/21 21:49
 *
 * @auther jlxu
 */
//孙夫人断后，挡住追兵
public class BlackEnemy implements Strategy {
    public void operate() {
        System.out.println("孙夫人断后，挡住追兵");
    }
}
