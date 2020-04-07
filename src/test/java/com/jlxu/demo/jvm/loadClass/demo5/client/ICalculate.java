package com.jlxu.demo.jvm.loadClass.demo5.client;

/**
 * 功能：
 * 创建时间：2020年04月06日
 * 文件名称：Versioned
 * 版本：1.0.0
 * 最后修改时间：2020/4/6 22:43
 *
 * @auther jlxu
 */
public interface ICalculate extends Versioned {
    String calculate(String expresion);
}
