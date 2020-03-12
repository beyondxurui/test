package com.jlxu.demo.designmode.builder;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能：Builder 模式
 * 创建时间：2020年03月11日
 * 文件名称：BuilderTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/11 23:54
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class BuilderTest {
    @Test
    public void builderTest() {
        //零、回顾部分
        //TODO:回顾内部类
        // 不同内部类的创建方式，  静态new Outer.Inner()  成员 new Outer().Inner()  局部（方法和代码块见内部类参考地址）
        //TODO:注意：一般会在外部类定义一个方法返回内部类对象
        // 内部类的作用：
        //使用内部类可以给我们带来以下优点：
        //内部类可以很好的实现隐藏（一般的非内部类，是不允许有 private 与 protected 权限的，但内部类可以）；
        //内部类拥有外围类的所有元素的访问权限；
        //可以实现多重继承；
        //可以避免修改接口而实现同一个类中两种同名方法的调用。

        //一. 动机
        //　　当我们需要创建一个复杂的对象时，使用静态工厂或者构造器的方式就显得特别笨拙和丑陋，因为它们有个共同的局限性：它们都不能很好地扩展到大量的可选参数。考虑用一个Person类来描述一个人，除了姓名，性别，生日，邮箱等必要的属性外，还有很多可选的属性，比如：身高，学历，绰号，体重，通讯地址等等。对于这样的类，我们应该如何创建对象呢？无论是常见的重叠构造器模式还是JavaBeans模式，它们都不能很好地解决这类问题，而我们本文所着重阐述的Builder模式则正好是解决此类问题的利剑。为了更深入的了解Builder模式所带来的好处，我们先分别采用重叠构造器模式和JavaBeans模式来解决上述问题。

        //二、使用重叠构造器模式创建复杂对象
        //（1）灵活性很差：如果客户端只想创建一个给定姓名，性别，生日，邮箱和体重的人，那么他将调用如下构造函数，这样无意中就“被迫”设置了他本不想设置的一些参数。
        //    public Person(String name, String sex, Date date, String email, int height, String edu, String nickName, int
        //            weight) {
        //        this(name, sex, date, email, height, edu, nickName, weight, null);
        //    }1234
        //（2）代码难以编写与阅读：当属性有很多的时候，代码不但看起来很丑陋，而且极易出错。试想，若客户端不小心颠倒了参数列表中两个参数的顺序 (例如，颠倒了参数“email”和“edu”)，编译器也不会出错，但是在运行时就会出现错误的行为，并且这种错误难以发现。
        //ps:这两种都体验过了，项目中的结果集

        //三、使用JavaBeans模式创建复杂对象
        //（1）Setter的存在妨碍了其成为不可变类的可能：这样，在并发环境下，我们就不得不考虑其线程安全性；  TODO: 百度set的并发问题
        //（2）代码丑陋且对象易处于不一致状态：上面创建对象的方式也比较丑陋，TODO:同时由于对象的构造过程分为若干个函数调用，所以容易导致对象处于不一致状态。(品品，就理解了)
        
        //四、使用Builder模式创建复杂对象
        Person.Builder builder = new Person.Builder("jlxu", 30);
        Person person = builder.address("雅克蒂斯").builder();
        //ps常见到的是没有第一行代码（视场景而定）
    }
}
