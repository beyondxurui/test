package com.jlxu.demo.designmode.decorator.demo1;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能:
 * 创建时间：2020年03月01日
 * 文件名称：ChickenBurger
 * 版本：1.0.0
 * 最后修改时间：2020/3/1 17:46
 *
 * @auther jlxu
 */
@Slf4j
public class Person implements Human {
    @Override
    public void wearClothes() {
        log.info("穿什么呢！");
    }

    @Override
    public void walkToWhere() {
        log.info("去哪里呢！");
    }
}
