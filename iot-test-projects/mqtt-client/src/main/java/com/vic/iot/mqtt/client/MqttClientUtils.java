package com.vic.iot.mqtt.client;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MqttClientUtils {


    public static final String HOST = "tcp://localhost:8883";
    public static final String TOPIC = "v1/devices/me/telemetry";
    private static final String CLIENT_ID = "client124";
    private static final String PASS_WORD = "";


    private MqttClient connect(String userName) throws Exception {
        // host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
        org.eclipse.paho.client.mqttv3.MqttClient client = new org.eclipse.paho.client.mqttv3.MqttClient(HOST, CLIENT_ID, new MemoryPersistence());
        // MQTT的连接设置
        MqttConnectOptions options = new MqttConnectOptions();
        // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
        options.setCleanSession(true);
        // 设置连接的用户名
        options.setUserName(userName);
        // 设置连接的密码
        options.setPassword(PASS_WORD.toCharArray());
        // 设置超时时间 单位为秒
        options.setConnectionTimeout(10);
        // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
        options.setKeepAliveInterval(20);
        // 设置回调
        client.setCallback(new PushCallback());
        client.connect(options);

        return client;
    }


    private void pubMsg(MqttClient client) {
        log.info("push msg start");
        try {

            Random random = new Random();
            int humidity = random.nextInt(50) + 20;
            int temperature = random.nextInt(25) + 10;
            int co = random.nextInt(50) + 10;
            int co2 = random.nextInt(25) + 10;
            int pm2_5 = random.nextInt(50) + 10;
            String msg = "{\"humidity\":" + humidity + ",\"temperature\":" + temperature +
                    ",\"co\":" + co + ",\"co2\":" + co2 + ",\"pm25\":" + pm2_5 + "}";
            client.publish(TOPIC, msg.getBytes(), 1, true);

            log.info(msg);

        } catch (Exception e) {
            e.printStackTrace();
        }


        log.info("push msg end");
    }

    public void run() throws Exception {
        MqttClient client = this.connect("A1");
        for (int i = 0; i < 10; i++) {
            this.pubMsg(client);
        }
    }
}
