package com.jlxu.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能:HelloController
 * 创建时间：2020年01月03日
 * 版本：1.0.0
 * 最后修改时间：2020/1/3 23:40
 *
 * @auther ${许金李}
 */
@RestController
@RequestMapping("hello")
public class HelloController {

    @GetMapping("index")
    public String hello() {
        return "hello docker";
    }
}
