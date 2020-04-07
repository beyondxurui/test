package com.jlxu.demo.jvm.loadClass.demo5.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 功能：
 * 创建时间：2020年04月06日
 * 文件名称：NetworkClassLoader
 * 版本：1.0.0
 * 最后修改时间：2020/4/6 22:53
 *
 * @auther jlxu
 */
public class NetworkClassLoader extends ClassLoader {
    private String rootDir;

    public NetworkClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    // 获取类的字节码
    public Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classDate = getClassDate(name);
        if (classDate == null) {
            throw new ClassNotFoundException();
        }
        return defineClass(name, classDate, 0, classDate.length);
    }

    private byte[] getClassDate(String classname) {
        // 从网络上读取的类的字节
        String path = classNameToPath(classname);
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            URL url = new URL(path);
            in = url.openStream();//用于从该连接读取数据
            out = new ByteArrayOutputStream();
            int bufferSizes = 4096;
            byte[] by = new byte[bufferSizes];
            int bufferNumRead = 0;
            while ((bufferNumRead = in.read(by)) != -1) {
                out.write(by, 0, bufferNumRead);
            }
            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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

    private String classNameToPath(String classname) {
        // 得到类文件的URL
        return classname + "/" + classname.replace('.', '/') + ".class";
    }


}
