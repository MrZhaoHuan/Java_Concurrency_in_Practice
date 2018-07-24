package com.zhao.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-18 16:39
 * @描述 io多路复用(1个selector管理多个通道)
 */
@SuppressWarnings("all")
public class TestMultipath_Reuse {

    public static void main(String[] args) throws IOException {
        //1个selector
        Selector selector = Selector.open();
        //3个socket通道
        SocketChannel socket1 = (SocketChannel) SocketChannel.open().bind(new InetSocketAddress(8080)).configureBlocking(false);
        SocketChannel socket2 = (SocketChannel) SocketChannel.open().bind(new InetSocketAddress(8081)).configureBlocking(false);
        SocketChannel socket3 = (SocketChannel) SocketChannel.open().bind(new InetSocketAddress(8082)).configureBlocking(false);
        //socket通道 注册到 selector上
        SelectionKey sk1 = socket1.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        SelectionKey sk2 = socket2.register(selector, SelectionKey.OP_READ);
        SelectionKey sk3 = socket3.register(selector, SelectionKey.OP_WRITE);

        System.out.println(sk1.interestOps()); //SelectionKey.OP_READ | SelectionKey.OP_WRITE

        //阻塞等待selectionkey就绪
        selector.select();
    }
}