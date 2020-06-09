package com.example.cp12upd;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.ThreadLocalRandom;

/**
 * @description:
 * @author: icecrea
 * @create: 2019-06-27 17:20
 **/
public class ChineseProverbServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    // 谚语列表
    private static final String[] DICTIONARY = {"只要功夫深，铁棒磨成针。",
            "旧时王谢堂前燕，飞入寻常百姓家。", "洛阳亲友如相问，一片冰心在玉壶。", "一寸光阴一寸金，寸金难买寸光阴。",
            "老骥伏枥，志在千里。烈士暮年，壮心不已!"};

    private String nextQuote() {
        //netty 的线程安全随机类
        int quoteId = ThreadLocalRandom.current().nextInt(DICTIONARY.length);
        return DICTIONARY[quoteId];
    }

    /**
     * netty对udp进行了封装，接收到的是DatagramPacket对象
     *
     * @param ctx
     * @param packet
     * @throws Exception
     */
    @Override
    public void messageReceived(ChannelHandlerContext ctx, DatagramPacket packet)
            throws Exception {
        // ByteBuf.toString packet内容转字符串
        String req = packet.content().toString(CharsetUtil.UTF_8);
        System.out.println(req);
        if ("谚语字典查询?".equals(req)) {
            ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(
                    "谚语查询结果: " + nextQuote(), CharsetUtil.UTF_8), packet
                    .sender()));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.close();
        cause.printStackTrace();
    }
}
