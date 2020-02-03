package com.jlxu.demo.jwt.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class User {
    private Long id;
    private String name;
    private String password;

}
