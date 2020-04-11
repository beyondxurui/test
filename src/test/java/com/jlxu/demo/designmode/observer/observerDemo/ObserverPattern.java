package com.jlxu.demo.designmode.observer.observerDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：观察者模式的实现
 * 创建时间：2020年04月10日
 * 文件名称：ObserverPattern
 * 版本：1.0.0
 * 最后修改时间：2020/4/10 23:01
 *
 * @auther jlxu
 */
public class ObserverPattern {
    public static void main(String[] args) {

        Subject sub = new ConcreteSubject();
        Observer obs = new ConcreteObserver1();
        Observer obs2 = new ConcreteObserver2();
        sub.add(obs);
        sub.add(obs2);
        sub.notifyObserver();
        //具体目标发生改变...
        //--------------
        //具体观察者1作出反应！
        //具体观察者2作出反应！

    }
}

//抽象目标
abstract class Subject {
    protected List<Observer> observers = new ArrayList<>();

    //增加观察者方法
    public void add(Observer observer) {
        observers.add(observer);
    }

    //删除观察者方法
    public void remove(Observer observer) {
        observers.remove(observer);
    }

    //通知观察者方法
    public abstract void notifyObserver();
}

//具体目标
class ConcreteSubject extends Subject {

    @Override
    public void notifyObserver() {
        System.out.println("具体目标发生改变...");
        System.out.println("--------------");
        for (Observer observer : observers) {
            observer.response();
        }
    }
}

//抽象观察者
interface Observer {
    void response();
}

//具体观察者1
class ConcreteObserver1 implements Observer {

    @Override
    public void response() {
        System.out.println("具体观察者1作出反应！");
    }
}

//具体观察者2
class ConcreteObserver2 implements Observer {

    @Override
    public void response() {
        System.out.println("具体观察者2作出反应！");
    }
}