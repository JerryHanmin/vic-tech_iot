package com.vic.user.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class User {
    @Id
    private String account;
    private String name;
    private int age;

}
