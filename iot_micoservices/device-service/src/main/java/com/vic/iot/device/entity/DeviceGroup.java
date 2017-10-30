package com.vic.iot.device.entity;

import com.vic.iot.common.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@Document(collection = "device_group")
public class DeviceGroup extends BaseEntity {

    @Id
    private String groupId;
    private String name;
    private String description;
}
