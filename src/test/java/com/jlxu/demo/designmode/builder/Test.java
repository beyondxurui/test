//package com.jlxu.demo.designmode.builder;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// * 功能
// * 创建时间：2020年03月12日
// * 文件名称：Test
// * 版本：1.0.0
// * 最后修改时间：2020/3/12 13:50
// *
// * @auther
// */
//@Slf4j
//public class Test {
//
//
//    public static void main(String[] args) {
//        Test test = new Test();
//        Person1 person1 = new Person1();
//        Person.Builder builder = new Person.Builder("jlxu", 30);
//        Person person = builder.address("雅克蒂斯").builder();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                person1.setName("ssd");
//                builder.address("雅克蒂斯").builder();
//                try {
//                    Thread.currentThread().sleep(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                log.info("person1: {}", person1);//Person1(name=ssd2, age=null, address=null)
//                log.info("builder: {}", builder);
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                person1.setName("ssd2");
//                builder.address("雅克蒂斯2").builder();
//                log.info("person1: {}", person1);
//                log.info("builder: {}", builder);
//            }
//        }).start();
//
//    }
//}
