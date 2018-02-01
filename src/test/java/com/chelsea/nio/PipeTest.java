package com.chelsea.nio;

import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.Pipe.SinkChannel;
import java.nio.channels.Pipe.SourceChannel;

import org.junit.Test;

/**
 * pipe管道测试类
 * 
 * @author shevchenko
 *
 */
public class PipeTest {

    @Test
    public void test() throws Exception {
        // 打开pipe
        Pipe pipe = Pipe.open();
        // 往pipe里写数据
        SinkChannel sinkChannel = pipe.sink();
        String newData = "New String to write to file..." + System.currentTimeMillis();
        ByteBuffer buf1 = ByteBuffer.allocate(48);
        buf1.clear();
        buf1.put(newData.getBytes());
        buf1.flip();
        while (buf1.hasRemaining()) {
            sinkChannel.write(buf1);
        }
        // 从pipe里读数据
        SourceChannel sourceChannel = pipe.source();
        ByteBuffer buf2 = ByteBuffer.allocate(48);
        sourceChannel.read(buf2);
        System.out.println(new String(buf2.array(), "UTF-8"));
    }
}
