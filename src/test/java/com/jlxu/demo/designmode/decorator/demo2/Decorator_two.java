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
public class Decorator_two extends Decorator {

    public Decorator_two(Human human) {
        super(human);
    }

    public void findClothes() {
        log.info("找到一件ku，，，");
    }

    public void findTheTarget() {
        log.info("在Map上找到亚特兰蒂斯，，，");
    }

    @Override
    public void wearClothes() {
        log.info("Condiment_two super ===> {}", super.toString());//com.jlxu.demo.designmode.decorator.demo2.Decorator_two@3cebbb30
        super.wearClothes();
        findClothes();
    }

    @Override
    public void walkToWhere() {
        super.walkToWhere();
        findTheTarget();
    }
}
