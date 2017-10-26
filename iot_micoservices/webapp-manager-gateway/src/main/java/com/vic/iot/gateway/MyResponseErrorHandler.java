package com.vic.iot.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@Slf4j
public class MyResponseErrorHandler implements org.springframework.web.client.ResponseErrorHandler {
    private HttpStatus httpStatus;
    private String responseStr;
    private byte[] responseByte;

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return hasError(getHttpStatusCode(response));
    }

    private HttpStatus getHttpStatusCode(ClientHttpResponse response) throws IOException {
        HttpStatus statusCode;
        try {
            statusCode = response.getStatusCode();
        } catch (IllegalArgumentException ex) {
            throw new UnknownHttpStatusCodeException(response.getRawStatusCode(),
                    response.getStatusText(), response.getHeaders(), getResponseBody(response), getCharset(response));
        }
        this.httpStatus = statusCode;
        return statusCode;
    }

    /**
     * Template method called from {@link #hasError(ClientHttpResponse)}.
     * <p>The default implementation checks if the given status code is
     * {@link HttpStatus.Series#CLIENT_ERROR CLIENT_ERROR}
     * or {@link HttpStatus.Series#SERVER_ERROR SERVER_ERROR}.
     * Can be overridden in subclasses.
     *
     * @param statusCode the HTTP status code
     * @return {@code true} if the response has an code; {@code false} otherwise
     */
    protected boolean hasError(HttpStatus statusCode) {
        return (statusCode.series() == HttpStatus.Series.CLIENT_ERROR ||
                statusCode.series() == HttpStatus.Series.SERVER_ERROR);
    }

    /**
     * This default implementation throws a {@link HttpClientErrorException} if the response status code
     * is {@link HttpStatus.Series#CLIENT_ERROR}, a {@link HttpServerErrorException}
     * if it is {@link HttpStatus.Series#SERVER_ERROR},
     * and a {@link RestClientException} in other cases.
     */
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        HttpStatus statusCode = getHttpStatusCode(response);

        switch (statusCode.series()) {
            case CLIENT_ERROR:
                throw new HttpClientErrorException(statusCode, response.getStatusText(),
                        response.getHeaders(), getResponseBody(response), getCharset(response));
            case SERVER_ERROR:
                throw new HttpServerErrorException(statusCode, response.getStatusText(),
                        response.getHeaders(), getResponseBody(response), getCharset(response));
            default:
                throw new RestClientException("Unknown status code [" + statusCode + "]");
        }
    }

    private byte[] getResponseBody(ClientHttpResponse response) {
        try {
            InputStream responseBody = response.getBody();
            if (responseBody != null) {
                responseByte = FileCopyUtils.copyToByteArray(responseBody);
                return responseByte;
            }
        } catch (IOException ex) {
            // ignore
        }
        return new byte[0];
    }

    private Charset getCharset(ClientHttpResponse response) {
        HttpHeaders headers = response.getHeaders();
        MediaType contentType = headers.getContentType();
        return contentType != null ? contentType.getCharSet() : null;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getResponseBody() {
        if (this.responseByte != null && this.responseByte.length > 0) {
            this.responseStr = new String(responseByte);
        }

        return responseStr;
    }

}
