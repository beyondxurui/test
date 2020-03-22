package com.jlxu.demo.thread.tomcatrequest.webServer.poorDesign;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 功能：如何像tomcat处理请求
 * 创建时间：2020年03月17日
 * 文件名称：TomcatHolderRequest
 * 版本：1.0.0
 * 最后修改时间：2020/3/17 0:04
 *
 * @auther jlxu
 */
@Slf4j
public class ThreadPerTaskWevServer {//per :每，按照，经

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            final Socket connection = socket.accept();
            Runnable task = () -> handleRequest(connection);
            task.run();
            //TODO:Lambda表达式  https://www.jianshu.com/p/f02b04344365
            //通过Runnable创建线程如何执行run方法的两大方式   1）作为Thread类构造的参数 调用start方法启动  2）Runnable实例调用run方法
            //TODO:回顾run和start的区别
        }
    }

    private static void handleRequest(Socket connection) {
        // request-handling logic here
    }
    //问题
    //性能方面
    //1）每个任务创建一条线程’这个方案好不好（暂先不说）    TODO：该写法也不能说是多线程（因为还是串行的），优化==>线程实例run方法换成start
    //代码方面
    //1）老马之前写的单线程服务器性能不高，但还是有适用场景的，比如在那些请求量很少而且请求都是轻量级的场合，就可以使用单线程，照你这样写，难道要我们跟客户说，‘单线程的，执行SingleThreadWebServer，多线程的，执行ThreadPerTaskWebServer’
    //2）重复的代码

    //2）3）解决
    //if else  ？设计模式白学了
    //TODO:经常变动的地方，要怎么样？”
    //“啊啊，经常变动的地方，要抽取出来。可是这里要使用什么设计模式呢？”
    //“小子，好好想想，我们这里是想将请求的产生和请求的处理进行解耦，我们Web服务器只负责接收请求，至于按照什么方式处理请求，是单线程还是多线程，这个我们交给别的类去处理”
    //“产生和处理？”，大雄若有所思，“啊，是生产者-消费者模式！”
    //“没错，也可以说是命令模式。”
}
