package com.zhao.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-18 16:39
 * @描述  selector:获取通道支持的操作
 */
public class TestChannel_SelectionKey {

    public static void main(String[] args) throws IOException {
        SocketChannel client = SocketChannel.open();
        System.out.println(client.validOps());
        System.out.println(SelectionKey.OP_READ | SelectionKey.OP_WRITE | SelectionKey.OP_CONNECT);


        ServerSocketChannel server = ServerSocketChannel.open();
        System.out.println(server.validOps());
        System.out.println(SelectionKey.OP_ACCEPT);
    }
}