package com.jlxu.demo.designmode.decorator.demo1;

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
public class Decorator_zero implements Human {
    private Human human;

    public Decorator_zero(Human human) {
        this.human = human;
    }

    @Override
    public void wearClothes() {
        log.info("进房子，，，");
    }

    @Override
    public void walkToWhere() {
        log.info("书房找Map，，，");
    }
}
