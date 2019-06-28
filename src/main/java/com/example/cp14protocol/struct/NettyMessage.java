package com.example.cp14protocol.struct;

import lombok.Data;

/**
 * @description:
 * @author: icecrea
 * @create: 2019-06-27 20:08
 **/
@Data
public final class NettyMessage {
    private Header header;

    private Object body;


}
