package com.vic.iot.device.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;
import java.util.UUID;

@Data
@ToString
@Document(collection = "supplier")
public class Supplier extends BaseEntity {
    @Id
    @Field("supplier_id")
    private String supplierId = UUID.randomUUID().toString();
    private String name;
    private String type;
    private String address;
    private String description;
}
