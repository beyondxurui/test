package com.jlxu.demo.concurrency.communication;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能： 线程间的通信：管道
 * 创建时间：2020年03月14日
 * 文件名称：Test
 * 版本：1.0.0
 * 最后修改时间：2020/3/14 9:32
 *
 * @auther jlxu
 */
@Slf4j
public class Test7 {

    public static void main(String[] args) {
        try {
            PipedInputStream input = new PipedInputStream();
            PipedOutputStream out = new PipedOutputStream();
            PipedWriter writer = new PipedWriter();
            PipedReader reader = new PipedReader();

            out.connect(input);


            ThreadRead threadRead = new ThreadRead(reader, input);
            threadRead.start();
            Thread.sleep(2000);
            ThreadWrite threadWrite = new ThreadWrite(writer, out);
            threadWrite.start();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //read :
        //write :
        //01234567891011121314151617181920212223242526272829
        //01234567891011121314151617181920212223242526272829

    }
}

//读线程
@Slf4j
class ThreadRead extends Thread {
    private PipedReader read;
    private PipedInputStream input;

    public ThreadRead(PipedReader read, PipedInputStream input) {
        super();
        this.read = read;
        this.input = input;
    }

    public void readMethod(PipedInputStream input) {
        try {
            log.info("read :");
            byte[] byteArray = new byte[20];
            int readLength = input.read(byteArray);
            while (readLength != -1) {
                String newData = new String(byteArray, 0, readLength);
                System.out.print(newData); //print不换行
                readLength = input.read(byteArray);
            }
            System.out.println();//换行  log的\n===>换行
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        readMethod(input);
    }

}

//写线程
@Slf4j
class ThreadWrite extends Thread {
    private PipedWriter writer;
    private PipedOutputStream out;

    public ThreadWrite(PipedWriter writer, PipedOutputStream out) {
        super();
        this.writer = writer;
        this.out = out;
    }

    public void writeMethod(PipedOutputStream out) {
        try {
            log.info("write :");
            for (int i = 0; i < 30; i++) {
                String outData = "" + i;
                out.write(outData.getBytes());
                System.out.print(outData);
            }
            System.out.println();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        writeMethod(out);
    }

}
