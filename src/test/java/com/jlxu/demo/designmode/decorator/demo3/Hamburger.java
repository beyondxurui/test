package com.jlxu.demo.designmode.decorator.demo3;

/**
 * 功能:汉堡包(被装饰者)
 * 创建时间：2020年03月01日
 * 文件名称：Hamburger
 * 版本：1.0.0
 * 最后修改时间：2020/3/1 17:26
 *
 * @auther jlxu
 */
public abstract class Hamburger {

    //    private String name;
    protected String name;//用protected  子类重写（补重写不报错）

    public String getName() {
        return name;
    }

    public abstract Double getPrice();//抽象中要有抽象方法

}
