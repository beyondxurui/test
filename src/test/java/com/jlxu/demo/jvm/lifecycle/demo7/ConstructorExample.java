package com.jlxu.demo.jvm.lifecycle.demo7;

/**
 * 功能：初始化典型典型案例三
 * 创建时间：2020年04月04日
 * 文件名称：ConstructorExample
 * 版本：1.0.0
 * 最后修改时间：2020/4/4 23:40
 *
 * @auther jlxu
 */
public class ConstructorExample {
    public static void main(String[] args) {
        Bad bad = new Bad();
        System.out.println(bad.getValue());
        //2
        //0  TODO：多条会吗？
        //2
    }
}

class Fou {
    int i = 1;

    Fou() {
        System.out.println(i);
        int x = getValue();
        System.out.println(x);
    }

    {
        i = 2;
    }

    protected int getValue() {
        return i;
    }
}

class Bad extends Fou {
    int j = 1;

    Bad() {
        j = 2;
    }

    {
        j = 3;
    }

    protected int getValue() {
        return j;
    }
}