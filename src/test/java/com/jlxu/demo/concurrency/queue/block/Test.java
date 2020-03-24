package com.jlxu.demo.concurrency.queue.block;

import lombok.extern.slf4j.Slf4j;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 功能：非阻塞队列  wait  notify实现生产者消费者模式
 * 创建时间：2020年03月24日
 * 文件名称：Test
 * 版本：1.0.0
 * 最后修改时间：2020/3/24 22:13
 *
 * @auther jlxu
 */
@Slf4j
public class Test {
    private static final Integer INITIAL_CAPACITY = 10;
    private static final Integer MIN_CAPACITY = 0;
    private static final Queue<Integer> queue = new PriorityQueue<>(10);

    public static void main(String[] args) {
        Test test = new Test();
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
                synchronized (queue) {
                    while (queue.size() == MIN_CAPACITY) {
                        System.out.println("队列空等待数据");
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    queue.poll(); //每次移走队首元素
                    queue.notify();
                    System.out.println("从队列取走一个元素，队列剩余" + queue.size() + "个元素");
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
                synchronized (queue) {
                    while (queue.size() == INITIAL_CAPACITY) {
                        System.out.println("队列满，等待有空余空间");
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    queue.offer(1);//每次插入一个元素
                    queue.notify();
                    System.out.println("向队列中插入一个元素，队列剩余空间：" + (INITIAL_CAPACITY - queue.size()));
                }
            }
        }
    }

}
