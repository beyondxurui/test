package com.jlxu.demo.spring;

import java.util.Date;
import java.util.Objects;

/**
 * 功能
 * 创建时间：2020年03月04日
 * 文件名称：Employee
 * 版本：1.0.0
 * 最后修改时间：2020/3/4 11:17
 */
public class Employee implements Cloneable {

    private String name;
    private double salary;
    private Date hireDay;

    public Employee(String name, double salary, Date hireDay) {
        this.name = name;
        this.salary = salary;
        this.hireDay = hireDay;
    }

    @Override
    public Object clone() throws CloneNotSupportedException { //clone是Object的方法，但是要实现Cloneable接口
        Employee cloned = (Employee) super.clone();
        cloned.hireDay = (Date) hireDay.clone();
        return cloned;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getHireDay() {
        return hireDay;
    }

    public void setHireDay(Date hireDay) {
        this.hireDay = hireDay;
    }


    @Override
    public boolean equals(Object o) {//TODO:简单了解下博主为什么那么写（源码可知）Objects.equals，
        // 所有实践中遇到比较适合可能为null的就可以用equals
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Double.compare(employee.getSalary(), getSalary()) == 0 &&
                Objects.equals(getName(), employee.getName()) &&
                Objects.equals(getHireDay(), employee.getHireDay());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSalary(), getHireDay());//TODO:同上，只不过
        //只不过博主的 long temp;
        //        temp = Double.doubleToLongBits(salary);
        //        result = prime * result + (int) (temp ^ (temp >>> 32));  ？？？
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDay=" + hireDay +
                '}';
    }
}
