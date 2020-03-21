package com.jlxu.demo.designmode.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能：策略模式测试类    刘备娶媳妇，诸葛亮给赵云锦囊，锦囊中有三个妙计，以保状刘备影城安全
 * 创建时间：2020年03月21日
 * 文件名称：StrategyTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/21 21:46
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class StrategyTest {

    //背景， 使用场景介绍，缺点硬编码，客户端也要跟着变（）
    //问题：如何让算法和对象分开来，使得算法可以独立于使用它的客户而变化？
    //方案：把每一个算法封装起来, 并且使它们可相互替换，使得算法可独立于使用它的客户而变化。这就是策略模式
    //适用情况：一个类定义了多种行为 , 并且这些行为在这个类的操作中以多个条件语句的形式出现。将相关的条件分支移入它们各自的Strategy类中以代替这些条件语句。

    @Test
    public void ownCreateTest() {
        Context context = new Context(new BackDoor());
        context.operate();

        context = new Context(new GivenGreenLight());
        context.operate();

        context = new Context(new BlackEnemy());
        context.operate();
    }

    //思路
    //1）硬编码 if else /case  TODO:这不也是硬编码吗？ 难道客户端在这里指的是 这个测试方法，那么实际中怎么应用呢，看下面
    //2）ps:工作流测策略（反射和注解）TODO:后续看看实现的细节

}
