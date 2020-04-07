package com.jlxu.demo.jvm.loadClass.demo5.client;

/**
 * 功能：加载网络上的类
 * 创建时间：2020年04月06日
 * 文件名称：CalculatorTest
 * 版本：1.0.0
 * 最后修改时间：2020/4/6 23:12
 *
 * @auther jlxu
 */
public class CalculatorTest {
    public static void main(String[] args) {
        String url = "http://localhost/demo/target/test-classes";
        NetworkClassLoader nw = new NetworkClassLoader(url);
        String basicClassName = "com.jlxu.demo.jvm.loadClass.demo5.server.CalculateBasic";
        String advanceClassName = "com.jlxu.demo.jvm.loadClass.demo5.server.CalculatorAdvanced";

        try {
            Class<?> clazz = nw.loadClass(basicClassName);
            ICalculate calculator = (ICalculate) clazz.newInstance(); //TODO: newInstance不是反射？？
            System.out.println(calculator.getVersion());
            calculator = (ICalculate) nw.loadClass(advanceClassName).newInstance();
            System.out.println(calculator.getVersion());
            System.out.println(calculator.calculate("expresion"));

            //1
            //2
            //Result isexpresion

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
