package com.jlxu.demo.genericity;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * 功能：泛型
 * 创建时间：2020年03月30日
 * 文件名称：GenericityTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/30 12:38
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class GenericityTest {
    @Test
    public void genericityTest() {
        //一、泛型的概念 TODO
        //泛型，即“参数化类型”。一提到参数，最熟悉的就是定义方法时有形参，然后调用此方法时传递实参。
        // 那么参数化类型怎么理解呢？
        // 顾名思义，就是将类型由原来的具体的类型参数化，
        // 类似于方法中的变量参数，此时类型也定义成参数形式（可以称之为类型形参），然后在使用/调用时传入具体的类型（类型实参）。
        // 泛型的本质是为了参数化类型（在不创建新的类型的情况下，通过泛型指定的不同类型   来控制形参具体限制的类型）。
        // 也就是说在泛型使用过程中，操作的数据类型被指定为一个参数，这种参数类型可以用在类、接口和方法中，分别被称为泛型类、泛型接口、泛型方法。
        //二、栗子
        List list = new ArrayList(2);
        list.add("ddl");
        list.add(333);
//        if (list!=null)
        for (int i = 0; i < list.size(); i++) {
            String item = (String) list.get(i);
            log.info("泛型测试  item===>{}", item);//java.lang.ClassCastException
            //TODO：为了解决这样的类似的问题 （编译阶段）泛型应用而生
        }
        //第一行声明初始化list的代码更改一下，编译器会在编译阶段就能够帮我们发现类似这样的问题
        List<String> list1 = new ArrayList<>();
        //List<T>  list2=new ArrayList<T>();    哈哈想多了

    }

    @Test
    public void test2() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //三、特性
        List<String> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        Class aClass = list2.getClass();
        Class aClass2 = list3.getClass();
        if (aClass.equals(aClass2))
            log.info("泛型的类型 相等");

        //通过上面的例子可以证明，在编译之后程序会采取去泛型化的措施。也就是说Java中的泛型，只在编译阶段有效。在编译过程中，正确检验泛型结果后，会将泛型的相关信息擦出，
        // 并且在对象进入和离开方法的边界处添加类型检查和类型转换的方法。也就是说，泛型信息不会进入到运行时阶段。

        //四泛型的使用
        //泛型有三种使用方式，分别为：泛型类、泛型接口、泛型方法
        //1） 泛型类
        //泛型类型用于类的定义中，被称为泛型类。通过泛型可以完成对一组类的操作对外开放相同的接口。
        // 最典型的就是各种容器类，如：List、Set、Map。
        //泛型类型的基本写法
        //class 类名称 <泛型标识：可以随便写任意标识号，标识指定的泛型的类型>{
        //  private 泛型标识 /*（成员变量类型）*/ var;
        //  .....
        //
        //  }
        //}
        //最简单的泛型
        /**
         * @see com.jlxu.demo.genericity.Generic
         */
        //实例化泛型类
        //1：指定泛型的类型
        Generic<String> g = new Generic("dd");  //同  Generic<String> g2 = new Generic<String>("dd");
        String key = g.getKey();
        //2：泛型类型实参一定要传吗
        //在使用泛型的时候如果传入泛型实参，则会根据传入的泛型实参做相应的限制，此时泛型才会起到本应起到的限制作用
        Generic g2 = new Generic("ss");
        Object key2 = g2.getKey();
        //3：泛型的类型参数只能是类类型（包含自定义类型），不能是基本类型
        //传入的实参类型需与泛型的类型参数类型相同，即为Integer.
        //Generic<int>   Type argument cannot be of primitive type
        //传入的实参类型需与泛型的类型参数类型相同，即为String===>"ss"
        Generic<String> g4 = new Generic<>("ss");
        log.info("key is : {}", g4.getKey());

        //ps 几个概念
        //泛型                    这里指的是      E
        //泛型的类型参数           这里指的是      实例化泛型类要传入的泛型类型  和下面一样
        //类型参数                这里指的是      String         和上面一样
        //实参类型                这里指的是      "ss"

        //对确切的泛型类型使用instanceof操作。如下面的操作是非法的
        //if (g4 instanceof Generic<Number>(23))

        //2） 泛型接口
        /**
         * @see com.jlxu.demo.genericity.GenericInterface
         * @see com.jlxu.demo.genericity.FruitGeneric
         * @see com.jlxu.demo.genericity.FruitGeneric2
         */

        //3）泛型通配符
        //我们知道Integer是Number的一个子类，同时在特性章节中我们也验证过Generic<Integer>与Generic<Number>实际上是相同的一种基本类型。
        // 那么问题来了，在使用Generic<Number>作为形参的方法中，能否使用Generic<Integer>的实例传入呢？
        // 在逻辑上类似于Generic<Number>和Generic<Integer>是否可以看成具有父子关系的泛型类型呢？
        Generic<Number> gNumber = new Generic<>(12);
        Generic<Integer> gInteger = new Generic<>(12);

        showKeyValue(gNumber);
        //showKeyValue(gInteger);//in GenericityTest cannot be applied
        //由此可以看出:同一种泛型可以对应多个版本（因为参数类型是不确定的），不同版本的泛型类实例是不兼容的
        //如何解决这个问题呢？
        showKeyValue2(gNumber);
        showKeyValue2(gInteger);

        //4）泛型方法
        //泛型类中的成员方法也都使用了泛型和泛型方法的区别？ com.jlxu.demo.genericity.GenericityTest
//        Class aClass1 = Class.forName("com.jlxu.demo.genericity.GenericityTest");
        Class<?> aClass1 = Class.forName("com.jlxu.demo.genericity.GenericityTest");
        Object o = genericMethod(aClass1);

        //5）泛型方法的使用

        //6）类中的泛型方法
        /**
         * @see  com.jlxu.demo.genericity.GenericFruit
         */

        //7）泛型方法和可变参数
        printMsg("ss", "dg", "dg", "dgg");   //可变参数方式一
        printMsg(Arrays.asList("dd"));              //方法二
        printMsg(new String[]{"dd", "ddg", "dg"});  //方式三

        //8）静态方法与泛型
        //静态方法无法访问类上定义的泛型；如果静态方法操作的引用数据类型不确定的时候，必须要将泛型定义在方法上。

        //9）泛型方法总结
        //TODO:后续实践体验体验
        //无论何时，如果你能做到，你就该尽量使用泛型方法。也就是说，如果使用泛型方法将整个类泛型化，那么就应该使用泛型方法。
        // 另外对于一个static的方法而已，无法访问泛型类型的参数。所以如果static方法要使用泛型能力，就必须使其成为泛型方法。

        //10）泛型上下边界
        //在使用泛型的时候，我们还可以为传入的泛型类型实参进行上下边界的限制，如：类型实参只准传入某种类型的父类或某种类型的子类
        Generic<Integer> g5 = new Generic<>(23);
        Generic<Boolean> g6 = new Generic<>(true);
        Generic<Double> g7 = new Generic<>(2.3);
        Generic<String> g8 = new Generic<>("dd");

        showKeyValue3(g5);
//        showKeyValue3(g6);//in GenericityTest cannot be applied to
        showKeyValue3(g7);
//        showKeyValue3(g8);//in GenericityTest cannot be applied to

        /**
         * @see
         */

        //Generic2<String> g9=new Generic2<>("dd");//Type parameter 'java.lang.String' is not within its bound; should extend 'java.lang.Number
        //类型参数

        //11）关于泛型数组要提一下
        //TODO:不能创建一个确切的泛型类型的数组
        //List<String>[] ls11 = new ArrayList<String>[10];//不可以
        List<?>[] ls22 = new ArrayList<?>[10];//可以

        //正确的栗子
        //数组的类型不可以是类型变量(泛型类型的变量)，除非是采用通配符的方式
        List<?>[] lists = new List[10];
        Object o2 = lists;
        Object[] oa2 = (Object[]) o2;
        List<Integer> li2 = Arrays.asList(new Integer(3));
        oa2[1] = li2;
        Integer i2 = (Integer) lists[1].get(0);
        log.info("i2 is : {}", i2);


        //ps:变量的赋值

    }

    //--------------泛型通配符---------------
    private void showKeyValue(Generic<Number> obj) {//不是泛型方法
        log.info("key is : {}", obj.getKey());
    }

    private void showKeyValue2(Generic<?> obj) {//不是泛型方法
        log.info("key is : {}", obj.getKey());
    }
    //--------------泛型通配符---------------

    //--------------泛型方法---------------

    /**
     * 泛型方法的基本介绍
     *
     * @param tClass 传入的泛型实参
     * @param <T>    TODO:非常重要，可以理解为声明此方法为泛型方法。
     * @return T 返回值为T类型
     * 说明：
     * 1）只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是泛型方法。
     * 2）<T>表明该方法将使用泛型类型T，此时才可以在方法中使用泛型类型T。
     */
    private <T> T genericMethod(Class<T> tClass) throws IllegalAccessException, InstantiationException {
        T instance = tClass.newInstance();
        return instance;
    }

    //TODO:但是只声明了泛型类型T，并未声明泛型类型E，因此编译器并不知道该如何处理E这个类型。
    //private <T> T showKeyName(Generic<E> contation) {
    //    return null;
    //}

    //T类型未定义
    //private void   showKey(T t){
    //}

    //--------------泛型方法---------------

    //--------------方法方法和可变参数--------------------
    private <T> void printMsg(T... agrs) {
        for (T t : agrs) {
            log.info("t is : {}", t);
        }
    }
    //--------------方法方法和可变参数--------------------

    //--------------泛型上下边界--------------------
    private void showKeyValue3(Generic<? extends Number> obj) {
        log.info("key is : {}", obj.getKey());
    }
    //--------------泛型上下边界--------------------
}
