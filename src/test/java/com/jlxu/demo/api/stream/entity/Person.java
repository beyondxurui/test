package com.jlxu.demo.api.stream.entity;

import lombok.Data;

/**
 * 功能
 * 版本权限：浙江省公众信息产业有限公司智慧旅游（事业）部
 * 创建时间：2020年03月31日
 * 文件名称：Person
 * 版本：1.0.0
 * 最后修改时间：2020/3/31 15:51
 *
 * @auther ${许金李}
 */
@Data
public class Person {
    private String name;
    private Integer id;

    public Person(String name, Integer id) {
        this.name = name;
        this.id = id;
    }
}
