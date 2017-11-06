package com.vic.iot.user.entity;

import com.vic.iot.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@Document(collection = "authority")
public class Authority extends BaseEntity {

    public Authority() {
    }

    public Authority(String authority) {
        this.authority = authority;
    }

    @Id
    private String authority;
    private String description;
}
