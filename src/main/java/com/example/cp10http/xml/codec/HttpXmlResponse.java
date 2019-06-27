package com.example.cp10http.xml.codec;

import io.netty.handler.codec.http.FullHttpResponse;
import lombok.Data;

/**
 * @description:
 * @author: icecrea
 * @create: 2019-06-27 10:50
 **/
@Data
public class HttpXmlResponse {

    private FullHttpResponse httpResponse;
    private Object result;

    public HttpXmlResponse(FullHttpResponse httpResponse, Object result) {
        this.httpResponse = httpResponse;
        this.result = result;
    }
}
