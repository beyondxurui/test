package com.jlxu.demo.jvm.loadClass.demo2;

import com.mysql.cj.jdbc.NonRegisteringDriver;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 功能： 反射 (调用java.lang.Class.forName(…)加载类)
 * 创建时间：2020年04月05日
 * 文件名称：ClassForNameTest
 * 版本：1.0.0
 * 最后修改时间：2020/4/5 22:29
 *
 * @auther jlxu
 */
public class ClassForNameTest {
    private static final String driver = "com.jlxu.demo.jvm.loadClass.demo2.Driver";

    public static void main(String[] args) throws ClassNotFoundException {//
        Class.forName(driver);//单参数版本的forName方法默认是完成初始化的
        //public static Class<?> forName(String name, boolean initialize, ClassLoader loader) throws ClassNotFoundException
        //第二个参数作用：设置强制加载时是否完成初始化

    }
}

//JDBC驱动类的实现
class Driver extends NonRegisteringDriver implements java.sql.Driver {

    public Driver() throws SQLException {
    }

    // 将initialize(初始化)设置为true来强制加载同时完成初始化，实现驱动注册
    static {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
