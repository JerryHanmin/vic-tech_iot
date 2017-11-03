package com.vic.iot.device;

import com.google.common.collect.Maps;
import com.vic.iot.device.entity.Device;
import com.vic.iot.device.entity.Supplier;
import com.vic.iot.device.repository.DeviceRepository;
import com.vic.iot.device.repository.SupplierRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DeviceApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeviceTest {

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Test
    public void testCreateDefaultDevice() {
        deviceRepository.deleteAll();

        Device device = new Device();
        device.setDeviceId(UUID.randomUUID().toString());
        device.setName("device1");
        device.setType("tbox");
        device.setSupplierId(supplierRepository.findAll().iterator().next().getSupplierId());

        Map<String, String> components = Maps.newHashMap();
        components.put("sim", "13011110000");
        components.put("ICCID", "xxoo");
        device.setComponents(components);


        Map<String, String> deviceInfo = Maps.newHashMap();
        deviceInfo.put("model", "xx");
        device.setDeviceInfo(deviceInfo);

        Map<String, Object> info = Maps.newHashMap();
        info.put("test", "test");
        device.setAdditionalInfo(info);

        deviceRepository.save(device);
    }

    @Test
    public void testCreateDefaultSupplier() {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(UUID.randomUUID().toString());
        supplier.setName("xx公司");
        supplier.setAddress("成都");
        supplier.setDescription("xxxx");

        Map<String, Object> info = Maps.newHashMap();
        info.put("test", "test");
        supplier.setAdditionalInfo(info);

        supplierRepository.save(supplier);

    }

}
