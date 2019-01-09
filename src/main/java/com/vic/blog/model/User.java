package com.vic.blog.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class User {
    private int id;

    @NotEmpty(message = "用户名不能为空")
    private String userName;

    private String name;

    private String age;

    private String address;

    @Size(min = 6,max = 10,message = "密码长度必须6到10位")
    private String password;
}
