/**
 *
 */
package com.vic.iot.common.properties;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SwaggerProperties {
    private String basePackage;
    private String title;
    private String description;
    private String contact;
    private String contactUrl;
    private String contactEmail;
    private String version;
}
