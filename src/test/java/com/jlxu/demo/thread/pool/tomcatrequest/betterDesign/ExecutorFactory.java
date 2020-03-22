package com.jlxu.demo.thread.pool.tomcatrequest.betterDesign;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 功能
 * 创建时间：2020年03月22日
 * 文件名称：ExecutorFactory
 * 版本：1.0.0
 * 最后修改时间：2020/3/22 11:26
 *
 * @auther jlxu
 */
@Slf4j
public class ExecutorFactory {

    private static final String EXECUTOR_CONFIG_FILE_NAME = "executorConfig.yml";

    private static final String EXECUTOR_TYPE = "executorType";
    private static final String THREAD_NUMBER = "threadNumber";

    // executor types
    private static final String SINGLE_THREAD = "singleThread";
    private static final String THREAD_PER_TASK = "threadPerTask";
    private static final String FIXED_THREAD_POOL = "fixedThreadPool";
    private static final String SINGLE_THREAD_ENHANCED = "singleThreadEnhanced";
    private static final String CACHED_THREAD_POOL = "cachedThreadPool";
    private static final String SCHEDULED_THREAD_POOL = "scheduledThreadPool";

    // default value
    private static String executorType = SINGLE_THREAD;
    private static int threadNumber = 10;


    static {
        readPropertiesFromConfigFile();
    }

    public static Executor newExecutor() {
        switch (executorType) {
            case SINGLE_THREAD:
                return new SingleThreadTaskExecutor();
            case THREAD_PER_TASK:
                return new ThreadPerTaskExecutor();
            case FIXED_THREAD_POOL:
                return Executors.newFixedThreadPool(threadNumber);
            case SINGLE_THREAD_ENHANCED:
                return new SingleThreadTaskExecutor();
            case CACHED_THREAD_POOL:
                return Executors.newCachedThreadPool();
            case SCHEDULED_THREAD_POOL:
                return Executors.newScheduledThreadPool(threadNumber);
            default:
                return new SingleThreadTaskExecutor();
        }
    }


    private static void readPropertiesFromConfigFile() {
        InputStream in = null;
        try {
            //ps
            //相对加载文件的路径的方式  TODO: 思考：为什么博主这里把能用static final修饰的都用了
            /**
             * @see com.jlxu.demo.io.IOTest
             */
            //这里不能用this.getClass..... 回顾static
            //Properties 类表示一组持久的属性。可以将{@code Properties}保存到流或从流中加载。属性列表中的每个键及其对应的值是一个字符串。  TODO:细节后续补充
            //executorConfig.yml:（持久化的）属性文件
            //TODO:思考题:该获取属性文件数据的方式和Spring注解的方式有什么区别  ===> 看源码（后续再次学习矿建时候处理，集合work下SpringBoot文档）
            //一般为什么框架下可以考虑这种方式，如在Spring框架下就用注解方式吧，会更好，且能复用
            in = ExecutorFactory.class.getClassLoader().getResourceAsStream(EXECUTOR_CONFIG_FILE_NAME);
            Properties executorProps = new Properties();
            executorProps.load(in);
            executorType = executorProps.getProperty(EXECUTOR_TYPE);
            threadNumber = Integer.parseInt(executorProps.getProperty(THREAD_NUMBER));
            log.info("executorType ===>{}", executorType);
            log.info("threadNumber ===>{}", threadNumber);
        } catch (IOException e) {
            log.error("Load properties failed!", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
