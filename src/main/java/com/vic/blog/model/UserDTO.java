package com.vic.blog.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ToString
@Getter
@Setter
public class UserDTO {

    private  Integer id;

    @NotNull
    private String account;
    @NotNull
    private String name;
    @NotNull
    private String sex;
    @NotNull
    @Pattern(regexp = "^[0-9]{11}$")
    private String phone;
    @NotNull
    private String email;
}
