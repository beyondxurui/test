package com.jlxu.demo.designmode.decorator.demo1;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能
 * 创建时间：2020年03月01日
 * 文件名称：HamburgerTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/1 17:48
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class HumanWearAndWalkTest {
    @Test
    public void humanWearAndWalkTest() {
        Person person = new Person();
        Human human = new Decorator_zero(new Decorator_one(new Decorator_two(person)));
        human.wearClothes();
        human.walkToWhere();
        //17:53:03.925 [main] INFO com.jlxu.demo.designmode.decorator.demo1.Lettuce - 进房子，，，
        //17:53:03.925 [main] INFO com.jlxu.demo.designmode.decorator.demo1.Lettuce - 书房找Map，，，
        //该场景不适合用接口，为什么会这样？===>  Decorator_xxx和person都是被装饰着的实现类，这里new的是Decorator_zero
    }
}
