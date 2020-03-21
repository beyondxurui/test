package com.jlxu.demo.io;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

/**
 * 功能: 细说Java IO相关
 * https://www.cnblogs.com/zhuYears/archive/2013/04/10/2993292.html   细说Java IO相关
 * https://zhuanlan.zhihu.com/p/24457041  适配器和装饰者（输入输出案例    ）
 * https://blog.csdn.net/csdn_kou/article/details/82910753   TCP/IP协议
 * https://blog.csdn.net/qq_35644234/article/details/68951041  TCP/IP协议
 * 创建时间：2020年03月19日
 * 文件名称：IOTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/19 15:25
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class IOTest {
    //字节流和字符流转换 （如：InputStreamReader）
    @Test
    public void test() {
        char[] charArray = new char[1];
        StringBuilder builder = new StringBuilder();
        try {
//            FileReader reader = new FileReader("test.txt");//java.io.FileNotFoundException: test.txt (系统找不到指定的文件。)
            //绝对路径
            FileReader reader = new FileReader("J:\\java\\idea_workspace\\springboot_demo\\src\\test\\resources\\test.txt");//
            //J:\java\idea_workspace\springboot_demo\src\test\resources   TODO:\变\\
            //相对路径
            // InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("file/大帐银行间.xlsx");


            while (reader.read(charArray) != -1) {//TODO:将字节读入数组，阻塞
                builder.append(charArray);
            }
            log.info("\n" + "编码：{}", reader.getEncoding());//编码：UTF8
            log.info("\n" + "文件内容：{}", builder.toString());//文件内容：ddfl你好  TODO:没乱码，见思考题

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //ps:
        // 1）TODO:参考
        //char[] buf = new char[1024];//接收输入流里字符数据
        //int count = 0;//记录要读的字符流里有几个字符
        //try {
        //while ((count = sr.read(buf)) != -1) {//read方法要读到一个字符数组中去，read如果读完会返回一个-1表示文件读完.
        //                System.out.printf(new String(buf,0,count));//将字符数组中的第一个到第count个转换成字符串输出
        //}
        //2）char是字符  byte是字节
        //3）JAVA基础知识之StreamDecoder流   了解 https://blog.csdn.net/ai_bao_zi/article/details/81205286
        //作用： 具体栗子  字符流(FileReader)读取文件时，会调用InputStreamReader的reader方法，StreamDecoder正是完成字节到字符的解码的实现类
        //4）码表的含义
        //https://blog.csdn.net/weixin_42223833/article/details/93223603   参考：总结性的
        //https://www.cnblogs.com/houxt/p/11250878.html  //TODO:
        //5）思考：编辑器设置utf-8意味着什么？
        // 以案例以为栗子 ，FileReader采用utf-8编码，idea编辑器默认采用gbk，按理说应该乱码的，没乱码时因为，me设置（才看文件什么的都是解码操作）为utf-8
        // ps感觉Api会提供设置编码的方法的
        //所有的乱码都是编码和解码使用不同码表造成的
    }

    //乱码案例  和test是联系在一起的  ps:真好和参考地址相反，原因，见test中思考题
    @Test
    public void test2() {
        char[] charArray = new char[1];
        StringBuilder builder = new StringBuilder();
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream("J:\\java\\idea_workspace\\springboot_demo\\src\\test\\resources\\test.txt"), "GBK");
            while (reader.read(charArray) != -1) {//TODO:将字节读入数组，阻塞
                builder.append(charArray);
            }
            log.info("\n" + "编码：{}", reader.getEncoding());//编码：GBK
            log.info("\n" + "文件内容：{}", builder.toString());//文件内容：ddfl浣犲ソ
            System.out.println();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //参考地址一
    //概述
    //对于程序员来说，接触到io的机会还是比较少的，其中大多也是简单的上传下载、读写文件等简单运用，如果接触网络通信相关的应用，接触io、nio等比较多
    //Java传统的io是基于流的io，从jdk1.4开始提供基于块的io，即nio，会在后面的文章介绍。
    //流
    //流的概念可能比较抽象，可以想象一下水流的样子。io在本质上是单个字节的移动，而流可以说是字节移动的载体和方式，  知道载体的含义就很好理解了
    //一个流，必有源和目标
    //io分类
    //流向（输入，输出）  字节字符  缓冲流

    //ps:IO中流的输入和输入输出的理解  TODO:中心是程序
    //FileInputStream、FileReader   把文件写入内存作为输入流，实现对文件的读取操作   TODO:用输入流把文件读入内存  更好呢
    //FileOutputStream、FileWriter  把内存中的数据作为输出流写入文件，实现对文件的写操作   TODO:用输出流把内存中数据写到文件   更好呢
    //io包与设计模式


    //适配器和装饰者（输入输出案例
    @Test
    public void test3() {
        //输入与输出
        //前三个字节是一组，通过UTF-8（我们会在后续的课程中陆续介绍编码的知识）的解码，可以得到前三个字节代表的十进制数是28023，这刚好就是中文字符“海”字的 unicode 码。可见，直接的字节操作对非ascii的字符会比较麻烦。例如，程序读入一个名字，想判断这个名字的姓氏是否为李，如果是字节的操作，我们就得先把读到的这些字节，解码到 unicode，或者反过来，把“李”编码为UTF-8再进行比较。这显然太麻烦了，编解码这么机械的工作，
        // 干嘛不让机器替我们做呢？基于这个想法，Java引入了一个可以把字节流转成字符流的适配器——InputStreamReader
        //上面这段 TODO:后续补充
        //适配器模式
        //装饰者模式
        //可以看到，BufferedReader本身就是一个Reader，因为它继承自Reader，同时，还有一个名为 in 的成员变量，也是Reader类型的，然后还开辟了一个数组。实际上，这个数组是为了做缓存的，我们可以一次从 in 这个成员对象中读取多个字符存入 cb 中。当真正调用BufferedReader 的 read 方法的时候，就直接从 cb 中读取了，提高了读取的性能。也就是说BufferedReader所提供的 read 方法经过了 cb 这个缓存的加速，其性能会高于直接从 in 这个对象去读取。这是一种增强普通的 Reader 对象的技术。
        //注意一点，BufferedReader在对一个Reader的对象做增强的时候，只要求了对象是Reader类型的，并没有要求对象是具体哪个类型。这个in对象可以是SocketReader，也可以是FileReader。

        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
    }
}
