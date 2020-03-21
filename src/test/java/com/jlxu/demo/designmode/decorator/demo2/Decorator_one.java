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
public class Decorator_one extends Decorator {

    public Decorator_one(Human human) {
        super(human);
    }

    public void goClothespress() {
        log.info("去衣柜找找看，，，");
    }

    public void findPlaceOnMap() {
        log.info("在Map上找找，，，");
    }

    @Override
    public void wearClothes() {
        log.info("Chili super ===> {}", super.toString());//com.jlxu.demo.designmode.decorator.demo2.Decorator_one@49049a04
        super.wearClothes();
        goClothespress();
    }

    @Override
    public void walkToWhere() {
        super.walkToWhere();
        findPlaceOnMap();
    }
}
