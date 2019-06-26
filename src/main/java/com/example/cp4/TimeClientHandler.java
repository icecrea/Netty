package com.example.cp4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @description:
 * @author: icecrea
 * @create: 2019-06-20 11:30
 **/
public class TimeClientHandler extends ChannelHandlerAdapter {

    private int counter;

    private byte[] req;

    public TimeClientHandler() {
        req = ("query time order" + System.getProperty("line.separator")).getBytes();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf msg;
        //循环发送一百次，每发送一条就更新一次,保证每条都写入channel。按照设计，服务端应该收到100次查询请求
        for (int i = 0; i < 100; i++) {
            msg = Unpooled.buffer(req.length);
            msg.writeBytes(req);
            ctx.writeAndFlush(msg);
        }
    }

    /**
     * 服务端返回信息时，打印
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        //正常情况，客户端应该打印100次服务端系统时间
        System.out.println("now is :" + body + "the counter is :" + ++counter);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exceptionCaught" + cause.getMessage());
        ctx.close();
    }


}
