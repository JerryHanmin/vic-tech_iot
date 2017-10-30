package com.vic.iot.device.repository;

import com.vic.iot.device.entity.DeviceGroup;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 处理数据到mongodb数据库
 *
 * @author hanmin
 */
public interface DeviceGroupRepository extends PagingAndSortingRepository<DeviceGroup, String> {
}
