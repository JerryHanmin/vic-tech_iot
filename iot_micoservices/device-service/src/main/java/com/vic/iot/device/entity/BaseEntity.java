package com.vic.iot.device.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;


@Data
public class BaseEntity {
    @Field("create_by")
    private String createBy;

    @Field("modify_by")
    private String modifyBy;

    @Field("create_time")
    private Long createTime = System.currentTimeMillis();

    @Field("modify_time")
    private Long modifyTime = System.currentTimeMillis();

    @Field("additional_info")
    private Map additionalInfo;
}
