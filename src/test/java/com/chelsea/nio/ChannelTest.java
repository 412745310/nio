package com.chelsea.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

import org.junit.Test;

/**
 * channel测试类
 * 
 * @author shevchenko
 *
 */
public class ChannelTest {

    @SuppressWarnings("resource")
    @Test
    public void testTransferFrom() throws Exception {
        RandomAccessFile fromFile = new RandomAccessFile("C:/Users/Administrator/Desktop/fromFile.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();
        RandomAccessFile toFile = new RandomAccessFile("C:/Users/Administrator/Desktop/toFile.txt", "rw");
        FileChannel toChannel = toFile.getChannel();
        long position = 1;
        long count = fromChannel.size();
        toChannel.transferFrom(fromChannel, position, count);
    }

    @SuppressWarnings("resource")
    @Test
    public void testTransferTo() throws Exception {
        RandomAccessFile fromFile = new RandomAccessFile("C:/Users/Administrator/Desktop/fromFile.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();
        RandomAccessFile toFile = new RandomAccessFile("C:/Users/Administrator/Desktop/toFile.txt", "rw");
        FileChannel toChannel = toFile.getChannel();
        long position = 1;
        long count = fromChannel.size();
        fromChannel.transferTo(position, count, toChannel);
    }
}
