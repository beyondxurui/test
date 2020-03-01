package com.jlxu.demo.designmode.decorator.demo2;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能:
 * 创建时间：2020年03月01日
 * 文件名称：Lettuce
 * 版本：1.0.0
 * 最后修改时间：2020/3/1 17:32
 *
 * @auther jlxu
 */
@Slf4j
public class Decorator_zero extends Decorator {

    public Decorator_zero(Human human) {
        super(human);
    }

    public void goHome() {
        log.info("进房子，，，");
    }

    public void findMap() {
        log.info("书房找Map，，，");
    }

    @Override
    public void wearClothes() {
        log.info("Lettuce super ===> {}", super.toString());//com.jlxu.demo.designmode.decorator.demo2.Lettuce@320de594
        super.wearClothes();
        goHome();
    }

    @Override
    public void walkToWhere() {
        super.walkToWhere();
        findMap();
    }
}
