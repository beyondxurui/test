package com.jlxu.demo.jvm.loadClass.demo4;

/**
 * 功能：磁盘上的类（J:\java\idea_workspace\springboot_demo\src\test\java\com\jlxu\demo\jvm\loadClass\demo4\Sample）
 * 创建时间：2020年04月06日
 * 文件名称：Sample
 * 版本：1.0.0
 * 最后修改时间：2020/4/6 20:52
 *
 * @auther jlxu
 */
public class Sample {
    private Sample sample;

    public void setSample(Object instance) {
        System.out.println(instance.toString());
        this.sample = (Sample) instance;
    }
}
