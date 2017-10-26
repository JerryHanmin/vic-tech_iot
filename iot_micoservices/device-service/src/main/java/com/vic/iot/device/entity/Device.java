package com.vic.iot.device.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Data
@ToString
@Document(collection = "device")
public class Device extends BaseEntity {
    @Id
    @Field("device_id")
    private String deviceId = UUID.randomUUID().toString();
    private String name;
    private String type;
    @Field("supplier_id")
    private String supplierId;

    private Map components;
}
