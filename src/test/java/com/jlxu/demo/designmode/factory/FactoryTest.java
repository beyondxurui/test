package com.jlxu.demo.designmode.factory;

import com.jlxu.demo.designmode.factory.abstractFactory.AbstractFactory;
import com.jlxu.demo.designmode.factory.abstractFactory.FactoryBMW320;
import com.jlxu.demo.designmode.factory.abstractFactory.FactoryBMW523;
import com.jlxu.demo.designmode.factory.factoryMethod.FactoryBM;
import com.jlxu.demo.designmode.factory.factoryMethod.FactoryBM320;
import com.jlxu.demo.designmode.factory.factoryMethod.FactoryBM523;
import com.jlxu.demo.designmode.factory.own.BMA320;
import com.jlxu.demo.designmode.factory.own.BMA523;
import com.jlxu.demo.designmode.factory.simple.BM;
import com.jlxu.demo.designmode.factory.simple.SimpleFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能 工厂模式测试类
 * 创建时间：2020年03月21日
 * 文件名称：FactoryTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/21 17:35
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class FactoryTest {
    //模式的问题：你如何能轻松方便地构造对象实例，而不必关心构造对象实例的细节和复杂过程呢？
    //解决方案：建立一个工厂来创建对
    //实现：
    //一、引言（工业革命）
    //二、分类
    //1）简单工厂模式（Simple Factory）
    //2）工厂方法模式（Factory Method）
    //3）抽象工厂模式（Abstract Factory）     GOF在《设计模式》将简单工厂模式（Simple Factory）看为工厂方法模式的一种特例，两者归为一类。
    //三、区别
    //工厂方法模式：
    //一个抽象产品类，可以派生出多个具体产品类。
    //一个抽象工厂类，可以派生出多个具体工厂类。
    //每个具体工厂类只能创建一个具体产品类的实例。
    //抽象工厂模式：
    //多个抽象产品类，每个抽象产品类可以派生出多个具体产品类。
    //一个抽象工厂类，可以派生出多个具体工厂类。
    //每个具体工厂类可以创建多个具体产品类的实例。
    //区别：
    //工厂方法模式只有一个抽象产品类，而抽象工厂模式有多个。
    //工厂方法模式的具体工厂类只能创建一个具体产品类的实例，而抽象工厂模式可以创建多个。
    //两者皆可。


    //自己创建对象
    @Test
    public void ownCreateTest() {
        BMA320 bma320 = new BMA320();
        BMA523 bma523 = new BMA523();
    }

    //简单工厂模式
    //客户需要知道怎么去创建一款车,客户和车就紧密耦合在一起了.为了降低耦合,就出现了工厂类,把创建宝马的操作细节都放到了工厂里面去,客户直接使用工厂的创建工厂方法,传入想要的宝马车型号就行了,而不必去知道创建的细节.这就是工业革命了：简单工厂模式
    //即我们建立一个工厂类方法来制造新的对象。如图：
    @Test
    public void simpleFactoryTest() {
        SimpleFactory factory = new SimpleFactory();
        BM bm320 = factory.createBM("bm320");
        BM bm523 = factory.createBM("bm523");
        //组成
        //1) 工厂类角色：这是本模式的核心，含有一定的商业逻辑和判断逻辑，用来创建产品
        //2) 抽象产品角色：它一般是具体产品继承的父类或者实现的接口。
        //3) 具体产品角色：工厂类所创建的对象就是此角色的实例。在java中由一个具体类实现。
        //开闭原则分析简单工厂的缺陷，引出工厂方式模式，见factoryMethodTest

    }

    //工厂方法模式
    @Test
    public void factoryMethodTest() {
        FactoryBM factoryBM320 = new FactoryBM320();
        factoryBM320.createBM();

        FactoryBM factoryBM523 = new FactoryBM523();
        factoryBM523.createBM();

        //厂方法模式仿佛已经很完美的对对象的创建进行了包装，使得客户程序中仅仅处理抽象产品角色提供的接口，
        // 但使得对象的数量成倍增长。当产品种类非常多时，会出现大量的与之对应的工厂对象，这不是我们所希望的。
    }

    //抽象工厂方法
    @Test
    public void abstractFactoryTest() {
        AbstractFactory factoryBMW320 = new FactoryBMW320();
        factoryBMW320.createEngine();
        factoryBMW320.createAircondition();

        AbstractFactory factoryBMW523 = new FactoryBMW523();
        factoryBMW523.createEngine();
        factoryBMW523.createAircondition();
        //抽象工厂的来源
    }
    //工厂模式使用的总结  博主的总是是：能降低耦合很重要，不必太在意是工厂方法模式还是抽象工厂模式


}
