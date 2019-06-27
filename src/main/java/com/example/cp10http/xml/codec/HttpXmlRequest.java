package com.example.cp10http.xml.codec;

import io.netty.handler.codec.http.FullHttpRequest;
import lombok.Data;

/**
 * @description:
 * @author: icecrea
 * @create: 2019-06-27 10:49
 **/
@Data
public class HttpXmlRequest {
    private FullHttpRequest request;
    private Object body;

    public HttpXmlRequest(FullHttpRequest request, Object body) {
        this.request = request;
        this.body = body;
    }

}
