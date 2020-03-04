package com.jlxu.demo.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能:Spring下篇学习
 * 创建时间：2020年03月02日
 * 文件名称：SpringTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/2 22:18
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class SpringLowerTest {

    //字符串常量池
    @Test
    public void springTest() {
        //一、字符串池
        //字符串的分配，和其他的对象分配一样，耗费高昂的时间与空间代价。
        // JVM为了提高“性能"和"减少内存开销"，在实例化字符串字面值的时候进行了一些优化。
        // 为了减少在JVM中创建的字符串的数量，字符串类维护了一个字符串常量池，每当以字面值形式创建一个字符串时，JVM会首先检查字符串常量池：
        String s = "34";
        log.info("s ===> {}", s);//34
        String s2 = "34";
        log.info("s ===> {}", s2);//34
        s += s;
        log.info("s ===> {}", s);//3434
        log.info("s=s2 ===> {}", s == s2);//true
        //问题:log.info("s ===> {}", s)或log.info("s ===> {}", s2)和log.info("s ===> {}", s)怎么理解下面这句话
        //1、怎么理解：Java能够进行这样的优化是因为”字符串是不可 变的“，可以不用担心数据冲突进行共享。
        //答：在Spring的上篇中 引用和变量 ps：还是没区别开引用的对象


        //二、手动入池
        //一个初始为空的字符串池，它由类 String 私有地维护。
        // 当调用 intern 方法时，如果池已经包含一个等于此 String 对象的字符串（用 equals(Object) 方法确定），则返回池中的字符串。
        // 否则，将此 String 对象添加到池中，并返回此 String 对象的引用。特别地，手动入池遵循以下规则：
        //对于任意两个字符串 s 和 t ，当且仅当 s.equals(t) 为 true 时，s.intern() == t.intern() 才为 true 。
        String s4 = "dfg";
        String s5 = new String("dfg");
        String s6 = s5.intern();//手动入池
        log.info("s4==s5 ===> {}", s4 == s5);//false
        log.info("s4==s6 ===> {}", s4 == s6);//true
    }

    //案例
    @Test
    public void springTest2() {
        //情景一：字符串常量池
        //Java虚拟机(JVM)中存在着一个字符串常量池，其中保存着很多"String对象"，并且这些"String对象"可以被"共享使用"，因此"提高了效率"。
        //之所以字符串具有"字符串常量池"，是因为"String对象"是不可变的，"因此可以被共享"。
        // 字符串常量池由String类维护，我们可以通过intern()方法使字符串池手动入池。
        String s1 = "abc";
        //↑ 在字符串池创建了一个对象
        String s2 = "abc";
        //↑ 字符串pool已经存在对象“abc”(共享),所以创建0个对象，累计创建一个对象

        //情景二：关于new String(“…”)   上篇已经总结了

        //情景三：字符串连接符“+”
        String s3 = "dd";
        String s4 = "fg";
        String s5 = s3 + s4;
        String s6 = "ddfg";
        log.info("s5==s6 ===> {}", s5 == s6);//false
        //第三行代码(s3 + s4)实质上会被分解成五个步骤，分别是：
        //(1). 调用 String 类的静态方法 String.valueOf() 将 s3 转换为"字符串表示"；  注：不是字符串对象
        //(2). JVM 在堆中创建一个 StringBuilder对象，同时用s3指向转换后的字符串对象进行初始化；　
        //(3). 调用StringBuilder对象的append方法完成与s4所指向的字符串对象的合并；
        //(4). 调用 StringBuilder 的 toString() 方法在堆中创建一个 String对象；
        //(5). 将刚刚生成的String对象的堆地址存赋给局部变量引用s5。
        //**：内存中实际上会存在五个字符串对象： “三个”在字符串常量池中的String对象、一个在堆中的String对象和一个在堆中的StringBuilder对象。

        //情景四：字符串的编译期优化
        String s8 = "klop";
        String s9 = "kl" + "op";
        log.info("s8 == s9 ===> {}", s8 == s9);//true

        final String s11 = "ab";
        String s12 = "abcd";
        String s13 = s11 + "cd";
        log.info("s12 == s13 ===> {}", s12 == s13);//true

        String s14 = "ac";
        String s16 = "acbd";
        String s17 = s14 + "bd";
        log.info("s16 == s17 ===> {}", s16 == s17);//false

        //“常量+字面值”的组合，其值在编译的时候就能够被确定了。在这里，s9 和 s13 的值在编译时就可以被确定，因此它们分别等价于： String s9 = “klop”; 和  String s13 = “abcd”;
        //Java 编译器对于含有 “String引用”的组合，则在运行期会产生新的对象 (通过调用StringBuilder类的toString()方法)，因此这个对象存储在堆中。
    }

    //三大字符串类 ： String、StringBuilder 和 StringBuffer
    public void springTest3() {
        //1、
        //String 类型 和 StringBuilder 类型的主要性能区别在于 String 是不可变的对象
        //对于经常改变内容的字符串，最好不要声明为 String 类型。但如果我们使用的是 StringBuilder 类，那么情形就不一样了。因为，我们的每次修改都是针对 StringBuilder 对象本身的，而不会像对String操作那样去生成新的对象并重新给变量引用赋值。所以，在一般情况下，推荐使用 StringBuilder ，特别是字符串对象经常改变的情况下

        //2.StringBuffer 与 StringBuilder
        //JDK的实现中 StringBuffer 与 StringBuilder 都继承自 AbstractStringBuilder。
        // AbstractStringBuilder的实现原理为：AbstractStringBuilder中采用一个 char数组 来保存需要append的字符串，char数组有一个初始大小，
        // 当append的字符串长度超过当前char数组容量时，则对char数组进行动态扩展，即重新申请一段更大的内存空间，
        // 然后将当前char数组拷贝到新的位置，因为重新分配内存并拷贝的开销比较大，
        // 所以每次重新申请内存空间都是采用申请大于当前需要的内存空间的方式，这里是 2 倍。
        //f>d StringBuffer（jdk1.0出现） StringBuilder（jdk1.5出现）  早（安全）  晚（不安全）

        //3.实例
        //2).字符串连接符的本质
        String s = null;
        for (int i = 0; i < 100; i++) {
            s += "a";//反编译（class文件，javap） s = (new StringBuilder(String.valueOf(s))).append("a").toString();
        }
        //，每做一次 字符串连接操作 “+” 就产生一个 StringBuilder 对象，然后 append 后就扔掉。
        // 下次循环再到达时，再重新 new 一个 StringBuilder 对象，然后 append 字符串，如此循环直至结束。
        // 事实上，如果我们直接采用 StringBuilder 对象进行 append 的话，
        // 我们可以节省 N - 1 次创建和销毁对象的时间。所以，对于在循环中要进行字符串连接的应用，一般都是用StringBulider对象来进行append操作。
        //ps:实践中一定要注意
    }

    //字符串与正则表达式：匹配、替换和验证
    @Test
    public void springTest4() {
        String regax = "a\\b";//4(1+1)
        log.info(regax.split("\\\\")[0]);
        log.info(regax.split("\\\\")[1]);


        Pattern pattern = Pattern.compile(".\\\\.");
        Matcher matcher = pattern.matcher("a\\b");

        log.info("m {}", matcher.matches());
        log.info(matcher.group());

        //ps TODO:Java 内置了对正则表达式的支持，其相关的类库在 java.util.regex 包下APi简单学习下吧

    }

    //String 与 (深)克隆
    @Test
    public void springTest5() throws CloneNotSupportedException {
        //（1）、克隆的定义与意义  克隆就是制造一个对象的副本。一般地，根据所要克隆的对象的成员变量中是否含有引用类型，可以将克隆分为两种：浅克隆(Shallow Clone) 和 深克隆(Deep Clone)，默认情况下使用Object中的clone方法进行克隆就是浅克隆，即完成对象域对域的拷贝。
        //（2）、Cloneable 接口  使用Object的clone方法要实现的接口，不然报异常
        //（3）、Clone & Copy  Copy：都会改变   Clone：域的域是基本类型/String（不可变对象）可以不变，如果是其他引用类型不行
        //（4）、3、Shallow Clone & Deep Clone  我们就需要进行 Deep Clone（手动） 了，以便对那些引用类型的域进行特殊的处理
        //例子
        Manger manger = new Manger("lbj", 23, new Date(), "NEU");
        log.info("manger对象：{}", manger.toString());//Manger{edu='NEU',name='lbj, salary=23.0, hireDay=Wed Mar 04 12:03:55 CST 2020}
        Manger cloned = (Manger) manger.clone();
        log.info("manger的克隆对象：{}", cloned.toString());//Manger{edu='NEU',name='lbj, salary=23.0, hireDay=Wed Mar 04 12:03:55 CST 2020}
        log.info("manger的克隆对象和manger对象是否相等：{}", cloned.equals(manger));//true

        manger.setEdu("TJU");
        log.info("修改后manger对象：{}", manger.toString());//Manger{edu='TJU',name='lbj, salary=23.0, hireDay=Wed Mar 04 12:03:55 CST 2020}
        log.info("原manger的克隆对象：{}", cloned.toString());//Manger{edu='NEU',name='lbj, salary=23.0, hireDay=Wed Mar 04 12:03:55 CST 2020}
        log.info("原manger的克隆对象和修改后manger对象是否相等：{}", cloned.equals(manger));//false
        //ps：达到的效果===>原对象和副本独立
        //明白 String 在克隆中的特殊性
        //String 在克隆时只是克隆了它的引用。
        //奇怪的是，在修改克隆后的 String 对象时，其原来的对象并未改变。原因是：String是在内存中不可以被改变的对象。
        // 虽然在克隆时，源对象和克隆对象都指向了同一个String对象，但当其中一个对象修改这个String对象的时候，会新分配一块内存用来保存修改后的String对象并将其引用指向新的String对象，
        // 而原来的String对象因为还存在指向它的引用，所以不会被回收。
        // 这样，对于String而言，虽然是复制的引用，但是当修改值的时候，并不会改变被复制对象的值。
        // 所以在使用克隆时，我们可以将 String类型 视为与基本类型，只需浅克隆即可。

        //十. String 总结
        //(1). 使用字面值形式创建字符串时，不一定会创建对象，但其引用一定指向位于字符串常量池的某个对象；
        //(2). 使用 new String(“…”)方式创建字符串时，一定会创建对象，甚至可能会同时创建两个对象（一个位于字符串常量池中，一个位于堆中）；
        //(3). String 对象是不可变的，对String 对象的任何改变都会导致一个新的 String 对象的产生，而不会影响到原String 对象；
        //(4). StringBuilder 与 StringBuffer 具有共同的父类，具有相同的API，分别适用于单线程和多线程环境下。特别地，在单线程环境下，StringBuilder 是 StringBuffer 的替代品，前者效率相对较高；
        //(5). 字符串比较时用的什么方法，内部实现如何？
        //使用equals方法 ： 先比较引用是否相同(是否是同一对象)，再检查是否为同一类型（str instanceof String）， 最后比较内容是否一致（String 的各个成员变量的值或内容是否相同）。
        // “这也同样适用于诸如 Integer 等的八种包装器类”。  ===>看源码可知
        //ps:Object的equals比较的地址  ===>看源码可知

    }
}
