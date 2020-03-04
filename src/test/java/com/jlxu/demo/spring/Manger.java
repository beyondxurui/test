package com.jlxu.demo.spring;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 功能
 * 版本权限：浙江省公众信息产业有限公司智慧旅游（事业）部
 * 创建时间：2020年03月04日
 * 文件名称：Manger
 * 版本：1.0.0
 * 最后修改时间：2020/3/4 11:43
 *
 * @auther ${许金李}
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Manger extends Employee {

    private String edu;


    public Manger(String name, double salary, Date hireDay, String edu) {
        super(name, salary, hireDay);
        this.edu = edu;
    }

    //情况一：Manger(edu=NEU)重写toString没包含父类中的属性 @Data注解重写toString默认不包含(编辑器自动生成也不包含)  TODO:@Data
    @Override
    public String toString() {//Manger{edu='TJU',name='lbj, salary=23.0, hireDay=Wed Mar 04 12:03:55 CST 2020}
        return "Manger{" +
                "edu='" + edu + '\'' +
                ",name='" + getName() +
                ", salary=" + getSalary() +
                ", hireDay=" + getHireDay() +
                '}';

    }
}
