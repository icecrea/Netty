package com.example.cp12upd;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * @description:
 * @author: icecrea
 * @create: 2019-06-27 17:16
 **/
public class ChineseProverbClient {
    public void run(int port) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new ChineseProverbClientHandler());
            Channel ch = b.bind(0).sync().channel();
            // 向网段内的所有机器广播UDP消息
            // 由于不需要和服务端建立链路，udp channel创建完成后，客户端就要主动发送广播消息
            // 而TCP客户端是在链路建立成功之后，由客户端handler发送消息，这是两者最大区别
            ch.writeAndFlush(
                    new DatagramPacket(Unpooled.copiedBuffer("谚语字典查询?",
                            CharsetUtil.UTF_8), new InetSocketAddress(
                            "255.255.255.255", port))).sync();
            if (!ch.closeFuture().await(15000)) {
                System.out.println("查询超时!");
            }
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        new ChineseProverbClient().run(port);
    }
}
