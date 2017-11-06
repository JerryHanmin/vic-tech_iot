package com.vic.iot.common.utils;

import org.springframework.data.domain.Pageable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class PageQueryParaUtils {
    private PageQueryParaUtils() {
    }

    /**
     * 拼接url查询参数，以?a=xxx&b=xxx字符串形式返回url
     *
     * @param uri
     * @param key
     * @param value
     * @return
     */
    public static String buildUrl(String uri, String key, String value) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(key, value);
        return build(uri, params, false);
    }

    /**
     * 拼接url查询参数，以?a=xxx&b=xxx字符串形式返回url
     *
     * @param uri      uri
     * @param o        查询对象
     * @param pageable 分页对象
     * @return
     */
    public static String buildUrl(String uri, Object o, Pageable pageable) {
        return buildParam(uri, o, pageable, false);
    }

    /**
     * 拼接url查询参数，以?a=xxx&b=xxx字符串形式返回encode后的url
     *
     * @param uri      uri
     * @param o        查询对象
     * @param pageable 分页对象
     * @return
     */
    public static String buildEncodeUrl(String uri, Object o, Pageable pageable) {
        return buildParam(uri, o, pageable, true);
    }

    private static String buildParam(String uri, Object obj, Pageable pageable, boolean isEncodeUrl) {
        Map<String, String> map = new HashMap<>();
        if (null != pageable) {
            map.put("page", String.valueOf(pageable.getPageNumber()));
            map.put("size", String.valueOf(pageable.getPageSize()));
        }
        if (null != obj) {
//			Field[] fields = obj.getClass().getDeclaredFields();
            PropertyDescriptor propertyDescriptor = null;
//			Class<?> superclass = obj.getClass().getSuperclass();
            // obj循环向上转型
            for (Class<?> clz = obj.getClass(); clz != Object.class; clz = clz.getSuperclass()) {
                if (clz != null) {
                    Field[] superfields = clz.getDeclaredFields();
                    for (Field field : superfields) {
                        try {
                            propertyDescriptor = new PropertyDescriptor(field.getName(), obj.getClass());
                            Method getMethod = propertyDescriptor.getReadMethod();
                            Object value = getMethod.invoke(obj);
                            if (null != value) {
                                map.put(field.getName(), value.toString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        for (String key : map.keySet()) {
            params.add(key, map.get(key));
        }
        return build(uri, params, isEncodeUrl);
    }

    private static String build(String uri, MultiValueMap<String, String> params, boolean isEncodeUrl) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri).queryParams(params);
        if (!isEncodeUrl) {
            return builder.build().toUriString();
        } else {
            return builder.build().encode().toUriString();
        }
    }
}

