package com.jlxu.demo.designmode.strategy;

/**
 * 功能：
 * 创建时间：2020年03月21日
 * 文件名称：Strategy
 * 版本：1.0.0
 * 最后修改时间：2020/3/21 21:49
 *
 * @auther jlxu
 */
//初到吴国
public class BackDoor implements Strategy {
    public void operate() { //实现类方法要加访问修饰符
        System.out.println("找乔国老帮忙，让吴国太给孙权施加压力，使孙权不能杀刘备");
    }
}
