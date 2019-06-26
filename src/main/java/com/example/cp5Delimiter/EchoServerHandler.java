package com.example.cp5Delimiter;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @description:
 * @author: icecrea
 * @create: 2019-06-20 22:45
 **/
public class EchoServerHandler extends ChannelHandlerAdapter {

    int counter = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("this is" + ++counter + "times receive client : [" + body + "]");
        //因为设置delimiterbasedFrameDecoder过滤了分隔符，所以返回客户端时需要在请求消息尾部拼接分隔符 $_
        body += "$_";

        ByteBuf resp = Unpooled.copiedBuffer(body.getBytes());
        ctx.writeAndFlush(resp);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
