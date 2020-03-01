package com.jlxu.demo.designmode.decorator.demo3;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能 :
 * 创建时间：2020年03月01日
 * 文件名称：HamburgerTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/1 17:48
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class HamburgerTest {
    @Test
    public void hamburgerTest() {
        ChickenBurger chickenBurger = new ChickenBurger();
        Hamburger hamburger = new Lettuce(new Chili(chickenBurger));
        log.info(hamburger.getName() + "价钱：" + hamburger.getPrice());

        Hamburger hamburger2 = new Lettuce(chickenBurger);
        log.info(hamburger2.getName() + "价钱：" + hamburger2.getPrice());

        Hamburger hamburger3 = new Chili(chickenBurger);
        log.info(hamburger3.getName() + "价钱：" + hamburger3.getPrice());

        log.info(chickenBurger.name + "价钱：" + chickenBurger.getPrice());
        //chickenBurger给name赋值===>鸡腿堡价钱：10.0   不赋值  ===> null：10.0     null? ===> 因为String是引用类型


        //20:41:13.297 [main] INFO com.jlxu.demo.designmode.decorator.demo3.HamburgerTest - 鸡腿堡 加辣椒 加生菜价钱：12.0
        //20:41:17.903 [main] INFO com.jlxu.demo.designmode.decorator.demo3.HamburgerTest - 鸡腿堡 加生菜价钱：11.5
        //20:41:20.237 [main] INFO com.jlxu.demo.designmode.decorator.demo3.HamburgerTest - 鸡腿堡 加辣椒价钱：10.5


        //案例二三的区别:
        // 1、使用类还是接口，看是否需要变量
        //2、构造的区别 2-1 使用super(装饰着)==>
        // public Decorator_one(Human human) {
        //        super(human);
        //    }
        //2-2 还是使用不用super  ===>
        // private Hamburger hamburger;
        //    public Chili(Hamburger hamburger) {
        //        this.hamburger = hamburger;
        //    }

    }
}
