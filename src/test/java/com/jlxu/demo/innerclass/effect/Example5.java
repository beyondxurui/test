package com.jlxu.demo.innerclass.effect;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能 ：可以实现多重继承
 * 创建时间：2020年03月05日
 * 文件名称：Example
 * 版本：1.0.0
 * 最后修改时间：2020/3/5 23:26
 *
 * @auther jlxu
 */
@Slf4j
public class Example5 {

    //成员内部类
    public class Inner1 extends Example3 {
        static final String NEELN = "233";//OK final或static & final  但初始化必须是一个常量表单式

        //        final static Inner1 a = new Inner1();//Error
        //        static Integer eie=34;// Error Inner classes cannot have static declarations
        public String getName() {
            return super.getName();
        }
    }


    private class Inner2 extends Example4 {
        public Integer getAge() {
            return super.getAge();
        }
    }

    //静态内部类
    private String ndm = "er";
    private static String ndm2 = "er";

    public static class Inner3 {
        public static String age = "23";

        public void test() {
            log.info(ndm2);
//            log.info(ndm);//Error  Non-static field 'ndm' cannot be referenced from a static context
        }
    }

    //局部内部类
    private String age = "24";

    public void test(int x, final int y) {
        class Inner4 {
            private int a;
            private int b;

            public Inner4(int x, int y) {//构造函数使用外部参数
                this.a = x;
                this.b = y;
                //局部函数构造器使用的外部参数不必是fianl
            }

            public void test2() {
                System.out.println(x);//OK   TODO: why
                System.out.println(y);
                System.out.println(age);
            }
        }
    }
//    Inner4 inner4=new Inner4();//Error  局部内部类只能在作用域中有效


    //匿名内部类一
    public void anonymityMethodTest() {
        new AbsDemo() {
            @Override
            void test() {
                log.info("anonymity");
            }

            public void test2() {
                log.info("被调用了");
            }
        }.test();
    }

    //匿名内部类2-1  匿名内部类没有直接使用其所在的外部类方法的参数
    public AbsDemo2 getInner(final String name, String city) {  // 形参 name 被设为 final
        return new AbsDemo2() {
            private String nameStr = name;       // OK
            private String cityStr = city;       // Error: 形参 city 未被设为 final  TODO:也没错 why

            public String getName() {
                return nameStr;
            }
        };
    }

    //匿名内部类2-2
    public AbsDemo3 getInner2(String name, String city) {   //注意这里的形参city，由于它没有被匿名内部类直接使用，而是被抽象类Inner的构造函数所使用，所以不必定义为final
        return new AbsDemo3(name, city) {//OK，形参 name 和 city 没有被匿名内部类直接使用
            private String nameStr = name;

            public String getName() {
                return nameStr;
            }
        };
    }

    public String getName() {//通过方法获取构造也可以在测试类中（感脚不是很好？）
        return new Inner1().getName();
    }

    public Integer getAge() {
        return new Inner2().getAge();
    }
}

