package com.jlxu.demo.designmode.decorator.demo3;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能:生菜
 * 创建时间：2020年03月01日
 * 文件名称：Lettuce
 * 版本：1.0.0
 * 最后修改时间：2020/3/1 17:32
 *
 * @auther jlxu
 */
@Slf4j
public class Lettuce extends Condiment {

    private Hamburger hamburger;

    public Lettuce(Hamburger hamburger) {
        this.hamburger = hamburger;
    }

    @Override
    public String getName() {
        return hamburger.getName() + " 加生菜";
    }

    @Override
    public Double getPrice() {
        return hamburger.getPrice() + 1.5;
    }
}
