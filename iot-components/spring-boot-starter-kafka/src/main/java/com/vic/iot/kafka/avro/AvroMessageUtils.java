package com.vic.iot.kafka.avro;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.commons.lang.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class AvroMessageUtils {

    public static byte[] serializer(Schema schema, Object object) throws Exception {
        GenericRecord payload = new GenericData.Record(schema);

        // Put data in that genericrecord object
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            payload.put(field.getName(), getFieldValueByName(field.getName(), field.getType().getTypeName(), object));
        }
        log.info("serialized message : " + payload);

        // Serialize the object to a bytearray
        DatumWriter<GenericRecord> writer = new SpecificDatumWriter<>(schema);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(out, null);
        writer.write(payload, encoder);
        encoder.flush();
        out.close();
        return out.toByteArray();
    }

    public static <T> T deserializer(Schema schema, byte[] content, Class clazz) {
        DatumReader<GenericRecord> reader = new SpecificDatumReader<>(schema);
        Decoder decoder = DecoderFactory.get().binaryDecoder(content, null);
        GenericRecord message = null;
        try {
            message = reader.read(null, decoder);
        } catch (IOException e) {
            log.error("deserializer error", e);
        }

        assert message != null;
        return (T) JSONObject.toBean(JSONObject.fromObject(message.toString()), clazz);
    }

    private static Object getFieldValueByName(String fieldName, String fieldType, Object o) {
        String firstLetter = fieldName.substring(0, 1).toUpperCase();
        String getter = "get" + firstLetter + fieldName.substring(1);

        if (StringUtils.equals("boolean", fieldType))
            getter = "is" + firstLetter + fieldName.substring(1);

        Method method = null;
        try {
            method = o.getClass().getMethod(getter);
        } catch (NoSuchMethodException e) {
            log.error("no such method : [{0}]" + getter, e);
            return null;
        }

        Object value = null;
        try {
            value = method.invoke(o);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("get value error : [{0}]" + getter, e);
        }
        return value;
    }

}
