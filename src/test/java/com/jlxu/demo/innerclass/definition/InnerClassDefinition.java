package com.jlxu.demo.innerclass.definition;

/**
 * 功能:java源文件名和类名的关系 需要符合下面三条规则
 * TODO: java源文件名的命名与内部类无关,以下3条规则中所涉及的类和接口均指的是外部类/接口，第二大类在Dettd源文件中
 * 创建时间：2020年03月05日
 * 文件名称：InnerClassDefinition
 * 版本：1.0.0
 * 最后修改时间：2020/3/5 22:37
 *
 * @auther jlxu
 */

//内部类的定义
//内部类指的是在一个类的内部所定义的类，类名不需要和源文件名相同。内部类是一个编译时的概念，一旦编译成功，内部类和外部类就会成为两个完全不同的类。
// 例如，对于一个名为 Outer 的外部类和在其内部定义的名为 Inner 的内部类，在编译完成后，会出现 Outer.class 和 Outer$inner.class 两个类。
// 因此，内部类的成员变量/方法名可以和外部类的相同。
// 内部类可以是静态static的，也可用 public，default，protected 和 private 修饰

class Oeef {//1-(2):public类(接口) 与 包可见的类(接口)在文件中的顺序可以随意，即public类(接口)可以不在第一个的位置。
}

//1、如果java源文件包含public类(public接口)，则源文件名必须与public类名(public接口名)相同。
//public class Fdeg{} //1的反例子Class 'Fdeg' is public, should be declared in a file named 'Fdeg.java
public class InnerClassDefinition {//1-(1):一个java源文件中，如果有public类或public接口，那么就只能有一个public类或一个public接口，不能有多个public的类或接口。

    // 当然，一个java源文件中可以有多个包可见的类或接口，即默认访问权限修饰符(类名前没有访问权限修饰符)
    public class Feef {
    }

    class Geef {
    }
//    class Feef {//3、类和接口的命名不能冲突。
//    }
}

class Teef {
}

//public class Teef {//1-(1)的反例子（注意TODO中的描述指的是外部类/接口）
//}


