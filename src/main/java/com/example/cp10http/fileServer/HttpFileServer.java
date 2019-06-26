package com.example.cp10http.fileServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @description:
 * @author: icecrea
 * @create: 2019-06-22 17:26
 **/
public class HttpFileServer {
    private static final String DEFAULT_URL = "/src/main/java/com/example/";

    public void run(final int port, final String url) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //http消息请求解码器
                            ch.pipeline().addLast("http-decoder", new HttpRequestDecoder());
                            //HttpObjectAggregator 将多个消息转换成单一的FullHttpRequest或者FullHttpResponse,因为http解码器在每个http消息中会生成多个消息对象
                            ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
                            ch.pipeline().addLast("http-encoder", new HttpResponseEncoder());
                            //支持异步发送大码流
                            ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
                            ch.pipeline().addLast("fileServerHandler", new HttpFileServerHandler(url));

                        }
                    });


            //bind绑定端口 sync同步阻塞等待绑定完成
            ChannelFuture f = b.bind("100.81.129.41", port).sync();
            System.out.println("HTTP文件目录服务器启动，网址是 : " + "http://100.81.129.41:" + port + url);

            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        new HttpFileServer().run(8080, DEFAULT_URL);
    }
}
