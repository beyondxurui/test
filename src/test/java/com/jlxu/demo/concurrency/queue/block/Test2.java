package com.jlxu.demo.concurrency.queue.block;

import lombok.extern.slf4j.Slf4j;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 功能：阻塞队列实现的生产者-消费者模式
 * 创建时间：2020年03月24日
 * 文件名称：Test
 * 版本：1.0.0
 * 最后修改时间：2020/3/24 22:13
 *
 * @auther jlxu
 */
@Slf4j
public class Test2 {
    private static final Integer INITIAL_CAPACITY = 10;
    private static final Integer MIN_CAPACITY = 0;
    private static final ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {
        Test2 test = new Test2();
        Producer producer = test.new Producer();
        Consumer consumer = test.new Consumer();
        producer.start();
        consumer.start();
    }

    //消费者
    class Consumer extends Thread {
        @Override
        public void run() {
            consumer();//
        }

        private void consumer() {
            while (true) {
                try {
                    queue.take();
                    System.out.println("从队列取走一个元素，队列剩余" + queue.size() + "个元素");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    //生产者
    class Producer extends Thread {
        @Override
        public void run() {
            producer();//
        }

        private void producer() {
            while (true) {
                try {
                    queue.put(1);
                    System.out.println("向队列中插入一个元素，队列剩余空间：" + (INITIAL_CAPACITY - queue.size()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
