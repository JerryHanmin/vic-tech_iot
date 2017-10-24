package com.vic.user.entity;

import lombok.Data;


@Data
public class BaseEntity {
    private String createBy;
    private String modifyBy;
    private Long createTime = System.currentTimeMillis();
    private Long modifyTime = System.currentTimeMillis();
}
