package com.jlxu.demo.genericity;

/**
 * 功能： 未传入泛型实参类型
 * 创建时间：2020年03月30日
 * 文件名称：FruitGeneric
 * 版本：1.0.0
 * 最后修改时间：2020/3/30 20:52
 *
 * @auther jlxu
 */
//未传入泛型实参类型时，与泛型类的定义相同，在声明类的时候，需将泛型的声明也一起加到类中
//class FruitGenerator<T> implements GenericInterface<T>{
public class FruitGeneric<T> implements GenericInterface<T> {
    @Override
    public T next() {
        return null;
    }
}
