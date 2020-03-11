package com.jlxu.demo.concurrency.synchronize;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能 线程安全问题
 * 创建时间：2020年03月10日
 * 文件名称：Test
 * 版本：1.0.0
 * 最后修改时间：2020/3/10 21:04
 *
 * @auther jlxu
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        try {//TODO:别想着抛异常
            PublicVar publicVarRef = new PublicVar(); //临界资源
            Thread1 thread1 = new Thread1(publicVarRef);//创建并启动线程
            thread1.start();
            Thread.sleep(200);// 打印结果受此值大小影响
            publicVarRef.getValue();
            //ps：从打印结果看，这不是人们想要的的
            //method=setValue 	threadName=Thread-0	 username=B, password=BB
            //method=setValue 	threadName=Thread-0	 username=B, password=BB

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

@Slf4j
class PublicVar {
    private String name = "A";
    private String password = "AA";

    public synchronized void setValue(String name, String password) {
        try {
            this.name = name;
            Thread.sleep(5000);
            this.password = password;
            log.info("method=setValue " + "\t" + "threadName="
                    + Thread.currentThread().getName() + "\t" + " username=" + this.name
                    + ", password=" + this.password);
//            method=setValue 	threadName=Thread-0	 username=B, password=BB
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getValue() {
        log.info("method=getValue " + "\t" + "threadName="
                + Thread.currentThread().getName() + "\t" + " username=" + this.name
                + ", password=" + this.password);
        //            method=getValue 	threadName=main	 username=B, password=AA
    }
}


class Thread1 extends Thread {

    private PublicVar publicVar;//共享资源

    public Thread1(PublicVar publicVar) {
        super();
        this.publicVar = publicVar;
    }

    @Override
    public void run() {
        publicVar.setValue("B", "BB");
    }
}
