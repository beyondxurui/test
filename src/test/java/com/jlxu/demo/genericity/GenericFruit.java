package com.jlxu.demo.genericity;

/**
 * 功能：类中的泛型方法
 * 创建时间：2020年03月30日
 * 文件名称：GenericFruit
 * 版本：1.0.0
 * 最后修改时间：2020/3/30 21:40
 *
 * @auther jlxu
 */
public class GenericFruit {
//    class Fruit {
//        public String toString() {
//            return "fruit";
//        }
//    }

    static class Apple {
        public String toString() {
            return "apple";
        }
    }

    static class Person {
        public String toString() {
            return "person";
        }
    }

    static class GeneratorTest<T> {
        public void show_1(T t) {
            System.out.println(t.toString());
        }

        //在泛型类中声明了一个泛型方法，使用泛型E，TODO:这种泛型E可以为任意类型。可以类型与T相同，也可以不同。
        //TODO：由于泛型方法在声明的时候会声明泛型<E>，因此即使在泛型类中并未声明泛型，编译器也能够正确识别泛型方法中识别的泛型。
        public <E> void show_3(E e) {
            System.out.println(e.toString());
        }

        // //在泛型类中声明了一个泛型方法，使用泛型T，TODO:注意这个T是一种全新的类型，可以与泛型类中声明的T不是同一种类型。
        public <T> void show_2(T t) {
            System.out.println(t.toString());
        }
    }

    public static void main(String[] args) {  //TODO:静态只能调用静态， 难道博主是更高版本的jdk，做了优化
        Person person = new Person();
        Apple apple = new Apple();
        GeneratorTest<Apple> generatorTest = new GeneratorTest<>();
        generatorTest.show_1(apple);
        //generatorTest.show_1(person);//   in GeneratorTest cannot be applied  to  类型实参为Apple
        //TODO
        generatorTest.show_2(apple);
        generatorTest.show_2(person);
        //TODO
        generatorTest.show_3(apple);
        generatorTest.show_3(person);

    }
}
