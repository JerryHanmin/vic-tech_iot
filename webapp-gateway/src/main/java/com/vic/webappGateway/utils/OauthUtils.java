package com.vic.webappGateway.utils;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OauthUtils {


    public static MultiValueMap<String, String> buildAouthHeader(String clientId, String clientSecret) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        List<String> auth = new ArrayList<>();
        String plainCreds = clientId + ":" + clientSecret;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        auth.add("Basic " + base64Creds);
        headers.put("Authorization", auth);
        return headers;
    }

    /**
     *
     * 将InputStream 转化为String
     *
     * @param stream
     *            inputstream
     * @param charset
     *            字符集
     * @return
     * @throws IOException
     */
    public static String getStreamAsString(InputStream stream, String charset) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset), 8192);
            StringWriter writer = new StringWriter();

            char[] chars = new char[8192];
            int count = 0;
            while ((count = reader.read(chars)) > 0) {
                writer.write(chars, 0, count);
            }

            return writer.toString();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }
}
