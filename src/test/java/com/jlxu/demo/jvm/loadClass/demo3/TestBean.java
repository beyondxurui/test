package com.jlxu.demo.jvm.loadClass.demo3;

import lombok.Data;
import sun.misc.URLClassPath;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 功能：
 * 创建时间：2020年04月06日
 * 文件名称：TestBean
 * 版本：1.0.0
 * 最后修改时间：2020/4/6 10:41
 *
 * @auther jlxu
 */
public class TestBean {
    private static final String BEAN_NAME = "com.jlxu.demo.jvm.loadClass.demo3.Bean";

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader myClassLoader = new ClassLoader() {
            public Class<?> findClass(String name) throws ClassNotFoundException {
                try {
                    String filename = name.substring(name.lastIndexOf(".") + 1) + ".class"; //Bean.class
                    InputStream is = getClass().getResourceAsStream(filename);
                    if (is == null) {
                        return super.loadClass(filename); // 递归调用父类加载器
                    }
                    //定义一个字节数组，TODO：为什么数组长度是0？
                    byte[] by = new byte[is.available()];
                    System.out.println(by.length);//1465
                    //io 这个方法是阻塞的
                    //从输入流中读取一定数量的字节并将其存储到缓冲区数组,实际读取的字节数作为整数返回。
                    // 此方法阻塞，直到输入数据可用、检测到文件结束或抛出异常。
                    is.read(by);
                    //字节数组转换为类的实例。在类可以使用之前，它必须被解析
                    return defineClass(name, by, 0, by.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);  //这里为什么这样处理 TODO: 提示，返回值和类加载器的的原理
                }

            }
        };

        Class loadClass = myClassLoader.loadClass(BEAN_NAME);
        Object obj = loadClass.newInstance();  //Class实例方法
        System.out.println(obj.getClass());
        System.out.println(obj instanceof Bean);

        //四. 常见问题分析
        //5，只要把重写的方法findClass改为loadClass就会报错  原因见 5中的1）
        System.out.println(loadClass.getName());//com.jlxu.demo.jvm.loadClass.demo3.Bean
        System.out.println(loadClass.getClassLoader());//sun.misc.Launcher$AppClassLoader@18b4aac2

        //7
        URL[] extURLs = ((URLClassLoader) ClassLoader.getSystemClassLoader().getParent()).getURLs();

        for (URL extURL : extURLs) {
            System.out.println(extURL);
        }
    }
}

@Data
class Bean {
    private String name;
}
