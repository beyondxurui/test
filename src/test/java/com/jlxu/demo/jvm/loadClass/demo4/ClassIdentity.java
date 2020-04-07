package com.jlxu.demo.jvm.loadClass.demo4;

import java.lang.reflect.Method;

/**
 * 功能：文件系统类加载器测试类
 * 创建时间：2020年04月06日
 * 文件名称：ClassIdentity
 * 版本：1.0.0
 * 最后修改时间：2020/4/6 21:32
 *
 * @auther jlxu
 */
public class ClassIdentity {
    public static void main(String[] args) {
//        String classDataRootPath = "J:\\java\\idea_workspace\\springboot_demo\\target\\test-classes";
        //获取classpath的绝对路径  /J:/java/idea_workspace/springboot_demo/target/test-classes/
        String classDataRootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        //当前类的绝对路径      /J:/java/idea_workspace/springboot_demo/target/test-classes/com/jlxu/demo/jvm/loadClass/demo4/
        //String path2 = ClassIdentity.class.getResource("").getPath();
        String classname = "com.jlxu.demo.jvm.loadClass.demo4.Sample";
        FileSystemClassLoader fs = new FileSystemClassLoader(classDataRootPath);
        FileSystemClassLoader fs2 = new FileSystemClassLoader(classDataRootPath);
        try {
            Class class1 = fs.loadClass(classname);  //TODO: loadClass 不是findClass
            Object obj1 = class1.newInstance();//反射方式之一
            Class class2 = fs2.loadClass(classname);
            Object obj2 = class2.newInstance();//反射方式之一
            Method method = class1.getMethod("setSample", Object.class);  //可变参数
            method.invoke(obj1, obj2);
            //com.jlxu.demo.jvm.loadClass.demo4.Sample@12edcd21

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
