package com.jlxu.demo.designmode.decorator.demo2;

import com.jlxu.demo.designmode.decorator.demo3.ChickenBurger;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能 :demo1纠正案例
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
    public void humanWearAndWalkTest2() {
        Person person = new Person();
        Human human = new Decorator_two(new Decorator_one(new Decorator_zero(person)));
        human.wearClothes();
        human.walkToWhere();

        //18:24:19.284 [main] INFO com.jlxu.demo.designmode.decorator.demo2.ChickenBurger - 穿什么呢！
        //18:24:19.284 [main] INFO com.jlxu.demo.designmode.decorator.demo2.Lettuce - 进房子，，，
        //18:24:19.284 [main] INFO com.jlxu.demo.designmode.decorator.demo2.Chili - 去衣柜找找看，，，
        //18:24:19.284 [main] INFO com.jlxu.demo.designmode.decorator.demo2.Condiment_two - 找到一件ku，，，
        //18:24:19.284 [main] INFO com.jlxu.demo.designmode.decorator.demo2.ChickenBurger - 去哪里呢！
        //18:24:19.285 [main] INFO com.jlxu.demo.designmode.decorator.demo2.Lettuce - 书房找Map，，，
        //18:24:19.286 [main] INFO com.jlxu.demo.designmode.decorator.demo2.Chili - 在Map上找找，，，
        //18:24:19.286 [main] INFO com.jlxu.demo.designmode.decorator.demo2.Condiment_two - 在Map上找到亚特兰蒂斯，，，

        //debug
        //1:Hamburger human = new，，，===> Lettuce   ->  Chili  ->  Condiment_two
        //2:human.wearClothes()
        //Decorator_two中的super.wearClothes() -> Decorator_one中的super.wearClothes() -> Decorator_zero中的super.wearClothes();
        //Person中的wearClothes()  然后再回来

        //实例化 ===>显示实例化  触发（如果有父类，先触发父类）变更和代码块初始化

        //super的值：看Decorator_xxx的日志  就是本身和高速缓存的一样

        ChickenBurger chickenBurger = new ChickenBurger();
    }
}
