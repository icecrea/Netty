package com.example.cp7serialize;

import com.example.cp7serialize.pojo.SubscribeReq;
import com.example.cp7serialize.pojo.SubscribeResp;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @description:
 * @author: icecrea
 * @create: 2019-06-28 17:23
 **/
public class SubReqServerHandler extends ChannelHandlerAdapter {
    /**
     * 经过解码器ObjectDecoder解码，SubReqServerHandler接到的消息已经自动解码成SubscribeReq对象,可以直接使用
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        SubscribeReq req = (SubscribeReq) msg;
        if ("Lilinfeng".equalsIgnoreCase(req.getUserName())) {
            System.out.println("Service accept client subscrib req : ["
                    + req.toString() + "]");
            ctx.writeAndFlush(resp(req.getSubReqID()));
        }
    }

    private SubscribeResp resp(int subReqID) {
        SubscribeResp resp = new SubscribeResp();
        resp.setSubReqID(subReqID);
        resp.setRespCode(0);
        resp.setDesc("Netty book order succeed, 3 days later, sent to the designated address");
        return resp;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();// 发生异常，关闭链路
    }
}
