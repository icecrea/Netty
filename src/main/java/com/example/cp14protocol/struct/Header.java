package com.example.cp14protocol.struct;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: icecrea
 * @create: 2019-06-27 20:10
 **/
@Data
public final class Header {
    private int crcCode = 0xabef0101;

    /**
     * 消息长度
     */
    private int length;

    /**
     * 会话ID
     */
    private long sessionID;

    /**
     * 消息类型
     * 0请求 1响应 2业务One way既是请求又是响应 3握手请求 4握手应答 5心跳请求 6心跳应答
     */
    private byte type;

    /**
     * 消息优先级
     */
    private byte priority;

    /**
     * 附件
     */
    private Map<String, Object> attachment = new HashMap<String, Object>();
}
