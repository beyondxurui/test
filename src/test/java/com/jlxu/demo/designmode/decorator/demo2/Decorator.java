package com.jlxu.demo.designmode.decorator.demo2;

/**
 * 功能 : 装饰者
 * 创建时间：2020年03月01日
 * 文件名称：Condiment
 * 版本：1.0.0
 * 最后修改时间：2020/3/1 18:02
 *
 * @auther jlxu
 */
public abstract class Decorator implements Human {
    private Human human;

    public Decorator(Human human) {
        this.human = human;
    }

    public void wearClothes() {
        human.wearClothes();//这是装饰的关键代码，下面同理
    }

    public void walkToWhere() {
        human.walkToWhere();
    }

}
