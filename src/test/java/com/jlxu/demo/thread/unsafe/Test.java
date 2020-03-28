package com.jlxu.demo.thread.unsafe;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：考题泄露
 * 创建时间：2020年03月15日
 * 文件名称：Test
 * 版本：1.0.0
 * 最后修改时间：2020/3/15 18:04
 *
 * @auther jlxu
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        QuizEventSource quizEventSource = new QuizEventSource();
        MyThread myThread = new MyThread(quizEventSource);
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(myThread);
        }
        for (Thread thread : threads) {
            thread.start();
        }

    }
}

class MyThread implements Runnable {
    private ThisEscape.EventSource quizEventSource;

    public MyThread(ThisEscape.EventSource quizEventSource) {
        this.quizEventSource = quizEventSource;
    }

    @Override
    public void run() {

        ThisEscape thisEscape = new ThisEscape(quizEventSource);
    }
}

@Slf4j
class ThisEscape {
    private final List<Event> listOfEvents;

    public ThisEscape(EventSource source) {
        source.registerListener(new EventListener() {
            @Override
            public void onEvent(Event event) {
                doSomething(event);
            }
        });
        listOfEvents = new ArrayList<>();
    }

    void doSomething(Event event) {
        log.info("进入doSomething了，，");
        listOfEvents.add(event);
    }


    interface EventSource {
        void registerListener(EventListener listener);
    }

    interface EventListener {
        void onEvent(Event event);
    }

    interface Event {

    }


}

//体温事件源
@Slf4j
class QuizEventSource implements ThisEscape.EventSource {

    @Override
    public void registerListener(ThisEscape.EventListener listener) {
        log.info("入了提问");
    }
}