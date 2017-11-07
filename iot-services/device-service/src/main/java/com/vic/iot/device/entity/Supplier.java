package com.vic.iot.device.entity;

import com.vic.iot.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@Document(collection = "supplier")
public class Supplier extends BaseEntity {
    @Id
    private String supplierId = UUID.randomUUID().toString();
    private String name;
    private String type;
    private String address;
    private String description;
}
