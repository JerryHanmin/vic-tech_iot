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
@Document(collection = "role")
public class Role extends BaseEntity {
    @Id
    private String roleId;
    private String name;
    private String[] authorities;
}
