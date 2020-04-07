package com.jlxu.demo.jvm.loadClass.demo;

/**
 * 功能：类加载双亲委派示例
 * 创建时间：2020年04月05日
 * 文件名称：ClassLoaderTest
 * 版本：1.0.0
 * 最后修改时间：2020/4/5 18:32
 *
 * @auther jlxu
 */
public class ClassLoaderTest {
    public static void main(String[] args) {

        try {
            //查看当前系统类路径中包含的路径条目
            String classPath = System.getProperty("java.class.path");  //TODO:通过日志可以明白什么是当前系统类路径的吧  J:\java\soft\jdk-8u162\jre\lib  在看看jdk环境变量配置的
            System.out.println(classPath);
            //调用加载当前类的类加载器（这里即为系统类加载器）加载TestBean
            Class type = Class.forName("com.jlxu.demo.jvm.loadClass.demo.entity.TestBean");//TODO: Class.forName()可以是相对路径？
            //查看被加载的TestBean类型是被那个类加载器加载的
            System.out.println(type.getClassLoader());

            //J:\java\soft\jdk-8u162\jre\lib\charsets.jar;
            //J:\java\soft\jdk-8u162\jre\lib\deploy.jar;
            // J:\java\soft\jdk-8u162\jre\lib\ext\access-bridge-64.jar;
            // J:\java\soft\jdk-8u162\jre\lib\ext\cldrdata.jar;
            // J:\java\soft\jdk-8u162\jre\lib\ext\dnsns.jar;
            // J:\java\soft\jdk-8u162\jre\lib\ext\jaccess.jar;
            // J:\java\soft\jdk-8u162\jre\lib\ext\jfxrt.jar;
            // J:\java\soft\jdk-8u162\jre\lib\ext\localedata.jar;
            // J:\java\soft\jdk-8u162\jre\lib\ext\nashorn.jar;
            // J:\java\soft\jdk-8u162\jre\lib\ext\sunec.jar;
            // J:\java\soft\jdk-8u162\jre\lib\ext\sunjce_provider.jar;
            // J:\java\soft\jdk-8u162\jre\lib\ext\sunmscapi.jar;
            // J:\java\soft\jdk-8u162\jre\lib\ext\sunpkcs11.jar;
            // J:\java\soft\jdk-8u162\jre\lib\ext\zipfs.jar;
            // J:\java\soft\jdk-8u162\jre\lib\javaws.jar;
            // J:\java\soft\jdk-8u162\jre\lib\jce.jar;
            // J:\java\soft\jdk-8u162\jre\lib\jfr.jar;
            // J:\java\soft\jdk-8u162\jre\lib\jfxswt.jar;
            // ，，，

            //sun.misc.Launcher$AppClassLoader@18b4aac2
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        //TODO:获取当前工程路径
        System.getProperty("user.dir");
        //TODO:java中JAVA_HOME、PATH及CLASSPATH解析（转载/整理）
        //https://www.cnblogs.com/xhj-records/archive/2013/03/18/2965661.html
        

    }
}
