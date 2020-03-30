package com.jlxu.demo.innerclass.effect;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Executors;

/**
 * 功能：内部类作用测试
 * 创建时间：2020年03月05日
 * 文件名称：InnerClassEffectTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/5 23:23
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class InnerClassEffectTest {

    //内部类可以很好的实现隐藏测试
    @Test
    public void test1() {
        Example example = new Example();
        example.getInnerInstance().test();
        //对客户端而言，我们可以通过 Example 的getInnerInstance()方法得到一个InterfaceTest 实例，但我们并不知道这个实例是如何实现的，也感受不到对应的具体实现类的存在。由于 InnerClass 是 private 的，所以，我们如果不看源代码的话，连实现这个接口的具体类的名字都看不到，所以说内部类可以很好的实现隐藏。
    }

    //内部类拥有外围类的所有元素的访问权限
    @Test
    public void test2() {
        Example2 example = new Example2();
        example.getInnerInstance();
//        Example2.Inner innerInstance = new Example2().getInnerInstance();//在客户端创建内部类对象时，需要先创建外部类对象。
    }

    //可以实现多重继承
    @Test
    public void test3() {
        //对多重继承而言，可以这样说，接口只是解决了部分问题，而内部类使得多重继承的解决方案变得更加完整。
        // 内部类使得Java的继承机制更加完善，是内部类存在的"最大理由"。
        // Java中的类只能继承一个类，它的多重继承在我们没有学习内部类之前是用接口来实现的。但使用接口有时候有很多不方便的地方，比如，我们实现一个接口就必须实现它里面的"所有方法"；
        // 而内部类可以使我们的类继承多个具体类或抽象类，规避接口的限制性
        Example5 example = new Example5();
        log.info(example.getName());
        log.info(example.getAge() + "");
    }

    //避免修改接口而实现同一个类中两种同名方法的调用
    @Test
    public void test4() {
        //(1)不使用内部类方式
        Example7 example = new Example7();
        example.test();

        //(2)使用内部类方式
        Example8 example8 = new Example8();
        example.test();
        example8.getInterfaceTest().test();
    }

    //成员内部类
    @Test
    public void test5() {
        //1
        //成员内部类是最普通的内部类，它是外围类的一个成员，在实际使用中，一般将其可见性设为 private。
        // 成员内部类是依附于外围类的，所以，只有先创建了外围类”对象“才能够创建内部类对象。也正是由于这个原因，成员内部类也不能含有 static 的变量和方法
        //例子可看Example5中
        String s = Example5.Inner1.NEELN;//常量表达式可以，内部类的实例不可以
        log.info(s);


        //TODO:如果上面的代码编译无误, 那么我们就可以直接通过 Outter.Inner.a（和访问修饰符有关系) 拿到内部类Inner的实例。
        // 由于内部类的实例一定要绑定到一个外部类的实例的（这句话真的没懂,懂了是内部类的实例（这里指的是a），不是内部类实例，多了一个的），
        // 所以矛盾。因此，成员内部类不能含有 static 变量/方法
        //ps:记得回顾

        //2交互
        //成员内部类可以直接访问外部类的所有成员和方法，即使是 private 的；
        //外部类需要通过内部类的对象访问内部类的所有成员变量/方法。

        //必须先有外部类的对象才能生成内部类的对象。例子如下 （注意是对象不是类）
        Example5.Inner1 inner1 = new Example5().new Inner1();
        //因此，成员内部类，外部类和客户端之间的交互关系为：
        // 在成员内部类使用外部类对象时，使用 outer.this 来表示外部类对象；
        //在外部类中使用内部类对象时，需要先进行创建内部类对象；
        //在客户端创建内部类对象时，需要先创建外部类对象。
        //特别地 推荐使用外部类getxxx()获取成员内部类对象

        //3、私有成员内部类
        //如果一个成员内部类只希望被外部类操作，那么可以使用 private 将其声明私有内部类
        //例子  如果Example的Inner1内部类用private修饰，就不可以 Example5.Inner1 inner1 = new Example5().new Inner1();了
    }

    //静态内部类
    @Test
    public void test6() {
        //1、子
        Example5.Inner3 inner3 = new Example5.Inner3();  //TODO:外部类不需要（）

        //2、交互
        //　静态内部类与外部类的交互关系为：
        //静态内部类可以直接访问外部类的所有静态成员和静态方法，即使是 private 的；  ；栗子见Example中的静态内部类
        //外部类可以通过内部类对象访问内部类的实例成员变量/方法；对于内部类的静态域/方法，外部类可以通过内部类类名访问 栗子如下
        String age = Example5.Inner3.age;

        //3、成员内部类和静态内部类的区别
        //成员内部类和静态内部类之间的不同点包括：
        //静态内部类对象的创建不依赖外部类的实例，但成员内部类对象的创建需要依赖外部类的实例；
        //成员内部类能够访问外部类的静态和非静态成员，静态内部类不能访问外部类的非静态成员；
    }

    //局部内部类
    @Test
    public void test7() {
        //定义与原理
        //有这样一种内部类，它是嵌套在方法和作用域内的，对于这个类的使用主要是”应用与解决“比较复杂的问题，
        // 想创建一个类来辅助我们的解决方案，但又”不希望这个类是公共可用的“，所以就产生了局部内部类。
        // 局部内部类和成员内部类一样被编译，只是它的”作用域“发生了改变，它只能在”该方法和属性中被使用“，出了该方法和属性就会失效。


        //2、final 参数
        //对于final参数，若是将引用类型参数声明为final，我们无法在方法中更改参数引用所指向的对象；
        // 若是将基本类型参数声明为final，我们可以读参数，但却无法修改参数（这一特性主要用来向局部内部类和匿名内部类传递数据）。
        //TODO:案例说明，案例的使用的jdk可能不是jdk1.8，jdk1.8
        // 以前java的匿名内部类在访问外部变量的时候，外部变量必须用final修饰。Bingo，java8对这个限制做了优化，外部变量可以不用显式使用final修饰，但编译器会自动把它当成final来处理。
        // 同理，对于匿名内部类使用外部类参数时同样适用

    }

    //五. 匿名内部类  TODO：匿名内部类的位子===>一般是外部类的方法内  ，可以说匿名内部类是局部内部类，因为匿名内部类没有名字
    @Test
    public void test8() {
        //匿名内部类是没有访问修饰符的；
        //匿名内部类是没有构造方法的 (因为匿名内部类连名字都没有)；
        //定义匿名内部类的前提是，内部类必须是继承一个类或者实现接口，
        // 格式为 new 父类或者接口(){子类的内容(如函数等)}。也就是说，匿名内部类最终提供给我们的是一个"匿名子类的对象"，例如：。

        //匿名内部类调用函数,匿名内部类方法只能调用一次
        Example5 example5 = new Example5();
        for (int i = 0; i < 2; i++) {
            example5.anonymityMethodTest();//打印了多次
            //应该是一次执行只调用了一次
        }
        // 匿名内部类实质上是一个匿名子类的对象 即：不调用方法，是不会执行方法内的代码块的


        //若匿名内部类 (匿名内部类没有构造方法) 需要直接使用其所在的外部类方法的参数时，该形参必须为 final 的；
        //如果匿名内部类没有直接使用其所在的外部类方法的参数时，那么该参数就不必为final

        //jdk1.8之前内部类直接使用外部类的为什么要加final？  注意，jdk1.8做了优化，见上面的TODO
        //  TODO:内部类被编译的时候会生成一个单独的内部类的.class文件，这个文件并不与外部类在同一class文件中
        // 具体分析如下
        //  1）从java程序的角度来看是直接的调用，例如：
        //public void dosome(final String a,final int b){
        //  class Dosome{
        //       public void dosome(){
        //            System.out.println(a+b)
        //       }
        //  };
        //
        //  Dosome some=new Dosome();
        //  some.dosome();
        //} 12345678910
        //
        //　2）从代码来看，好像是内部类直接调用的a参数和b参数，但是实际上不是，在java编译器编译以后实际的操作代码是:
        //class Outer$Dosome{
        //  public Dosome(final String a,final int b){
        //      this.Dosome$a=a;
        //      this.Dosome$b=b;
        //  }
        //  public void dosome(){
        //      System.out.println(this.Dosome$a+this.Dosome$b);
        //  }
        //}123456789
        //
        //　TODO:从以上代码来看，内部类并不是直接调用方法传进来的参数，
        // 而是内部类将传进来的参数通过自己的构造器备份到了自己的内部，
        // 自己内部的方法调用的实际是自己的属性而不是外部类方法的参数。
        // 这样就很容易理解为什么要用final了，因为两者从外表看起来是同一个东西，
        // 实际上却不是这样，如果内部类改掉了这些参数的值也不可能影响到原参数，
        // 然而这样却失去了参数的一致性，因为从编程人员的角度来看他们是同一个东西，
        // 如果编程人员在程序设计的时候在内部类中改掉参数的值，
        // 但是外部调用的时候又发现值其实没有被改掉，这就让人非常的难以理解和接受，
        // 为了避免这种尴尬的问题存在，所以编译器设计人员把内部类能够使用的参数设定为必须是final来规避这种莫名其妙错误的存在。

    }

    //六. 内部类的继承
    //内部类的继承，是指内部类被继承，普通类 extents 内部类。而这时候代码上要有点特别处理，具体看以下例子：
    public void test9() {
        WithInner withInner = new WithInner();
        InheritInner inheritInner = new InheritInner(withInner);
        //子类的构造函数里面要使用父类的外部类对象.super() [成员内部类对象的创建依赖于外部类对象]；
        // 而这个外部类对象需要从外面创建并传给形参。
        //TODO: why  ===> 品一品（结合成员内部类对象的创建依赖于外部类对象和withInner.super(含义)）
    }
}
