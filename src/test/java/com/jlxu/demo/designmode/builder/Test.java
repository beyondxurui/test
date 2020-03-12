package com.jlxu.demo.designmode.builder;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能
 * 创建时间：2020年03月12日
 * 文件名称：Test
 * 版本：1.0.0
 * 最后修改时间：2020/3/12 13:50
 *
 * @auther
 */
@Slf4j
public class Test {


    public static void main(String[] args) {
        final Person1 person1 = new Person1();
        final Person.Builder builder = new Person.Builder("jlxu", 30);
//        builder = new Person.Builder("jlxu2", 300);//2）TODO:测试是否要加final===>不加final (可以)new实例后

        new Thread(new Runnable() {
            @Override
            public void run() {
                person1.setName("ssd");
                Person person2 = builder.address("雅克蒂斯").builder();
//                Person person2 = builder.address("雅克蒂斯").builder();//2) Variable 'builder' is accessed from within inner class, needs to be final or effectively final
                try {
                    Thread.currentThread().sleep(200);//TODO:这里睡眠时为了看下面线程是否修改后，这里是否还是原来的值
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("person1: {}", person1);//Person1(name=ssd2, age=null, address=null)
                log.info("person2: {}", person2);//Person(name=jlxu, age=30, address=雅克蒂斯2)
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                person1.setName("ssd2");
                Person person3 = builder.address("雅克蒂斯2").builder();
//                Person person3 = builder.address("雅克蒂斯2").builder();//2) Variable 'builder' is accessed from within inner class, needs to be final or effectively final
                log.info("person1: {}", person1);//Person1(name=ssd2, age=null, address=null)
                log.info("person3: {}", person3);//Person(name=jlxu, age=30, address=雅克蒂斯2)
            }
        }).start();

    }
}
