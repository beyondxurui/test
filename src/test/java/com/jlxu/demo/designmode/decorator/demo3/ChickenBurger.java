package com.jlxu.demo.designmode.decorator.demo3;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能:配料基类
 * 创建时间：2020年03月01日
 * 文件名称：ChickenBurger
 * 版本：1.0.0
 * 最后修改时间：2020/3/1 17:46
 *
 * @auther jlxu
 */
@Slf4j
public class ChickenBurger extends Hamburger {
    
    public ChickenBurger() {
        name = "鸡腿堡";
    }
//    public ChickenBurger() {  //为了测试不给name赋值会怎么样
//        name = "鸡腿堡";
//    }

    @Override
    public Double getPrice() {
        return 10.0;
    }
}
