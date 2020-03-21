package com.jlxu.demo.designmode.adapter;

import com.jlxu.demo.designmode.adapter.demo.Adapter;
import com.jlxu.demo.designmode.adapter.demo.Adeptee;
import com.jlxu.demo.designmode.adapter.demo.ConcreteTarget;
import com.jlxu.demo.designmode.adapter.demo.Target;
import com.jlxu.demo.designmode.adapter.demo2.Adapter2;
import com.jlxu.demo.designmode.adapter.demo2.Adeptee2;
import com.jlxu.demo.designmode.adapter.demo2.ConcreteTarget2;
import com.jlxu.demo.designmode.adapter.demo2.Target2;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能：适配器模式   https://zhuanlan.zhihu.com/p/24457041   https://blog.csdn.net/jason0539/article/details/22468457
 * 创建时间：2020年03月20日
 * 文件名称：AdapterTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/20 23:26
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class AdapterTest {

    //类的适配器模式
    @Test
    public void test() {
        // 使用普通功能类
        Target concreteTarget = new ConcreteTarget();
        concreteTarget.request();

        //使用特殊功能类，即适配类
        Target target = new Adapter();
        target.request();

        //普通类， 具有普通功能，，，
        //被适配类具有特殊功能，，

        //ps目标类/接口（称为标准接口）（Target2）和另外一个类（不符合标准接口，暂且称为被适配类）已经存在（Adeptee2）,
        // 现在想通过目标类既有目标的功能（这是肯定的），又想用类适配类的功能，所有引入了适配类
        //目标设计为接口更好

    }

    //对象的适配器模式（不是通过继承，而是关联或委托）   扩展性更好
    @Test
    public void test2() {
        // 使用普通功能类
        Target2 target2 = new ConcreteTarget2();
        target2.request();

        // 使用特殊功能类，即适配类，
        // 需要先创建一个被适配类的对象作为参数
        Target2 target21 = new Adapter2(new Adeptee2());
        target21.request();
    }
    //ps TODO:适配器的优缺点和使用场景和栗子 TODO:后续看看
}
