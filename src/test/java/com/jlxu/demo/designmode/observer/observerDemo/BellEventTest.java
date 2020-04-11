package com.jlxu.demo.designmode.observer.observerDemo;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;

/**
 * 功能：利用观察者模式设计一个学校铃声的事件处理程序
 * 创建时间：2020年04月10日
 * 文件名称：BellEventTest
 * 版本：1.0.0
 * 最后修改时间：2020/4/10 23:43
 *
 * @auther jlxu
 */
public class BellEventTest {
    public static void main(String[] args) {

        BellEventSource bellEventSource = new BellEventSource();
        //注册监听器
        bellEventSource.addPersonListener(new TeachEventListener());
        bellEventSource.addPersonListener(new StuEventListener());
        bellEventSource.ring(true);
        bellEventSource.ring(false);
    }
}

//铃声事件类：用于封装事件源及一些与事件相关的参数
class RingEvent extends EventObject {
    private boolean sound;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public RingEvent(Object source, boolean sound) {
        super(source);
        this.sound = sound;
    }

    public boolean isSound() {
        return sound;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }
}

//目标类：事件源，铃
class BellEventSource {
    private List<BellEventListener> listeners;

    public BellEventSource() {
        listeners = new ArrayList<>();
    }

    //给事件源绑定监听器
    public void addPersonListener(BellEventListener listener) {
        listeners.add(listener);
    }

    //事件触发器：敲钟，当铃声sound的值发生变化时，触发事件。
    public void ring(boolean sound) {
        String type = sound ? "上课铃" : "下课铃";
        System.out.println(type + "铃");
        RingEvent ringEvent = new RingEvent(this, sound);
        //通知注册在该事件源上的所有监听器
        notifies(ringEvent);
    }

    protected void notifies(RingEvent ringEvent) {
        for (BellEventListener listener : listeners) {
            listener.heardBell(ringEvent);
        }
    }
}

//抽象观察者类：铃声事件监听器
interface BellEventListener extends EventListener {
    //事件处理方法，听到铃声
    void heardBell(RingEvent rie);

}

//具体观察者类：老师事件监听器
class TeachEventListener implements BellEventListener {

    @Override
    public void heardBell(RingEvent rie) {
        if (rie.isSound()) {
            System.out.println("老师上课了...");
        } else {
            System.out.println("老师下课了...");
        }
    }
}


//具体观察者类：学生事件监听器
class StuEventListener implements BellEventListener {

    @Override
    public void heardBell(RingEvent rie) {
        if (rie.isSound()) {
            System.out.println("同学们，上课了...");
        } else {
            System.out.println("同学们，上课下...");
        }
    }
}
