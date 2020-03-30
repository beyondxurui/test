package com.jlxu.demo.genericity;

import java.util.Random;

/**
 * 功能： 未传入泛型实参类型
 * 创建时间：2020年03月30日
 * 文件名称：FruitGeneric
 * 版本：1.0.0
 * 最后修改时间：2020/3/30 20:52
 *
 * @auther jlxu
 */
//在实现类实现泛型接口时，如已将泛型类型传入实参类型，则所有使用泛型的地方都要替换成传入的实参类型
//即：Generator<T>，public T next();中的的T都要替换成传入的String类型。
public class FruitGeneric2 implements GenericInterface<String> {

    private String[] strArr = new String[]{"23", "eee", "dg"};

    @Override
    public String next() {
        Random random = new Random();

        return strArr[random.nextInt(3)];
    }
}
