package com.chelsea.nio;

import java.nio.CharBuffer;

import org.junit.Test;

/**
 * buffer测试类
 * 
 * @author shevchenko
 *
 */
public class BufferTest {

    @Test
    public void test() {
        CharBuffer buff = CharBuffer.allocate(8);
        System.out.println("capacity:" + buff.capacity());
        System.out.println("limit:" + buff.limit());
        System.out.println("position:" + buff.position());
        buff.put("a");
        buff.put("b");
        buff.put("c");
        System.out.println("加入三个元素后,position=" + buff.position());
        buff.flip();
        System.out.println("执行flip后,limit=" + buff.limit());
        System.out.println("position=" + buff.position());
        // 取出第一个元素
        System.out.println("第一个元素(position=0):" + buff.get());
        System.out.println("取出第一个元素后,position=" + buff.position());
        buff.clear();
        System.out.println("执行clear方法后,limit=" + buff.limit());
        System.out.println("执行clear方法后,position=" + buff.position());
        System.out.println("执行clear后,缓冲区的内容并没有被清空.第三个元素为:" + buff.get(2));
        System.out.println("执行绝对读取后,position=" + buff.position());
    }

}
