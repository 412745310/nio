package com.chelsea.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import org.junit.Test;

/**
 * SocketChannel测试类(客户端)
 * 
 * @author shevchenko
 *
 */
public class SocketChannelTest {

    @Test
    public void test() {
        SocketChannel socketChannel = null;

        try {
            System.out.println("[client]start a client socket:");
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 90));

            while (true) {
                if (socketChannel.finishConnect()) {
                    System.out.println("[client]connected...");

                    ByteBuffer buffer = ByteBuffer.allocate(100);
                    buffer.put("Hello".getBytes("UTF-8"));
                    buffer.flip();
                    socketChannel.write(buffer);

                    break;
                }
            }
        } catch (Exception e) {
        } finally {
            try {
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
