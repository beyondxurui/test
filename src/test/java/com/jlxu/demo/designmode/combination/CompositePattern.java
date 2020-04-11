package com.jlxu.demo.designmode.combination;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：组合模式
 * 创建时间：2020年04月11日
 * 文件名称：CompositePattern
 * 版本：1.0.0
 * 最后修改时间：2020/4/11 17:42
 *
 * @auther jlxu
 */
public class CompositePattern {
    public static void main(String[] args) {

        Component cp = new Composite();
        Component cp2 = new Composite();
        Component leaf = new Leaf("1");
        Component leaf2 = new Leaf("2");
        Component leaf3 = new Leaf("3");
        cp.add(cp2);
        cp.add(leaf);
        cp2.add(leaf2);
        cp2.add(leaf3);
        cp.operation();


        //子叶2被访问
        //子叶3被访问
        //子叶1被访问

    }
}

//抽象构件
interface Component {
    void add(Component component);

    void remove(Component component);

    Component getChild(int index);

    void operation();
}

//树叶构件
class Leaf implements Component {
    private String name;

    public Leaf(String name) {
        this.name = name;
    }

    @Override
    public void add(Component component) {

    }

    @Override
    public void remove(Component component) {

    }

    @Override
    public Component getChild(int index) {
        return null;
    }

    @Override
    public void operation() {
        System.out.println("子叶" + name + "被访问");
    }
}

//树枝构件
class Composite implements Component {
    private List<Component> components = new ArrayList<>();

    @Override
    public void add(Component component) {
        components.add(component);
    }

    @Override
    public void remove(Component component) {
        components.remove(component);
    }

    @Override
    public Component getChild(int index) {
        return components.get(index);
    }

    @Override
    public void operation() {
        for (Component component : components) {
            component.operation();
        }
    }
}
