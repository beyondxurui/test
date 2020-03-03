package com.jlxu.demo.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能:jvm内存模型 常量测试，字符串定义测试 String 的不可变性测试  String对象创建方式 https://blog.csdn.net/justloveyou_/article/details/52556427
 * 创建时间：2020年03月02日
 * 文件名称：SpringTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/2 22:18
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class SpringTest {

    //常量测试
    @Test
    public void constantTest() {
        String s = "xx";//因为String本生被final修饰   那么字符串是常量对不对  肯定对啊  都被final修饰，你港呢
        int i = 1;//根据总结：注意整性字面量也是常量
        long l = 3L;
        short t = 21;
        Human human = new Human();
        Human human1 = human;//通过结构可以知道这段代码的含义是  human1就是human，指向的内存地址是是同一个，只不过引用名称/变量名不一样
        log.info("human===> {}", human);//加了@Data human== human1 ===> true  没加 com.jlxu.demo.spring.Human@290222c1
        log.info("human== human1 ===> {}", human1 == human);//human== human1 ===> true
        human.setAge(23);
        human.setName("dd");
        log.info("human== human1 ======> {}", human1 == human);//human== human1 ======> true


        //总结：
        //常量池主要存放两大常量：字面量(Literal) 和 符号引用(Symbolic References)
        //字面量主要包括字符串字面量，整型字面量 和 声明为final的常量值等；
        // 而符号引用则属于编译原理方面的概念，包括了下面三类常量：类和接口的全限定名； 字段的名称和描述符； 方法的名称和描述符；

        //对总结的分析
        //内存地址不变,值可以改变的东西称为变量，换句话说，在内存地址不变的前提下内存的内容是可变的
        //若内存地址不变, 则值也不可以改变的东西称为常量（基本类型）    final int i = 5; 基本类型被final修饰后值不可变
    }

    //字符串定义测试
    @Test
    public void stringTest() {
        String s = " abc";
        String s2 = s.substring(1);
        log.info("s ===>{}{}", s.length(), s);//s ===>4 abc
        log.info("s2 ===>{}{}", s2.length(), s2);//s2 ===>3abc

        String s3 = "eerrr";
        int i = 2;
        changeString(s3, i);
        log.info("s3 ===> {}", s3);//s3 =====> eerrr
        log.info("i ===> {}", i);//i ===> 2
//        String s5;
//        log.info("s5 ===> {}", s5);//Variable 's5' might not have been initialized:使用前引用类型需要初始化
        String s5 = null;
        log.info("s5 ===> {}", s5);//s5 ===> null

        //String 的不可变性导致其像八种基本类型一样，比如，作为方法参数时，像基本类型的传值效果一样  TODO:和想不一样（不管是基本类型，还是String）
//        String 的实例是一个对象。因为对象的默认值是null
    }

    public static void changeString(String s3, int i) {
        String s4 = s3;//String是常量  TODO：没有地址值（和其他引用类型不一样）一说？ 错误
        s3 += "dfgg";
        i += i;
        log.info("s4 =====> {}", s4);//s3 =====> eerrr
        log.info("s3 =====> {}", s3);//s3 =====> eerrrdfgg
        log.info("i =====> {}", i);//i =====> 4
    }

    //String 的不可变性测试
    @Test
    public void stringTest2() {
        //1、什么是不可变对象
//        如果一个对象，在它创建完成之后，不能再改变它的状态，那么这个对象就是不可变的。不能改变状态指的是不能改变对象内的成员变量，包括：
//        基本数据类型的值不能改变;
//        引用类型的变量不能指向其他的对象;
//        引用类型指向的对象的状态也不能改变;
//        除此之外，还应具有以下特点：
//        除了构造函数之外，不应该有其它任何函数（至少是任何public函数）修改任何成员变量;
//        任何使成员变量获得新值的函数都应该将新的值保存在新的对象中，而保持原来的对象不被修改。

        //2、引用和对象区别？  最基本的
        String s = "ee";
        log.info(s);//ee   s是引用
        s = "244";
        log.info(s);//244  s是引用

        //3、字符串“对象”是不可变对象吗？  哪些方法返回的是新的字符串对象
        String s2 = "ee";
        log.info(s2);//ee
        String replace = s2.replace("e", "E");
        log.info(replace);//EE
        log.info(s2);//ee
        //4、字符串“对象”真的不可变吗？  反射
    }

    //String对象创建方式
    @Test
    public void stringTest3() {
        //一、JVM会自动根据字符串常量池中字符串的实际情况来决定是否创建新对象 (要么不创建，要么创建一个对象，关键要看常量池中有没有)
        String s = "2334";//等价于下面两行  ====> 走debug看看
//        char data[] = {'1', '2', '3', '4', '5'};
//        String str = new String(data);
        //注：该种方式先在栈中创建一个对String类的对象引用变量s，然后去查找 “abc”是否被保存在字符串常量池中。
        // 若”abc”已经被保存在字符串常量池中，则在字符串常量池中找到值为”abc”的对象，然后将s 指向这个对象;
        // 否则，在 堆 中创建char数组 data，然后在 堆 中创建一个String对象object，它由 data 数组支持，紧接着这个String对象 object 被存放进字符串常量池，最后将 s 指向这个对象。
        //案例
        String s0 = "dkkd";
        String s1 = "dkkd";
        String s2 = "dk" + "kd";
        log.info("s0=s1 ===> {}", s0 == s1);//s0=s1 ===> true
        log.info("s0=s1 ===> {}", s0 == s2);//s0=s2 ===> true
        //案例解释（注意上面的否则）
        //执行第 1 行代码时，“kvill” 入池并被 s0 指向；
        // 执行第 2 行代码时，s1 从常量池查询到” kvill” 对象并直接指向它；所以，s0 和 s1 指向同一对象。
        // 由于 ”kv” 和 ”ill” 都是字符串字面值，所以 s2 在编译期由编译器直接解析为 “kvill”，所以 s2 也是常量池中”kvill”的一个引用。 所以，我们得出 s0==s1==s2;
    }

    //String对象创建方式
    @Test
    public void stringTest4() {

        //二
        //通过 new 创建字符串对象 : 一概在堆中创建新对象，无论字符串字面值是否相等 (要么创建一个，要么创建两个对象，关键要看常量池中有没有)
        String s6 = new String("abc");//debug ===>  先走String(char value[])  再走String(String original)

        //String s7 = "abc";
        //String s8 = new String(s7);
        //所以，通过 new 操作产生一个字符串（“abc”）时，会先去常量池中查找是否有“abc”对象，
        // 如果没有，则创建一个此字符串对象并放入常量池中。然后，在堆中再创建“abc”对象，并返回该对象的地址。
        // 所以，对于 String str=new String(“abc”)：如果常量池中原来没有”abc”，则会产生两个对象（一个在常量池中，一个在堆中）；否则，产生一个对象。


        //案例
        String s0 = "kvill";
        String s1 = new String("kvill");
        String s2 = "kv" + new String("ill");

        String s = "ill";
        String s3 = "kv" + s;


        System.out.println(s0 == s1);       // false
        System.out.println(s0 == s2);       // false
        System.out.println(s1 == s2);       // false
        System.out.println(s0 == s3);       // false
        System.out.println(s1 == s3);       // false
        System.out.println(s2 == s3);       // false
        //例子中，s0 还是常量池中”kvill”的引用，s1 指向运行时创建的新对象”kvill”，二者指向不同的对象。
        // 对于s2，因为后半部分是 new String(“ill”)，所以无法在编译期确定，在运行期会 new 一个 StringBuilder 对象， 并由 StringBuilder 的 append 方法连接并调用其 toString 方法返回一个新的 “kvill” 对象。
        // 此外，s3 的情形与 s2 一样，均含有编译期无法确定的元素。因此，以上四个 “kvill” 对象互不相同。
        // StringBuilder 的 toString 为：
        // public String toString() {
        //    return new String(value, 0, count);   // new 的方式创建字符串
        //    }
    }

    @Test
    public void sprintTest5() {

        //博主jdk版本是1.6
        // 所创建的对象在大多数情形下会与源字符串 original 共享 char数组 ==>走debug  走了debug，懵啊，TODO：后面有时间再走（jdk版本不一样，源码也不一样）
        //String s6 = "23445";
        //String s7 = s6.substring(3);
        //String s8 = new String(s7);
    }

}
