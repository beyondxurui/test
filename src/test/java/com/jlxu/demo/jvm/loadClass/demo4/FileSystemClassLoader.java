package com.jlxu.demo.jvm.loadClass.demo4;

import java.io.*;

/**
 * 功能：文件系统类加载器    继承ClassLoader
 * 创建时间：2020年04月06日
 * 文件名称：FileSystemClassLoader
 * 版本：1.0.0
 * 最后修改时间：2020/4/6 20:55
 *
 * @auther jlxu
 */
public class FileSystemClassLoader extends ClassLoader {

    private String rootDir;

    public FileSystemClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //获取类的字节码
        byte[] classDate = getClassDate(name);
        //加载
        if (classDate == null) {//这里能想到?想到怎么会处理？
            throw new ClassNotFoundException();
        }
        return defineClass(name, classDate, 0, classDate.length);
    }

    private byte[] getClassDate(String className) {
        String path = classNameToPath(className);
        FileInputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new FileInputStream(path);
            out = new ByteArrayOutputStream();  //为什么是字节数据  TODO:后续处理：System.arrayCopy  Map源码分析中有的
            //https://blog.csdn.net/qq_32440951/article/details/78357325
            int bufferSizes = 4096;
            byte[] buffer = new byte[bufferSizes];
            int byteNumRead = 0;
            while ((byteNumRead = in.read(buffer)) != -1) {  //TODO:这里面用if ，io白学了啊
                out.write(buffer, 0, byteNumRead);  //  TODO:多此一举？
            }
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {  //注意下
            if (out != null) {
                try {
                    out.close();
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private String classNameToPath(String className) {
        // 得到类文件的完全路径  TODO:  File.separatorChar:分隔符处理，不同的系统分割符不同  char:字符用单引号
        //rootDir写死的方式
        //return rootDir + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
        //rootDi动态获取方式 第一个File.separatorChar感觉应该去掉，结果不除掉也没问题
        return rootDir + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
    }
}
