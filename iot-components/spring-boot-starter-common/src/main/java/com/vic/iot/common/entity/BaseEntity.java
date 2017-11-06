package com.vic.iot.common.entity;

import lombok.Data;

import java.util.Map;


@Data
public class BaseEntity {
    private String createBy;

    private String modifyBy;

    private Long createTime = System.currentTimeMillis();

    private Long modifyTime = System.currentTimeMillis();

    private Map<String, Object> additionalInfo;
}
