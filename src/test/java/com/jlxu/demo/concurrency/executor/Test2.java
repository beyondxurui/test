package com.jlxu.demo.concurrency.executor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 功能: 使用Callable+Future获取执行结果
 * 创建时间：2020年03月15日
 * 文件名称：Test
 * 版本：1.0.0
 * 最后修改时间：2020/3/15 16:51
 *
 * @auther jlux
 */
@Slf4j
public class Test2 {
    public static void main(String[] args) {
        /*方式一：线程池*/
        ExecutorService executor = Executors.newCachedThreadPool();
        Task2 task = new Task2();
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        executor.submit(futureTask);//提交需要执行的任务
        executor.shutdown();

        /*方式二：Thread*/
//        Thread thread = new Thread(futureTask);
//        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("主线程在执行任务");

        try {
            log.info("task运行结果" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        log.info("所有任务执行完成");

    }
}

@Slf4j
class Task2 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        log.info("子线程进行计算");
        Thread.sleep(3000);
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        return sum;
    }
}
