package com.example.cp4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

/**
 * @description:
 * @author: icecrea
 * @create: 2019-06-20 11:48
 **/
public class TimeServerHandler extends ChannelHandlerAdapter {

    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8").substring(0, req.length -System.getProperty("line.separator").length());
        System.out.println("time server receive order:" + body + "; the counter is " + ++counter);
        String currentTime = "query time order".equalsIgnoreCase(body) ?
                new Date(System.currentTimeMillis()).toString() : "bad order";
        currentTime += System.getProperty("line.separator");

        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        //考虑性能，防止频繁唤醒selector进行消息发送，write方法不直接写入socketchannel，而发送到缓冲数组中，通过调用flush方法，写到socketchannel中
        ctx.write(resp);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
