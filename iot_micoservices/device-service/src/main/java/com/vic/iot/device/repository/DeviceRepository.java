package com.vic.iot.device.repository;

import com.vic.iot.device.entity.Device;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 处理数据到mongodb数据库
 *
 * @author hanmin
 */
public interface DeviceRepository extends PagingAndSortingRepository<Device, String> {
}
