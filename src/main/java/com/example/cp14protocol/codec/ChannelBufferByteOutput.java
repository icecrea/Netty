package com.example.cp14protocol.codec;

import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.ByteOutput;

import java.io.IOException;

/**
 * @description:
 * @author: icecrea
 * @create: 2019-06-28 17:39
 **/
public class ChannelBufferByteOutput implements ByteOutput {
    private final ByteBuf buffer;


    public ChannelBufferByteOutput(ByteBuf buffer) {
        this.buffer = buffer;
    }

    @Override
    public void close() throws IOException {
        // Nothing to do
    }

    @Override
    public void flush() throws IOException {
        // nothing to do
    }

    @Override
    public void write(int b) throws IOException {
        buffer.writeByte(b);
    }

    @Override
    public void write(byte[] bytes) throws IOException {
        buffer.writeBytes(bytes);
    }

    @Override
    public void write(byte[] bytes, int srcIndex, int length) throws IOException {
        buffer.writeBytes(bytes, srcIndex, length);
    }

    /**
     * Return the {@link ByteBuf} which contains the written content
     *
     */
    ByteBuf getBuffer() {
        return buffer;
    }
}
