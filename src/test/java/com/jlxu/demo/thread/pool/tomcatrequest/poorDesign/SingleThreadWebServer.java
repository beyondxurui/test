package com.jlxu.demo.thread.pool.tomcatrequest.poorDesign;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 功能：
 * 创建时间：2020年03月17日
 * 文件名称：SingleThreadWebServer
 * 版本：1.0.0
 * 最后修改时间：2020/3/17 23:55
 *
 * @auther jlxu
 */
public class SingleThreadWebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket();   //ServerSocket? TODO:等待请求通过网络进入
        while (true) {
            Socket connection = socket.accept();
            handleRequest(connection);
        }

    }

    private static void handleRequest(Socket connection) {
        //request-handing logic here
    }
    //ps
    //socket.accept()方法阻塞
    //如果这个请求一直不结束，新的请求就一直不会被处理，在用户看来服务器就是没有响应。
    //TODO:作为一个高性能的服务器，Tomcat肯定要解决一个问题：要怎样实现当请求正在处理的同时，其他请求也可以同时被处理呢？===>多线程
    //当时之所以用单线程，一是硬件资源的限制，二是那时我们业务的请求不多，一天也不超过100条请求，而且都是非常轻量级的请求
    //TODO:硬件资源?  https://zhidao.baidu.com/question/588662923.html(百度百科-硬件资源分配)

}
