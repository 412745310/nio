package com.chelsea.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

/**
 * ServerSocketChannel测试类(服务器)
 * 
 * @author shevchenko
 *
 */
public class ServerSocketChannelTest {

    @Test
    public void test() {
        ServerSocketChannel serverSocketChannel = null;
        Selector selector = null;

        try {
            selector = Selector.open();

            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(90));

            // register to selector
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            // 轮询selector
            while (true) {
                System.out.println("[server] Selector select and blocking ...");
                selector.select(); // blocking,wait until 1 event

                Set<SelectionKey> keys = selector.selectedKeys(); // not .keys()
                Iterator<SelectionKey> iter = keys.iterator();
                while (iter.hasNext()) {
                    SelectionKey key = (SelectionKey) iter.next();
                    iter.remove();
                    // 以下判断最好使用 if else
                    if (key.isValid() == false) {
                        continue;
                    } else if (key.isAcceptable()) {
                        SocketChannel socketChannel = ((ServerSocketChannel) key.channel()).accept();
                        socketChannel.configureBlocking(false); // 继续设置为非阻塞模式（也可以是阻塞，直接处理）
                        socketChannel.register(selector, SelectionKey.OP_READ); // 继续向Selector注册
                    } else if (key.isReadable()) {
                        // start read
                        SocketChannel client = (SocketChannel) key.channel();

                        ByteBuffer bf = ByteBuffer.allocate(1000);
                        client.read(bf);
                        System.out.println("-->" + new String(bf.array(), "UTF-8"));
                        key.cancel();
                    } else if (key.isWritable()) {
                        // 不做处理
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                selector.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
